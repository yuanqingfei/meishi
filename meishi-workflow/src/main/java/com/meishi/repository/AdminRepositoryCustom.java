package com.meishi.repository;

import com.meishi.model.Administrator;

public interface AdminRepositoryCustom {
	
	public Administrator findByWorker(String workerIdentity);

}
