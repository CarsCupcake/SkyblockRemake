package me.CarsCupcake.SkyblockRemake.Skyblock.projectile;

import me.CarsCupcake.SkyblockRemake.Main;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.projectile.EntityWitherSkull;
import net.minecraft.world.level.World;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftWitherSkull;
import org.bukkit.craftbukkit.v1_17_R1.util.CraftVector;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractSbWitherSkull extends CraftWitherSkull implements SkyblockProjectile {
    public AbstractSbWitherSkull(Location location) {
        this(location, EntityTypes.bb.a(((CraftWorld) location.getWorld()).getHandle()));

    }

    private AbstractSbWitherSkull(Location location, EntityWitherSkull witherSkull) {
        super((CraftServer) Main.getMain().getServer(), witherSkull);
        ((CraftWorld) location.getWorld()).getHandle().addEntity(entity, CreatureSpawnEvent.SpawnReason.CUSTOM);
        witherSkull.setPositionRotation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        Vector direction = location.getDirection().multiply(10);
        ((EntityWitherSkull) entity).setDirection(direction.getX(), direction.getY(), direction.getZ());
    }
}
