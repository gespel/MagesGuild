package de.heimbrodt.sten.magesguild.spells

import org.bukkit.Effect
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.event.player.PlayerInteractEvent

class SpellEffector {
    var spell: Spell? = null
    var event: PlayerInteractEvent? = null
    constructor(spell: Spell, event: PlayerInteractEvent) {
        this.spell = spell
        this.event = event
    }

    fun activate() {
        val loc = event!!.clickedBlock!!.location
        val block = loc.block

        val above = loc.add(0.0, 1.0, 0.0).block

        if (above.type == Material.AIR) {
            above.type = Material.FIRE
        }

        block.world.playEffect(loc, Effect.MOBSPAWNER_FLAMES, 0)
        block.world.playSound(loc, Sound.ITEM_FLINTANDSTEEL_USE, 1.0f, 1.0f)
    }
}