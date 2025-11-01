package de.heimbrodt.sten.magesguild.spells

import kotlinx.serialization.builtins.ListSerializer
import org.yaml.snakeyaml.Yaml
import java.io.File

class SpellLoader {
    companion object {
        fun loadSpells(name: String): List<Spell> {
            val yaml = Yaml()
            val data = yaml.load<List<Map<String, Any>>>(File(name).readText())
            val spells = data.map {
                Spell(it["name"] as String,
                    it["manaCost"] as Int,
                    it["description"] as String,
                    it["damage"] as Int,
                    it["range"] as Int,
                    it["element"] as String
                )
            }
            return spells
        }
    }

}