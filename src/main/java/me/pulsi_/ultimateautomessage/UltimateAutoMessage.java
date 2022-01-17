package me.pulsi_.ultimateautomessage;

import me.pulsi_.ultimateautomessage.managers.ConfigManager;
import me.pulsi_.ultimateautomessage.managers.DataManager;
import me.pulsi_.ultimateautomessage.mechanics.AutoMessageMechanics;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class UltimateAutoMessage extends JavaPlugin {

    private static UltimateAutoMessage instance;
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        instance = this;

        configManager = new ConfigManager(this);
        configManager.createConfigs();

        DataManager.setupPlugin();
        AutoMessageMechanics.processAutoMessage(0);
    }

    @Override
    public void onDisable() {
        instance = this;
        DataManager.shutdownPlugin();
    }

    public static UltimateAutoMessage getInstance() {
        return instance;
    }

    public FileConfiguration getConfig() {
        return configManager.getConfig();
    }

    public void reloadConfig() {
        configManager.reloadConfig();
    }
}