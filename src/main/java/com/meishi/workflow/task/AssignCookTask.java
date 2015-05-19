package com.meishi.workflow.task;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.meishi.workflow.model.Cook;
import com.meishi.workflow.model.Order;
import com.meishi.workflow.model.OrderStatusEntry;
import com.meishi.workflow.repository.OrderRepository;

public class AssignCookTask implements JavaDelegate {
	
	private static final Logger logger = Logger.getLogger(AssignCookTask.class);

	@Override
	public void execute(DelegateExecution exec) throws Exception {
		
//		String clientLocatioin = (String)exec.getVariable("clientLocation");
//		exec.setVariable("cook", arg1);
		
		Cook cook = new Cook();
		cook.setIdentity("111111");
		cook.setName("Real_Cook");
		cook.setTelephoneNumber("222222");
		exec.setVariable("cook", cook);
		logger.info("###### assign cook to " + cook );
		
	}

}
