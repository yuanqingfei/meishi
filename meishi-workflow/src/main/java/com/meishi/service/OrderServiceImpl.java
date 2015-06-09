package com.meishi.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meishi.model.Order;
import com.meishi.repository.OrderRepository;

@Component
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepo;

	@Override
	public Order upsert(Order entity) {
		return orderRepo.save(entity);
	}

	@Override
	public Long count() {
		return orderRepo.count();
	}

	@Override
	public List<Order> getByCookIdentity(String cookIdentity) {
		return orderRepo.findByCook(cookIdentity);
	}

	@Override
	public Double getTotalPriceForCook(String cookIdentity) {
		return sumPrice(getByCookIdentity(cookIdentity));
	}

	private Double sumPrice(List<Order> orders) {
		Double totalPrice = 0.0;
		for (Order order : orders) {
			totalPrice += order.getTotalPrice();
		}
		return totalPrice;
	}

	@Override
	public Double getTotalPriceForCookFromTo(String cookIdentity, Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getTotalDealsForCook(String cookIdentity) {
		return getByCookIdentity(cookIdentity).size();
	}

	@Override
	public Integer getTotalDealsForCookFromTo(String cookIdentity, Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getByAdminIdentity(String adminIdentity) {
		return orderRepo.findByAdministrator_Identity(adminIdentity);
	}

	@Override
	public Double getTotalPriceForAdmin(String adminIdentity) {
		return sumPrice(getByAdminIdentity(adminIdentity));
	}

	@Override
	public Double getTotalPriceForAdminFromTo(String adminIdentity, Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getTotalDealsForAdmin(String adminIdentity) {
		return getByAdminIdentity(adminIdentity).size();
	}

	@Override
	public Integer getTotalDealsForAdminFromTo(String adminIdentity, Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getBySenderIdentity(String senderIdentity) {
		return orderRepo.findBySender(senderIdentity);
	}

	@Override
	public Double getTotalPriceForSender(String senderIdentity) {
		return sumPrice(getBySenderIdentity(senderIdentity));
	}

	@Override
	public Double getTotalPriceForSenderFromTo(String senderIdentity, Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getTotalDealsForSender(String senderIdentity) {
		return getBySenderIdentity(senderIdentity).size();
	}

	@Override
	public Integer getTotalDealsForSenderFromTo(String senderIdentity, Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getByCustomerIdentity(String customerIdentity) {
		return orderRepo.findByCustomer_Identity(customerIdentity);
	}

	@Override
	public Double getTotalPriceForCustomer(String customerIdentity) {
		return sumPrice(getByCustomerIdentity(customerIdentity));
	}

	@Override
	public Double getTotalPriceForCustomerFromTo(String customerIdentity, Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getTotalDealsForCustomer(String customerIdentity) {
		return getByCustomerIdentity(customerIdentity).size();
	}

	@Override
	public Integer getTotalDealsForCustomerFromTo(String customerIdentity, Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getTotalPrice() {
		return sumPrice(getAll());
	}

	@Override
	public Double getTotalPriceFromTo(Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order getHighestPrice() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order getLowestPrice() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getAll() {
		return orderRepo.findAll();
	}

	@Override
	public Order getByCustomerIdentityAndOrderTime(String identity, Date orderTime) {
		return orderRepo.findByCustomer_IdentityAndOrderTime(identity, orderTime);
	}

	@Override
	public void deleteAll() {
		orderRepo.deleteAll();
	}

	@Override
	public Order getOne(String orderId) {
		return orderRepo.findOne(orderId);
	}

}
