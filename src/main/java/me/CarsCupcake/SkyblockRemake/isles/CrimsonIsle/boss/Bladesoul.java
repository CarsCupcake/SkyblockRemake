package me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.boss;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.Skyblock.projectile.AbstractSbWitherSkull;
import me.CarsCupcake.SkyblockRemake.Skyblock.projectile.SkyblockProjectile;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.Items.ItemBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Pair;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import me.CarsCupcake.SkyblockRemake.utils.runnable.EntityRunnable;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.projectile.EntityWitherSkull;
import net.minecraft.world.level.World;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftWitherSkull;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Bladesoul extends AbstracCrimsonIsleBoss {
    private final Set<WitherGuard> guards = new HashSet<>();
    private LivingEntity entity;
    private HeadBlaze child;
    private ArmorStand nameTag;

    public Bladesoul(Location spawning) {
        super(spawning);
    }

    @Override
    public int getMaxHealth() {
        if (guards != null) return 50_000_000 + (guards.size() * 12_500_000);
        else return 500_000_000;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public int getDamage() {
        return 4000;
    }

    @Override
    public void spawn(Location loc) {
        health = getMaxHealth();
        entity = loc.getWorld().spawn(loc, WitherSkeleton.class, witherSkeleton -> {
            witherSkeleton.getEquipment().setHelmet(Tools.CustomHeadTexture("http://textures.minecraft.net/texture/2f955fa01692f3d9e80adea6913e0e327795e28ccfb8b0a46a0af5197d450f12"));
            witherSkeleton.getEquipment().setItemInMainHand(new ItemBuilder(Material.GOLDEN_AXE).addEnchant(Enchantment.KNOCKBACK, 2).build());
        });
        entity.setRemoveWhenFarAway(false);
        SkyblockEntity.livingEntity.addEntity(entity, this);
        nameTag = loc.getWorld().spawn(entity.getEyeLocation(), ArmorStand.class, armorStand -> {
            armorStand.setMarker(true);
            armorStand.setCustomNameVisible(true);
            armorStand.setBasePlate(false);
            armorStand.setInvisible(true);
            armorStand.addScoreboardTag("remove");
        });
        new EntityRunnable() {
            @Override
            public void run() {
                nameTag.teleport(entity.getEyeLocation().add(0, 0.2, 0));
            }
        }.runTaskTimer(this, 1, 1);
        updateNameTag();
        new EntityRunnable() {
            public void run() {
                child = new HeadBlaze(Bladesoul.this);
                child.spawn(loc);
                entity.addPassenger(child.entity);
            }
        }.runTaskLater(this, 10);

        new EntityRunnable() {
            @Override
            public void run() {
                if (guards.size() < 5 && entity.isOnGround()) spawnGuard();

            }
        }.runTaskTimer(this, 400, 400);

        new EntityRunnable() {
            int cooldown = 0;
            double chance = 0.4;

            @Override
            public void run() {
                if (entity.getNearbyEntities(30, 30, 30).stream().filter(e -> e instanceof Player).findAny().isEmpty())
                    return;
                if (cooldown <= 0) if (new Random().nextDouble() <= chance) {
                    cooldown = 2;
                    for (int i = 0; i < 360; i += 5) {
                        Location l = new Location(entity.getLocation().getWorld(), entity.getLocation().getX(), entity.getLocation().getY() + 0.5, entity.getLocation().getZ(), i, 0);
                        l.add(l.getDirection().normalize());
                        new OneShotSkull(l).setVelocity(l.getDirection().normalize().multiply(0.5));
                    }
                    chance = 0.4;
                    return;
                }
                chance += 0.1;
                if (cooldown != 0) cooldown--;
                for (Entity e : entity.getNearbyEntities(25, 25, 25)) {
                    if (!(e instanceof Player p)) continue;
                    SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(p);
                    entity.launchProjectile(WitherSkull.class, player.getLocation().add(0, 0.5, 0).subtract(entity.getEyeLocation()).toVector().normalize().multiply(0.5));
                }
            }
        }.runTaskTimer(this, 100, 100);

    }

    @Override
    public void updateNameTag() {
        super.updateNameTag();
        nameTag.setCustomName(entity.getCustomName());
    }

    @Override
    public String getName() {
        return "§8§lBladesoul";
    }


    @Override
    public boolean hasNoKB() {
        return true;
    }

    @Override
    protected void getNew(Location l) {
        new Bladesoul(l);
    }

    @Override
    public void kill() {
        super.kill();
        child.kill();
        child.entity.remove();
        for (WitherGuard guard : guards) {
            guard.kill();
            guard.entity.remove();
        }
        nameTag.remove();
    }

    public void spawnGuard() {
        Block b = entity.getLocation().subtract(0, 1, 0).getBlock();
        Tools.FakeBlock fakeBlock = Tools.placeFakeBlock(b, Material.COAL_BLOCK);
        new EntityRunnable() {
            @Override
            public void run() {
                fakeBlock.release();
                if (entity == null || entity.isDead()) return;
                WitherGuard witherGuard = new WitherGuard(Bladesoul.this);
                guards.add(witherGuard);
                witherGuard.spawn(b.getLocation().add(0, 1, 0));
                health += 12_500_000;
            }

            @Override
            public synchronized void cancel() throws IllegalStateException {
                super.cancel();
                if (fakeBlock.isReleased()) return;
                fakeBlock.release();
            }
        }.runTaskLater(this, 200);
    }

    private void guardDeath(WitherGuard guard) {
        guards.remove(guard);
        Calculator c = new Calculator();
        c.damage = 12_500_000;
        c.damageEntity(entity, null);
    }

    private static class WitherGuard extends SkyblockEntity {
        private final Bladesoul base;
        private LivingEntity entity;

        public WitherGuard(Bladesoul base) {
            super();
            this.base = base;
        }

        @Override
        public int getMaxHealth() {
            return 5_000_000;
        }

        @Override
        public LivingEntity getEntity() {
            return entity;
        }

        @Override
        public int getDamage() {
            return 4000;
        }

        @Override
        public void spawn(Location loc) {
            entity = loc.getWorld().spawn(loc, Zombie.class, blaze -> {
                blaze.setBaby();
                blaze.setSilent(true);
                blaze.setCustomNameVisible(true);
                blaze.getEquipment().setHelmet(Tools.CustomHeadTexture("http://textures.minecraft.net/texture/6217869ec205d1727fc4c6055bd868b7886f2c8ad98fa704cb76e11bdd802879"));
                blaze.getEquipment().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).setLeatherColor(Color.BLACK).build());
                blaze.getEquipment().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS).setLeatherColor(Color.BLACK).build());
                blaze.getEquipment().setBoots(new ItemBuilder(Material.LEATHER_BOOTS).setLeatherColor(Color.BLACK).build());
            });
            SkyblockEntity.livingEntity.addEntity(entity, this);
        }

        @Override
        public String getName() {
            return "Wither Guard";
        }

        @Override
        public void updateNameTag() {
            entity.setCustomName(SkyblockEntity.getBaseName(this));
        }

        @Override
        public void kill() {
            super.kill();
            base.guardDeath(this);
        }

        @Override
        public boolean hasNoKB() {
            return true;
        }

        @Override
        public boolean isIgnored() {
            return true;
        }
    }


    private static class HeadBlaze extends SkyblockEntity {
        private final Bladesoul base;
        private LivingEntity entity;

        public HeadBlaze(Bladesoul base) {
            super();
            this.base = base;
        }

        @Override
        public int getMaxHealth() {
            if (base != null) return base.getMaxHealth();
            else return 500_000_00;
        }

        @Override
        public LivingEntity getEntity() {
            return base.entity;
        }

        @Override
        public void spawn(Location loc) {
            entity = loc.getWorld().spawn(loc, Blaze.class, blaze -> {
                blaze.setCustomNameVisible(false);
                blaze.setCustomName("Dinnerbone");
                blaze.setSilent(true);
                blaze.setAI(false);
            });
            SkyblockEntity.livingEntity.addEntity(entity, this);
        }

        @Override
        public String getName() {
            return "Dinnerbone";
        }

        @Override
        public void updateNameTag() {
            entity.setCustomNameVisible(false);
            entity.setCustomName("Dinnerbone");
        }

        @Override
        public void kill() {
            super.kill();

        }

        @Override
        public void damage(double damage, SkyblockPlayer player) {

        }

        @Override
        public boolean hasNoKB() {
            return true;
        }
    }

    public static class OneShotSkull extends AbstractSbWitherSkull {

        public OneShotSkull(Location location) {
            super(location);

        }

        private double damage;

        @Override
        public void onRawHit(LivingEntity hit) {
            if (hit.getType() == EntityType.PLAYER) {
                this.damage = SkyblockPlayer.getSkyblockPlayer((Player) hit).getStat(Stats.Health);
                hit.sendMessage("§7The Wither Skull hit you for §c" + Tools.cleanDouble(damage));
            }

        }

        @Override
        public Pair<Integer> getDamageBundle() {
            return new Pair<>(0, (int) damage);
        }
    }
}
