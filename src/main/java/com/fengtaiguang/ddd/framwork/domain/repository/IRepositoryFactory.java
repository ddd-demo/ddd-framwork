package com.fengtaiguang.ddd.framwork.domain.repository;

public interface IRepositoryFactory {
	<T> T get(Class<T> cls);
}
