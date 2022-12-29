package me.CarsCupcake.SkyblockRemake.API.ItemEvents;

import lombok.Getter;
import lombok.Setter;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Stats;
import org.bukkit.inventory.ItemStack;

public class GetStatFromItemEvent extends ItemStackEvent{
    private final Stats stat;
    private double value;
    private final SkyblockPlayer player;
    @Getter@Setter
    private double multiplier = 1;
    public GetStatFromItemEvent(ItemStack item, Stats stats, double value, SkyblockPlayer player) {
        super(item);
        stat = stats;
        this.value = value;
        this.player = player;

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
    public SkyblockPlayer getPlayer(){
        return player;
    }

}
