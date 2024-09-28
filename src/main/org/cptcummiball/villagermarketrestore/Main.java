package org.cptcummiball.villagermarketrestore;

import org.cptcummiball.villagermarketrestore.commands.RestoreVillagersCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("VillagerMarketRestore enabled!");
        // Registriere den Command
        this.getCommand("villagerrestore").setExecutor(new RestoreVillagersCommand(this));
    }

    @Override
    public void onDisable() {
        getLogger().info("VillagerMarketRestore disabled!");
    }
}
