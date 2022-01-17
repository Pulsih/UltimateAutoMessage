package me.pulsi_.ultimateautomessage.mechanics;

import me.pulsi_.ultimateautomessage.UltimateAutoMessage;
import me.pulsi_.ultimateautomessage.utils.ChatUtils;
import me.pulsi_.ultimateautomessage.utils.Methods;
import me.pulsi_.ultimateautomessage.values.Values;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AutoMessageMechanics {

    public static List<List<String>> autoMessagesList;

    public static void processAutoMessage(int count) {
        if (Values.isRandomMode()) {
            int randomInt = new Random().nextInt(autoMessagesList.size());
            AutoMessageMechanics.sendMessage(randomInt);
            Bukkit.getScheduler().runTaskLater(UltimateAutoMessage.getInstance(), () -> processAutoMessage(0), Methods.secondsInTicks(Values.getMessagesDelay()));
            return;
        }

        if (AutoMessageMechanics.isAutoMessageListEnded(count)) count = 0;
        AutoMessageMechanics.sendMessage(count);
        count++;

        int finalCount = count;
        Bukkit.getScheduler().runTaskLater(UltimateAutoMessage.getInstance(), () -> processAutoMessage(finalCount), Methods.secondsInTicks(Values.getMessagesDelay()));
    }

    public static void loadMessages() {
        AutoMessageMechanics.autoMessagesList = new ArrayList<>();
        FileConfiguration config = UltimateAutoMessage.getInstance().getConfig();

        ConfigurationSection section = config.getConfigurationSection("Messages");
        for (String key : section.getKeys(false)) {
            List<String> singleMessageList = config.getStringList("Messages." + key);
            AutoMessageMechanics.autoMessagesList.add(singleMessageList);
        }
    }

    private static void sendMessage(int count) {
        List<String> autoMessage = autoMessagesList.get(count);

        if (Values.isMessagesToConsole())
            for (String message : autoMessage) ChatUtils.log(message);

        for (Player p : Bukkit.getOnlinePlayers())
            for (String message : autoMessage)
                p.sendMessage(ChatUtils.color(message));
    }

    private static boolean isAutoMessageListEnded(int count) {
        return count >= autoMessagesList.size();
    }
}