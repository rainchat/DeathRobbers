package com.rainchat.deathrobbers;

import com.rainchat.deathrobbers.api.PlayerDeathDropEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class DeathListener implements Listener {

    private final Plugin plugin;
    private final ConfigManager configManager;

    public DeathListener(Plugin plugin, ConfigManager configManager) {
        this.plugin = plugin;
        this.configManager = configManager;
    }

    @EventHandler
    public void onDie(PlayerDeathEvent event) {

        event.setKeepInventory(false);
        Player player = event.getEntity();
        ItemStack[] inventory = player.getInventory().getArmorContents();


        player.getInventory().setArmorContents(inventory);
        inventory = player.getInventory().getContents();

        for (int a = 0; a < inventory.length; ++a) {

            ItemStack item = inventory[a];

            if (item != null && !item.getType().equals(Material.AIR)) {
                PlayerDeathDropEvent dropEvent = new PlayerDeathDropEvent(player, item, true);
                Bukkit.getPluginManager().callEvent(dropEvent);
                ItemStack result = dropEvent.getItem();
                if (isDrop(item) && !configManager.protectedDrop.contains(a)) {
                    inventory[a] = null;
                    player.getWorld().dropItemNaturally(player.getLocation(), result);
                } else {
                    inventory[a] = result;
                }
            }

        }

        player.getInventory().setContents(inventory);
        event.getDrops().clear();
        event.setKeepInventory(true);

    }

    public boolean isDrop(ItemStack itemStack) {
        if (configManager.blackList) {
            return configManager.protectedItems.contains(itemStack.getType());
        }
        return !configManager.protectedItems.contains(itemStack.getType());
    }
}
