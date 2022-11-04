package me.CarsCupcake.SkyblockRemake.API.ItemEvents;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class ItemManagerEvent extends Event {
    private static HandlerList HANDLERS = new HandlerList();
    private final ItemManager manager;
    public ItemManagerEvent(ItemManager manager){
        this.manager = manager;
    }
    public ItemManager getItem(){
        return manager;
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
