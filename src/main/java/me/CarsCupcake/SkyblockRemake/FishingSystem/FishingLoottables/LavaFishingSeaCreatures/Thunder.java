package me.CarsCupcake.SkyblockRemake.FishingSystem.FishingLoottables.LavaFishingSeaCreatures;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Corruptable;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Tools;
import net.minecraft.network.protocol.game.PacketPlayInBlockDig;
import net.minecraft.server.level.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class Thunder extends SkyblockEntity {
    private int health = 35000000;
    private LivingEntity entity;
    private BukkitRunnable phaseRunnable;
    private BukkitRunnable a;
    private BukkitRunnable b;
    private ArmorStand block;
    @Override
    public int getMaxHealth() {
        int maxHealth = 35000000;
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
        return 10000;
    }

    @Override
    public void spawn(Location loc) {
        WorldServer world = ((CraftWorld)loc.getWorld()).getHandle();
        ThunderMob e = new ThunderMob(world,loc);

        UUID id = e.getUniqueID();
        world.addEntity(e);
        entity = (LivingEntity) Bukkit.getEntity(id);
        entity.setCustomNameVisible(true);
        SkyblockEntity.livingEntity.put(entity, this);
        Main.updateentitystats(entity);

        phaseRunnable = new BukkitRunnable() {
            private boolean a = true;
            @Override
            public void run() {
                if(a)
                    a();
                else
                    b();
                a = !a;
            }
        };
        phaseRunnable.runTaskTimer(Main.getMain(), 20, 20*5);

    }

    private void a(){

        /*
        "Every tick (1⁄20 second), non-flying players and mobs have their vertical speed decremented (less upward motion, more downward motion) by 0.08 blocks per tick (1.6 m/s), then multiplied by 0.98."
         Friction is not on the page, but I know where to find it in the code so I could check it later.
         */
        boolean hasPlayer = false;
        ArrayList<Player> targets = new ArrayList<>();
        for (Entity e : entity.getNearbyEntities(8,8,8))
            if(e instanceof Player) {
                targets.add((Player) e);
                hasPlayer = true;
            }
        if(!hasPlayer)
            return;
        SkyblockPlayer target = SkyblockPlayer.getSkyblockPlayer(targets.get(new Random().nextInt(targets.size())));
        final Location targetLocation = target.getLocation().clone();
        final Vector startVector = target.getLocation().clone().subtract(entity.getLocation().clone()).toVector().multiply(0.1).add(new Vector(0,1,0));
        entity.setVelocity(startVector);


       /* a = new BukkitRunnable() {
            private int tick = -1;
            private Vector takeOver = startVector;
            private boolean isDone = false;


            @Override
            public void run() {




                //.add(new Vector(0, fallspeed, 0)
                if(!isDone)
                {
                    Vector teleportDirection = takeOver.clone().normalize().multiply(0.4);
                    Vector beforeTeleport = entity.getLocation().toVector().clone();
                    entity.teleport(entity.getLocation().add(teleportDirection));
                    takeOver = takeOver.subtract(beforeTeleport.subtract(entity.getLocation().toVector()));
                    if(entity.getLocation().distance(targetLocation.clone().add(0,5,0)) < 1){
                    isDone = true;
                }
                }else{
                    tick++;
                    double fallspeed = Tools.getFallSpeedFromTimeElapsed(tick);
                    entity.teleport(entity.getLocation().add(0,fallspeed,0));
                    if(entity.getLocation().distance(targetLocation) < 1){
                        cancel();
                        entity.setGravity(true);
                    }
                }


            }
        };
        a.runTaskTimer(Main.getMain(), 0, 1);*/

    }
    private void b(){

    }


    @Override
    public String getName() {
        return "Thunder";
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
        try {
            phaseRunnable.cancel();
        }catch (Exception ignored){}
        try {
            block.remove();
        }catch (Exception ignored){}
        try {
            a.cancel();
        }catch (Exception ignored){}
        try {
            b.cancel();
        }catch (Exception ignored){}


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
