package me.CarsCupcake.SkyblockRemake.API;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import net.minecraft.network.protocol.Packet;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PacketRecieveEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Packet<?> packet;
    private final SkyblockPlayer player;
    @NotNull
    @Override
    public HandlerList getHandlers() {
        return null;
    }
    public PacketRecieveEvent(Packet<?> packet, SkyblockPlayer player){
        this.packet = packet;
        this.player = player;
    }
    public Packet<?> getPacket(){
        return packet;
    }
    public SkyblockPlayer getPlayer(){
        return player;
    }
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
