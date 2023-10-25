package com.drachenclon.announcer.Entities;

import java.time.Duration;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.drachenclon.announcer.Handlers.StringConverter;

public final class BarConfig {
	private final String _name;
	private final String _date;
	private final double _time;
	
	public BarConfig(String name, String date, double time) {
		_name = name;
		_date = date;
		_time = time;
	}
	
	public String calculateAndGetMessage() {
		return StringConverter.convertTimestamp(this);
	}
	
	public String getName() {
		return _name;
	}
	
	public String getDate() {
		return _date;
	}
	
	public double getTime() {
		return _time;
	}
	
	public boolean past() {
		return duration().isNegative();
	}
	
	public BarConfig copy() {
		return new BarConfig(_name, _date, _time);
	}
	
	public static BarConfig[] copyArray(BarConfig[] input) {
		BarConfig[] result = new BarConfig[input.length];
		for(int i = 0; i < input.length; i++) {
			result[i] = input[i].copy();
		}
		return result;
	}
	
	public Duration duration() {
		String[] temp = _date.split("\\.");
		
		Calendar currentCalendar = Calendar.getInstance();
		
		int day = 0;
		int month = currentCalendar.get(Calendar.MONTH) + 1;
		int year = currentCalendar.get(Calendar.YEAR);
		
		if (temp[0].contains("X")) {
			return Duration.ZERO;
		} else {
			day = Integer.parseInt(temp[0]);
		}
		if (!temp[1].contains("X")) {
			month = Integer.parseInt(temp[1]) - 1;
		}
		if (!temp[2].contains("X")) {
			year = Integer.parseInt(temp[2]);
		}
		
		Calendar targetCalendar = new GregorianCalendar(year, month, day);
		return Duration.between(currentCalendar.toInstant(), targetCalendar.toInstant());
	}
}
