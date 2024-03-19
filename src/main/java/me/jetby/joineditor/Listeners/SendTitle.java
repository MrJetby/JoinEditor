package me.jetby.joineditor.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static me.jetby.joineditor.Main.cfg;
import static me.jetby.joineditor.Utils.Color.color;

public class SendTitle implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        String title = cfg.getString("settings.title");
        String subtitle = cfg.getString("settings.subtitle");

        Player p = e.getPlayer();
        p.sendTitle(color(title), color(subtitle));
    }
}
