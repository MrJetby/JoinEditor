package me.jetby.joineditor.Listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static me.jetby.joineditor.Main.settings;
import static me.jetby.joineditor.Utils.Placeholders.ps;

public class SendTitle implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        String title = settings.getString("settings.title");
        String subtitle = settings.getString("settings.subtitle");

        Player p = e.getPlayer();

        p.sendTitle(ps(p, title), ps(p, subtitle));
    }
}
