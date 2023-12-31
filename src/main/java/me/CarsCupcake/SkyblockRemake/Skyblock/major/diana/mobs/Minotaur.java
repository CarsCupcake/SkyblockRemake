package me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.mobs;

import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.NPC.disguise.PlayerDisguise;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.MythologicalPerk;
import me.CarsCupcake.SkyblockRemake.utils.Pair;
import me.CarsCupcake.SkyblockRemake.utils.ai.ChaseAndRunAwayPathFinder;
import me.CarsCupcake.SkyblockRemake.utils.runnable.EntityRunnable;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.monster.EntityZombie;
import net.minecraft.world.entity.player.EntityHuman;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.util.Date;

public class Minotaur extends SkyblockEntity implements Listener, ChaseAndRunAwayPathFinder.Status {
    private static final String texture = "ewogICJ0aW1lc3RhbXAiIDogMTU5ODI5MjM3NTc3NCwKICAicHJvZmlsZUlkIiA6ICI3MmNiMDYyMWU1MTA0MDdjOWRlMDA1OTRmNjAxNTIyZCIsCiAgInByb2ZpbGVOYW1lIiA6ICJNb3M5OTAiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2Y5MmQ4MTRiOTk3ZmI3YTY3NmZjOGNkMTVkZjIzYjdkNDg1ZDYxMTEwNjBiM2YxZGUzZGQxYjljZmE4MTExYyIKICAgIH0KICB9Cn0=";
    private static final String signature = "j/tq2Q+2hPDi1PI/kyRAXM5kaQ+Cn93EprcvDSvJzs1jDcUV0WEmmFE0hgMnU7RnAxv6+yJ8M+Hg72WNivHC4awYUvXj/dPEuOFFN4wwx2w+qQrz+JQsJ12BLfhAPK7zt2F1IL1kv4PMrJZ/rwhoSzwxyL7wNT2eyp20e2xQSCnwjVGdqzlXro4A+AhG2/N7Bk/GQmwVxFnazGqwff5C1RbA/CryfnrplfXPZZ1TQLrB5wA6cGxuf7IpGQ4mIjBfuuAa0VDPMmigb3ud50QUT7Z/pGdudxKutddLtFpXqaTLCJdAjjvBsA2bSrBoCXO3Rbfx+Xxo9l06VYA/Zs9zlumHZ6KMxHTehR69MSlle5M85DUPY9jz/tsMeM1FUZkVWS+TmNgkmaEX504lheBx+O25Qwq87g+kvKPTGrh6TAwm6wQ8AoVyrcA+Ka20f/AYSZv/cRtR/p1n5+3n2CFzrdJmAIVf01eBLBlbc3ZZKEibisH55Rl6LLdZ7P0wtpLY46WQUZr3vZMNTchd9QNrqyvV6WwCCkb2ZZE+ibOHbqPuZeAv8p3r26Zxhpt60xb6SdK8fk/s3G+1GE56XrygaPjvplsnH/abAuiUjlkhF0X5Paw6jCHeB6OGGrWxOpe1HA9ll18Qpe7woJbP6Slt+l9C+IiFLU0A2FfHi33WT6M=";
    private int maxHealth = 1;
    private final int damage;
    private final MythologicalPerk perk;
    private int counter = 0;
    Date lastHit = new Date();

    public Minotaur(ItemRarity rarity, MythologicalPerk perk) {
        switch (rarity) {
            case RARE, EPIC -> {
                maxHealth = 625_000;
                damage = 250;
            }
            case LEGENDARY -> {
                maxHealth = 2_500_000;
                damage = 600;
            }
            default -> {
                maxHealth = 60_000;
                damage = 200;
            }
        }
        health = maxHealth;
        this.perk = perk;
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public LivingEntity getEntity() {
        return zombie;
    }

    private Zombie zombie;
    private ArmorStand damageStand;

    @Override
    public void spawn(Location loc) {
        MinotaurZombie nmsZombie = new MinotaurZombie(loc);
        ((CraftWorld) loc.getWorld()).addEntityToWorld(nmsZombie, CreatureSpawnEvent.SpawnReason.CUSTOM);
        zombie = (Zombie) nmsZombie.getBukkitEntity();
        zombie.setCustomNameVisible(true);
        zombie.setAdult();
        zombie.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_AXE));
        damageStand = zombie.getWorld().spawn(loc.add(0, 0.15, 0), ArmorStand.class, s -> {
            s.setGravity(false);
            s.setInvisible(true);
            s.setInvulnerable(true);
            s.setCustomNameVisible(true);
            s.addScoreboardTag("remove");
        });
        updateTag();
        new PlayerDisguise(zombie, texture, signature);
        SkyblockEntity.livingEntity.addEntity(zombie, this);
        new EntityRunnable() {
            @Override
            public void run() {
                damageStand.teleport(zombie.getLocation().add(0, 0.15, 0));
            }
        }.runTaskTimer(this, 0, 1);

