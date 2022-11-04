package me.CarsCupcake.SkyblockRemake.Skyblock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;


import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Tools;
import net.minecraft.network.protocol.game.PacketPlayOutEntityMetadata;
import net.minecraft.network.protocol.game.PacketPlayOutPlayerInfo;
import net.minecraft.network.syncher.DataWatcher;
import net.minecraft.network.syncher.DataWatcherRegistry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.EntityPlayer;

import net.minecraft.server.level.WorldServer;
import net.minecraft.server.network.PlayerConnection;


	@SuppressWarnings("unused")

public class TabListManagerCopie implements Listener{
	public static HashMap<Player, TabListManagerCopie> managers = new HashMap<>();
	private Player player;
//	private static  HashMap<UUID, String> teams = new HashMap<>();
	private static ArrayList<EntityPlayer> fakePlayers = new ArrayList<>();
	public static HashMap<UUID, String> teams = new HashMap<>();

	
	private ArrayList<EntityPlayer> playerCounts= new ArrayList<EntityPlayer>();
	private ArrayList<EntityPlayer> playerSlots= new ArrayList<EntityPlayer>();
	
	private EntityPlayer speed;
	private EntityPlayer strength;
	private EntityPlayer cc;
	private EntityPlayer cd;

	private EntityPlayer as;
	
	public TabListManagerCopie(SkyblockPlayer player) {
		managers.put(player, this);
		this.player = player;
		
//		PlayerConnection connection = ((CraftPlayer)player).getHandle().b;
		
		hideAllPlayers();
		
		
		playerCounts.add(addFakePlayer("§a§lPlayers §f(" + Bukkit.getOnlinePlayers().size() + ")", 01, TablistIcons.Green, null));
		
		
		for (int i = 0; i < 19; i++) {
			playerSlots.add(addFakePlayer("   " , 02, TablistIcons.Grey, null));
			
		}playerCounts.add(addFakePlayer("§a§lPlayers §f(" + Bukkit.getOnlinePlayers().size() + ")§7", 03, TablistIcons.Green, null));
		
		
		for (int i = 0; i < 19; i++) {
			playerSlots.add(addFakePlayer("§7   " , 04, TablistIcons.Grey, null));
		}
		
		addFakePlayer("§2§lServer Info", 05, TablistIcons.DarkAqua, null);
		addFakePlayer("§b§lArea §r§7Dwarven Mines", 06, TablistIcons.Grey, null);
		addFakePlayer(" Server §7m2", 07, TablistIcons.Grey, null);
		addFakePlayer(" Gems §aN/§aA", 07 + 1, TablistIcons.Grey, null);
		addFakePlayer("§", 8+ 1, TablistIcons.Grey, null);
		addFakePlayer("§9§l᠅ Powders", 9+ 1, TablistIcons.Grey, null);
		addFakePlayer(" Mithril Powder " + player.mithrilpowder, 10+ 1, TablistIcons.Grey, player);
		addFakePlayer(" Gemstone Powder §d-", 11+ 1, TablistIcons.Grey, player);
		addFakePlayer("§§", 12+ 1, TablistIcons.Grey, player);
		addFakePlayer("§9§lComissions", 13+ 1, TablistIcons.Grey, player);
		addFakePlayer(" " + player.Comms.get(0).getComm() + " §c" +Tools.round( player.Comms.get(0).getPersentage(),1) + "%", 14+ 1, TablistIcons.Grey, player);
		addFakePlayer(" " + player.Comms.get(1).getComm() + " §c" +Tools.round( player.Comms.get(1).getPersentage(),1) + "%", 15+ 1, TablistIcons.Grey, player);
		addFakePlayer("§§§", 16+ 1, TablistIcons.Grey, player);
		addFakePlayer("§§§", 16+ 1, TablistIcons.Grey, player);
		addFakePlayer("§9§lForges", 17+ 1, TablistIcons.Grey, player);
		addFakePlayer(" 1) §7EMPTY", 18+ 1, TablistIcons.Grey, player);
		addFakePlayer(" 2) §7EMPTY", 19+ 1, TablistIcons.Grey, player);
		addFakePlayer(" 3) §7EMPTY", 20+ 1, TablistIcons.Grey, player);
		addFakePlayer(" 4) §7EMPTY", 21+ 1, TablistIcons.Grey, player);
		addFakePlayer(" 5) §7EMPTY", 22+ 1, TablistIcons.Grey, player);
		addFakePlayer("§6§lAccount Info", 23+ 1, TablistIcons.Gold, player);
		addFakePlayer("§e§lProfile §r§a-", 24+ 1, TablistIcons.Grey, player);
		addFakePlayer(" Pet Sitter §bN/A", 25+ 1, TablistIcons.Grey, player);
		addFakePlayer(" Bank §6" + Tools.toShortNumber((int)SkyblockPlayer.getSkyblockPlayer(player).coins), 26+ 1, TablistIcons.Grey, player);
		addFakePlayer(" Interest §e-", 27+ 1, TablistIcons.Grey, player);
		addFakePlayer("§§§§", 28+ 1, TablistIcons.Grey, player);
		addFakePlayer("§e§lSkills ", 29+ 1, TablistIcons.Grey, player);

	    speed = 	addFakePlayer(" Speed §l✦" + Main.playerspeedcalc(player), 30+ 1, TablistIcons.Grey, player);
	    strength = addFakePlayer(" Strength §c§??" + Main.playerstrengthcalc(player), 31+ 1, TablistIcons.Grey, player);
	    cc = addFakePlayer(" Crit Chance §9☣" + Main.playercccalc(player), 32+ 1, TablistIcons.Grey, player);
	    cd = addFakePlayer(" Crit Damage §9☠" + Main.playercdcalc(player), 33+ 1, TablistIcons.Grey, player);
	    as = addFakePlayer(" Attack Speed §e⚔" + Main.playerattackspeed(player), 34+ 1, TablistIcons.Grey, player);
	    addFakePlayer("§§§§§", 35+ 1, TablistIcons.Grey, player);
	    addFakePlayer("§e§lEvent §c-", 36+ 1, TablistIcons.Grey, player);
	    addFakePlayer(" Starts In §eN/A", 37+ 1, TablistIcons.Grey, player);
	    addFakePlayer("§§§§§§", 38+ 1, TablistIcons.Grey, player);
	    addFakePlayer("§e§lElection §bN/A", 39+ 1, TablistIcons.Grey, player);
	    addFakePlayer(" §cCarsCupcake ---------- §f(100%)", 41, TablistIcons.Grey, player);
	    addFakePlayer(" §cCarsCupcake ---------- §f(100%)", 41, TablistIcons.Grey, player);
	    addFakePlayer(" §cCarsCupcake ---------- §f(100%)", 41, TablistIcons.Grey, player);
	    
	  
		
		
		
		
	}
	
