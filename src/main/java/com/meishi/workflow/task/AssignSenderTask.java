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

import com.meishi.model.Cook;
import com.meishi.model.Order;
import com.meishi.model.Sender;
import com.meishi.model.WorkerStatus;
import com.meishi.service.OrderService;
import com.meishi.service.SenderService;

@Component
public class AssignSenderTask implements JavaDelegate {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private OrderService orderService;

	@Autowired
	private SenderService senderService;

	@Override
	public void execute(DelegateExecution exec) throws Exception {
		List<Cook> cooks = (List<Cook>) exec.getVariable("cooks");
		Cook cook = cooks.get(0);
		double[] cookAddress = cook.getLocation();

		Sender sender = senderService.selectByStatusLocationRank(new Point(cookAddress[0], cookAddress[1]),
				new Distance(2));
		sender.setStatus(WorkerStatus.BUSY);
		
		Order order = orderService.get("orderId");
		
		List<Sender> senders = new ArrayList<Sender>();
		senders.add(sender);
		order.setSenders(senders);
		
		orderService.upsert(order);

		exec.setVariable("sender", sender);
		logger.info("###### assign sender to " + sender);
	}

}
