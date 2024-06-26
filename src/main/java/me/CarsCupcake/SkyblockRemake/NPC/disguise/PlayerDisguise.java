package me.CarsCupcake.SkyblockRemake.NPC.disguise;

import com.mojang.authlib.GameProfile;
import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Pair;
import me.CarsCupcake.SkyblockRemake.utils.ReflectionUtils;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.*;
import net.minecraft.network.syncher.DataWatcher;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.world.entity.EnumItemSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3D;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import com.mojang.authlib.properties.Property;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Team;
import org.bukkit.util.Vector;

import java.util.*;

public class PlayerDisguise {
    public static final HashMap<Integer, PlayerDisguise> nonFake = new HashMap<>();
    public static final HashMap<Integer, PlayerDisguise> fake = new HashMap<>();
    private final HashSet<SkyblockPlayer> shown = new HashSet<>();
    private final LivingEntity entity;
    @Getter
    private final EntityPlayer fakePlayer;
    private final ArmorStand name;
    private Location l;
    private final BukkitRunnable runnable;
    private boolean customSounds = true;

    public PlayerDisguise(LivingEntity entity, Pair<String> textures) {
        this(entity, textures.getFirst(), textures.getLast());
    }

    public PlayerDisguise(LivingEntity entity, String texture, String value) {
        this(entity, new Property("textures", texture, value));
    }

    public PlayerDisguise(LivingEntity entity, Property skin) {
        entity.setSilent(true);
        l = entity.getLocation();
        this.entity = entity;
        GameProfile profile = new GameProfile(UUID.randomUUID(), "§§§§§9§9§9§9");
        name = entity.getWorld().spawn(entity.getEyeLocation(), ArmorStand.class, armorStand -> {
            armorStand.setMarker(true);
            armorStand.setBasePlate(false);
            armorStand.setCustomName(entity.getCustomName());
            armorStand.setCustomNameVisible(true);
            armorStand.addScoreboardTag("remove");
            armorStand.setVisible(false);
        });
        new BukkitRunnable() {
            double maxH = 0;

            public void run() {
                if (entity.isDead()) {
                    cancel();
                    name.remove();
                    return;
                }
                name.teleport(entity.getEyeLocation());
                name.setCustomName(entity.getCustomName());
                if (maxH != entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue())
                    fakePlayer.getBukkitEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
                maxH = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
                fakePlayer.setHealth((float) entity.getHealth());
            }
        }.runTaskTimer(Main.getMain(), 1, 1);

        profile.getProperties().put("textures", skin);
        fakePlayer = new EntityPlayer(((CraftServer) Bukkit.getServer()).getServer(), ((CraftWorld) entity.getWorld()).getHandle(), profile);
        fakePlayer.setPosition(entity.getLocation().getX(), entity.getLocation().getY(), entity.getLocation().getZ());
        for (Player player : Bukkit.getOnlinePlayers()) {
            check(SkyblockPlayer.getSkyblockPlayer(player));
        }
        /*Location location = new Location(entity.getWorld(), 0, 0, 0);
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
            e.printStackTrace(System.out);
        }
        sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, fakePlayer));
        sendPacket(new PacketPlayOutNamedEntitySpawn(fakePlayer));
        sendPacket(new PacketPlayOutEntityMetadata(fakePlayer.getId(), watcher, false));
        sendPacket(dummyTp);
        sendPacket(new PacketPlayOutEntityDestroy(entity.getEntityId()));*/

        nonFake.put(entity.getEntityId(), this);
        fake.put(fakePlayer.getId(), this);

        runnable = new BukkitRunnable() {
            int i = 0;

            @Override
            public void run() {
                i++;
                if (l.equals(entity.getLocation())) return;
                Location location = entity.getLocation();
                Packet<?> packet;
                if (entity.getLocation().distance(l) > 5 || i >= 20 * 5) {
                    packet = new PacketPlayOutEntityTeleport(fakePlayer);
                    ReflectionUtils.setField("b", packet, location.getX());
                    ReflectionUtils.setField("c", packet, location.getY());
                    ReflectionUtils.setField("d", packet, location.getZ());
                    ReflectionUtils.setField("e", packet, (byte) ((int) (location.getYaw() * 256.0F / 360.0F)));
                    ReflectionUtils.setField("f", packet, (byte) ((int) (location.getPitch() * 256.0F / 360.0F)));
                    ReflectionUtils.setField("g", packet, true);
                } else
                    packet = new PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook(fakePlayer.getId(),
                            (short) ((location.getX() * 32 - l.getX() * 32) * 128),
                            (short) ((location.getY() * 32 - l.getY() * 32) * 128),
                            (short) ((location.getZ() * 32 - l.getZ() * 32) * 128),
                            (byte) ((location.getYaw() % 360.) * 256 / 360),
                            (byte) ((location.getPitch() % 360.) * 256 / 360), true);
                sendPacket(packet);
                l = entity.getLocation();
            }
        };
        runnable.runTaskTimer(Main.getMain(), 1, 1);

    }
    public void toggleCustomSoundEffects() {
        customSounds = !customSounds;
        entity.setSilent(customSounds);
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
        idFieldsOut.put(PacketPlayOutNamedEntitySpawn.class, "a");
        idFieldsOut.put(PacketPlayOutSpawnEntityLiving.class, "a");
        idFieldsOut.put(PacketPlayOutSpawnEntity.class, "c");
        idFieldsIn.put(PacketPlayInEntityAction.class, "a");
        idFieldsIn.put(PacketPlayInUseEntity.class, "a");
    }

