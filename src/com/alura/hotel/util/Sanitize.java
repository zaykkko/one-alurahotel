package com.alura.hotel.util;

public final class Sanitize {
	public static String phoneNumber(String phoneNumber) {
		return phoneNumber.replaceAll("\\D", ""); // \D shorthand that removes all non-numeric characters
	}
}
