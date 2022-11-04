package me.CarsCupcake.SkyblockRemake.Tabs;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import me.CarsCupcake.SkyblockRemake.Main;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.network.protocol.game.PacketPlayOutPlayerListHeaderFooter;



public class TabManager {
	
	
	private List<ChatComponentText> headers = new ArrayList<>();
	private List<ChatComponentText> footers = new ArrayList<>();
	
	private Main plugin;
	public TabManager(Main plugin) {
		this.plugin = plugin;
	}
	
	
	public void showTab() {
		if (headers.isEmpty() && footers.isEmpty())
			return;
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            
            @Override
            public void run() {
		PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter(null, null);
		int count1 = 0;
		int count2 = 0;
		try {
			
			Field a = packet.getClass().getDeclaredField("header");
			a.setAccessible(true);
			Field b = packet.getClass().getDeclaredField("footer");
			b.setAccessible(true);
			
			if (count1 >= headers.size())
				count1 = 0;
			if (count2 >= footers.size())
				count2 = 0;
			
			a.set(packet, headers.get(count1));
			b.set(packet, footers.get(count2));
			
			if(Bukkit.getOnlinePlayers().size() != 0) {
				for (Player player : Bukkit.getOnlinePlayers()) 
					((CraftPlayer)player).getHandle().b.sendPacket(packet);
				    
				    
			}
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
            }}, 10, 40);
	}
	
	
	
	
	public void addHeader(String header) {
		headers.add(new ChatComponentText(format(header)));
	}
	
	public void addFooter(String footer) {
		footers.add(new ChatComponentText(format(footer)));
	}
	
	private String format(String msg) {
		return ChatColor.translateAlternateColorCodes('§', msg);
	}
	
	

	
	



}
