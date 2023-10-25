package com.drachenclon.announcer;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

import com.drachenclon.announcer.Commands.AnnouncerCommand;
import com.drachenclon.announcer.Handlers.BarHandler;
import com.drachenclon.announcer.Handlers.ConfigHandler;
import com.drachenclon.announcer.Handlers.LanguageHandler;
import com.drachenclon.announcer.Handlers.StringConverter;
import com.drachenclon.announcer.Printers.BarPrinter;

public class AnnouncerCore extends JavaPlugin {
	
	public static void SendWrongPermission(Player player) {
		player.sendMessage(StringConverter.format(LanguageHandler.getLine("no-permission")));
	}
	
	@Override
	public void onEnable() {
		if (!getDataFolder().exists()) {
			getDataFolder().mkdir();
		}
		saveDefaultConfig();
		
		BarHandler.init(this);
		ConfigHandler.init(this);
		
		BarPrinter bp = new BarPrinter();
		
		BarHandler.SetPrinter(bp);
		BarHandler.SetValues(ConfigHandler.getAllBarValues());
		BarHandler.CreateBar();
		
		this.getCommand("announcer").setExecutor(new AnnouncerCommand(this));
		
		this.getServer().getPluginManager().registerEvents(bp, this);
	}
	
	@Override
	public void onDisable() {
		BarHandler.RemoveBar();
	}
}
