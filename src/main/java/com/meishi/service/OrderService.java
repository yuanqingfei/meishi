package com.meishi.service;

import java.util.Date;
import java.util.List;

import com.meishi.model.Cook;
import com.meishi.model.Order;
import com.meishi.model.Sender;

public interface OrderService extends Crud<Order> {
	
	public Order getOne(String id);

	public Order getByCustomerIdentityAndOrderTime(String identity, Date orderTime);
	
	
	public List<Order> getOrdersByCook(Cook cook);
	
	public List<Order> getOrdersByCook(String cookIdentity);
	
	public List<Order> getOrdersBySender(Sender sender);
	
	public List<Order> getOrdersBySender(String senderIdentity);
	

	public abstract List<Order> getByCookIdentity(String cookIdentity);
	
	public abstract Double getTotalPriceForCook(String cookIdentity);
	
	public abstract Double getTotalPriceForCookFromTo(String cookIdentity, Date from, Date to);
	
	public abstract Integer getTotalDealsForCook(String cookIdentity);
	
	public abstract Integer getTotalDealsForCookFromTo(String cookIdentity, Date from, Date to);
	
	
	public abstract List<Order> getByAdminIdentity(String adminIdentity);
	
	public abstract Double getTotalPriceForAdmin(String adminIdentity);
	
	public abstract Double getTotalPriceForAdminFromTo(String adminIdentity, Date from, Date to);
	
	public abstract Integer getTotalDealsForAdmin(String adminIdentity);
	
	public abstract Integer getTotalDealsForAdminFromTo(String adminIdentity, Date from, Date to);
	
	
	public abstract List<Order> getBySenderIdentity(String senderIdentity);
	
	public abstract Double getTotalPriceForSender(String senderIdentity);
	
	public abstract Double getTotalPriceForSenderFromTo(String senderIdentity, Date from, Date to);
	
	public abstract Integer getTotalDealsForSender(String senderIdentity);
	
	public abstract Integer getTotalDealsForSenderFromTo(String senderIdentity, Date from, Date to);
	
	
	public abstract List<Order> getByCustomerIdentity(String customerIdentity);
	
	public abstract Double getTotalPriceForCustomer(String customerIdentity);
	
	public abstract Double getTotalPriceForCustomerFromTo(String customerIdentity, Date from, Date to);
	
	public abstract Integer getTotalDealsForCustomer(String customerIdentity);
	
	public abstract Integer getTotalDealsForCustomerFromTo(String customerIdentity, Date from, Date to);
	
	
	public abstract Double getTotalPrice();
	
	public abstract Double getTotalPriceFromTo(Date from, Date to);
	
	public abstract Order getHighestPrice();
	
	public abstract Order getLowestPrice();
 	
}
