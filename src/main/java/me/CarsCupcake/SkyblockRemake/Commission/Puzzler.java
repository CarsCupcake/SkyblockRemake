package me.CarsCupcake.SkyblockRemake.Commission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Location;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class Puzzler {
private static  HashMap<SkyblockPlayer, Puzzler> puzzlers = new HashMap<>();
private String puzzlerstring;
private ArrayList<Direction> directions = new ArrayList<>();
private final SkyblockPlayer player;
public boolean isFailed = false;
public Puzzler(SkyblockPlayer player) {
	puzzlers.put(player, this);
	this.player = player;
	generateChain();
	createString();
}
private void generateChain() {
	int top = 4;
	int bot = 1;
	Random rand = new Random();
	for(int i = 0; i < 11; i++) {
		directions.add(Direction.getFromValue(rand.nextInt(top - bot) + bot));
	}
}
private void createString() {
	puzzlerstring = "";
	for(Direction dir : directions)
		puzzlerstring += dir.toString();
	
}
public static Puzzler getPuzzler(SkyblockPlayer player) {
	return puzzlers.get(player);
}
public String getString() {
return puzzlerstring;
}
public ArrayList<Direction> getDirections(){
	return directions;
}
public static boolean activePlayerQuest(SkyblockPlayer player) {
	return puzzlers.containsKey(player);
}
public Location getEstimatetBlockLocation() {
	Location loc = new Location(player.getWorld(), 181, 195, 135);
	
	for(Direction dir : directions) {
		if(dir == Direction.Up)
			loc = loc.add(0,0,1);
		if(dir == Direction.Down)
			loc = loc.add(0,0,-1);
		if(dir == Direction.Left)
			loc = loc.add(1,0,0);
		if(dir == Direction.Right)
			loc = loc.add(-1,0,0);
	}
	
	return loc;
	
}
public void remove() {
	puzzlers.remove(player);
}
public void markAsFailed() {
	isFailed = true;
}
public void retry() {
	isFailed = false;
}
}
