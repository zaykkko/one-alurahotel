package com.alura.hotel.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public final class Config {
	private final static String CONFIG_FILE = "config.properties";

	private static Properties config;

	public static void init() {
		Properties config = new Properties();
		config.setProperty("username", "root");
		config.setProperty("password", "root");
		config.setProperty("dbName", "alura_hotel");
		config.setProperty("dbPort", "5432");
		config.setProperty("dbUsername", "postgres");
		config.setProperty("dbPassword", "");

		try {
			config.load(new FileReader(CONFIG_FILE));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Config.config = config;
	}

	public static Properties getConfig() {
		if (config == null) {
			init();
		}

		return config;
	}

	public static String getValue(String key) {
		return getConfig().getProperty(key);
	}
}
