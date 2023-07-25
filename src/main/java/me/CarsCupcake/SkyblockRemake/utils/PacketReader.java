package me.CarsCupcake.SkyblockRemake.utils;

import java.lang.reflect.Field;
import java.util.*;


import io.netty.channel.*;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.NPC.NPC;
import me.CarsCupcake.SkyblockRemake.NPC.Questing.QuestNpc;
import me.CarsCupcake.SkyblockRemake.NPC.RightClickNPC;
import me.CarsCupcake.SkyblockRemake.NPC.disguise.PlayerDisguise;
import me.CarsCupcake.SkyblockRemake.Settings.InfoManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.ServerType;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockServer;
import me.CarsCupcake.SkyblockRemake.utils.SignGUI.SignManager;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.*;
import net.minecraft.network.protocol.handshake.PacketHandshakingInSetProtocol;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_17_R1.block.data.CraftBlockData;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import me.CarsCupcake.SkyblockRemake.SkyblockRemakeEvents;
import me.CarsCupcake.SkyblockRemake.Main;

public class PacketReader {

	private final SkyblockPlayer player;
	private int count = 0;
	private static final HashMap<Player, PacketReader> readers= new HashMap<>();
	
	public PacketReader(Player player) {
		this.player = SkyblockPlayer.getSkyblockPlayer(player);

	}
	public static PacketReader getPlayerMethod(Player player) {
		return readers.get(player);
	}
	
	public void inject() {
		
		readers.put(player, this);

		Channel channel = player.getHandle().b.a.k;


		ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler() {
			@Override
			public void channelRead(ChannelHandlerContext channelHandlerContext,Object packet) {
				try {
					if (packet instanceof PacketHandshakingInSetProtocol protocol) {
						System.out.println(protocol.c + " " + protocol.d);
					}
					if (packet instanceof PacketPlayInUpdateSign) {
						SignManager.recivePacket(player.getUniqueId(), (PacketPlayInUpdateSign) packet);
					}


					if (packet instanceof PacketPlayInBlockDig && SkyblockServer.getServer().type() != ServerType.PrivateIsle) {
						SkyblockRemakeEvents.readBeakBlock((PacketPlayInBlockDig) packet, player);
					}
					if (packet instanceof PacketPlayInUseEntity) {
						PacketReader.getPlayerMethod(player).read((PacketPlayInUseEntity) packet);
					}
					if (packet instanceof PacketPlayInBlockDig) {
						if (player.getItemInHand() != null && player.getItemInHand().hasItemMeta() && ItemHandler.hasPDC("id", player.getItemInHand(), PersistentDataType.STRING) &&
								ItemHandler.getPDC("id", player.getItemInHand(), PersistentDataType.STRING).equals("GHOST_BLOCKS_PICK"))
							return;
					}
					if (packet instanceof PacketPlayInFlying pkt && InfoManager.isMovementLag()) {
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), () -> {
							try {
								super.channelRead(channelHandlerContext, pkt);
							} catch (Exception e) {
								throw new RuntimeException(e);
							}
						}, 4);
						return;
					}
					PlayerDisguise.packetInManager((Packet<?>) packet);
					if (InfoManager.isPacketLog() && InfoManager.getPacketLogFilter().isIn() && searchCheck(packet.getClass().getSimpleName())) {
						System.out.println(player.getName() + " IN: " + packet.getClass().getSimpleName());
						if (InfoManager.getPacketLogFilter().isDetailed())
							InfoManager.getPacketLogFilter().printAsDetailed((Packet<?>) packet);
					}
				}catch (Exception e){
					System.out.println("Error while reading packet: " + packet.getClass().getSimpleName());
					e.printStackTrace();
					player.sendMessage("§cCould not read packet " + packet.getClass().getSimpleName());
					return;
				}
				try {
					super.channelRead(channelHandlerContext, packet);
				}catch (Exception e){
					e.printStackTrace();
				}
			}

			@Override
			public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
				try{
					if (!PlayerDisguise.packetOutManager((Packet<?>) msg)) return;
					if (msg instanceof PacketPlayOutEntityStatus p) {
						System.out.println("STATUS: " + p.b());
					}
					if (InfoManager.isPacketLog() && InfoManager.getPacketLogFilter().isOut() && searchCheck(msg.getClass().getSimpleName())) {
						System.out.println(player.getName() + " OUT: " + msg.getClass().getSimpleName());
						if (InfoManager.getPacketLogFilter().isDetailed())
							InfoManager.getPacketLogFilter().printAsDetailed((Packet<?>) msg);
					}
					if (msg instanceof PacketPlayOutBlockChange p) {
						if (Tools.FakeBlock.getBlocks().containsKey(Tools.asBukkitBlock(p.c(), player.getWorld()))) {
							ReflectionUtils.setField("b", p, ((CraftBlockData) Tools.FakeBlock.getBlocks().get(Tools.asBukkitBlock(p.c(), player.getWorld())).get(0).getMaterial().createBlockData()).getState());
						}
					}
				}catch (Exception e){
					System.out.println("Error while writing packet: " + msg.getClass().getSimpleName());
					e.printStackTrace();
					player.sendMessage("§cCould not write packet " + msg.getClass().getSimpleName());
					return;
				}
				super.write(ctx, msg, promise);
			}
		};
		channel.pipeline().addBefore("packet_handler", player.getName(),channelDuplexHandler);


	}
	private boolean searchCheck(String s){
		if(InfoManager.getPacketLogFilter().getSearch() == null) return true;
		return s.contains(InfoManager.getPacketLogFilter().getSearch());
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
			if(NPC.NPCPacket.containsKey(entityID)) {
				new BukkitRunnable() {

					@Override
					public void run() {
						Bukkit.getPluginManager().callEvent(new RightClickNPC(player, NPC.NPCPacket.get(entityID)));
					}

				}.runTask(Main.getPlugin(Main.class));
			}else {
				for (QuestNpc npc : QuestNpc.shownNpc.get(player))
					if(npc.getNpc().getId() == entityID) {
						npc.onClick(player);
						break;
					}
			}
			
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
