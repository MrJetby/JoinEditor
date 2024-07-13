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
        Player p = e.getPlayer();
        List<String> Console = settings.getStringList("commandsOnJoin.Console");

        List<String> defaultActions = settings.getStringList("Join.default.actions");
        if (e.getPlayer().hasPlayedBefore()) {
        for (String act : defaultActions) {
                executeAction(p, act);
           }
        }
    }


    public void executeAction(Player player, String action) {
        String command = action.substring(action.indexOf(' ') + 1);
        switch (action.split("]")[0]) {
            case "[MSG":
                player.sendMessage(command);
                break;
            case "[MSG_BROADCAST":
                Bukkit.broadcastMessage(command);
                break;
            case "[TITLE":
                String[] titleParts = command.split(";");
                player.sendTitle(titleParts[0], titleParts[1], 10, 70, 20);
                break;
            case "[TITLE_BROADCAST":
                String[] broadcastTitleParts = command.split(";");
                Bukkit.getOnlinePlayers().forEach(p -> p.sendTitle(broadcastTitleParts[0], broadcastTitleParts[1], 10, 70, 20));
                break;

            case "[CONSOLE":
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", player.getName()));
                break;
            case "[PLAYER":
                Bukkit.dispatchCommand(player, command);
                break;
            case "[SOUND":
                player.playSound(player.getLocation(), Sound.valueOf(command), 1.0F, 1.0F);
                break;
        }
}
}
