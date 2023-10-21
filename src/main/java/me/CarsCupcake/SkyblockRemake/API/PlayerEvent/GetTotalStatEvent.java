package me.CarsCupcake.SkyblockRemake.API.PlayerEvent;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import org.bukkit.event.Cancellable;

public class GetTotalStatEvent extends PlayerEvent implements Cancellable {
    @Getter
    private final Stats stat;
    @Getter
    private double value;
    @Getter
    private double multiplier = 1;
    private boolean isCancelled = false;

    public GetTotalStatEvent(SkyblockPlayer player, Stats stats, double value) {
        super(player);
        stat = stats;
        this.value = value;

    }

    public void setValue(double i){
        value = i;
    }
    public void addMultiplier(double d) {
        multiplier *= d;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        isCancelled = b;
    }
}
