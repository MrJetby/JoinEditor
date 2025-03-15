package me.jetby.joineditor.listeners;

import me.jetby.joineditor.utils.Actions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;
import java.util.Set;

import static me.jetby.joineditor.Main.*;
import static me.jetby.joineditor.configurations.Config.CFG;

public class Quit implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        e.setQuitMessage(null);
        if (e.getPlayer().hasPlayedBefore()) {

            Set<String> JoinKeys = CFG().getConfigurationSection("Quit").getKeys(false);

            for (String name : JoinKeys) {

                String perm = CFG().getString("Quit." + name + ".permission", null);

                if (perm == null || p.hasPermission(perm)) {
                    List<String> actions = CFG().getStringList("Quit." + name + ".actions");
                    for (String action : actions) {
                        Actions.execute(p, action);
                    }
                    return;
                }
            }

            List<String> actions = CFG().getStringList("Quit.default.actions");
            if (actions.isEmpty()) {
                return;
            }

            for (String action : actions) {
                Actions.execute(p, action);
            }



        } else {
            if (CFG().getBoolean("FirstQuit.enable")) {
                List<String> actions = CFG().getStringList("FirstQuit.actions");
                for (String action : actions) {
                    Actions.execute(p, action);
                }
            }
        }
    }
}
