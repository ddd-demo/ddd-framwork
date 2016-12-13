package com.fengtaiguang.ddd.framwork.domain.rules;

class RuleItem<T> {
	private IRule<T> rule;
	private String messageKey;

	public RuleItem(IRule<T> rule, String messageKey) {
		this.rule = rule;
		this.messageKey = messageKey;
	}

	public IRule<T> getRule() {
		return rule;
	}

	public String getMessageKey() {
		return messageKey;
	}
}
