package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6.Entitys;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.goal.PathfinderGoalLookAtPlayer;
import net.minecraft.world.entity.ai.goal.PathfinderGoalMeleeAttack;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.monster.EntityZombie;
import net.minecraft.world.entity.player.EntityHuman;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;

public class TerracottaAI extends EntityZombie {
    public TerracottaAI(Location location) {
        super(EntityTypes.be, ((CraftWorld) Bukkit.getWorld("world")).getHandle());
        setBaby(false);
        setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
    }

    @Override
    public void initPathfinder(){
        this.bP.a(0,new PathfinderGoalMeleeAttack(this, 1,true));
        this.bQ.a(1,new PathfinderGoalLookAtPlayer(this, TerracottaAI.class, 16));
    }
}
