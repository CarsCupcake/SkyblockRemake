package me.CarsCupcake.SkyblockRemake.cmd;



import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Generator;
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
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.DungeonWorld;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import org.jetbrains.annotations.NotNull;

public class DungeonCMD implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender arg0, @NotNull Command arg1, @NotNull String arg2, String[] arg3) {
		Player player = (Player) arg0;
		
		ItemStack item = Main.item_updater(Items.MagicalMap(), SkyblockPlayer.getSkyblockPlayer(player));
		Generator render = new Generator();
		MapMeta meta = (MapMeta) item.getItemMeta();
		MapView view = Bukkit.createMap(player.getWorld());
		
		view.getRenderers().clear();
		view.addRenderer(render);
		view.setCenterX(-104);
		view.setCenterZ(-104);
		meta.setMapView(view);
		item.setItemMeta(meta);
		player.getInventory().addItem(item);
		
		new DungeonWorld();
		return false;
	}

}
