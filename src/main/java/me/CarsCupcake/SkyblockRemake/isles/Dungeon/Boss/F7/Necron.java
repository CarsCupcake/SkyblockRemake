package me.CarsCupcake.SkyblockRemake.isles.Dungeon.Boss.F7;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.DungeonMob;
import me.CarsCupcake.SkyblockRemake.utils.Laser;
import me.CarsCupcake.SkyblockRemake.utils.Pair;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import me.CarsCupcake.SkyblockRemake.utils.maps.CountMap;
import me.CarsCupcake.SkyblockRemake.utils.runnable.EntityRunnable;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftWither;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import javax.tools.Tool;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Necron extends DungeonMob {
    private Wither entity;
    private BossBar display;
    private EntityRunnable waitingRunnable;
    private BukkitTask currentAbilityRunnable;

    public Necron(int floor, boolean master) {
        super(floor, master);
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public void spawn(Location loc) {
        entity = loc.getWorld().spawn(loc, Wither.class, wither -> {
            wither.setCustomNameVisible(true);
            wither.setRemoveWhenFarAway(false);
            wither.setAI(false);
        });
        ((CraftWither) entity).getHandle().setInvul(0);
        SkyblockEntity.livingEntity.addEntity(entity, this);
        display = Bukkit.createBossBar("Necron", BarColor.RED, BarStyle.SOLID);
        for (Player p : Bukkit.getOnlinePlayers())
            display.addPlayer(p);
        Main.updateentitystats(entity);
        Bukkit.getScheduler().runTaskLater(Main.getMain(), this::movingBeams, 20);
    }

    private void startAbilitiesCountdown() {
        waitingRunnable = new EntityRunnable() {
            @Override
            public void run() {
                int ability = new Random().nextInt(4) + 1;
                switch (ability) {
                    case 1 -> movingBeams();
                    case 2 -> empoveredGreatsword();
                    case 3 -> storm();
                    case 4 -> necronsFrenzy();
                }

            }
        };
        waitingRunnable.runTaskLater(this, 10 * 20);
    }
    static Vector[] abilityLocations = new Vector[]{new Vector(41.5, 70, 76.5), new Vector(67.5, 70, 76.5), new Vector(54.5, 70, 63.5), new Vector(54.5, 70, 89.5)};
     private Location fromVector(Vector v) {
         return new Location(entity.getWorld(), v.getX(), v.getY(), v.getZ());
     }
    CountMap<SkyblockPlayer> hitCooldownMap = new CountMap<>();
    private void movingBeams() {
        entity.teleport(fromVector(Tools.getRandom(abilityLocations)));
        entity.setAI(false);
        Set<Laser> lasers = new HashSet<>();
        for (double d = 62d; d <= 73; d += 0.75) {
            Location center = entity.getLocation().clone();
            center.setY(d);
            lasers.add(new Laser(center, new Location(center.getWorld(), center.getX(), d, center.getZ() + 50d)));
            lasers.add(new Laser(center, new Location(center.getWorld(), center.getX(), d, center.getZ() - 50d)));
            lasers.add(new Laser(center, new Location(center.getWorld(), center.getX() + 50d, d, center.getZ())));
            lasers.add(new Laser(center, new Location(center.getWorld(), center.getX() - 50d, d, center.getZ())));
        }
        currentAbilityRunnable = new EntityRunnable() {
            int i = 0;
            @Override
            public void run() {
                for (Laser laser : lasers) {
                    laser.rotateAroundStartY(1);
                    hitCooldownMap.subtractAll(1);
                    hitCooldownMap.removeByAmount(0);
                    List<Entity> entities = laser.getHitEntitys(entity1 -> {
                        if (!(entity1 instanceof Player p)) return false;
                        return !hitCooldownMap.containsKey(SkyblockPlayer.getSkyblockPlayer(p));
                    });
                    entities.stream().map(entity1 -> SkyblockPlayer.getSkyblockPlayer((Player) entity1)).forEach(player -> {
                        hitCooldownMap.put(player, 20);
                        Calculator calculator = new Calculator();
                        double damage = Main.getPlayerStat(player, Stats.Health) * 0.25;
                        calculator.entityToPlayerDamage(Necron.this, player, new Bundle<>(0, (int) damage));
                        calculator.damagePlayer(player);
                        calculator.showDamageTag(player);
                    });
                    if (i == 200) {
                        this.cancel();
                        startAbilitiesCountdown();
                    }
                    i++;
                }
            }

            @Override
            public synchronized void cancel() throws IllegalStateException {
                super.cancel();
                lasers.forEach(Laser::stop);
            }
        }.runTaskTimer(this, 0, 1);
    }
    private void empoveredGreatsword() {

    }

    private void storm() {

    }

    private void necronsFrenzy() {
        entity.setAI(false);
        currentAbilityRunnable = new EntityRunnable() {
            private int runtime = 0;

            private int switchTarget = 0;
            private Player target = (Player) Bukkit.getOnlinePlayers().toArray()[new Random().nextInt(Bukkit.getOnlinePlayers().size())];

            @Override
            public void run() {
                runtime++;
                switchTarget++;
                if (runtime > 20 * 2.5) {
                    cancel();
                    entity.setAI(true);
                    startAbilitiesCountdown();
                    if (target != null) {
                        entity.setTarget(target);
                    }
                    return;
                }
                if (switchTarget == 5) {
                    target = (Player) Bukkit.getOnlinePlayers().toArray()[new Random().nextInt(Bukkit.getOnlinePlayers().size())];
                    switchTarget = 0;
                }
                if (target != null) {
                    Location lo = entity.getLocation().setDirection(target.getEyeLocation().subtract(0, 0.5, 0).subtract(entity.getEyeLocation()).toVector());
                    entity.teleport(lo);
                    Location temp = entity.getLocation().clone();
                    temp.setYaw(entity.getLocation().getYaw() + 90);
                    Location head1 = entity.getEyeLocation().clone().add(temp.clone().getDirection().multiply(1));
                    Location head2 = entity.getEyeLocation().clone().add(temp.clone().getDirection().multiply(-1));
                    WitherSkull skull = entity.launchProjectile(WitherSkull.class);
                    Vector velocity = skull.getVelocity().clone();
                    head1.getWorld().spawn(head1.add(entity.getLocation().getDirection().clone().multiply(1)), WitherSkull.class, c -> skull.setVelocity(velocity));
                    head2.getWorld().spawn(head2.add(entity.getLocation().getDirection().clone().multiply(1)), WitherSkull.class, c -> skull.setVelocity(velocity));
                    entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_WITHER_SHOOT, 2f, 1.5f);
                }
            }
        }.runTaskTimer(this, 0, 2);
    }

    @Override
    public String getName() {
        return "§cNecron";
    }

    @Override
    public boolean hasNoKB() {
        return true;
    }

    @Override
    protected int healthFromFloor(int floor, boolean master) {
        return master ? 1_400_000_000 : 1_000_000_000;
    }

    @Override
    protected int damageFromFloor(int floor, boolean master) {
        return master ? 250_000 : 50_000;
    }

    @Override
    protected double defenseFromFloor(int floor, boolean master) {
        return master ? 2_100 : 0;
    }

    @Override
    public void updateNameTag() {
        entity.setCustomName("§cNecron");
        entity.getBossBar().removeFlag(BarFlag.DARKEN_SKY);
        entity.getBossBar().removeAll();
        display.setProgress(((double) getHealth()) / ((double) getMaxHealth()));
    }
}
