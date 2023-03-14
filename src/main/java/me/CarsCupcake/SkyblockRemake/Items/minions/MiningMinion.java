package me.CarsCupcake.SkyblockRemake.Items.minions;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.game.PacketPlayOutBlockBreakAnimation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

import java.util.*;

public class MiningMinion extends AbstractMinion {
    private final AbstractMiningMinion minion;

    public MiningMinion(int level, AbstractMiningMinion base, Location location, String minionid, SkyblockPlayer player) {
        super(level, base, location, minionid, player);
        minion = base;
    }

    @Override
    void startGetAnimation() {
        List<Block> missing = new ArrayList<>(getMinableBlocks());
        if (missing.isEmpty())
            return;

        Collections.shuffle(missing);
        Block target = missing.iterator().next();
        missing.remove(target);

        Location lo = stand.getLocation().setDirection(Tools.getAsLocation(target).clone().subtract(stand.getLocation()).toVector());
        EulerAngle angle = new EulerAngle(Math.toRadians(lo.getPitch()), 0, 0);
        stand.teleport(lo);
        stand.setHeadPose(angle);

        new BukkitRunnable() {
            double rotation = -90;
            int through;
            int i = 0;

            @Override
            public void run() {
                if (stand == null || stand.isDead()) {
                    cancel();
                    return;
                }

                if (rotation >= 0) {
                    rotation = -90;
                    through += 1;
                    if (through == 4) {
                        cancel();
                        target.setType(Material.AIR);
                        generateLoot();
                        PacketPlayOutBlockBreakAnimation animation = new PacketPlayOutBlockBreakAnimation(1, new BlockPosition(target.getX(), target.getY(), target.getZ()), 10);
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            ((CraftPlayer) p).getHandle().b.sendPacket(animation);
                        }
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                if (stand == null || stand.isDead()) {
                                    return;
                                }

                                Location l = stand.getLocation().clone();
                                l.setYaw(0);
                                l.setPitch(0);
                                stand.teleport(l);
                                stand.setHeadPose(new EulerAngle(0, 0, 0));
                                stand.setRightArmPose(new EulerAngle(0, 0, 0));
                            }
                        }.runTaskLater(Main.getMain(), 10);
                        return;
                    }
                }

                rotation += 90d / 4d;
                if (rotation >= 0)
                    rotation = 0;
                stand.setRightArmPose(new EulerAngle(Math.toRadians(rotation), 0, 0));
                PacketPlayOutBlockBreakAnimation animation = new PacketPlayOutBlockBreakAnimation(1, new BlockPosition(target.getX(), target.getY(), target.getZ()), getBlockBreakStage(i));
                for (Player p : Bukkit.getOnlinePlayers()) {
                    ((CraftPlayer) p).getHandle().b.sendPacket(animation);
                }

                i++;
            }
        }.runTaskTimer(Main.getMain(), 10, 2);
    }

    @Override
    boolean startGenerateAnimation() {
        List<Block> missing = new ArrayList<>(getPlacebleBlocks());
        if (missing.isEmpty())
            return true;

        Collections.shuffle(missing);
        Block target = missing.iterator().next();
        missing.remove(target);

        Location lo = stand.getLocation().setDirection(Tools.getAsLocation(target).clone().subtract(stand.getLocation()).toVector());
        EulerAngle angle = new EulerAngle(Math.toRadians(lo.getPitch()), 0, 0);
        stand.teleport(lo);
        stand.setHeadPose(angle);

        new BukkitRunnable() {
            double rotation = -90;
            int i = 0;

            @Override
            public void run() {
                if (stand == null || stand.isDead()) {
                    cancel();
                    return;
                }

                if (rotation >= 0) {
                    cancel();
                    target.setType(minion.representiveBlock());
                    generateLoot();

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (stand == null || stand.isDead()) {
                                return;
                            }

                            Location l = stand.getLocation().clone();
                            l.setYaw(0);
                            l.setPitch(0);
                            stand.teleport(l);
                            stand.setHeadPose(new EulerAngle(0, 0, 0));
                            stand.setRightArmPose(new EulerAngle(0, 0, 0));
                        }
                    }.runTaskLater(Main.getMain(), 10);
                    return;
                }

                rotation += 90d / 4d;

                stand.setRightArmPose(new EulerAngle(Math.toRadians(rotation), 0, 0));
                i++;
            }
        }.runTaskTimer(Main.getMain(), 10, 2);

        return missing.isEmpty();
    }

    private int getBlockBreakStage(int currentTick) {
        double result = ((double) currentTick / (double) 16) * 10;
        return (int) result;
    }

    @Override
    boolean isMaxGenerated() {
        return getPlacebleBlocks().isEmpty();
    }

    @Override
    int settableSpace() {
        int blocks = 0;
        for (Block b : getBlocks()) {
            if (b.getType() == Material.AIR)
                continue;
            blocks++;
        }
        return blocks;
    }

    public Set<Block> getBlocks() {
        Set<Block> blocks = new HashSet<>();
        for (Block block : Tools.getBlocksBetween(location.clone().add(2, -1, 2).getBlock(), location.clone().subtract(2, 1, 2).getBlock())) {
            if (block.getX() == location.getBlockX() && block.getY() == location.getBlockY() - 1 && block.getZ() == location.getBlockZ())
                continue;
            blocks.add(block);
        }
        return blocks;
    }

    public Set<Block> getMinableBlocks() {
        Set<Block> blocks = new HashSet<>();
        for (Block block : Tools.getBlocksBetween(location.clone().add(2, -1, 2).getBlock(), location.clone().subtract(2, 1, 2).getBlock())) {
            if (block.getX() == location.getBlockX() && block.getY() == location.getBlockY() - 1 && block.getZ() == location.getBlockZ())
                continue;
            if (block.getType() == minion.representiveBlock())
                blocks.add(block);
        }
        return blocks;
    }

    private Set<Block> getPlacebleBlocks() {
        Set<Block> blocks = new HashSet<>();
        for (Block block : Tools.getBlocksBetween(location.clone().add(2, -1, 2).getBlock(), location.clone().subtract(2, 1, 2).getBlock())) {
            if (block.getX() == location.getBlockX() && block.getY() == location.getBlockY() - 1 && block.getZ() == location.getBlockZ())
                continue;
            if (block.getType() == Material.AIR)
                blocks.add(block);
        }
        return blocks;
    }
}
