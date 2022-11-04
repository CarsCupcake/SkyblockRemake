package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F1;

import org.bukkit.Location;
import org.bukkit.World;

public enum BonzoAbilitys {
WitherSkull,
BonzosBalloon,
BalloonBarrage;
public static Location PillarLocations(int index, World world) {
	switch (index) {
	case 0:
		return new Location(world, -32.5, 76, 30.5);
	case 1:
		return new Location(world,-27.5, 76, 20.5);
	case 2:
		return new Location(world, -32, 76, 10.5);
	case 3:
		return new Location(world, -42, 76, 5.5);
	case 4:
		return new Location(world, -52.5, 76, 10.5);
	case 5:
		return new Location(world, -57.5, 76, 20.5);
	case 6:
		return new Location(world, -52.5, 76, 30.5);
	
}
	return new Location(world, -42.5, 71, 20.5);
}
}
