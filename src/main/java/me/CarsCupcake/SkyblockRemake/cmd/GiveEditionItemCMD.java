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
import me.CarsCupcake.SkyblockRemake.Configs.EditionItems;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.Items;

public class GiveEditionItemCMD implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg0 instanceof Player && arg3.length == 2 && Bukkit.getPlayer(arg3[1]) != null) {
			
			if(arg3[0].equals("dctrspacehelmet")){
				Player player = Bukkit.getPlayer(arg3[1]);
				ItemManager manager = Items.SkyblockItems.get("DCTR_SPACE_HELM");
				ItemStack item = manager.createNewItemStack();
				player.sendMessage(arg0.getName() +  " §egave you a " + manager.rarity.getPrefix() + manager.name + " §7(§e#" + EditionItems.get().getInt(manager.itemID) +"§7)!");
				ItemMeta meta = item.getItemMeta();
				PersistentDataContainer data = meta.getPersistentDataContainer();
				data.set(new NamespacedKey(Main.getMain(), "to"), PersistentDataType.STRING, arg3[1]);
				data.set(new NamespacedKey(Main.getMain(), "from"), PersistentDataType.STRING, arg0.getName());
				item.setItemMeta(meta);
				player.getInventory().addItem(Main.item_updater(item, SkyblockPlayer.getSkyblockPlayer(player)));
			}
			if(arg3[0].equals( "kloonboat")) {
				Player player = Bukkit.getPlayer(arg3[1]);
				ItemManager manager = Items.SkyblockItems.get("KLOONBOAT");
				ItemStack item = manager.createNewItemStack();
				player.sendMessage(arg0.getName() +  " §egave you a " + manager.rarity.getPrefix() + manager.name + " §7(§e#" + EditionItems.get().getInt(manager.itemID) +"§7)!");
				ItemMeta meta = item.getItemMeta();
				PersistentDataContainer data = meta.getPersistentDataContainer();
				data.set(new NamespacedKey(Main.getMain(), "to"), PersistentDataType.STRING, arg3[1]);
				data.set(new NamespacedKey(Main.getMain(), "from"), PersistentDataType.STRING, arg0.getName());
				item.setItemMeta(meta);
				player.getInventory().addItem(Main.item_updater(item,SkyblockPlayer.getSkyblockPlayer(player)));
			}

			if(arg3[0].equals( "intellij")) {
				Player player = Bukkit.getPlayer(arg3[1]);
				ItemManager manager = Items.SkyblockItems.get("INTELLIJ");
				ItemStack item = manager.createNewItemStack();
				player.sendMessage(arg0.getName() +  " §egave you a " + manager.rarity.getPrefix() + manager.name + " §7(§e#" + EditionItems.get().getInt(manager.itemID) +"§7)!");
				ItemMeta meta = item.getItemMeta();
				PersistentDataContainer data = meta.getPersistentDataContainer();
				data.set(new NamespacedKey(Main.getMain(), "to"), PersistentDataType.STRING, arg3[1]);
				data.set(new NamespacedKey(Main.getMain(), "from"), PersistentDataType.STRING, arg0.getName());
				item.setItemMeta(meta);
				player.getInventory().addItem(Main.item_updater(item,SkyblockPlayer.getSkyblockPlayer(player)));
			}
			if(arg3[0].equals( "susflare")) {
				Player player = Bukkit.getPlayer(arg3[1]);
				ItemManager manager = Items.SkyblockItems.get("SUS_FLARE");
				ItemStack item = manager.createNewItemStack();
				player.sendMessage(arg0.getName() +  " §egave you a " + manager.rarity.getPrefix() + manager.name + " §7(§e#" + EditionItems.get().getInt(manager.itemID) +"§7)!");
				ItemMeta meta = item.getItemMeta();
				PersistentDataContainer data = meta.getPersistentDataContainer();
				data.set(new NamespacedKey(Main.getMain(), "to"), PersistentDataType.STRING, arg3[1]);
				data.set(new NamespacedKey(Main.getMain(), "from"), PersistentDataType.STRING, arg0.getName());
				item.setItemMeta(meta);
				player.getInventory().addItem(Main.item_updater(item,SkyblockPlayer.getSkyblockPlayer(player)));
			}
				
			
		}
		return false;
	}

}
