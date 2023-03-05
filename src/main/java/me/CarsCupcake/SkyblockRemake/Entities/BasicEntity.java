package me.CarsCupcake.SkyblockRemake.Entities;

import lombok.SneakyThrows;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.libs.org.apache.http.MethodNotSupportedException;
import org.bukkit.entity.LivingEntity;

import java.util.HashMap;

public class BasicEntity extends SkyblockEntity {
    private LivingEntity entity;
    private final int maxHealth;
    private final int damage;
    private Class<? extends LivingEntity> aClass;

    public BasicEntity(LivingEntity entity, int maxHealth){
        super();
        health = maxHealth;
        this.entity = entity;
        this.maxHealth = maxHealth;
        damage = (int) (entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue() * 5);
        SkyblockEntity.livingEntity.put(entity, this);
        Main.updateentitystats(entity);
    }
    public BasicEntity(Class<? extends LivingEntity> entity, int maxHealth, int damage){
        aClass = entity;
        this.maxHealth = maxHealth;
        health = maxHealth;
        this.damage = damage;

    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    @SneakyThrows
    public void spawn(Location loc) {
        if(aClass == null)
            throw new MethodNotSupportedException("Method is not avaidable!");
        entity = loc.getWorld().spawn(loc, aClass, entity1 -> entity1.setCustomNameVisible(true));
    }

    @Override
    @SuppressWarnings("deprecation")
    public String getName() {
        return entity.getType().getName();
    }

    @Override
    public HashMap<ItemManager, Integer> getDrops(SkyblockPlayer player) {
        return null;
    }

    @Override
    public void updateNameTag() {
        entity.setCustomName(SkyblockEntity.getBaseName(this));
    }

    @Override
    public void kill() {

    }

    @Override
    public void damage(double damage, SkyblockPlayer player) {
        health -= damage;
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
