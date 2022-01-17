package me.pulsi_.ultimateautomessage.utils;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Methods {

    public static int secondsInTicks(int seconds) {
        return seconds * 20;
    }

    public static void playReloadSound(Player p) {
        Sound sound;
        try {
            sound = Sound.ENTITY_ARROW_SHOOT;
        } catch (IllegalArgumentException e) {
            sound = Sound.valueOf("SHOOT_ARROW");
        }
        p.playSound(p.getLocation(), sound, 10, 1);
    }
}