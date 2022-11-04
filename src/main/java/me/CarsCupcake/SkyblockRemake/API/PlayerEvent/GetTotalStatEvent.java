package me.CarsCupcake.SkyblockRemake.API.PlayerEvent;

import me.CarsCupcake.SkyblockRemake.API.ItemEvents.ItemStackEvent;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Stats;
import org.bukkit.inventory.ItemStack;

public class GetTotalStatEvent extends PlayerEvent {
    private final Stats stat;
    private double value;

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

}
