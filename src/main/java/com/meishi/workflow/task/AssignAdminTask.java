package com.meishi.workflow.task;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;

import com.meishi.workflow.model.Administrator;

public class AssignAdminTask implements JavaDelegate {
	
	private static final Logger logger = Logger.getLogger(AssignAdminTask.class); 

	@Override
	public void execute(DelegateExecution exec) throws Exception {
//		String clientLocatioin = (String)exec.getVariable("clientLocation");
//		List<Admin> adminList = xx.getAdmin();
//		for(Admin ad : adminList){
//			
//		}
		
		//set admin
//		exec.setVariable("admin", arg1);
		
		// just for show
		Administrator admin = new Administrator();
		admin.setName("MyAdmin");
		exec.setVariable("admin", admin);
		logger.info("###### assign admin to " + admin );

	}

}
