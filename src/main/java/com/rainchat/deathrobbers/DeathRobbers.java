package com.rainchat.deathrobbers;

import org.bukkit.plugin.java.JavaPlugin;

public final class DeathRobbers extends JavaPlugin {

    @Override
    public void onEnable() {

        ConfigManager configManager = new ConfigManager(this);
        configManager.setup();

        getServer().getPluginManager().registerEvents(new DeathListener(this, configManager), this);
    }

    @Override
    public void onDisable() {

    }
}
