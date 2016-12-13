package com.fengtaiguang.ddd.framwork.domain.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class EmailRule<T> extends BaseRule<T, String> {

	public static final String EMAIL_REG ="^\\s*([A-Za-z0-9_-]+(\\.\\w+)*@(\\w+\\.)+\\w{2,5})\\s*$";
	
	public EmailRule(String property) {
		super(property);
	}

	@Override
	public boolean isSatisfy(T model) {
		String value = this.getObjectAttrValue(model);
		
		if(StringUtils.isEmpty(value)){
			return true;
		}
		
		Pattern p = Pattern.compile(EMAIL_REG);
		
		Matcher m = p.matcher(value);
		return m.matches();
	}

}
