package me.CarsCupcake.SkyblockRemake.abilitys;

import org.bukkit.Sound;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import me.CarsCupcake.SkyblockRemake.Commission.CommissionInv;
import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class SeekTheKing implements AbilityManager<PlayerInteractEvent>{

	@Override
	public boolean executeAbility(PlayerInteractEvent event) {
		
		event.setCancelled(true);
		SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
		player.playSound(player.getLocation(), Sound.ENTITY_BAT_AMBIENT, 1, (float) 0.4);
		player.openInventory(CommissionInv.getInv(player));
		
		return false;
	}



}
