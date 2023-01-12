package me.CarsCupcake.SkyblockRemake.Slayer.Enderman;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.GetTotalStatEvent;
import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Slayer;
import me.CarsCupcake.SkyblockRemake.Stats;
import me.CarsCupcake.SkyblockRemake.Tools;
import me.CarsCupcake.SkyblockRemake.abilitys.EntityAbilitys;
import org.bukkit.Location;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Random;

public class EndermanT1 extends Slayer implements Listener {


    private int health = 300000;
    public boolean isInHitsPhase = true;
    public int hitphaseHits = 15;
    private int hitsPhase = 0;
    private Enderman entity;
    private BukkitRunnable aoe;
    private BukkitRunnable tp;

    public EndermanT1(SkyblockPlayer player) {
        super(player);
    }

    @Override
    public int getMaxHealth() {
        return 300000;
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
    public void setHealth(int i) {
        health = 300000;
    }

    @Override
    public int getDamage() {
        return 1200;
    }

    @Override
    public void spawn(Location loc) {
        entity = loc.getWorld().spawn(loc, Enderman.class, enderman ->{

        });

        aoe = new BukkitRunnable() {
            @Override
            public void run() {
                Calculator calculator = new Calculator();
                calculator.entityToPlayerDamage(EndermanT1.this, owner, new Bundle<>(getDamage()/2, getTrueDamage()));
                calculator.damagePlayer(owner);
                calculator.showDamageTag(owner);
                owner.damage(0.0000001);
            }
        };
        aoe.runTaskTimer(Main.getMain(), 20, 20);
        tp = new BukkitRunnable() {
            @Override
            public void run()
                {
                    EntityAbilitys.voidgloomTeleport(entity, owner);
                }

        };
        tp.runTaskTimer(Main.getMain(), 20*5, 20*5);



    }


    @Override
    public String getName() {
        return "Voidgloom Seraph";
    }

    @Override
    public HashMap<ItemManager, Integer> getDrops(SkyblockPlayer player) {
        HashMap<ItemManager, Integer> drops = new HashMap<>();
        drops.put(EndermanSlayerItems.NullSphere(), new Random().nextInt(1) + 2);
        return drops;
    }

    @Override
    public void updateNameTag() {
        if(isInHitsPhase){
            entity.setCustomName("§c" + Character.toChars(9760)[0] + " §b" + getName() + " "+getColor()+"§l"
                    + hitphaseHits + "Hits");
        }else
            entity.setCustomName("§c" + Character.toChars(9760)[0] + " §b" + getName() + " §a"
                    + Tools.toShortNumber(health) + "§c");
    }
    private String getColor(){
        double pers = hitphaseHits/15d;
        if(pers > 0.6)
            return "§r";
        if (pers > 0.3)
            return "§d";
        else
            return "§5";
    }

    @Override
    public void kill() {
        try {
            aoe.cancel();
        }catch (Exception ignored){}
        try {
            tp.cancel();
        }catch (Exception ignored){}
    }

    @Override
    public void damage(double damage, SkyblockPlayer player) {
        if(isInHitsPhase) {
            hitphaseHits--;
            if(hitphaseHits <= 0) {
                isInHitsPhase = false;
                hitsPhase++;
            }
        }else {
            health -= damage;

            if(toNextHitPhase() && health != 0){
                hitphaseHits = 15;
                isInHitsPhase = true;
            }
        }
    }
    private boolean toNextHitPhase(){

        if(hitsPhase == 1 && health <=198000){
            return true;
        }
        return hitsPhase == 2 && health <= 99000;
    }

    @Override
    public boolean hasNoKB() {
        return true;
    }

    @Override
    public int getTrueDamage() {
        return 0;
    }

    @EventHandler
    public void onDamage(SkyblockDamageEvent event){
        if(event.getType() == SkyblockDamageEvent.DamageType.PlayerToEntity && SkyblockEntity.livingEntity.containsKey(event.getEntity())){
            SkyblockEntity entity = SkyblockEntity.livingEntity.get(event.getEntity());
            if(entity instanceof EndermanT1 endermanT1) {
                if(endermanT1.isInHitsPhase)
                    event.getCalculator().damage = 0;
            }
        }
    }

    @EventHandler
    public void onCalculation(GetTotalStatEvent event){
        if(Slayer.hasActiveSlayer(event.getPlayer())){
            Slayer slayer = Slayer.getSlayer(event.getPlayer());
            if(slayer instanceof EndermanT1 endermanT1){
                if(!endermanT1.isInHitsPhase)
                    if(event.getStat() == Stats.Ferocity)
                        event.setValue(event.getValue()*0.25);
            }
        }
    }

}
