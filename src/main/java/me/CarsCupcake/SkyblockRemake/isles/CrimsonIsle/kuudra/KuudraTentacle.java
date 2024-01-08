package me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.kuudra;

import lombok.Getter;
import lombok.Setter;
import me.CarsCupcake.SkyblockRemake.Entities.StandCoreExtention;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.MagicNumber;
import me.CarsCupcake.SkyblockRemake.utils.runnable.EntityRunnable;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.MagmaCube;

import java.util.ArrayList;
import java.util.List;

public class KuudraTentacle extends KuudraEntity {
    @Getter
    private List<MagmaCube> entities = new ArrayList<>(13);
    private MagmaCube cube;
    @Getter
    public static final double eyeHeight = 54;
    @Getter
    @Setter
    private Location target;
    @Getter
    @Setter
    private List<KuudraEntity> spawnableEntities = new ArrayList<>();
    @Getter
    private Location kuudraLocation;
    @Setter
    @Getter
    private Location movementPointer;
    @Getter
    private TentacleAnimations animations;
    @Getter
    @Setter
    public boolean mirroredPoints = true;
    @Getter
    @Setter
    public SkyblockPlayer playerGrabTarget;
    @Getter
    private final List<Location> imaginaryLocation = new ArrayList<>();
    @Getter
    private List<Location> restLocations = new ArrayList<>();

    public KuudraTentacle(int tier) {
        super(tier);
    }

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
        kuudraLocation = loc.clone();
        kuudraLocation.setY(79);
        cube = loc.getWorld().spawn(loc, MagmaCube.class, magmaCube -> {
            magmaCube.setAI(false);
            magmaCube.setSize(14);
            magmaCube.setInvulnerable(true);
            magmaCube.setRemoveWhenFarAway(false);
        });
        imaginaryLocation.add(cube.getLocation());
        SkyblockEntity.livingEntity.addEntity(cube, this);
        entities.add(cube);
        for (int i = 13; i > 0; i--)
            spawnCube(i);
        new EntityRunnable() {
            @Override
            public void run() {
                KuudraTentacleMover.moveTentacle(KuudraTentacle.this, movementPointer);
            }
        }.runTaskTimer(this, 0, 1);
    }
    private void spawnCube(int size) {
        MagmaCube last = entities.get(entities.size() - 1);
        Location l = last.getLocation();
        l.setY(last.getBoundingBox().getMaxY() - (last.getSize() * 0.0240384615d));
        MagmaCube c = cube.getWorld().spawn(l, MagmaCube.class, magmaCube -> {
            magmaCube.setSize(size);
            magmaCube.setAI(false);
            magmaCube.setInvulnerable(true);
            magmaCube.setRemoveWhenFarAway(false);
        });
        new StandCoreExtention(c, this);
        c.setCustomNameVisible(false);
        imaginaryLocation.add(c.getLocation());
        entities.add(c);
        restLocations.add(c.getLocation());
        if (size == 1) movementPointer = c.getLocation();
    }

    @Override
    public String getName() {
        return "Tentacle";
    }

    @Override
    public boolean hasNoKB() {
        return true;
    }

    @Override
    public int getTokens() {
        return 0;
    }

    @Override
    public void kill() {
        super.kill();
        entities.forEach(Entity::remove);
    }

    public void setAnimations(TentacleAnimations animations) {
        if (animations == null) animations = TentacleAnimations.Idle;
        this.animations = animations;
        animations.run(this);
    }
}
