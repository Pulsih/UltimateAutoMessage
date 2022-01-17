package me.pulsi_.ultimateautomessage.managers;

import me.pulsi_.ultimateautomessage.UltimateAutoMessage;
import me.pulsi_.ultimateautomessage.commands.Commands;
import me.pulsi_.ultimateautomessage.external.UpdateChecker;
import me.pulsi_.ultimateautomessage.utils.ChatUtils;

public class DataManager {

    public static void setupPlugin() {
        UltimateAutoMessage plugin = UltimateAutoMessage.getInstance();

        long ms = System.currentTimeMillis();

        plugin.getCommand("ultimateautomessage").setExecutor(new Commands());
        plugin.getServer().getPluginManager().registerEvents(new UpdateChecker(plugin), plugin);

        ChatUtils.log("");
        ChatUtils.log("  &8[&a&lUltimateAutoMessage&8] &3v" + plugin.getDescription().getVersion());
        ChatUtils.log("     &2Plugin enabled! &8(&2" + (System.currentTimeMillis() - ms) + "ms&8)");
        ChatUtils.log("");
    }


    public static void shutdownPlugin() {
        ChatUtils.log("");
        ChatUtils.log("  &8[&a&lUltimateAutoMessage&8] &cPlugin disabled!");
        ChatUtils.log("");
    }
}