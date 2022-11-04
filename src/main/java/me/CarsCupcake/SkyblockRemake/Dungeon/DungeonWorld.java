package me.CarsCupcake.SkyblockRemake.Dungeon;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;



public class DungeonWorld {
	String path = Bukkit.getServer().getPluginManager().getPlugin("SkyblockRemake").getDataFolder().getPath() + "/DungeonRooms";

	private File roomsFolder = new File(path);
	
	public Location spawmLocation;
	public World world = Bukkit.getWorld("dungeon");
public DungeonWorld(){
	roomsFolder.mkdir();
	WorldCreator wc = new WorldCreator("dungeon");
	wc.environment(World.Environment.NORMAL);
	wc.type(WorldType.FLAT);
	wc.generateStructures(false);
	
//    wc.generatorSettings("2;0;1;");
	world = Bukkit.createWorld(wc);
	
	 if(!loadRooms()) {
		 Bukkit.broadcastMessage("Failed to load rooms");
	 }else
		 for(Player player :Bukkit.getOnlinePlayers()) {
			 player.teleport(new Location(world, 0, 0, 0));
		 }
	 
	
}

public boolean loadRooms() {

	WorldEditPlugin worldEditPlugin = (WorldEditPlugin)Bukkit.getPluginManager().getPlugin("WorldEdit");
	if(worldEditPlugin == null)
		return false;

	ArrayList<ArrayList<DungeonRoom>> rooms = DungeonGeneration.layers;
	
	for(ArrayList<DungeonRoom> lost : rooms) {

		for(DungeonRoom room : lost) {
//			try {
//
//	            File schematic = new File(path + "/" + room.type.toString() + "/" + room.id + ".schem");
//	            ClipboardFormat format = ClipboardFormats.findByFile(schematic);
//	            
//	                ClipboardReader reader = format.getReader(new FileInputStream(schematic));
//	                Clipboard clipboard = reader.read();
//	                EditSession editSession = WorldEdit.getInstance().newEditSession(BukkitAdapter.adapt(Bukkit.getPlayer("CarsCupcake").getWorld()));
//	                        @SuppressWarnings({  "resource" })
//							Operation operation = new ClipboardHolder(clipboard)
//	                        .createPaste(editSession)
//	                        .to(BlockVector3.at(5*32 - room.x * 32, 50, 5*32 - room.y * 32))
//	                        .ignoreAirBlocks(false)
//	                        .build();
//	                Operations.complete(operation);
//	                editSession.flushQueue();
//	                Bukkit.broadcastMessage("§a A schematic succesfully load Loc: " + (5*32 - room.x * 32) + " " + 50 + " " + (5*32 - room.y * 32));
//	            } catch (Exception e) {
//	                Bukkit.broadcastMessage("§c A schematic failed to load");
//	                System.out.println(e);
//	            }
			File file = new File(path + "/" + room.type.toString() + "/" + room.id + ".schem");
			Clipboard clipboard = null;

			ClipboardFormat format = ClipboardFormats.findByFile(file);
			try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {
			    clipboard = reader.read();
			} catch (FileNotFoundException e) {
				Bukkit.broadcastMessage("§c A schematic failed to load");
				e.printStackTrace();
			} catch (IOException e) {
				Bukkit.broadcastMessage("§c A schematic failed to load");
				e.printStackTrace();
			}
			try (EditSession editSession = WorldEdit.getInstance().newEditSession(BukkitAdapter.adapt(world) )) {

			  
				@SuppressWarnings("resource")

				Operation operation = new ClipboardHolder(clipboard)
				
			            .createPaste(editSession)
			            .to(BlockVector3.at(5*32 - room.x * 32, 50, 5*32 - room.y * 32))
			            
			            .build();
			    Operations.complete(operation);
			    Bukkit.broadcastMessage("§a A schematic succesfully load Loc: " + (5*32 - room.x * 32) + " " + 50 + " " + (5*32 - room.y * 32));
			}catch (Exception e) {
				Bukkit.broadcastMessage("§c A schematic failed to load");
			}
			
			
			
			
		}
		}
	
	return true;
}
  
}

