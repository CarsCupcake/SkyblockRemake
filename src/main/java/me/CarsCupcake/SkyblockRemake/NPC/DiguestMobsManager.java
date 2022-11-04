package me.CarsCupcake.SkyblockRemake.NPC;


import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Entity;


import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import net.minecraft.server.level.EntityPlayer;


public class DiguestMobsManager {
	// entity = entity, string = name
	public static HashMap<Entity, String> entitys = new HashMap<>();
	//Entity -> PlayerDiguise
	public static HashMap<Entity, PlayerDisguise> getDiguested = new HashMap<>();

	public static void createEntity(Entity entity, Location loc, String name, String texture, String signature) {
		GameProfile profiel = new GameProfile(UUID.randomUUID(), name);
		profiel.getProperties().put("textures", new Property("textures", texture,signature));
		EntityPlayer temp = new EntityPlayer(((CraftServer)Bukkit.getServer()).getServer(), ((CraftWorld)loc.getWorld()).getHandle(), profiel);
		PlayerDisguise mobDisguise = new PlayerDisguise(temp.getBukkitEntity().getPlayer(),temp.getBukkitEntity().getPlayer());
		mobDisguise.setEntity(entity);
		mobDisguise.startDisguise();
		entitys.put(entity, name);
		getDiguested.put(entity, mobDisguise);
	}
	

}
