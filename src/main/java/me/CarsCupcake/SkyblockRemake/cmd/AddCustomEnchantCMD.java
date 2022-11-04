package me.CarsCupcake.SkyblockRemake.cmd;



import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import me.CarsCupcake.SkyblockRemake.Enchantments.SkyblockEnchants;


public class AddCustomEnchantCMD implements CommandExecutor{


	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		
	
		
		if(!arg2.equalsIgnoreCase("ench"))
			return false;
		SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer((Player) arg0);
		
		if(arg3.length != 2 ) {
			return false;
		}
		if(player.getItemInHand() == null)
			return false;

		if(SkyblockEnchants.skyblockEnchantIds.contains(arg3[0])){

			ItemMeta meta = player.getItemInHand().getItemMeta();
			meta.addEnchant(SkyblockEnchants.enchantments.get(SkyblockEnchants.skyblockEnchantIds.indexOf(arg3[0])), Integer.parseInt(arg3[1]), true);
			player.getItemInHand().setItemMeta(meta);
		}else
			player.sendMessage(arg3[0] + " dosent exist");
		
		return false;
	}

}
