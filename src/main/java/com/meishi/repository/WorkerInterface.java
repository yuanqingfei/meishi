package com.meishi.repository;

import java.util.List;

import com.meishi.model.Rank;
import com.meishi.model.WorkerStatus;

public interface WorkerInterface<T> {

	public List<T> findByStatus(WorkerStatus status);

	public List<T> findByRank(Rank rank);

	public T findByIdentity(String identity);
}
