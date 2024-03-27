package me.CarsCupcake.SkyblockRemake.API.PlayerEvent;

import lombok.Getter;
import lombok.Setter;
import me.CarsCupcake.SkyblockRemake.API.SkyblockPlayerEvent;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;


@Getter
public class PlayerManaRegenEvent extends SkyblockPlayerEvent implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private final double totalMana;
    private final double currentMana;
    @Setter
    private double regenrateAmount;
    @Setter
    private double multiplier = 1;
    private boolean cancellable = false;
    public PlayerManaRegenEvent(@NotNull SkyblockPlayer player, double totalMana, double currentMana, double regenrateAmount) {
        super(player);
        this.totalMana = totalMana;
        this.currentMana = currentMana;
        this.regenrateAmount = regenrateAmount;
    }

    @Override
    public boolean isCancelled() {
        return cancellable;
    }

    @Override
    public void setCancelled(boolean b) {
        cancellable = b;
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
