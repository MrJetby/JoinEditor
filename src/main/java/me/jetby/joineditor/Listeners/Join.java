package me.jetby.joineditor.Listeners;

import me.jetby.joineditor.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

import static me.jetby.joineditor.Main.*;
import static me.jetby.joineditor.Utils.Color.color;
import static me.jetby.joineditor.Utils.Placeholders.ps;

public class Join implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        List<String> Player = settings.getStringList("commandsOnJoin.Player");
        List<String> Console = settings.getStringList("commandsOnJoin.Console");
        String p = e.getPlayer().getName();
        String join = db.getString("Players." + p + ".join");
        if (e.getPlayer().hasPlayedBefore()) {
        if (settings.getBoolean("enables.commandsOnJoin.Console")==true) {
            for(String cmd : Console) {
                String newCmd = cmd;
                if(newCmd.contains("%player%")) {
                    newCmd = newCmd.replace("%player%", e.getPlayer().getName());
                }
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), ps(e.getPlayer(), newCmd));
            }
        }
        if (settings.getBoolean("enables.commandsOnJoin.Player")==true) {
            for(String cmd : Player) {
                String newCmd = cmd;
                if(newCmd.contains("%player%")) {
                    newCmd = newCmd.replace("%player%", e.getPlayer().getName());
                }
                Bukkit.getServer().dispatchCommand(e.getPlayer(), ps(e.getPlayer(), newCmd));
            }
        }
        if (join == null || join.isEmpty()) {

            for (String message : settings.getStringList("settings.join")) {
                e.setJoinMessage(null);
                Bukkit.broadcastMessage(ps(e.getPlayer(), message));
            }
            for (Player sounds : Bukkit.getOnlinePlayers()) {
                String soundName = settings.getString("sounds.Join");
                Sound sound = Sound.valueOf(soundName);
                sounds.playSound(sounds.getLocation(), sound, 1, 1);
            }
        } else {
            for (String message : settings.getStringList("settings.customjoin")) {
                e.setJoinMessage(null);
                message = message.replace("%msg%", join);
                Bukkit.broadcastMessage(ps(e.getPlayer(), message));
            }
            for (Player sounds : Bukkit.getOnlinePlayers()) {
                String soundName = settings.getString("sounds.CustomJoin");
                Sound sound = Sound.valueOf(soundName);
                sounds.playSound(sounds.getLocation(), sound, 1, 1);
            }
        }}}}