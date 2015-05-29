package com.meishi.service;

public interface MoneyService {
	
	/**
	 * save money to your account.
	 * @param identity
	 * @param depositMoney
	 * @return
	 */
	Double deposit(String identity, Double depositMoney);
	
	/**
	 * cost money from your account
	 * @param identity
	 * @param costMoney
	 * @return
	 */
	Double cost(String identity, Double costMoney);
	
	/**
	 * will deduct your money when you drop an order. This money will not really cost until
	 * this order is done. it will pair with succeed or refund method.
	 * @param identity
	 * @param creditMoney
	 * @return
	 */
//	Double credit(String identity, String orderId, Double creditMoney);
//	
//	Double succeed(String identity, String orderId);
//	
//	Double refund(String identity, String orderId);
	

}
