package me.CarsCupcake.SkyblockRemake.Entities;

import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.ai.goal.PathfinderGoal;
import org.bukkit.Location;

public class PathfinderGoalPassiveGoToLocation extends PathfinderGoal {
    private final EntityInsentient insentient;
    private final double c;
    private final double d;
    private final double e;
    public PathfinderGoalPassiveGoToLocation(EntityInsentient insentient, Location location){
        this.insentient = insentient;
        this.c = location.getX();
        this.d = location.getY();
        this.e = location.getZ();
    }
    @Override
    public boolean a() {
        return insentient.getGoalTarget() == null;
    }
    public void c() {
        this.insentient.getNavigation().a(this.c, this.d, this.e, 1);
    }

    public void d() {
        this.insentient.getNavigation().o();
        super.d();
    }
}
