package org.cptcummiball.villagermarketrestore.commands;

import org.cptcummiball.villagermarketrestore.util.VillagerRestorer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class RestoreVillagersCommand implements CommandExecutor {

    private final JavaPlugin plugin;

    public RestoreVillagersCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("villagerrestore.use")) {
            player.sendMessage("You don't have permission to use this command.");
            return true;
        }

        player.sendMessage("Restoring missing villagers...");
        
        // Starte die Wiederherstellung der Villager
        VillagerRestorer.restoreVillagers(plugin, player);

        return true;
    }
}
