package de.heimbrodt.sten.magesguild.spells

import org.bukkit.Material
import org.bukkit.inventory.ItemStack


class SpellItem {
    var item: ItemStack? = null
    var spell: Spell? = null
    constructor(spell: Spell) {
        val book = ItemStack(Material.BOOK) // oder Material.WRITTEN_BOOK
        val meta = book.itemMeta

        if (meta != null) {
            meta.setDisplayName(spell.name)
            meta.lore = mutableListOf<String?>(
                "§7${spell.description}",
            )
            book.itemMeta = meta
        }
        item = book
        this.spell = spell
    }
}