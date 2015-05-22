package com.meishi.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meishi.model.Cook;
import com.meishi.model.Order;
import com.meishi.model.Sender;
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
	public void delete(String identity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Order get(String identity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isExisted(String identity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getByCookIdentity(String cookIdentity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getTotalPriceForCook(String cookIdentity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getTotalPriceForCookFromTo(String cookIdentity, Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getTotalDealsForCook(String cookIdentity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getTotalDealsForCookFromTo(String cookIdentity, Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getByAdminIdentity(String adminIdentity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getTotalPriceForAdmin(String adminIdentity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getTotalPriceForAdminFromTo(String adminIdentity, Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getTotalDealsForAdmin(String adminIdentity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getTotalDealsForAdminFromTo(String adminIdentity, Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getBySenderIdentity(String senderIdentity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getTotalPriceForSender(String senderIdentity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getTotalPriceForSenderFromTo(String senderIdentity, Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getTotalDealsForSender(String senderIdentity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getTotalDealsForSenderFromTo(String senderIdentity, Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getByCustomerIdentity(String customerIdentity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getTotalPriceForCustomer(String customerIdentity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getTotalPriceForCustomerFromTo(String customerIdentity, Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getTotalDealsForCustomer(String customerIdentity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getTotalDealsForCustomerFromTo(String customerIdentity, Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getTotalPrice() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order getByCustomerIdentityAndOrderTime(String identity, Date orderTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getOrdersByCook(Cook cook) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getOrdersByCook(String cookIdentity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getOrdersBySender(Sender sender) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getOrdersBySender(String senderIdentity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order getOne(String id) {
		return orderRepo.findOne(id);
	}

	@Override
	public void deleteAll() {
		orderRepo.deleteAll();

	}

}
