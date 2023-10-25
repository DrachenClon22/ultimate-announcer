package com.drachenclon.announcer.Handlers;

import com.drachenclon.announcer.AnnouncerCore;
import com.drachenclon.announcer.Entities.BarConfig;
import com.drachenclon.announcer.Interfaces.IBarPrinter;
import com.drachenclon.announcer.Printers.Tests.ConsolePrinter;

public final class BarHandler {
	private static BarConfig[] _values;
	private static AnnouncerCore _core;
	
	private static IBarPrinter _barCreator;
	
	public static void init(AnnouncerCore core) {
		_core = core;
		
		// This is for debug purposes
		SetPrinter(new ConsolePrinter());
	}
	
	public static AnnouncerCore getCore() {
		return _core;
	}
	
	public static void SetPrinter(IBarPrinter creator) {
		_barCreator = creator;
	}
	
	public static void SetValues(BarConfig[] values) {
		_values = values;
	}
	
	public static void ReloadBar() {
		RemoveBar();
		_core.reloadConfig();
		CreateBar();
	}
	
	public static void RemoveBar() {
		_barCreator.RemoveBar();
	}
	
	public static void CreateBar() {
		if (_values.length > 0) {
			_barCreator.CreateBar(_values);
		}
	}
}
