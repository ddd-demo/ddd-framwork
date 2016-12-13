package com.fengtaiguang.ddd.framwork.domain.rules;

import java.lang.reflect.Method;

public abstract class BaseRule<T, Value> implements IRule<T> {

	private String property;

	private String[] properties;

	public BaseRule(String property) {
		this.property = property;
		this.parseProperty();

	}

	protected String getProperty() {
		return this.property;
	}

	private void parseProperty() {
		this.properties = this.property.split("\\.");
	}

	@SuppressWarnings("unchecked")
	protected Value getObjectAttrValue(T model) {
		Object value = this.getObjectValue(model);

		if (value == null) {
			return null;
		}
		return (Value) value;
	}

	private Object getObjectValue(T model) {
		Object objectValue = model;
		for (String p : this.properties) {

			String pro = p.replaceFirst(p.substring(0, 1), p.substring(0, 1)
					.toUpperCase());

			if (objectValue == null) {
				return null;
			}
			try {
				
				Method m = getMethod(objectValue.getClass(),"get" + pro);
				m.setAccessible(true);
				objectValue = m.invoke(objectValue);
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}
		}
		return objectValue;
	}

	private Method getMethod(Class<?> c, String method) {
		Method m = null;
		try {
			m = c.getDeclaredMethod(method);
		} catch (NoSuchMethodException e) {
			
			try {
				m = c.getMethod(method);
			} catch (NoSuchMethodException ex) {
				if(c.getSuperclass() == null){
					return m;
				}
				else{
					m = getMethod(c.getSuperclass(), method);
				}
			} 
		}
		return m;
	}

	@Override
	public abstract boolean isSatisfy(T model);

}
