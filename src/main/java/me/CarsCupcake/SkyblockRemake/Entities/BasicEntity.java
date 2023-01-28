package me.CarsCupcake.SkyblockRemake.Entities;

import lombok.SneakyThrows;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTableInfo;
import net.minecraft.world.level.storage.loot.parameters.LootContextParameterSets;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.libs.org.apache.http.MethodNotSupportedException;
import org.bukkit.entity.LivingEntity;
import org.bukkit.loot.LootContext;

import java.util.HashMap;

public class BasicEntity extends SkyblockEntity {
    private final LivingEntity entity;
    private final int maxHealth;
    private final int damage;

    public BasicEntity(LivingEntity entity, int maxHealth){
        super();
        health = maxHealth;
        this.entity = entity;
        this.maxHealth = maxHealth;
        damage = (int) (entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue() * 5);
        SkyblockEntity.livingEntity.put(entity, this);
        Main.updateentitystats(entity);
    }
    private BasicEntity(Class<? extends LivingEntity> clazz, Location l){
        entity = l.getWorld().spawn(l, clazz);
        maxHealth = (int) (entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() * 5);
        health = maxHealth;
        damage = (int) (entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue() * 5);
        SkyblockEntity.livingEntity.put(entity, this);
        Main.updateentitystats(entity);

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
        throw new MethodNotSupportedException("Method is not avaidable!");
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
