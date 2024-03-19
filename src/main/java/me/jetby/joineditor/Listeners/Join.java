package me.jetby.joineditor.Listeners;

import me.jetby.joineditor.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

import static me.jetby.joineditor.Main.*;
import static me.jetby.joineditor.Utils.Color.color;

public class Join implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        String p = e.getPlayer().getName();
        String join = getInstance().db.getString("Players." + p + ".join");

        if (join == null || join.isEmpty()) {

            for (String message : cfg.getStringList("settings.join")) {
                e.setJoinMessage("");
                message = message.replace("%player%", p);
                Bukkit.broadcastMessage(color(message));
            }
        } else {
            for (String message : cfg.getStringList("settings.customjoin")) {
                e.setJoinMessage("");
                message = message.replace("%player%", p);
                message = message.replace("%msg%", join);
                Bukkit.broadcastMessage(color(message));
            }

        }}}