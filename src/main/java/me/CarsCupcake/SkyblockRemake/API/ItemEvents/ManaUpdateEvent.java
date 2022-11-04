package me.CarsCupcake.SkyblockRemake.API.ItemEvents;

import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ManaUpdateEvent extends ItemStackEvent {
    private static HandlerList HANDLERS = new HandlerList();
    private int mana;
    public ManaUpdateEvent(ItemStack item, int mana) {
        super(item);
        this.mana = mana;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
    public static HandlerList getHandlerList(){
        return HANDLERS;
    }
    public int getMana(){
        return mana;
    }
    public void setMana(int mana){
        this.mana = mana;
    }

}
