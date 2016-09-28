package de.SebastianMikolai.PlanetFx.Screenbox;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

public class EventListener implements Listener {
	
	private static EventListener instance;
	
	public EventListener() {
		instance = this;
	}
	
	public static EventListener getInstance() {
		return instance;
	}
	
	public void unregisterAll() {
		HandlerList.unregisterAll();
	}
}