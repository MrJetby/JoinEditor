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

        if (join.isEmpty()) {

            for (String message : cfg.getStringList("default.join.message")) {
                e.setJoinMessage("");
                Bukkit.broadcastMessage(color(message).replace("{name}", p));
            }
        } else {

                e.setJoinMessage("");
                Bukkit.broadcastMessage(color(join).replace("{name}", p));


        }}}