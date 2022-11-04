package me.CarsCupcake.SkyblockRemake.Areas;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockScoreboard;

public class AreaListender implements Listener{

	
@EventHandler
public void movement(PlayerMoveEvent event) {
	if(Main.getMain().getServer().getPort() == 25564 || !Main.isLocalHost) {
		
		DwarvenAreas oldarea = SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).dwarvenArea;
		SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).setDwarvenArea(DwarvenAreas.movement(event));

		if(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).dwarvenArea != oldarea)
			SkyblockScoreboard.updateScoreboard(event.getPlayer());
	}
}
}
