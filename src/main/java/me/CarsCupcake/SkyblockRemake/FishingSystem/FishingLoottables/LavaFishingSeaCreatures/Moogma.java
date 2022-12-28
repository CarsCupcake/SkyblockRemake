package me.CarsCupcake.SkyblockRemake.FishingSystem.FishingLoottables.LavaFishingSeaCreatures;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Corruptable;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.entity.ai.goal.PathfinderGoalFloat;
import net.minecraft.world.entity.ai.goal.PathfinderGoalMeleeAttack;
import net.minecraft.world.entity.ai.goal.PathfinderGoalZombieAttack;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftMushroomCow;
import org.bukkit.entity.Endermite;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.MushroomCow;
import org.bukkit.event.entity.EntityTargetEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Moogma extends SkyblockEntity implements Corruptable {

    private boolean isCorrupted = false;
    private int maxHealth = 2500000;
    private int health = 2500000;
    private LivingEntity entity;
    private final SkyblockPlayer target;
    public Moogma(SkyblockPlayer target){
        this.target = target;
    }
    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }


    @Override
    public int getDamage() {
        return 9000;
    }

    @Override
    public void spawn(Location loc) {
        WorldServer world = ((CraftWorld)loc.getWorld()).getHandle();
        MoogmaEntity e = new MoogmaEntity(loc,world);

        UUID id = e.getUniqueID();
        world.addEntity(e);
        entity = (LivingEntity) Bukkit.getEntity(id);
        e.setGoalTarget(target.getHandle(), EntityTargetEvent.TargetReason.CLOSEST_ENTITY, false);
        SkyblockEntity.livingEntity.put(entity, this);
        Main.updateentitystats(entity);
    }


    @Override
    public String getName() {
        return "Moogma";
    }

    @Override
    public HashMap<ItemManager,Integer> getDrops(SkyblockPlayer player) {
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

    @Override
    public void corrupt() {
        if(!isCorrupted){
            maxHealth *= 3;
            health *=3;
            isCorrupted = true;
        }

    }

    @Override
    public boolean isCorrupted() {
        return isCorrupted;
    }
}
