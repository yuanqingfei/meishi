package com.meishi.workflow.rest;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ClientService {
	
    @Autowired
    private RuntimeService runtimeService;
    

    @Autowired
    private TaskService taskService;

    
    @RequestMapping(value="/startOrder", method= RequestMethod.POST)
    public void startProcessInstance() {
    	runtimeService.startProcessInstanceByKey("myProcess");
    }

}
