package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6.Entitys;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalHurtByTarget;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.animal.EntityIronGolem;
import net.minecraft.world.entity.animal.EntityTurtle;
import net.minecraft.world.entity.monster.EntityGiantZombie;
import net.minecraft.world.entity.monster.EntityPigZombie;
import net.minecraft.world.entity.monster.EntityZombie;
import net.minecraft.world.entity.npc.EntityVillagerAbstract;
import net.minecraft.world.entity.player.EntityHuman;
import net.minecraft.world.level.World;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;

public class GiantAI extends EntityGiantZombie {
    public GiantAI(Location location) {
        super(EntityTypes.G, ((CraftWorld)location.getWorld()).getHandle());
        setAggressive(true);
        setNoAI(false);
        setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
    }
    protected void initPathfinder() {
        this.bP.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 16.0F));
        this.bP.a(8, new PathfinderGoalRandomLookaround(this));
        this.a();
    }


    public void a() {
        this.bP.a(0, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
        this.bP.a(1, new PathfinderGoalMeleeAttack(this, 1,true));
        this.bP.a(7, new PathfinderGoalRandomStrollLand(this, 1.0));
        this.bQ.a(1, (new PathfinderGoalHurtByTarget(this)).a(EntityPigZombie.class));
        this.bQ.a(2, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));

    }
}
