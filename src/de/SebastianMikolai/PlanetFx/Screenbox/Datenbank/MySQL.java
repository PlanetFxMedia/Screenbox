package de.SebastianMikolai.PlanetFx.Screenbox.Datenbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MySQL {
	
	public static FileConfiguration cfg;
	public static Connection con;
	
	public MySQL(FileConfiguration _cfg) {
		cfg = _cfg;
	}

	public Connection connect() {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://" + cfg.getString("database.host") + ":" + 
					cfg.getInt("database.port") + "/" + cfg.getString("database.db") + "?autoReconnect=true", cfg.getString("database.user"), cfg.getString("database.password"));
			return con;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Connection getConnection() {
		try {
			if (con == null) {
				con = connect();
			} else if (con.isClosed()) {
				con = connect();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public void checkTable() {
		try {
			Connection c = getConnection();
			Statement stmt = c.createStatement();
			ResultSet rss = stmt.executeQuery("SHOW TABLES LIKE 'Screenbox'");
			if (rss.next()) {
				Bukkit.getLogger().info("Die Tabelle Screenbox wurde geladen!");
			} else {
				int rs = stmt.executeUpdate("CREATE TABLE Screenbox (id INTEGER PRIMARY KEY AUTO_INCREMENT, BoxName TEXT, SpawnLocationJson TEXT, HeadLocationJson TEXT)");
				Bukkit.getLogger().info("Die Tabelle Screenbox wurde erstellt! (" + rs + ")");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addScreenbox(Box box) {
		try {
			Connection c = getConnection();
			Statement stmt = c.createStatement();
			stmt.execute("INSERT INTO Screenbox (BoxName, SpawnLocationJson, HeadLocationJson) VALUES ('" + box.getName() + "', '" + box.getSpawnLocJson().toString() +  "', '" + box.getHeadLocJson().toString() + "')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteScreenbox(String BoxName) {
		try {
			Connection c = getConnection();
			Statement stmt = c.createStatement();
			stmt.execute("DELETE FROM Screenbox WHERE BoxName='" + BoxName + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, Box> getScreenboxen() {
		Map<String, Box> Screenboxen = new HashMap<String, Box>();
		try {
			Connection c = getConnection();
			Statement stmt = c.createStatement();
			ResultSet rss = stmt.executeQuery("SELECT * FROM Screenbox");
			while (rss.next()) {
				JsonParser SpawnJsonParser = new JsonParser();
				JsonElement SpawnJsonElement = SpawnJsonParser.parse(rss.getString("SpawnLocationJson"));
				JsonObject SpawnJsonObject = SpawnJsonElement.getAsJsonObject();
				Location SpawnLoc = new Location(Bukkit.getWorld(SpawnJsonObject.get("world").getAsString()), SpawnJsonObject.get("x").getAsInt(), SpawnJsonObject.get("y").getAsInt(), SpawnJsonObject.get("z").getAsInt());
				JsonParser HeadJsonParser = new JsonParser();
				JsonElement HeadJsonElement = HeadJsonParser.parse(rss.getString("HeadLocationJson"));
				JsonObject HeadJsonObject = HeadJsonElement.getAsJsonObject();
				Location HeadLoc = new Location(Bukkit.getWorld(HeadJsonObject.get("world").getAsString()), HeadJsonObject.get("x").getAsInt(), HeadJsonObject.get("y").getAsInt(), HeadJsonObject.get("z").getAsInt());
				Screenboxen.put(rss.getString("BoxName"), new Box(rss.getString("BoxName"), SpawnLoc, HeadLoc));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Screenboxen;
	}
}