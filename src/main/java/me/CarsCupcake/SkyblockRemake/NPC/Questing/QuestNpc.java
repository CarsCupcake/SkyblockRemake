package me.CarsCupcake.SkyblockRemake.NPC.Questing;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Assert;
import me.CarsCupcake.SkyblockRemake.utils.Pair;
import me.CarsCupcake.SkyblockRemake.utils.ReflectionUtils;
import me.CarsCupcake.SkyblockRemake.utils.maps.MapList;
import net.minecraft.network.protocol.game.*;
import net.minecraft.network.syncher.DataWatcher;
import net.minecraft.network.syncher.DataWatcherRegistry;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.network.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;

public abstract class QuestNpc {
    public static final MapList<SkyblockPlayer, QuestNpc> shownNpc = new MapList<>();
    @Getter
    private final EntityPlayer npc;
    @Getter
    private final HashMap<SkyblockPlayer, Location> locations = new HashMap<>();
    @Getter
    private final Set<SkyblockPlayer> hidden = new HashSet<>();

    public QuestNpc(Location location, String name, Pair<String> skin) {
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), name);
        gameProfile.getProperties().put("textures", new Property("textures", skin.getFirst(), skin.getLast()));
        npc = new EntityPlayer(((CraftServer) Bukkit.getServer()).getServer(), ((CraftWorld) location.getWorld()).getHandle(), gameProfile);
        npc.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
    }

    public void spawn(SkyblockPlayer player) {
        if (!showToPlayer(player)) return;
        shownNpc.add(player, this);
        new BukkitRunnable() {
            public void run() {
                PlayerConnection connection = player.getHandle().b;
                connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, npc));
                connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
                DataWatcher watcher = npc.getDataWatcher();
                byte b = 0x01 | 0x02 | 0x04 | 0x08 | 0x10 | 0x20 | 0x40;
                watcher.set(DataWatcherRegistry.a.a(17), b);
                connection.sendPacket(new PacketPlayOutEntityMetadata(npc.getId(), watcher, false));
                tp(getLocation(player), player);
            }
        }.runTaskLater(Main.getMain(), 20);
    }

    public abstract Location getLocation(SkyblockPlayer player);

    public abstract void onClick(SkyblockPlayer player);

    public abstract boolean showToPlayer(SkyblockPlayer player);

    private final MapList<SkyblockPlayer, BukkitRunnable> runnables = new MapList<>();

    public void remove(SkyblockPlayer player) {
        player.getHandle().b.sendPacket(new PacketPlayOutEntityDestroy(npc.getId()));
        shownNpc.removeFromList(player, this);
        for (BukkitRunnable r : new ArrayList<>(runnables.get(player)))
            r.cancel();
    }
    public void walk(SkyblockPlayer player, Runnable done, Location... checkpoints) {
        walk(player, done, 0.2, checkpoints);
    }
    public void walk(SkyblockPlayer player, Runnable done, double speed,  Location... checkpoints) {
        walk(player, done, speed, true, checkpoints);
    }
    public void walk(SkyblockPlayer player, Runnable done, boolean onGround,  Location... checkpoints) {
        walk(player, done, 0.2, onGround, checkpoints);
    }

    public void walk(SkyblockPlayer player, Runnable done, double speed, boolean isOnGround, Location... checkpoints) {
        assert checkpoints != null;
        Assert.state(checkpoints.length > 0, "No checkpoints existing");
        Vector first = checkpoints[0].toVector().subtract(locations.get(player).toVector());
        if(isOnGround) first = first.setY(0);
        first = first.normalize().multiply(speed);
        final Vector t = first;

        BukkitRunnable rb = new BukkitRunnable() {
            int checkpoint = 0;
            Vector vector = t;

            @Override
            public void run() {
                Location newL;
                if (locations.get(player).distance(checkpoints[checkpoint]) < speed) {
                    Main.getDebug().debug("New Checkpoint reached : " + checkpoint, false);
                    newL = checkpoints[checkpoint];
                    checkpoint++;
                    if (checkpoint == checkpoints.length) {
                        cancel();
                        done.run();
                    } else {
                        vector = checkpoints[checkpoint].toVector().subtract(newL.toVector());
                        if(isOnGround) vector = vector.setY(0);
                        vector = vector.normalize().multiply(speed);
                    }
                } else newL = locations.get(player).clone().add(vector);
                newL.setDirection(vector);
                if(isOnGround){
                    Block fittingBlock = getNextFittingBlock(newL.getBlock());
                    newL.setY(fittingBlock.getBoundingBox().getMaxY());
                }
                step(newL, player);
            }

            @Override
            public synchronized void cancel() throws IllegalStateException {
                runnables.removeFromList(player, this);
                try {
                    super.cancel();
                } catch (Exception ignored) {
                }
            }
        };
        runnables.add(player, rb);
        rb.runTaskTimer(Main.getMain(), 0, 1);
    }

    private Block getNextFittingBlock(Block b) {
        if (b.isPassable()) {
            while (b.isPassable()) {
                if (b.getY() == -1 || b.getY() == 255) return b;
                b = b.getLocation().subtract(0, 1, 0).getBlock();
            }
            return b;
        } else {
            while (!b.isPassable()) {
                if (b.getY() == -1 || b.getY() == 255) return b;
                b = b.getLocation().add(0, 1, 0).getBlock();
            }
            return b.getLocation().subtract(0, 1, 0).getBlock();
        }
    }

    public void step(Location location, SkyblockPlayer player) {
        Location prev = locations.get(player);
        locations.put(player, location);
        player.getHandle().b.sendPacket(new PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook(npc.getId(), (short) ((location.getX() * 32 - prev.getX() * 32) * 128), (short) ((location.getY() * 32 - prev.getY() * 32) * 128), (short) ((location.getZ() * 32 - prev.getZ() * 32) * 128), (byte) ((location.getYaw() % 360.) * 256 / 360), (byte) ((location.getPitch() % 360.) * 256 / 360), true));
        player.getHandle().b.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) ((location.getYaw() % 360.) * 256 / 360)));
    }

    public void tp(Location location, SkyblockPlayer player) {
        locations.put(player, location);
        PacketPlayOutEntityTeleport packet = new PacketPlayOutEntityTeleport(npc);
        ReflectionUtils.setField("b", packet, location.getX());
        ReflectionUtils.setField("c", packet, location.getY());
        ReflectionUtils.setField("d", packet, location.getZ());
        ReflectionUtils.setField("e", packet, (byte) ((int) (location.getYaw() * 256.0F / 360.0F)));
        ReflectionUtils.setField("f", packet, (byte) ((int) (location.getPitch() * 256.0F / 360.0F)));
        ReflectionUtils.setField("g", packet, true);
        player.getHandle().b.sendPacket(packet);
    }
    public void hide(SkyblockPlayer player){
        player.getHandle().b.sendPacket(new PacketPlayOutEntityDestroy(npc.getId()));
        hidden.add(player);
    }
    public void show(SkyblockPlayer player){
        hidden.remove(player);
        PlayerConnection connection = player.getHandle().b;
        connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, npc));
        connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
        DataWatcher watcher = npc.getDataWatcher();
        byte b = 0x01 | 0x02 | 0x04 | 0x08 | 0x10 | 0x20 | 0x40;
        watcher.set(DataWatcherRegistry.a.a(17), b);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), () ->connection.sendPacket(new PacketPlayOutEntityMetadata(npc.getId(), watcher, true)), 10);
        tp(locations.get(player), player);
    }
}
