package me.pulsi_.ultimateautomessage.managers;

import me.pulsi_.ultimateautomessage.UltimateAutoMessage;
import me.pulsi_.ultimateautomessage.mechanics.AutoMessageMechanics;
import me.pulsi_.ultimateautomessage.values.Values;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigManager {

    private final UltimateAutoMessage plugin;
    private FileConfiguration config;
    private File configFile;

    public ConfigManager(UltimateAutoMessage plugin) {
        this.plugin = plugin;
    }

    public void createConfigs() {
        configFile = new File(plugin.getDataFolder(), "config.yml");
        if (!configFile.exists()) plugin.saveResource("config.yml", false);

        config = new YamlConfiguration();
        reloadConfig();
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
        Values.setupValues();
        AutoMessageMechanics.loadMessages();
    }
}