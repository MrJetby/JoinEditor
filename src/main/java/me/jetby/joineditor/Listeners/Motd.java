package me.jetby.joineditor.Listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static me.jetby.joineditor.Main.settings;
import static me.jetby.joineditor.Utils.Color.color;
import static me.jetby.joineditor.Utils.Placeholders.ps;

public class Motd implements Listener {

    @EventHandler
    public void Motd(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        for (String message : settings.getStringList("settings.motd")) {
            message =  ps(p, color(message));

                    p.sendMessage(message);
        }
    }
}
