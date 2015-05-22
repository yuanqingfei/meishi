package com.meishi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import com.meishi.model.Rank;
import com.meishi.model.Sender;
import com.meishi.model.WorkerStatus;
import com.meishi.repository.SenderRepository;

@Component
public class SenderServiceImpl implements SenderService {
	
	private WorkerFinder workerFinder;

	@Autowired
	private SenderRepository senderRepo;

	@Override
	public Sender upsert(Sender entity) {
		return senderRepo.save(entity);
	}

	@Override
	public void delete(String identity) {
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
		workerFinder = new WorkerFinderImpl(senderRepo);
		return (Sender) workerFinder.findWorker(location, distance);
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
		senderRepo.deleteAll();
	}
}
