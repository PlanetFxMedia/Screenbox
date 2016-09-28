package de.SebastianMikolai.PlanetFx.Screenbox;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.SebastianMikolai.PlanetFx.Screenbox.Datenbank.Box;
import de.SebastianMikolai.PlanetFx.Screenbox.Datenbank.MySQL;

public class Screenbox extends JavaPlugin {

	private static Screenbox instance;
	private static MySQL mysql;
	private Map<String, Box> Screenboxen = new HashMap<String, Box>();

	public static Screenbox getInstance() {
		return instance;
	}
	
	public static MySQL getMySQL() {
		return mysql;
	}
	
	@Override
	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		mysql = new MySQL(getConfig());
		mysql.connect();
		mysql.checkTable();
		Screenboxen = mysql.getScreenboxen();
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new EventListener(), instance);
		getCommand("sb").setExecutor(new CommandListener());
	}
	
	@Override
	public void onDisable() {
		EventListener.getInstance().unregisterAll();
	}
	
	public void addBox(Box box) {
		Screenboxen.put(box.getName(), box);
		mysql.addScreenbox(box);
	}
	
	public void deleteBox(String BoxName) {
		Screenboxen.remove(BoxName);
		mysql.deleteScreenbox(BoxName);
	}
	
	public Box getBox(String BoxName) {
		return Screenboxen.get(BoxName);
	}
	
	public boolean contains(String BoxName) {
		return Screenboxen.containsKey(BoxName);
	}
}