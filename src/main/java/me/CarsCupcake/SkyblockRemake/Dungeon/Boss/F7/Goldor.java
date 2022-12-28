package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7;

import me.CarsCupcake.SkyblockRemake.API.HealthChangeReason;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.boss.BarFlag;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftWither;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Random;


public class Goldor extends SkyblockEntity {
    private int health = 750000000;
    private LivingEntity entity;

    private BukkitRunnable attacks;
    private boolean isDone = false;
    private int gate = 0;

    @Override
    public int getMaxHealth() {
        return 750000000;
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
        return 50000;
    }
    public void setGate(int g){
        gate = g;
    }

    @Override
    public void spawn(Location loc) {
        entity = loc.getWorld().spawn(loc, Wither.class,wither ->{
            wither.setCustomNameVisible(true);
            wither.setRemoveWhenFarAway(false);
            wither.addScoreboardTag("combatxp:5000");
        });
        SkyblockEntity.livingEntity.put(entity,this);
        Main.updateentitystats(entity);
        entity.setAI(false);
        killingWave();



    }



    public void enableGoldor(){
        isDone = true;
        skillShoot();
    }


    private void skillShoot() {
        entity.setAI(false);
    attacks = new BukkitRunnable() {

        private int switchTarget = 0;
        private Player target = (Player) Bukkit.getOnlinePlayers().toArray()[ new Random().nextInt(Bukkit.getOnlinePlayers().size())];
        @Override
        public void run() {
            switchTarget++;
            if(switchTarget == 10){
                 target = (Player) Bukkit.getOnlinePlayers().toArray()[ new Random().nextInt(Bukkit.getOnlinePlayers().size())];
                 switchTarget = 0;
            }
            if (target != null){
                Location lo = entity.getLocation().setDirection(target.getEyeLocation().subtract(0,0.5,0).subtract(entity.getEyeLocation()).toVector());
                entity.teleport(lo);
                Location temp = entity.getLocation().clone();
                temp.setYaw(entity.getLocation().getYaw() + 90);
                Location head1 = entity.getEyeLocation().clone().add(temp.clone().getDirection().multiply(1));
                Location head2 = entity.getEyeLocation().clone().add(temp.clone().getDirection().multiply(-1));
                WitherSkull skull = entity.launchProjectile(WitherSkull.class);
                Vector velocity = skull.getVelocity().clone();
                head1.getWorld().spawn(head1.add(entity.getLocation().getDirection().clone().multiply(1)), WitherSkull.class,c -> skull.setVelocity(velocity));
                head2.getWorld().spawn(head2.add(entity.getLocation().getDirection().clone().multiply(1)), WitherSkull.class,c -> skull.setVelocity(velocity));
                entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_WITHER_SHOOT, 2f, 1.5f);



            }

            }


    };
    attacks.runTaskTimer(Main.getMain(), 0,4);

    }


    private void killingWave() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if(entity == null || entity.isDead()){
                    cancel();
                    return;
                }

                entity.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, entity.getLocation().add(0, 0.5, 0), 6, 0, 0, 0, 6, null, true);
                for (Player rawPlayer : Bukkit.getOnlinePlayers()){
                    SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(rawPlayer);
                    boolean isInRange = false;
                    switch (gate){
                        case 0,2,5 -> isInRange = Tools.isInRange(entity.getLocation().getX() - 12, entity.getLocation().getX() + 12, player.getLocation().getX()) && Tools.isInRange(entity.getLocation().getZ() - 3, entity.getLocation().getZ() + 3, player.getLocation().getZ());
                        case 1,3 -> isInRange = Tools.isInRange(entity.getLocation().getX() - 3, entity.getLocation().getX() + 3, player.getLocation().getX()) && Tools.isInRange(entity.getLocation().getZ() - 12, entity.getLocation().getZ() + 12, player.getLocation().getZ());
                    }
                    if(isInRange)
                        player.setHealth(0, HealthChangeReason.Force);
                }
            }
        }.runTaskTimer(Main.getMain(),0,10);
    }

    @Override
    public String getName() {
        return "Goldor";
    }

    @Override
    public HashMap<ItemManager,Integer> getDrops(SkyblockPlayer player) {
        return null;
    }

    @Override
    public void updateNameTag() {

        entity.setCustomName("§cGoldor §a" + Tools.toShortNumber(health));
        ((CraftWither)entity).getBossBar().removeFlag(BarFlag.DARKEN_SKY);
        ((CraftWither)entity).getBossBar().removeAll();

    }

    @Override
    public void kill() {
        entity = null;
        try{
            attacks.cancel();
        }catch (Exception ignored){

        }


        for(SkyblockEntity e : SkyblockEntity.livingEntity.values())
            e.getEntity().setHealth(0);

    }

    @Override
    public void damage(double damage, SkyblockPlayer player) {
        if(isDone)
            health -= damage;
        F7Phase3.activePhase.hit();

    }

    @Override
    public boolean hasNoKB() {
        return true;
    }

    @Override
    public int getTrueDamage() {
        return 0;
    }
}
