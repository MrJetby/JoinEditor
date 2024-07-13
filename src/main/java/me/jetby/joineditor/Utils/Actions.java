package me.jetby.joineditor.Utils;

import me.jetby.joineditor.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Actions {
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
    public String colorized(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }}
