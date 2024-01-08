package me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.kuudra.entitys;

import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.kuudra.KuudraBossfight;
import me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.kuudra.KuudraEntity;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.Items.ItemBuilder;
import me.CarsCupcake.SkyblockRemake.utils.SinusMovement;
import me.CarsCupcake.SkyblockRemake.utils.runnable.EntityRunnable;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class BlazingGolem extends KuudraEntity {
    private LivingEntity entity;

    public BlazingGolem(int tier) {
        super(tier);
    }

    @Override
    public int getMaxHealth() {
        return switch (tier) {
            case  2 -> 4_000_000;
            case  3 -> 6_500_000;
            case  4 -> 9_000_000;
            case  5 -> 13_000_000;
            default -> 2_000_000;
        };
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public int getDamage() {
        return switch (tier) {
            case  2 -> 9_000;
            case  3 -> 11_800;
            case  4 -> 13_000;
            case  5 -> 17_000;
            default -> 6_500;
        };
    }

    @Override
    public void spawn(Location loc) {
        entity = loc.getWorld().spawn(loc, Golem.class, zombie -> {
            zombie.setCustomNameVisible(true);
        });
        livingEntity.addEntity(entity, this);

        new EntityRunnable() {
            private boolean b = true;
            @Override
            public void run() {
                int playerSize = Bukkit.getOnlinePlayers().size();
                if (playerSize == 0) return;
                SkyblockPlayer rPlayer = (KuudraBossfight.bossfight == null) ? SkyblockPlayer.getSkyblockPlayer(Bukkit.getOnlinePlayers().toArray(new Player[0])[new Random().nextInt(playerSize)]) :
                        KuudraBossfight.bossfight.getAlive().get(new Random().nextInt(KuudraBossfight.bossfight.getAlive().size()));
                if (b) {
                    SinusMovement movement = new SinusMovement(1, 5, 0);
                    movement.move(entity, 15 ,0, entity.getLocation(), rPlayer.getLocation());
                } else {
                    Vector v = rPlayer.getLocation().toVector().subtract(entity.getLocation().toVector()).add(new Vector(0d, 1d, 0d)).normalize();
                    TNTPrimed tntPrimed = entity.getWorld().spawn(entity.getEyeLocation(), TNTPrimed.class, tntPrimed1 -> {
                        tntPrimed1.setFuseTicks(80);
                        tntPrimed1.setVelocity(v);
                        tntPrimed1.setSource(entity);
                    });
                    new EntityRunnable() {
                        @Override
                        public void run() {
                            tntPrimed.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, tntPrimed.getLocation(), 1);
                            tntPrimed.getNearbyEntities(4, 4, 4).forEach(entity1 -> {
                                if (entity1 instanceof Player p) {
                                    SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(p);
                                    if (KuudraBossfight.bossfight != null && !KuudraBossfight.bossfight.getAlive().contains(player)) return;
                                    Calculator calculator = new Calculator();
                                    calculator.entityToPlayerDamage(BlazingGolem.this, player);
                                    calculator.damage *= 1.5;
                                    calculator.damagePlayer(player);
                                }
                            });
                            tntPrimed.remove();

                        }
                    }.runTaskLater(BlazingGolem.this, 60);
                }
                b = !b;
            }
        }.runTaskTimer(this, 40, 20*5);
    }

    @Override
    public String getName() {
        return "Blazing Golem";
    }

    @Override
    public void updateNameTag() {
        entity.setCustomName(getBaseName(this));
    }

    @Override
    public boolean hasNoKB() {
        return true;
    }

    @Override
    public int getTokens() {
        return 50;
    }
}
