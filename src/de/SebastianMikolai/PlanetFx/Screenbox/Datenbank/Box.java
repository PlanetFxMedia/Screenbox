package de.SebastianMikolai.PlanetFx.Screenbox.Datenbank;

import org.bukkit.Location;

import com.google.gson.JsonObject;

public class Box {
	
	private String BoxName;
	private Location SpawnLoc;
	private Location HeadLoc;
	
	public Box(String _BoxName, Location _SpawnLoc, Location _HeadLoc) {
		BoxName = _BoxName;
		SpawnLoc = _SpawnLoc;
		HeadLoc = _HeadLoc;
	}
	
	public String getName() {
		return BoxName;
	}

	public Location getSpawnLoc() {
		return SpawnLoc;
	}
	
	public Location getHeadLoc() {
		return HeadLoc;
	}
	
	public JsonObject getSpawnLocJson() {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("world", SpawnLoc.getWorld().getName());
		jsonObject.addProperty("x", SpawnLoc.getBlockX());
		jsonObject.addProperty("y", SpawnLoc.getBlockY());
		jsonObject.addProperty("z", SpawnLoc.getBlockZ());
		return jsonObject;
	}
	
	public JsonObject getHeadLocJson() {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("world", HeadLoc.getWorld().getName());
		jsonObject.addProperty("x", HeadLoc.getBlockX());
		jsonObject.addProperty("y", HeadLoc.getBlockY());
		jsonObject.addProperty("z", HeadLoc.getBlockZ());
		return jsonObject;
	}
}