package com.drachenclon.announcer.Handlers;

import org.bukkit.ChatColor;

import com.drachenclon.announcer.Entities.BarConfig;

public class StringConverter {
	public static String convertTimestamp(BarConfig cfg) {
		long minutes = cfg.duration().toMinutes();
		long hours = cfg.duration().toHours();
		long days = cfg.duration().toDays();
		long month = days/31;
		long years = days/365;
		
		String message = cfg.getName()
				.replace("{minutes}", String.valueOf(minutes))
				.replace("{hours}", String.valueOf(hours))
				.replace("{days}", String.valueOf(days))
				.replace("{month}", String.valueOf(month))
				.replace("{years}", String.valueOf(years))
				
				.replace("{month:days}", String.valueOf(days%31))
				.replace("{days:hours}", String.valueOf(hours%24))
				.replace("{hours:minutes}", String.valueOf(minutes%60));
		message = message
				.replace("{minute:s}", minutes != 1 ? LanguageHandler.getLine("minutes") : LanguageHandler.getLine("minute"))
				.replace("{hour:s}", hours != 1 ? LanguageHandler.getLine("hours") : LanguageHandler.getLine("hour"))
				.replace("{day:s}", days != 1 ? LanguageHandler.getLine("days") : LanguageHandler.getLine("day"))
				.replace("{month:s}", month != 1 ? LanguageHandler.getLine("months") : LanguageHandler.getLine("month"))
				.replace("{year:s}", years != 1 ? LanguageHandler.getLine("years") : LanguageHandler.getLine("year"))
				
				.replace("{month:day:s}", days%31 != 1 ? LanguageHandler.getLine("days") : LanguageHandler.getLine("day"))
				.replace("{days:hour:s}", hours%24 != 1 ? LanguageHandler.getLine("hours") : LanguageHandler.getLine("hour"))
				.replace("{hours:minute:s}", minutes%60 != 1 ? LanguageHandler.getLine("minutes") : LanguageHandler.getLine("minute"));
		
		return message;
	}
	
	public static String format(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
}
