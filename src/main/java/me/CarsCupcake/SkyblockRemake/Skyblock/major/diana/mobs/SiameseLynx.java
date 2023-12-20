package me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.mobs;

import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.MythologicalPerk;
import me.CarsCupcake.SkyblockRemake.utils.runnable.EntityRunnable;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.goal.PathfinderGoalOcelotAttack;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.animal.EntityCat;
import net.minecraft.world.entity.player.EntityHuman;
import net.minecraft.world.level.World;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Cat;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class SiameseLynx extends SkyblockEntity {
    private int maxHealth = 1;
    private final int damage;
    private final MythologicalPerk perk;
    private final String name;
    public SiameseLynx(ItemRarity rarity, MythologicalPerk perk, String name) {
        switch (rarity) {
            case RARE, EPIC -> {
                maxHealth = 150_000;
                damage = 625;
            }
            case LEGENDARY -> {
                maxHealth = 800_000;
                damage = 2_125;
            }
            default -> {
                maxHealth = 30_000;
                damage = 250;
            }
        }
        health = maxHealth;
        this.perk = perk;
        this.name = name;
    }
    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }
    LivingEntity entity;

    @Override
    public void spawn(Location loc) {
        Cat cat = new Cat(loc);
        ((CraftWorld) loc.getWorld()).addEntityToWorld(cat, CreatureSpawnEvent.SpawnReason.CUSTOM);
        entity = (LivingEntity) cat.getBukkitEntity();
        new EntityRunnable() {
            @Override
            public void run() {
                entity.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, entity.getEyeLocation(), 1);
            }
        }.runTaskTimer(this, 1, 2);
    }

    @Override
    public String getName() {
        return name;
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
    public static final class Cat extends EntityCat {

        public Cat(Location location) {
            super(EntityTypes.j, ((CraftWorld) location.getWorld()).getHandle());
            setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        }

        @Override
        protected void initPathfinder() {
            this.bP.a(0, new PathfinderGoalOcelotAttack(this));
            this.bQ.a(1, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
        }
    }
}
