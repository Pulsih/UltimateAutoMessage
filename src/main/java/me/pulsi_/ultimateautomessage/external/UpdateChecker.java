package me.pulsi_.ultimateautomessage.external;

import me.pulsi_.ultimateautomessage.UltimateAutoMessage;
import me.pulsi_.ultimateautomessage.utils.ChatUtils;
import me.pulsi_.ultimateautomessage.values.Values;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class UpdateChecker implements Listener {

    private final boolean isUpToDate;
    private final UltimateAutoMessage plugin;

    public UpdateChecker(UltimateAutoMessage plugin) {
        boolean isUpdated;
        this.plugin = plugin;
        try {
            isUpdated = isPluginUpdated();
        } catch (IOException e) {
            isUpdated = true;
        }
        this.isUpToDate = isUpdated;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!Values.isUpdateChecker() || (!p.isOp() && !p.hasPermission("ultimateautomessage.notify")) || isUpToDate) return;

        TextComponent update = new TextComponent(ChatUtils.color("&8[&aUltimateAutoMessage&8] &dNew update available! "));
        TextComponent updateButton = new TextComponent(ChatUtils.color("&7(CLICK HERE)"));
        updateButton.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org/resources/ultimateautomessage.99294/"));
        updateButton.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click here to download it!").color(ChatColor.GRAY).create()));

        update.addExtra(updateButton);

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            p.sendMessage("");
            p.spigot().sendMessage(update);
            p.sendMessage("");
        }, 80);
    }

    private boolean isPluginUpdated() throws IOException {
        final String currentVersion = new BufferedReader(new InputStreamReader(new URL("https://api.spigotmc.org/legacy/update.php?resource=99294").openConnection().getInputStream())).readLine();
        return plugin.getDescription().getVersion().equals(currentVersion);
    }
}