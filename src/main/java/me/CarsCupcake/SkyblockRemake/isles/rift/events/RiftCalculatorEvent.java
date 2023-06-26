package me.CarsCupcake.SkyblockRemake.isles.rift.events;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.isles.rift.RiftCalculator;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public abstract class RiftCalculatorEvent extends Event implements Cancellable {
    private boolean cancelled;
    @Getter
    private final RiftCalculator calculator;
    @Getter
    private double multiplier = 1;
    public RiftCalculatorEvent(RiftCalculator calculator){
        this.calculator = calculator;
    }

    @Override
    public void setCancelled(boolean b) {
        cancelled = b;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    private static final HandlerList handlers = new HandlerList();
    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }
    public void multiplyMultiplyer(double d){
        multiplier += d;
    }
}
