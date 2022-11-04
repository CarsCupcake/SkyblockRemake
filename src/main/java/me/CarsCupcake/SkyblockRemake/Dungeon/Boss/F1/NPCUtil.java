package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F1;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;


import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.game.PacketPlayOutEntityMetadata;
import net.minecraft.network.protocol.game.PacketPlayOutNamedEntitySpawn;
import net.minecraft.network.protocol.game.PacketPlayOutPlayerInfo;
import net.minecraft.network.syncher.DataWatcher;
import net.minecraft.network.syncher.DataWatcherObject;
import net.minecraft.network.syncher.DataWatcherRegistry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.EntityPlayer;

import net.minecraft.server.level.WorldServer;
import net.minecraft.server.network.PlayerConnection;
import net.minecraft.world.entity.EntityPose;


public class NPCUtil {

	 
    public static EntityPlayer  spawnNPC(Location spawnLocation, String texture, String Signature) {

        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "§cBonzo");
        gameProfile.getProperties().put("textures", new Property("textures", texture, Signature));
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
		WorldServer world = ((CraftWorld) spawnLocation.getWorld()).getHandle();
        EntityPlayer entityPlayer = new EntityPlayer(server, world, gameProfile);
        entityPlayer.setPosition(spawnLocation.getX(), spawnLocation.getY(), spawnLocation.getZ());

        DataWatcher watcher = entityPlayer.getDataWatcher();
        try {
            byte b =   0x02 | 0x04 | 0x08 | 0x10 | 0x20 | 0x40;
            watcher.set(DataWatcherRegistry.a.a(17), b);
 
            
        } catch (Exception ignored) {
 ignored.printStackTrace();
        }

		entityPlayer.setPose(EntityPose.c);

        for (Player on : Bukkit.getOnlinePlayers()) {
            PlayerConnection p = ((CraftPlayer) on).getHandle().b;
           
            p.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, entityPlayer));
            p.sendPacket(new PacketPlayOutNamedEntitySpawn(entityPlayer));
            p.sendPacket(new PacketPlayOutEntityMetadata(entityPlayer.getId(), watcher, false));
         
        }
        return entityPlayer;
 
    }
}
