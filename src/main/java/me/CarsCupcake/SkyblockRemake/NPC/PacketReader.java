package me.CarsCupcake.SkyblockRemake.NPC;

import java.lang.reflect.Field;
import java.net.SocketAddress;
import java.util.*;


import io.netty.channel.*;
import me.CarsCupcake.SkyblockRemake.Skyblock.ServerType;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockServer;
import me.CarsCupcake.SkyblockRemake.utils.SignGUI.SignManager;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.PacketPlayInFlying;
import net.minecraft.network.protocol.game.PacketPlayInUpdateSign;
import net.minecraft.network.protocol.handshake.PacketHandshakingInSetProtocol;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.CarsCupcake.SkyblockRemake.SkyblockRemakeEvents;
import me.CarsCupcake.SkyblockRemake.Main;
import net.minecraft.network.protocol.game.PacketPlayInBlockDig;
import net.minecraft.network.protocol.game.PacketPlayInUseEntity;

public class PacketReader {

	private final Player player;
	private int count = 0;
	private static final HashMap<Player, PacketReader> readers= new HashMap<>();
	
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
				if(packet instanceof PacketHandshakingInSetProtocol protocol){
					System.out.println(protocol.c + " " + protocol.d);
				}
				if(packet instanceof PacketPlayInUpdateSign){
					SignManager.recivePacket(player.getUniqueId(), (PacketPlayInUpdateSign) packet);
				}


				if(packet instanceof PacketPlayInBlockDig && SkyblockServer.getServer().getType() != ServerType.PrivateIsle) {
					SkyblockRemakeEvents.readBeakBlock((PacketPlayInBlockDig)packet,player);
				}
				if(packet instanceof PacketPlayInUseEntity) {
					PacketReader.getPlayerMethod(player).read((PacketPlayInUseEntity)packet);
				}

				if(packet instanceof Packet<?> p)
					DiguestMobsManager.inputStream(p);


				super.channelRead(channelHandlerContext, packet);
			}

			@Override
			public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {

				super.connect(ctx, remoteAddress, localAddress, promise);
			}

			@Override
			public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
				if(msg instanceof Packet<?> p)
					DiguestMobsManager.outputStream(p);
				super.write(ctx, msg, promise);
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
			int entityID = (int) getValue(packetPlayInUseEntity);
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
	private Object getValue(Object instance) {
		Object result = null;
		try {
			
			Field field = instance.getClass().getDeclaredField("a");
			field.setAccessible(true);
			
			result = field.get(instance);
			
			field.setAccessible(false);
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	private static final ChannelDuplexHandler loginHandler = new ChannelDuplexHandler(){
		@Override
		public void channelRead(ChannelHandlerContext ctx, Object packet) throws Exception {
			if(packet instanceof PacketPlayInFlying) {
				super.channelRead(ctx, packet);
				return;
			}
			System.out.println(packet);
			if(packet instanceof PacketHandshakingInSetProtocol protocol){
				System.out.println(protocol.c + " " + protocol.d);
			}
			super.channelRead(ctx, packet);
		}
	};
	
}
