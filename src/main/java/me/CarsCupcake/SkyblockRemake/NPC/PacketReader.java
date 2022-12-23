package me.CarsCupcake.SkyblockRemake.NPC;

import java.lang.reflect.Field;
import java.util.HashMap;


import me.CarsCupcake.SkyblockRemake.utils.SignGUI.SignManager;
import net.minecraft.network.protocol.game.PacketPlayInUpdateSign;
import net.minecraft.network.protocol.game.PacketPlayInUseItem;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;

import io.netty.channel.ChannelHandlerContext;
import me.CarsCupcake.SkyblockRemake.SkyblockRemakeEvents;
import me.CarsCupcake.SkyblockRemake.Main;
import net.minecraft.network.protocol.game.PacketPlayInBlockDig;
import net.minecraft.network.protocol.game.PacketPlayInUseEntity;

public class PacketReader {

	private final Player player;
	private int count = 0;
	private static HashMap<Player, PacketReader> readers= new HashMap<>();
	
	public PacketReader(Player player) {
		this.player = player;

	}
	public static PacketReader getPlayerMethod(Player player) {
		return readers.get(player);
	}
	
	public void inject() {
		
		readers.put(player, this);
		
		CraftPlayer nmsPlayer = (CraftPlayer) player;
		
		Channel channel2 = nmsPlayer.getHandle().b.a.k;


		ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler() {
			@Override
			public void channelRead(ChannelHandlerContext channelHandlerContext,Object packet) throws Exception {
				if(packet instanceof PacketPlayInUpdateSign){
					SignManager.recivePacket(player.getUniqueId(), (PacketPlayInUpdateSign) packet);
				}


				if(packet instanceof PacketPlayInBlockDig) {
					SkyblockRemakeEvents.readBeakBlock((PacketPlayInBlockDig)packet,player);
				}
				if(packet instanceof PacketPlayInUseEntity) {
					PacketReader.getPlayerMethod(player).read((PacketPlayInUseEntity)packet);
				}
					
				super.channelRead(channelHandlerContext, packet);
			}

			
		};
		channel2.pipeline().addBefore("packet_handler", player.getName(),channelDuplexHandler);


	}

	public void uninject(Player player) {
		CraftPlayer nmsPlayer = (CraftPlayer) player;
		Channel channel = nmsPlayer.getHandle().b.a.k;
		if (channel.pipeline().get(player.getName()) != null)
			channel.pipeline().remove(player.getName());
	}
	
	private void read(PacketPlayInUseEntity packetPlayInUseEntity ) {

		count++;
		if(count == 4) {

			count = 0;
			int entityID = (int) getValue(packetPlayInUseEntity, "a");
			//call event
			if(!NPC.NPCPacket.containsKey(entityID))
				return;

			new BukkitRunnable() {

				@Override
				public void run() {
					Bukkit.getPluginManager().callEvent(new RightClickNPC(player, NPC.NPCPacket.get(entityID)));
				}
				
			}.runTask(Main.getPlugin(Main.class));
			
		}
	}
	private Object getValue(Object instance, String name) {
		Object result = null;
		try {
			
			Field field = instance.getClass().getDeclaredField(name);
			field.setAccessible(true);
			
			result = field.get(instance);
			
			field.setAccessible(false);
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
