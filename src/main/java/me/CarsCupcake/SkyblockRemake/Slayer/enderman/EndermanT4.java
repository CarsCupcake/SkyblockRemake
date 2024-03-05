package me.CarsCupcake.SkyblockRemake.Slayer.enderman;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.*;
import me.CarsCupcake.SkyblockRemake.abilities.EntityAbilities;
import me.CarsCupcake.SkyblockRemake.utils.Laser;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Random;
import java.util.stream.Stream;

public class EndermanT4 extends HighEndermanSlayer implements FinalDamageDesider {
    public boolean isInHitsPhase = true;
    public boolean isInRotationPhase = false;
    public int hitphaseHits = 100;
    private int hitsPhase = 0;
    private int rotationPhase = 0;
    private Enderman entity;
    private BukkitRunnable aoe;
    private BukkitRunnable tp;
    private boolean isInHeadsPhase = false;
    private boolean isInBeaconPhase = false;

    public EndermanT4(SkyblockPlayer player) {
        super(player);
    }

    @Override
    public int getMaxHealth() {
        return 210000000;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public int getDamage() {
        return 21000;
    }

    @Override
    public void spawn(@NotNull Location loc) {
        entity = loc.getWorld().spawn(loc, Enderman.class, enderman -> enderman.setRemoveWhenFarAway(false));
        SkyblockEntity.livingEntity.addEntity(entity, this);
        Main.updateentitystats(entity);
        entity.setCustomNameVisible(true);

        aoe = new BukkitRunnable() {
            @Override
            public void run() {
                Calculator calculator = new Calculator();
                calculator.entityToPlayerDamage(EndermanT4.this, owner, new Bundle<>(getDamage() / 2, getTrueDamage()));
                calculator.damagePlayer(owner);
                calculator.showDamageTag(owner);
                owner.damage(0.0000001);
            }
        };
        aoe.runTaskTimer(Main.getMain(), 20, 20);
        tp = new BukkitRunnable() {
            @Override
            public void run() {
                EntityAbilities.voidgloomTeleport(entity, owner);
            }

        };
        tp.runTaskTimer(Main.getMain(), 20 * 5, 20 * 5);
    }

    @Override
    public String getName() {
        return "Voidgloom Seraph";
    }

    @Override
    public HashMap<ItemManager, Integer> getGarantuedDrops(SkyblockPlayer player) {
        int amount = new Random().nextInt(50) + 105;
        HashMap<ItemManager, Integer> map = new HashMap<>();
        map.put(Items.SkyblockItems.get("NULL_SPHERE"), amount);
        return map;
    }

    @Override
    public void updateNameTag() {
        if (isInHitsPhase) {
            entity.setCustomName("§c" + Character.toChars(9760)[0] + " §b" + getName() + " " + getColor() + "§l" + hitphaseHits + "Hits");
        } else
            entity.setCustomName("§c" + Character.toChars(9760)[0] + " §b" + getName() + " §a" + Tools.toShortNumber(health) + "§c");
    }

    @Override
    public void kill() {
        super.kill();
        try {
            tp.cancel();
        } catch (Exception ignored) {
        }
        try {
            aoe.cancel();
        } catch (Exception ignored) {
        }
    }

    private String getColor() {
        double pers = hitphaseHits / 100d;
        if (pers > 0.6) return "§r";
        if (pers > 0.3) return "§d";
        else return "§5";
    }

    @Override
    public void damage(double damage, SkyblockPlayer player) {
        if (!entity.hasAI()) return;

        if (isInHitsPhase) {
            hitphaseHits--;
            if (hitphaseHits <= 0) {
                isInHitsPhase = false;
                hitsPhase++;
            }
        } else {
            health -= damage;
            if (health == 0) return;

            if (toNextHitPhase() && health != 0) {
                hitphaseHits = 100;
                isInHitsPhase = true;
            }
            if (!isInRotationPhase && toNextRotationPhase()) doHeartRotation();

            if (!isInHeadsPhase && getHealth() <= getMaxHealth() * 0.33) {
                isInHeadsPhase = true;
                startNukekubiFixations();
            }

            if (!isInBeaconPhase && getHealth() <= getMaxHealth() / 2) {
                isInBeaconPhase = true;
                startBeaconPhase();
            }

        }
    }

    private boolean toNextHitPhase() {

        if (hitsPhase == 1 && health <= 140000000) {
            return true;
        }
        return hitsPhase == 2 && health <= 70000000;
    }

    private boolean toNextRotationPhase() {

        if (rotationPhase == 0 && health <= 175000000) {
            return true;
        }
        if (rotationPhase == 1 && health <= 105000000) {
            return true;
        }
        return rotationPhase == 2 && health <= 35000000;
    }

    private void doHeartRotation() {
        entity.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, entity.getLocation(), 1);
        rotationPhase++;
        isInRotationPhase = true;
        Location eL = entity.getLocation();
        eL.setPitch(0);
        entity.teleport(eL);
        try {
            tp.cancel();
        } catch (Exception ignored) {
        }

        entity.setAI(false);

        ArmorStand stand = entity.getLocation().getWorld().spawn(entity.getLocation().add(0, -0.5, 0), ArmorStand.class, s -> {
            s.setInvisible(true);
            s.setInvulnerable(true);
            s.setCollidable(false);
        });

        stand.addPassenger(entity);

        Laser l1 = new Laser(entity.getLocation(), entity.getLocation().add(10, 0, 0));
        Vector ve = new Vector(10, 0, 0);
        HashMap<Laser, Vector> items = new HashMap<>();
        items.put(l1, ve.clone());
        l1 = new Laser(entity.getLocation().add(0, 1.45, 0), entity.getLocation().add(10, 1.45, 0));
        items.put(l1, ve.clone());
        l1 = new Laser(entity.getEyeLocation(), entity.getEyeLocation().add(10, 0, 0));
        items.put(l1, ve.clone());
        HashMap<Laser, Vector> clone = new HashMap<>(items);
        for (Laser l : clone.keySet()) {
            Vector v = new Vector(-10, 0, 0);
            Laser nL = new Laser(l.getStart().getEntity().getLocation(), l.getStart().getEntity().getLocation().add(v));
            items.put(nL, v);

            v = new Vector(0, 0, 10);
            nL = new Laser(l.getStart().getEntity().getLocation(), l.getStart().getEntity().getLocation().add(v));
            items.put(nL, v);

            v = new Vector(0, 0, -10);
            nL = new Laser(l.getStart().getEntity().getLocation(), l.getStart().getEntity().getLocation().add(v));
            items.put(nL, v);
        }

        new BukkitRunnable() {
            int i = 0;
            final HashMap<SkyblockPlayer, Integer> hitCooldown = new HashMap<>();

            @Override
            public void run() {
                for (Laser l : items.keySet()) {
                    Vector v = items.get(l);
                    v.rotateAroundY(0.025);
                    l.getEnd().getEntity().teleport(l.getStart().getEntity().getLocation().add(v));
                    double y = l.getStart().getEntity().getLocation().getY();
                    Location k = entity.getLocation();
                    k.setY(y);
                    l.getStart().getEntity().teleport(k);
                    ((Guardian) l.getStart().getEntity()).setTarget(l.getEnd().getEntity());
                    Vector dir = v.clone().normalize().multiply(0.3);
                    Location lol = l.getStart().getEntity().getLocation();
                    if (i > 10) for (double d = 0.3; d < 10; d += 0.3) {
                        Stream<Entity> e = lol.getWorld().getNearbyEntities(lol, 0.3, 0.3, 0.3).stream().filter(c -> c instanceof Player p && SkyblockPlayer.getSkyblockPlayer(p) != null && !hitCooldown.containsKey(SkyblockPlayer.getSkyblockPlayer(p)));
                        e.forEach(b -> {
                            hitCooldown.put(SkyblockPlayer.getSkyblockPlayer((Player) b), 0);
                            Calculator c = new Calculator();
                            SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer((Player) b);
                            c.entityToPlayerDamage(EndermanT4.this, player, new Bundle<>(0, (int) (Main.getPlayerStat(SkyblockPlayer.getSkyblockPlayer((Player) b), Stats.Health) * 0.25)));
                            c.damagePlayer(player);
                            c.showDamageTag(player);
                        });

                        lol = lol.add(dir);
                    }
                }
                for (SkyblockPlayer player : hitCooldown.keySet()) {
                    hitCooldown.replace(player, hitCooldown.get(player) + 1);
                    if (hitCooldown.get(player) > 10) hitCooldown.remove(player);
                }
                i++;
                if (i > 20 * 7) {
                    stand.removePassenger(entity);
                    stand.remove();
                    entity.setAI(true);
                    cancel();
                    tp = new BukkitRunnable() {
                        @Override
                        public void run() {
                            EntityAbilities.voidgloomTeleport(entity, owner);
                        }

                    };
                    tp.runTaskTimer(Main.getMain(), 20 * 5, 20 * 5);
                    isInRotationPhase = false;

                    for (Laser l : items.keySet())
                        l.stop();
                }
            }
        }.runTaskTimer(Main.getMain(), 1, 1);
    }

    @Override
    public boolean hasNoKB() {
        return true;
    }


    @Override
    public double getFinalDamage(SkyblockPlayer player, double damage) {
        if (!entity.hasAI()) return 0;

        return (isInHitsPhase) ? 1 : damage;
    }
}
