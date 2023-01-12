package me.CarsCupcake.SkyblockRemake.FishingSystem;

import me.CarsCupcake.SkyblockRemake.FishingSystem.FishingLoottables.LavaFishing;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftFishHook;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Random;

public class LavaFishingHook implements Listener {
    private static final HashMap<FishHook, LavaFishingHook> hooks = new HashMap<>();
    private final FishHook hook;
    private final SkyblockPlayer player;
    private BukkitRunnable lavaRunnable;
    private boolean hookReady = false;
    public LavaFishingHook(FishHook hook, SkyblockPlayer player, int catchTicks){
        this.hook = hook;
        this.player = player;
        lavaRunnable = new BukkitRunnable() {
            public int catchTime = catchTicks;
            private boolean inLava = false;
            @Override
            public void run() {
                hook.setVisualFire(false);
                hook.setFireTicks(0);

                if(hook.getLocation().getBlock().getType() == Material.WATER) {
                    hook.remove();
                    cancel();
                }
                if(hook.getLocation().getBlock().getType() == Material.LAVA){
                    /*hook.teleport(new Location(hook.getWorld(),
                            hook.getLocation().getZ(),
                            hook.getLocation().getBlock().getY()+ 0.9,
                            hook.getLocation().getZ()));*/
                    int y = hook.getLocation().getBlockY();
                    double space = hook.getLocation().getY() - y;
                    if(space < 0.85)
                      hook.setVelocity(new Vector(0,0.05,0));
                    hook.setGravity(false);

                    catchTime--;
                    inLava = true;

                }else {
                    if(inLava)
                        catchTime--;
                }
                if(catchTime <= 0)
                    startAnimation();
                if(hook.isDead()){
                    cancel();
                }


            }
        };
        lavaRunnable.runTaskTimer(Main.getMain(), 0, 1);
        hooks.put(hook, this);
    }
    public void remove(){
        hooks.remove(hook);
        try{
            lavaRunnable.cancel();

        }catch (Exception ignored){

        }
    }
    public void startAnimation(){
        int low = -1;
        int high = 3;
        Random r = new Random();
        double x = r.nextDouble();
        double z = r.nextDouble();
        if(x == 0)
            x = -1;
        if(z == 0)
            z = -1;
        x *= 4;
        z *= 4;
        final double FINAL_X = x;
        final double FINAL_Z = z;
        final Particle.DustOptions dust = new Particle.DustOptions(
                Color.fromRGB(0x2d2d2e),2);
        lavaRunnable.cancel();
        lavaRunnable = new BukkitRunnable() {
            private boolean isDone = false;
            private Location location = new Location(hook.getWorld(),hook.getLocation().getX() + (r.nextInt(high-low)+ low + FINAL_X) , hook.getLocation().getY(), hook.getLocation().getZ() + (r.nextInt(high-low)+ low + FINAL_Z));
            private int hooktime = 20;

            @Override
            public void run() {
                hook.setVisualFire(false);
                hook.setFireTicks(0);

                if(hook.getLocation().getBlock().getType() == Material.WATER) {
                    hook.remove();
                    cancel();
                }
                if(!isDone){
                    location = location.setDirection(hook.getLocation().clone().subtract(location.clone()).toVector());
                    Vector dir = location.getDirection();
                    dir = dir.multiply(0.1);
                    location = location.add(dir);

                    location.getWorld().spawnParticle(Particle.REDSTONE, location,1, dust);

                    if(hook.getLocation().distance(location) <= 0.3){
                        isDone = true;
                        hookReady = true;
                    }
                }else {
                    hookReady = hooktime > -1;
                    hooktime--;
                }
                if(hook.getLocation().getBlock().getType() == Material.LAVA){
                    int y = hook.getLocation().getBlockY();
                    double space = hook.getLocation().getY() - y;
                    if((space < 0.85 && !hookReady) || (space < 0.6 && hookReady))
                        hook.setVelocity(new Vector(0,0.05,0));




                }
                if(hook.isDead()){
                    cancel();
                }


            }
        };
        lavaRunnable.runTaskTimer(Main.getMain(), 0, 1);
    }
    public void reelIn(){
        hooks.remove(hook);
        try{
            lavaRunnable.cancel();
        }catch (Exception ignored){

        }
        if(hookReady){
            double seaCreatureChance = Main.playerseacreaturechance(player) / 100;
            if(seaCreatureChance > new Random().nextDouble()){
                new LavaFishing().summonSeaCreature(player, hook.getLocation().add(0,0.5,0),
                        new Vector(player.getEyeLocation().getX() - hook.getLocation().getX(), (player.getEyeLocation().getY() - hook.getLocation().getY()) - 0.5, player.getEyeLocation().getZ() - hook.getLocation().getZ()).multiply(0.15));
            }else{
                Location spawnLoc = hook.getLocation().add(0,0.5, 0);
                ItemStack stack =  new LavaFishing().getDrop(player);

                if(stack.getType() != Material.AIR)
                {
                    Entity entity = spawnLoc.getWorld().dropItemNaturally(spawnLoc, stack);
                    entity.setVelocity(new Vector(player.getEyeLocation().getX() - hook.getLocation().getX(), (player.getEyeLocation().getY() - hook.getLocation().getY()) - 0.5, player.getEyeLocation().getZ() - hook.getLocation().getZ()).multiply(0.15));
                }
            }


        }
    }
    public static LavaFishingHook get(FishHook hook){
        return hooks.get(hook);
    }
    public static boolean contains(FishHook hook){
        return hooks.containsKey(hook);
    }
}
