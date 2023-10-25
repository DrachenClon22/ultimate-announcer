package com.drachenclon.announcer.Entities;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import com.drachenclon.announcer.AnnouncerCore;
import com.drachenclon.announcer.Handlers.StringConverter;

public class Bar {
	private int taskID = -1;
	private final AnnouncerCore plugin;
	private BossBar bar;
	private BarConfig[] _values;
	private double _pauseTime = 0;
	
	public void SetValues(BarConfig[] values) {
		_values = values;
	}
	
	public void SetPauseTime(double pause) {
		_pauseTime = pause;
	}
	
	public Bar(AnnouncerCore plugin) {
		this.plugin = plugin;
	}
	
	public Bar(AnnouncerCore plugin, BarColor color) {
		this(plugin);
		SetColor(color);
	}
	
	public Bar(AnnouncerCore plugin, BarStyle style) {
		this(plugin);
		SetStyle(style);
	}
	
	public Bar(AnnouncerCore plugin, BarColor color, BarStyle style) {
		this(plugin);
		SetColor(color);
		SetStyle(style);
	}
	
	public void SetColor(BarColor color) {
		
	}
	
	public void SetStyle(BarStyle style) {
		
	}
	
	public void AddPlayer(Player player) {
		bar.addPlayer(player);
	}
	
	public BossBar getBar() {
		return bar;
	}
	
	public void createBar() {
		bar = Bukkit.createBossBar("", BarColor.PINK, BarStyle.SOLID);
		bar.setVisible(true);
		cast();
	}
	
	public void Remove() {
		getBar().removeAll();
		Bukkit.getScheduler().cancelTask(taskID);
	}
	
	private void cast() {
		setTaskID(Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			
			int count = 0;
			int direction = 1;
			
			BarConfig current = _values[count];
			
			double bar_progress = 1.0;
			double local_progress = 1.0;
			double time = 1.0 / current.getTime();
			double local_pauseTime = 0;
			
			@Override
			public void run() {
				if (local_pauseTime <= 0) {
					if (!bar.isVisible()) {
						bar.setVisible(true);
					}
					bar.setProgress(bar_progress);			
					bar.setTitle(StringConverter.format(current.calculateAndGetMessage()));
					
					bar_progress = bar_progress - (time * direction);
					bar_progress = bar_progress > 1 ? 1 : bar_progress < 0 ? 0 : bar_progress;
					
					local_progress = local_progress - time;
					
					if (local_progress <= 0) {
						count = (count + 1)%_values.length;
						local_progress = 1.0;
						direction *= -1;
						
						current = _values[count];
						time = 1.0 / current.getTime();
						local_pauseTime = _pauseTime;
					}
				} else {
					if (bar.isVisible()) {
						bar.setVisible(false);
					}
					
					local_pauseTime -= 1;
				}
			}
			
		}, 0, 20));
	}

	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
}
