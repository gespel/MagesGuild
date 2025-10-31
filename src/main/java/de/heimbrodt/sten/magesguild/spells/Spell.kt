package de.heimbrodt.sten.magesguild.spells

import kotlinx.serialization.Serializable

@Serializable
data class Spell (
    val name: String,
    val manaCost: Int,
    val description: String,
    val damage: Int,
    val range: Int
)