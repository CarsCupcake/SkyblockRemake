package me.CarsCupcake.SkyblockRemake.AccessoryBag.Powers;

import java.util.ArrayList;
import java.util.HashMap;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Stats;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.Items;

public interface Powers {
	public static HashMap<String, Powers> powers = new HashMap<>();
	public static HashMap<Player, Powers> activepower = new HashMap<>();
	HashMap<Player,ArrayList<Powers>> obitained = new HashMap<>();
	
	public PowerLevelPackage getPackage();
	public String getName();
	public ItemStack getItem();
	public ItemManager getManager();
	public default int CalculateStats(Stats stat, Player p) {
		SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(p);
		double statval = 0;
		if(getPackage().stats.get(stat) != null)
		if(getPackage().stats.get(stat) == 0) {
			if(getPackage().basestats.containsKey(stat) && getPackage().basestats.get(stat) != 0) {
			
				statval = getPackage().basestats.get(stat);}
				
			else
		return 0;
	}else {
		if(getPackage().basestats.containsKey(stat) && getPackage().basestats.get(stat) != 0) {
			
			statval = getPackage().basestats.get(stat);
			}
	}
		
		double multipl = Math.pow(29.97*(Math.log(0.0019*player.getMagicalpower() + 1 )), 1.2);
		
		if(getPackage().stats.get(stat) != null)
		 statval += getPackage().stats.get(stat) * multipl;
		
		return (int) statval;
		
	}
	public static void initPower(Player player) {
		obitained.put(player, new ArrayList<>());
	}
	public default void addObitained(Player player) {
		ArrayList<Powers> pows = obitained.get(player);
		if(!pows.contains(this))
		pows.add(this);
		obitained.replace(player, pows);
	}
	public default void removeObitained(Player player) {
		obitained.remove(player);
	}
	public default void removeObitainedPower(Player player, Powers pow) {
		ArrayList<Powers> pows = obitained.get(player);
		pows.remove(pow);
		obitained.replace(player, pows);
	}
	public static ArrayList<Powers> getObitained(Player player) {
		return obitained.get(player);
	}
	public static void initPowers(Powers power) {
		powers.put(power.getName(), power);
		
	}
	public default void setActive(Player player) {
		activepower.put(player, this);
	}
	public static void removeActive(Player player) {
		activepower.remove(player);
	}
	public static void  initStones() {
		powers.values().forEach(power->{
			if(power.getManager() != null)
				Items.SkyblockItems.put(power.getManager().itemID, power.getManager());
		});
	}
	
}
