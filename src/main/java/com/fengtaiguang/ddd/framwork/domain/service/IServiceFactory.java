package com.fengtaiguang.ddd.framwork.domain.service;

public interface IServiceFactory {
	<T> T get(Class<T> cls);
}
