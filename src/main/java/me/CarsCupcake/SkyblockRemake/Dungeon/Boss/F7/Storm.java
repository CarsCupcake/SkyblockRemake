package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.boss.BarFlag;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftWither;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Storm extends SkyblockEntity {
    private int health = 400000000;
    public boolean isCrushed = false;
    public int crushTimes = 0;
    private LivingEntity entity;

    private BukkitRunnable attacks;

    private BukkitRunnable spawnRunnable;
    private BukkitRunnable fireballs;

    @Override
    public int getMaxHealth() {
        return 810000000;
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

    @Override
    public void spawn(Location loc) {
        entity = loc.getWorld().spawn(loc, Wither.class, wither ->{
            wither.setCustomNameVisible(true);
            wither.setRemoveWhenFarAway(false);
            wither.addScoreboardTag("combatxp:5000");
        });
        SkyblockEntity.livingEntity.put(entity,this);
        Main.updateentitystats(entity);
        startLightningPhase();
        ((CraftWither)entity).setTarget((Player) Bukkit.getOnlinePlayers().toArray()[ new Random().nextInt(Bukkit.getOnlinePlayers().size())]);





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
                    entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_WITHER_SHOOT, 2, 1);



                }

            }


        };
        attacks.runTaskTimer(Main.getMain(), 0,2);

    }
    private void startLightningPhase(){
        entity.setAI(false);
        try
        {
            attacks.cancel();
        }catch (Exception ignored){

        }

        if(new Random().nextBoolean())
            moveToLoc(new Location(entity.getWorld(), 100.5,188,53.5), 1,0.6);
        else
            moveToLoc(new Location(entity.getWorld(), 46.5,188,53.5), 1,0.6);

        startFireBalls();



    }
    private void startFireBalls(){

        fireballs = new BukkitRunnable() {
            @Override
            public void run() {
                Player player = (Player) Bukkit.getOnlinePlayers().toArray()[ new Random().nextInt(Bukkit.getOnlinePlayers().size())];
                Location lo = entity.getLocation().setDirection(player.getLocation().subtract(entity.getLocation()).toVector());
                lo.getWorld().spawn(lo, Fireball.class, fireball -> fireball.addScoreboardTag("storm"));
            }
        };
        fireballs.runTaskTimer(Main.getMain(), 20,40);


    }
    private void moveToLoc(Location loc, int nextMove, double speed){
        Location lo = entity.getLocation().setDirection(loc.clone().subtract(entity.getLocation()).toVector()) ;

        entity.teleport(lo);
        entity.setAI(false);

        new BukkitRunnable() {

            @Override
            public void run() {
                //Face to target Position player

                Vector dir = entity.getLocation().getDirection();
                Location l = entity.getLocation();
                if(l.distance(loc) > speed) {
                    dir.multiply(speed);
                    l = l.add(dir);
                }
                else {
                    l.setX(loc.getX());
                    l.setY(loc.getY());
                    l.setZ(loc.getZ());
                }


                entity.teleport(l);
                if(l.distance(loc) < 0.1 && l.distance(loc) >-0.1)
                {


                    if(nextMove == 0) {
                        entity.setAI(true);
                        ((CraftWither)entity).setTarget((Player) Bukkit.getOnlinePlayers().toArray()[ new Random().nextInt(Bukkit.getOnlinePlayers().size())]);
                    }
                    if(nextMove == 1) {
                        moveToLoc(new Location(entity.getWorld(), 72.5,190,53.5), 2,0.2);
                    }
                    if(nextMove == 2) {
                        gigaLightning();
                    }
                    cancel();
                }


            }
        }.runTaskTimer(Main.getMain(), 1, 1);

    }
    private void gigaLightning(){
        fireballs.cancel();
        new BukkitRunnable() {
            private int i = 7;
            @Override
            public void run() {
                i--;
                if(i == 0) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        for (int i = 0; i < 11; i++)
                        p.getWorld().strikeLightningEffect(p.getLocation());
                    }
                    cancel();
                    phaseBridge();
                    entity.setAI(true);((CraftWither)entity).setTarget((Player) Bukkit.getOnlinePlayers().toArray()[ new Random().nextInt(Bukkit.getOnlinePlayers().size())]);

                }
                else
                    for (Player p : Bukkit.getOnlinePlayers())
                        p.sendTitle("§4" + i , "" , 0 , 20 , 0);

            }
        }.runTaskTimer(Main.getMain(), 0,20);
    }

    public void setCrusheds(){
        crushTimes++;
        isCrushed = true;
        entity.setAI(false);
        try{
            attacks.cancel();
        }catch(Exception ignored){

        }
        new BukkitRunnable() {
            @Override
            public void run() {
                isCrushed =false;
                if(entity != null) {
                    entity.setAI(true);
                    F7Phase1.instance.respawnAll();
                    phaseBridge();

                }else if(F7Phase1.instance != null)
                    F7Phase1.instance.removeAll();

            }
        }.runTaskLater(Main.getMain(),5*20);
    }

    @Override
    public String getName() {
        return "Storm";
    }

    @Override
    public HashMap<ItemManager,Integer> getDrops(SkyblockPlayer player) {
        return null;
    }

    @Override
    public void updateNameTag() {
        entity.setCustomName("§cStorm §a" + Tools.toShortNumber(health));
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
            spawnRunnable.cancel();
        }catch (Exception ignored){

        }
    }

    @Override
    public void damage(double damage, SkyblockPlayer player) {
        health -= damage;
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
