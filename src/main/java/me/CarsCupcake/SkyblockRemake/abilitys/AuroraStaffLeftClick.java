package me.CarsCupcake.SkyblockRemake.abilitys;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class AuroraStaffLeftClick implements AbilityManager<PlayerInteractEvent> {

	@Override
	public boolean executeAbility(PlayerInteractEvent event) {
		SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
		ArcaneRune rune = ArcaneRune.getPlayerRune(player).nextRune(player);
		
		String colorprefix = "";
		switch(rune) {
		case Defender:
			colorprefix = "§3";
			break;
		case Mediator:
			colorprefix = "§4";
			break;
		case Virtuoso:
			colorprefix = "§5";
			break;
		default:
			break;
		
		}
		
		player.sendMessage("§aSwappet rune to the " +colorprefix + rune + " §arune.");
		return false;
	}
}
