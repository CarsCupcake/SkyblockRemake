package me.CarsCupcake.SkyblockRemake.FishingSystem.FishingLoottables.LavaFishingSeaCreatures;

import me.CarsCupcake.SkyblockRemake.Skyblock.MultipleEntity.MultipleEntityBodyPart;
import me.CarsCupcake.SkyblockRemake.Skyblock.MultipleEntity.MultipleEntityHead;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class FireEelBody extends MultipleEntityBodyPart {
    private boolean isTail;
    public FireEelBody(MultipleEntityHead main, boolean isTail) {
        super(main);
    }

    @Override
    public void move() {}

    @Override
    public LivingEntity getEntity() {
        return null;
    }

    @Override
    public void spawn(Location loc) {




    }



    @Override
    public void updateNameTag() {

    }

    @Override
    public void kill() {

    }
}
