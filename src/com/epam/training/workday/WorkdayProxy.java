package com.epam.training.workday;

import java.util.Date;

import com.epam.training.workday.util.WorkdayConstants;

public class WorkdayProxy implements Workday {

	private final Workday instance;

	private WorkdayProxy() {
		Workday instance = NullWorkday.getInstance();
		try {
			instance = new SimpleWorkday();
		} catch (Exception ex) {
			System.err.println(WorkdayConstants.ERROR_MESSAGE);
		}
		this.instance = instance;
	}

	@Override
	public boolean checkDateIsWorkday(String date) {
		return instance.checkDateIsWorkday(date);
	}

	@Override
	public boolean checkDateIsWorkday(Date date) {
		return instance.checkDateIsWorkday(date);
	}

	private static class WorkdayProxyHolder {
		private static final WorkdayProxy INSTANCE = new WorkdayProxy();
	}

	public static WorkdayProxy getInstance() {
		return WorkdayProxyHolder.INSTANCE;
	}

}
