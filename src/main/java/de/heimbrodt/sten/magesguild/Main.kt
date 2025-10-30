package de.heimbrodt.sten.magesguild

import de.heimbrodt.sten.magesguild.spells.LoadedSpells
import de.heimbrodt.sten.magesguild.spells.SpellLoader
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.command.CommandExecutor
import org.bukkit.plugin.java.JavaPlugin


class Main : JavaPlugin(), CommandExecutor {
    override fun onEnable() {
        /*
            Loading Spells.
         */
        keepAreaLoaded()
        val guildPath = dataFolder.toPath()
        logger.info("Found Mages Guild Path.")
        for (s in SpellLoader.loadSpells("${guildPath}/spells.yaml")) {
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
        getCommand("village")!!.setExecutor(c)
    }
    fun keepAreaLoaded() {
        val world = Bukkit.getWorld("world")
        if (world == null) {
            Bukkit.getLogger().warning("Welt 'world' nicht gefunden!")
            return
        }

        // Mittelpunkt
        val center: Location = Location(world, 1332.0, 63.0, 3720.0)
        val centerX: Int = center.blockX shr 4
        val centerZ: Int = center.blockZ shr 4

        // Radius in Chunks (z. B. 1 = 3×3 Chunks)
        val radius = 10

        for (x in centerX - radius..centerX + radius) {
            for (z in centerZ - radius..centerZ + radius) {
                // Chunk laden und force-load aktivieren
                val chunk = world.getChunkAt(x, z)
                if (!chunk.isLoaded) chunk.load() // stellt sicher, dass der Chunk geladen ist

                chunk.isForceLoaded = true // verhindert Entladen durch Server
            }
        }

        Bukkit.getLogger().info("Chunks um $center bleiben dauerhaft geladen (auch ohne Spieler).")
    }
}