package org.cptcummiball.villagermarketrestore.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

public class VillagerRestorer {

    public static void restoreVillagers(JavaPlugin plugin, CommandSender sender) {
        File shopFolder = new File(plugin.getDataFolder().getParentFile(), "VillagerMarket/Shops");

        if (!shopFolder.exists() || !shopFolder.isDirectory()) {
            sender.sendMessage("VillagerMarket Shops folder not found.");
            return;
        }

        File[] files = shopFolder.listFiles((dir, name) -> name.endsWith(".yml"));
        if (files == null) {
            sender.sendMessage("No shop files found.");
            return;
        }

        Yaml yaml = new Yaml();

        for (File file : files) {
            try (InputStream inputStream = new FileInputStream(file)) {
                Map<String, Object> data = yaml.load(inputStream);

                // UUID des Villagers (Dateiname ist die UUID)
                String uuidString = file.getName().replace(".yml", "");
                UUID villagerUUID = UUID.fromString(uuidString);

                // Prüfe, ob der Villager auf dem Server existiert
                if (!isVillagerPresent(villagerUUID)) {
                    // Profession und Name aus YAML auslesen
                    Map<String, Object> entityData = (Map<String, Object>) data.get("entity");
                    String profession = (String) entityData.get("profession");
                    String name = (String) entityData.get("name");

                    // Position aus YAML auslesen
                    Map<String, Object> locationData = (Map<String, Object>) entityData.get("location");
                    double x = (Double) locationData.get("x");
                    double y = (Double) locationData.get("y");
                    double z = (Double) locationData.get("z");
                    String worldName = (String) locationData.get("world");

                    World world = Bukkit.getWorld(worldName);
                    if (world == null) {
                        sender.sendMessage("World " + worldName + " not found.");
                        continue;
                    }

                    Location location = new Location(world, x, y, z);

                    // Erstelle den Villager mit der gegebenen UUID, Namen und Profession
                    Villager villager = (Villager) world.spawnEntity(location, EntityType.VILLAGER);
                    villager.setCustomName(name);
                    villager.setProfession(Villager.Profession.valueOf(profession.toUpperCase()));
                    villager.setAI(false); // Optional: AI deaktivieren, wenn notwendig
                    villager.setPersistent(true); // Villager soll persistent sein
                    villager.setUniqueId(villagerUUID);

                    sender.sendMessage("Restored villager: " + name + " at " + location.toString());

                    // Führe den VillagerMarket Restore Command aus
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "villagermarket restore");
                }
            } catch (Exception e) {
                sender.sendMessage("Error restoring villager from file: " + file.getName());
                e.printStackTrace();
            }
        }
    }

    private static boolean isVillagerPresent(UUID uuid) {
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntitiesByClass(Villager.class)) {
                if (entity.getUniqueId().equals(uuid)) {
                    return true;
                }
            }
        }
        return false;
    }
}