    public boolean onPacketOut(Packet<?> packet, SkyblockPlayer player) {
        if (idFieldsOut.containsKey(packet.getClass()))
            ReflectionUtils.setField(idFieldsOut.get(packet.getClass()), packet, fakePlayer.getId());
        if (packet instanceof PacketPlayOutEntity.PacketPlayOutEntityLook || packet instanceof PacketPlayOutEntity.PacketPlayOutRelEntityMove || packet instanceof PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook)
            return false;

        if (packet instanceof PacketPlayOutEntityStatus || packet instanceof PacketPlayOutEntityMetadata) {
            fakePlayer.getBukkitEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
            fakePlayer.setHealth((float) entity.getHealth());
        }
        if(packet instanceof PacketPlayOutEntityMetadata p) {
            List<DataWatcher.Item<?>> items = new ArrayList<>(p.b());
            for (DataWatcher.Item<?> it : p.b())
                if(it.a().a() == 15 && (entity.getHealth() <= 0 || entity.isDead()))
                    items.remove(it);
            ReflectionUtils.setField("b", p, items);
        }
        if(packet instanceof PacketPlayOutEntityStatus p && customSounds && p.b() == 2)
            player.playSound(entity.getLocation(), Sound.ENTITY_PLAYER_HURT, SoundCategory.HOSTILE, 1,1);
        return true;
    }

