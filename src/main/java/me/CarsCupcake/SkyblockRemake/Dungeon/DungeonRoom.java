package me.CarsCupcake.SkyblockRemake.Dungeon;

public class DungeonRoom {
   public DungeonRoomsTypes type;
   public int x;
   public int y;
   public boolean door;
   public DungeonRoom base;
    public int id = 0;
    public DungeonRoom(DungeonRoomsTypes type, int locx, int locy) throws Exception {

        if(!DungeonRoomsTypes.isPlaceble(type, locx, locy))
        throw new Exception("Unable to generate room");

        if(type == DungeonRoomsTypes.r2x2){
            DungeonGeneration.layers.get(locy + 1).set(locx, new DungeonRoom(type, this, locx, locy + 1));
            DungeonGeneration.betweendoors.get(locy).set(locx, "k");
            DungeonGeneration.betweendoors.get(locy).set(locx + 1, "k");
            DungeonGeneration.doors.get(locy).set(locx, "k");
            DungeonGeneration.doors.get(locy + 1).set(locx, "k");
            DungeonGeneration.layers.get(locy).set(locx + 1, new DungeonRoom(type, this, locx +1 , locy));
            DungeonGeneration.layers.get(locy + 1).set(locx + 1, new DungeonRoom(type, this, locx +1 , locy + 1));

        }
        if(type == DungeonRoomsTypes.r1x2){
            DungeonGeneration.layers.get(locy + 1).set(locx, new DungeonRoom(type, this, locx, locy + 1));
            DungeonGeneration.betweendoors.get(locy).set(locx, "k");
          

        }
        if(type == DungeonRoomsTypes.r1x3){
            DungeonGeneration.layers.get(locy + 1).set(locx, new DungeonRoom(type, this, locx, locy + 1));
            DungeonGeneration.betweendoors.get(locy).set(locx, "k");
            DungeonGeneration.layers.get(locy + 2).set(locx, new DungeonRoom(type, this, locx, locy + 2));
            DungeonGeneration.betweendoors.get(locy + 1).set(locx, "k");
          

        }
        if(type == DungeonRoomsTypes.r1x4){
            DungeonGeneration.layers.get(locy + 1).set(locx, new DungeonRoom(type, this, locx, locy + 1));
            DungeonGeneration.betweendoors.get(locy).set(locx, "k");
            DungeonGeneration.layers.get(locy + 2).set(locx, new DungeonRoom(type, this, locx, locy + 2));
            DungeonGeneration.betweendoors.get(locy + 1).set(locx, "k");
            DungeonGeneration.layers.get(locy + 3).set(locx, new DungeonRoom(type, this, locx, locy + 3));
            DungeonGeneration.betweendoors.get(locy + 2).set(locx, "k");
          

        }
        if(type == DungeonRoomsTypes.rLShaped){
            DungeonGeneration.layers.get(locy + 1).set(locx, new DungeonRoom(type, this, locx, locy + 1));
            DungeonGeneration.betweendoors.get(locy).set(locx, "k");
            DungeonGeneration.doors.get(locy + 1).set(locx, "k");
            DungeonGeneration.layers.get(locy + 1).set(locx + 1, new DungeonRoom(type, this, locx +1 , locy + 1));
          

        }
        this.type = type;
        x = locx;
        y=locy;
         door = false;
         if(DungeonRoomsTypes.r1x1 == type) {
        	 
         }
         if(DungeonRoomsTypes.green == type) {
        	 System.out.println("green" + x);
        	 System.out.println("green" + y);
         }
    }
    public DungeonRoom(DungeonRoomsTypes type, int locx, int locy, boolean force) throws Exception {

if(!force)
        if(!DungeonRoomsTypes.isPlaceble(type, locx, locy))
        throw new Exception("Unable to generate room");

        if(type == DungeonRoomsTypes.r2x2){
        	 DungeonGeneration.layers.get(locy + 1).set(locx, new DungeonRoom(type, this, locx, locy + 1));
             DungeonGeneration.betweendoors.get(locy).set(locx, "k");
             DungeonGeneration.betweendoors.get(locy).set(locx + 1, "k");
             DungeonGeneration.doors.get(locy).set(locx, "k");
             DungeonGeneration.doors.get(locy + 1).set(locx, "k");
             DungeonGeneration.layers.get(locy).set(locx + 1, new DungeonRoom(type, this, locx +1 , locy));
             DungeonGeneration.layers.get(locy + 1).set(locx + 1, new DungeonRoom(type, this, locx +1 , locy + 1));

        }
        if(type == DungeonRoomsTypes.r1x2){
            DungeonGeneration.layers.get(locy + 1).set(locx, new DungeonRoom(type, this, locx, locy + 1));
            DungeonGeneration.betweendoors.get(locy).set(locx, "k");
          

        }
        if(type == DungeonRoomsTypes.r1x3){
            DungeonGeneration.layers.get(locy + 1).set(locx, new DungeonRoom(type, this, locx, locy + 1));
            DungeonGeneration.betweendoors.get(locy).set(locx, "k");
            DungeonGeneration.layers.get(locy + 2).set(locx, new DungeonRoom(type, this, locx, locy + 2));
            DungeonGeneration.betweendoors.get(locy + 1).set(locx, "k");
          

        }
        if(type == DungeonRoomsTypes.r1x4){
            DungeonGeneration.layers.get(locy + 1).set(locx, new DungeonRoom(type, this, locx, locy + 1));
            DungeonGeneration.betweendoors.get(locy).set(locx, "k");
            DungeonGeneration.layers.get(locy + 2).set(locx, new DungeonRoom(type, this, locx, locy + 2));
            DungeonGeneration.betweendoors.get(locy + 1).set(locx, "k");
            DungeonGeneration.layers.get(locy + 3).set(locx, new DungeonRoom(type, this, locx, locy + 3));
            DungeonGeneration.betweendoors.get(locy + 2).set(locx, "k");
          

        }
        if(type == DungeonRoomsTypes.rLShaped){
            DungeonGeneration.layers.get(locy + 1).set(locx, new DungeonRoom(type, this, locx, locy + 1));
            DungeonGeneration.betweendoors.get(locy).set(locx, "k");
            DungeonGeneration.doors.get(locy + 1).set(locx, "k");
            DungeonGeneration.layers.get(locy + 1).set(locx + 1, new DungeonRoom(type, this, locx +1 , locy + 1));
          

        }

        this.type = type;
        x = locx;
        y=locy;
        door = false;
        if(DungeonRoomsTypes.green == type) {
       	 System.out.println("green" + x);
       	 System.out.println("green" + y);
        }
    }
    public DungeonRoom(DungeonRoomsTypes type, DungeonRoom base, int locx,int  locy){
this.type = type;
this.base = base;
x = locx;
y = locy;
if(DungeonRoomsTypes.green == type) {
	 System.out.println("green" + x);
	 System.out.println("green" + y);
}
    }
    
    public void setDoors(boolean b) {
    	door = b;
    }
    public static boolean isSame(DungeonRoom cr, DungeonRoom r1) {
    	if(!((r1.base != null 
				&& cr == r1.base )
				||(cr.base != null && cr.base == r1.base)  
						||( r1.base != null && r1.base==cr )||(r1.base != null && r1.base==cr.base))) {
    		return false;
    	}return true;
    }


    

}
