package com.meishi.repository;

import org.springframework.data.repository.query.Param;

import com.meishi.model.Administrator;

public interface AdminRepositoryCustom {
	
	public Administrator findByWorker(@Param("workerId") String workerIdentity);

}
