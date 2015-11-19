package com.epam.training.workday;

import static com.epam.training.workday.util.WorkdayConstants.*;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

import com.epam.training.workday.util.DateConverter;

class SimpleWorkday implements Workday {

	private final Properties properties;
	private final DateTimeFormatter defaultDateFormatter;
	private Set<DayOfWeek> workdays;
	private List<LocalDate> vacations;
	private List<LocalDate> extraWorkdays;

	SimpleWorkday() throws Exception {
		properties = new Properties();
		defaultDateFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
		init();
	}

	@Override
	public boolean checkDateIsWorkday(String date) {
		try {
			return checkLocalDateIsWorkday(LocalDate.parse(date, defaultDateFormatter));
		} catch (DateTimeParseException ex) {
			System.err.println(INVALID_DATE_FORMAT);
			return false;
		}
	}

	@Override
	public boolean checkDateIsWorkday(Date date) {
		LocalDate localDate = DateConverter.convertDateToLocalDate(date);
		return (localDate == null) ? false : checkLocalDateIsWorkday(localDate);
	}

	private boolean checkLocalDateIsWorkday(LocalDate date) {
		return (isWorkday(date) || isExtraWorkday(date)) && !isVacation(date);
	}

	private boolean isWorkday(LocalDate date) {
		return workdays.contains(date.getDayOfWeek());
	}

	private boolean isExtraWorkday(LocalDate date) {
		return extraWorkdays.contains(date);
	}

	private boolean isVacation(LocalDate date) {
		// @formatter:off
		return vacations.stream()
			.filter(vacation -> vacation.getDayOfYear() == date.getDayOfYear())
			.findFirst().isPresent();
		// @formatter:on
	}

	private void init() throws Exception {
		loadProperties();
		fillCollections();
		cleanCollections();
	}

	private void loadProperties() throws Exception {
		properties.load(SimpleWorkday.class.getResourceAsStream(WORKDAY_PROPERTIES));
	}

	private void fillCollections() {
		workdays = getDayOfWeekListBy();
		vacations = getDateListBy(VACATION_FORMAT, VACATION_PREFIX);
		vacations.addAll(getDateListBy(EXTRA_VACATION_FORMAT, EXTRA_VACATION_PREFIX));

		extraWorkdays = getDateListBy(EXTRA_WORKDAY_FORMAT, EXTRA_WORKDAY_PREFIX);
	}

	private void cleanCollections() {
		workdays.removeAll(Collections.singleton(null));
		vacations.removeAll(Collections.singleton(null));
		extraWorkdays.removeAll(Collections.singleton(null));
	}

	private Set<DayOfWeek> getDayOfWeekListBy() {
		// @formatter:off
		return getDateListBy(WORKDAY_FORMAT, WORKDAY_PREFIX)
					.stream()
					.map(localDate -> localDate.getDayOfWeek())
					.collect(Collectors.toSet());
		// @formatter:on
	}

	private List<LocalDate> getDateListBy(String dateFormat, String value) {
		SimpleDateFormat format = new SimpleDateFormat(properties.getProperty(dateFormat));
		// @formatter:off
		return properties.entrySet()
				.stream()
				.filter(entry -> ((String) entry.getKey()).startsWith(value))
				.map(entry -> DateConverter.getDate(format, entry.getValue()))
				.collect(Collectors.toList());
		// @formatter:on
	}

}
