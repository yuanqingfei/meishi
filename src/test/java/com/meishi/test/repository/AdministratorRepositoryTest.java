package com.meishi.test.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.meishi.model.Administrator;
import com.meishi.model.Location;
import com.meishi.model.Rank;
import com.meishi.model.WorkerStatus;
import com.meishi.repository.AdministratorRepository;
import com.meishi.repository.MeishiRepositoryApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { MeishiRepositoryApplication.class })
public class AdministratorRepositoryTest {
	
	@Autowired
	private AdministratorRepository adminRepo;
	
	private Administrator admin;
	
	@Before
	public void setUp(){
		admin = new Administrator();
		admin.setIdentity("8888888");
		Location address = new Location();
		address.setStreetName("StreetName");
		address.setCoordinationX(25);
		address.setCoordinationY(45);
		admin.setAddress(address);
		admin.setStatus(WorkerStatus.READY);
		admin.setRank(Rank.Rank5);
		adminRepo.save(admin);
	}
	
	@After
	public void tearDown(){
		adminRepo.delete(admin);
	}
	
	@Test
	public void testFindByStatus(){
		Assert.assertEquals(1, adminRepo.findByStatus(WorkerStatus.READY).size());
	}
	
	@Test
	public void testFindByRank(){
		Assert.assertEquals(1, adminRepo.findByRank(Rank.Rank5).size());
	}
	
	@Test
	public void testFindByIdentity(){
		Assert.assertEquals("StreetName", adminRepo.findByIdentity("8888888").getAddress().getStreetName());
	}

}
