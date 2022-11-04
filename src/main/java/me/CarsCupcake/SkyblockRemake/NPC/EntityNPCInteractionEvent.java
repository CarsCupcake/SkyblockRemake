package me.CarsCupcake.SkyblockRemake.NPC;

import me.CarsCupcake.SkyblockRemake.API.SkyblockPlayerEvent;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;
import org.jetbrains.annotations.NotNull;

public class EntityNPCInteractionEvent extends EntityNPCEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean isCancelled = false;
    private final SkyblockPlayer player;
    public EntityNPCInteractionEvent(EntityNPC npc, SkyblockPlayer player) {
        super(npc);
        this.player = player;
    }


    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        isCancelled = b;
    }
    @NotNull
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }
    public SkyblockPlayer getPlayer(){
        return player;
    }
}
