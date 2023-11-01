package me.CarsCupcake.SkyblockRemake.Slayer.zombie;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.jetbrains.annotations.NotNull;

public class ZombieT4 extends Enrage{
    private Zombie entity;
    public ZombieT4(SkyblockPlayer player) {
        super(player);
    }

    @Override
    public int getMaxHealth() {
        return 1500000;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public int getBaseDamage() {
        return 400;
    }

    @Override
    public void spawn(@NotNull Location loc) {
        super.spawn(loc);
        entity = loc.getWorld().spawn(loc, Zombie.class, zombie -> {
            zombie.setAdult();
            zombie.setCustomNameVisible(true);
            equip(zombie);
        });
        SkyblockEntity.livingEntity.addEntity(entity, this);
        Main.updateentitystats(entity);
    }

    @Override
    public String getName() {
        return "Revenant Horror";
    }

    @Override
    public void updateNameTag() {
        entity.setCustomName(getBaseName(this));
    }
}
