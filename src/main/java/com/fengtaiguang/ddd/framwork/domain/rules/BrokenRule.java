package com.fengtaiguang.ddd.framwork.domain.rules;

public class BrokenRule {

	private String name;
	private String description;
	private String property;

	public BrokenRule(String name, String description) {
		this(name, description, "");
	}

	public BrokenRule(String name, String description, String property) {
		this.name = name;
		this.description = description;
		this.property = property;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getProperty() {
		return property;
	}
}
