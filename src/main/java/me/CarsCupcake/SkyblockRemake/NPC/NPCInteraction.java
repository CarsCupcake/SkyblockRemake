package me.CarsCupcake.SkyblockRemake.NPC;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import me.CarsCupcake.SkyblockRemake.AccessoryBag.Powers.MaxwellInv;
import me.CarsCupcake.SkyblockRemake.Commission.CommissionInv;
import me.CarsCupcake.SkyblockRemake.Commission.Puzzler;
import me.CarsCupcake.SkyblockRemake.Drill.DrillMechanicInv;
import me.CarsCupcake.SkyblockRemake.Dungeon.MalikInventory;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;



public class NPCInteraction implements Listener{
@EventHandler
public void onInteract(RightClickNPC event) {
	
if(event.getNPC().getName().contains("Drill Guy")) {
		
		event.getPlayer().openInventory(DrillMechanicInv.drillMechInv());
		return;
	}
if(event.getNPC().getName().contains("Malik")) {
	
	event.getPlayer().openInventory(MalikInventory.Main());
	return;
}
if(event.getNPC().getName().contains("Maxwell")) {
	
	event.getPlayer().openInventory(MaxwellInv.getMainInventory(event.getPlayer()));
	return;
}
if(event.getNPC().getName().contains("Emissary")) {
	
	event.getPlayer().openInventory(CommissionInv.getInv(SkyblockPlayer.getSkyblockPlayer(event.getPlayer())));
	return;
}
if(event.getNPC().getName().contains("Puzzler")) {
	
	SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
	
	if(Puzzler.activePlayerQuest(player)) {
		player.sendMessage(Puzzler.getPuzzler(player).getString());
		Puzzler.getPuzzler(player).retry();
	}
	else
		player.sendMessage(new Puzzler(player).getString());
	
	
	return;
}

	/*Inventory inv = Bukkit.createInventory(null, 54, ChatColor.GRAY +  event.getNPC().getName());
	event.getPlayer().openInventory(inv);*/
	
}
}
