package me.CarsCupcake.SkyblockRemake.cmd;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.reforges.ReforgeStones.Jaded;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.bukkit.persistence.PersistentDataType;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.reforges.AddReforges;
import me.CarsCupcake.SkyblockRemake.reforges.registerReforge;
import me.CarsCupcake.SkyblockRemake.reforges.ReforgeStones.Auspicious;
import me.CarsCupcake.SkyblockRemake.reforges.ReforgeStones.Loving;
import me.CarsCupcake.SkyblockRemake.reforges.ReforgeStones.Necrotic;
import me.CarsCupcake.SkyblockRemake.reforges.blacksmith.epic;
import me.CarsCupcake.SkyblockRemake.reforges.blacksmith.heroic;

public class AddReforge implements CommandExecutor{

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(!arg2.equalsIgnoreCase("reforge"))
			return false;
		Player player = (Player) arg0;
		
		if(arg3.length != 1 ) {
			return false;
		}
		if(Items.SkyblockItems.containsKey(player.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING)))
		switch(arg3[0]) {
		case "Epic":
			ItemRarity rarity = ItemRarity.valueOf(player.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "rarity"), PersistentDataType.STRING));
			player.setItemInHand(AddReforges.toItemStack(player.getItemInHand(), rarity, new epic()));
			 player.setItemInHand(Main.item_updater(player.getItemInHand(), SkyblockPlayer.getSkyblockPlayer(player)));
			 break;
		case "Heroic":
			 rarity = ItemRarity.valueOf(player.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "rarity"), PersistentDataType.STRING));
			player.setItemInHand(AddReforges.toItemStack(player.getItemInHand(), rarity, new heroic()));
			 player.setItemInHand(Main.item_updater(player.getItemInHand(), SkyblockPlayer.getSkyblockPlayer(player)));
			 break;
		case "Necrotic":
			 rarity = ItemRarity.valueOf(player.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "rarity"), PersistentDataType.STRING));
			player.setItemInHand(AddReforges.toItemStack(player.getItemInHand(), rarity, new Necrotic()));
			 player.setItemInHand(Main.item_updater(player.getItemInHand(), SkyblockPlayer.getSkyblockPlayer(player)));
			 break;
		case "Loving":
			 rarity = ItemRarity.valueOf(player.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "rarity"), PersistentDataType.STRING));
			player.setItemInHand(AddReforges.toItemStack(player.getItemInHand(), rarity, new Loving()));
			 player.setItemInHand(Main.item_updater(player.getItemInHand(), SkyblockPlayer.getSkyblockPlayer(player)));
			 break;
			case "Auspicious":
				rarity = ItemRarity.valueOf(player.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "rarity"), PersistentDataType.STRING));
				player.setItemInHand(AddReforges.toItemStack(player.getItemInHand(), rarity, new Auspicious()));
				player.setItemInHand(Main.item_updater(player.getItemInHand(), SkyblockPlayer.getSkyblockPlayer(player)));
				break;
			case "Jaded":
				rarity = ItemRarity.valueOf(player.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "rarity"), PersistentDataType.STRING));
				player.setItemInHand(AddReforges.toItemStack(player.getItemInHand(), rarity, new Jaded()));
				player.setItemInHand(Main.item_updater(player.getItemInHand(), SkyblockPlayer.getSkyblockPlayer(player)));
				break;
	  default:
		  rarity = ItemRarity.valueOf(player.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "rarity"), PersistentDataType.STRING));
		  if(player.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "reforge"), PersistentDataType.STRING) != null &&registerReforge.reforges.containsKey(player.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "reforge"), PersistentDataType.STRING)))
			player.setItemInHand(AddReforges.removeOldReforge(player.getItemInHand(), rarity, registerReforge.reforges.get(player.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "reforge"), PersistentDataType.STRING))));
		  player.sendMessage("Succesfully removed the reforge");
			 player.setItemInHand(Main.item_updater(player.getItemInHand(), SkyblockPlayer.getSkyblockPlayer(player)));
			
		}
		else {
			player.sendMessage("Item is invalid");
		}
		return false;
	}

}
