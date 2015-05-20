package com.meishi.workflow;

import java.util.Map;

public interface WorkflowService {

	public abstract void createOrder(Map<String, Object> variables);

	public abstract void cookAcceptOrder();

	public abstract void cookDoneOrder();

	public abstract void senderAcceptOrder();

	public abstract void senderDoneOrder();

	public abstract void adminEsclateOrder();

}