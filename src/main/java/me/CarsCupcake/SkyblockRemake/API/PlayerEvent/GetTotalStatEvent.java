package me.CarsCupcake.SkyblockRemake.API.PlayerEvent;

import lombok.Getter;
import lombok.Setter;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import org.bukkit.event.Cancellable;

public class GetTotalStatEvent extends PlayerEvent implements Cancellable {
    private final Stats stat;
    private double value;
    @Getter
    @Setter
    private double multiplier = 1;
    private boolean isCancelled = false;

    public GetTotalStatEvent(SkyblockPlayer player, Stats stats, double value) {
        super(player);
        stat = stats;
        this.value = value;

    }
    public double getValue(){
        return value;
    }
    public void setValue(double i){
        value = i;
    }
    public Stats getStat(){
        return stat;
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
