package me.CarsCupcake.SkyblockRemake.NPC;


import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.UUID;

import lombok.SneakyThrows;
import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.SkyblockDamagePlayerToEntityExecuteEvent;
import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.utils.ReflectionUtils;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.PacketPlayInEntityAction;
import net.minecraft.network.protocol.game.PacketPlayOutAnimation;
import net.minecraft.network.protocol.game.PacketPlayOutEntity;
import net.minecraft.server.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Entity;


import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import net.minecraft.server.level.EntityPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;


public class DiguestMobsManager implements Listener {
	// entity = entity, string = name
	public static HashMap<Entity, String> entitys = new HashMap<>();
	//Entity -> PlayerDiguise
	public static HashMap<Entity, PlayerDisguise> getDiguested = new HashMap<>();
	private static final HashMap<Integer, DisguisedEntity> diguestedEntitys = new HashMap<>();

	public static void createEntity(Entity entity, Location loc, String name, String texture, String signature) {
		GameProfile profiel = new GameProfile(UUID.randomUUID(), name);
		profiel.getProperties().put("textures", new Property("textures", texture,signature));
		EntityPlayer temp = new EntityPlayer(((CraftServer)Bukkit.getServer()).getServer(), ((CraftWorld)loc.getWorld()).getHandle(), profiel);
		PlayerDisguise mobDisguise = new PlayerDisguise(temp.getBukkitEntity().getPlayer(),temp.getBukkitEntity().getPlayer());
		mobDisguise.setEntity(entity);
		mobDisguise.startDisguise();
		entitys.put(entity, name);
		getDiguested.put(entity, mobDisguise);
		/*diguestedEntitys.put(entity.getEntityId(), new DisguisedEntity((LivingEntity) entity, texture, signature));*/
	}
	static void inputStream(Packet<?> packet){
	}
	private static final Field movePacketId = ReflectionUtils.findField(PacketPlayOutEntity.class, "a");
	@SneakyThrows
	static void outputStream(Packet<?> packet){
		/*if(packet instanceof PacketPlayOutAnimation p) {
			if(diguestedEntitys.containsKey(p.b())) {
				DisguisedEntity entity = diguestedEntitys.get(p.b());
				entity.sendPacket(new PacketPlayOutAnimation(entity.getPlayer(), p.c()));
			}
		}
		if(packet instanceof PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook p){
			if(!movePacketId.canAccess(p))
				ReflectionUtils.makeAccessible(movePacketId);
			int id = movePacketId.getInt(p);
			if(diguestedEntitys.containsKey(id)){
				DisguisedEntity entity = diguestedEntitys.get(id);
				entity.sendPacket(new PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook(id, p.b(), p.c(), p.d(), p.e(), p.f(), p.i()));
			}
		}
		if(packet instanceof PacketPlayOutEntity.PacketPlayOutEntityLook p){
			if(!movePacketId.canAccess(p))
				ReflectionUtils.makeAccessible(movePacketId);
			int id = movePacketId.getInt(p);
			if(diguestedEntitys.containsKey(id)){
				DisguisedEntity entity = diguestedEntitys.get(id);
				entity.sendPacket(new PacketPlayOutEntity.PacketPlayOutEntityLook(id, p.e(), p.f(), p.i()));
			}
		}
		if(packet instanceof PacketPlayOutEntity.PacketPlayOutRelEntityMove p){
			if(!movePacketId.canAccess(p))
				ReflectionUtils.makeAccessible(movePacketId);
			int id = movePacketId.getInt(p);
			if(diguestedEntitys.containsKey(id)){
				DisguisedEntity entity = diguestedEntitys.get(id);
				entity.sendPacket(new PacketPlayOutEntity.PacketPlayOutRelEntityMove(id, p.b(), p.c(), p.d(), p.i()));
			}
		}*/
	}
	@EventHandler
	public void onDmg(SkyblockDamagePlayerToEntityExecuteEvent event){
		if(diguestedEntitys.containsKey(event.getEntity().getEntityId())) {
			if(event.getCalculator().getType() == SkyblockDamageEvent.DamageType.PlayerToEntity)
				diguestedEntitys.get(event.getEntity().getEntityId()).performAction(DisguisedEntity.Action.Hurt);
		}
	}
	@EventHandler
	public void onDie(EntityDeathEvent event){
		if(diguestedEntitys.containsKey(event.getEntity().getEntityId())) {
				diguestedEntitys.get(event.getEntity().getEntityId()).performAction(DisguisedEntity.Action.Die);
		}
	}


	

}
