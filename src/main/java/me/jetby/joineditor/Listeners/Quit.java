package me.jetby.joineditor.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static me.jetby.joineditor.Main.*;
import static me.jetby.joineditor.Utils.Color.color;
import static me.jetby.joineditor.Utils.Placeholders.ps;

public class Quit implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {

        String p = e.getPlayer().getName();
        String quit = db.getString("Players." + p + ".quit");

        if (quit == null || quit.isEmpty()) {

            for (String message : settings.getStringList("settings.quit")) {
                e.setQuitMessage("");
                Bukkit.broadcastMessage(ps(e.getPlayer(), message));
            }
        } else {
            for (String message : settings.getStringList("settings.customquit")) {
                e.setQuitMessage("");
                message = message.replace("%msg%", quit);
                Bukkit.broadcastMessage(ps(e.getPlayer(), message));
            }

        }}}