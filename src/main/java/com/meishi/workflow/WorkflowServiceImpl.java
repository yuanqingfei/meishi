package com.meishi.workflow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.User;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.meishi.model.Order;
import com.meishi.model.Worker;
import com.meishi.service.CookService;
import com.meishi.service.OrderService;
import com.meishi.service.SenderService;
import com.meishi.util.Constants;

@Component
public class WorkflowServiceImpl implements WorkflowService {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private IdentityService identityService;

	@Autowired
	private CookService cookService;
	
	@Autowired
	private SenderService senderService;

	@Autowired
	private OrderService orderService;

	@Override
	@Transactional
	public void createOrder(Map<String, Object> variables, String personIdentity) {
		runtimeService.startProcessInstanceByKey(Constants.PROCESS_ID).getId();
		String taskId = getTaskId(Constants.CLIENT_CREATE_TASK_ID, personIdentity);
		claimTask(taskId, personIdentity);
		taskService.complete(taskId, variables);
	}

	@Override
	@Transactional
	public void cookAcceptOrder(String personIdentity) {
		String taskId = getTaskId(Constants.COOK_ACCEPT_TASK_ID, personIdentity);
		checkCurrentAgainistRecommond(taskId, personIdentity, Constants.COOK_VARIABLE);
		taskService.setVariable(taskId, Constants.COOK_VARIABLE,
				Collections.singletonList(cookService.get(personIdentity)));
		claimTask(taskId, personIdentity);
		taskService.complete(taskId);
	}

	@Override
	@Transactional
	public void cookDoneOrder(String personIdentity) {
		String taskId = getTaskId(Constants.COOK_DONE_TASK_ID, personIdentity);
		claimTask(taskId, personIdentity);
		taskService.complete(taskId);
	}

	@Override
	@Transactional
	public void senderAcceptOrder(String personIdentity) {
		String taskId = getTaskId(Constants.SENDER_ACCEPT_TASK_ID, personIdentity);
		checkCurrentAgainistRecommond(taskId, personIdentity, Constants.SENDER_VARIABLE);
		taskService.setVariable(taskId, Constants.SENDER_VARIABLE,
				Collections.singletonList(senderService.get(personIdentity)));
		claimTask(taskId, personIdentity);
		taskService.complete(taskId);
	}

	@Override
	@Transactional
	public void senderDoneOrder(String personIdentity) {
		String taskId = getTaskId(Constants.SENDER_DONE_TASK_ID, personIdentity);
		claimTask(taskId, personIdentity);
		taskService.complete(taskId);
	}

	@Override
	@Transactional
	public void adminEsclateOrder(Map<String, Object> variables, String personIdentity) {
		String taskId = getTaskId(Constants.ADMIN_ESCLATE_TASK_ID, personIdentity);
		String orderId = (String) taskService.getVariable(taskId, Constants.ORDER_ID_VARIABLE);
		Order errorOrder = orderService.getOne(orderId);
		String errorOrderAdminId = errorOrder.getAdministrator().getIdentity();
		if (!errorOrderAdminId.equals(personIdentity)) {
			logger.error("current user " + " is not the admin of error order admin: " + errorOrderAdminId);
			throw new RuntimeException("Wrong Admin");
		} else {
			logger.info("current user is the right admin to esclate");
		}
		claimTask(taskId, personIdentity);
		taskService.complete(taskId, variables);

	}

	// only support one cook / one sender
	private void checkCurrentAgainistRecommond(String taskId, String userId, String variable) {
		List<Worker> selectedCooks = (List<Worker>) taskService.getVariable(taskId, variable);
		List<String> selectedIds = new ArrayList<String>();
		for (Worker selectedCook : selectedCooks) {
			selectedIds.add(selectedCook.getIdentity());
		}
		if (selectedIds.contains(userId)) {
			logger.info("Current user " + userId + " in the recommend list");
		} else {
			logger.warn("Current user " + userId + " not in the recommend list : " + selectedCooks);
		}
	}

	private void claimTask(String taskId, String userId) {
		User user = identityService.createUserQuery().userId(userId).singleResult();
		Assert.notNull(user, "current user is not existed: " + userId);
		taskService.claim(taskId, user.getId());
	}

	private String getTaskId(String taskDefId, String userId) {
		logger.info("taskDefId: " + taskDefId + "  userId: " + userId);

		List<Task> tasks = taskService.createTaskQuery().taskCandidateUser(userId).taskDefinitionKey(taskDefId)
				.orderByTaskPriority().desc().orderByTaskCreateTime().asc().list();
		if (tasks == null || tasks.size() == 0) {
			logger.warn("No task " + taskDefId + " for " + userId);
			return null;
		}

		String taskId = tasks.get(0).getId();
		return taskId;
	}

	@Transactional
	public List<String> getTasks(String userName) {
		List<Task> tasks = taskService.createTaskQuery().taskCandidateUser(userName).list();

		List<String> result = new ArrayList<String>();
		for (Task task : tasks) {
			result.add(task.getId() + "  " + task.getName());
		}
		return result;
	}

	@Transactional
	public List<String> getOrderProcessIds() {
		List<String> result = new ArrayList<String>();
		List<ProcessInstance> instances = runtimeService.createProcessInstanceQuery().list();
		for (ProcessInstance instance : instances) {
			result.add(instance.getId());
		}
		return result;
	}
}
