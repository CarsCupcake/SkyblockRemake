package me.CarsCupcake.SkyblockRemake.DwarvenMines;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Entitys.IceWalker;



public class IceWalkerSpawning implements Listener{
public static IceWalkerSpawning instance;
public HashMap<Integer, LivingEntity> naturalicewalkers = new HashMap<>();
private BukkitRunnable respawner;
public IceWalkerSpawning() {
	instance = this;
	spawning();
}

private Location getSpawnLocFromId(int id) {
	
	switch(id) {
	case 1:
		return new Location(Bukkit.getWorld("world"), -20, 128, 162);
	case 2:
		return new Location(Bukkit.getWorld("world"), -8, 128, 169);
	case 3:
		return new Location(Bukkit.getWorld("world"), 0, 128, 164);
	case 4:
		return new Location(Bukkit.getWorld("world"), 9, 128, 170);
	case 5:
		return new Location(Bukkit.getWorld("world"), 38, 132, 154);
	case 6:
		return new Location(Bukkit.getWorld("world"), 54, 136, 141);
	case 7:
		return new Location(Bukkit.getWorld("world"), 62, 143, 163);
	case 8:
		return new Location(Bukkit.getWorld("world"), 44, 143, 170);
	case 9:
		return new Location(Bukkit.getWorld("world"), 44, 143, 188);
		
	}
	
	
	return null;
}
public void cancleSpawn() {
	respawner.cancel();
}
private void spawning() {
	respawner = new BukkitRunnable() {
		
		@Override
		public void run() {
			respawnMissing();
			
		}
	};
	respawner.runTaskTimer(Main.getMain(), 0, 20*20);
}
public void respawnMissing() {
		for(int i = 1; i < 10; i++) {
			if(!naturalicewalkers.containsKey(i)) {
				Bukkit.getWorld("world").loadChunk(getSpawnLocFromId(i).getChunk());
				IceWalker w = new IceWalker();
				w.spawn(getSpawnLocFromId(i));
				LivingEntity e = w.getEntity();
			
				e.addScoreboardTag("spawnId:" + i);
				naturalicewalkers.put(i, e);
				e.getLocation().getWorld().loadChunk(e.getLocation().getChunk());
				
			}
		}
	}
@EventHandler
public void removeDead(EntityDeathEvent event) {
	if(event.getEntity() instanceof Player) {
		//do nothing
	}else
	{
		if(naturalicewalkers.containsValue(event.getEntity())) {
			for(String str: event.getEntity().getScoreboardTags()) {
				if(str.startsWith("spawnId:")) {
					int id = Integer.parseInt(str.split(":")[1]);
					naturalicewalkers.remove(id);
					
				}
			}
		}
	}
}


}
