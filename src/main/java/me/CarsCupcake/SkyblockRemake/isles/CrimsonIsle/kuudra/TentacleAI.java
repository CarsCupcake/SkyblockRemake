package me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.kuudra;

import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import me.CarsCupcake.SkyblockRemake.utils.runnable.EntityRunnable;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class TentacleAI {
    private final KuudraTentacle tentacle;
    public TentacleAI(KuudraTentacle tentacle) {
        this.tentacle = tentacle;
    }
    public void launchNewAttack() {
        new EntityRunnable() {
            @Override
            public void run() {
                SkyblockPlayer player = KuudraBossfight.bossfight.getAlive().get(new Random().nextInt(KuudraBossfight.bossfight.getAlive().size()));
                if (tentacle.getAnimations() != TentacleAnimations.Idle) {
                    launchNewAttack();
                    return;
                }
                launchAttackAtTarget(player.getLocation(), true);
            }
        }.runTaskLater(tentacle, 400 + new Random().nextInt(400));
    }
    private static final Material[] warningStages = new Material[]{Material.GREEN_TERRACOTTA, Material.LIME_TERRACOTTA, Material.YELLOW_TERRACOTTA, Material.ORANGE_TERRACOTTA, Material.RED_TERRACOTTA};
    public void launchAttackAtTarget(Location target, boolean sheduleNew) {
        target.setY(78);
        Set<Block> highlights = new HashSet<>();
        Set<Tools.FakeBlock> fakes = new HashSet<>();
        Location l = tentacle.getEntity().getLocation();
        l.setY(78);
        Vector v = target.toVector().subtract(l.toVector()).normalize();
        Vector side1 = v.clone().rotateAroundY(Math.toRadians(90));
        Vector side2 = v.clone().rotateAroundY(Math.toRadians(-90));
        for (int i = 0; i <= 54; i++) {
            l.add(v);
            Block b = l.getBlock();
            if (b.getType() != Material.AIR)
                highlights.add(b);
            b = l.clone().add(side1).getBlock();
            if (b.getType() != Material.AIR)
                highlights.add(b);
            b = l.clone().add(side2).getBlock();
            if (b.getType() != Material.AIR)
                highlights.add(b);
        }
        for (Block b : highlights) fakes.add(Tools.placeFakeBlock(b, Material.GREEN_TERRACOTTA));
        new EntityRunnable() {
            int i = 0;
            @Override
            public void run() {
                i++;
                if (i == warningStages.length) {
                    if (sheduleNew) launchNewAttack();
                    cancel();
                    Location l = tentacle.getEntity().getLocation();
                    l.setY(78);
                    Set<SkyblockPlayer> possibleAllives = new HashSet<>(KuudraBossfight.bossfight.getAlive());
                    for (int i = 0; i <= 54; i++) {
                        l.add(v);
                        for (SkyblockPlayer player : new HashSet<>(possibleAllives)) {
                            if (player.getLocation().distance(l) <= 3d) {
                                possibleAllives.remove(player);
                                Calculator calculator = new Calculator();
                                calculator.entityToPlayerDamage(tentacle, player);
                                calculator.damagePlayer(player);
                            }
                        }
                    }
                    return;
                }
                if (i == warningStages.length - 1) {
                    tentacle.setTarget(target);
                    tentacle.setAnimations(TentacleAnimations.AttackPlayer);
                }
                fakes.forEach(fakeBlock -> fakeBlock.change(warningStages[i]));
            }

            @Override
            public synchronized void cancel() throws IllegalStateException {
                super.cancel();
                fakes.forEach(Tools.FakeBlock::release);
            }
        }.runTaskTimer(tentacle, 20, 20);
    }
}
