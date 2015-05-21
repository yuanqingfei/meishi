package com.meishi.service;

import com.meishi.model.Administrator;
import com.meishi.model.Worker;

public interface AdministratorService extends WorkerService<Administrator> {

	public Administrator getByWorker(Worker worker);

	public Administrator getByWorker(String identity);

}
