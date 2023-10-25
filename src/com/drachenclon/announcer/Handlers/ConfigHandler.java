package com.drachenclon.announcer.Handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.drachenclon.announcer.AnnouncerCore;
import com.drachenclon.announcer.Entities.BarConfig;

public final class ConfigHandler {
	private static AnnouncerCore _core;
	
	public static void init(AnnouncerCore core) {
		_core = core;
	}
	
	public static String getConfigValue(String param) {
		return _core.getConfig().getString(param);
	}
	
	public static double getConfigValueAsDouble(String param) {
		return Double.parseDouble(getConfigValue(param));
	}
	
	public static boolean getConfigValueAsBoolean(String param) {
		return Boolean.parseBoolean(getConfigValue(param));
	}
	
	@Deprecated
	public static String getMessage(String param) {
		return _core.getConfig().getString("lang." + getConfigValue("language") + "." + param);
	}
	
	public static BarConfig[] getAllBarValues() {
		Set<String> values = _core.getConfig().getConfigurationSection("events").getKeys(false);
		
		List<BarConfig> result = new ArrayList<BarConfig>();
		
		for (String s : values) {
			try {
				s = "events." + s; 
				String name = getConfigValue(s + ".title");
				String date = getConfigValue(s + ".date");
				double time = getConfigValueAsDouble(s + ".time");
				result.add(new BarConfig(name, date, time));
			} catch (Exception e) {
				continue;
			}
		}
		
		return result.toArray(new BarConfig[result.size()]);
	}
}
