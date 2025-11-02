package de.heimbrodt.sten.magesguild.mage

import org.bukkit.entity.Player

class Mage {
    var player: Player? = null

    constructor(player: Player) {
        this.player = player
    }
}