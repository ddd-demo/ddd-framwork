package com.fengtaiguang.ddd.framwork.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InterfaceProxy implements InvocationHandler {

	private Object target;

	public Object bind(Object target) {
		this.target = target;

		return Proxy.newProxyInstance(this.target.getClass().getClassLoader(),
				this.target.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		return method.invoke(target, args);
	}

}
