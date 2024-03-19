package me.jetby.joineditor.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static me.jetby.joineditor.Main.*;
import static me.jetby.joineditor.Utils.Color.color;

public class Quit implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {

        String p = e.getPlayer().getName();
        String quit = getInstance().db.getString("Players." + p + ".quit");

        if (quit == null || quit.isEmpty()) {

            for (String message : cfg.getStringList("settings.quit")) {
                e.setQuitMessage("");
                message = message.replace("%player%", p);
                Bukkit.broadcastMessage(color(message));
            }
        } else {
            for (String message : cfg.getStringList("settings.customquit")) {
                e.setQuitMessage("");
                message = message.replace("%player%", p);
                message = message.replace("%msg%", quit);
                Bukkit.broadcastMessage(color(message));
            }

        }}}