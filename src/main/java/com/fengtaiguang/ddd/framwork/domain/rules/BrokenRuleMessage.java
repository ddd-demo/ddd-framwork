package com.fengtaiguang.ddd.framwork.domain.rules;

import java.util.HashMap;

public abstract class BrokenRuleMessage {

	private HashMap<String, String> messages;

	public BrokenRuleMessage() {
		this.messages = new HashMap<String, String>();
		this.populateMessage();
	}

	protected HashMap<String, String> getMessages() {
		return this.messages;
	}

	protected abstract void populateMessage();

	public String getRuleDescription(String messageKey) {

		String desc = "";
		if (this.messages.containsKey(messageKey)) {
			desc = this.messages.get(messageKey);
		}
		return desc;
	}
}
