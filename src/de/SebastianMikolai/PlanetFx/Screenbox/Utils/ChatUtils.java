package de.SebastianMikolai.PlanetFx.Screenbox.Utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class ChatUtils {
	
	private static YamlConfiguration cfg;
	
	public ChatUtils(YamlConfiguration _cfg) {
		cfg = _cfg;
	}
	
	public static void sendMessageConfig(Player p, String msg) {
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', cfg.getString("Messages.Prefix")) + ChatColor.translateAlternateColorCodes('&', cfg.getString("Messages." + msg)));
	}
	
	public static void sendMessageConfig(CommandSender cs, String msg) {
		cs.sendMessage(ChatColor.translateAlternateColorCodes('&', cfg.getString("Messages.Prefix")) + ChatColor.translateAlternateColorCodes('&', cfg.getString("Messages." + msg)));
	}
	
	public static void sendMessageConfig(Player p, String msg, String replace, String replace_mitwas) {
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', cfg.getString("Messages.Prefix")) + ChatColor.translateAlternateColorCodes('&', cfg.getString("Messages." + msg).replace(replace, replace_mitwas)));
	}
	
	public static void sendMessageConfig(CommandSender cs, String msg, String replace, String replace_mitwas) {
		cs.sendMessage(ChatColor.translateAlternateColorCodes('&', cfg.getString("Messages.Prefix")) + ChatColor.translateAlternateColorCodes('&', cfg.getString("Messages." + msg).replace(replace, replace_mitwas)));
	}
}