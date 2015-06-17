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
	@RequestMapping(value = "/action/createOrder", method = RequestMethod.POST, consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8")
	public void createOrder(@RequestBody Map<String, Object> data, Principal principal) {
		meishiService.createOrder(data, principal.getName());
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/action/cookAcceptOrder", method = RequestMethod.POST, consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8")
	public void cookAcceptOrder(Principal principal) {
		meishiService.cookAcceptOrder(principal.getName());
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/action/cookDoneOrder", method = RequestMethod.POST, consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8")
	public void cookDoneOrder(Principal principal) {
		meishiService.cookDoneOrder(principal.getName());
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/action/senderAcceptOrder", method = RequestMethod.POST, consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8")
	public void senderAcceptOrder(Principal principal) {
		meishiService.senderAcceptOrder(principal.getName());
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/action/senderDoneOrder", method = RequestMethod.POST, consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8")
	public void senderDoneOrder(Principal principal) {
		meishiService.senderDoneOrder(principal.getName());
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/action/adminEsclateOrder", method = RequestMethod.POST, consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8")
	public void adminEsclateOrder(@RequestBody Map<String, Object> data, Principal principal) {
		meishiService.adminEsclateOrder(data, principal.getName());
	}

}
