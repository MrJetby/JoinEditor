package me.jetby.joineditor;

import me.jetby.joineditor.commands.CustomMessage;
import me.jetby.joineditor.commands.TabCompleter;
import me.jetby.joineditor.configurations.Config;
import me.jetby.joineditor.configurations.DataBase;
import me.jetby.joineditor.configurations.Messages;
import me.jetby.joineditor.listeners.*;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;


public final class Main extends JavaPlugin {

    private static Main instance;


    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        Config cfg = new Config();
        cfg.loadYamlFile(this);

        DataBase db = new DataBase();
        db.loadYamlFile(this);

        Messages msg = new Messages();
        msg.loadYamlFile(this);

        getServer().getPluginManager().registerEvents(new Join(), this);
        getServer().getPluginManager().registerEvents(new Quit(), this);
        getCommand("joineditor").setExecutor(new CustomMessage());
        getCommand("joineditor").setTabCompleter(new TabCompleter());

        int pluginId = 24870;
        Metrics metrics = new Metrics(this, pluginId);


    }

}
