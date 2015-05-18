package com.meishi.workflow.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;

import com.meishi.workflow.model.Transporter;

public class AssignSenderService implements JavaDelegate {
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void execute(DelegateExecution exec) throws Exception {
		
//		String cook = (String)exec.getVariable("cook");
		
		// get the nearest sender according to cook location.
		
		
		//set sender
		Transporter sender = new Transporter();
		sender.setIdentity("111111");
		sender.setName("Real_Sender");
		sender.setTelephoneNumber("222222");
		exec.setVariable("sender", sender);
		logger.info("###### assign sender to " + sender );
	}

}
