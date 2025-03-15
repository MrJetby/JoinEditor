package me.jetby.joineditor.commands;

import me.jetby.joineditor.Main;
import me.jetby.joineditor.configurations.Config;
import me.jetby.joineditor.configurations.DataBase;
import me.jetby.joineditor.configurations.Messages;
import me.jetby.joineditor.utils.Actions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;

import static me.jetby.joineditor.configurations.Config.CFG;
import static me.jetby.joineditor.configurations.Messages.MSG;
import static me.jetby.joineditor.utils.Color.color;

public class CustomMessage implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player) sender;

        if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("joineditor.admin")) {

                p.sendMessage(MSG().getString("noperm"));
                return true;

            } else {

                Config cfg = new Config();
                cfg.reloadCfg(Main.getInstance());

                DataBase db = new DataBase();
                db.reloadCfg(Main.getInstance());

                Messages msg = new Messages();
                msg.reloadCfg(Main.getInstance());
                p.sendMessage(MSG().getString("reload"));
                return true;
            }
        }



        if (args[0].equalsIgnoreCase("test")) {
            if (!sender.hasPermission("joineditor.admin")) {

                p.sendMessage(color(MSG().getString("noperm")));

            } else {
                if (args[1].equalsIgnoreCase("Join")) {
                    Set<String> JoinKeys = CFG().getConfigurationSection("Join").getKeys(false);

                    for (String name : JoinKeys) {

                        String perm = CFG().getString("Join." + name + ".permission", null);

                        if (perm == null || p.hasPermission(perm)) {
                            List<String> actions = CFG().getStringList("Join." + name + ".actions");
                            for (String action : actions) {
                                Actions.execute(p, action);
                            }
                            return true;
                        }
                    }
                    List<String> actions = CFG().getStringList("Join.default.actions");
                    if (actions.isEmpty()) {
                        return true;
                    }
                    for (String action : actions) {
                        Actions.execute(p, action);
                    }
                    return true;
                }
                if (args[1].equalsIgnoreCase("FirstJoin")) {
                    List<String> actions = CFG().getStringList("FirstJoin.actions");
                    if (actions.isEmpty()) {
                        return true;
                    }
                    for (String action : actions) {
                        Actions.execute(p, action);
                    }
                    return true;
                }
                if (args[1].equalsIgnoreCase("Quit")) {
                    Set<String> JoinKeys = CFG().getConfigurationSection("Quit").getKeys(false);

                    for (String name : JoinKeys) {

                        String perm = CFG().getString("Quit." + name + ".permission", null);

                        if (perm == null || p.hasPermission(perm)) {
                            List<String> actions = CFG().getStringList("Quit." + name + ".actions");
                            for (String action : actions) {
                                Actions.execute(p, action);
                            }
                            return true;
                        }
                    }
                    List<String> actions = CFG().getStringList("Quit.default.actions");
                    if (actions.isEmpty()) {
                        return true;
                    }
                    for (String action : actions) {
                        Actions.execute(p, action);
                    }
                    return true;
                }
                if (args[1].equalsIgnoreCase("FirstQuit")) {
                    List<String> actions = CFG().getStringList("FirstQuit.actions");
                    if (actions.isEmpty()) {
                        return true;
                    }
                    for (String action : actions) {
                        Actions.execute(p, action);
                    }
                    return true;
                }
            }
        return true;
        }
        return false;
    }
}
