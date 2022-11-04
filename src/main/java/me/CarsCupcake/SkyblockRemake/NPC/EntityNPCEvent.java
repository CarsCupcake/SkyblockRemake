package me.CarsCupcake.SkyblockRemake.NPC;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class EntityNPCEvent extends Event {
    private static final HandlerList HANLERS = new HandlerList();
    private final EntityNPC npc;
    public EntityNPCEvent(EntityNPC npc){
        this.npc = npc;
    }
    public EntityNPCEvent(@NotNull EntityNPC npc, boolean async){
        super(async);
        this.npc = npc;
    }
    @NotNull
    public EntityNPC getNpc(){
        return npc;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return null;
    }
    public static HandlerList getHandlerList(){
        return HANLERS;
    }
}
