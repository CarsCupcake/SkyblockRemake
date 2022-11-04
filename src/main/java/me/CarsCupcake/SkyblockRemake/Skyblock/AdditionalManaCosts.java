package me.CarsCupcake.SkyblockRemake.Skyblock;

import org.bukkit.scheduler.BukkitRunnable;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;

public class AdditionalManaCosts {
public final SkyblockPlayer player;
public final String itemID;
public int amount;

private final int removeAmount;
private final int removeAfter;
private BukkitRunnable run;
public AdditionalManaCosts(SkyblockPlayer player, String itemID, int amount, int removeAfter, int removeAmount) {
	this.player = player;
	this.itemID = itemID;
	this.amount = amount;
	this.removeAfter = removeAfter;
	this.removeAmount = removeAmount;
	if(removeAfter > 0) {
		startRemove();
	}
}
public void setAmount(int i) {
	amount = i;
	try {
		run.cancel();
	} catch (Exception e) {
		// TODO: handle exception
	}
	startRemove();
}
public void addAmount(int i) {
	try {
		run.cancel();
	} catch (Exception e) {
		// TODO: handle exception
	}
	amount += i;
	startRemove();
}
public void resetTimer() {
	run.cancel();
	startRemove();
}

private void startRemove() {

	run = new BukkitRunnable() {
		
		@Override
		public void run() {
			if(removeAmount == -1)
				amount = 0;
			else
			amount -= removeAmount;
			if(amount <= 0) {
				AbilityManager.additionalMana.get(player).remove(itemID);

				}
			else
				startRemove();
			
		}
	};
	run.runTaskLater(Main.getMain(), removeAfter);
}


}
