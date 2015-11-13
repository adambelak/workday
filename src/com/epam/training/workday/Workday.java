package com.epam.training.workday;

import java.util.Date;

public interface Workday {

	boolean checkDateIsWorkday(String date);

	boolean checkDateIsWorkday(Date date);

}