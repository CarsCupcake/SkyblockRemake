package me.CarsCupcake.SkyblockRemake.reforges;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Stats;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.math.BigDecimal;
import java.util.HashMap;

public class ReforgeStatPackage {
	private final HashMap<Stats, Double> stats;
	private double damage;
	public ReforgeStatPackage(){
		stats  = new HashMap<>();
	}
	public ReforgeStatPackage(HashMap<Stats, Double> stats){
		this.stats = stats;

	}
	public void addStats(Stats stat, double value){
		stats.put(stat, value);
	}
	public void addStats(Stats stat, int value){
		stats.put(stat,(double) value);
	}
	public void setDamage(double damage){
		this.damage = damage;
	}
	public double getDamage(){
		return damage;
	}
	public ItemStack applyPackage(ItemStack item){
		ItemMeta meta = item.getItemMeta();
		PersistentDataContainer pdc = meta.getPersistentDataContainer();

		for (Stats stat : stats.keySet()) {
			if(stat == Stats.AbilityDamage){
				BigDecimal v = BigDecimal.valueOf(stats.get(stat));
				if(!pdc.has(stat.getKey(), PersistentDataType.FLOAT))
				{
					pdc.set(stat.getKey(), PersistentDataType.FLOAT, v.floatValue());
				}else {
					pdc.set(stat.getKey(), PersistentDataType.FLOAT,pdc.get(stat.getKey(), PersistentDataType.FLOAT)  + v.floatValue());
				}
			}else {
				if(!pdc.has(stat.getKey(), PersistentDataType.DOUBLE)) {
					pdc.set(stat.getKey(), PersistentDataType.DOUBLE, stats.get(stat));
				}else {
					pdc.set(stat.getKey(), PersistentDataType.DOUBLE,pdc.get(stat.getKey(), PersistentDataType.DOUBLE)  + stats.get(stat));
				}
			}
		}
		if(damage != 0){
			if(pdc.has(new NamespacedKey(Main.getMain(), "dmg"), PersistentDataType.STRING)){
				double val = Main.weapondamage(item) + damage;
				pdc.set(new NamespacedKey(Main.getMain(), "dmg"), PersistentDataType.STRING, val + "");
			}else
				pdc.set(new NamespacedKey(Main.getMain(), "dmg"), PersistentDataType.STRING, damage + "");
		}


		item.setItemMeta(meta);
		return item;
	}
	public ItemStack removePackage(ItemStack item){
		ItemMeta meta = item.getItemMeta();
		PersistentDataContainer pdc = meta.getPersistentDataContainer();

		for (Stats stat : stats.keySet()) {
			if(stat == Stats.AbilityDamage){
				BigDecimal v = BigDecimal.valueOf(stats.get(stat));
				
				pdc.set(stat.getKey(), PersistentDataType.FLOAT,pdc.get(stat.getKey(), PersistentDataType.FLOAT)  - v.floatValue());
				
			}else {
				pdc.set(stat.getKey(), PersistentDataType.DOUBLE,pdc.get(stat.getKey(), PersistentDataType.DOUBLE)  - stats.get(stat));
				
			}
		}
		if(damage != 0){
				double val = Main.weapondamage(item) - damage;
				pdc.set(new NamespacedKey(Main.getMain(), "dmg"), PersistentDataType.STRING, val + "");
			
		}


		item.setItemMeta(meta);
		return item;
	}
	public double getStat(Stats stat){
		if(hasStat(stat))
			return stats.get(stat);
		else
			return 0;
	}
	public boolean hasStat(Stats stat){
		return stats.containsKey(stat);
	}
}
