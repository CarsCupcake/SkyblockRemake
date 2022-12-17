package me.CarsCupcake.SkyblockRemake;

import java.util.HashMap;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.MiningSystem.MiningBlock;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.game.PacketPlayOutBlockBreakAnimation;

public class MiningSys {
	private static HashMap<SkyblockPlayer, MiningSys> mines = new HashMap<>();
	@Getter
	private static HashMap<Material, Class<? extends MiningBlock>> registeredBlocks = new HashMap<>();
private final SkyblockPlayer player;
private BukkitRunnable runn;
@Getter
private me.CarsCupcake.SkyblockRemake.MiningSystem.MiningBlock block;
public int ticks;
public Block b;
private boolean hasStoped = false;

public MiningSys(SkyblockPlayer player) {
	this.player = player;
	mines.put(player, this);
}
public void stopMinig() {
	try {
		hasStoped = true;
	runn.cancel();
	PacketPlayOutBlockBreakAnimation animation = new PacketPlayOutBlockBreakAnimation(1,new BlockPosition(b.getX(), b.getY(), b.getZ()), 10);
    player.getHandle().b.sendPacket(animation);
	}catch (Exception ignored) {}
}
public static MiningSys getMiningSystem(SkyblockPlayer player) {
	return mines.get(player);
}
public static MiningSys getMiningSystem(Player player) {
	return mines.get(SkyblockPlayer.getSkyblockPlayer(player));
}
@SuppressWarnings("deprecation")
public void startMining(Block block) {
	if(!checkValid(block)) return;
	
	if(player.getItemInHand().getItemMeta() != null){
	ItemManager manager = Items.SkyblockItems.get(player.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING));
	
	if(manager != null && manager.type == ItemType.Drill) {
		if(player.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fuel"), PersistentDataType.INTEGER) <= 0)
			return;
	}
	}


	try {
		this.block = registeredBlocks.get(block.getType()).newInstance();
	}catch (Exception e){
		e.printStackTrace();
		return;
	}
	if(this.block == null)
		return;
	if(this.block.getBreakingPower() > Main.playerbreakingpower(player)) {
		return;
	}
	SkyblockPlayer p =  SkyblockPlayer.getSkyblockPlayer(player);
	final int ticks;
	double speed = Main.getPlayerMiningSpeed(p.getPlayer());
	if(speed >= this.block.getSoftCap()) {
		if(speed >= this.block.getInstaMineSpeed())
			ticks = 1;
		else
			ticks = 4;
		

	}else
		ticks = this.block.getMiningTicks(player);
	
	this.ticks = ticks;
	b = block;

	runn = new BukkitRunnable() {
		public int ti = ticks;
		@Override
		public void run() {
			if(!b.getLocation().equals(block.getLocation()) || runn.isCancelled())
				return;
				
			int t = ti;
			t -= 1;
			PacketPlayOutBlockBreakAnimation animation = new PacketPlayOutBlockBreakAnimation(1,new BlockPosition(block.getX(), block.getY(), block.getZ()), getBlockBreakStage(ticks,ticks- t));

            MiningSys.this.ticks = t;
            ti = t;
            if(t <= 0) {
            	
            	this.cancel();
            	
            	p.breakBlock(block);
            	
            	
            	 animation = new PacketPlayOutBlockBreakAnimation(1,new BlockPosition(block.getX(), block.getY(), block.getZ()), 10);


				new BukkitRunnable()
				{
					@Override
					public void run() {
						if (checkValid(b.getLocation().getBlock()) && !hasStoped && MiningSys.this.block.getBreakingPower() <= Main.playerbreakingpower(player)) {
							startMining(block.getLocation().getBlock());
						}
					}
					}.runTaskLater(Main.getMain(), 1);
				
			
			 
           	
					
            	
            }
			for (Player pp : Bukkit.getOnlinePlayers())
			  ((CraftPlayer)pp).getHandle().b.sendPacket(animation);
		}
	};
	runn.runTaskTimer(Main.getMain(), 0, 1);
	

	
}
public SkyblockPlayer getPlayer() {

	return player;
	
}







public int getBlockBreakStage(int totalTicks, int currentTick) {
	
	double result =	((double)currentTick/(double)totalTicks)*10;


	return (int)result;
	}
	
	
	
	/*public int getBlockBreakingPower(Block block) {
		if(block.getType()== Material.STONE || block.getType()== Material.COBBLESTONE || block.getType() == Material.COAL_ORE|| block.getType() == Material.GLOWSTONE)
			return 1;
		if(block.getType() == Material.IRON_ORE || block.getType() == Material.LAPIS_ORE)
			return 2;
		if( block.getType() == Material.GOLD_ORE || block.getType() == Material.DIAMOND_ORE)
			return 3;
		if(block.getType() == Material.GRAY_WOOL || block.getType() == Material.CYAN_TERRACOTTA || block.getType() == Material.PRISMARINE || block.getType() == Material.DARK_PRISMARINE || block.getType() == Material.PRISMARINE_BRICKS || block.getType() == Material.LIGHT_BLUE_WOOL)
			return 4;
		if(block.getType() == Material.POLISHED_DIORITE)
			return 5;
		
		
		return 0;
	}
	
	
	
	public int[] getCustomBlockData(Block block) {
		//1. is hardenes 2. is insta break 
		int[] result = {0,0};
		
		if(block.getType()== Material.STONE) {
			result[0] = 15;
			result[1] = 450;
		}
		if(block.getType()== Material.COBBLESTONE) {
			result[0] = 20;
			result[1] = 600;
		}
		if(block.getType() == Material.COAL_ORE || block.getType() == Material.IRON_ORE || block.getType() == Material.GOLD_ORE || block.getType() == Material.DIAMOND_ORE|| block.getType() == Material.GLOWSTONE || block.getType() == Material.LAPIS_ORE || block.getType() == Material.EMERALD_ORE) {
			result[0] = 30;
			result[1] = 1800;
		}
		//grey mithril
		if(block.getType() == Material.GRAY_WOOL || block.getType() == Material.CYAN_TERRACOTTA) {
			result[0] = 500;
			result[1] = 30000;
		}
		if(block.getType() == Material.PRISMARINE || block.getType() == Material.DARK_PRISMARINE || block.getType() == Material.PRISMARINE_BRICKS) {
			result[0] = 800;
			result[1] = 48000;
		}
		if(block.getType() == Material.LIGHT_BLUE_WOOL) {
			result[0] = 1500;
			result[1] = 90000;
		}
		if(block.getType() == Material.POLISHED_DIORITE) {
			result[0] = 2000;
			result[1] = 120000;
		}
		
		return result;
	}
	
	public int estimateBreakingTime(Player player, double blockStrength) {
		double mining_speed = Main.getPlayerMiningSpeed(player);
		double SoftCap=Tools.round(6.66666666666666666666666666666666666*blockStrength, 0);
		if(SoftCap <= mining_speed)
			mining_speed = SoftCap;
		
		double MiningTime = (blockStrength * 30)/mining_speed;
		
		
			
		
		return (int) MiningTime;
	}
   
	*/
		public boolean checkValid(Block block) {
			return registeredBlocks.containsKey(block.getType());
		}


}
