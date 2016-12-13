package com.fengtaiguang.ddd.framwork.domain.rules;

import org.apache.commons.lang3.StringUtils;

public class IsBlankRule<T> extends BaseRule<T, String> {

	public IsBlankRule(String property) {
		super(property);
		
	}

	@Override
	public boolean isSatisfy(T model) {
		
		String v = this.getObjectAttrValue(model);
		
		if(StringUtils.isBlank(v)){
			return false;
		}
		return true;
	}

}
