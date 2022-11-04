package me.CarsCupcake.SkyblockRemake.cmd;

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
import me.CarsCupcake.SkyblockRemake.Dungeon.MagicalMapGeneration;
import me.CarsCupcake.SkyblockRemake.Items.Items;

public class generateMap implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		
		if(arg2.equalsIgnoreCase("generate") && arg0 instanceof Player) {
			Player player = (Player) arg0;
			ItemStack item = Main.item_updater(Items.MagicalMap(), SkyblockPlayer.getSkyblockPlayer(player));
			MagicalMapGeneration render = new MagicalMapGeneration();
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
