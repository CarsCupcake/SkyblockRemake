package me.CarsCupcake.SkyblockRemake.API.ItemEvents;

import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ManaUpdateEvent extends ItemStackEvent {
    private static final HandlerList HANDLERS = new HandlerList();
    private double mana;
    public ManaUpdateEvent(ItemStack item, double mana) {
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
    public double getMana(){
        return mana;
    }
    public void setMana(double mana){
        this.mana = mana;
    }

}
