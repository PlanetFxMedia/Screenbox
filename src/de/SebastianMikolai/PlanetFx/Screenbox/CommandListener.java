package de.SebastianMikolai.PlanetFx.Screenbox;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.SebastianMikolai.PlanetFx.Screenbox.Datenbank.Box;
import de.SebastianMikolai.PlanetFx.Screenbox.Utils.ChatUtils;

public class CommandListener implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (cs instanceof Player) {
			Player p = (Player)cs;
			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("add") && p.hasPermission("pfx.sb.add")) {
					String BoxName = args[1];
					Location SpawnLoc = p.getLocation();
					Box box = new Box(BoxName, SpawnLoc, null);
					Screenbox.getMySQL().addScreenbox(box);
				} else if (args[0].equalsIgnoreCase("delete") && p.hasPermission("pfx.sb.delete")) {
					String BoxName = args[1];
					Box box = Screenbox.getInstance().getBox(BoxName);
					if (box != null) {
						Screenbox.getInstance().deleteBox(BoxName);
					} else {
						ChatUtils.sendMessageConfig(cs, "NotFound", "<BoxName>", BoxName);
					}
				} else {
					ChatUtils.sendMessageConfig(p, "NoPermission");
				}
			} else {
				ChatUtils.sendMessageConfig(cs, "Syntax", "<cmd>", "/sb add <BoxName>");
			}
		} else {
			ChatUtils.sendMessageConfig(cs, "OnlyPlayer");
		}
		return true;
	}
}