package me.CarsCupcake.SkyblockRemake.Drill;

import java.util.ArrayList;
import java.util.HashMap;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;

public class DrillPart extends ItemManager{
	//only useful if ItemType = Drill Engine
	public int applyminingspeed;
	//only useful if ItemType = Fuel Tank
	public int applyfuelcap;
	//List of all parts by: ID | The Class
	public static HashMap<String, DrillPart> parts = new HashMap<>();
	
	//Lore on the dril when applied
	public ArrayList<String> appliedLore = new ArrayList<>();
	
	public DrillPart(String name, String id, ItemType type, ArrayList<String> lore, ArrayList<String> applyLore, ItemRarity rarity, String headTexture) {
		super(name, id, type,  rarity, headTexture);
		this.appliedLore = applyLore;
		parts.put(id, this);
	}
	//set The Apply Mining Speed
	public void SetApplyMiningSpeed(int speed) {
		applyminingspeed = speed;
	}
	//set the new fuel cap
public void setFuelCapacity(int fuel) {
	applyfuelcap = fuel;
}
}
