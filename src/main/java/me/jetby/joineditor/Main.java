package me.jetby.joineditor;

import me.jetby.joineditor.Commands.CustomJoin;
import me.jetby.joineditor.Commands.CustomMessage;
import me.jetby.joineditor.Commands.CustomQuit;
import me.jetby.joineditor.Commands.TabCompleter;
import me.jetby.joineditor.Listeners.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public final class Main extends JavaPlugin {

    public static Main instance;

    public static YamlConfiguration settings;
    public static YamlConfiguration db;
    public YamlConfiguration messages;


    @Override
    public void onEnable() {

        instance = this;

        getServer().getPluginManager().registerEvents(new Join(), this);
        getServer().getPluginManager().registerEvents(new FistJoin(), this);
        getServer().getPluginManager().registerEvents(new Quit(), this);
        getServer().getPluginManager().registerEvents(new SendTitle(), this);
        getServer().getPluginManager().registerEvents(new Motd(), this);
        getCommand("joineditor").setExecutor(new CustomMessage());
        getCommand("customjoin").setExecutor(new CustomJoin());
        getCommand("customquit").setExecutor(new CustomQuit());
        getCommand("joineditor").setTabCompleter(new TabCompleter());

        dbLoad();
        messageLoad();
        settingsLoad();
    }

    public void dbLoad() {
        saveResource("db.yml", false);
        File file = new File(getDataFolder().getAbsolutePath() + "/db.yml");
        db = YamlConfiguration.loadConfiguration(file);
    }
    public void messageLoad() {
        saveResource("messages.yml", false);
        File file = new File(getDataFolder().getAbsolutePath() + "/messages.yml");
        messages = YamlConfiguration.loadConfiguration(file);
    }

    public void settingsLoad() {
        saveResource("settings.yml", false);
        File file = new File(getDataFolder().getAbsolutePath() + "/settings.yml");
        settings = YamlConfiguration.loadConfiguration(file);
    }

    public void dbsave() {
        try {
            File file = new File(getDataFolder(), "db.yml");
            db.save(file);
        } catch (IOException e) {
            getLogger().log(Level.SEVERE, "Не удалось сохранить файл db.yml", e);
        }
    }
}
