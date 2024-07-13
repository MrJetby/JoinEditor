package me.jetby.joineditor.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.jetby.joineditor.Main.instance;
import static me.jetby.joineditor.Utils.Color.color;
import static me.jetby.joineditor.Utils.Placeholders.ps;

public class CustomQuit implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player p = (Player) sender;

        if (args[0].equalsIgnoreCase("customquit")) {
            if (!sender.hasPermission("joineditor.customquit")) {

                p.sendMessage(color(instance.messages.getString("noperm")));
                return true;

            }
            if (args.length>=0) {

                String message = "";
                for (int i = 0; i < args.length; i++) { //цикл, который проходит по массиву аргументов
                    message = message+args[i]+" "; //сбор всех аргументов в одно целое
                }


                instance.db.set("Players." + p.getName() + "." + "quit", ps(p, message));
                instance.dbsave();
                for (String successMsg : instance.messages.getStringList("success")) {
                    successMsg = successMsg.replace("%msg%", message);
                    p.sendMessage(ps(p, successMsg));
                }
            }
            return true;
        }

        return false;
    }
}
