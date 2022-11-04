package me.CarsCupcake.SkyblockRemake.API;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerHealthChangeEvent extends Event implements Cancellable {
    private boolean isCancelled = false;
    private static final HandlerList HANDLERS = new HandlerList();
    private final SkyblockPlayer player;
    private int helthChangeAmount;
    private final HealthChangeReason reason;


    public PlayerHealthChangeEvent(SkyblockPlayer player, int health, HealthChangeReason reason){
        this.player = player;
        helthChangeAmount = health;
        this.reason = reason;
    }

    public int getHelthChangeAmount() {
        return helthChangeAmount;
    }
    public void setHelthChangeAmount(int helthChangeAmount) {
        this.helthChangeAmount = helthChangeAmount;
    }
    public HealthChangeReason getReason() {
        return reason;
    }
    public SkyblockPlayer getPlayer() {
        return player;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.isCancelled = b;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
