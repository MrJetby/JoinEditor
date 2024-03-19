package me.jetby.joineditor.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static me.jetby.joineditor.Main.cfg;
import static me.jetby.joineditor.Utils.Color.color;

public class Motd implements Listener {

    @EventHandler
    public void Motd(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        for (String message : cfg.getStringList("settings.motd")) {
            p.sendMessage(color(message));
        }
    }
}
