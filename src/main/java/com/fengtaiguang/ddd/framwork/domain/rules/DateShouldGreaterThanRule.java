package com.fengtaiguang.ddd.framwork.domain.rules;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public class DateShouldGreaterThanRule<T> extends BaseRule<T, Date> {

	private Date date;

	public DateShouldGreaterThanRule(String property, Date date) {
		super(property);

		this.date = DateUtils.truncate(date, Calendar.SECOND);
	}

	@Override
	public boolean isSatisfy(T model) {
		Date d = this.getObjectAttrValue(model);
		d = DateUtils.truncate(d, Calendar.SECOND);

		return d.compareTo(this.date) > 0;
	}

}
