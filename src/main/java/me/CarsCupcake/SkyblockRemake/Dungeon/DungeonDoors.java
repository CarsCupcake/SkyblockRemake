package me.CarsCupcake.SkyblockRemake.Dungeon;

import java.util.ArrayList;

public class DungeonDoors {
	public static ArrayList<Locs> currdors = new ArrayList<>();
	@SuppressWarnings("unchecked")
	public static void initDoors(int x, int y) {
		DungeonGeneration.betweendoors.get(y - 1).set(x, "y");
		DungeonGeneration.layers.get(y ).get(x).setDoors(true);
		
		ArrayList<Locs> newDoors = new ArrayList<>();

		currdors.add(new Locs(x, y-1));
		for(int i = 0; i < 10; i++){
                newDoors = (ArrayList<Locs>) currdors.clone();
			if(!currdors.isEmpty())
			for(Locs loc :newDoors) {
				
				if(DungeonGeneration.layers.get(loc.y ).get(loc.x).type == DungeonRoomsTypes.red)
					continue;
				if(DungeonGeneration.layers.get(loc.y ).get(loc.x).type == DungeonRoomsTypes.green)
					continue;
				
				DungeonRoom r1 = null;
				DungeonRoom r2 = null;
				DungeonRoom r3 = null;
				DungeonRoom r4 = null;
				
				DungeonRoom cr = DungeonGeneration.layers.get(loc.y ).get(loc.x);
				currdors.remove(loc);
				DungeonRoomsTypes type = cr.type;
				if(type == DungeonRoomsTypes.miniboss || type == DungeonRoomsTypes.puzzle || type == DungeonRoomsTypes.Trap)
					continue;
				if(loc.y + 1 < 6)
				 r1 = DungeonGeneration.layers.get(loc.y +1).get(loc.x);
				if(loc.y - 1 > -1)
				 r2 = DungeonGeneration.layers.get(loc.y -1).get(loc.x);
				if(loc.x - 1 > -1)
				 r3 = DungeonGeneration.layers.get(loc.y ).get(loc.x-1);
				 if(loc.x + 1 < 6)
				 r4 = DungeonGeneration.layers.get(loc.y ).get(loc.x+1);

				
				
				if(r1 != null) {
					
				if(!r1.door && !(r1.base != null && r1.base.door)) {
					if(!((r1.base != null 
							&& cr == r1.base )
							||(cr.base != null && cr.base == r1.base)  
									||( r1.base != null && r1.base==cr )||(r1.base != null && r1.base==cr.base))) {
						
					DungeonGeneration.betweendoors.get(r1.y-1).set(r1.x , "y");
					DungeonGeneration.layers.get(r1.y).get(r1.x).setDoors(true);
					currdors.add(new Locs(r1.x, r1.y));
					if(r1.base != null) {
						DungeonGeneration.layers.get(r1.base.y).get(r1.base.x).setDoors(true);
					}
					}
					
				}else
					if(!((r1.base != null 
					&& cr == r1.base )
					||(cr.base != null && cr.base == r1.base)  
							||( r1.base != null && r1.base==cr )||(r1.base != null && r1.base==cr.base)))
				currdors.add(new Locs(r1.x, r1.y));}
				if(r2 != null) {
					
				if(r2.door == false && !(r2.base != null && r2.base.door !=false)) {
					if(!((r2.base != null 
							&&cr == r2.base )
							||(cr.base != null && cr.base == r2.base)  
									||(r2.base != null && r2.base==cr) ||(r2.base != null && r2.base==cr.base))) {
						
					DungeonGeneration.betweendoors.get(r2.y).set(r2.x, "y");
					DungeonGeneration.layers.get(r2.y).get(r2.x).setDoors(true);
					currdors.add(new Locs(r2.x, r2.y));
					if(r2.base != null) {
						DungeonGeneration.layers.get(r2.base.y).get(r2.base.x).setDoors(true);
					}
					}
					
				}else
					if(!((r2.base != null 
					&&cr == r2.base )
					||(cr.base != null && cr.base == r2.base)  
							||(r2.base != null && r2.base==cr) ||(r2.base != null && r2.base==cr.base)))
				currdors.add(new Locs(r2.x, r2.y));
				}
				if(r3 != null) {
					
				if(r3.door == false && !(r3.base != null && r3.base.door !=false)) {
					if(!((r3.base != null 
							&& cr == r3.base )
							||(cr.base != null && cr.base == r3.base)  
									||( r3.base != null && r3.base==cr )||(r3.base != null && r3.base==cr.base))) {
				
					DungeonGeneration.doors.get(r3.y).set(r3.x  , "y");
					DungeonGeneration.layers.get(r3.y).get(r3.x).setDoors(true);
					if(r3.base != null) {
						DungeonGeneration.layers.get(r3.base.y).get(r3.base.x).setDoors(true);
					}currdors.add(new Locs(r3.x, r3.y));
					}
					
				}else
					if(!((r3.base != null 
					&& cr == r3.base )
					||(cr.base != null && cr.base == r3.base)  
							||( r3.base != null && r3.base==cr )||(r3.base != null && r3.base==cr.base)))
				currdors.add(new Locs(r3.x, r3.y));
				}
				if(r4 != null) {
					
				if(r4.door == false && !(r4.base != null && r4.base.door !=false)) {
					if(!((r4.base != null 
							&& cr == r4.base )
							||(cr.base != null && cr.base == r4.base)  
									||( r4.base != null && r4.base==cr )||(r4.base != null && r4.base==cr.base))) {
				
					DungeonGeneration.doors.get(r4.y).set(r4.x  -1, "y");
					DungeonGeneration.layers.get(r4.y).get(r4.x  ).setDoors(true);
					if(r4.base != null) {
						DungeonGeneration.layers.get(r4.base.y).get(r4.base.x).setDoors(true);
					}currdors.add(new Locs(r4.x, r4.y));
					}
					
				}else
					if(((r4.base != null 
					&& cr == r4.base )
					||(cr.base != null && cr.base == r4.base)  
							||( r4.base != null && r4.base==cr )||(r4.base != null && r4.base==cr.base)))
				currdors.add(new Locs(r4.x, r4.y));}
			}
		}
	}
}
