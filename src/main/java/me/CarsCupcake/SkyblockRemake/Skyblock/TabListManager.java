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
import net.minecraft.network.protocol.game.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;
import net.minecraft.network.syncher.DataWatcher;
import net.minecraft.network.syncher.DataWatcherRegistry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.EntityPlayer;

import net.minecraft.server.level.WorldServer;
import net.minecraft.server.network.PlayerConnection;


	@SuppressWarnings("unused")

public class TabListManager implements Listener{
	public static HashMap<Player, TabListManager> managers = new HashMap<>();
	private SkyblockPlayer player;
//	private static  HashMap<UUID, String> teams = new HashMap<>();
	private static ArrayList<EntityPlayer> fakePlayers = new ArrayList<>();
	public static HashMap<UUID, String> teams = new HashMap<>();

	
	private ArrayList<EntityPlayer> playerCounts= new ArrayList<>();
	private ArrayList<EntityPlayer> playerSlots= new ArrayList<>();
	private HashMap<Player, EntityPlayer> playerShow = new HashMap<>();
	
	private final EntityPlayer speed;
	private final EntityPlayer strength;
	private final EntityPlayer cc;
	private final EntityPlayer cd;

	private final EntityPlayer as;
	private int count;
	private int maxInt = 0;
	public TabListManager(){speed = null; strength = null; cc = null; cd = null; as=null;}
	public TabListManager(SkyblockPlayer player) {
		managers.put(player, this);
		this.player = player;
		
//		PlayerConnection connection = ((CraftPlayer)player).getHandle().b;
		
		
		
		
		
		
		
		for (int i = 0; i < 19; i++) {
			playerSlots.add(addFakePlayer("   " , 02, TablistIcons.Grey, null));
			
		}
		
		for (int i = 0; i < 19; i++) {
			playerSlots.add(addFakePlayer("§7   " , 04, TablistIcons.Grey, null));
		}
		
		
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
		
		addFakePlayer("§e§lProfile §r§a-", 24+ 1, TablistIcons.Grey, player);
		addFakePlayer(" Pet Sitter §bN/A", 25+ 1, TablistIcons.Grey, player);
		addFakePlayer(" Bank §6" + Tools.toShortNumber((int)SkyblockPlayer.getSkyblockPlayer(player).coins), 26+ 1, TablistIcons.Grey, player);
		addFakePlayer(" Interest §e-", 27+ 1, TablistIcons.Grey, player);
		addFakePlayer("§§§§", 28+ 1, TablistIcons.Grey, player);
		addFakePlayer("§e§lSkills ", 29+ 1, TablistIcons.Grey, player);

	    speed = 	addFakePlayer(" Speed ✦" + Main.playerspeedcalc(player), 30+ 1, TablistIcons.Grey, player);
	    strength = addFakePlayer(" Strength §c❁" + Main.playerstrengthcalc(player), 31+ 1, TablistIcons.Grey, player);
	    cc = addFakePlayer(" Crit Chance §9☣" + Main.playercccalc(player), 32+ 1, TablistIcons.Grey, player);
	    cd = addFakePlayer(" Crit Damage §9☠" + Main.playercdcalc(player), 33+ 1, TablistIcons.Grey, player);
	    as = addFakePlayer(" Attack Speed §e⚔" + Main.playerattackspeed(player), 34+ 1, TablistIcons.Grey, player);
	    addFakePlayer("§§§§§", 35+ 1, TablistIcons.Grey, player);
	    addFakePlayer("§e§lEvent §c-", 36+ 1, TablistIcons.Grey, player);
	    addFakePlayer(" Starts In §eN/A", 37+ 1, TablistIcons.Grey, player);
	    addFakePlayer("§§§§§§", 38+ 1, TablistIcons.Grey, player);
	    addFakePlayer("§e§lElection §bN/A", 39+ 1, TablistIcons.Grey, player);
	    addFakePlayer(" §cCarsCupcake |||||||||| §f(100%)", 41, TablistIcons.Grey, player);
	    addFakePlayer(" §cCarsCupcake |||||||||| §f(100%)", 41, TablistIcons.Grey, player);
	    addFakePlayer(" §cCarsCupcake |||||||||| §f(100%)", 41, TablistIcons.Grey, player);
	    
	    
	    
	    playerCounts.add(addFakePlayer(center("§a§lPlayers §f(" + Bukkit.getOnlinePlayers().size() + ")",maxInt), 01, TablistIcons.Green, null));
		playerCounts.add(addFakePlayer(center("§a§lPlayers §f(" + Bukkit.getOnlinePlayers().size() + ")",maxInt), 03, TablistIcons.Green, null));
		addFakePlayer(center("§2§lServer Info",maxInt), 05, TablistIcons.DarkAqua, null);
		addFakePlayer(center("§6§lAccount Info",maxInt), 23+ 1, TablistIcons.Gold, player);
	    
	    
	  hideAllPlayers();
		
		
		
		
	}
	private String center(String text, int len){
	    String out = String.format("%"+len+"s%s%"+len+"s", "",text,"");
	    float mid = (out.length()/2);
	    float start = mid - (len/2);
	    float end = start + len; 
	    return out.substring((int)start, (int)end);
	}
	
