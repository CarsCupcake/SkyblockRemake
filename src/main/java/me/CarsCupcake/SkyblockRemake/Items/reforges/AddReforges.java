package me.CarsCupcake.SkyblockRemake.Items.reforges;

import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
public class AddReforges {
public static ReforgeStatPackage getStatPackage(ItemRarity rarity, Reforge reforge){
		switch(rarity){
			case LEGENDARY, SPECIAL -> {
				return reforge.LegendaryStats();
			}
			case RARE -> {
				return reforge.RareStats();
			}
			case COMMON, UNDEFINED -> {
				return reforge.CommonStats();
			}
			case EPIC -> {
				return reforge.EpicStats();
			}
			case UNCOMMON -> {
				return reforge.UncommonStats();
			}
			case DIVINE -> {
				return reforge.DivineStats();
			}
			case MYTHIC, SUPREME, VERY_SPECIAL -> {
				return reforge.MythicStats();
			}
			default -> throw new NullPointerException("Rarity is null");
		}

}

}
