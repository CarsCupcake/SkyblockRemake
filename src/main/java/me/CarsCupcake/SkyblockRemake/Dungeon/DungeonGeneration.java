package me.CarsCupcake.SkyblockRemake.Dungeon;
import org.bukkit.Bukkit;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Random;

/**
 * DungeonGeneration
 */
public class DungeonGeneration {

    public static ArrayList<ArrayList<DungeonRoom>> layers = new ArrayList<>();
    public static ArrayList<ArrayList<String>> doors = new ArrayList<>();
    public static ArrayList<ArrayList<String>> betweendoors = new ArrayList<>();
    
    public static void main(String[] args) {
    	layers.clear();
    	doors.clear();
    	betweendoors.clear();
        init();
        StartGeneration();
        printresult();
    }

    public static void init(){
        layers.add(addEmpty(new ArrayList<>()));
        
        layers.add(addEmpty(new ArrayList<>()));
        layers.add(addEmpty(new ArrayList<>()));
        layers.add(addEmpty(new ArrayList<>()));
        layers.add(addEmpty(new ArrayList<>()));
        layers.add(addEmpty(new ArrayList<>()));
        


        doors.add(addNoDoors(new ArrayList<>()));doors.add(addNoDoors(new ArrayList<>()));doors.add(addNoDoors(new ArrayList<>()));doors.add(addNoDoors(new ArrayList<>()));doors.add(addNoDoors(new ArrayList<>()));doors.add(addNoDoors(new ArrayList<>()));
        betweendoors.add(addEmpty(new ArrayList<>(), "n"));betweendoors.add(addEmpty(new ArrayList<>(), "n"));betweendoors.add(addEmpty(new ArrayList<>(), "n"));betweendoors.add(addEmpty(new ArrayList<>(), "n"));betweendoors.add(addEmpty(new ArrayList<>(), "n"));betweendoors.add(addEmpty(new ArrayList<>(), "n"));

    }
    public static ArrayList<DungeonRoom> addEmpty(ArrayList<DungeonRoom> list){
        for(int i = 0; i < 6; i++)
            list.add(null);
        return list;
    }
    
