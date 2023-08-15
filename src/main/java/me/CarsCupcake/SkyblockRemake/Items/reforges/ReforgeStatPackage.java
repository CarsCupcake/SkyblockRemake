package me.CarsCupcake.SkyblockRemake.Items.reforges;

import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;

import java.util.HashMap;

public class ReforgeStatPackage {
	private final HashMap<Stats, Double> stats;
	public ReforgeStatPackage(){
		stats  = new HashMap<>();
	}

	public void addStats(Stats stat, double value){
		stats.put(stat, value);
	}
	public void addStats(Stats stat, int value){
		stats.put(stat,(double) value);
	}
	public double getStat(Stats stat){
		if(hasStat(stat))
			return stats.get(stat);
		else
			return 0;
	}
	public boolean hasStat(Stats stat){
		return stats.containsKey(stat);
	}
}
