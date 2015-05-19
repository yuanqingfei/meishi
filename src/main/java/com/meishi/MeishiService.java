package com.meishi;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class MeishiService {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private IdentityService identityService;

	private String processInstanceId;

	private static final String CLIENT_CREATE_TASK_ID = "clientDropOrder";
	private static final String COOK_ACCEPT_TASK_ID = "cookAcceptOrder";
	private static final String COOK_DONE_TASK_ID = "cookDoneOrder";
	private static final String SENDER_ACCEPT_TASK_ID = "senderAcceptOrder";
	private static final String SENDER_DONE_TASK_ID = "senderDoneOrder";
	private static final String ADMIN_ESCLATE_TASK_ID = "adminEsclateOrder";

	private static final String PROCESS_ID = "orderProcess";

	private static final String CLIENT_ID = "client1";
	private static final String COOK_ID = "cook1";
	private static final String SENDER_ID = "sender1";
	private static final String ADMIN_ID = "admin1";

	@Transactional
	public void createOrder(Map<String, Object> variables) {
		logger.info("variable: " + variables);
		logger.info("runtimeSerivice: " + runtimeService);
		processInstanceId = runtimeService.startProcessInstanceById(PROCESS_ID).getId();
		logger.info("Instance Id : " + processInstanceId);
//		 processInstanceId =
//		 runtimeService.startProcessInstanceByKey(PROCESS_ID).getId();
		User user = identityService.createUserQuery().userId(CLIENT_ID).singleResult();
		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).active()
				.taskId(CLIENT_CREATE_TASK_ID).singleResult();

		Assert.notNull(user);
		Assert.notNull(task);

		String taskId = task.getId();
		taskService.claim(taskId, user.getId());
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

	@Transactional
	public void cookAcceptOrder() {
		completeTask(COOK_ACCEPT_TASK_ID, COOK_ID);
	}

	@Transactional
	public void cookDoneOrder() {
		completeTask(COOK_DONE_TASK_ID, COOK_ID);
	}

	@Transactional
	public void senderAcceptOrder() {
		completeTask(SENDER_ACCEPT_TASK_ID, SENDER_ID);
	}

	@Transactional
	public void senderDoneOrder() {
		completeTask(SENDER_DONE_TASK_ID, SENDER_ID);
	}

	@Transactional
	public void adminEsclateOrder() {
		completeTask(ADMIN_ESCLATE_TASK_ID, ADMIN_ID);
	}

	private void completeTask(String taskDefId, String userId) {
		logger.info("taskDefId: " + taskDefId + "  userId: " + userId);
		
		User user = identityService.createUserQuery().userId(userId).singleResult();
		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).active().taskId(taskDefId)
				.singleResult();

		Assert.notNull(user);
		Assert.notNull(task);

		String taskId = task.getId();
		taskService.claim(taskId, user.getId());
		taskService.complete(taskId);
	}

}
