package me.CarsCupcake.SkyblockRemake.Slayer.Zombie;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.EntityAtributes;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Slayer;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;

import java.util.HashMap;

@EntityAtributes({EntityAtributes.Attributes.AbilityImune, EntityAtributes.Attributes.ProjectileImune})
public class ZombieT1 extends LifeDrain {
    private Zombie entity;
    public ZombieT1(SkyblockPlayer player) {
        super(player);
    }

    @Override
    public int getMaxHealth() {
        return 0;
    }

    @Override
    public LivingEntity getEntity() {
        return null;
    }

    @Override
    public void setHealth(int i) {

    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public void spawn(Location loc) {
        super.spawn(loc);

    }

    @Override
    public String getName() {
        return null;
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
    int healAmount() {
        return 0;
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
}
