package me.CarsCupcake.SkyblockRemake.reforges.ReforgeStones;

import java.util.ArrayList;

import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Stats;
import me.CarsCupcake.SkyblockRemake.reforges.Reforge;
import me.CarsCupcake.SkyblockRemake.reforges.ReforgeStatPackage;

public class Necrotic implements Reforge{

	@Override
	public ReforgeStatPackage CommonStats() {
		ReforgeStatPackage statPackage = new ReforgeStatPackage();
		statPackage.addStats(Stats.Inteligence, 30);
		return statPackage;
	}

	@Override
	public ReforgeStatPackage UncommonStats() {
		ReforgeStatPackage statPackage = new ReforgeStatPackage();
		statPackage.addStats(Stats.Inteligence, 60);
		return statPackage;
	}

	@Override
	public ReforgeStatPackage RareStats() {
		ReforgeStatPackage statPackage = new ReforgeStatPackage();
		statPackage.addStats(Stats.Inteligence, 90);
		return statPackage;
	}

	@Override
	public ReforgeStatPackage EpicStats() {
		ReforgeStatPackage statPackage = new ReforgeStatPackage();
		statPackage.addStats(Stats.Inteligence, 120);
		return statPackage;
	}

	@Override
	public ReforgeStatPackage LegendaryStats() {
		ReforgeStatPackage statPackage = new ReforgeStatPackage();
		statPackage.addStats(Stats.Inteligence, 150);
		return statPackage;
	}

	@Override
	public ReforgeStatPackage MythicStats() {
		ReforgeStatPackage statPackage = new ReforgeStatPackage();
		statPackage.addStats(Stats.Inteligence, 200);
		return statPackage;
	}

	@Override
	public ReforgeStatPackage DivineStats() {
		// TODO Auto-generated method stub
		return MythicStats();
	}

	@Override
	public ItemType[] Reforgable() {
		ItemType[] types = {ItemType.Helmet, ItemType.Chestplate, ItemType.Leggings, ItemType.Boots};
		return types;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Necrotic";
	}

	@Override
	public ArrayList<String> getLore() {
		// TODO Auto-generated method stub
		return null;
	}

}
