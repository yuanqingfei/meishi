package com.meishi.test.service;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.meishi.MeishiApplication;
import com.meishi.model.Administrator;
import com.meishi.model.Rank;
import com.meishi.model.WorkerStatus;
import com.meishi.service.AdministratorService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { MeishiApplication.class })
public class AdministratorServiceTest {

	@Autowired
	private AdministratorService adminService;

	private Administrator admin;

	@Before
	public void setUp() {
		admin = new Administrator();
		admin.setIdentity("8888888");
		admin.setStatus(WorkerStatus.READY);
		admin.setRank(Rank.Rank5);
		adminService.upsert(admin);
	}

	@After
	public void tearDown() {
		adminService.deleteAll();
	}

}
