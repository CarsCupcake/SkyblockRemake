package me.CarsCupcake.SkyblockRemake.NPC;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import net.minecraft.world.entity.ai.goal.PathfinderGoal;
import net.minecraft.world.entity.ai.goal.PathfinderGoalSelector;
import net.minecraft.world.entity.monster.EntityZombie;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public abstract class LivingNPC extends SkyblockEntity {
    private static final Set<LivingNPC> hashSet = new HashSet<>();
    private static BukkitRunnable runner;
    public PathfinderGoalSelector bP;
    public LivingNPC(Map<Integer, PathfinderGoal> pathfinders, World world){


    }
    protected void startEntity(Map<Integer, PathfinderGoal> pathfinders, World world){
        this.bP = new PathfinderGoalSelector(((CraftWorld)world).getHandle().getMethodProfilerSupplier());
        pathfinders.forEach((integer, pathfinderGoal) -> bP.a(integer, pathfinderGoal));
        this.bP.a(PathfinderGoal.Type.a, false);
        this.bP.a(PathfinderGoal.Type.c, false);
        this.bP.a(PathfinderGoal.Type.b, false);
        addEntity(this);

    }
    private void doTick(){
        bP.doTick();
    }

    @Override @OverridingMethodsMustInvokeSuper
    public void kill() {
        removeEntity(this);
    }

    private static void addEntity(LivingNPC entityNPC){
        if(hashSet.size() == 0){
            runner = new BukkitRunnable() {
                @Override
                public void run() {
                    hashSet.forEach(LivingNPC::doTick);
                }
            };
            runner.runTaskTimer(Main.getMain(), 1, 1);
        }
        hashSet.add(entityNPC);
    }
    private static void removeEntity(LivingNPC entityNPC){
        hashSet.remove(entityNPC);
        if(hashSet.size() == 0)
            runner.cancel();
    }
}
