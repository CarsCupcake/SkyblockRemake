package me.CarsCupcake.SkyblockRemake.API.PlayerEvent;

import lombok.Getter;
import lombok.Setter;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerFarmEvent extends PlayerEvent{
    private static final HandlerList HANDLERS = new HandlerList();
    @Getter
    private final Block block;
    @Getter
    @Setter
    private double farmingFortune;
    @Getter
    @Setter
    private double farmingWisdom;
    @Getter
    @Setter
    private int drops;

    public PlayerFarmEvent(SkyblockPlayer player, Block block, double farmingFortune, double famrinWisdom, int drops) {
        super(player);
        this.block = block;
        this.farmingFortune = farmingFortune;
        this.farmingWisdom = famrinWisdom;
        this.drops = drops;
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
