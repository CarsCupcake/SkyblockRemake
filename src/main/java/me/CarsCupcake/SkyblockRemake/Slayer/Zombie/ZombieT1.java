package me.CarsCupcake.SkyblockRemake.Slayer.Zombie;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.EntityAtributes;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkillDroper;
import me.CarsCupcake.SkyblockRemake.Skyblock.Skills.Skills;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;

import java.util.HashMap;
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
    public void setHealth(int i) {

    }

    @Override
    public int getDamage() {
        return 15;
    }

    @Override
    public void spawn(Location loc) {
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
    public HashMap<ItemManager, Integer> getDrops(SkyblockPlayer player) {
        return null;
    }

    @Override
    public void updateNameTag() {

    }

    @Override
    public void kill() {
        super.kill();

    }


    @Override
    public void damage(double damage, SkyblockPlayer player) {

    }

    @Override
    public boolean hasNoKB() {
        return false;
    }

    @Override
    public int getTrueDamage() {
        return 0;
    }

    @Override
    public Map<Skills, Double> getSkillXp() {
        return Map.of(Skills.Combat, 50d);
    }
}
