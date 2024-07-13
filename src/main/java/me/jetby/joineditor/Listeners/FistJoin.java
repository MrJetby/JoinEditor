package me.jetby.joineditor.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

import static me.jetby.joineditor.Main.db;
import static me.jetby.joineditor.Main.settings;
import static me.jetby.joineditor.Utils.Placeholders.ps;

public class FistJoin implements Listener {


    @EventHandler
    public void onFirstJoin(PlayerJoinEvent e) {
        List<String> Player = settings.getStringList("FirstJoinCommands.Player");
        List<String> Console = settings.getStringList("FirstJoinCommands.Console");

        if (!(e.getPlayer().hasPlayedBefore())) {
        if (settings.getBoolean("enables.FirstJoinCommands.Console")==true) {
            for(String cmd : Console) {
                String newCmd = cmd;
                if(newCmd.contains("%player%")) {
                    newCmd = newCmd.replace("%player%", e.getPlayer().getName());
                }
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), ps(e.getPlayer(), newCmd));
            }
        }
        if (settings.getBoolean("enables.FirstJoinCommands.Player")==true) {
            for(String cmd : Player) {
                String newCmd = cmd;
                if(newCmd.contains("%player%")) {
                    newCmd = newCmd.replace("%player%", e.getPlayer().getName());
                }
                Bukkit.getServer().dispatchCommand(e.getPlayer(), ps(e.getPlayer(), newCmd));
            }
        }
            for (String message : settings.getStringList("settings.FirstJoin")) {
                e.setJoinMessage(null);
                Bukkit.broadcastMessage(ps(e.getPlayer(), message));
            }
            String FirstSound = settings.getString("sounds.FirstJoin");
            if (FirstSound==null) return;
            for (org.bukkit.entity.Player sounds : Bukkit.getOnlinePlayers()) {
                Sound sound = Sound.valueOf(FirstSound);
                sounds.playSound(sounds.getLocation(), sound, 1, 1);
            }
        }}}
