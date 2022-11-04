package me.CarsCupcake.SkyblockRemake.reforges.ReforgeStones;

import java.util.ArrayList;

import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Stats;
import me.CarsCupcake.SkyblockRemake.reforges.Reforge;
import me.CarsCupcake.SkyblockRemake.reforges.ReforgeStatPackage;

public class Auspicious implements Reforge {

	@Override
	public ReforgeStatPackage CommonStats() {
		ReforgeStatPackage statPackage = new ReforgeStatPackage();
		statPackage.addStats(Stats.MiningSpeed, 7);
		return statPackage;
	}

	@Override
	public ReforgeStatPackage UncommonStats() {
		ReforgeStatPackage statPackage = new ReforgeStatPackage();
		statPackage.addStats(Stats.MiningSpeed, 14);
		return statPackage;
	}

	@Override
	public ReforgeStatPackage RareStats() {
		ReforgeStatPackage statPackage = new ReforgeStatPackage();
		statPackage.addStats(Stats.MiningSpeed, 23);
		return statPackage;
	}

	@Override
	public ReforgeStatPackage EpicStats() {
		ReforgeStatPackage statPackage = new ReforgeStatPackage();
		statPackage.addStats(Stats.MiningSpeed, 34);
		return statPackage;
	}

	@Override
	public ReforgeStatPackage LegendaryStats() {
		ReforgeStatPackage statPackage = new ReforgeStatPackage();
		statPackage.addStats(Stats.MiningSpeed, 45);
		return statPackage;
	}

	@Override
	public ReforgeStatPackage MythicStats() {
		ReforgeStatPackage statPackage = new ReforgeStatPackage();
		statPackage.addStats(Stats.MiningSpeed, 60);
		return statPackage;
	}

	@Override
	public ReforgeStatPackage DivineStats() {
		ReforgeStatPackage statPackage = new ReforgeStatPackage();
		statPackage.addStats(Stats.MiningSpeed, 75);
		return statPackage;
	}

	@Override
	public ItemType[] Reforgable() {
		ItemType[] types = {ItemType.Drill, ItemType.Pickaxe};
		return types;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Auspicious";
	}

	@Override
	public ArrayList<String> getLore() {
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§9Auspicious Bonus");
		lore.add("§7Grants §6+8☘ Mining");
		lore.add("§6Fortune§7, which increases your");
		lore.add("§7chance for multiple drops.");
		return lore;
	}

}
