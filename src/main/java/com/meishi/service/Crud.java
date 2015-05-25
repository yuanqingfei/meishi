package com.meishi.service;

import java.util.List;

public interface Crud<T> {
	
	public T upsert(T entity);

	public void delete(String identity);

	public T get(String identity);

	public Boolean isExisted(String identity);
	
	public Long count();
	
	public List<T> getAll();
	
	public void deleteAll();

}
