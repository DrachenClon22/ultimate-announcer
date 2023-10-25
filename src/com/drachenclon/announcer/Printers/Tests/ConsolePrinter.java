package com.drachenclon.announcer.Printers.Tests;

import com.drachenclon.announcer.Entities.BarConfig;
import com.drachenclon.announcer.Handlers.ConfigHandler;
import com.drachenclon.announcer.Handlers.StringConverter;
import com.drachenclon.announcer.Interfaces.IBarPrinter;

public class ConsolePrinter implements IBarPrinter {

	@Override
	public void CreateBar(BarConfig[] barValues) {
		for (BarConfig s: ConfigHandler.getAllBarValues()) {
			if (s.past()) {
				continue;
			}
			String message = StringConverter.convertTimestamp(s);
			System.out.println(message);
		}
		
	}

	@Override
	public void RemoveBar() {
		// TODO Auto-generated method stub
		
	}

}
