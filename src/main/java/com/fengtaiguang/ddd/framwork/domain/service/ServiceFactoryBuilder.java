package com.fengtaiguang.ddd.framwork.domain.service;

import java.io.InputStream;

public class ServiceFactoryBuilder {
	public IServiceFactory build(InputStream stream){
		return new ServiceFactory(stream);
	}
}
