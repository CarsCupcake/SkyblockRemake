package me.CarsCupcake.SkyblockRemake.Items.minions;

import me.CarsCupcake.SkyblockRemake.Entities.MinionEntity;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

import java.util.*;
import java.util.stream.Collectors;

public class CombatMinion extends AbstractMinion {
    private static final int maxEntityAmount = 6;
    private final AbstractCombatMinionData minion;

    /**
     * This constructor provides the basic values
     *
     * @param level            is the level of the minion
     * @param base             is the base item of the minion
     * @param location         is the location where the minion is placed
     * @param minionIdentifier is a string for the minion. This is a random UUID from the method {@link UUID#randomUUID()} and is also used to load the minion from the file
     * @param placer           is the player who owns the isle
     */
    public CombatMinion(int level, AbstractCombatMinionData base, Location location, String minionIdentifier, SkyblockPlayer placer) {
        super(level, base, location, minionIdentifier, placer);
        minion = base;
    }

    @Override
    void startGetAnimation() {
        List<Entity> missing = location.getWorld().getNearbyEntities(location, 5, 5, 5).stream().filter(entity -> entity instanceof LivingEntity e && SkyblockEntity.livingEntity.exists(e) &&
                SkyblockEntity.livingEntity.getSbEntity(e) instanceof MinionEntity && SkyblockEntity.livingEntity.getSbEntity(e).getEntity().getScoreboardTags().contains("minion:" + CombatMinion.super.minionId)).collect(Collectors.toList());
        if (missing.isEmpty())
            return;

        Collections.shuffle(missing);
        Entity target = missing.get(0);
        missing.remove(target);
        if(!(target instanceof LivingEntity entity))
            return;

        Location lo = stand.getLocation().setDirection(target.getLocation().clone().add(0, entity.getEyeHeight()/2,0).clone().subtract(stand.getLocation()).toVector());
        EulerAngle angle = new EulerAngle(Math.toRadians(lo.getPitch()), 0, 0);
        stand.teleport(lo);
        stand.setHeadPose(angle);
        entity.addScoreboardTag("minionkill");
        SkyblockEntity.killEntity(SkyblockEntity.livingEntity.getSbEntity(entity), null);
        generateLoot();
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
                if (rotation >= 0)
                    rotation = 0;
                stand.setRightArmPose(new EulerAngle(Math.toRadians(rotation), 0, 0));

                i++;
            }
        }.runTaskTimer(Main.getMain(), 10, 2);
    }

    @Override
    void startGenerateAnimation() {
        List<Block> missing = new ArrayList<>(getPlacebleBlocks());
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
            int i = 0;

            @Override
            public void run() {
                if (stand == null || stand.isDead()) {
                    cancel();
                    return;
                }

                if (rotation >= 0) {
                    cancel();
                    SkyblockEntity entity = minion.getEntity().makeNew();
                    entity.spawn(Tools.getAsLocation(target));
                    entity.getEntity().addScoreboardTag("minion:" + CombatMinion.super.minionId);
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
        isMaxGenerated();
    }

    @Override
    boolean isMaxGenerated() {
        return settableSpace() <= 0;
    }

    @Override
    int settableSpace() {
        int i = location.getWorld().getNearbyEntities(location, 5, 5, 5).stream().filter(entity -> entity instanceof LivingEntity e && SkyblockEntity.livingEntity.exists(e) &&
                SkyblockEntity.livingEntity.getSbEntity(e) instanceof MinionEntity && SkyblockEntity.livingEntity.getSbEntity(e).getEntity().getScoreboardTags().contains("minion:" + CombatMinion.super.minionId)).toList().size();
        if (i > maxEntityAmount) return 0;
        else return maxEntityAmount - i;
    }

    List<Block> getPlacebleBlocks() {
        List<Block> blocks = new ArrayList<>();
        for (Block block : Tools.getBlocksBetween(location.clone().add(2, 1, 2).getBlock(), location.clone().subtract(2, 1, 2).getBlock())) {
            if (block.getX() == location.getBlockX() && block.getY() == location.getBlockY() - 1 && block.getZ() == location.getBlockZ())
                continue;
            Block b = getNextIfPossible(block);
            if (b == null)
                continue;
            blocks.add(b);
        }

        Collections.shuffle(blocks);
        return blocks;
    }

    private Block getNextIfPossible(Block b) {
        if (!b.getLocation().add(0, -1, 0).getBlock().isPassable())
            return b;
        if (!b.getLocation().add(0, -2, 0).getBlock().isPassable())
            return b.getLocation().add(0, -1, 0).getBlock();
        return null;
    }
}
