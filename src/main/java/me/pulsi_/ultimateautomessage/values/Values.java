package me.pulsi_.ultimateautomessage.values;

import me.pulsi_.ultimateautomessage.UltimateAutoMessage;
import org.bukkit.configuration.file.FileConfiguration;

public class Values {

    private static int messagesDelay;
    private static boolean randomMode;
    private static boolean messagesToConsole;
    private static boolean updateChecker;

    public static void setupValues() {
        FileConfiguration config = UltimateAutoMessage.getInstance().getConfig();

        messagesDelay = config.getInt("Settings.Messages-Delay");
        randomMode = config.getBoolean("Settings.Random-Mode");
        messagesToConsole = config.getBoolean("Settings.Show-Messages-To-Console");
        updateChecker = config.getBoolean("Settings.Update-Checker");
    }

    public static int getMessagesDelay() {
        return messagesDelay;
    }

    public static boolean isRandomMode() {
        return randomMode;
    }

    public static boolean isMessagesToConsole() {
        return messagesToConsole;
    }

    public static boolean isUpdateChecker() {
        return updateChecker;
    }
}