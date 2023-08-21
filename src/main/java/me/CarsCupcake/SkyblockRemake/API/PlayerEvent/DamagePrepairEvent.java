package me.CarsCupcake.SkyblockRemake.API.PlayerEvent;

import lombok.Getter;
import lombok.Setter;
import me.CarsCupcake.SkyblockRemake.Entities.EntityHandler;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class DamagePrepairEvent extends PlayerEvent{
    private static final HandlerList HANDLERS = new HandlerList();
    @Getter
    private final LivingEntity entity;
    @Getter
    private double preMultiplier;
    @Getter
    private double postMultiplier;
    @Getter
    private final Calculator calculator;
    @Getter
    private final HashMap<Stats, Double> stats;
    @Getter
    @Setter
    private double weaponDamage;
    @Getter
    private final int hits;
    public DamagePrepairEvent(SkyblockPlayer player, LivingEntity entity, Calculator calculator, double pre, double post, HashMap<Stats, Double> stats, double weaponDamage) {
        super(player);
        this.entity = entity;
        this.calculator = calculator;
        preMultiplier = pre;
        postMultiplier = post;
        this.stats = stats;
        this.weaponDamage = weaponDamage;
        hits = EntityHandler.getOrDefault("hit", entity, PersistentDataType.INTEGER, 0);
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
    public static HandlerList getHandlerList(){
        return HANDLERS;
    }
    public void addPreMultiplier(double d){
        preMultiplier+=d;
    }

    public void addPostMultiplier(double d){
        postMultiplier*=d;
    }

}
