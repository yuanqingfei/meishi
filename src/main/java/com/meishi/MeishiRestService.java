package com.meishi;

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

@RestController
public class MeishiRestService {
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private MeishiService meishiService;

//	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/meishi/createOrder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void createOrder(@RequestBody Map<String, String> data) {
		logger.info("data: " + data);
		Map<String, Object> variables = new HashMap<String, Object>(data);
		meishiService.createOrder(variables);
	}

	@RequestMapping(value = "/getOrderProcessIds", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getOrderProcessIds() {
		return meishiService.getOrderProcessIds();
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/cookAcceptOrder", method = RequestMethod.POST)
	public void cookAcceptOrder() {
		meishiService.cookAcceptOrder();
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/cookDoneOrder", method = RequestMethod.POST)
	public void cookDoneOrder() {
		meishiService.cookDoneOrder();
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/senderAcceptOrder", method = RequestMethod.POST)
	public void senderAcceptOrder() {
		meishiService.senderAcceptOrder();
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/senderDoneOrder", method = RequestMethod.POST)
	public void senderDoneOrder() {
		meishiService.senderDoneOrder();
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/adminEsclateOrder", method = RequestMethod.POST)
	public void adminEsclateOrder() {
		meishiService.adminEsclateOrder();
	}

}
