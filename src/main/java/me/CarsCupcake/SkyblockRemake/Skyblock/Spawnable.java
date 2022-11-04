package me.CarsCupcake.SkyblockRemake.Skyblock;

import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Spawnable implements Listener {
    private BukkitRunnable runnable;
    private final HashMap<Integer, SkyblockEntity> entitys = new HashMap<>();
    private static final ArrayList<Spawnable> a = new ArrayList<>();
    public Spawnable(){
        init();
        a.add(this);
    }

    private void init(){
        runnable = new BukkitRunnable() {
            @Override
            public void run() {
                respawnMissing();
            }
        };
        runnable.runTaskTimer(Main.getMain(), 0, frequence());
    }
    public void stop(){
        try {
            runnable.cancel();
        }catch (Exception ignored){}
        for (SkyblockEntity entity : entitys.values())
            entity.getEntity().remove();
        entitys.clear();
    }
    private SkyblockEntity getNewEntity(){
        try {
            @SuppressWarnings("deprecation")
            SkyblockEntity e =  spawnEntity().newInstance();
            return e;
        } catch (Exception ignored) {}
        return null;
    }
    @EventHandler
    public void removeDead(EntityDeathEvent event) {
        if(!(event.getEntity() instanceof Player))
        {
            boolean isIn = false;
            for(SkyblockEntity entity : entitys.values()){
                if(entity.getEntity().equals(event.getEntity()))
                    isIn = true;
            }
            if(!isIn)
                return;
            for (String s : event.getEntity().getScoreboardTags()){
                if(s.startsWith("spawnId:")) {
                    int id = Integer.parseInt(s.split(":")[1]);
                    entitys.remove(id);
                }
            }
        }
    }
    private void respawnMissing(){
        for(int i = 0; i < getSpawnLocations().length; i++){
            if(!entitys.containsKey(i)) {
                SkyblockEntity entity = getNewEntity();
                entity.spawn(getSpawnLocations()[i]);
                entity.getEntity().addScoreboardTag("spawnId:" + i);
                entitys.put(i, entity);

            }
        }
    }
    public static void disable(){
        for (Spawnable spawnable : a){
            spawnable.stop();
        }
    }


    public abstract Location[] getSpawnLocations();
    public abstract long frequence();
    public abstract Class<? extends SkyblockEntity> spawnEntity();

}
