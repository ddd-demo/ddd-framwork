package com.fengtaiguang.ddd.framwork.domain.repository;

import java.io.Serializable;
import java.util.List;

public interface IRepository<T> {
	public void save(T entity);

	public void delete(T entity);

	public void update(T entity);

	public T get(Class type, Serializable id);

	public List<T> findByExample(T exampleEntity);
}
