package com.meishi.test.service;

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

import com.meishi.MeishiApplication;
import com.meishi.model.Administrator;
import com.meishi.model.Rank;
import com.meishi.repository.AdminRepository;
import com.meishi.service.WorkerFinder;
import com.meishi.service.WorkerFinderImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { MeishiApplication.class })
public class WorkerFinderTest {

	@Autowired
	private AdminRepository adminRepo;

	private WorkerFinder<Administrator> workerFinder;

	@Before
	public void setUp() {
		Administrator admin1 = new Administrator();
		admin1.setName("admin1");
		admin1.setIdentity("admin1Identity");
		admin1.setLocation(new double[] { 4, 4 });
		adminRepo.save(admin1);

		Administrator admin2 = new Administrator();
		admin2.setName("admin2");
		admin2.setIdentity("admin2Identity");
		admin2.setLocation(new double[] { 5, 5 });
		adminRepo.save(admin2);
		
		Administrator admin3 = new Administrator();
		admin3.setName("admin3");
		admin3.setIdentity("admin3Identity");
		admin3.setRank(Rank.Rank5);
		admin3.setLocation(new double[] { 4, 3 });
		adminRepo.save(admin3);
		
		workerFinder = new WorkerFinderImpl<Administrator>(adminRepo);
	}

	@After
	public void tearDown() {
		adminRepo.deleteAll();
	}

	@Test
	public void testFindWorker() {
		Administrator result = workerFinder.findWorker(new Point(3, 3));
		Assert.assertEquals("admin3", result.getName());
	}

	@Test(expected = RuntimeException.class)
	public void testFindWorkerFailed() {
		workerFinder.findWorker(new Point(3, 3), new Distance(0.9));
	}
	
	@Test
	public void testFindWorerForHighestRank(){
		Administrator result = workerFinder.findWorker(new Point(3, 3));
		Assert.assertEquals("admin3", result.getName());
	}

}
