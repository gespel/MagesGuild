package de.heimbrodt.sten.magesguild

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import kotlin.io.path.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.exists

class Main : JavaPlugin(), CommandExecutor {
    override fun onEnable() {
        if (!Path("magesguild").exists()) {
            logger.info("No Mages Guild Path exists. Creating a new instance...")
            Path("plugins/magesguild").createDirectories()
        }

        logger.info("MagesGuild Plugin Enabled")
        getCommand("mage")!!.setExecutor(this)
    }

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>): Boolean {
        val cmdName = cmd.name
        println(cmdName)

        if (cmdName.equals("mage", true)) {
            if (sender is Player) {
                sender.sendMessage("Enabling Mage Mode...")
            }
            else {
                sender.sendMessage("Enabled Spells are:")
            }
        }
        if (cmdName.equals("home", true)) {
            if (sender is Player) {
                sender.sendMessage("Teleporting home...")
                sender.teleport(Location(Bukkit.getWorld("world"), 1332.0, 63.0, 3720.0))
            }
            else {
                sender.sendMessage("You are no player you dum dum...")
            }
        }
        return true
    }
}