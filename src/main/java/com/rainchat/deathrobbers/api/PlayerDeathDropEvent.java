package com.rainchat.deathrobbers.api;


import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class PlayerDeathDropEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Player p;
    private final boolean cancelled;
    private final boolean armor;
    private boolean isCancelled = false;
    private ItemStack item;

    public PlayerDeathDropEvent(Player p, ItemStack item, boolean isArmor) {
        this.p = p;
        this.item = item;
        this.cancelled = false;
        this.armor = isArmor;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public boolean isCancelled() {
        return this.isCancelled;
    }

    public void setCancelled(boolean b) {
        this.isCancelled = b;
    }

    public boolean isArmor() {
        return this.armor;
    }

    public ItemStack getItem() {
        return this.item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public Player getPlayer() {
        return this.p;
    }

}