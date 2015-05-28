package com.meishi.rest;

import java.security.Principal;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	@RequestMapping(value = "/meishi/createOrder", method = RequestMethod.POST, consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8")
	public void createOrder(@RequestBody Map<String, Object> data, Principal principal) {
		meishiService.createOrder(data, principal.getName());
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/meishi/cookAcceptOrder", method = RequestMethod.POST)
	public void cookAcceptOrder(Principal principal) {
		meishiService.cookAcceptOrder(principal.getName());
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/meishi/cookDoneOrder", method = RequestMethod.POST)
	public void cookDoneOrder(Principal principal) {
		meishiService.cookDoneOrder(principal.getName());
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/meishi/senderAcceptOrder", method = RequestMethod.POST)
	public void senderAcceptOrder(Principal principal) {
		meishiService.senderAcceptOrder(principal.getName());
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/meishi/senderDoneOrder", method = RequestMethod.POST)
	public void senderDoneOrder(Principal principal) {
		meishiService.senderDoneOrder(principal.getName());
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/meishi/adminEsclateOrder", method = RequestMethod.POST)
	public void adminEsclateOrder(Principal principal) {
		meishiService.adminEsclateOrder(principal.getName());
	}

}
