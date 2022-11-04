package me.CarsCupcake.SkyblockRemake.NPC;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;


import net.minecraft.network.protocol.game.PacketPlayOutEntityMetadata;
import net.minecraft.network.protocol.game.PacketPlayOutNamedEntitySpawn;
import net.minecraft.network.protocol.game.PacketPlayOutPlayerInfo;
import net.minecraft.network.syncher.DataWatcher;
import net.minecraft.network.syncher.DataWatcherRegistry;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.network.PlayerConnection;



public class NPCEntity {
	
	public static Map<Integer, EntityPlayer> corpseEntitys = new HashMap<>();

	public static void execute(Player player,String name) {
		EntityPlayer craftPlayer = ((CraftPlayer) player).getHandle();
		
		//textures
		Property textures = (Property) craftPlayer.getProfile().getProperties().get("textures").toArray()[0];
		GameProfile gameProfile = new GameProfile(UUID.randomUUID(), name);
		gameProfile.getProperties().put("textures", new Property("textures", textures.getValue(), textures.getSignature()));
		
		//create NPC
		EntityPlayer corpse = new EntityPlayer(
				((CraftServer)Bukkit.getServer()).getServer(),
				((CraftWorld)player.getWorld()).getHandle(),
				gameProfile);
		corpse.setPosition(player.getLocation().getX(),player.getLocation().getY(),player.getLocation().getZ());
		
		//skin overlays
		DataWatcher watcher = corpse.getDataWatcher();
		byte b = 0x01 | 0x02 | 0x04 | 0x08 | 0x10 | 0x20 | 0x40;
		watcher.set(DataWatcherRegistry.a.a(17),b);
		
		//send packets
		for (Player on: Bukkit.getOnlinePlayers()) {
			PlayerConnection connection = ((CraftPlayer)on).getHandle().b;
			connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a));
			connection.sendPacket(new PacketPlayOutNamedEntitySpawn(corpse));
			connection.sendPacket(new PacketPlayOutEntityMetadata(corpse.getId(),watcher,false));

		}
		corpseEntitys.put(corpse.getId(), corpse);
	}
}
