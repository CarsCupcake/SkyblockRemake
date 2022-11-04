package me.CarsCupcake.SkyblockRemake.reforges.blacksmith;

import java.util.ArrayList;

import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Stats;
import me.CarsCupcake.SkyblockRemake.reforges.Reforge;
import me.CarsCupcake.SkyblockRemake.reforges.ReforgeStatPackage;

public class epic implements Reforge{

	@Override
	public ReforgeStatPackage CommonStats() {
		ReforgeStatPackage statPackage = new ReforgeStatPackage();
		statPackage.addStats(Stats.Strength, 15);
		statPackage.addStats(Stats.CritDamage, 10);
		statPackage.addStats(Stats.AttackSpeed, 1);
		return statPackage;
	}

	@Override
	public ReforgeStatPackage UncommonStats() {
		ReforgeStatPackage statPackage = new ReforgeStatPackage();
		statPackage.addStats(Stats.Strength, 20);
		statPackage.addStats(Stats.CritDamage, 15);
		statPackage.addStats(Stats.AttackSpeed, 2);
		return statPackage;
	}

	@Override
	public ReforgeStatPackage RareStats() {
		ReforgeStatPackage statPackage = new ReforgeStatPackage();
		statPackage.addStats(Stats.Strength, 25);
		statPackage.addStats(Stats.CritDamage, 20);
		statPackage.addStats(Stats.AttackSpeed, 4);
		return statPackage;
	}

	@Override
	public ReforgeStatPackage EpicStats() {
		ReforgeStatPackage statPackage = new ReforgeStatPackage();
		statPackage.addStats(Stats.Strength, 32);
		statPackage.addStats(Stats.CritDamage, 27);
		statPackage.addStats(Stats.AttackSpeed, 7);
		return statPackage;
	}

	@Override
	public ReforgeStatPackage LegendaryStats() {
		ReforgeStatPackage statPackage = new ReforgeStatPackage();
		statPackage.addStats(Stats.Strength, 40);
		statPackage.addStats(Stats.CritDamage, 35);
		statPackage.addStats(Stats.AttackSpeed, 10);
		return statPackage;
	}

	@Override
	public ReforgeStatPackage MythicStats() {
		ReforgeStatPackage statPackage = new ReforgeStatPackage();
		statPackage.addStats(Stats.Strength, 50);
		statPackage.addStats(Stats.CritDamage, 45);
		statPackage.addStats(Stats.AttackSpeed, 15);
		return statPackage;
	}

	@Override
	public ReforgeStatPackage DivineStats() {
		return MythicStats();
	}

	@Override
	public ItemType[] Reforgable() {
		ItemType[] type = {ItemType.Sword};
		return type;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Epic";
	}

	@Override
	public ArrayList<String> getLore() {
		// TODO Auto-generated method stub
		return null;
	}

}