	@EventHandler
	public void hidePlayer(PlayerJoinEvent event) {
		for(TabListManagerCopie manager : managers.values()) {
			manager.hidePlayer(player);
			manager.updatePlayerCount();
		}
	}
	@EventHandler 
	public void hidePlayer(PlayerQuitEvent event) {
		for(TabListManagerCopie manager : managers.values()) {
			
			manager.updatePlayerCount();
		}
	}
	
	public void updatePlayerCount() {
		int i = 0;
		for(Player player : Bukkit.getOnlinePlayers()) {
		for(EntityPlayer entityPlayer : playerCounts) {
			
			String name = "§a§lPlayers §f(" + Bukkit.getOnlinePlayers().size() + ")";
			if(i == 1)
				name += "§7";
			
			
			String prefix = "";
			String suffix = "";
			String n = "";
			if(name.length() <= 16)
				n = name;
			else {
				String[] str = name.split("(?<=\\G................)");
				prefix = str[0];
				n = str[1];
				if(str.length == 3) {
					suffix = str[2];
				}
				
				
				
			}
			
			String s = teams.get(entityPlayer.getUniqueID());
			player.getScoreboard().getTeam(s).setPrefix(prefix);
			player.getScoreboard().getTeam(s).setSuffix(suffix);
			entityPlayer.getBukkitEntity().setCustomName(name);
			entityPlayer.getBukkitEntity().setDisplayName(name);
			entityPlayer.getBukkitEntity().setPlayerListName(name);
			
			
			i++;
		}
		}
	}
	
	
	@SuppressWarnings("deprecation")
	private void update() {
		for(EntityPlayer entityPlayer :  fakePlayers) {
			
			if(teams.get(entityPlayer.getUniqueID()) == null) {
				continue;
			}
			if(player.getScoreboard().getTeam(teams.get(entityPlayer.getUniqueID())) == null)
				continue;
			
			
			player.getScoreboard().getTeam(teams.get(entityPlayer.getUniqueID())).addPlayer(entityPlayer.getBukkitEntity());
		}
		teams.forEach((uuid, s)->{
			if(Bukkit.getPlayer(uuid) != null && player.getScoreboard().getTeam(s) != null) {
				player.getScoreboard().getTeam(s).addPlayer(Bukkit.getPlayer(uuid));
			}
		});
		
	}
	
	