    public static ArrayList<String> addEmpty(ArrayList<String> list, String s){
        for(int i = 0; i < 6; i++)
            list.add(s);
        return list;
    }
    public static ArrayList<String> addNoDoors(ArrayList<String> list){
        for(int i = 0; i < 5; i++)
            list.add("n");
        return list;
    }
    public static void StartGeneration(){
    	if(!DungeonDoors.currdors.isEmpty())
    		DungeonDoors.currdors.clear();
        Random rand = new Random();
        int redPlace = rand.nextInt(5);
        int greenPlace = rand.nextInt(5);
        try {
			layers.get(5).set(greenPlace, new DungeonRoom(DungeonRoomsTypes.green, greenPlace, 5, true));
		} catch (Exception ignored) {}
        try {
			layers.get(0).set(redPlace, new DungeonRoom(DungeonRoomsTypes.red, redPlace, 0, true));
		} catch (Exception ignored) {}
        int fairyx = rand.nextInt(5);
        int fairyy = rand.nextInt(5);

        if(fairyy == 0){
            while(fairyx == redPlace){
                fairyx = rand.nextInt(5);
            }
        }
        if(fairyy == 5){
            while(fairyx == greenPlace){
                fairyx = rand.nextInt(5);
            }
        }

        try {
            layers.get(fairyy).set(fairyx, new DungeonRoom(DungeonRoomsTypes.fairy, fairyx, fairyy, true));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Pathfind( fairyx, fairyy,greenPlace, 5).forEach((s)->{
            int l = Integer.parseInt(s.split(":")[1]);
            int i= Integer.parseInt(s.split(":")[0]);
            boolean blace = true;

            boolean r2x2 = true;
            boolean r1x2 = true;
            boolean r1x3 = true;
            boolean r1x4 = true;
            boolean lshape = true;

            if(layers.get(l).get(i) == null) {
                System.out.println("nonnull");
            while(blace){
                
                    Random r = new Random();
                    if(r.nextDouble() < 0.3 && r2x2){
                        try {
                            layers.get(l).set(i, new DungeonRoom(DungeonRoomsTypes.r2x2, i, l));
                        } catch (Exception e) {
                            r2x2 = false;
                        }
                    }
                    if(r.nextDouble() < 0.3 && lshape){
                        try {
                            layers.get(l).set(i, new DungeonRoom(DungeonRoomsTypes.rLShaped, i, l));
                        } catch (Exception e) {
                            lshape = false;
                        }
                    }
                    if(r.nextDouble() < 0.1){
                        try {
                            layers.get(l).set(i, new DungeonRoom(DungeonRoomsTypes.r1x1, i, l));
                        } catch (Exception e) {
                            break;
                        }
                    }
                    if(r.nextDouble() < 0.3 && r1x2){
                        try {
                            layers.get(l).set(i, new DungeonRoom(DungeonRoomsTypes.r1x2, i, l));
                        } catch (Exception e) {
                            r1x2 = false;
                        }
                    }
                    if(r.nextDouble() < 0.3 && r1x3){
                        try {
                            layers.get(l).set(i, new DungeonRoom(DungeonRoomsTypes.r1x3, i, l));
                        } catch (Exception e) {
                            r1x3 = false;
                        }
                    }
                    if(r.nextDouble() < 0.3 && r1x4){
                        try {
                            layers.get(l).set(i, new DungeonRoom(DungeonRoomsTypes.r1x4, i, l));
                        } catch (Exception e) {
                            r1x4 = false;
                        }
                    }
                    try {
                        layers.get(l).set(i, new DungeonRoom(DungeonRoomsTypes.r1x1, i, l));
                    } catch (Exception e) {
                        break;
                    }
                    System.out.println(layers.get(l).get(i).type.toString());
                    blace = false;
                    break;
                
            }} 
        });
        Pathfind( fairyx, fairyy,redPlace, 0).forEach((s)->{
            int l = Integer.parseInt(s.split(":")[1]);
            int i= Integer.parseInt(s.split(":")[0]);
            boolean blace = true;

            boolean r2x2 = true;
            boolean r1x2 = true;
            boolean r1x3 = true;
            boolean r1x4 = true;
            boolean lshape = true;

            if(layers.get(l).get(i) == null) {
                System.out.println("nonnull");
            while(blace){
                
                    Random r = new Random();
                    if(r.nextDouble() < 0.3 && r2x2){
                        try {
                            layers.get(l).set(i, new DungeonRoom(DungeonRoomsTypes.r2x2, i, l));
                        } catch (Exception e) {
                            r2x2 = false;
                        }
                    }
                    if(r.nextDouble() < 0.3 && lshape){
                        try {
                            layers.get(l).set(i, new DungeonRoom(DungeonRoomsTypes.rLShaped, i, l));
                        } catch (Exception e) {
                            lshape = false;
                        }
                    }
                    if(r.nextDouble() < 0.1){
                        try {
                            layers.get(l).set(i, new DungeonRoom(DungeonRoomsTypes.r1x1, i, l));
                        } catch (Exception e) {
                            break;
                        }
                    }
                    if(r.nextDouble() < 0.3 && r1x2){
                        try {
                            layers.get(l).set(i, new DungeonRoom(DungeonRoomsTypes.r1x2, i, l));
                        } catch (Exception e) {
                            r1x2 = false;
                        }
                    }
                    if(r.nextDouble() < 0.3 && r1x3){
                        try {
                            layers.get(l).set(i, new DungeonRoom(DungeonRoomsTypes.r1x3, i, l));
                        } catch (Exception e) {
                            r1x3 = false;
                        }
                    }
                    if(r.nextDouble() < 0.3 && r1x4){
                        try {
                            layers.get(l).set(i, new DungeonRoom(DungeonRoomsTypes.r1x4, i, l));
                        } catch (Exception e) {
                            r1x4 = false;
                        }
                    }
                    try {
                        layers.get(l).set(i, new DungeonRoom(DungeonRoomsTypes.r1x1, i, l));
                    } catch (Exception e) {
                        break;
                    }
                    System.out.println(layers.get(l).get(i).type.toString());
                    blace = false;
                    break;
                
            }} 
        });
        
        
        try {
            layers.get(0).set(redPlace, new DungeonRoom(DungeonRoomsTypes.red, redPlace, 0, true));
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        try {
            layers.get(5).set(greenPlace, new DungeonRoom(DungeonRoomsTypes.green, greenPlace, 5, true));
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
        
        ArrayList<Locs> oneByOne = new ArrayList<>();
        for(int l = 0; l < 6; l++)
            for(int i = 0; i < 6; i++){
            boolean blace = true;

            boolean r2x2 = true;
            boolean r1x2 = true;
            boolean r1x3 = true;
            boolean r1x4 = true;
            boolean lshape = true;

            if(layers.get(l).get(i) == null) {
                System.out.println("nonnull");
            while(blace){
                
                    Random r = new Random();
                    if(r.nextDouble() < 0.3 && r2x2){
                        try {
                            layers.get(l).set(i, new DungeonRoom(DungeonRoomsTypes.r2x2, i, l));
                        } catch (Exception e) {
                            r2x2 = false;
                        }
                    }
                    if(r.nextDouble() < 0.3 && lshape){
                        try {
                            layers.get(l).set(i, new DungeonRoom(DungeonRoomsTypes.rLShaped, i, l));
                        } catch (Exception e) {
                            lshape = false;
                        }
                    }
                    if(r.nextDouble() < 0.1){
                        try {
                            layers.get(l).set(i, new DungeonRoom(DungeonRoomsTypes.r1x1, i, l));
                        } catch (Exception e) {
                            break;
                        }
                    }
                    if(r.nextDouble() < 0.3 && r1x2){
                        try {
                            layers.get(l).set(i, new DungeonRoom(DungeonRoomsTypes.r1x2, i, l));
                        } catch (Exception e) {
                            r1x2 = false;
                        }
                    }
                    if(r.nextDouble() < 0.3 && r1x3){
                        try {
                            layers.get(l).set(i, new DungeonRoom(DungeonRoomsTypes.r1x3, i, l));
                        } catch (Exception e) {
                            r1x3 = false;
                        }
                    }
                    if(r.nextDouble() < 0.3 && r1x4){
                        try {
                            layers.get(l).set(i, new DungeonRoom(DungeonRoomsTypes.r1x4, i, l));
                        } catch (Exception e) {
                            r1x4 = false;
                        }
                    }
                    try {
                        layers.get(l).set(i, new DungeonRoom(DungeonRoomsTypes.r1x1, i, l));
                        oneByOne.add(new Locs(l,i));
                    } catch (Exception e) {
                        break;
                    }

                    blace = false;
                    break;
                
            }}
        }
        int puzzle = new Random().nextInt(3) + 2;
        if(puzzle + 2 > oneByOne.size()){
            Bukkit.broadcastMessage("§cGeneration Failed! Generation was not able to place enouth rooms!");
            return;
        }
        Collections.shuffle(oneByOne);
        Locs l = oneByOne.get(0);
        layers.get(l.x).get(l.y).type = DungeonRoomsTypes.miniboss;
        l = oneByOne.get(1);
        layers.get(l.x).get(l.y).type = DungeonRoomsTypes.Trap;
        for(int i = 0; i < puzzle; i++){
            l = oneByOne.get(puzzle + 3);
            layers.get(l.x).get(l.y).type = DungeonRoomsTypes.puzzle;
        }
        
        doorPathFind(fairyx, fairyy,redPlace, 0);
        doorPathFind(greenPlace, 5,fairyx, fairyy);
        
        DungeonDoors.initDoors(greenPlace, 5);
        
    }
    public static void printresult(){
        int i = 0;
        int l = 0;
        for(ArrayList<DungeonRoom> lists : layers){
            for(DungeonRoom rooms : lists){

                if(i >= 5){
                    if(rooms != null)
                System.out.print(rooms.type.toString() );
                else
                System.out.print("non");

            }
                else{
                    if(rooms != null)
                    System.out.print(rooms.type.toString() + " " + doors.get(l).get(i) + " ");
                    else
                    System.out.print( "non " + doors.get(l).get(i) + " ");
            }
                i++;
            }
            System.out.print("\n");
            
            if(l != 5){
            System.out.print(" ");    
            for(String rooms : betweendoors.get(l)){

                System.out.print(rooms + "     ");
            }}
            l++;
            i = 0;
            System.out.print("\n" );
        }
    }
    //1. = x 2. = y
    public static ArrayList<String> Pathfind(int startx, int starty, int endx, int endy){
        ArrayList<String> nextRooms = new ArrayList<String>();
       
       
        
     if(starty-endy != 0){
                System.out.println("y-div:" + (starty-endy));
                        if(starty-endy > 0){
                            for(int i = starty - 1; i >= endy; i--){
                               
                               
                                nextRooms.add(startx+":"+i);
                                System.out.println("-2");
                            }
                        }else{
                            for(int i = starty + 1; i <=endy; i++){
                           
                               
                                
                                nextRooms.add(startx+":"+i);
                                
                                System.out.println("-1" + (starty - endy));
                            }
                    }
                    }
            if(startx-endx != 0){
                System.out.println("x-div:" + (startx-endx));
                if(startx-endx >0){
                    for(int i = startx - 1; i >= endx; i--){
                        
                        nextRooms.add(i+":"+endy);
                        System.out.println("-4");
                    }
                    
                }else{
                    for(int i = startx + 1; i <= endx; i++){
                        
                        nextRooms.add(i+":"+endy);
                        System.out.println("-3");
                    }
                }
            }
            
        

        return nextRooms;
    }
    
    private static void doorPathFind(int startx, int starty, int endx, int endy) {
    	if(starty-endy != 0){
            System.out.println("y-div:" + (starty-endy));
                    if(starty-endy > 0){
                        for(int i = starty - 1; i >= endy; i--){
                           
                        	DungeonRoom cr = layers.get(i).get(startx);
                        	if(i-1 >=0) {
                        	DungeonRoom r1 = layers.get(i-1).get(startx);
                        	
                        	
                           if(!DungeonRoom.isSame(cr, r1)) {
                        	   layers.get(i).get(startx).setDoors(true);
                        	   betweendoors.get(i).set(startx, "y");
                        	   DungeonDoors.currdors.add(new Locs(startx,i));
                           }}
                        	
                            
                            System.out.println("-2");
                        }
                    }else{
                        for(int i = starty + 1; i <=endy; i++){
                       
                        	DungeonRoom cr = layers.get(i).get(startx);
                        	if(i+1 < 6) {
                        	DungeonRoom r1 = layers.get(i+1).get(startx);
                        	
                        	
                           if(!DungeonRoom.isSame(cr, r1)) {
                        	   layers.get(i).get(startx).setDoors(true);
                        	   betweendoors.get(i-1).set(startx, "y");
                        	   DungeonDoors.currdors.add(new Locs(startx,i));
                           }}
                            
                            
                            
                            System.out.println("-1" + (starty - endy));
                        }
                }
                }
        if(startx-endx != 0){
            System.out.println("x-div:" + (startx-endx));
            if(startx-endx >0){
                for(int i = startx - 1; i >= endx; i--){
                    
                	DungeonRoom cr = layers.get(endy).get(i);
                	if(i-1 >= 0) {
                	DungeonRoom r1 = layers.get(endy).get(i-1);
                	
                	
                   if(!DungeonRoom.isSame(cr, r1)) {
                	   layers.get(endy).get(i).setDoors(true);
                	   doors.get(endy).set(i, "y");
                	   DungeonDoors.currdors.add(new Locs(i,endy));}
                   }
                   
                    System.out.println("-4");
                }
                
            }else{
                for(int i = startx + 1; i <= endx; i++){
                	DungeonRoom cr = layers.get(endy).get(i);
                	DungeonRoom r1 = layers.get(endy).get(i+1);
                	if(i+1 < 6) {
                	
                   if(!DungeonRoom.isSame(cr, r1)) {
                	   layers.get(endy).get(i).setDoors(true);
                	   doors.get(endy).set(i-1, "y");
                	   DungeonDoors.currdors.add(new Locs(i,endy));
                   }}
                   
                    System.out.println("-3");
                }
            }
        }
    }
    

}
