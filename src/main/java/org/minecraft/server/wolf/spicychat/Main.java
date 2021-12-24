package org.minecraft.server.wolf.spicychat;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {
    static FileConfiguration config;
    static JavaPlugin instance;
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        saveDefaultConfig();
        ConfigLoader.configLoad();
        if(Bukkit.getPluginCommand("spicychat") != null)
        {
            Objects.requireNonNull(Bukkit.getPluginCommand("spicychat")).setExecutor(new CommandExecutor());
        }
        Bukkit.getPluginManager().registerEvents(new PlayerChatLisener(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
