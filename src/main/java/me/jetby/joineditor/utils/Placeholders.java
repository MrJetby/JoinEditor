package me.jetby.joineditor.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public class Placeholders {

    public static String ps(Player p, String string) {
        return PlaceholderAPI.setPlaceholders(p, string
                .replace("%player%", p.getName()));
    }

}
