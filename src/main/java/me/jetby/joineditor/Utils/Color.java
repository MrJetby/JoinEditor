package me.jetby.joineditor.Utils;

import org.bukkit.ChatColor;

public class Color {

    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
