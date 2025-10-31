package de.heimbrodt.sten.magesguild

import de.heimbrodt.sten.magesguild.spells.LoadedSpells
import de.heimbrodt.sten.magesguild.spells.LoadedSpells.Companion.loadedSpells
import de.heimbrodt.sten.magesguild.spells.SpellEffector
import de.heimbrodt.sten.magesguild.spells.SpellLoader
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.command.CommandExecutor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.plugin.java.JavaPlugin


class Main : JavaPlugin(), CommandExecutor, Listener {
    override fun onEnable() {
        /*
            Loading Spells.
         */
        keepAreaLoaded()
        val guildPath = dataFolder.toPath()
        logger.info("Found Mages Guild Path.")
        for (s in SpellLoader.loadSpells("${guildPath}/spells.yaml")) {
            loadedSpells[s.name.lowercase()] = s
        }
        logger.info("Loaded ${loadedSpells.size} spells.")
        logger.info("MagesGuild Plugin Enabled")

        /*
            Registering Commands
         */
        val c = Commands()
        getCommand("mage")!!.setExecutor(c)
        getCommand("home")!!.setExecutor(c)
        getCommand("village")!!.setExecutor(c)

        /*
            Events
         */
        server.pluginManager.registerEvents(this, this)
    }

    @EventHandler
    fun onRightClick(event: PlayerInteractEvent) {
        // Prüfen ob Rechtsklick
        val action: Action = event.getAction()
        if (action !== Action.RIGHT_CLICK_AIR && action !== Action.RIGHT_CLICK_BLOCK) {
            return
        }

        val item = event.getItem()
        if (item == null || item.getType() != Material.BOOK) {
            return
        }

        val meta = item.itemMeta ?: return

        if ("fireball" == meta.displayName.lowercase()) {
            val spell = loadedSpells[meta.displayName.lowercase()]!!
            val spellEffector = SpellEffector(spell, event)
            event.player.sendMessage("Casting ${spell.name}")
            spellEffector.activate()
        }
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