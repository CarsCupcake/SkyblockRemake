package me.CarsCupcake.SkyblockRemake.reforges;

import java.math.BigDecimal;
import java.util.ArrayList;

import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Stats;

public interface Reforge {
	 ReforgeStatPackage CommonStats();
	 ReforgeStatPackage UncommonStats();
	 ReforgeStatPackage RareStats();
	 ReforgeStatPackage EpicStats();
	 ReforgeStatPackage LegendaryStats();
	 ReforgeStatPackage MythicStats();
	 ReforgeStatPackage DivineStats();
	 ItemType[] Reforgable();
	 String getName();
	 ArrayList<String> getLore();
	
	
	 static double getReforgeValue(Reforge reforge, ItemRarity rarity, String s) {
		 ReforgeStatPackage statPackage = AddReforges.getStatPackage(rarity, reforge);
		 if(s.equals("dmg")){
			 return statPackage.getDamage();
		 }else {
			 if(!s.equals("breakingpower")) {
				 Stats stat = Stats.getFromDataName(s);
				 return statPackage.getStat(stat);
			 }
		 }
		 return 0;
	}
	static float getReforgeAbilityDamageValue(Reforge reforge, ItemRarity rarity) {
		 ReforgeStatPackage statPackage = AddReforges.getStatPackage(rarity, reforge);
		 return BigDecimal.valueOf(statPackage.getStat(Stats.AbilityDamage)).floatValue();

	 }

}
