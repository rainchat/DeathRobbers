package com.rainchat.deathrobbers;


import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ConfigManager {

    public boolean blackList;
    public List<Integer> protectedDrop;
    public List<Material> protectedItems;

    private final Plugin plugin;

    public ConfigManager(Plugin plugin) {
        this.plugin = plugin;
        protectedDrop = new ArrayList<>();
        protectedItems = new ArrayList<>();
    }

    public void setup() {
        plugin.saveDefaultConfig();
        blackList = plugin.getConfig().getBoolean("blackList");

        ConfigurationSection section = plugin.getConfig().getConfigurationSection("protected-slots");
        if (section != null) {
            protectedDrop = (List<Integer>) section.getList("black-slots");
        }

        section = plugin.getConfig().getConfigurationSection("protected-items");
        for (String path : section.getKeys(false)) {

            try {
                Material material = Material.valueOf(section.getString(path + ".material").toUpperCase());
                protectedItems.add(material);
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }

}
