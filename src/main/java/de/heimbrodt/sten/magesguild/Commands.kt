package de.heimbrodt.sten.magesguild

import de.heimbrodt.sten.magesguild.spells.LoadedSpells.Companion.loadedSpells
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Commands : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>): Boolean {
        val cmdName = cmd.name
        println(cmdName)

        if (cmdName.equals("mage", true)) {
            if (sender is Player) {
                sender.sendMessage("Enabling Mage Mode...")
                if (args.size == 2) {
                    if (args[0].equals("spells")) {
                        if (args[1].equals("list", true)) {
                        }
                    }
                }
            }
            else {
                sender.sendMessage("Enabled Spells are:")
                for (spell in loadedSpells.values) {
                    sender.sendMessage("${spell.name} - ${spell.description}")
                }
            }
        }
        if (cmdName.equals("home", true)) {
            if (sender is Player) {
                sender.sendMessage("Teleporting home...")
                sender.teleport(Location(Bukkit.getWorld("world"), 1332.0, 63.0, 3720.0))
            }
            else {
                sender.sendMessage("You are no player you dum dum...")
            }
        }
        if (cmdName.equals("village", true)) {
            if (sender is Player) {
                sender.sendMessage("Teleporting to village...")
                sender.teleport(Location(Bukkit.getWorld("world"), 1715.0, 64.0, 4151.0))
            }
        }
        
        return true
    }
}
