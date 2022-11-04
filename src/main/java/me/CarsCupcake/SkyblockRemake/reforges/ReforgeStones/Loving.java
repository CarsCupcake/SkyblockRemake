package me.CarsCupcake.SkyblockRemake.reforges.ReforgeStones;

import java.util.ArrayList;

import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Stats;
import me.CarsCupcake.SkyblockRemake.reforges.Reforge;
import me.CarsCupcake.SkyblockRemake.reforges.ReforgeStatPackage;

public class Loving implements Reforge{

	@Override
	public ReforgeStatPackage CommonStats() {
		ReforgeStatPackage statPackage = new ReforgeStatPackage();
		statPackage.addStats(Stats.Health, 4);
		statPackage.addStats(Stats.Defense, 4);
		statPackage.addStats(Stats.Inteligence, 20);
		return statPackage;
	}

	@Override
	public ReforgeStatPackage UncommonStats() {
		ReforgeStatPackage statPackage = new ReforgeStatPackage();
		statPackage.addStats(Stats.Health, 5);
		statPackage.addStats(Stats.Defense, 5);
		statPackage.addStats(Stats.Inteligence, 40);
		return statPackage;
	}

	@Override
	public ReforgeStatPackage RareStats() {
		ReforgeStatPackage statPackage = new ReforgeStatPackage();
		statPackage.addStats(Stats.Health, 6);
		statPackage.addStats(Stats.Defense, 6);
		statPackage.addStats(Stats.Inteligence, 60);
		return statPackage;
	}

	@Override
	public ReforgeStatPackage EpicStats() {
		ReforgeStatPackage statPackage = new ReforgeStatPackage();
		statPackage.addStats(Stats.Health, 8);
		statPackage.addStats(Stats.Defense, 7);
		statPackage.addStats(Stats.Inteligence, 80);
		return statPackage;
	}

	@Override
	public ReforgeStatPackage LegendaryStats() {
		ReforgeStatPackage statPackage = new ReforgeStatPackage();
		statPackage.addStats(Stats.Health, 10);
		statPackage.addStats(Stats.Defense, 10);
		statPackage.addStats(Stats.Inteligence, 100);
		return statPackage;
	}

	@Override
	public ReforgeStatPackage MythicStats() {
		ReforgeStatPackage statPackage = new ReforgeStatPackage();
		statPackage.addStats(Stats.Health, 14);
		statPackage.addStats(Stats.Defense, 14);
		statPackage.addStats(Stats.Inteligence, 120);
		return statPackage;
	}

	@Override
	public ReforgeStatPackage DivineStats() {
		// TODO Auto-generated method stub
		
		return MythicStats();
	}

	@Override
	public ItemType[] Reforgable() {
		ItemType[] types = {ItemType.Chestplate};
		return types;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Loving";
	}

	@Override
	public ArrayList<String> getLore() {
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§9Loving Bonus");
		lore.add("§7Increases ability damage by");
		lore.add("§a5%");
		return lore;
	}

}
