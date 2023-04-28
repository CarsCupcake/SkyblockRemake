package me.CarsCupcake.SkyblockRemake.Skyblock.projectile;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.projectile.EntityWitherSkull;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_17_R1.util.CraftVector;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractSbWitherSkull extends EntityWitherSkull implements SkyblockProjectile {
    public AbstractSbWitherSkull(LivingEntity entity) {
        super(EntityTypes.bb, ((CraftWorld) entity.getWorld()).getHandle());
        ((CraftWorld) entity.getWorld()).getHandle().addEntity(this);
        launch(entity, entity.getLocation().getDirection().normalize().multiply(0.5));
    }
    public AbstractSbWitherSkull(LivingEntity entity, Location location) {
        super(((CraftWorld) entity.getWorld()).getHandle(), ((CraftLivingEntity) entity).getHandle(), location.getX(), location.getY(), location.getZ());
        this.setYawPitch(location.getYaw(), location.getPitch());
        ((CraftWorld) entity.getWorld()).getHandle().addEntity(this);
        launch(entity, location);

    }
    public void setup(LivingEntity source){
        this.setLocation(source.getLocation().getX(), source.getLocation().getY(), source.getLocation().getZ(), source.getLocation().getYaw(), source.getLocation().getPitch());
        this.setPositionRotation(source.getLocation().getX(), source.getLocation().getY(), source.getLocation().getZ(), source.getLocation().getYaw(), source.getLocation().getPitch());
    }
    public void launch(@Nullable LivingEntity source,@NotNull Vector vector){
        setup(source);
        this.projectileSource = source;
        this.setMot(CraftVector.toNMS(vector.normalize().multiply(0.5)));

    }
    public void launch(@Nullable LivingEntity source,@NotNull Location vector){
        this.projectileSource = source;
        this.setMot(CraftVector.toNMS(vector.getDirection().normalize().multiply(0.5)));
    }
}
