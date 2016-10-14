package de.SebastianMikolai.PlanetFx.Screenbox;

import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class EventListener implements Listener {
	
	private static EventListener instance;
	
	public EventListener() {
		instance = this;
	}
	
	public static EventListener getInstance() {
		return instance;
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if (!e.getPlayer().hasPermission("pfx.sb.add")) {
			e.setCancelled(true);
		} else {
			
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if (!e.getPlayer().hasPermission("pfx.sb.delete")) {
			e.setCancelled(true);
		} else {
			
		}
	}
	
	public void unregisterAll() {
		HandlerList.unregisterAll();
	}
}