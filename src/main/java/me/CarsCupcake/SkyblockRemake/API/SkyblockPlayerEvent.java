package me.CarsCupcake.SkyblockRemake.API;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class SkyblockPlayerEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final SkyblockPlayer player;

    public SkyblockPlayerEvent(@NotNull SkyblockPlayer player){
        this.player = player;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
    public static HandlerList getHandlerList(){
        return HANDLERS;
    }
    public @NotNull SkyblockPlayer getPlayer(){
        return player;
    }
}
