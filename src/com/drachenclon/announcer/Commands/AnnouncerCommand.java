package com.drachenclon.announcer.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.drachenclon.announcer.AnnouncerCore;
import com.drachenclon.announcer.Handlers.BarHandler;
import com.drachenclon.announcer.Handlers.LanguageHandler;
import com.drachenclon.announcer.Handlers.StringConverter;

public class AnnouncerCommand implements CommandExecutor {
	AnnouncerCore _core;
	
	public AnnouncerCommand(AnnouncerCore core) { 
		_core = core;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		try {
			if (args[0].equals("reload")) {
				if (sender.hasPermission("announcer.reload")) {
					BarHandler.ReloadBar();
					sender.sendMessage(StringConverter.format(LanguageHandler.getLineWithPrefix("message-reloaded")));
				} else {
					if (sender instanceof Player) {
						AnnouncerCore.SendWrongPermission((Player)sender);
					} else {
						System.out.println(StringConverter.format("&cSomething went wrong. ANC-0000"));
					}
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
