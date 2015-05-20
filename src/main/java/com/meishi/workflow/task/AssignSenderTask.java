package com.meishi.workflow.task;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.meishi.model.Sender;

@Component
public class AssignSenderTask implements JavaDelegate {
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void execute(DelegateExecution exec) throws Exception {
		
		//set sender
		Sender sender = new Sender();
		sender.setIdentity("111111");
		sender.setName("Real_Sender");
		sender.setTelephoneNumber("222222");
		exec.setVariable("sender", sender);
		logger.info("###### assign sender to " + sender );
	}

}
