package com.meishi.workflow.task;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import com.meishi.model.Administrator;
import com.meishi.model.Constants;
import com.meishi.model.Customer;
import com.meishi.model.WorkerStatus;
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
		String clientLocation = (String) exec.getVariable("clientLocation");
		String clientId = (String) exec.getVariable("clientId");
		double[] location = new double[] {};
		if (clientLocation == null || clientLocation.length() == 0) {
			Customer customer = customerService.get(clientId);
			location = customer.getLocation();
		} else {
			String[] address = clientLocation.split(",");
			location[0] = Double.valueOf(address[0]);
			location[1] = Double.valueOf(address[1]);
		}
		Point point = new Point(location[0], location[1]);
		
		Administrator admin = adminService.selectByStatusLocationRank(point, Constants.DEFAULT_DISTANCE);
		exec.setVariable("admin", admin);
		
		adminService.occupy(admin.getIdentity());
		
		logger.info("assign admin to " + admin);
	}

}
