package com.meishi.workflow.task;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import com.meishi.model.Administrator;
import com.meishi.model.Customer;
import com.meishi.service.AdministratorService;
import com.meishi.service.CustomerService;
import com.meishi.util.Constants;

@Component
public class AssignAdminTask implements JavaDelegate {

	private static final Logger logger = Logger.getLogger(AssignAdminTask.class);

	@Autowired
	private CustomerService customerService;

	@Autowired
	private AdministratorService adminService;

	@Override
	public void execute(DelegateExecution exec) throws Exception {
		String clientLocation = (String) exec.getVariable(Constants.CLIENT_LOCATION_VARIABLE);
		String clientId = (String) exec.getVariable(Constants.CLIENT_ID_VARIABLE);
		double[] location = new double[2] ;
		if (clientLocation == null || clientLocation.length() == 0) {
			Customer customer = customerService.get(clientId);
			location = customer.getLocation();
		} else {
			String[] adAndLoc = clientLocation.split(":");
			String[] locationArray = adAndLoc[1].split(",");
			location[0] = Double.valueOf(locationArray[0]);
			location[1] = Double.valueOf(locationArray[1]);
		}
		Point point = new Point(location[0], location[1]);

		Administrator admin = adminService.findByLocation(point, Constants.ADMIN_DISTANCE);
		exec.setVariable(Constants.ADMIN_VARIABLE, admin);

		logger.info("assign admin to " + admin);
	}

}