        //Thorwing Axe Ability
        new EntityRunnable() {
            @Override
            public void run() {
                new ThrowingAxe(((perk == null) ? SkyblockPlayer.getSkyblockPlayer(Bukkit.getOnlinePlayers().stream().toList().get(0)) : perk.getPlayer()).getLocation().toVector().subtract(getEntity().getLocation().toVector()));
            }
        }.runTaskTimer(this, 30, 30);

        //Bleed Ability
        new EntityRunnable() {
            @Override
            public void run() {
                if (new Date().getTime() - lastHit.getTime() >= 5000) {
                    System.out.println(new Date().getTime() - lastHit.getTime());
                    counter = 0;
                    updateTag();
                }
                if (counter != 0) {
                    Calculator calculator = new Calculator();
                    calculator.entityToPlayerDamage(Minotaur.this, perk.getPlayer(), new Pair<>(counter * 75, 0));
                    calculator.damagePlayer(perk.getPlayer());
                    calculator.showDamageTag(perk.getPlayer());
                }
            }
        }.runTaskTimer(this, 20, 20);
    }

    private void updateTag() {
        damageStand.setCustomName("§cBleed " + counter);
    }

    @Override
    public String getName() {
        return "§2Minotaur";
    }

    @Override
    public boolean hasNoKB() {
        return false;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void kill() {
        super.kill();
        if (perk != null) perk.kill(this);
    }

    @Override
    protected NametagType nametagType() {
        return NametagType.SmallNumber;
    }

    @Override
    public boolean check() {
        return counter < 27;
    }

    public class MinotaurZombie extends EntityZombie {
        public MinotaurZombie(Location location) {
            super(EntityTypes.be, ((CraftWorld) location.getWorld()).getHandle());
            setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        }

        @Override
        protected void initPathfinder() {
            this.bP.a(0, new ChaseAndRunAwayPathFinder<>(Minotaur.this, this, EntityHuman.class, 5, 1, 1));
            this.bQ.a(2, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
        }
    }

    public class ThrowingAxe extends EntityRunnable {
        private static final double SPEED = 0.15;
        private final Vector dir;
        private final ArmorStand stand;
        private final SkyblockPlayer player;

        public ThrowingAxe(Vector v) {
            this.player = ((perk == null) ? SkyblockPlayer.getSkyblockPlayer(Bukkit.getOnlinePlayers().stream().toList().get(0)) : perk.getPlayer());
            dir = v.normalize().multiply(SPEED);
            stand = getEntity().getWorld().spawn(getEntity().getLocation(), ArmorStand.class, armorStand -> {
                armorStand.setInvisible(true);
                armorStand.setCollidable(false);
                armorStand.setBasePlate(false);
                armorStand.addScoreboardTag("remove");
                armorStand.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_AXE));
            });
            runTaskTimer(Minotaur.this, 0, 1);
        }

        int i = 0;
        int lastHit = -10;

        @Override
        public void run() {
            i++;
            if (i == 20 * 5) cancel();
            if (lastHit - i <= -10) {
                if (player.getLocation().distance(stand.getEyeLocation().subtract(0, 0.5, 0)) <= 1.5) {
                    lastHit = i;
                    counter += 2;
                    Calculator calculator = new Calculator();
                    calculator.entityToPlayerDamage(Minotaur.this, perk.getPlayer());
                    calculator.damagePlayer(perk.getPlayer());
                    calculator.showDamageTag(perk.getPlayer());
                }
            }
            stand.teleport(stand.getLocation().add(dir));
            double xPos = stand.getRightArmPose().getX();
            stand.setRightArmPose(new EulerAngle(xPos + 0.6D, 0.0D, 0.0D));
        }

        @Override
        public synchronized void cancel() throws IllegalStateException {
            super.cancel();
            stand.remove();
        }
    }

    @EventHandler
    public void onDamage(SkyblockDamageEvent event) {
        if (event.isCancelled()) return;
        if (event.getType() != SkyblockDamageEvent.DamageType.EntityToPlayer) return;
        if (!(event.getCalculator().getSkyblockEntity() instanceof Minotaur minotaur)) return;
        minotaur.counter++;
        updateTag();
        lastHit = new Date();
    }
}
