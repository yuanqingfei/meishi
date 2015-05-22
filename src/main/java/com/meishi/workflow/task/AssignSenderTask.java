package com.meishi.workflow.task;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import com.meishi.model.Constants;
import com.meishi.model.Cook;
import com.meishi.model.Order;
import com.meishi.model.Sender;
import com.meishi.service.OrderService;
import com.meishi.service.SenderService;

@Component
public class AssignSenderTask implements JavaDelegate {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private SenderService senderService;

	@Override
	public void execute(DelegateExecution exec) throws Exception {
		List<Cook> cooks = (List<Cook>) exec.getVariable("cooks");

		List<Sender> senders = new ArrayList<Sender>();
		for (Cook cook : cooks) {
			double[] cookAddress = cook.getLocation();
			Sender sender = senderService.selectByStatusLocationRank(new Point(cookAddress[0], cookAddress[1]),
					Constants.DEFAULT_DISTANCE);
			senders.add(sender);
		}

		exec.setVariable("senders", senders);
		logger.info("###### assign sender to " + senders);
	}

}
