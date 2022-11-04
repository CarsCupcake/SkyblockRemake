package me.CarsCupcake.SkyblockRemake.NPC;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;


import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import me.CarsCupcake.SkyblockRemake.Main;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.protocol.EnumProtocolDirection;
import net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy;
import net.minecraft.network.protocol.game.PacketPlayOutEntityHeadRotation;
import net.minecraft.network.protocol.game.PacketPlayOutEntityMetadata;
import net.minecraft.network.protocol.game.PacketPlayOutNamedEntitySpawn;
import net.minecraft.network.protocol.game.PacketPlayOutPlayerInfo;
import net.minecraft.network.syncher.DataWatcher;
import net.minecraft.network.syncher.DataWatcherRegistry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.EntityPlayer;

import net.minecraft.server.level.WorldServer;
import net.minecraft.server.network.PlayerConnection;



public class NPC {
	public static Map<Integer, EntityPlayer> NPCPacket = new HashMap<>();
	private static List<EntityPlayer> NPC = new ArrayList<>();

	
	public static void createNPC(Player player, String skin) {

		GameProfile gameProfile = new GameProfile(UUID.randomUUID(),skin);
		
		
		
		
		String[] name = getSkin(player);
		gameProfile.getProperties().put("textures", new Property("textures", name[0], name[1]));
		
		EntityPlayer npc = new EntityPlayer(((CraftServer)Bukkit.getServer()).getServer(), ((CraftWorld)player.getWorld()).getHandle(), gameProfile);
		npc.setLocation(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch());
		

		
		addNPCPacket(npc);
		
		
	
		
		NPCPacket.put(npc.getId(), npc);
		NPC.add(npc);
		
		int var = 1;
		if (Main.getData().contains("data"))
			var = Main.getData().getConfigurationSection("data").getKeys(false).size() + 1;
		Main.getData().set("data." + var + ".x", (int) player.getLocation().getX());
		Main.getData().set("data." + var + ".y", (int) player.getLocation().getY());
		Main.getData().set("data." + var + ".z", (int) player.getLocation().getZ());
		Main.getData().set("data." + var + ".p", player.getLocation().getPitch());
		Main.getData().set("data." + var + ".ya", player.getLocation().getYaw());
		Main.getData().set("data." + var + ".world", player.getLocation().getWorld().getName());
		Main.getData().set("data." + var + ".name", skin);
		Main.getData().set("data." + var + ".text", name[0]);
		Main.getData().set("data." + var + ".signature", name[1]);
		Main.saveData();
		
	}
	public static void loadNPC(Location location, GameProfile profile) {
		MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
		WorldServer world = ((CraftWorld) location.getWorld()).getHandle();


		NPCUpdate entityPlayer = new NPCUpdate(server, world, profile);
		new PlayerConnection(server, new NetworkManager(EnumProtocolDirection.a), entityPlayer);
		entityPlayer.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getProvidingPlugin(Main.class), () -> addNPCPacket(entityPlayer), 20);
		NPCPacket.put(entityPlayer.getId(), entityPlayer);
		NPC.add(entityPlayer);
//		CustomMobNPCS.test(entityPlayer.getBukkitEntity());
	}
	private static String[] getSkin(Player player) {
		EntityPlayer craftPlayer = ((CraftPlayer) player).getHandle();
		Property textures = (Property) craftPlayer.getProfile().getProperties().get("textures").toArray()[0];
		
			return new String[] {textures.getValue(), textures.getSignature()};
		
			
	}
	
	
	public static void addNPCPacket(EntityPlayer npc) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			PlayerConnection connection = ((CraftPlayer)player).getHandle().b;
			connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, npc));
			connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
			DataWatcher watcher = npc.getDataWatcher();
byte b = 0x01 | 0x02 | 0x04 | 0x08 | 0x10 | 0x20 | 0x40;
watcher.set(DataWatcherRegistry.a.a(17),b);
connection.sendPacket(new PacketPlayOutEntityMetadata(npc.getId(), watcher, false));
			
			
Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getProvidingPlugin(Main.class), new Runnable() {
                
                @Override
                public void run() {
                   connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.e, npc));
                }
            }, 70);


			
			
		}
	}
	public static void removeNPC(Player player, EntityPlayer npc) {
		PlayerConnection connection = ((CraftPlayer)player).getHandle().b;
		connection.sendPacket(new PacketPlayOutEntityDestroy(npc.getId()));
		
		
	}
	
	public static void addJoinPacket(Player player) {
		for (EntityPlayer npc : NPC) {
	        PlayerConnection connection = ((CraftPlayer) player.getPlayer()).getHandle().b;
	        connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, npc));
	        DataWatcher watcher = npc.getDataWatcher();
byte b = 0x01 | 0x02 | 0x04 | 0x08 | 0x10 | 0x20 | 0x40;
watcher.set(DataWatcherRegistry.a.a(17),b);
connection.sendPacket(new PacketPlayOutEntityMetadata(npc.getId(), watcher, false));
	        connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
	        connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.getHeadRotation() * 256 / 360)));
	        connection.sendPacket(new PacketPlayOutEntityMetadata(npc.getId(), watcher, true));
	        
Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getProvidingPlugin(Main.class), new Runnable() {
                
                @Override
                public void run() {
                    connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.e, npc));
                }
            }, 70);

	        
	        
		}
		
	}
	
	public static List<EntityPlayer> getNPCs() {
		return NPC;
	}
	
	

}

