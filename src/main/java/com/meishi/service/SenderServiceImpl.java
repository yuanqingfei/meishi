package com.meishi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meishi.model.Location;
import com.meishi.model.Rank;
import com.meishi.model.Sender;
import com.meishi.model.WorkerStatus;
import com.meishi.repository.SenderRepository;

@Component
public class SenderServiceImpl implements SenderService {

	@Autowired
	private SenderRepository senderRepo;

	@Override
	public Sender saveAndUpdate(Sender entity) {
		return senderRepo.save(entity);
	}

	@Override
	public void delete(String identity) {
		Sender sender = find(identity);
		senderRepo.delete(sender);
	}

	@Override
	public Boolean isExisted(String identity) {
		Sender sender = find(identity);
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
	public Sender getNearest(Location location) {
		String streetName = location.getStreetName();
		double x = location.getCoordinationX();
		double y = location.getCoordinationY();
		List<Sender> senders = getAllAvailable();
		double shortestDistance = 100;
		Sender selected = null;
		for (Sender sender : senders) {
			Location senderLocation = sender.getAddress();
			String senderStreetName = senderLocation.getStreetName();
			double senderX = senderLocation.getCoordinationX();
			double senderY = senderLocation.getCoordinationY();
			if (senderStreetName != null && senderStreetName.equalsIgnoreCase(streetName)) {
				return sender;
			}
			double currentDistance = Math.sqrt((senderX - x) * (senderX - x) + (senderY - y) * (senderY - y));
			if (currentDistance < shortestDistance) {
				shortestDistance = currentDistance;
				selected = sender;
			}
		}
		return selected;
	}

	@Override
	public List<Sender> getRankHighest() {
		return senderRepo.findByRank(Rank.Rank5);
	}

	@Override
	public void disable(String identity) {
		Sender sender = find(identity);
		sender.setStatus(WorkerStatus.DISABLE);
		senderRepo.save(sender);
	}

	@Override
	public Long count() {
		return senderRepo.count();
	}

	@Override
	public Sender find(String identity) {
		return senderRepo.findByIdentity(identity);
	}
}
