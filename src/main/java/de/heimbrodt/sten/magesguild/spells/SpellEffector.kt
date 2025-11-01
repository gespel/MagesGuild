package de.heimbrodt.sten.magesguild.spells

import org.bukkit.Bukkit
import org.bukkit.Effect
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

class SpellEffector {
    var spell: Spell? = null
    var event: PlayerInteractEvent? = null
    var plugin: Plugin? = null
    constructor(plugin: JavaPlugin, spell: Spell, event: PlayerInteractEvent) {
        this.spell = spell
        this.event = event
        this.plugin = plugin
    }

    fun activate(onComplete: () -> Unit) {
        val loc = event!!.clickedBlock!!.location
        val block = loc.block

        val above = loc.add(0.0, 1.0, 0.0).block
        if (spell!!.element.equals("fire", true)) {
            if (above.type == Material.AIR) {
                above.type = Material.FIRE
            }

            block.world.playEffect(loc, Effect.MOBSPAWNER_FLAMES, 0)
            block.world.playSound(loc, Sound.ITEM_FLINTANDSTEEL_USE, 1.0f, 1.0f)
        }
        if(spell!!.element.equals("ice", true)) {
            if (above.type == Material.AIR) {
                above.type = Material.SNOW
            }
            block.world.playEffect(loc, Effect.POTION_BREAK, 0)
            block.world.playSound(loc, Sound.ITEM_FLINTANDSTEEL_USE, 1.0f, 1.0f)
        }

        Bukkit.getScheduler().runTaskLater(plugin!!, Runnable {
            onComplete()
        }, 20L)
    }
}