package com.drachenclon.announcer.Printers;

import java.util.Collection;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.drachenclon.announcer.AnnouncerCore;
import com.drachenclon.announcer.Entities.Bar;
import com.drachenclon.announcer.Entities.BarConfig;
import com.drachenclon.announcer.Handlers.BarHandler;
import com.drachenclon.announcer.Handlers.ConfigHandler;
import com.drachenclon.announcer.Interfaces.IBarPrinter;

public class BarPrinter implements IBarPrinter, Listener {

	private Bar _bar;
	
	@EventHandler
	public void OnPlayerJoin(PlayerJoinEvent event) {
		_bar.AddPlayer(event.getPlayer());
	}
	
	@Override
	public void CreateBar(BarConfig[] barValues) {
		AnnouncerCore core = BarHandler.getCore();
		if (_bar == null) {
			_bar = new Bar(core);
		}
		_bar.SetValues(barValues);
		try {
			_bar.SetPauseTime(ConfigHandler.getConfigValueAsDouble("pause-between"));
		} catch (Exception e) {
			System.out.println("Error in config.yml");
		}
		_bar.createBar();
		
		if (barValues.length > 0) {
			Collection<? extends Player> players = core.getServer().getOnlinePlayers();
			
			for (Player pl : players) {
				_bar.AddPlayer(pl);
			}
		}
	}

	@Override
	public void RemoveBar() {
		_bar.Remove();
	}
	
}
