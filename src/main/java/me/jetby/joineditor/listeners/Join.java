package me.jetby.joineditor.listeners;

import me.jetby.joineditor.Main;
import me.jetby.joineditor.configurations.DataBase;
import me.jetby.joineditor.utils.Actions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.*;

import static me.jetby.joineditor.configurations.Config.CFG;
import static me.jetby.joineditor.configurations.DataBase.DB;

public class Join implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        e.setJoinMessage(null);
        if (e.getPlayer().hasPlayedBefore()) {

            Set<String> JoinKeys = CFG().getConfigurationSection("Join").getKeys(false);

            for (String name : JoinKeys) {

                String perm = CFG().getString("Join." + name + ".permission", null);

                if (perm == null || p.hasPermission(perm)) {
                    List<String> actions = CFG().getStringList("Join." + name + ".actions");
                    for (String action : actions) {

                        Actions.execute(p, action);
                    }
                    return;
                }
            }

            List<String> actions = CFG().getStringList("Join.default.actions");

            if (!actions.isEmpty()) {
                for (String action : actions) {
                    Actions.execute(p, action);
                }
            }




        } else {
            if (CFG().getBoolean("FirstJoin.enable")) {
                int playerNumber;
                if (CFG().getBoolean("counting")) {
                    playerNumber = DB().getInt("number", 0);
                    DB().set("number", playerNumber+1);
                    DataBase db = new DataBase();
                    db.saveCfg(Main.getInstance());
                } else {
                    playerNumber = 0;

                }
                List<String> actions = CFG().getStringList("FirstJoin.actions");
                if (!actions.isEmpty()) {
                    for (String action : actions) {
                        Actions.execute(p, action.replace("%number%", String.valueOf(playerNumber)));
                    }
                }
            }
        }
    }
}
