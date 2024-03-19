package me.jetby.joineditor;

import me.jetby.joineditor.Commands.CustomMessage;
import me.jetby.joineditor.Commands.Reload;
import me.jetby.joineditor.Commands.TabCompleter;
import me.jetby.joineditor.Listeners.Join;
import me.jetby.joineditor.Listeners.Motd;
import me.jetby.joineditor.Listeners.Quit;
import me.jetby.joineditor.Listeners.SendTitle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public final class Main extends JavaPlugin {

    public static Main instance;

    public static FileConfiguration cfg;
    public YamlConfiguration db;
    public YamlConfiguration messages;
    public YamlConfiguration config;
    public static Main getInstance() {
        return instance;
    }

    public static FileConfiguration getCfg() {
        return cfg;
    }

    @Override
    public void onEnable() {

        instance = this;
        cfg = getConfig();

        getServer().getPluginManager().registerEvents(new Join(), this);
        getServer().getPluginManager().registerEvents(new Quit(), this);
        getServer().getPluginManager().registerEvents(new SendTitle(), this);
        getServer().getPluginManager().registerEvents(new Motd(), this);
        getCommand("joineditor").setExecutor(new CustomMessage());
        getCommand("joineditor").setExecutor(new Reload());
        getCommand("joineditor").setTabCompleter(new TabCompleter());

        saveDefaultConfig();
        reloadConfig();

        dbLoad();
        messageLoad();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
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

    public void dbsave() {
        try {
            File file = new File(getDataFolder(), "db.yml");
            db.save(file);
        } catch (IOException e) {
            getLogger().log(Level.SEVERE, "Не удалось сохранить файл db.yml", e);
        }
    }
}
