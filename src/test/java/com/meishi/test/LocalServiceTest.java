package com.meishi.test;

import java.util.List;

import org.junit.Assert;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.meishi.workflow.MeishiApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { MeishiApplication.class })
// @WebAppConfiguration
@IntegrationTest
public class LocalServiceTest {
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Test
	public void testRuntime() {
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("orderProcess");
		Assert.assertEquals(1, runtimeService.createProcessInstanceQuery().count());
//		Assert.assertEquals("orderProcess",  processInstance.getProcessDefinitionKey());
	}
	
	@Test
	public void testTask(){
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("orderProcess");
		List<Task> tasks = taskService.createTaskQuery().list();
		for (Task task : tasks) {
			logger.info(task.getName());
		}
	}

}
