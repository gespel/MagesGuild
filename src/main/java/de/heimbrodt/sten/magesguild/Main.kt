package de.heimbrodt.sten.magesguild;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements CommandExecutor {
    @Override
    public void onEnable() {
        getLogger().info("MagesGuild Plugin Enabled");
        getCommand("mage").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        String cmdName = cmd.getName();
        System.out.println(cmdName);

        if (sender instanceof Player player) {
            if (cmdName.equalsIgnoreCase("mage")) {
                player.sendMessage("Enabling Mage Mode...");
            }
            if (cmdName.equalsIgnoreCase("home")) {
                player.sendMessage("Teleporting home...");
                player.teleport(new Location(Bukkit.getWorld("world"), 1332.0, 63.0, 3720.0));
            }

        }

        return true;
    }

}