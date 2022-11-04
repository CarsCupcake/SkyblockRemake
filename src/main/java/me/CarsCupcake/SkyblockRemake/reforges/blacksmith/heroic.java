package me.CarsCupcake.SkyblockRemake.reforges.blacksmith;

import java.util.ArrayList;

import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Stats;
import me.CarsCupcake.SkyblockRemake.reforges.Reforge;
import me.CarsCupcake.SkyblockRemake.reforges.ReforgeStatPackage;

public class heroic implements Reforge{

	@Override
	public ReforgeStatPackage CommonStats() {
		ReforgeStatPackage statPackage = new ReforgeStatPackage();
		statPackage.addStats(Stats.Strength, 15);
		statPackage.addStats(Stats.Inteligence, 40);
		statPackage.addStats(Stats.AttackSpeed, 1);
		return statPackage;
	}

	@Override
	public ReforgeStatPackage UncommonStats() {
		ReforgeStatPackage statPackage = new ReforgeStatPackage();
		statPackage.addStats(Stats.Strength, 20);
		statPackage.addStats(Stats.Inteligence, 50);
		statPackage.addStats(Stats.AttackSpeed, 2);
		return statPackage;
	}

	@Override
	public ReforgeStatPackage RareStats() {
		ReforgeStatPackage statPackage = new ReforgeStatPackage();
		statPackage.addStats(Stats.Strength, 25);
		statPackage.addStats(Stats.Inteligence, 65);
		statPackage.addStats(Stats.AttackSpeed, 2);
		return statPackage;
	}

	@Override
	public ReforgeStatPackage EpicStats() {
		ReforgeStatPackage statPackage = new ReforgeStatPackage();
		statPackage.addStats(Stats.Strength, 32);
		statPackage.addStats(Stats.Inteligence, 80);
		statPackage.addStats(Stats.AttackSpeed, 3);
		return statPackage;
	}

	@Override
	public ReforgeStatPackage LegendaryStats() {
		ReforgeStatPackage statPackage = new ReforgeStatPackage();
		statPackage.addStats(Stats.Strength, 40);
		statPackage.addStats(Stats.Inteligence, 100);
		statPackage.addStats(Stats.AttackSpeed, 5);
		return statPackage;
	}

	@Override
	public ReforgeStatPackage MythicStats() {
		ReforgeStatPackage statPackage = new ReforgeStatPackage();
		statPackage.addStats(Stats.Strength, 50);
		statPackage.addStats(Stats.Inteligence, 125);
		statPackage.addStats(Stats.AttackSpeed, 7);
		return statPackage;
	}

	@Override
	public ReforgeStatPackage DivineStats() {
		// TODO Auto-generated method stub
		return MythicStats();
	}

	@Override
	public ItemType[] Reforgable() {
		ItemType[] types = {ItemType.Sword, ItemType.Wand};
		return types;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Heroic";
	}

	@Override
	public ArrayList<String> getLore() {
		// TODO Auto-generated method stub
		return null;
	}

}
