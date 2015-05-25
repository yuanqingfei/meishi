package com.meishi.workflow;

import java.util.Map;

public interface WorkflowService {

	void createOrder(Map<String, Object> variables);

	void cookAcceptOrder();

	void cookDoneOrder();

	void senderAcceptOrder();

	void senderDoneOrder();

	void adminEsclateOrder();

}