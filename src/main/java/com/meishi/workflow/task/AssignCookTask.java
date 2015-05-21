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
import com.meishi.model.Order;
import com.meishi.model.WorkerStatus;
import com.meishi.service.CookService;
import com.meishi.service.OrderService;

@Component
public class AssignCookTask implements JavaDelegate {
	
	private static final Logger logger = Logger.getLogger(AssignCookTask.class);
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CookService cookService;

	@Override
	public void execute(DelegateExecution exec) throws Exception {
		
		String[] meishiNames = ((String)exec.getVariable("meishiList")).split(",");
		Set<Cook> resultCooks = new HashSet<Cook>();
		for(String dishName : meishiNames){
			List<Cook> cooks = cookService.getByDish(dishName);
			resultCooks.addAll(cooks);
		}
		for(Cook cook : resultCooks){
			cook.setStatus(WorkerStatus.BUSY);
		}
		
		exec.setVariable("cooks", resultCooks);
		logger.info("###### assign cook to " + resultCooks );
		
	}

}
