package me.jetby.joineditor.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

import static me.jetby.joineditor.Main.*;
import static me.jetby.joineditor.Utils.Color.color;

public class CustomMessage implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player) sender;



        if (args[0].equalsIgnoreCase("customjoin")) {
            if (!sender.hasPermission("joineditor.customjoin")) {

                p.sendMessage(color(getCfg().getString("messages.noperm")));

            }
            if (args.length>0) {

                String message = "";
                for (int i = 1; i < args.length; i++) { //цикл, который проходит по массиву аргументов
                    message = message+args[i]+" "; //сбор всех аргументов в одно целое
                }

                message.replace("{name}", p.getName());

                instance.db.set("Players." + p.getName() + "." + "join", message);
                instance.dbsave();

                for (String successMsg : instance.messages.getStringList("success")) {
                    successMsg = successMsg.replace("{msg}", message);
                    p.sendMessage(color(successMsg));
                }
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("customquit")) {
            if (!sender.hasPermission("joineditor.customquit")) {

                p.sendMessage(color(getCfg().getString("messages.noperm")));

            }
            if (args.length>0) {

                String message = "";
                for (int i = 1; i < args.length; i++) { //цикл, который проходит по массиву аргументов
                    message = message+args[i]+" "; //сбор всех аргументов в одно целое
                }

                message.replace("{name}", p.getName());

                instance.db.set("Players." + p.getName() + "quit", message);
                instance.dbsave();
                p.sendMessage("Your msg: " + color(message));
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("test")) {
            sender.sendMessage(color(getCfg().getString("Players." + p.getName() + "." + "join")));
            sender.sendMessage(color(getCfg().getString("Players." + p.getName() + "." + "quit")));
            return true;
        }
        return false;
    }
}
