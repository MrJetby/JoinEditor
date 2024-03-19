package me.jetby.joineditor.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

import static me.jetby.joineditor.Main.cfg;
import static me.jetby.joineditor.Main.instance;
import static me.jetby.joineditor.Utils.Color.color;

public class Reload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {


        if (sender instanceof Player) {


        Player p = (Player) sender;

        if (!(p.hasPermission("joineditor.admin"))) {
            p.sendMessage(color(instance.messages.getString("noperm")));
            return true;
        }
            if (args[0].equalsIgnoreCase("reload")) {
                try {
                    cfg.save("config.yml"); // Перезагрузка конфигурации из файла
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                instance.reloadConfig();
                instance.dbLoad();
                instance.messageLoad();
                p.sendMessage(color(instance.messages.getString("reload")));
                return true;
            }
        } else {
            sender.sendMessage("Команда доступна только игрокам");
            return true;
        }
        return false;
    }
}