package com.epam.training.workday;

import java.util.Date;

class NullWorkday implements Workday {

	private NullWorkday() {
	}

	@Override
	public boolean checkDateIsWorkday(String date) {
		return true;
	}

	@Override
	public boolean checkDateIsWorkday(Date date) {
		return true;
	}

	private static class NullWorkdayHolder {
		private static final NullWorkday INSTANCE = new NullWorkday();
	}

	public static NullWorkday getInstance() {
		return NullWorkdayHolder.INSTANCE;
	}

}
