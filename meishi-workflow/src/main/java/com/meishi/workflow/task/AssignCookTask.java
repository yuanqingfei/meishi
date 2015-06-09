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
import com.meishi.util.Constants;

@Component
public class AssignCookTask implements JavaDelegate {

	private static final Logger logger = Logger.getLogger(AssignCookTask.class);

	@Autowired
	private CookService cookService;

	@Override
	public void execute(DelegateExecution exec) throws Exception {
		
		// for re-cooking
		List<Cook> previousCooks = (List<Cook>)exec.getVariable(Constants.COOK_VARIABLE);
		if(previousCooks != null && previousCooks.size() > 0){
			for(Cook cook : previousCooks){
				cookService.release(cook.getIdentity());
			}
		}

		String[] meishiNames = ((String[]) exec.getVariable(Constants.FOOD_ARRAY_VARIABLE));

		// use set to filter duplicate cook
		Set<Cook> resultCooks = new HashSet<Cook>();
		for (String dishName : meishiNames) {
			List<Cook> cooks = cookService.getByDish(dishName);
			resultCooks.addAll(cooks);
		}

		exec.setVariable(Constants.COOK_VARIABLE, new ArrayList<Cook>(resultCooks));
	}

}
