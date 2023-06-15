package me.CarsCupcake.SkyblockRemake.isles.Dungeon.Boss.F3.guardians;

import me.CarsCupcake.SkyblockRemake.Entities.PathfinderGoalPassiveGoToLocation;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.attributes.GenericAttributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.boss.wither.EntityWither;
import net.minecraft.world.entity.monster.EntityGuardian;
import net.minecraft.world.entity.monster.EntityGuardianElder;
import net.minecraft.world.entity.player.EntityHuman;
import net.minecraft.world.level.World;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;

import java.util.EnumSet;
import java.util.HashMap;

public class RougeGuardianP1 extends SkyblockEntity {
    private LivingEntity entity;
    private final boolean mm;
    public RougeGuardianP1(boolean mm){
        super();
        this.mm = mm;
        health = (mm) ? 20_000_000 : 100_000;
    }
    @Override
    public int getMaxHealth() {
        return (mm) ? 20_000_000 : 100_000;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public void spawn(Location loc) {
        CustomGuardian guardian = new CustomGuardian(loc);
        guardian.getWorld().addEntity(guardian);
        entity = (LivingEntity) guardian.getBukkitEntity();
        livingEntity.addEntity(getEntity(), this);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public HashMap<ItemManager, Integer> getDrops(SkyblockPlayer player) {
        return null;
    }

    @Override
    public boolean hasNoKB() {
        return false;
    }

    @Override
    public int getTrueDamage() {
        return 0;
    }
    private static class CustomGuardian extends EntityGuardian {

        public CustomGuardian(Location location) {
            super(EntityTypes.K, ((CraftWorld) location.getWorld()).getHandle());
            setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
            setAggressive(true);
            setNoAI(false);
        }

        @Override
        protected void initPathfinder() {
            this.bP.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
            this.bP.a(4, new PathfinderGoalGuardianAttack(this));
            this.bQ.a(2, new PathfinderGoalNearestAttackableTarget<>(this, EntityPlayer.class, false, false));
            this.bP.a(9, new PathfinderGoalPassiveGoToLocation(this, new Location(getWorld().getWorld(), -14.5, 74, -13.5)));
            this.bP.a(5, new PathfinderGoalRandomStrollLand(this, 1.0));
        }
    }
    private static class PathfinderGoalGuardianAttack extends PathfinderGoal {
        private final EntityGuardian a;
        private int b;
        private final boolean c;

        public PathfinderGoalGuardianAttack(EntityGuardian entityguardian) {
            this.a = entityguardian;
            this.c = entityguardian instanceof EntityGuardianElder;
            this.a(EnumSet.of(Type.a, Type.b));
        }

        public boolean a() {
            EntityLiving entityliving = this.a.getGoalTarget();
            return entityliving != null && entityliving.isAlive();
        }

        public boolean b() {
            return super.b() && (this.c || this.a.f(this.a.getGoalTarget()) > 9.0);
        }

        public void c() {
            this.b = -10;
            this.a.getNavigation().o();
            this.a.getControllerLook().a(this.a.getGoalTarget(), 90.0F, 90.0F);
            this.a.af = true;
        }

        public void d() {
            this.a.a(0);
            this.a.setGoalTarget(null);
            this.a.d.h();
        }

        public void e() {
            EntityLiving entityliving = this.a.getGoalTarget();
            this.a.getNavigation().o();
            this.a.getControllerLook().a(entityliving, 90.0F, 90.0F);
            if (!this.a.hasLineOfSight(entityliving)) {
                this.a.setGoalTarget(null);
            } else {
                ++this.b;
                if (this.b == 0) {
                    this.a.a(this.a.getGoalTarget().getId());
                    if (!this.a.isSilent()) {
                        this.a.t.broadcastEntityEffect(this.a, (byte)21);
                    }
                } else if (this.b >= this.a.p()) {
                    float f = 1.0F;
                    if (this.a.t.getDifficulty() == EnumDifficulty.d) {
                        f += 2.0F;
                    }

                    if (this.c) {
                        f += 2.0F;
                    }

                    entityliving.damageEntity(DamageSource.c(this.a, this.a), f);
                    entityliving.damageEntity(DamageSource.mobAttack(this.a), (float)this.a.b(GenericAttributes.f));
                    this.a.setGoalTarget(null);
                }

                super.e();
            }

        }
    }

}
