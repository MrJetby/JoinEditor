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
    public void executeAction(String action, Player p){
        FileConfiguration config = Main.instance.getConfig();
        if(action == null){
            if(config.getBoolean("config.debug-mode")){
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&eDeluxeJoin&6] &eNo action found for &e") + p.getName());
            }
        }else{
            List<String> actions = config.getStringList("join-actions." + action + ".actions");
            for(String a : actions) {
                //regex to get upperCase string inside brackets
                String regex = "\\[(.*?)\\]";
                Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
                Matcher matcher = pattern.matcher(a);
                String actionName = null;
                while (matcher.find()) {
                    actionName = matcher.group(1);
                }
                //switch statement to check for actionName values

                switch (actionName) {
                    case "MSG":
                        //get the string without "[MESSAGE]"
                        String message = a.replace("[MSG] ", "").replaceAll("%player%", p.getName());
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
                        break;
                    case "PLAYER":
                        //get the string without "[COMMAND]"
                        String command = a.replace("[COMMAND] ", "").replaceAll("%player%", p.getName());
                        p.performCommand(command);
                        break;
                    case "CONSOLE":
                        //get the string without "[CONSOLE]"
                        String console = a.replace("[CONSOLE] ", "").replaceAll("%player%", p.getName());
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), console);
                        break;
                    case "MSG_BROADCAST":
                        //get the string without "[BROADCAST]"
                        String broadcast = a.replace("[MSG_BROADCAST] ", "").replaceAll("%player%", p.getName());
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', broadcast));
                        break;
                    case "TITLE":
                        //Separate the string between /subtitle
                        String[] titleMsg = a.split(";");
                        //get the string without "[TITLEMSG]"
                        String title = titleMsg[0].replace("[TITLE] ", "").replaceAll("%player%", p.getName());
                        String subtitle = titleMsg[1].replace("[TITLE] ", "").replaceAll("%player%", p.getName());
                        p.sendTitle(colorized(title), colorized(subtitle), 20, 60, 20);
                        break;
                    case "TITLE_BROADCAST":
                        //get the string without "[TITLEBROADCAST]"
                        String[] titleBroadcastString = a.split("/subtitle");
                        String titleBroadcast = titleBroadcastString[0].replace("[TITLEBROADCAST]", "").replaceAll("%player%", p.getName());
                        //get the string after /subtitle
                        String subtitleBroadcast = titleBroadcastString[1].replace("[TITLEBROADCAST]", "").replaceAll("%player%", p.getName());
                        //for each player online send title
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.sendTitle(colorized(titleBroadcast), colorized(subtitleBroadcast), 20, 60, 20);
                        }
                        break;
                    case "SOUNDMSG":
                        //get the string without "[SOUNDMSG]"
                        String sound = a.replace("[SOUNDMSG] ", "");
                        p.playSound(p.getLocation(), Sound.valueOf(sound), 100, 1);
                        break;
                    case "SOUNDBROADCAST":
                        //get the string without "[SOUNDBROADCAST]"
                        String soundBroadcast = a.replace("[SOUNDBROADCAST] ", "");
                        //for each player online play sound
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.playSound(player.getLocation(), Sound.valueOf(soundBroadcast), 1, 1);
                        }
                        break;
                    case "ACTIONBARMSG":
                        //get the string without "[ACTIONBARMSG]"
                        String actionbar = a.replace("[ACTIONBARMSG] ", "").replaceAll("%player%", p.getName());
                        //send actionbar message to the player
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(colorized(actionbar)));
                        break;
                    case "ACTIONBARBROADCAST":
                        //get the string without "[ACTIONBARBROADCAST]"
                        String actionbarBroadcast = a.replace("[ACTIONBARBROADCAST] ", "").replaceAll("%player%", p.getName());
                        //for each player online send actionbar message
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(colorized(actionbarBroadcast)));
                        }
                        break;
                    default:
                        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&eDeluxeJoin&6] &eNo action type found for &e") + a);
                        break;
                }
            }
        }

}
    public String colorized(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }}
