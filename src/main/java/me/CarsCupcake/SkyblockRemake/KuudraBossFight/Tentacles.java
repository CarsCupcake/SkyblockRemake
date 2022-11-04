package me.CarsCupcake.SkyblockRemake.KuudraBossFight;



import java.util.ArrayList;


import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Wither;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Items.CustomEntitys;

public class Tentacles {
	public static ArrayList<Tentacles> tentacles = new ArrayList<>();
	public Wither tentacle;
	public int hp;
	public Location startLocation;
	
	public Tentacles(Location loc, int tier) {
		tentacle = (Wither) CustomEntitys.KuudraTentacle(loc);
		
		if(tier > 0 && tier < 3) {
			if(tier == 1)
				hp = 270000;
			else
				hp = 300000;
		}else
			hp = 200;
		startLocation = loc;
		tentacles.add(this);
	}
	
	public static ArrayList<Tentacles> nearEntitys(Entity entity) {
		if(tentacles.isEmpty())
		return null;
		ArrayList<Tentacles> targets = new ArrayList<>();
		for(Tentacles t : tentacles) {
			if(entity.getWorld().getNearbyEntities(t.tentacle.getLocation(), 6, 6, 6).contains(entity)) {
				targets.add(t);
			}
		}
		System.out.println(targets);
		if(targets.size() != 0)
		return targets;
		else
			return null;
		
	}
	
	public void setHP(int newHP) {
		hp = newHP;
		if(hp <= 0) {
			tentacles.remove(this);
			tentacle.remove();
		}
	}
	public void removeTentakle() {
		tentacles.remove(this);
		tentacle.remove();
		Main.dinnerboneNametags.get(tentacle).remove();
	}
	
	public static void removeAllTentakle() {
		if(tentacles.isEmpty()) return;
		tentacles.forEach(t->{
			t.tentacle.remove();
			Main.dinnerboneNametags.get(t.tentacle).remove();
		});
		tentacles.clear();
	}
	

}
