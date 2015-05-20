package com.meishi.service;

import java.util.List;

public interface CrudInterface<T> {
	
	public abstract T saveAndUpdate(T entity);

	public abstract void delete(String identity);

	public abstract T find(String identity);

	public abstract Boolean isExisted(String identity);
	
	public abstract Long count();
	
	public abstract List<T> getAll();

}
