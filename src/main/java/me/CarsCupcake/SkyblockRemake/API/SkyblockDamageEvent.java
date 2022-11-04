package me.CarsCupcake.SkyblockRemake.API;

import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SkyblockDamageEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean isCanceled = false;
    private final SkyblockPlayer player;
    private final LivingEntity entity;
    private final DamageType type;
    private Calculator calculator;
    private final EntityDamageEvent.DamageCause cause;
    private Projectile projectile;


    public SkyblockDamageEvent(@Nullable SkyblockPlayer player, @Nullable LivingEntity entity, @NotNull Calculator calculator, @NotNull DamageType type, @NotNull EntityDamageEvent.DamageCause cause){
        this.player = player;
        this.entity = entity;
        this.type = type;
        this.calculator = calculator;
        this.cause = cause;

    }

    public SkyblockDamageEvent(@Nullable SkyblockPlayer player, @Nullable LivingEntity entity, @NotNull Calculator calculator, @NotNull DamageType type, @NotNull EntityDamageEvent.DamageCause cause, Projectile projectile){
        this.player = player;
        this.entity = entity;
        this.type = type;
        this.calculator = calculator;
        this.cause = cause;
        this.projectile = projectile;

    }

    @NotNull
    public DamageType getType(){
        return type;
    }

    @NotNull
    public EntityDamageEvent.DamageCause getCause(){
        return cause;
    }

    @Nullable
    public SkyblockPlayer getPlayer(){
        return player;
    }

    @Nullable
    public LivingEntity getEntity(){
        return entity;
    }

    @Nullable
    public Projectile getProjectile(){
        if(projectile == null)
            throw new NullPointerException("Projectile is null");
        return projectile;
    }

    @NotNull
    public Calculator getCalculator(){
        return calculator;
    }

    public void setCalculator(@NotNull Calculator calculator){
        this.calculator = calculator;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return isCanceled;
    }

    @Override
    public void setCancelled(boolean b) {
        isCanceled = b;
    }

    /**
     * The enum for the damage type
     */
    public enum DamageType{
        EntityToPlayer,
        PlayerToEntity,
        PlayerSelve,
        EntitySelve
    }











    //Stop stealing my code!!!!
}
