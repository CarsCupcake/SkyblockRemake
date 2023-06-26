package me.CarsCupcake.SkyblockRemake.isles.rift.events;

import me.CarsCupcake.SkyblockRemake.isles.rift.RiftCalculator;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class RiftDamageEvent extends RiftCalculatorEvent{

    public RiftDamageEvent(RiftCalculator calculator) {
        super(calculator);
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
}
