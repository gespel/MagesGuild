package de.heimbrodt.sten.magesguild

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin(), CommandExecutor {
    override fun onEnable() {
        logger.info("MagesGuild Plugin Enabled")
        getCommand("mage")!!.setExecutor(this)
    }

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>): Boolean {
        val cmdName = cmd.name
        println(cmdName)

        if (sender is Player) {
            if (cmdName.equals("mage", ignoreCase = true)) {
                sender.sendMessage("Enabling Mage Mode...")
            }
            if (cmdName.equals("home", ignoreCase = true)) {
                sender.sendMessage("Teleporting home...")
                sender.teleport(Location(Bukkit.getWorld("world"), 1332.0, 63.0, 3720.0))
            }
        }

        return true
    }
}