package me.CarsCupcake.SkyblockRemake.abilitys;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;

public class PlayMusic implements AbilityManager {

	@Override
	public boolean executeAbility(PlayerInteractEvent event) {
		
		return false;
	}

	@Override
	public boolean executeAbility(EntityDamageByEntityEvent event) {

		return false;
	}

}
