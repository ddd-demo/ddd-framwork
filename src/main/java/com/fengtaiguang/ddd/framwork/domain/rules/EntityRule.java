package com.fengtaiguang.ddd.framwork.domain.rules;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class EntityRule<T extends BrokenRuleObject> implements IRule<T> {

	private HashMap<String, List<RuleItem<T>>> rules;
	private List<RuleItem<T>> classRules;

	public EntityRule() {
		this.rules = new HashMap<String, List<RuleItem<T>>>();
		this.classRules = new ArrayList<RuleItem<T>>();
		

	}

	/**
	 * 是否为null和空字符或空白字符串
	 * 
	 * @param property
	 *            要验证的属性性
	 * @param messageKey
	 *            错误消息ID
	 */
	public void isBlank(String property, String messageKey) {
		IsBlankRule<T> rule = new IsBlankRule<T>(property);
		this.addRule(property, rule, messageKey);
	}

	/**
	 * 是否为电子邮件字符串
	 * 
	 * @param property
	 * @param messageKey
	 */
	public void email(String property, String messageKey) {
		EmailRule<T> rule = new EmailRule<T>(property);
		this.addRule(property, rule, messageKey);
	}

	/**
	 * 数字应该大于
	 * 
	 * @param property
	 * @param v
	 * @param messageKey
	 */
	public <Value extends Number> void numberShouldGreaterThan(String property,
			Value v, String messageKey) {
		NumberShouldGreaterThanRule<T, Value> rule = new NumberShouldGreaterThanRule<T, Value>(
				property, v);
		this.addRule(property, rule, messageKey);
	}

	/**
	 * 数字应该小于
	 * 
	 * @param property
	 * @param v
	 * @param messageKey
	 */
	public <Value extends Number> void numberShouldLessThan(String property,
			Value v, String messageKey) {
		NumberShouldLessThanRule<T, Value> rule = new NumberShouldLessThanRule<T, Value>(
				property, v);

		this.addRule(property, rule, messageKey);
	}

	/**
	 * 日期就是大于
	 * 
	 * @param property
	 * @param date
	 * @param messageKey
	 */
	public void dateShouldGreaterThan(String property, Date date,
			String messageKey) {
		DateShouldGreaterThanRule<T> rule = new DateShouldGreaterThanRule<T>(
				property, date);

		this.addRule(property, rule, messageKey);
	}

	/**
	 * 日期应小于
	 * 
	 * @param property
	 * @param date
	 * @param messageKey
	 */
	public void dateShouldLessThan(String property, Date date, String messageKey) {
		DateShouldLessThanRule<T> rule = new DateShouldLessThanRule<T>(
				property, date);

		this.addRule(property, rule, messageKey);

	}

	/**
	 * boolean值应该等于
	 * 
	 * @param property
	 * @param bool
	 * @param messageKey
	 */
	public void BooleanShouldEqual(String property, boolean bool,
			String messageKey) {
		BooleanRule<T> rule = new BooleanRule<>(property, bool);
		this.addRule(property, rule, messageKey);
	}

	/**
	 * 数字应该 等于
	 * 
	 * @param property
	 * @param bool
	 * @param messageKey
	 */
	public <Value extends Number> void numberShouldEqual(String property,
			Value value, String messageKey) {
		NumberEqualRule<T, Value> rule = new NumberEqualRule<T, Value>(
				property, value);
		this.addRule(property, rule, messageKey);
	}

	/**
	 * 添加自定义规则
	 * 
	 * @param property
	 * @param rule
	 * @param messageKey
	 */
	public void addRule(String property, IRule<T> rule, String messageKey) {
		if (this.rules.containsKey(property)) {
			this.rules.get(property).add(new RuleItem<T>(rule, messageKey));
		} else {
			List<RuleItem<T>> ruleItems = new ArrayList<RuleItem<T>>();
			ruleItems.add(new RuleItem<T>(rule, messageKey));

			this.rules.put(property, ruleItems);
		}
	}

	/**
	 * 添加自定义规则
	 * 
	 * @param rule
	 * @param messageKey
	 */
	public void addRule(IRule<T> rule, String messageKey) {
		this.classRules.add(new RuleItem<T>(rule, messageKey));
	}

	@Override
	public boolean isSatisfy(T model) {
		boolean propertyIsValid = true;
		for (Entry<String, List<RuleItem<T>>> entry : this.rules.entrySet()) {
			for (RuleItem<T> rule : entry.getValue()) {
				if (!rule.getRule().isSatisfy(model)) {
					propertyIsValid = false;
					model.addBrokenRule(rule.getMessageKey(), entry.getKey());
					break;
				}
			}
		}

		boolean classIsValid = true;
		for (RuleItem<T> rule : this.classRules) {

			if (!rule.getRule().isSatisfy(model)) {
				classIsValid = false;
				model.addBrokenRule(rule.getMessageKey());
				break;
			}
		}
		return propertyIsValid && classIsValid;
	}
}
