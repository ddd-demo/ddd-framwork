package com.fengtaiguang.ddd.framwork.domain.rules;

public class BooleanRule<T> extends BaseRule<T, Boolean> {

	private boolean value;

	public BooleanRule(String property, boolean value) {
		super(property);
		this.value = value;
	}

	@Override
	public boolean isSatisfy(T model) {
		boolean v = this.getObjectAttrValue(model);
		
		return v == this.value;
	}
}
