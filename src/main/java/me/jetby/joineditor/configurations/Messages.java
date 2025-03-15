package me.jetby.joineditor.configurations;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import static org.bukkit.Bukkit.getLogger;

public class Messages {
    private static FileConfiguration config;
    private static File file;

    public void loadYamlFile(Plugin plugin) {
        file = new File(plugin.getDataFolder(), "messages.yml");
        if (!file.exists()) { //проверка на то есть ли файл, если нет - создаётся папка и сохраняется файл.
            plugin.getDataFolder().mkdirs();
            plugin.saveResource("messages.yml", true);
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration MSG() {
        return config;
    }

    public void reloadCfg(Plugin plugin) {
        if(!file.exists()) { //проверка на то есть ли файл, если нет - создаётся папка и сохраняется файл.
            plugin.getDataFolder().mkdirs();
            plugin.saveResource("messages.yml", true);
        }
        try {
            config.load(file);
            Bukkit.getConsoleSender().sendMessage("Конфигурация перезагружена! (messages.yml)");
        } catch (IOException | InvalidConfigurationException e) {
            Bukkit.getConsoleSender().sendMessage("Не удалось перезагрузить конфигурацию! (messages.yml)");
        }
    }
    public void saveCfg(Plugin plugin) {
        try {
            File file = new File(plugin.getDataFolder(), "messages.yml");
            config.save(file);
        } catch (IOException e) {
            getLogger().log(Level.SEVERE, "Не удалось сохранить файл messages.yml", e);
        }
    }
}
