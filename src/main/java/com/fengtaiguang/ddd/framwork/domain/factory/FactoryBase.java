package com.fengtaiguang.ddd.framwork.domain.factory;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

public class FactoryBase implements IDomainFactory, ApplicationContextAware {

	public static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext() {

		return applicationContext;
	}
}
