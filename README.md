## VillagerRestore

**VillagerRestore** is a Bukkit (Spigot) plugin that allows server admins to restore lost villagers from the VillagerMarket plugin. Sometimes, VillagerMarket villagers can be lost or not properly restored by the plugin itself, and this tool helps to resolve that issue by checking if the villagers associated with shop files are still present on the server. If any villagers are missing, the plugin automatically restores them based on the information stored in the shop's YAML file.

### Features
- **Manual restoration**: Restores missing villagers from the VillagerMarket's shop folder with a simple command.
- **UUID-based restoration**: Ensures the restored villagers have the same UUID, name, profession, and location as specified in the VillagerMarket configuration files.
- **Automatic VillagerMarket restore**: Triggers the `/villagermarket restore` command after recreating villagers.

### How it works
1. The plugin reads the shop configuration files from the `VillagerMarket/Shops` folder.
2. It checks if the villagers specified in these files (based on UUID) are still present on the server.
3. If a villager is missing, the plugin will recreate the villager at the specified location with the same profession and name.
4. After all missing villagers are restored, the `/villagermarket restore` command is executed to ensure full integration with VillagerMarket.

### Usage

#### Command
The plugin provides a single command for manually restoring villagers:

’’/villagerrestore’’

#### Permissions
To use the command, players must have the following permission:
- `villagerrestore.use`: Allows the player to restore missing villagers. (Default: OP)

### Installation

1. Download the latest release of **VillagerRestore**.
2. Place the `.jar` file in your server's `plugins` directory.
3. Restart your server.
4. Ensure that the `VillagerMarket` plugin is installed and properly configured, as this plugin interacts with its shop files.

### Requirements
- Minecraft server running **Spigot 1.20.4** or newer.
- **VillagerMarket** plugin installed and configured.

### Notes
- This plugin does not automatically restore villagers; it requires manual execution of the `/villagerrestore` command.
- Ensure that the world specified in the shop configuration files still exists on the server.

### License
This plugin is distributed under the MIT License.
