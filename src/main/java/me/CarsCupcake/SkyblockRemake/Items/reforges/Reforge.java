package me.CarsCupcake.SkyblockRemake.Items.reforges;

import java.util.ArrayList;

import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;

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
	 default double getReforgeValue(ItemRarity rarity, Stats s) {
		 ReforgeStatPackage statPackage = AddReforges.getStatPackage(rarity, this);
		 return statPackage.getStat(s);
	}

}