	@EventHandler
	public void hidePlayer(PlayerJoinEvent event) {
		for(TabListManager manager : managers.values()) {
			if(!event.getPlayer().equals(manager.player.getPlayer()))
				manager.playerJoin(event.getPlayer());
		}
	}
	@EventHandler 
	public void hidePlayer(PlayerQuitEvent event) {
		for(TabListManager manager : managers.values()) {
			if(event.getPlayer()!=manager.player.getPlayer())
				manager.removePlayer(player);
		}
		managers.remove(player.getPlayer());
	}
	
	public void updatePlayerCount() {

		for(Player player : Bukkit.getOnlinePlayers()) {
		for(EntityPlayer entityPlayer : playerCounts) {
			String name = "§a§l        Players §f(" + Bukkit.getOnlinePlayers().size() + ")";
			String s = teams.get(entityPlayer.getUniqueID());
			 Team t = player.getScoreboard().getTeam(s);
			 if(t != null) {
				 t.setPrefix(name);
				 System.out.println("renamed");
			 }
		}
		}
	}
	
	public void playerJoin(Player player) {
		int count = Bukkit.getOnlinePlayers().size() -1;
		if(count < 37) {
		replace(playerSlots.get(count), player.getName(), TablistIcons.Players, player);
		playerShow.put(player, playerSlots.get(count));
		}
		updatePlayerCount();
		hidePlayer(player);
	}
	
	
	private void replace(EntityPlayer entityPlayer, String str, TablistIcons icon, Player target) {


			((CraftPlayer)player).getHandle().b.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.e , entityPlayer));

		entityPlayer.getProfile().getProperties().removeAll("textures");
		entityPlayer.getProfile().getProperties().put("textures", new Property("textures", icon.getSkinTexture(target), icon.getSkinSignature(target)));


			((CraftPlayer)player).getHandle().b.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.a , entityPlayer));
			


		
		String s = teams.get(entityPlayer.getUniqueID());
		     Team r = player.getScoreboard().getTeam(s);
			if(r != null)
				r.unregister();
		     System.out.println(s.split("n")[0]);
		     String newS = s.split("n")[0] +"a" + str.substring(1, 5);


				if(player.getScoreboard().getTeam(newS) != null) {
					player.getScoreboard().getTeam(newS).unregister();
				}
				Team team = player.getScoreboard().registerNewTeam(newS);

				team.setPrefix(str);
				team.setSuffix("");
				teams.put(entityPlayer.getUniqueID(), newS);
				update();
		
		
	}
	public void removePlayer(Player p) {
		EntityPlayer entityPlayer = playerShow.get(p);
		try {
			((CraftPlayer) this.player).getHandle().b.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.e, entityPlayer));
		}catch (Exception ignored){}
		
		entityPlayer.getProfile().getProperties().removeAll("textures");
		entityPlayer.getProfile().getProperties().put("textures", new Property("textures", TablistIcons.Grey.getSkinTexture(null), TablistIcons.Grey.getSkinSignature(null)));

		try {
			((CraftPlayer)this.player).getHandle().b.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.a , entityPlayer));
		}catch (Exception ignored){}
			
		
		String s = teams.get(entityPlayer.getUniqueID());
		     player.getScoreboard().getTeam(s).unregister();

		     String newS = s.split("a")[0] +"n" + entityPlayer.getUniqueID().toString().substring(1, 5);
				

				if(player.getScoreboard().getTeam(newS) != null) {
					player.getScoreboard().getTeam(newS).unregister();;
				}
				Team team = player.getScoreboard().registerNewTeam(newS);

				team.setPrefix("  ");
				team.setSuffix("");
				teams.put(entityPlayer.getUniqueID(), newS);
				update();
				playerShow.remove(p);
				updatePlayerCount();
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
		count += 1;
		if(name.length() > maxInt)
			maxInt = name.length();
		
		
		String prefix = "";
		String suffix = "";
		String n = "§§§§";
		String c = count + "";
		for(char ch : c.toCharArray()) {
			Character chare = ch;
			n += "§" + chare;
		}
		
		
		prefix = name;
		
		
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
		
String s = prioString + "n" + entityPlayer.getUniqueID().toString().substring(1, 5);
		

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
		String s = level + "n" + p.getUniqueId().toString().substring(1, 5);
		
		Scoreboard scoreboard = player.getScoreboard();
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
	  for(Player p :Bukkit.getOnlinePlayers()) {
		  
		  playerJoin(p);
		  }
	}

	public void tick(){
		Team t = player.getScoreboard().getTeam(teams.get(strength.getUniqueID()));
		t.setPrefix(" Strength §c❁" + Main.playerstrengthcalc(player));
//
		setName(speed, " Speed ✦" + Main.playerspeedcalc(player));
		setName(as, " Attack Speed §e⚔" + Main.playerattackspeed(player));
		setName(cc, " Crit Chance §9☣" + Main.playercccalc(player));
		setName(cd, " Crit Damage §9☠" + Main.playercdcalc(player));

	}
	private void setName(EntityPlayer eP, String newName){
		Team t = player.getScoreboard().getTeam(teams.get(eP.getUniqueID()));
		t.setPrefix(newName);
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
