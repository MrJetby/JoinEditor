package me.jetby.joineditor.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] args) {

        if (args.length==1) {
            return List.of(
                    "customjoin",
                    "customquit"
            );
        }

        return null;
    }
}
