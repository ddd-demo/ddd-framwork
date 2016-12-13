package com.fengtaiguang.ddd.framwork.domain.repository;

import java.io.InputStream;

public class RepositoryFactoryBuilder {
	public IRepositoryFactory build(InputStream stream){
		return new RepositoryFactory(stream);
	}
}
