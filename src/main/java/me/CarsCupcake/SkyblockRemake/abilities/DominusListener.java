package me.CarsCupcake.SkyblockRemake.abilities;

import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.SkyblockDamagePlayerToEntityExecuteEvent;
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

	@EventHandler
	public void skyblockDamage(SkyblockDamagePlayerToEntityExecuteEvent event) {
		if(event.getCalculator().isFerocity() || event.getCalculator().isMagic() || event.getCalculator().isOverload() || event.getCalculator().getProjectile() != null) return;
		if (!Dominus.playerDominus.containsKey(event.getPlayer().getPlayer())) return;
		Dominus dominus = Dominus.playerDominus.get(event.getPlayer().getPlayer());
		if (dominus.stacks == 10) dominus.dominusStrike(event.getEntity().getLocation(), event.getEntity());
	}
	
}
