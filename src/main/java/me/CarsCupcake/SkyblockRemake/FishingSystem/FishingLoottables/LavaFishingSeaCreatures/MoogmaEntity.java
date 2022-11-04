package me.CarsCupcake.SkyblockRemake.FishingSystem.FishingLoottables.LavaFishingSeaCreatures;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.animal.EntityMushroomCow;
import net.minecraft.world.level.World;
import org.bukkit.Location;

public class MoogmaEntity extends EntityMushroomCow {

    public MoogmaEntity(Location loc, World world) {
        super(EntityTypes.ah, world);
        this.setPosition(loc.getX(), loc.getY(), loc.getZ());
        this.setHealth(20);
        this.setCustomNameVisible(true);

    }


    @Override
    public void initPathfinder() {


        this.bP.a(0, new MoogmaAttackGoal(this, 1.0, true));
    }
}
