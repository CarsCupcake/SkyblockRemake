package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6.Entitys;


import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6.Phase2;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Giant;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GiantLASR extends SkyblockEntity {
    private int health = 25000000;
    Giant entity;
    private BukkitRunnable run;
    private Phase2 p2;

    public GiantLASR(){

    }
    public GiantLASR(Phase2 p2){
        this.p2 = p2;
    }



    @Override
    public int getMaxHealth() {
        return 25000000;
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
        return 0;
    }

    @Override
    public void spawn(Location loc) {
        GiantAI ai = new GiantAI(loc);
        ((CraftWorld) loc.getWorld()).getHandle().addEntity(ai, CreatureSpawnEvent.SpawnReason.CUSTOM);

        entity = (Giant) ai.getBukkitEntity();
        entity.setAI(true);
        entity.setRemoveWhenFarAway(false);
        entity.setAware(true);
        entity.addScoreboardTag("combatxp:3000");
        entity.setTarget((Player)Bukkit.getOnlinePlayers().toArray()[ new Random().nextInt(Bukkit.getOnlinePlayers().size())]);

        SkyblockEntity.livingEntity.put(entity, this);
        Main.updateentitystats(entity);
        start();

    }
    private void start(){
        run = new BukkitRunnable() {
            @Override
            public void run() {
                List<Entity> e = entity.getNearbyEntities(16,16,16).stream().filter(entity1 -> entity1 instanceof Player).toList();
                if(!e.isEmpty()){
                    if(e.contains(entity.getTarget())){
                        laser(SkyblockPlayer.getSkyblockPlayer((Player) entity.getTarget()));
                    }else {
                        SkyblockPlayer newTarget = SkyblockPlayer.getSkyblockPlayer(((Player)e.get(new Random().nextInt(e.size()))));
                        entity.setTarget(newTarget);
                        laser(newTarget);
                    }
                }
            }
        };
        run.runTaskTimer(Main.getMain(), 10, 10);
    }
    private void laser(SkyblockPlayer target){
        final Particle.DustOptions dust = new Particle.DustOptions(
                Color.fromRGB( 255, 0,  0), 2);
        Calculator c = new Calculator();
        c.entityToPlayerDamage(this, target, new Bundle<>(60000,0));
        c.damagePlayer(target);
        c.showDamageTag(target);
        Vector dir = target.getLocation().toVector().subtract(entity.getEyeLocation().toVector());
        dir.normalize();
        Location ll = entity.getLocation();
        ll.setDirection(dir.clone());
        entity.teleport(ll);
        dir = dir.multiply(0.2);
        Location l = entity.getEyeLocation().clone();
        int i = 0;
        while (target.getLocation().distance(l) > 0.5){
            l = l.add(dir);
            entity.getLocation().getWorld().spawnParticle(Particle.REDSTONE, l.getX(), l.getY(), l.getZ(),1, dust);
            i++;
            if(i > 1000000)
                break;
        }
    }


    @Override
    public String getName() {
        return "LASR";
    }

    @Override
    public HashMap<ItemManager, Integer> getDrops(SkyblockPlayer player) {
        return null;
    }

    @Override
    public void updateNameTag() {
        entity.setCustomName("§c§l" + getName());
    }
    private void stop(){
        try {
            run.cancel();
        }catch (Exception ignored){}
    }

    @Override
    public void kill() {
        stop();
        if(p2 != null)
            p2.killGiant();
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
