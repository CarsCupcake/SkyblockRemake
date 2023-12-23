package me.CarsCupcake.SkyblockRemake.utils.ai;

import net.minecraft.world.entity.EntityCreature;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.IEntitySelector;
import net.minecraft.world.entity.ai.goal.PathfinderGoal;
import net.minecraft.world.entity.ai.navigation.NavigationAbstract;
import net.minecraft.world.entity.ai.targeting.PathfinderTargetCondition;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.level.pathfinder.PathEntity;
import net.minecraft.world.phys.Vec3D;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;
import java.util.Objects;
import java.util.function.Predicate;

public class ChaseAndRunAwayPathFinder<T extends EntityLiving> extends PathfinderGoal {


    private final Status entity;
    protected final EntityCreature a;
    private final double i;
    private final double j;
    protected T b;
    protected final float c;
    protected PathEntity d;
    protected final NavigationAbstract e;
    protected final Class<T> f;
    protected final Predicate<EntityLiving> g;
    protected final Predicate<EntityLiving> h;
    private final PathfinderTargetCondition k;


    public ChaseAndRunAwayPathFinder(@NotNull("Status not allowed to be null!") Status q, EntityCreature var0, Class<T> var1, float var2, double var3, double var5) {
        this(Objects.requireNonNull(q),var0, var1, (var0x) -> true, var2, var3, var5, IEntitySelector.e::test);
    }

    public ChaseAndRunAwayPathFinder(@NotNull("Status not allowed to be null!") Status q, EntityCreature var0, Class<T> var1, Predicate<EntityLiving> var2, float var3, double var4, double var6, Predicate<EntityLiving> var8) {
        entity = Objects.requireNonNull(q);
        Objects.requireNonNull(var8);
        this.a = var0;
        this.f = var1;
        this.g = var2;
        this.c = var3;
        this.i = var4;
        this.j = var6;
        this.h = var8;
        this.e = var0.getNavigation();
        this.a(EnumSet.of(Type.a,Type.b));
        this.k = PathfinderTargetCondition.a().a(var3).a(var8.and(var2));


    }

    @Override
    public boolean a() {
        if(!entity.check()) {
            this.b = this.a.t.a(this.a.t.a(this.f, this.a.getBoundingBox().grow(this.c, 3.0, this.c), (var0x) -> true), this.k, this.a, this.a.locX(), this.a.locY(), this.a.locZ());
            if (this.b == null) {
                return false;
            } else {
                Vec3D var0 = DefaultRandomPos.a(this.a, 16, 7, this.b.getPositionVector());
                if (var0 == null) {
                    return false;
                } else if (this.b.h(var0.b, var0.c, var0.d) < this.b.f(this.a)) {
                    return false;
                } else {
                    this.d = this.e.a(var0.b, var0.c, var0.d, 0);
                    return this.d != null;
                }
            }
        }
        return false;
    }

    public boolean b() {
        return !this.e.m();
    }

    public void c() {
        this.e.a(this.d, this.i);
    }

    public void d() {
        this.b = null;
    }

    public void e() {
        if (this.a.f(this.b) < 49.0) {
            this.a.getNavigation().a(this.j);
        } else {
            this.a.getNavigation().a(this.i);
        }

    }
    public interface Status {
        /**
         * Checks if the entity should attack or run away
         * @return returns true if the entity should attack and false if not
         */
        boolean check();
    }
}
