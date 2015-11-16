package com.epam.training.workday.util;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateConverter {

	public static DayOfWeek getDayOfWeek(String value) {
		return DayOfWeek.of(Integer.valueOf(value));
	}

	public static LocalDate getDate(SimpleDateFormat format, Object value) {
		try {
			return convertDateToLocalDate(format.parse((String) value));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public static LocalDate convertDateToLocalDate(Date date) {
		return (date == null) ? null : date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	private DateConverter() {
	}

}
