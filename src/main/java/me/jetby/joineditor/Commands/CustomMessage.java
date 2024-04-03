package me.jetby.joineditor.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

import static me.jetby.joineditor.Main.*;
import static me.jetby.joineditor.Utils.Color.color;
import static me.jetby.joineditor.Utils.Placeholders.ps;

public class CustomMessage implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player) sender;

        if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("joineditor.admin")) {

                p.sendMessage(color(instance.messages.getString("noperm")));

            } else {
                instance.settingsLoad();
                instance.dbLoad();
                instance.messageLoad();
                p.sendMessage(color(instance.messages.getString("reload")));
                return true;
            }
        }

        if (args[0].equalsIgnoreCase("customjoin")) {
            if (!sender.hasPermission("joineditor.customjoin")) {

                p.sendMessage(color(instance.messages.getString("noperm")));

            }
            if (args.length>0) {

                String message = "";
                for (int i = 1; i < args.length; i++) { //цикл, который проходит по массиву аргументов
                    message = message+args[i]+" "; //сбор всех аргументов в одно целое
                }

                instance.db.set("Players." + p.getName() + "." + "join", ps(p, message));
                instance.dbsave();

                for (String successMsg : instance.messages.getStringList("success")) {
                    successMsg = successMsg.replace("%msg%", message);
                    p.sendMessage(ps(p, successMsg));
                }
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("customquit")) {
            if (!sender.hasPermission("joineditor.customquit")) {

                p.sendMessage(color(instance.messages.getString("noperm")));

            }
            if (args.length>0) {

                String message = "";
                for (int i = 1; i < args.length; i++) { //цикл, который проходит по массиву аргументов
                    message = message+args[i]+" "; //сбор всех аргументов в одно целое
                }


                instance.db.set("Players." + p.getName() + "." + "quit", ps(p, message));
                instance.dbsave();
                p.sendMessage("Your msg: " + color(message));
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("test")) {
            if (!sender.hasPermission("joineditor.admin")) {

                p.sendMessage(color(instance.messages.getString("noperm")));

            } else {
                if (args[1].equalsIgnoreCase("title")) {
                    p.sendTitle(ps(p, instance.settings.getString("settings.title")), ps(p, settings.getString("settings.subtitle")));
                    return true;
                }
                if (args[1].equalsIgnoreCase("join")) {
                    for (String message : settings.getStringList("settings.join")) {
                        p.sendMessage(ps(p, message));
                    }
                    return true;
                }
                if (args[1].equalsIgnoreCase("quit")) {
                    for (String message : settings.getStringList("settings.quit")) {
                        p.sendMessage(ps(p, message));
                    }
                    return true;
                }
                if (args[1].equalsIgnoreCase("motd")) {
                    for (String message : settings.getStringList("settings.motd")) {
                        message =  ps(p, color(message));

                        p.sendMessage(message);
                    }
                    return true;
                }}
        return true;
        }
        return false;
    }
}
