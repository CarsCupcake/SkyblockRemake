package me.CarsCupcake.SkyblockRemake.utils;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.game.*;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.decoration.EntityArmorStand;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hologram {
    private final List<EntityArmorStand> holograms = new ArrayList<>();
    private final Set<SkyblockPlayer> players = new HashSet<>();
    private Location location;
    public Hologram(Location l, String... lines){
        location = l;
        double offsset = 0;
        for (String s : lines){
            Main.getDebug().debug("Creating hologram \"" +  s +"\"");
            EntityArmorStand stand = new EntityArmorStand(EntityTypes.c, ((CraftWorld) l.getWorld()).getHandle());
            stand.setMarker(true);
            stand.setBasePlate(false);
            stand.setCustomName(IChatBaseComponent.a(s));
            stand.setCustomNameVisible(true);
            stand.setInvisible(true);
            stand.setLocation(l.getX(), l.getY() + offsset, l.getY(), l.getYaw(), l.getPitch());
            holograms.add(stand);
            offsset -= 0.25;
        }
    }
    public void move(Location l){
        double offsset = 0;
        for (EntityArmorStand armorStand : holograms) {
            armorStand.setLocation(l.getX(), l.getY() + offsset, l.getY(), l.getYaw(), l.getPitch());
            if(l.distance(location) > 5){
                for (SkyblockPlayer player : players){
                    player.getHandle().b.sendPacket(new PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook(armorStand.getId(), (short) ((l.getX() * 32 - location.getX() * 32) * 128), (short) ((l.getY() * 32 - location.getY() * 32) * 128), (short) ((l.getZ() * 32 - location.getZ() * 32) * 128), (byte) ((l.getYaw() % 360.) * 256 / 360), (byte) ((l.getPitch() % 360.) * 256 / 360), true));
                    player.getHandle().b.sendPacket(new PacketPlayOutEntityHeadRotation(armorStand, (byte) ((l.getYaw() % 360.) * 256 / 360)));
                }
            }else {
                for (SkyblockPlayer player : players){
                    PacketPlayOutEntityTeleport packet = new PacketPlayOutEntityTeleport(armorStand);
                    ReflectionUtils.setField("b", packet, l.getX());
                    ReflectionUtils.setField("c", packet, l.getY() + offsset);
                    ReflectionUtils.setField("d", packet, l.getZ());
                    ReflectionUtils.setField("e", packet, (byte) ((int) (l.getYaw() * 256.0F / 360.0F)));
                    ReflectionUtils.setField("f", packet, (byte) ((int) (l.getPitch() * 256.0F / 360.0F)));
                    ReflectionUtils.setField("g", packet, true);
                    player.getHandle().b.sendPacket(packet);
                }
            }
            offsset -= 0.25;
        }
        location = l;
        }
    public void addPlayer(SkyblockPlayer player){
        if(players.contains(player)) return;
        players.add(player);
        double offsset = 0;
        for (EntityArmorStand s : holograms){
            Main.getDebug().debug("Sending hologram \"" +  s.getCustomName().getString() +"\" to " + player.getName());
            player.getHandle().b.sendPacket( new PacketPlayOutSpawnEntityLiving(s));
            player.getHandle().b.sendPacket(new PacketPlayOutEntityMetadata(s.getId(), s.getDataWatcher(), true));
            PacketPlayOutEntityTeleport packet = new PacketPlayOutEntityTeleport(s);
            ReflectionUtils.setField("b", packet, location.getX());
            ReflectionUtils.setField("c", packet, location.getY() + offsset);
            ReflectionUtils.setField("d", packet, location.getZ());
            ReflectionUtils.setField("e", packet, (byte) ((int) (location.getYaw() * 256.0F / 360.0F)));
            ReflectionUtils.setField("f", packet, (byte) ((int) (location.getPitch() * 256.0F / 360.0F)));
            ReflectionUtils.setField("g", packet, true);
            player.getHandle().b.sendPacket(packet);
            offsset -= 0.25;
        }
    }
    public void removePlayer(SkyblockPlayer player){
        for (EntityArmorStand s : holograms){
            PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(s.getId());
            player.getHandle().b.sendPacket(packet);
        }
    }
    public void destroy(){
        for (SkyblockPlayer p : players) removePlayer(p);
        holograms.clear();
    }
}
