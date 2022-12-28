package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
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


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class Maxor extends SkyblockEntity {
    private int health = 100000000;

    public boolean isInLaser = false;
    public int laserTimes = 0;
    private LivingEntity entity;

    private BukkitRunnable attacks;
    private BukkitRunnable tntsummoner;
    private BukkitRunnable spawnRunnable;

    @Override
    public int getMaxHealth() {
        return 210000000;
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
        return 30000;
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
        phaseBridge();
        ((CraftWither)entity).setTarget((Player) Bukkit.getOnlinePlayers().toArray()[ new Random().nextInt(Bukkit.getOnlinePlayers().size())]);

        tntsummoner = new BukkitRunnable() {
            @Override
            public void run() {
                entity.getWorld().spawn(entity.getLocation(), TNTPrimed.class,tnt ->{
                    tnt.setFuseTicks(3*20);
                    tnt.addScoreboardTag("maxor");
                });
            }
        };
        tntsummoner.runTaskTimer(Main.getMain(),5*20,5*20);

        spawnRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                for (int i = 0; i < 4; i++)
                    new SpecialSpawnMob(Vector.getRandom().setY(0.3), WitherMiner.class,entity.getLocation());
            }
        };
        spawnRunnable.runTaskTimer(Main.getMain(),5*20,13*20);


    }





    private void phaseBridge() {

    attacks = new BukkitRunnable() {
        @Override
        public void run() {
            skillShoot();
        }
    };
    attacks.runTaskLater(Main.getMain(), (new Random().nextInt(5)+10) *20);


    }
    private void skillShoot() {
        entity.setAI(false);
    attacks = new BukkitRunnable() {
        private int runtime = 0;

        private int switchTarget = 0;
        private Player target = (Player) Bukkit.getOnlinePlayers().toArray()[ new Random().nextInt(Bukkit.getOnlinePlayers().size())];
        @Override
        public void run() {
            runtime++;
            switchTarget++;
            if (runtime > 20*2.5){
                cancel();
                entity.setAI(true);
                phaseBridge();
                if(target != null){
                    ((CraftWither)entity).setTarget(target);
                }
                return;
            }
            if(switchTarget == 5){
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
    attacks.runTaskTimer(Main.getMain(), 0,2);

    }
    public void setLaser(){
        laserTimes++;
        isInLaser = true;
        entity.setAI(false);
        try{
            attacks.cancel();
        }catch(Exception ignored){

        }
        new BukkitRunnable() {
            @Override
            public void run() {
                isInLaser=false;
                if(entity != null) {
                    entity.setAI(true);
                    F7Phase1.instance.respawnAll();
                    phaseBridge();
                    frenzy();
                }else if(F7Phase1.instance != null)
                    F7Phase1.instance.removeAll();

            }
        }.runTaskLater(Main.getMain(),5*20);
    }

    private void frenzy() {
        new BukkitRunnable() {
            private int runrime = 0;
            @Override
            public void run() {
                runrime++;
                if(runrime == 11){
                    cancel();
                    return;
                }
                entity.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, entity.getLocation().add(0, 0.5, 0), 6, 0, 0, 0, 6, null, true);
                for(Player p : Bukkit.getOnlinePlayers())
                    p.playSound(p.getLocation(), Sound.ENTITY_WITHER_HURT, 1, 1);
                List<Entity> entities = entity.getNearbyEntities(10,10,10).stream().filter(entity -> entity instanceof Player).toList();
                if(!entities.isEmpty()){
                    for(Entity entity : entities){
                        Player player = (Player) entity;
                        player.damage(0.1);


                        Calculator calc = new Calculator();
                        calc.entityToPlayerDamage(Maxor.this, SkyblockPlayer.getSkyblockPlayer(player));
                        calc.damagePlayer(SkyblockPlayer.getSkyblockPlayer(player));


                    }
                }
            }
        }.runTaskTimer(Main.getMain(),0,10);
    }

    @Override
    public String getName() {
        return "Maxor";
    }

    @Override
    public HashMap<ItemManager,Integer> getDrops(SkyblockPlayer player) {
        return null;
    }

    @Override
    public void updateNameTag() {

        entity.setCustomName("§cMaxor §a" + Tools.toShortNumber(health));
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
        try{
            tntsummoner.cancel();
        }catch (Exception ignored){

        }
        try{
            spawnRunnable.cancel();
        }catch (Exception ignored){

        }


        for(SkyblockEntity e : SkyblockEntity.livingEntity.values())
            e.getEntity().setHealth(0);

    }

    @Override
    public void damage(double damage, SkyblockPlayer player) {
        if(isInLaser) {
            if (laserTimes > 1)
                health -= damage;
            else if (health - damage < 25000000)
                health = 25000000;
            else
                health -= damage;
        }


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
