package com.fengtaiguang.ddd.framwork.domain.rules;

public class BrokenRuleException extends Exception {
	private static final long serialVersionUID = 708303351975681548L;

	private String code;

	public BrokenRuleException(String code, String message) {
		super(message);
		
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
