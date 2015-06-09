package com.meishi.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meishi.model.Customer;

@Component
public class MoneyServiceImpl implements MoneyService {

	private static Logger logger = Logger.getLogger(MoneyServiceImpl.class);

	private Map<String, Map<String, Double>> internalTransactions = Collections
			.synchronizedMap(new HashMap<String, Map<String, Double>>());

	@Autowired
	private CustomerService customerService;

	@Override
	public Double deposit(String identity, Double depositMoney) {
		Customer customer = getCustomer(identity);
		synchronized (customer) {
			customer.setAccountBalance(customer.getAccountBalance() + depositMoney);
			customerService.upsert(customer);
			return customer.getAccountBalance();
		}
	}

	private Customer getCustomer(String identity) {
		Customer customer = customerService.get(identity);
		if (customer == null) {
			throw new RuntimeException("can not proceed as we cannot find you : " + identity + " in system");
		} else {
			return customer;
		}
	}

	@Override
	public Double cost(String identity, Double costMoney) {
		Customer customer = getCustomer(identity);
		if (costMoney > customer.getAccountBalance()) {
			String errorMessage = "you account balance " + customer.getAccountBalance() + " less than what you cost "
					+ costMoney;
			logger.error(errorMessage);
			throw new RuntimeException(errorMessage);
		}
		synchronized (customer) {
			customer.setAccountBalance(customer.getAccountBalance() - costMoney);
			customerService.upsert(customer);
			return customer.getAccountBalance();
		}
	}

	// @Override
	// public Double credit(String identity, String orderId, Double creditMoney)
	// {
	// Map<String, Double> orderMoney = new HashMap<String, Double>();
	// Map<String, Double> existedOrderMoney =
	// internalTransactions.get(identity);
	// if(existedOrderMoney == null){
	// orderMoney.put(orderId, creditMoney);
	// internalTransactions.put(identity, orderMoney);
	// }
	//
	// }
	//
	// @Override
	// public Double succeed(String identity, String orderId) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public Double refund(String identity, String orderId) {
	// // TODO Auto-generated method stub
	// return null;
	// }

}
