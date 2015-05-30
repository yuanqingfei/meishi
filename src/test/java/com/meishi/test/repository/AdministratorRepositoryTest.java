package com.meishi.test.repository;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.meishi.MeishiApplication.RepositoryApplication;
import com.meishi.model.Administrator;
import com.meishi.model.Rank;
import com.meishi.model.WorkerStatus;
import com.meishi.repository.AdminRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { RepositoryApplication.class })
public class AdministratorRepositoryTest {

	@Autowired
	private AdminRepository adminRepo;

	private Administrator admin;

	private Administrator anotherAdmin;

	@Before
	public void setUp() {
		adminRepo.deleteAll();

		admin = new Administrator();
		admin.setIdentity("8888888");
		double[] point = new double[] { 5, 6 };
		admin.setLocation(point);
		admin.setRank(Rank.Rank5);

		anotherAdmin = new Administrator();
		anotherAdmin.setIdentity("999999999");
		double[] anotherPoint = new double[] { 6, 6 };
		anotherAdmin.setLocation(anotherPoint);
		Administrator existed = adminRepo.save(anotherAdmin);
		admin.getDirectWorkerIds().add(existed.getIdentity());

		adminRepo.save(admin);
	}

	@After
	public void tearDown() {
		adminRepo.deleteAll();
	}

	@Test
	public void testFindByStatus() {
		Assert.assertEquals(2, adminRepo.findByStatus(WorkerStatus.READY).size());
	}

	@Test
	public void testFindByRank() {
		Assert.assertEquals(1, adminRepo.findByRank(Rank.Rank5).size());
	}

	@Test
	public void testFindByIdentity() {
		Assert.assertNotNull(adminRepo.findByIdentity("8888888"));
	}

	@Test
	public void testFindByLocationNear() {
		Point point = new Point(5, 5);
		// Distance distance = new Distance(2, Metrics.KILOMETERS);
		Distance distance = new Distance(1);
		List<Administrator> admins = adminRepo.findByLocationNear(point, distance);
		Assert.assertEquals(1, admins.size());
	}

	@Test
	public void testFindByWorkerIdentity() {
		Assert.assertNotNull(adminRepo.findByWorker(anotherAdmin.getIdentity()));
	}

}
