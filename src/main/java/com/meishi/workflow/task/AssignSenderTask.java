package com.meishi.workflow.task;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import com.meishi.model.Cook;
import com.meishi.model.Sender;
import com.meishi.service.SenderService;
import com.meishi.util.Constants;

@Component
public class AssignSenderTask implements JavaDelegate {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private SenderService senderService;

	@Override
	public void execute(DelegateExecution exec) throws Exception {
		
		// for re-sender
		List<Sender> previousSenders = (List<Sender>)exec.getVariable(Constants.SENDER_VARIABLE);
		if(previousSenders != null && previousSenders.size() > 0){
			for(Sender cook : previousSenders){
				senderService.release(cook.getIdentity());
			}
		}
		
		List<Cook> cooks = (List<Cook>) exec.getVariable(Constants.COOK_VARIABLE);

		List<Sender> senders = new ArrayList<Sender>();
		for (Cook cook : cooks) {
			double[] cookAddress = cook.getLocation();
			Sender sender = senderService.selectByStatusLocationRank(new Point(cookAddress[0], cookAddress[1]),
					Constants.DEFAULT_DISTANCE);
			senders.add(sender);
		}

		exec.setVariable(Constants.SENDER_VARIABLE, senders);
	}

}
