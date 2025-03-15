package me.jetby.joineditor.utils;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;



import static me.jetby.joineditor.utils.Color.color;
import static me.jetby.joineditor.utils.Placeholders.ps;

public class Actions {
    public static void execute(Player player, String command) {
        String[] args = command.split(" ");
        String withoutCMD = command.replace(args[0] + " ", "");
        withoutCMD = withoutCMD.replace("%player%", player.getName());


        switch (args[0]) {
            case "[MESSAGE]", "[MSG]": {
                player.sendMessage(color(ps(player, withoutCMD)));
                break;
            }
            case "[MESSAGE_ALL]", "[MSG_ALL]", "[MSG_BROADCAST]": {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.sendMessage(color(ps(p, withoutCMD)));
                }
                break;
            }
            case "[PLAYER]", "[CMD]": {
                Bukkit.dispatchCommand(player, color(ps(player, withoutCMD)));
                break;
            }
            case "[CONSOLE]", "[CONSOLE_CMD]": {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), color(ps(player, withoutCMD.replace("%player%", player.getName()))));
                break;
            }
            case "[ACTIONBAR]": {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(color(ps(player, withoutCMD
                        .replace("%player%", player.getName())))));
                break;
            }
            case "[SOUND]": {
                float volume = 1.0f;
                float pitch = 1.0f;
                for (String arg : args) {
                    if (arg.startsWith("-volume:")) {
                        volume = Float.parseFloat(arg.replace("-volume:", ""));
                        continue;
                    }
                    if (!arg.startsWith("-pitch:")) continue;
                    pitch = Float.parseFloat(arg.replace("-pitch:", ""));
                }
                player.playSound(player.getLocation(), Sound.valueOf((String) args[1]), volume, pitch);
                break;
            }
            case "[EFFECT]": {
                int strength = 0;
                int duration = 1;
                for (String arg : args) {
                    if (arg.startsWith("-strength:")) {
                        strength = Integer.parseInt(arg.replace("-strength:", ""));
                        continue;
                    }
                    if (!arg.startsWith("-duration:")) continue;
                    duration = Integer.parseInt(arg.replace("-duration:", ""));
                }
                PotionEffectType effectType = PotionEffectType.getByName((String) args[1]);
                if (effectType == null) {
                    return;
                }
                if (player.hasPotionEffect(effectType)) {
                    return;
                }
                player.addPotionEffect(new PotionEffect(effectType, duration * 20, strength));
                break;
            }
            case "[TITLE]": {
                String title = "";
                String subTitle = "";
                int fadeIn = 1;
                int stay = 3;
                int fadeOut = 1;
                for (String arg : args) {
                    if (arg.startsWith("-fadeIn:")) {
                        fadeIn = Integer.parseInt(arg.replace("-fadeIn:", ""));
                        withoutCMD = withoutCMD.replace(arg, "");
                        continue;
                    }
                    if (arg.startsWith("-stay:")) {
                        stay = Integer.parseInt(arg.replace("-stay:", ""));
                        withoutCMD = withoutCMD.replace(arg, "");
                        continue;
                    }
                    if (!arg.startsWith("-fadeOut:")) continue;
                    fadeOut = Integer.parseInt(arg.replace("-fadeOut:", ""));
                    withoutCMD = withoutCMD.replace(arg, "");
                }
                String[] message = color(withoutCMD).split(";");
                if (message.length >= 1) {
                    title = message[0];
                    if (message.length >= 2) {
                        subTitle = message[1];
                    }
                }
                player.sendTitle(ps(player, title), ps(player, subTitle), fadeIn * 20, stay * 20, fadeOut * 20);
            }
        }
    }
}