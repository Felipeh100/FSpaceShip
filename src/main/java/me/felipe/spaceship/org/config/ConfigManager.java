package me.felipe.spaceship.org.config;

import me.felipe.spaceship.org.RegisterProvider;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ConfigManager {

    private Map<ConfigType, ConfigHandler> configurations;

    public ConfigManager() {
        configurations = new HashMap<>();
    }

    public void loadFiles(RegisterProvider registerProvider) {
        registerFile(ConfigType.CONFIG, new ConfigHandler(registerProvider.getPlugin(), "config"));
        registerFile(ConfigType.LOCATIONS, new ConfigHandler(registerProvider.getPlugin(), "locations"));
        registerFile(ConfigType.DATABASE, new ConfigHandler(registerProvider.getPlugin(), "database"));

        configurations.values().forEach(ConfigHandler::saveDefaultConfig);
    }

    public ConfigHandler getFile(ConfigType type) {
        return configurations.get(type);
    }

    public void reloadFiles() {
        configurations.values().forEach(ConfigHandler::reload);
    }

    public void registerFile(ConfigType type, ConfigHandler config) {
        configurations.put(type, config);
    }

    public FileConfiguration getFileConfiguration(File file) {
        return YamlConfiguration.loadConfiguration(file);
    }
}
