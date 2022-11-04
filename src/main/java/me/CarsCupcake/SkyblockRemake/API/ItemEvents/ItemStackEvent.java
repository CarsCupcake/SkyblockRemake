package me.CarsCupcake.SkyblockRemake.API.ItemEvents;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ItemStackEvent extends Event {
    private static HandlerList HANDLERS = new HandlerList();
    private final ItemStack item;
    public ItemStackEvent(ItemStack item){
        this.item = item;
    }
    public ItemStack getItem(){
        return item;
    }


    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
    public static HandlerList getHandlerList(){
        return HANDLERS;
    }
}
