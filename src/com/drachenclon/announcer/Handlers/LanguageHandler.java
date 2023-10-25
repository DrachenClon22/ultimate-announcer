package com.drachenclon.announcer.Handlers;

public class LanguageHandler {
	@SuppressWarnings("deprecation")
	public static String getLine(String param) {
		return ConfigHandler.getMessage(param);
	}
	@SuppressWarnings("deprecation")
	public static String getLineWithPrefix(String param) {
		return "&2[Ultimate Announcer] &r" + ConfigHandler.getMessage(param);
	}
}
