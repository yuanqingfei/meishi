package com.meishi.service;

import com.meishi.model.Administrator;

public interface AdministratorService extends WorkerService<Administrator> {

	public Administrator getByWorker(String identity);

}
