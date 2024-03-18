package me.jetby.joineditor.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static me.jetby.joineditor.Main.*;
import static me.jetby.joineditor.Utils.Color.color;

public class Quit implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {

        String p = e.getPlayer().getName();
        String quit = getInstance().db.getString("Players." + p + ".quit");

        if (quit.isEmpty()) {

            for (String message : cfg.getStringList("default.quit.message")) {
                e.setQuitMessage("");
                Bukkit.broadcastMessage(color(message).replace("{name}", p));
            }
        } else {

            e.setQuitMessage("");
            Bukkit.broadcastMessage(color(quit).replace("{name}", p));
        }}}
