package com.meishi.workflow.task;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meishi.model.Administrator;
import com.meishi.model.Customer;
import com.meishi.model.Location;
import com.meishi.service.AdministratorService;
import com.meishi.service.CustomerService;

@Component
public class AssignAdminTask implements JavaDelegate {

	private static final Logger logger = Logger.getLogger(AssignAdminTask.class);

	@Autowired
	private CustomerService customerService;

	@Autowired
	private AdministratorService adminService;

	@Override
	public void execute(DelegateExecution exec) throws Exception {
		Location clientLocation = (Location) exec.getVariable("clientLocation");
		String clientId = (String) exec.getVariable("clientId");
		if (clientLocation == null) {
			Customer customer = customerService.find(clientId);
			clientLocation = customer.getAddress();
		}
		Administrator admin = adminService.getNearest(clientLocation);
		exec.setVariable("admin", admin);

		logger.info("assign admin to " + admin);
	}

}
