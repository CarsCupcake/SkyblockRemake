package me.CarsCupcake.SkyblockRemake.cmd.impl.test;

import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Generator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import org.jetbrains.annotations.NotNull;

public class generateMap implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender arg0, @NotNull Command arg1, String arg2, String[] arg3) {
		
		if(arg2.equalsIgnoreCase("generate") && arg0 instanceof Player player) {
			ItemStack item = Main.itemUpdater(Items.MagicalMap(), SkyblockPlayer.getSkyblockPlayer(player));
			Generator render = new Generator();
			render.generateMap(player.getWorld());
			MapMeta meta = (MapMeta) item.getItemMeta();
			MapView view = Bukkit.createMap(player.getWorld());
			
			view.getRenderers().clear();
			view.addRenderer(render);
			view.setCenterX(-104);
			view.setCenterZ(-104);
			meta.setMapView(view);
			item.setItemMeta(meta);
			player.getInventory().addItem(item);
		}
		
		// TODO Auto-generated method stub
		return false;
	}

}