    public void onPacketIn(Packet<?> packet) {
        if (idFieldsIn.containsKey(packet.getClass()))
            ReflectionUtils.setField(idFieldsIn.get(packet.getClass()), packet, entity.getEntityId());
    }
    @SuppressWarnings("deprecation")
    public void check(SkyblockPlayer player) {
        if (player == null) return;
        if (!shown.contains(player)) {
            if (player.getLocation().distance(entity.getLocation()) < player.getClientViewDistance() * 16) {
                shown.add(SkyblockPlayer.getSkyblockPlayer(player));
                player.getHandle().b.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, fakePlayer));
                player.getHandle().b.sendPacket(new PacketPlayOutNamedEntitySpawn(fakePlayer));
                Location location = entity.getLocation();
                Packet<?> packet = new PacketPlayOutEntityTeleport(fakePlayer);
                ReflectionUtils.setField("b", packet, location.getX());
                ReflectionUtils.setField("c", packet, location.getY());
                ReflectionUtils.setField("d", packet, location.getZ());
                ReflectionUtils.setField("e", packet, (byte) ((int) (location.getYaw() * 256.0F / 360.0F)));
                ReflectionUtils.setField("f", packet, (byte) ((int) (location.getPitch() * 256.0F / 360.0F)));
                ReflectionUtils.setField("g", packet, true);
                player.getHandle().b.sendPacket(packet);
                player.getHandle().b.sendPacket(new PacketPlayOutEntityDestroy(entity.getEntityId()));
                String s = 99 + "n" + fakePlayer.getUniqueID().toString().substring(1, 5);
                Team team = player.getScoreboard().registerNewTeam(s);
                team.setNameTagVisibility(NameTagVisibility.NEVER);
                team.addPlayer(fakePlayer.getBukkitEntity());
                List<com.mojang.datafixers.util.Pair<EnumItemSlot, ItemStack>> items = new ArrayList<>();
                for (EnumItemSlot slot : EnumItemSlot.values())
                    items.add(new com.mojang.datafixers.util.Pair<>(slot, CraftItemStack.asNMSCopy(entity.getEquipment().getItem(transform(slot)))));
                player.getHandle().b.sendPacket(new PacketPlayOutEntityEquipment(fakePlayer.getId(), items));
            }
        } else if (player.getLocation().distance(entity.getLocation()) > player.getClientViewDistance() * 16) {
            shown.remove(SkyblockPlayer.getSkyblockPlayer(player));
        }
    }
    private EquipmentSlot transform(EnumItemSlot slot) {
        return switch (slot) {
            case a -> EquipmentSlot.HAND;
            case b -> EquipmentSlot.OFF_HAND;
            case c -> EquipmentSlot.FEET;
            case d -> EquipmentSlot.LEGS;
            case e -> EquipmentSlot.CHEST;
            case f -> EquipmentSlot.HEAD;
        };
    }

    private void sendPacket(Packet<?> packet) {
        if (shown.isEmpty()) return;
        for (SkyblockPlayer player : shown) {
            if (player == null) continue;
            player.getHandle().b.sendPacket(packet);
        }
    }

    public static void packetInManager(Packet<?> packet) {
        if (!idFieldsIn.containsKey(packet.getClass())) return;
        int id = (int) ReflectionUtils.getField(ReflectionUtils.findField(packet.getClass(), idFieldsIn.get(packet.getClass())), packet);
        if (fake.containsKey(id))
            fake.get(id).onPacketIn(packet);
    }

    public static boolean packetOutManager(Packet<?> packet, SkyblockPlayer player) {
        if ((packet instanceof PacketPlayOutSpawnEntityLiving || packet instanceof PacketPlayOutSpawnEntity || packet instanceof PacketPlayOutNamedEntitySpawn) && nonFake.containsKey((int) ReflectionUtils.getField(ReflectionUtils.findField(packet.getClass(), idFieldsOut.get(packet.getClass())), packet)))
            return false;
        if (!idFieldsOut.containsKey(packet.getClass())) return true;
        int id = (int) ReflectionUtils.getField(ReflectionUtils.findField(packet.getClass(), idFieldsOut.get(packet.getClass())), packet);
        if (nonFake.containsKey(id))
            return nonFake.get(id).onPacketOut(packet, player);
        return true;
    }

    public void kill(SkyblockPlayer player) {
        if (player != null) {
            Vector vec = entity.getLocation().toVector().subtract(player.getLocation().toVector()).normalize().setY(0.3);
            PacketPlayOutEntityVelocity p = new PacketPlayOutEntityVelocity(fakePlayer.getId(), new Vec3D(vec.getX(), vec.getY(), vec.getZ()));
            sendPacket(p);
        }
        Bukkit.getScheduler().runTaskLater(Main.getMain(), () -> {
            entity.setHealth(0);
            fakePlayer.setHealth(0);
            sendPacket(new PacketPlayOutEntityStatus(fakePlayer, (byte) 60));
        }, 1);
        if(customSounds) {
            for (SkyblockPlayer p : shown) p.playSound(entity.getLocation(), Sound.ENTITY_PLAYER_DEATH, SoundCategory.HOSTILE, 1, 1);
        }
        runnable.cancel();
        Bukkit.getScheduler().runTaskLater(Main.getMain(), () -> {
            sendPacket(new PacketPlayOutEntityDestroy(fakePlayer.getId()));
            nonFake.remove(entity.getEntityId());
            fake.remove(fakePlayer.getId());
            entity.remove();
        }, 20);

    }

    public void status(byte b) {
        sendPacket(new PacketPlayOutEntityStatus(fakePlayer, b));
    }
}
