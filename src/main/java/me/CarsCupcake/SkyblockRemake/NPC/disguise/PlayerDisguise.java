package me.CarsCupcake.SkyblockRemake.NPC.disguise;

import com.mojang.authlib.GameProfile;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.ReflectionUtils;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.*;
import net.minecraft.network.syncher.DataWatcher;
import net.minecraft.network.syncher.DataWatcherRegistry;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityLiving;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftLivingEntity;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import com.mojang.authlib.properties.Property;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class PlayerDisguise {
    public static final HashMap<Integer, PlayerDisguise> nonFake = new HashMap<>();
    public static final HashMap<Integer, PlayerDisguise> fake = new HashMap<>();
    private final HashSet<SkyblockPlayer> shown = new HashSet<>();
    private final LivingEntity entity;
    private final EntityPlayer fakePlayer;
    private final ArmorStand name;
    final PacketPlayOutEntityTeleport dummyTp;
    public PlayerDisguise(LivingEntity entity, Property skin){
        this.entity = entity;
        GameProfile profile = new GameProfile(UUID.randomUUID(), "§§§§");
        name = entity.getWorld().spawn(entity.getEyeLocation(), ArmorStand.class, armorStand -> {
            armorStand.setMarker(true);
            armorStand.setBasePlate(false);
            armorStand.setCustomName(entity.getCustomName());
            armorStand.setCustomNameVisible(true);
            armorStand.addScoreboardTag("remove");
            armorStand.setVisible(false);
        });
        new BukkitRunnable(){
            public void run(){
                if(entity.isDead()){
                    cancel();
                    name.remove();
                    return;
                }
                name.teleport(entity.getEyeLocation());
                name.setCustomName(entity.getCustomName());
            }
        }.runTaskTimer(Main.getMain(), 1, 1);

        profile.getProperties().put("textures", skin);
        fakePlayer = new EntityPlayer(((CraftServer) Bukkit.getServer()).getServer(), ((CraftWorld) entity.getWorld()).getHandle(), profile);
        fakePlayer.setPosition(entity.getLocation().getX(), entity.getLocation().getY(), entity.getLocation().getZ());

        for (Player player : Bukkit.getOnlinePlayers()){
            if(player.getLocation().distance(entity.getLocation()) < player.getClientViewDistance() * 16)
                shown.add(SkyblockPlayer.getSkyblockPlayer(player));
        }
        Location location = new Location(entity.getWorld(), 0, 0, 0);
        dummyTp = new PacketPlayOutEntityTeleport(((CraftLivingEntity) entity).getHandle());
        ReflectionUtils.setField("b", dummyTp, location.getX());
        ReflectionUtils.setField("c", dummyTp, location.getY());
        ReflectionUtils.setField("d", dummyTp, location.getZ());
        ReflectionUtils.setField("e", dummyTp, (byte) ((int) (location.getYaw() * 256.0F / 360.0F)));
        ReflectionUtils.setField("f", dummyTp, (byte) ((int) (location.getPitch() * 256.0F / 360.0F)));
        ReflectionUtils.setField("g", dummyTp, true);

        DataWatcher watcher = fakePlayer.getDataWatcher();
        try {
            byte b = 0x02 | 0x04 | 0x08 | 0x10 | 0x20 | 0x40;
            watcher.set(DataWatcherRegistry.a.a(17), b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, fakePlayer));
        sendPacket(new PacketPlayOutNamedEntitySpawn(fakePlayer));
        sendPacket(new PacketPlayOutEntityMetadata(fakePlayer.getId(), watcher, false));
        sendPacket(dummyTp);
        nonFake.put(entity.getEntityId(), this);
        fake.put(fakePlayer.getId(), this);
    }
    private static final HashMap<Class<? extends Packet<?>>, String> idFieldsOut = new HashMap<>();
    private static final HashMap<Class<? extends Packet<?>>, String> idFieldsIn = new HashMap<>();
    static {
        idFieldsOut.put(PacketPlayOutEntity.PacketPlayOutEntityLook.class, "a");
        idFieldsOut.put(PacketPlayOutEntity.PacketPlayOutRelEntityMove.class, "a");
        idFieldsOut.put(PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook.class, "a");
        idFieldsOut.put(PacketPlayOutPosition.class, "g");
        idFieldsOut.put(PacketPlayOutEntityStatus.class, "a");
        idFieldsOut.put(PacketPlayOutEntityHeadRotation.class, "a");
        idFieldsOut.put(PacketPlayOutAttachEntity.class, "a");
        idFieldsOut.put(PacketPlayOutEntityVelocity.class, "a");
        idFieldsOut.put(PacketPlayOutEntityEffect.class, "d");
        idFieldsOut.put(PacketPlayOutEntitySound.class, "c");
        idFieldsOut.put(PacketPlayOutEntityEquipment.class, "b");
        idFieldsOut.put(PacketPlayOutAnimation.class, "g");
        idFieldsOut.put(PacketPlayOutEntityMetadata.class, "a");

        idFieldsIn.put(PacketPlayInEntityAction.class, "a");
        idFieldsIn.put(PacketPlayInUseEntity.class, "a");
    }
    public void onPacketOut(Packet<?> packet, SkyblockPlayer player){
        if(idFieldsOut.containsKey(packet.getClass()))
            ReflectionUtils.setField(idFieldsOut.get(packet.getClass()), packet, fakePlayer.getId());
        if(packet instanceof PacketPlayOutEntity.PacketPlayOutEntityLook || packet instanceof PacketPlayOutEntity.PacketPlayOutRelEntityMove || packet instanceof PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook){
            //TODO: edit movement!
            check(player);
            sendPacket(dummyTp);
        }
        if(packet instanceof PacketPlayOutEntityStatus status){
            System.out.println(status.b());
        }
    }
    public void onPacketIn(Packet<?> packet){
        if(idFieldsIn.containsKey(packet.getClass()))
            ReflectionUtils.setField(idFieldsIn.get(packet.getClass()), packet, entity.getEntityId());
    }
    public void check(SkyblockPlayer player){
        if(!shown.contains(player)) {
            if (player.getLocation().distance(entity.getLocation()) < player.getClientViewDistance() * 16) {
                shown.add(SkyblockPlayer.getSkyblockPlayer(player));
                player.getHandle().b.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, fakePlayer));
                player.getHandle().b.sendPacket(new PacketPlayOutNamedEntitySpawn(fakePlayer));
            }
        }
        else
            if(player.getLocation().distance(entity.getLocation()) > player.getClientViewDistance() * 16) {
                shown.remove(SkyblockPlayer.getSkyblockPlayer(player));
            }
    }
    private void sendPacket(Packet<?> packet){
        for (SkyblockPlayer player : shown){
            player.getHandle().b.sendPacket(packet);
        }
    }
    public static void packetInManager(Packet<?> packet){
        if(!idFieldsIn.containsKey(packet.getClass())) return;
        int id = (int) ReflectionUtils.getField(ReflectionUtils.findField(packet.getClass(), idFieldsIn.get(packet.getClass())), packet);
        if(fake.containsKey(id))
            fake.get(id).onPacketIn(packet);
    }
    public static void packetOutManager(Packet<?> packet, SkyblockPlayer player){
        if(!idFieldsOut.containsKey(packet.getClass())) return;
        int id = (int) ReflectionUtils.getField(ReflectionUtils.findField(packet.getClass(), idFieldsOut.get(packet.getClass())), packet);
        if(nonFake.containsKey(id))
            nonFake.get(id).onPacketOut(packet, player);
    }
    public void kill(){
        entity.remove();
        sendPacket(new PacketPlayOutEntityStatus(fakePlayer, (byte) 3));
        sendPacket(new PacketPlayOutEntityStatus(fakePlayer, (byte) 60));
        Bukkit.getScheduler().runTaskLater(Main.getMain(), () -> sendPacket(new PacketPlayOutEntityDestroy(fakePlayer.getId())), 20);
        nonFake.remove(entity.getEntityId());
        fake.remove(fakePlayer.getId());
    }
    public void status(byte b){
        sendPacket(new PacketPlayOutEntityStatus(fakePlayer, b));
    }
}
