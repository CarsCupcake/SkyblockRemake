package me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.kuudra;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Entities.StandCoreExtention;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.utils.MagicNumber;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.MagmaCube;

import java.util.ArrayList;
import java.util.List;

public class KuudraTentacle extends SkyblockEntity {
    @Getter
    private List<MagmaCube> entities = new ArrayList<>(13);
    private MagmaCube cube;

    @Override
    public int getMaxHealth() {
        return (int) MagicNumber.milion(30);
    }

    @Override
    public LivingEntity getEntity() {
        return cube;
    }

    @Override
    public void spawn(Location loc) {
        cube = loc.getWorld().spawn(loc, MagmaCube.class, magmaCube -> {
            magmaCube.setAI(false);
            magmaCube.setSize(14);
        });
        SkyblockEntity.livingEntity.addEntity(cube, this);
        entities.add(cube);
        for (int i = 13; i > 0; i--)
            spawnCube(i);
    }
    private void spawnCube(int size) {
        MagmaCube last = entities.get(entities.size() - 1);
        Location l = last.getLocation();
        l.setY(last.getBoundingBox().getMaxY());
        MagmaCube c = cube.getWorld().spawn(l, MagmaCube.class, magmaCube -> {
            magmaCube.setSize(size);
            magmaCube.setAI(false);
        });
        new StandCoreExtention(c, this);
        entities.add(c);
    }

    @Override
    public String getName() {
        return "Tentacle";
    }

    @Override
    public boolean hasNoKB() {
        return true;
    }
}
