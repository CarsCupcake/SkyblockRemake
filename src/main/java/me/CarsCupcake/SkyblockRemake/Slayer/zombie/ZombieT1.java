package me.CarsCupcake.SkyblockRemake.Slayer.zombie;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.EntityAtributes;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkillDroper;
import me.CarsCupcake.SkyblockRemake.Skyblock.Skills.Skills;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@EntityAtributes({EntityAtributes.Attributes.AbilityImune, EntityAtributes.Attributes.ProjectileImune})
public class ZombieT1 extends LifeDrain implements SkillDroper {
    private Zombie entity;
    public ZombieT1(SkyblockPlayer player) {
        super(player);
    }

    @Override
    public int getMaxHealth() {
        return 500;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public int getDamage() {
        return 15;
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
    public Map<Skills, Double> getSkillXp() {
        return Map.of(Skills.Combat, 50d);
    }
}
