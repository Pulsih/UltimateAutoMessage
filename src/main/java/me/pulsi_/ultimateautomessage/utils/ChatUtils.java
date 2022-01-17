package me.pulsi_.ultimateautomessage.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class ChatUtils {
    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    public static void logPref(String message) {
        Bukkit.getConsoleSender().sendMessage(color("&8[&a&lUltimateAutoMessage&8] " + message));
    }
    public static void log(String message) {
        Bukkit.getConsoleSender().sendMessage(color(message));
    }
}