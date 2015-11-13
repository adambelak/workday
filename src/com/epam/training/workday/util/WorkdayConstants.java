package com.epam.training.workday.util;

public final class WorkdayConstants {

	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	public static final String WORKDAY_PROPERTIES = "/workday.properties";

	public static final String WORKDAY_FORMAT = "workday-format";
	public static final String WORKDAY_PREFIX = "workday.";
	public static final String VACATION_FORMAT = "vacation-format";
	public static final String VACATION_PREFIX = "vacation.";
	public static final String EXTRA_WORKDAY_FORMAT = "extra-workday-format";
	public static final String EXTRA_WORKDAY_PREFIX = "extra-workday.";
	public static final String EXTRA_VACATION_FORMAT = "extra-vacation-format";
	public static final String EXTRA_VACATION_PREFIX = "extra-vacation.";

	public static final String ERROR_MESSAGE = "The workday.properties not found. The result of the checkDateIsWorkday() always will be true.";
	public static final String SHORT_ERROR_MESSAGE = "Invalid.";
	public static final String INVALID_DATE_FORMAT = "Invalid dateformat. Please use the \"yyyy-MM-dd\" pattern. for example: 2015-01-02";

}
