package me.CarsCupcake.SkyblockRemake.Commission;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

import me.CarsCupcake.SkyblockRemake.Skills;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class CommissionListenders implements Listener{
@EventHandler
public void InvClickEvent(InventoryClickEvent event) {
	if (!event.getView().getTitle().contains("Commissions"))
		  return;
	 event.setCancelled(true);
	  if (event.getCurrentItem() == null)
		  return;
	  if (event.getCurrentItem().getItemMeta() == null)
		  return;
	  
	  SkyblockPlayer player =SkyblockPlayer.getSkyblockPlayer((Player) event.getWhoClicked());
	  
	  if(player.commissionsSlots == 2) {
		  if(event.getSlot() == 11 && player.Comms.get(0).isDone()) {
			  player.sendMessage("§a§lCommission Completet");
			  player.Comms.set(0, Commission.generateNewConsistentCommision());
			  player.openInventory(CommissionInv.getInv(player));
			  player.setMithrilpowder(player.mithrilpowder + 400);
			  player.addSkillXp(8000, Skills.Mining);
		  }
		  if(event.getSlot() == 15 && player.Comms.get(1).isDone()) {
			  player.sendMessage("§a§lCommission Completet");
			  player.Comms.set(1, Commission.generateNewSituationCommision());
			  player.openInventory(CommissionInv.getInv(player));
			  player.addSkillXp(8000, Skills.Mining);
		  }
	  }
	  
	  
	  if(event.getClickedInventory().getType() == InventoryType.PLAYER)
		  return;
}
@EventHandler
public void killIceWalker(EntityDeathEvent event) {

		if(event.getEntity().getKiller() == null && event.getEntity().getScoreboardTags().contains("id:IceWalker")) {
			for(String str : event.getEntity().getScoreboardTags())
				if(str.startsWith("killer")) {
					SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(Bukkit.getPlayer(str.split(":")[1]));

					for(Commission com : player.Comms) {
						if(com.getComm() == DwarvenCommissions.IceWalkerSlayer) {
							com.adOne();
						}
					}
					}
		}
	
}
}
