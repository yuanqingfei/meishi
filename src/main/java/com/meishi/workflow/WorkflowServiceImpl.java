package com.meishi.workflow;

import java.util.ArrayList;
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

	private static final String CLIENT_CREATE_TASK_ID = "clientDropOrder";
	private static final String COOK_ACCEPT_TASK_ID = "cookAcceptOrder";
	private static final String COOK_DONE_TASK_ID = "cookDoneOrder";
	private static final String SENDER_ACCEPT_TASK_ID = "senderAcceptOrder";
	private static final String SENDER_DONE_TASK_ID = "senderDoneOrder";
	private static final String ADMIN_ESCLATE_TASK_ID = "adminEsclateOrder";

	private static final String PROCESS_ID = "orderProcess";

	private void startProcess() {
		String id = runtimeService.startProcessInstanceByKey(PROCESS_ID).getId();
		logger.info("instance id: " + id);
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

	@Override
	@Transactional
	public void createOrder(Map<String, Object> variables, String personIdentity) {
		startProcess();
		String taskId = claimTask(CLIENT_CREATE_TASK_ID, personIdentity);
		taskService.complete(taskId, variables);
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

	@Override
	@Transactional
	public void cookAcceptOrder(String personIdentity) {
		completeTask(COOK_ACCEPT_TASK_ID, personIdentity);
	}

	@Override
	@Transactional
	public void cookDoneOrder(String personIdentity) {
		completeTask(COOK_DONE_TASK_ID, personIdentity);
	}

	@Override
	@Transactional
	public void senderAcceptOrder(String personIdentity) {
		completeTask(SENDER_ACCEPT_TASK_ID, personIdentity);
	}

	@Override
	@Transactional
	public void senderDoneOrder(String personIdentity) {
		completeTask(SENDER_DONE_TASK_ID, personIdentity);
	}

	@Override
	@Transactional
	public void adminEsclateOrder(String personIdentity) {
		completeTask(ADMIN_ESCLATE_TASK_ID, personIdentity);
	}

	private void completeTask(String taskDefId, String userId) {
		String taskId = claimTask(taskDefId, userId);
		taskService.complete(taskId);
	}

	private String claimTask(String taskDefId, String userId) {
		logger.info("taskDefId: " + taskDefId + "  userId: " + userId);

		User user = identityService.createUserQuery().userId(userId).singleResult();
		List<Task> tasks = taskService.createTaskQuery().taskCandidateUser(userId).taskDefinitionKey(taskDefId)
				.orderByTaskPriority().desc().orderByTaskCreateTime().asc().list();

		Assert.notNull(user);

		if (tasks == null || tasks.size() == 0) {
			logger.warn("No task " + taskDefId + " for " + userId);
			return null;
		}

		String taskId = tasks.get(0).getId();
		taskService.claim(taskId, user.getId());
		return taskId;
	}
}
