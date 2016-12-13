package com.fengtaiguang.ddd.framwork.domain.rules;

import org.apache.commons.lang3.StringUtils;

public class MaxLengthRule<T> extends BaseRule<T, String> {

	private int maxLength;

	public MaxLengthRule(String property, int maxLength) {
		super(property);

		this.maxLength = maxLength;
	}

	@Override
	public boolean isSatisfy(T model) {
		String value = this.getObjectAttrValue(model);

		if (StringUtils.isEmpty(value)) {
			return true;
		}
		if (value.length() > this.maxLength) {
			return false;
		}
		return true;
	}

}
