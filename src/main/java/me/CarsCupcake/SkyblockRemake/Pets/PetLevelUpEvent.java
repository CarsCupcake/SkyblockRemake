package me.CarsCupcake.SkyblockRemake.Pets;

import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.PlayerEvent;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.event.HandlerList;

public class PetLevelUpEvent extends PlayerEvent {
    private static HandlerList HANDLERS = new HandlerList();
    private final Pet pet;
    private final int level;

    public PetLevelUpEvent(SkyblockPlayer player, Pet pet, int level) {
        super(player);
        this.level = level;
        this.pet = pet;
    }
    public static HandlerList getHandlerList(){
        return HANDLERS;
    }
    public Pet getPet(){
        return pet;
    }
    public int getLevel(){
        return level;
    }
}
