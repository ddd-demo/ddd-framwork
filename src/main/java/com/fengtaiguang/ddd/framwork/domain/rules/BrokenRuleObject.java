package com.fengtaiguang.ddd.framwork.domain.rules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BrokenRuleObject {

	private List<BrokenRule> brokenRules;
	private BrokenRuleMessage brokenRuleMessage;
	
    private static EmptyBrokenRule emptyBrokenRule = new EmptyBrokenRule();


	public BrokenRuleObject() {
		this.brokenRules = new ArrayList<BrokenRule>();
		this.brokenRuleMessage = this.getBrokenRuleMessages();
	}

	protected abstract BrokenRuleMessage getBrokenRuleMessages();
    public abstract Boolean validate();

	public List<BrokenRule> getBrokenRules() {
		return Collections.unmodifiableList(this.brokenRules);
	}

	public void addBrokenRule(String messageKey) {
		String message = this.brokenRuleMessage.getRuleDescription(messageKey);
		BrokenRule rule = new BrokenRule(messageKey, message);
		this.brokenRules.add(rule);
	}

	public void addBrokenRule(String messageKey, String property) {
		String message = this.brokenRuleMessage.getRuleDescription(messageKey);
		BrokenRule rule = new BrokenRule(messageKey, message, property);
		this.brokenRules.add(rule);
	}

	public BrokenRule findBrokenRule(String property) {
		BrokenRule rule = null;
		for (BrokenRule b : this.brokenRules) {
			if (b.getProperty() == property) {
				rule = b;
				break;
			}
		}
		if(rule == null){
			return emptyBrokenRule;
		}
		return rule;
	}
	public void clearBrokenRules()
    {
        this.brokenRules.clear();
    }
}