	private EntityPlayer addFakePlayer(String name, int Priority, TablistIcons icon, Player target) {
		
		
		
		String prefix = "";
		String suffix = "";
		String n = "";
		if(name.length() <= 16)
			n = name;
		else {
			String[] str = name.split("(?<=\\G................)");
			prefix = str[0];
			n = str[1];
			if(str.length == 3) {
				suffix = str[2];
			}
			
			
			
		}
		
		Location spawnLocation = new Location(player.getWorld(), 0, 0, 0);
		MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
		WorldServer world = ((CraftWorld) spawnLocation.getWorld()).getHandle();
		GameProfile gameProfile = new GameProfile(UUID.randomUUID(), n);
		
		    
		EntityPlayer entityPlayer = new EntityPlayer(server, world, gameProfile);
		gameProfile.getProperties().put("textures", new Property("textures", icon.getSkinTexture(target), icon.getSkinSignature(target)));
		PlayerConnection connection = ((CraftPlayer)player).getHandle().b;
		
		
		
		connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, entityPlayer));

		DataWatcher watcher = entityPlayer.getDataWatcher();
byte b = 0x01 | 0x02 | 0x04 | 0x08 | 0x10 | 0x20 | 0x40;
watcher.set(DataWatcherRegistry.a.a(17),b);
connection.sendPacket(new PacketPlayOutEntityMetadata(entityPlayer.getId(), watcher, false));
		
		
		String prioString = Priority + "";
		if(Priority < 10) {
			prioString = "0" + Priority;
		}
		
String s = prioString + entityPlayer.getUniqueID().toString().substring(1, 6);
		

		if(player.getScoreboard().getTeam(s) != null) {
			player.getScoreboard().getTeam(s).unregister();;
		}
		Team team = player.getScoreboard().registerNewTeam(s);

		team.setPrefix(prefix);
		team.setSuffix(suffix);

		
		
		


		teams.put(entityPlayer.getUniqueID(), s);
		fakePlayers.add(entityPlayer);
		update();
		return entityPlayer;
		
		
	}
	

	
	
	public void removePlayers() {
		for(EntityPlayer p : fakePlayers) {
			
			PlayerConnection connection = ((CraftPlayer)player).getHandle().b;
			connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.e, p));
		}
	}
	public void registerTeam(Player p, String prefix, ChatColor color, String suffix, int level) {
		String s = level + p.getUniqueId().toString().substring(1, 6);
		
		Scoreboard scoreboard = p.getScoreboard();
		if(scoreboard == null)
			scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		if(scoreboard.getTeam(s) != null) {
			scoreboard.getTeam(s).unregister();;
		}
		Team team = scoreboard.registerNewTeam(s);
		team.setColor(color);
		team.setPrefix(prefix + " ");
		team.setSuffix(suffix);
		
//		p.setPlayerListName(prefix + " " + color +p.getName());
//		p.setScoreboard(scoreboard);
		
		teams.put(p.getUniqueId(), s);
		
		update();
	}
	public void hidePlayer(Player player) {
		registerTeam(player, "", ChatColor.WHITE, "", 99);
	}
	public void hideAllPlayers() {
	  for(Player p :Bukkit.getOnlinePlayers())
		  hidePlayer(p);
	}
	
	
	
	
//	private void registerTeam(Player player, int level) {
//		String s = "a" +level + "a";
//		
//		
//		if(scoreboard.getTeam(s) != null) {
//			scoreboard.removeTeam(scoreboard.getTeam(s));
//		}
//		ScoreboardTeam team = scoreboard.createTeam(s);
//
//		team.setPrefix(new ChatComponentText(""));
//		team.setSuffix(new ChatComponentText(""));
//		
//		teams.put(player.getUniqueId(), s);
//		update();
//	}
//	private void registerTeam(EntityPlayer player, int level) {
//		String s = level + "";
//		
//		
//		if(scoreboard.getTeam(s) != null) {
//			scoreboard.removeTeam(scoreboard.getTeam(s));
//		}
//		ScoreboardTeam team = scoreboard.createTeam(s);
//
//		team.setPrefix(new ChatComponentText(""));
//		team.setSuffix(new ChatComponentText(""));
//		team.a(level);
//		scoreboard.addPlayerToTeam(player.getName(), team);
//		teams.put(player.getUniqueID(), s);
//		
//		
//		update();
//	}
//	
//	private void update() {
//		for(Player p : Bukkit.getOnlinePlayers()) {
//			if(!scoreboard.getTeam(teams.get(p.getUniqueId())).getPlayerNameSet().contains(p.getName())) {
//				scoreboard.getTeam(teams.get(p.getUniqueId())).getPlayerNameSet().add(p.getName());
//				
//				
//				
//			}
			
			
			
			
			

			
//		}
		
//	}
	
	
	
	
	

}
