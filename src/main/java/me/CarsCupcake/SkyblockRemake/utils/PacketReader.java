package me.CarsCupcake.SkyblockRemake.utils;

import java.util.*;


import io.netty.channel.*;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.NPC.NPC;
import me.CarsCupcake.SkyblockRemake.NPC.Questing.QuestNpc;
import me.CarsCupcake.SkyblockRemake.NPC.RightClickNPC;
import me.CarsCupcake.SkyblockRemake.NPC.disguise.PlayerDisguise;
import me.CarsCupcake.SkyblockRemake.Settings.ServerSettings;
import me.CarsCupcake.SkyblockRemake.Skyblock.ServerType;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockServer;
import me.CarsCupcake.SkyblockRemake.utils.SignGUI.SignManager;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.*;
import net.minecraft.network.protocol.handshake.PacketHandshakingInSetProtocol;
import net.minecraft.world.entity.EntityLiving;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_17_R1.block.data.CraftBlockData;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import me.CarsCupcake.SkyblockRemake.SkyblockRemakeEvents;
import me.CarsCupcake.SkyblockRemake.Main;

public class PacketReader {

    private final SkyblockPlayer player;
    private int count = 0;
    private static final HashMap<Player, PacketReader> readers = new HashMap<>();

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
            public void channelRead(ChannelHandlerContext channelHandlerContext, Object packet) {
                if (ServerSettings.isLag()) {
                    Bukkit.getScheduler().runTaskLater(Main.getMain(), () -> cR_o(channelHandlerContext, packet), 2);
                }else cR_o(channelHandlerContext, packet);
            }

            public void cR_o(ChannelHandlerContext channelHandlerContext, Object packet) {
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
                    if (packet instanceof PacketPlayInFlying pkt && ServerSettings.isMovementLag()) {
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
                    if (ServerSettings.isPacketLog() && ServerSettings.getPacketLogFilter().isIn() && searchCheck(packet.getClass().getSimpleName())) {
                        System.out.println(player.getName() + " IN: " + packet.getClass().getSimpleName());
                        if (ServerSettings.getPacketLogFilter().isDetailed())
                            ServerSettings.getPacketLogFilter().printAsDetailed((Packet<?>) packet);
                    }
                } catch (Exception e) {
                    System.out.println("Error while reading packet: " + packet.getClass().getSimpleName());
                    e.printStackTrace(System.err);
                    player.sendMessage("§cCould not read packet " + packet.getClass().getSimpleName());
                    return;
                }
                try {
                    super.channelRead(channelHandlerContext, packet);
                } catch (Exception e) {
                    e.printStackTrace(System.out);
                }
            }

            @Override
            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
                if (ServerSettings.isLag()) {
                    Bukkit.getScheduler().runTaskLater(Main.getMain(), () -> w(ctx, msg, promise), 2);
                }else w(ctx, msg, promise);
            }
            public void w(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
                try {
                    if (!PlayerDisguise.packetOutManager((Packet<?>) msg, player)) return;
                    if (ServerSettings.isPacketLog() && ServerSettings.getPacketLogFilter().isOut() && searchCheck(msg.getClass().getSimpleName())) {
                        System.out.println(player.getName() + " OUT: " + msg.getClass().getSimpleName());
                        if (ServerSettings.getPacketLogFilter().isDetailed())
                            ServerSettings.getPacketLogFilter().printAsDetailed((Packet<?>) msg);
                    }
                    if (msg instanceof PacketPlayOutBlockChange p) {
                        if (Tools.FakeBlock.getBlocks().containsKey(Tools.asBukkitBlock(p.c(), player.getWorld()))) {
                            ReflectionUtils.setField("b", p, ((CraftBlockData) Tools.FakeBlock.getBlocks().get(Tools.asBukkitBlock(p.c(), player.getWorld())).get(0).getMaterial().createBlockData()).getState());
                        }
                    }
                    super.write(ctx, msg, promise);
                } catch (Exception e) {
                    System.out.println("Error while writing packet: " + msg.getClass().getSimpleName());
                    e.printStackTrace(System.out);
                    player.sendMessage("§cCould not write packet " + msg.getClass().getSimpleName());
                    return;
                }
            }
        };
        channel.pipeline().addBefore("packet_handler", player.getName(), channelDuplexHandler);


    }

    private boolean searchCheck(String s) {
        if (ServerSettings.getPacketLogFilter().getSearch() == null) return true;
        return s.contains(ServerSettings.getPacketLogFilter().getSearch());
    }

    public void uninject(Player player) {
        CraftPlayer nmsPlayer = (CraftPlayer) player;
        Channel channel = nmsPlayer.getHandle().b.a.k;
        if (channel.pipeline().get(player.getName()) != null)
            channel.pipeline().remove(player.getName());
    }

    private void read(PacketPlayInUseEntity packetPlayInUseEntity) {
        count++;
        if (count == 4) {
            count = 0;
            int entityID = (int) ReflectionUtils.getField(ReflectionUtils.findField(packetPlayInUseEntity.getClass(), "a"), packetPlayInUseEntity);
            //call event
            if (NPC.NPCPacket.containsKey(entityID)) {
                new BukkitRunnable() {

                    @Override
                    public void run() {
                        Bukkit.getPluginManager().callEvent(new RightClickNPC(player, NPC.NPCPacket.get(entityID)));
                    }

                }.runTask(Main.getPlugin(Main.class));
            } else {
                for (QuestNpc npc : QuestNpc.shownNpc.get(player))
                    if (npc.getNpc().getId() == entityID) {
                        npc.onClick(player);
                        break;
                    }
            }
        }
    }

}
