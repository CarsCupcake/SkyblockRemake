package me.CarsCupcake.SkyblockRemake.Skyblock;



import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import org.bukkit.scoreboard.ScoreboardManager;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Tools;
import me.CarsCupcake.SkyblockRemake.DwarvenEvents.DwarvenEvent;
import me.CarsCupcake.SkyblockRemake.DwarvenEvents.DwarvenEvents;


public class SkyblockScoreboard implements Listener {
public static HashMap<SkyblockPlayer, Scoreboard> scoreboards = new HashMap<>();

	@EventHandler
	public void sbonJoin(PlayerJoinEvent event) {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				updateScoreboard(event.getPlayer());
				
			}
		}.runTaskLater(Main.getMain(), 5);
		
		
	}
	
	public static void updateScoreboard(Player player) {
		SkyblockPlayer p = SkyblockPlayer.getSkyblockPlayer(player);

		ScoreboardDisplayer.setScore(p,ChatColor.GRAY + "--/--/-- " + ChatColor.DARK_GRAY + "m2",14);

		ScoreboardDisplayer.setScore(p,"§r   ",13);

		ScoreboardDisplayer.setScore(p,ChatColor.WHITE + " /jahreszeit/ 0th",12);

		ScoreboardDisplayer.setScore(p,ChatColor.GRAY + " 00:00am " + ChatColor.AQUA + "☽",11);
	
		if(p.dwarvenArea == null)
			if(Main.getMain().getServer().getPort() == 25564)
				ScoreboardDisplayer.setScore(p,ChatColor.GRAY + "⏣ §2Dwarven Mines",10);
				else
					ScoreboardDisplayer.setScore(p,ChatColor.GRAY + "⏣ None",10);
		else
			ScoreboardDisplayer.setScore(p,ChatColor.GRAY + "⏣ " + p.dwarvenArea.getString(),10);
		
		ScoreboardDisplayer.setScore(p,"§c   ",9);

		/*ChatColor.GOLD + Tools.addDigits( (int) p.coins)*/
		try {
			ScoreboardDisplayer.setScore(p, ChatColor.WHITE + "Purse: " + "§6" + Tools.addDigits(p.coins), 8);
		}
		catch(Exception e){
			ScoreboardDisplayer.setScore(p, ChatColor.WHITE + "Purse: " + "§6" + Tools.toShortNumber(p.coins), 8);
		}
		if(p.showMithrilPowder)
			ScoreboardDisplayer.setScore(p,"§2᠅ §fMithril: §2" +Tools.addDigits(p.mithrilpowder),8);

		if(DwarvenEvent.ActiveEvent != null) {
			if(DwarvenEvent.ActiveEvent.getEvent() == DwarvenEvents.GoneWithTheWind) {
				ScoreboardDisplayer.setScore(p,"§9Gone With The Wind",5);
				ScoreboardDisplayer.setScore(p,getGoneWithTheWindString(p),4);

				
			}
			}
			ScoreboardDisplayer.setScore(p,ChatColor.WHITE + "Bits: " + ChatColor.AQUA + p.bits,7);
		

			ScoreboardDisplayer.setScore(p,"§r ",6);

		
		
		
		
		
		
		
		
			ScoreboardDisplayer.setScore(p,ChatColor.YELLOW + "localhost:" + Main.getMain().getServer().getPort(),1);


		

		
		
		
		
	}
	
	public static void createScoreboard(SkyblockPlayer player) {
		SkyblockPlayer p = SkyblockPlayer.getSkyblockPlayer(player);
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		assert manager != null;
		Scoreboard board = (Scoreboard) manager.getNewScoreboard();
		Objective obj = ((org.bukkit.scoreboard.Scoreboard) board).registerNewObjective("SkyBlockBoard", "dummy", ChatColor.BOLD + "§6§lSKYBLOCK");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		player.setScoreboard((org.bukkit.scoreboard.Scoreboard) board);
		ScoreboardDisplayer.setScore(p,ChatColor.GRAY + "--/--/-- " + ChatColor.DARK_GRAY + "m2",14);

		ScoreboardDisplayer.setScore(p,"§r   ",13);

		ScoreboardDisplayer.setScore(p,ChatColor.WHITE + " /jahreszeit/ 0th",12);

		ScoreboardDisplayer.setScore(p,ChatColor.GRAY + " 00:00am " + ChatColor.AQUA + "˜½",11);
	
		if(p.dwarvenArea == null)
			if(Main.getMain().getServer().getPort() == 25564)
				ScoreboardDisplayer.setScore(p,ChatColor.GRAY + "?£ §2Dwarven Mines",10);
				else
					ScoreboardDisplayer.setScore(p,ChatColor.GRAY + "?£ None",10);
		else
			ScoreboardDisplayer.setScore(p,ChatColor.GRAY + "?£ " + p.dwarvenArea.getString(),10);
		
		ScoreboardDisplayer.setScore(p,"§c   ",9);

		/*ChatColor.GOLD + Tools.addDigits(  p.coins)*/
		try {
			ScoreboardDisplayer.setScore(p, ChatColor.WHITE + "Purse: " + "§6" + Tools.addDigits(player.coins), 8);
		}
		catch(Exception e){
			e.printStackTrace();
			ScoreboardDisplayer.setScore(p, ChatColor.WHITE + "Purse: " + "§6" + Tools.toShortNumber(player.coins), 8);
		}
		if(p.showMithrilPowder)
			ScoreboardDisplayer.setScore(p,"§2á … §fMithril: §2" +Tools.addDigits(p.mithrilpowder),8);

		if(DwarvenEvent.ActiveEvent != null) {
			if(DwarvenEvent.ActiveEvent.getEvent() == DwarvenEvents.GoneWithTheWind) {
				ScoreboardDisplayer.setScore(p,"§9Gone With The Wind",5);
				ScoreboardDisplayer.setScore(p,getGoneWithTheWindString(p),4);

				
			}
			}
			ScoreboardDisplayer.setScore(p,ChatColor.WHITE + "Bits: " + ChatColor.AQUA + p.bits,7);
		

			ScoreboardDisplayer.setScore(p,"§r ",6);

		
		
		
		
		
		
		
		
			ScoreboardDisplayer.setScore(p,ChatColor.YELLOW + "localhost:" + Main.getMain().getServer().getPort(),1);


		

		
		
		scoreboards.put(p, board);
	}
	
	
	public static String getGoneWithTheWindString(SkyblockPlayer player) {
		int dir = DwarvenEvent.ActiveEvent.getGoneWithTheWindYaw();
		int yaw =(int) player.getLocation().getYaw();
		
		if(Tools.isInRange(dir + 20, dir - 20, yaw)) {
			
			int spaces = (dir-yaw)/2;

			String finalstring = " ";
			int totspaces = 10+spaces;
			
				
				for(int i = 0; i < totspaces; i++)
					finalstring += " ";
				finalstring += getWindColor(yaw) + "‰ˆ";
					
				
			
				
			return finalstring;
			
		}else {
			if(yaw < dir) {
				return "                      >";
			}else
				return "<";
				
		}
	}
	public static String getWindColor(int yaw) {
		int dir = DwarvenEvent.ActiveEvent.getGoneWithTheWindYaw();
		if(Tools.isInRange(dir + 5, dir - 5, yaw))
			return "§2";
		if(Tools.isInRange(dir + 15, dir - 15, yaw))
			return "§a";
		
		
		return "";
	}
	
	
} 
