package com.meishi.service;

import java.util.List;

public interface Crud<T> {
	
	public abstract T upsert(T entity);

	public abstract void delete(String identity);

	public abstract T get(String identity);

	public abstract Boolean isExisted(String identity);
	
	public abstract Long count();
	
	public abstract List<T> getAll();
	
	public void deleteAll();

}
