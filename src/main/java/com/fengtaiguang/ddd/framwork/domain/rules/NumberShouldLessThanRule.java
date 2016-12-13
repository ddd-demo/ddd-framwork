package com.fengtaiguang.ddd.framwork.domain.rules;

public class NumberShouldLessThanRule<T, Value extends Number> extends
		BaseRule<T, Value> {

	private Value value;

	public NumberShouldLessThanRule(String property, Value value) {
		super(property);
		this.value = value;
	}

	@Override
	public boolean isSatisfy(T model) {
		Value v = this.getObjectAttrValue(model);

		if (this.value == null) {
			return false;
		}
		String name = this.value.getClass().getName();

		if (Integer.class.getName() == name) {
			return v.intValue() < value.intValue();
		} 
		else if (Long.class.getName() == name) {
			return v.doubleValue() < value.doubleValue();
		} 
		else if (Short.class.getName() == name) {
			return v.shortValue() < value.shortValue();
		}
		else if (Float.class.getName() == name) {
			return v.floatValue() < value.floatValue();
		} 
		else if (Double.class.getName() == name) {
			return v.doubleValue() < value.doubleValue();
		} 
		else if (Byte.class.getName() == name) {
			return v.byteValue() < value.byteValue();
		}
		return false;
	}

}
