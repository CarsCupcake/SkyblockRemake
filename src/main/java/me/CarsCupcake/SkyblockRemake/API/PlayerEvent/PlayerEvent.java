package me.CarsCupcake.SkyblockRemake.API.PlayerEvent;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final SkyblockPlayer player;
    public PlayerEvent(SkyblockPlayer player){
        this.player = player;
    }
    public SkyblockPlayer getPlayer(){
        return player;
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
