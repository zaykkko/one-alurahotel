package com.alura.hotel.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public final class Validate {
	public static boolean isInteger(String string) {
		try {
			Integer.parseInt(string);

			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean phoneNumber(String phoneNumber) {
		return Pattern.matches("^(\\d{1,4})(\\d{1,2})(\\d{4}\\d{4})$", phoneNumber.replaceAll("[-\\s\\+]", ""));
	}

	public static boolean dateFormat(String format, String value) {
		LocalDateTime ldt = null;
		DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(format);

		try {
			ldt = LocalDateTime.parse(value, fomatter);
			String result = ldt.format(fomatter);
			return result.equals(value);
		} catch (DateTimeParseException e) {
			try {
				LocalDate ld = LocalDate.parse(value, fomatter);
				String result = ld.format(fomatter);
				return result.equals(value);
			} catch (DateTimeParseException exp) {
				try {
					LocalTime lt = LocalTime.parse(value, fomatter);
					String result = lt.format(fomatter);
					return result.equals(value);
				} catch (DateTimeParseException e2) {
					e2.printStackTrace();
				}
			}
		}

		return false;
	}
}
