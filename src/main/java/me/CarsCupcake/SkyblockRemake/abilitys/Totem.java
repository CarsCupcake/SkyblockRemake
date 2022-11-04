package me.CarsCupcake.SkyblockRemake.abilitys;

import it.unimi.dsi.fastutil.Hash;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Corruptable;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Totem {
    private static final HashMap<SkyblockPlayer, Totem> totems = new HashMap<>();
    private final SkyblockPlayer player;
    private final Block location;
    private final BukkitRunnable runnable;
    private ArmorStand time;
    private ArmorStand name;
    private ArmorStand head;
    public Totem(SkyblockPlayer owner, Block block){
        if(totems.containsKey(owner))
            totems.get(owner).remove();
        player = owner;
        location = block;
        totems.put(player, this);
        Location loc = block.getLocation();

        loc = loc.add(0.5, 0, 0.5);
        name = loc.getWorld().spawn(loc, ArmorStand.class, s ->{
            s.setCustomNameVisible(true);
            s.setCustomName("§7Owner: §e" + player.getName());
            s.setInvisible(true);
            s.setGravity(false);
            s.setInvulnerable(true);
        });
        loc.add(0,0.25,0);
        time = loc.getWorld().spawn(loc, ArmorStand.class, s ->{
            s.setCustomNameVisible(true);
            s.setCustomName("§7Remaining: §e2m 00s");
            s.setInvisible(true);
            s.setGravity(false);
            s.setInvulnerable(true);
        });
        loc.add(0,0.25,0);
        head = loc.getWorld().spawn(loc, ArmorStand.class, s ->{
            s.setCustomNameVisible(true);
            s.setCustomName("§5§lTotem of Corruption");
            s.setInvisible(true);
            s.setGravity(false);
            s.setInvulnerable(true);
        });

        runnable = new BukkitRunnable() {
            int i = 120*20;
            @Override
            public void run() {
                if(i <= 0) {
                    remove();
                }
                if(i % 20 == 0){
                    updateTime(i);
                }
                if(i % 5 == 0)
                    tick();

                i--;

            }

            @Override
            public synchronized void cancel() throws IllegalStateException {
                Bukkit.getScheduler().cancelTask(this.getTaskId());
                totems.remove(player);
                name.remove();
                time.remove();
                head.remove();
            }
        };
        runnable.runTaskTimer(Main.getMain(), 0, 1);
    }

    public void remove(){
        location.setType(Material.AIR);
        try {
            runnable.cancel();
        }catch (Exception ignored){

        }
    }
    private void tick(){
        for(Entity entity : location.getWorld().getNearbyEntities(location.getLocation(), 15,15,15))
            if(entity instanceof LivingEntity && SkyblockEntity.livingEntity.containsKey((LivingEntity) entity))
                if(SkyblockEntity.livingEntity.get((LivingEntity) entity) instanceof Corruptable){
                    Corruptable corruptable = (Corruptable) (SkyblockEntity.livingEntity.get((LivingEntity) entity));
                    if(!corruptable.isCorrupted())
                        corruptable.corrupt();
                }



    }
    private void updateTime(int i){
        int duration = i / 20;
        String string = "";
        int days = 0;
        int hours = 0;
        int minutes = 0;
        int seconds = 0;
        if (duration / 60 / 60 / 24 >= 1) {
            days = duration / 60 / 60 / 24;
            duration -= duration / 60 / 60 / 24 * 60 * 60 * 24;
        }
        if (duration / 60 / 60 >= 1) {
            hours = duration / 60 / 60;
            duration -= duration / 60 / 60 * 60 * 60;
        }
        if (duration / 60 >= 1) {
            minutes = duration / 60;
            duration -= duration / 60 * 60;
        }
        if (duration >= 1)
            seconds = duration;

        if(days != 0)
         if (days <= 9) {
            string = String.valueOf(string)  + days+ "d ";
         } else {
             string = String.valueOf(string) + days + "d ";}
        if(hours != 0)
         if (hours <= 9) {
            string = String.valueOf(string)  + hours + "h ";
         } else {
            string = String.valueOf(string) + hours + "h ";
         }
        if(minutes != 0)
         if (minutes <= 9) {
            string = String.valueOf(string)  + minutes + "m ";
         } else {
            string = String.valueOf(string) + minutes + "m ";
         }
        if (seconds <= 9) {
            string = String.valueOf(string)  + "0" + seconds + "s";
        } else {
            string = String.valueOf(string) + seconds + "s";
        }
        time.setCustomName("§7Remaining: §e"+ string);
    }
    public static void stopAll(){
        for (Totem totem : ((HashMap<SkyblockPlayer, Totem>)totems.clone()).values())
            totem.remove();
    }
}
