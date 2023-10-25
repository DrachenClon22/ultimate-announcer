package com.drachenclon.announcer.Interfaces;

import com.drachenclon.announcer.Entities.BarConfig;

public interface IBarPrinter {
	void CreateBar(BarConfig[] barValues);
	void RemoveBar();
}
