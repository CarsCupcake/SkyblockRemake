package me.CarsCupcake.SkyblockRemake.cmd;



import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.configs.EditionItems;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import org.jetbrains.annotations.NotNull;

public class GiveEditionItemCMD implements CommandExecutor {
	
	@Override
	public boolean onCommand(@NotNull CommandSender arg0, @NotNull Command arg1, @NotNull String arg2, String[] arg3) {
		if(arg0 instanceof Player && arg3.length == 2 && Bukkit.getPlayer(arg3[1]) != null) {

			Player player = Bukkit.getPlayer(arg3[1]);
			ItemManager manager = switch (arg3[0]) {
				case "dctrspacehelmet" -> Items.SkyblockItems.get("DCTR_SPACE_HELM");
				case "kloonboat" -> Items.SkyblockItems.get("KLOONBOAT");
				case "intellij" -> Items.SkyblockItems.get("INTELLIJ");
				case "susflare" -> Items.SkyblockItems.get("SUS_FLARE");
				case "racinghelmet" -> Items.SkyblockItems.get("RACING_HELMET");
				default -> throw new IllegalStateException("Unexpected value: " + arg3[0]);
			};
			ItemStack item = manager.createNewItemStack();
			player.sendMessage(arg0.getName() +  " §egave you a " + manager.getRarity().getPrefix() + manager.name + " §7(§e#" + EditionItems.get().getInt(manager.itemID) +"§7)!");
			ItemMeta meta = item.getItemMeta();
			PersistentDataContainer data = meta.getPersistentDataContainer();
			data.set(new NamespacedKey(Main.getMain(), "to"), PersistentDataType.STRING, arg3[1]);
			data.set(new NamespacedKey(Main.getMain(), "from"), PersistentDataType.STRING, arg0.getName());
			item.setItemMeta(meta);
			player.getInventory().addItem(Main.itemUpdater(item,SkyblockPlayer.getSkyblockPlayer(player)));
		}
		return false;
	}

}
