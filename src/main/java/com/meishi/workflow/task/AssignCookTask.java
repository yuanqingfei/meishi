package com.meishi.workflow.task;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meishi.model.Cook;
import com.meishi.service.CookService;
import com.meishi.service.OrderService;

@Component
public class AssignCookTask implements JavaDelegate {

	private static final Logger logger = Logger.getLogger(AssignCookTask.class);

	@Autowired
	private CookService cookService;

	@Override
	public void execute(DelegateExecution exec) throws Exception {

		String[] meishiNames = ((String[]) exec.getVariable("meishiNames"));

		// use set to filter duplicate cook
		Set<Cook> resultCooks = new HashSet<Cook>();
		for (String dishName : meishiNames) {
			List<Cook> cooks = cookService.getByDish(dishName);
			resultCooks.addAll(cooks);
		}

		exec.setVariable("cooks", new ArrayList<Cook>(resultCooks));
		logger.info("###### assign cook to " + resultCooks);

	}

}
