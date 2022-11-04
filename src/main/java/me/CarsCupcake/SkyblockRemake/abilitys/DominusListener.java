package me.CarsCupcake.SkyblockRemake.abilitys;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

public class DominusListener implements Listener{
	@EventHandler
	public void entityDeath(EntityDeathEvent event) {
		Dominus.getKillEvent(event);
	}
	
	@EventHandler
	public void entityHit(ProjectileHitEvent event) {

		HydraStrike.getEntityKill(event);
	}
	
}
