package com.meishi.repository;

import com.meishi.model.Administrator;
import com.meishi.model.Worker;

public interface AdminRepositoryCustom {
	
	public Administrator findByWorker(Worker worker);
	
	public Administrator findByWorker(String workerIdentity);

}
