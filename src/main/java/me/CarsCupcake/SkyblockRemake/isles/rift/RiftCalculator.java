package me.CarsCupcake.SkyblockRemake.isles.rift;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.FinalDamageDesider;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.isles.rift.entitys.RiftEntity;
import me.CarsCupcake.SkyblockRemake.isles.rift.events.RiftCalculatorEvent;
import me.CarsCupcake.SkyblockRemake.isles.rift.events.RiftDamageEvent;
import me.CarsCupcake.SkyblockRemake.utils.Assert;
import me.CarsCupcake.SkyblockRemake.utils.runnable.RunnableWithParam;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

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
        if(action == Action.PlayerToEntity && entity instanceof FinalDamageDesider fdd)
            heartsDamage = fdd.getFinalDamage(player, heartsDamage);
        isDone = true;
        action.run(event);
        isDone = false;
    }
    public void spawnTag(SkyblockEntity entity, String str){
        Entity e = entity.getEntity();
        spawnTag(new Location(e.getWorld(), e.getLocation().getX(), e.getLocation().getY() + 0.7, e.getLocation().getZ()), str);
    }
    public void spawnTag(Entity e, String str){
        spawnTag(new Location(e.getWorld(), e.getLocation().getX(), e.getLocation().getY() + 0.7, e.getLocation().getZ()), str);
    }
    public void spawnTag(Location loc, String str){
        loc = loc.clone().add(new Random().nextDouble(0.4) - 0.2, new Random().nextDouble(0.4) - 0.2, new Random().nextDouble(0.4) - 0.2);
        ArmorStand stand = loc.getWorld().spawn(loc, ArmorStand.class, armorstand -> {
            armorstand.setVisible(false);
            armorstand.setGravity(false);
            armorstand.setMarker(true);
            armorstand.setCustomNameVisible(true);
            armorstand.setInvulnerable(true);
            armorstand.setCustomName("§7" + str);
            armorstand.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 999999));
            armorstand.addScoreboardTag("damage_tag");
            armorstand.setArms(false);
            armorstand.setBasePlate(false);
        });
        Main.getMain().killarmorstand(stand);
    }
    public enum Action implements RunnableWithParam<RiftCalculatorEvent> {
        PlayerToEntity() {
            @Override
            public void run(RiftCalculatorEvent event) {
                if(!event.getCalculator().isDone) throw new IllegalCallerException("Not allowed to call this before the calculation is done!");
                event.getCalculator().entity.damage(event.getCalculator().heartsDamage * event.getMultiplier(), event.getCalculator().getPlayer());
                if(event.getCalculator().heartsDamage > 0)
                    event.getCalculator().spawnTag(event.getCalculator().getEntity().getEntity(), String.format("%.0f", event.getCalculator().heartsDamage));
                if(event.getCalculator().getEntity().getHealth() <= 0)
                    SkyblockEntity.killEntity(event.getCalculator().getEntity(), event.getCalculator().getPlayer());
            }
        },
        EntityToPlayer {
            @Override
            public void run(RiftCalculatorEvent event) {
                if(!event.getCalculator().isDone) throw new IllegalCallerException("Not allowed to call this before the calculation is done!");
                event.getCalculator().player.damage(event.getCalculator().heartsDamage * event.getMultiplier(), event.getCalculator().entity.getEntity());
                event.getCalculator().player.subtractRiftTime(event.getCalculator().timeDamage);
                if(event.getCalculator().heartsDamage > 0)
                    event.getCalculator().spawnTag(event.getCalculator().getPlayer(), String.format("%.0f", event.getCalculator().heartsDamage));
                if(event.getCalculator().timeDamage > 0)
                    event.getCalculator().spawnTag(event.getCalculator().getPlayer(), "§a" + event.getCalculator().timeDamage);
            }
        },
        PlayerSelfe{
            @Override
            public void run(RiftCalculatorEvent event) {
                if(!event.getCalculator().isDone) throw new IllegalCallerException("Not allowed to call this before the calculation is done!");
                event.getCalculator().player.damage(event.getCalculator().heartsDamage * event.getMultiplier());
                event.getCalculator().player.subtractRiftTime(event.getCalculator().timeDamage);
                if(event.getCalculator().heartsDamage > 0)
                    event.getCalculator().spawnTag(event.getCalculator().getPlayer(), String.format("%.0f", event.getCalculator().heartsDamage));
                if(event.getCalculator().timeDamage > 0)
                    event.getCalculator().spawnTag(event.getCalculator().getPlayer(), "§a" + event.getCalculator().timeDamage);
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
