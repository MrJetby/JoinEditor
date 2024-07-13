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

        if (settings.getBoolean("enables.title")==false) {
            if (settings.getBoolean("enables.subtitle")==true) {
                if (title==null || subtitle!=null) {
                    p.sendTitle(" ", ps(p, subtitle));
                }
                return;
            }
            return;
        } else {
            if (title!=null || subtitle==null) {
                p.sendTitle(ps(p, title), " ");
            }

        }
        if (settings.getBoolean("enables.subtitle")==false) {
            return;
        } else {
            if (title==null || subtitle!=null) {
                p.sendTitle(" ", ps(p, subtitle));
            }
        }
        if (title==null || subtitle==null) return;


        p.sendTitle(ps(p, title), ps(p, subtitle));
    }
}
