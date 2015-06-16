package com.meishi.util;

import org.springframework.data.geo.Distance;

public interface Constants {
	
	String ADMIN_GROUP_ID = "admin";
	String COOK_GROUP_ID = "cook";
	String SENDER_GROUP_ID = "sender";
	String CLIENT_GROUP_ID = "client";
	

	Distance DEFAULT_DISTANCE = new Distance(2.0);

	Distance ADMIN_DISTANCE = new Distance(10.0);

	String ADMIN_VARIABLE = "admin";

	String COOK_VARIABLE = "cooks";

	String SENDER_VARIABLE = "senders";

	String ORDER_ID_VARIABLE = "orderId";

	String CLIENT_ID_VARIABLE = "clientId";

	String CLIENT_LOCATION_VARIABLE = "clientLocation";

	String CLIENT_LOCATION_SEPARATER_VARIABLE = ",";

	String FOOD_LIST_VARIABLE = "meishiList";

	String FOOD_ARRAY_VARIABLE = "meishiNames";

	String FOOD_SEPARATER_VARIABLE = ",";

	String CLIENT_CREATE_TASK_ID = "clientDropOrder";
	
	String COOK_ACCEPT_TASK_ID = "cookAcceptOrder";
	
	String COOK_DONE_TASK_ID = "cookDoneOrder";
	
	String SENDER_ACCEPT_TASK_ID = "senderAcceptOrder";
	
	String SENDER_DONE_TASK_ID = "senderDoneOrder";
	
	String ADMIN_ESCLATE_TASK_ID = "adminEsclateOrder";
	
	String PROCESS_ID = "orderProcess";

	String REFUND = "refund";

	String TOTAL_PRICE_VARIABLE = "totalPrice";

}
