package com.meishi.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meishi.workflow.WorkflowService;

@RestController
public class MeishiRestService {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private WorkflowService meishiService;

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/meishi/createOrder", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createOrder(@RequestBody Map<String, Object> data) {
		meishiService.createOrder(data);
	}

//	@RequestMapping(value = "/meishi/getOrderProcessIds", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public List<String> getOrderProcessIds() {
//		return meishiService.getOrderProcessIds();
//	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/meishi/cookAcceptOrder", method = RequestMethod.POST)
	public void cookAcceptOrder() {
		meishiService.cookAcceptOrder();
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/meishi/cookDoneOrder", method = RequestMethod.POST)
	public void cookDoneOrder() {
		meishiService.cookDoneOrder();
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/meishi/senderAcceptOrder", method = RequestMethod.POST)
	public void senderAcceptOrder() {
		meishiService.senderAcceptOrder();
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/meishi/senderDoneOrder", method = RequestMethod.POST)
	public void senderDoneOrder() {
		meishiService.senderDoneOrder();
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/meishi/adminEsclateOrder", method = RequestMethod.POST)
	public void adminEsclateOrder() {
		meishiService.adminEsclateOrder();
	}

}
