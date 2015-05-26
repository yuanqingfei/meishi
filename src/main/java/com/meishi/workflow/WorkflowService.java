package com.meishi.workflow;

import java.util.Map;

public interface WorkflowService {

	void createOrder(Map<String, Object> variables, String personIdentity);

	void cookAcceptOrder(String personIdentity);

	void cookDoneOrder(String personIdentity);

	void senderAcceptOrder(String personIdentity);

	void senderDoneOrder(String personIdentity);

	void adminEsclateOrder(String personIdentity);

}