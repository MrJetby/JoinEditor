package me.jetby.joineditor.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] args) {
        if (args.length==1 && sender.hasPermission("joineditor.admin")) {
            return List.of(
                    "reload",
                    "test"
            );
        }

        if (args[0].equalsIgnoreCase("test") && args.length==2 && sender.hasPermission("joineditor.admin")) {
                return List.of(
                        "Join",
                        "FirstJoin",
                        "Quit",
                        "FirstQuit"
                );
            }
        return null;
    }
}
