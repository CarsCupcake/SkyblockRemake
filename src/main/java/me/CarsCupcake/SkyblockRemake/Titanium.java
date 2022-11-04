package me.CarsCupcake.SkyblockRemake;

import org.bukkit.Location;
import org.bukkit.Material;

import org.bukkit.scheduler.BukkitRunnable;

public class Titanium {
	BukkitRunnable titaniumRunnable;
	private final Material before_block;
	private final Location loc;
public Titanium(Location loc, Material before_block) {
this.before_block = before_block;
this.loc = loc;
SkyblockRemakeEvents.TitaniumRegen.get(loc);
	titaniumRunnable = new BukkitRunnable() {
		
		@Override
		public void run() {
			loc.getBlock().setType(before_block);

		}
	};titaniumRunnable.runTaskLater(Main.getMain(), 20*60);
}

public void cancle() {
	titaniumRunnable.cancel();
}
public void resetTitaniumBlock() {
	titaniumRunnable.cancel();
	loc.getBlock().setType(before_block);
}

}
