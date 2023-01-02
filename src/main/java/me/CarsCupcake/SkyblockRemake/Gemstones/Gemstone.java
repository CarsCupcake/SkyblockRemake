package me.CarsCupcake.SkyblockRemake.Gemstones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;

public class Gemstone extends ItemManager{
	public static HashMap<String, Gemstone> gemstones = new HashMap<>();
	public GemstoneType gemType;
	public GemState gemState;
	public Gemstone(String name,String id,ArrayList<String> lore, GemstoneType type, GemState state, String texture) {
		super(name, id, ItemType.Gemstone, state.getRarity(), texture, UUID.fromString("fbf14e46-d852-11ec-9d64-0242ac120002"));
		gemType = type;
		gemState = state;
		gemstones.put(id, this);
	}
	
	public int getStatBoost(ItemRarity target) {
		return gemType.getStats(target, gemState);
	}
	
	public double getDoubleStatBoost(ItemRarity target) {
		return gemType.getDoubleStats(target, gemState);
	}
	
public int statBoostRecom(ItemRarity oldrarity, ItemRarity newrarity) {
		
		return getStatBoost(newrarity) - getStatBoost(oldrarity);
		

	}
public double doublestatBoostRecom(ItemRarity oldrarity, ItemRarity newrarity) {
	
	return getDoubleStatBoost(newrarity) - getDoubleStatBoost(oldrarity);
	

}

}
