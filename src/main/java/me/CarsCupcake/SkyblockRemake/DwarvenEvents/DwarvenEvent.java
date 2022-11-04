package me.CarsCupcake.SkyblockRemake.DwarvenEvents;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Tools;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockScoreboard;

public class DwarvenEvent {
	public static DwarvenEvent ActiveEvent;
	
	
	
	private DwarvenEvents event;
	private BukkitRunnable runn;
	//time in seconds (20 Ticks)
	private int time;
	private BossBar bar;
	
	
	//if the event is gone with the wind
	private int windDir = new Random().nextInt(300)-140;
	private int changeWindDir;
	
	
	public DwarvenEvent(DwarvenEvents event) {
		this.event = event;
		time = 60*20;
		changeWindDir = 60;
		bar = Main.getMain().getServer().createBossBar("§e§lPASSIVE EVENT §9GONE WITH THE WIND §e§lRUNNING FOR §a20:00", BarColor.WHITE, BarStyle.SOLID, BarFlag.CREATE_FOG);
		for(Player player : Bukkit.getOnlinePlayers())
		bar.addPlayer(player);
		bar.setProgress(1);
		runnable();
		
		ActiveEvent = this;
		
	}
	public DwarvenEvents getEvent() {
		return event;
	}
	public void cancleEvent() {
		runn.cancel();
		event = null;
		ActiveEvent = null;
		bar.removeAll();
		for(Player player : Bukkit.getOnlinePlayers())
			SkyblockScoreboard.updateScoreboard(player);
	}
	//if there is no gwtw event it will return 0
	public int getGoneWithTheWindStatBoost(double Yaw) {
		
		if(event != DwarvenEvents.GoneWithTheWind)
			return 0;
		
		if(Tools.isInRange(windDir + 5, windDir -5, Yaw))
			return 600;
		
		if(Tools.isInRange(windDir +15, windDir -15, Yaw))
			return 300;
		return 0;
		
	}
	private void changeWindDirection() {
		windDir = new Random().nextInt(300)-140;
	}
	
	public int getGoneWithTheWindYaw() {
		return windDir;
	}
	
	private void runnable() {
		runn = new BukkitRunnable() {
			
			@Override
			public void run() {
				time -= 1;
				
				if(time == 0) {
					cancleEvent();
				}
				
				changeWindDir -= 1;
				if(changeWindDir == 0) {
					changeWindDir = new Random().nextInt(100)+20;
					Bukkit.broadcastMessage("§aThe wind has changed Direction!");
					changeWindDirection();
					for(Player player : Bukkit.getOnlinePlayers())
						SkyblockScoreboard.updateScoreboard(player);
				}
				
				int duration = time;
				int hours = 0;
				int minutes = 0;
				int seconds = 0;
				String min = "00";
				String sec = "00";
				if (duration / 60 / 60 / 24 >= 1) {
			        duration -= duration / 60 / 60 / 24 * 60 * 60 * 24;
			    }
			    if (duration / 60 / 60 >= 1) {
			        hours = duration / 60 / 60;
			        duration -= duration / 60 / 60 * 60 * 60;
			    }
			    if (duration / 60 >= 1) {
			        minutes = duration / 60;
			        duration -= duration / 60 * 60;
			    }
			    if (duration >= 1)
			        seconds = duration;
			    minutes += hours*60;
				    
				    if(minutes >9) {
						   min = minutes + "";
					   }
					   if(minutes <10 && minutes != 0) {
						   min = "0" + minutes;
						   
					   }
					   if(seconds >9) {
						   sec = seconds + "";
					   }
					   if(seconds <10 && seconds != 0) {
						   sec = "0" + seconds;
						   
					   }
					   bar.setTitle("§e§lPASSIVE EVENT §9§lGONE WITH THE WIND §eRUNNING FOR §a§l" + min + ":" +sec);
				
			}
		};
		runn.runTaskTimer(Main.getMain(), 0, 20);
	}
	
}
