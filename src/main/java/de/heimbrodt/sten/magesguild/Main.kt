package de.heimbrodt.sten.magesguild

import de.heimbrodt.sten.magesguild.spells.SpellLoader
import org.bukkit.command.CommandExecutor
import org.bukkit.plugin.java.JavaPlugin
import de.heimbrodt.sten.magesguild.spells.LoadedSpells


class Main : JavaPlugin(), CommandExecutor {
    override fun onEnable() {
        /*
            Loading Spells.
         */
        val guildPath = dataFolder.toPath()
        logger.info("Found Mages Guild Path.")
        val sl = SpellLoader()
        for (s in sl.loadSpells("${guildPath}/spells.yaml")) {
            LoadedSpells.loadedSpells[s.name] = s
        }
        logger.info("Loaded ${LoadedSpells.loadedSpells.size} spells.")
        logger.info("MagesGuild Plugin Enabled")

        /*
            Registering Commands
         */
        val c = Commands()
        getCommand("mage")!!.setExecutor(c)
        getCommand("home")!!.setExecutor(c)
    }

}