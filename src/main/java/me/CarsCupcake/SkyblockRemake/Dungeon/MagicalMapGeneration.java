package me.CarsCupcake.SkyblockRemake.Dungeon;

import java.awt.Color;
import java.awt.Graphics2D;


import java.awt.image.BufferedImage;
import java.util.ArrayList;


import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;


public class MagicalMapGeneration extends MapRenderer{
	private  BufferedImage image;
	private  boolean done;
	public MagicalMapGeneration() {
		done = false;
		image = null;
		image = new BufferedImage(128, 128, BufferedImage.TYPE_INT_RGB);	
	}
	public  void generateMap( World world) {
		
		MapView view = Bukkit.createMap(world);
		view.getRenderers().clear();
		view.setTrackingPosition(false);
		
		
		DungeonGeneration.main(null);
		
	
int currx = 6;
int curry = 6;
int runntime1 = 0;
int runntime2 = 0;
Graphics2D g2 = (Graphics2D) image.getGraphics();
Color c1= new Color(183,165,130,255);
Color c2 = new Color(161,144,114,255);
Color trap = new Color(0xe68a00);
Color puzzle = Color.MAGENTA;
Color yellow = Color.YELLOW;

for(int l = 0; l<128; l++)
	for(int i = 0; i <128;i++) {
		if (i% 2 == 0) 
			g2.setColor(c1);
		else
			g2.setColor(c2);
			
			
			g2.drawRect(i, l, i+1, l+1);
		
	}





for(ArrayList<DungeonRoom> lists : DungeonGeneration.layers) {
	for(DungeonRoom room : lists) {
		
		if(room != null) {
			g2.setColor(new Color(113,67,27,255));
			if(room.type == DungeonRoomsTypes.fairy) 
				g2.setColor(new Color(200,106,138,255));

			if(room.type == DungeonRoomsTypes.Trap)
				g2.setColor(trap);

			if(room.type == DungeonRoomsTypes.puzzle)
				g2.setColor(puzzle);

			if(room.type == DungeonRoomsTypes.miniboss)
				g2.setColor(yellow);
			
				if(room.type == DungeonRoomsTypes.red)
					g2.setColor(Color.red);

				if(room.type == DungeonRoomsTypes.green)
					g2.setColor(Color.GREEN);
				
			
			g2.fillRect(currx, curry, 14, 14);
		}
		currx += 14;
		if(runntime1 < 5 && DungeonGeneration.doors.get(runntime2).get(runntime1).equals("k")) {
			g2.setColor(new Color(113,67,27,255));
			g2.fillRect(currx, curry, 6, 14);
		}
		if(runntime1 < 5 && DungeonGeneration.doors.get(runntime2).get(runntime1).equals("y")) {
			g2.setColor(new Color(113,67,27,255));
			g2.fillRect(currx, curry + 4, 6, 6);
		}
		if(room.type == DungeonRoomsTypes.r2x2 && room.base == null) {
			g2.setColor(new Color(113,67,27,255));
			g2.fillRect(currx, curry + 14, 6, 6);
		}
		currx += 6;
		
		runntime1++;
	}
	runntime1 = 0;
	curry += 14;
	currx = 6;
	if(runntime2 < 5)
	for(String str : DungeonGeneration.betweendoors.get(runntime2)) {
		if(str.equals("k")) {
			g2.setColor(new Color(113,67,27,255));
			g2.fillRect(currx, curry, 14, 6);
			}
		if(str.equals("y")) {
			System.out.println("addBridge");
			g2.setColor(new Color(113,67,27,255));
			g2.fillRect(currx+4, curry, 6, 6);
		}
			
			
			
		
		currx += 20;
	}
	
	runntime2++;
	curry += 6;
	currx = 6;
}


		
		g2.dispose();
	}

	
	
	@Override
	public void render(@NotNull MapView view, @NotNull MapCanvas canvas, @NotNull Player player) {
		if(done)
			return;
		
		canvas.drawImage(0, 0, image);
		
		done = true;
		
		
	}

}
