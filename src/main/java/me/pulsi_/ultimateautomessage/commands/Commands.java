package me.pulsi_.ultimateautomessage.commands;

import me.pulsi_.ultimateautomessage.UltimateAutoMessage;
import me.pulsi_.ultimateautomessage.utils.ChatUtils;
import me.pulsi_.ultimateautomessage.utils.Methods;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command command, String label, String[] args) {

        if (args.length == 0) {
            String v = UltimateAutoMessage.getInstance().getDescription().getVersion();
            s.sendMessage(ChatUtils.color("&8[&a&lUltimateAutoMessage&8] &fRunning on &av" + v + " &f, plugin made by &aPulsi_&f!"));
            return false;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (!s.hasPermission("ultimateautomessage.reload")) {
                s.sendMessage(ChatUtils.color("&8[&a&lUltimateAutoMessage&8] &cYou don't have the permission!"));
                return false;
            }

            UltimateAutoMessage.getInstance().reloadConfig();
            s.sendMessage(ChatUtils.color("&8[&a&lUltimateAutoMessage&8] &2Plugin successfully reloaded!"));
            if (s instanceof Player) Methods.playReloadSound(((Player) s));
        }
        return true;
    }
}