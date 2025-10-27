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
        else {
            logger.info("Found Mages Guild Path.")
        }

        logger.info("MagesGuild Plugin Enabled")
        val c = Commands()
        getCommand("mage")!!.setExecutor(c)
        getCommand("home")!!.setExecutor(c)
    }

}