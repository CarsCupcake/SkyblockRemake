package me.CarsCupcake.SkyblockRemake.reforges;

import java.util.HashMap;

import me.CarsCupcake.SkyblockRemake.reforges.ReforgeStones.Auspicious;
import me.CarsCupcake.SkyblockRemake.reforges.ReforgeStones.Jaded;
import me.CarsCupcake.SkyblockRemake.reforges.ReforgeStones.Loving;
import me.CarsCupcake.SkyblockRemake.reforges.ReforgeStones.Necrotic;
import me.CarsCupcake.SkyblockRemake.reforges.blacksmith.epic;
import me.CarsCupcake.SkyblockRemake.reforges.blacksmith.heroic;



public class registerReforge {
	public static HashMap<String, Reforge> reforges = new HashMap<>();
	public static HashMap<String, Reforge> blacksmith_reforges = new HashMap<>();
	
	public static HashMap<String, Reforge> reforgeStones_reforges = new HashMap<>();
public static void init() {
	initBlacksmith();
	initReforgeStones();
}
private static void initBlacksmith() {
	blacksmith_reforges.put("Epic", new epic());
	reforges.put("Epic", new epic());
	
	blacksmith_reforges.put("Heroic", new heroic());
	reforges.put("Heroic", new heroic());
}

private static void initReforgeStones() {
	reforgeStones_reforges.put("Necrotic", new Necrotic());
	reforges.put("Necrotic", new Necrotic());
	
	reforgeStones_reforges.put("Loving", new Loving());
	reforges.put("Loving", new Loving());

	reforgeStones_reforges.put("Auspicious", new Auspicious());
	reforges.put("Auspicious", new Auspicious());

	reforgeStones_reforges.put("Jaded", new Jaded());
	reforges.put("Jaded", new Jaded());
	
}

}
