package com.meishi.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import com.meishi.model.Rank;
import com.meishi.model.Sender;
import com.meishi.model.WorkerStatus;
import com.meishi.repository.SenderRepository;
import com.meishi.util.Constants;

@Component
public class SenderServiceImpl implements SenderService {

	@Autowired
	private SenderRepository senderRepo;

	private WorkerFinder<Sender> workerFinder;
	
	@Autowired
	private UserAndGroupService ugService;

	@PostConstruct
	public void setUpRepo() {
		workerFinder = new WorkerFinderImpl<Sender>(senderRepo);
	}

	@Override
	public Sender upsert(Sender entity) {
		Sender sender = senderRepo.save(entity);
		ugService.createUser(entity.getIdentity(), entity.getPassword(), Constants.SENDER_GROUP_ID);
		return sender;
	}

	@Override
	public void delete(String identity) {
		ugService.deleteUser(identity, Constants.SENDER_GROUP_ID);
		Sender sender = get(identity);
		senderRepo.delete(sender);
	}

	@Override
	public Boolean isExisted(String identity) {
		Sender sender = get(identity);
		return senderRepo.exists(sender.getId());
	}

	@Override
	public List<Sender> getAll() {
		return senderRepo.findAll();
	}

	@Override
	public List<Sender> getAll(WorkerStatus status) {
		return senderRepo.findByStatus(status);
	}

	@Override
	public List<Sender> getAllAvailable() {
		return senderRepo.findByStatus(WorkerStatus.READY);
	}

	@Override
	public List<Sender> getRankHighest() {
		return senderRepo.findByRank(Rank.Rank5);
	}

	@Override
	public void disable(String identity) {
		Sender sender = get(identity);
		sender.setStatus(WorkerStatus.DISABLE);
		senderRepo.save(sender);
	}

	@Override
	public Long count() {
		return senderRepo.count();
	}

	@Override
	public Sender get(String identity) {
		return senderRepo.findByIdentity(identity);
	}

	@Override
	public Sender selectByStatusLocationRank(Point location, Distance distance) {
		return workerFinder.findWorker(location, distance);
	}

	@Override
	public void occupy(String identity) {
		Sender entity = senderRepo.findByIdentity(identity);
		entity.setStatus(WorkerStatus.BUSY);
		senderRepo.save(entity);
	}

	@Override
	public void release(String identity) {
		Sender entity = senderRepo.findByIdentity(identity);
		entity.setStatus(WorkerStatus.READY);
		senderRepo.save(entity);
	}

	@Override
	public void deleteAll() {
		ugService.deleteAll(Constants.SENDER_GROUP_ID);
		senderRepo.deleteAll();
	}
}
