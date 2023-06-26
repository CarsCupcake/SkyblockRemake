package me.CarsCupcake.SkyblockRemake.isles.rift;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.isles.rift.entitys.RiftEntity;
import me.CarsCupcake.SkyblockRemake.isles.rift.events.RiftCalculatorEvent;
import me.CarsCupcake.SkyblockRemake.isles.rift.events.RiftDamageEvent;
import me.CarsCupcake.SkyblockRemake.utils.Assert;
import me.CarsCupcake.SkyblockRemake.utils.runnable.RunnableWithParam;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RiftCalculator {
    public long timeDamage;
    public double heartsDamage;
    @Getter
    private RiftPlayer player;
    @Getter
    private SkyblockEntity entity;
    @Getter
    private Action action;
    private boolean isDone = false;
    public void damagePlayer(RiftEntity entity, RiftPlayer player){
        damagePlayer(entity, player, 0);
    }
    public void damagePlayer(@Nullable RiftEntity entity,@NotNull RiftPlayer player, int extraHeartDamage){
        Assert.notNull(player, "Player is not allowed to be null!");
        if(entity == null) action = Action.PlayerSelfe; else action = Action.EntityToPlayer;
        this.entity = entity;
        this.player = player;
        timeDamage = (entity != null) ? entity.getRiftTimeDamage() : 0;
        heartsDamage = ((entity != null) ? entity.getHeartsDamage() : 0) + extraHeartDamage;
    }
    public void damageEntity(SkyblockEntity entity, RiftPlayer player){
        damageEntity(entity, player, 0);
    }
    public void damageEntity(SkyblockEntity entity, RiftPlayer player, int extraHeartDamage){
        Assert.notNull(entity, "Entity is not allowed to be null!");
        if(player == null) action = Action.EntitySelfe; else action = Action.PlayerToEntity;
        this.entity = entity;
        this.player = player;
        timeDamage = 0;
        heartsDamage = ((player != null) ? Main.getPlayerStat(player, Stats.RiftDamage) : 0) + extraHeartDamage;
    }
    public void execute(){
        RiftCalculatorEvent event = new RiftDamageEvent(this);
        Bukkit.getPluginManager().callEvent(event);
        if(event.isCancelled()) return;
        isDone = true;
        action.run(event);
        isDone = false;
    }
    public enum Action implements RunnableWithParam<RiftCalculatorEvent> {
        PlayerToEntity() {
            @Override
            public void run(RiftCalculatorEvent event) {
                if(!event.getCalculator().isDone) throw new IllegalCallerException("Not allowed to call this before the calculation is done!");
                event.getCalculator().entity.damage(event.getCalculator().heartsDamage * event.getMultiplier(), event.getCalculator().getPlayer());
            }
        },
        EntityToPlayer {
            @Override
            public void run(RiftCalculatorEvent event) {
                if(!event.getCalculator().isDone) throw new IllegalCallerException("Not allowed to call this before the calculation is done!");
                event.getCalculator().player.damage(event.getCalculator().heartsDamage * event.getMultiplier(), event.getCalculator().entity.getEntity());
                event.getCalculator().player.subtractRiftTime(event.getCalculator().timeDamage);
            }
        },
        PlayerSelfe{
            @Override
            public void run(RiftCalculatorEvent event) {
                if(!event.getCalculator().isDone) throw new IllegalCallerException("Not allowed to call this before the calculation is done!");
                event.getCalculator().player.damage(event.getCalculator().heartsDamage * event.getMultiplier());
                event.getCalculator().player.subtractRiftTime(event.getCalculator().timeDamage);
            }
        },
        EntitySelfe {
            @Override
            public void run(RiftCalculatorEvent event) {
                PlayerToEntity.run(event);
            }
        }
    }
}
