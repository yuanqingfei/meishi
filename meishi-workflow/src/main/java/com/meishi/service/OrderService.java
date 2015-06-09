package com.meishi.service;

import java.util.Date;
import java.util.List;

import com.meishi.model.Order;

public interface OrderService {
	
	Order getByCustomerIdentityAndOrderTime(String identity, Date orderTime);
	
	
	List<Order> getByCookIdentity(String cookIdentity);
	
	Double getTotalPriceForCook(String cookIdentity);
	
	Double getTotalPriceForCookFromTo(String cookIdentity, Date from, Date to);
	
	Integer getTotalDealsForCook(String cookIdentity);
	
	Integer getTotalDealsForCookFromTo(String cookIdentity, Date from, Date to);
	
	
	List<Order> getByAdminIdentity(String adminIdentity);
	
	Double getTotalPriceForAdmin(String adminIdentity);
	
	Double getTotalPriceForAdminFromTo(String adminIdentity, Date from, Date to);
	
	Integer getTotalDealsForAdmin(String adminIdentity);
	
	Integer getTotalDealsForAdminFromTo(String adminIdentity, Date from, Date to);
	
	
	List<Order> getBySenderIdentity(String senderIdentity);
	
	Double getTotalPriceForSender(String senderIdentity);
	
	Double getTotalPriceForSenderFromTo(String senderIdentity, Date from, Date to);
	
	Integer getTotalDealsForSender(String senderIdentity);
	
	Integer getTotalDealsForSenderFromTo(String senderIdentity, Date from, Date to);
	
	
	List<Order> getByCustomerIdentity(String customerIdentity);
	
	Double getTotalPriceForCustomer(String customerIdentity);
	
	Double getTotalPriceForCustomerFromTo(String customerIdentity, Date from, Date to);
	
	Integer getTotalDealsForCustomer(String customerIdentity);
	
	Integer getTotalDealsForCustomerFromTo(String customerIdentity, Date from, Date to);
	
	
	Double getTotalPrice();
	
	Double getTotalPriceFromTo(Date from, Date to);
	
	Order getHighestPrice();
	
	Order getLowestPrice();


	Order upsert(Order entity);
	Long count();
	void deleteAll();
	List<Order> getAll();


	Order getOne(String orderId);
 	
}
