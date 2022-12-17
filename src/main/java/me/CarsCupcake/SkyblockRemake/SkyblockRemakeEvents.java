package me.CarsCupcake.SkyblockRemake;



import java.rmi.server.Skeleton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import me.CarsCupcake.SkyblockRemake.API.HealthChangeReason;
import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.FishingSystem.LavaFishingHook;
import me.CarsCupcake.SkyblockRemake.NPC.EntityNPC;
import me.CarsCupcake.SkyblockRemake.Skyblock.*;
import me.CarsCupcake.SkyblockRemake.utils.SignGUI.SignGUI;
import me.CarsCupcake.SkyblockRemake.utils.SignGUI.SignManager;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.CommandBlock;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;

import org.bukkit.craftbukkit.v1_17_R1.entity.CraftEnderDragonPart;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;

import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;

import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.CarsCupcake.SkyblockRemake.AccessoryBag.Powers.Powers;
import me.CarsCupcake.SkyblockRemake.Commission.Commission;
import me.CarsCupcake.SkyblockRemake.Commission.Puzzler;
import me.CarsCupcake.SkyblockRemake.Configs.ConfigFile;
import me.CarsCupcake.SkyblockRemake.Configs.PetMenus;
import me.CarsCupcake.SkyblockRemake.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Items.SpawnEggEntitys;
import me.CarsCupcake.SkyblockRemake.NPC.NPC;
import me.CarsCupcake.SkyblockRemake.NPC.PacketReader;
import me.CarsCupcake.SkyblockRemake.Pets.Pet;
import me.CarsCupcake.SkyblockRemake.Pets.PetFollowRunner;
import me.CarsCupcake.SkyblockRemake.Skyblock.terminals.maze;
import me.CarsCupcake.SkyblockRemake.Skyblock.terminals.order;
import me.CarsCupcake.SkyblockRemake.abilitys.HydraStrike;
import me.CarsCupcake.SkyblockRemake.cmd.itemCMD;

import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.protocol.game.PacketPlayInBlockDig;
import net.minecraft.network.protocol.game.PacketPlayInBlockDig.EnumPlayerDigType;
import net.minecraft.network.protocol.game.PacketPlayOutEntity;
import net.minecraft.network.protocol.game.PacketPlayOutEntityHeadRotation;

import net.minecraft.server.network.PlayerConnection;
import org.jetbrains.annotations.NotNull;


@SuppressWarnings("deprecation")
public class SkyblockRemakeEvents implements Listener{
	public static Inventory SoulShopInv;
	public static Inventory SoulReforging;
	public static Inventory SoulBuyInv;
	public String gm;

	public HashMap<Player,MiningBlock> breakTicksLeft = new HashMap<>();
	public static boolean iteminforge = false;
	public static int soulcost;
	public static HashMap<Location, Material> TitaniumRegen = new HashMap<>();
	public static HashMap<Location, Titanium> TitaniumObject = new HashMap<>();


	public SkyblockRemakeEvents Events;
	@EventHandler
	public void onTNT(EntityExplodeEvent event){
		event.setCancelled(true);
	}
	@EventHandler
	public void playerdrop(PlayerDropItemEvent event) {
		event.getItemDrop().addScoreboardTag("player");
	}
	public static void readBeakBlock(PacketPlayInBlockDig packet,Player player) {

		Location loc = new Location(player.getWorld(), packet.b().getX(), packet.b().getY(), packet.b().getZ());

		Bukkit.getScheduler().runTask(Main.getMain(), () ->{
			if (packet.d() == EnumPlayerDigType.a)
				MiningSys.getMiningSystem(player).startMining(loc.getBlock());
			if (packet.d() == EnumPlayerDigType.b || packet.d() == EnumPlayerDigType.c)
				MiningSys.getMiningSystem(player).stopMinig();
		});
		
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		player.getInventory().setItem(8, Items.SkyblockMenu());
		Main.absorbtion.put(player, 0);
		Main.absorbtionrunntime.put(player, 0);
		Main.shortbow_cd.put(player, false);
		Main.termhits.put(player, 0);
		Powers.initPower(player);
		Main.initAccessoryBag(player);
		
		new SkyblockPlayer((CraftServer) Main.getMain().getServer(),((CraftPlayer )event.getPlayer()).getHandle());
		
		if(PetMenus.get().getConfigurationSection(player.getUniqueId().toString()) == null ||!PetMenus.get().getConfigurationSection(player.getUniqueId().toString()).getKeys(false).contains("equiped")) {
			PetMenus.get().set(player.getUniqueId() + ".equiped", 0);
			PetMenus.save();
			PetMenus.reload();
		}
		
		if(PetMenus.get().getInt(player.getUniqueId() + ".equiped") != 0) {
			new PetFollowRunner(player, Pet.pets.get(PetMenus.get().getString(player.getUniqueId() + "." + PetMenus.get().getInt(player.getUniqueId() + ".equiped")    + ".id") ) , PetMenus.get().getInt(player.getUniqueId() + ".equiped"));
		}
		
		player.setBedSpawnLocation(new Location(Bukkit.getServer().getWorld("world"), -2.5, 70, -69.5,180, 0));
		
		Main.getMain().reloadConfig();;
		if(event.getPlayer().getServer().getPort() == 25565)
		player.teleport(new Location(Bukkit.getServer().getWorld("world"), -2.5, 70, -69.5,180, 0));
		if(event.getPlayer().getServer().getPort() == 25564 || !Main.isLocalHost)
			player.teleport(new Location(Bukkit.getServer().getWorld("world"), -48.5, 200, -121.5,270, 0));
		
		if(event.getPlayer().getServer().getPort() == 25570)
			player.teleport(new Location(Bukkit.getServer().getWorld("world"), -29.5, 121, 0.5,90, 0));
		
		
		
		//Crimson Isle
		if(event.getPlayer().getServer().getPort() == 25568)
			player.teleport(new Location(Bukkit.getServer().getWorld("world_nether"), -360.5, 80, -426.5,180, 0));
		//The Instance
		if(event.getPlayer().getServer().getPort() == 25569)
			player.teleport(new Location(Bukkit.getServer().getWorld("world"), -101.5, 41, -185.5,0, 0));
		if(event.getPlayer().getServer().getPort() == 25566)
			player.teleport(new Location(Bukkit.getServer().getWorld("dungeon"), 0,0,0,0, 0));
		//F1 Bossroom
		if(event.getPlayer().getServer().getPort() == 25567)
			player.teleport(new Location(Bukkit.getServer().getWorld("world"), -42.5,71.5,42.5,180, 0));
		if(SkyblockServer.getServer().getType() == ServerType.F6)
			player.teleport(new Location(Bukkit.getServer().getWorld("world"), -8.5,69.5,-0.5,0, 0));
		
		
		
		

		PacketReader reader = new PacketReader((event.getPlayer()));
		reader.inject();
		if (NPC.getNPCs() == null)
			return;
		if (NPC.getNPCs().isEmpty())
			return;
Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getProvidingPlugin(Main.class), new Runnable() {
            
            @Override
            public void run() {
		NPC.addJoinPacket(event.getPlayer());
            }}, 70);
		
		
		
	}
	@EventHandler
	public void MininSystem(BlockDamageEvent event) {
		
		
		
		
		SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
		
		if(Puzzler.activePlayerQuest(player) ) {
		Puzzler puzzle =	Puzzler.getPuzzler(player);
		if(!puzzle.isFailed)
		if(event.getBlock().equals(puzzle.getEstimatetBlockLocation().getBlock())) {
			player.sendMessage("§e[NPC] §dPuzzler§f: §a✌✓");
			player.sendMessage("§e[NPC] §dPuzzler§f: ▶▶Nice! ▲Here, ◀have ▼some◀ ▶ §2᠅ Mithril Powder§f!▲");
			player.sendMessage("§dPuzzler §6gave you §21,000 Mithril Powder §6for solving the puzzle!");
			player.addMithrilPowder(1000);
			puzzle.remove();
		}else {
			player.sendMessage("§e[NPC] §dPuzzler§f: §c╳§f (╯°□°）╯︵ ┻━┻");
			player.sendMessage("§e[NPC] §dPuzzler§f: §c▶▲Wrong▲ ◀block◀ ▼▶try again▶▶");
			puzzle.markAsFailed();
		}
		}
		
		if(event.getBlock().getType() == Material.STONE)
		if(Main.getPlayerMiningSpeed(event.getPlayer()) >= 450 ) {
			//event.setInstaBreak(true);

		}else {
			
			
			
		}
		

	}
	public static int getBlockBreakStage(int totalTicks, int currentTick) {
		
	double result =	((double)currentTick/(double)totalTicks)*10;

		
		return (int)result;
	}
	public static int getBlockBreakingPower(Block block) {
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
	public static int[] getCustomBlockData(Block block) {
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
	public static int estimateBreakingTime(Player player, double blockStrength) {
		double mining_speed = Main.getPlayerMiningSpeed(player);
		double SoftCap=Tools.round(6.66666666666666666666666666666666666*blockStrength, 0);
		if(SoftCap <= mining_speed)
			mining_speed = SoftCap;
		
		double MiningTime = (blockStrength * 30)/mining_speed;
		
		
			
		
		return (int) MiningTime;
	}
		public static boolean checkValid(Block block) {
			if(block.getType() == Material.STONE || block.getType() == Material.COBBLESTONE || block.getType() == Material.COAL_ORE || block.getType() == Material.IRON_ORE || block.getType() == Material.GOLD_ORE || block.getType() == Material.DIAMOND_ORE || block.getType() == Material.GLOWSTONE || block.getType() == Material.LAPIS_ORE || block.getType() == Material.EMERALD_ORE
					||( (Main.getMain().getServer().getPort() == 25564 || !Main.isLocalHost ) && (block.getType() == Material.GRAY_WOOL || block.getType() == Material.CYAN_TERRACOTTA || block.getType() == Material.PRISMARINE || block.getType() == Material.DARK_PRISMARINE || block.getType() == Material.PRISMARINE_BRICKS || block.getType() == Material.LIGHT_BLUE_WOOL || block.getType() == Material.POLISHED_DIORITE)))
				return true;
			return false;
		}
	@EventHandler
	public void BlockBreak(BlockBreakEvent event) {
		
		if(Main.getMain().getConfig().getBoolean("StatSystem") == true)
		event.setCancelled(true);
		
		if(Main.getMain().getConfig().getBoolean("StatSystem") == true && event.getPlayer().getItemInHand().getItemMeta() != null &&
				Items.SkyblockItems.get(event.getPlayer().getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING)) != null) {
			
			
			ItemManager manager = Items.SkyblockItems.get(event.getPlayer().getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING));
			if(manager.type == ItemType.Drill) {
				ItemStack item = event.getPlayer().getItemInHand();
				ItemMeta meta = item.getItemMeta();
				PersistentDataContainer data = meta.getPersistentDataContainer();
				
				data.set(new NamespacedKey(Main.getMain(), "fuel"), PersistentDataType.INTEGER, event.getPlayer().getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fuel"), PersistentDataType.INTEGER) - 1);
				item.setItemMeta(meta);
				event.getPlayer().setItemInHand( Main.item_updater(event.getPlayer().getItemInHand(),SkyblockPlayer.getSkyblockPlayer(event.getPlayer())));
				
			}
			SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
			MiningSys.getMiningSystem(player).getBlock().breakBlock(event.getBlock(), player);
			player.playSound(event.getBlock().getLocation(), Sound.BLOCK_STONE_BREAK, 1, 1);

			/*
			if(event.getBlock().getType() == Material.STONE || event.getBlock().getType() == Material.COBBLESTONE) {
				
				if(event.getBlock().getType() == Material.COBBLESTONE)
				event.getBlock().setType(Material.BEDROCK);
				else
					event.getBlock().setType(Material.COBBLESTONE);
				
				player.addSkillXp(1, Skills.Mining);
				ItemStack item = new ItemStack(Material.COBBLESTONE);
				item = Main.item_updater(item,player);
				item = Main.item_updater(item,player);
				if(player.getItemInHand().getEnchantments().containsKey(SkyblockEnchants.TELIKINESIS))
					player.addItem(item);
					else
					event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(),item);	
				new BukkitRunnable() {
					
					@Override
					public void run() {
						if(event.getBlock().getLocation().getBlock().getType()== Material.COBBLESTONE)
						event.getBlock().setType(Material.STONE);
						else
							event.getBlock().setType(Material.COBBLESTONE);
					}
				}.runTaskLater(Main.getMain(), 5*20);
			}
		if(event.getBlock().getType() == Material.COAL_ORE) {
			event.getBlock().setType(Material.BEDROCK);
			player.addSkillXp(5, Skills.Mining);
			ItemStack item = new ItemStack(Material.COAL);
			item = Main.item_updater(item,player);
			item = Main.item_updater(item,player);
			if(player.getItemInHand().getEnchantments().containsKey(SkyblockEnchants.TELIKINESIS))
				player.addItem(item);
				else
				event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(),item);	
			new BukkitRunnable() {
				
				@Override
				public void run() {
					
						event.getBlock().setType(Material.COAL_ORE);
				}
			}.runTaskLater(Main.getMain(), 5*20);
		
		}
		if(event.getBlock().getType() == Material.DIAMOND_ORE) {
			event.getBlock().setType(Material.BEDROCK);
			player.addSkillXp(10, Skills.Mining);
			ItemStack item = new ItemStack(Material.DIAMOND);
			item = Main.item_updater(item,player);
			item = Main.item_updater(item,player);
			if(player.getItemInHand().getEnchantments().containsKey(SkyblockEnchants.TELIKINESIS))
				player.addItem(item);
				else
				event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(),item);	
			new BukkitRunnable() {
				
				@Override
				public void run() {
					
						event.getBlock().setType(Material.DIAMOND_ORE);
				}
			}.runTaskLater(Main.getMain(), 5*20);
		
		}
		if(event.getBlock().getType() == Material.IRON_ORE) {
			event.getBlock().setType(Material.BEDROCK);
			player.addSkillXp(5, Skills.Mining);
			ItemStack item = new ItemStack(Material.IRON_ORE);
			item = Main.item_updater(item,player);
			item = Main.item_updater(item,player);
			if(player.getItemInHand().getEnchantments().containsKey(SkyblockEnchants.TELIKINESIS))
				player.addItem(item);
				else
				event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(),item);	
			new BukkitRunnable() {
				
				@Override
				public void run() {
					
						event.getBlock().setType(Material.IRON_ORE);
				}
			}.runTaskLater(Main.getMain(), 5*20);
		
		}
		if(event.getBlock().getType() == Material.GOLD_ORE) {
			event.getBlock().setType(Material.BEDROCK);
			player.addSkillXp(5, Skills.Mining);
			ItemStack item = new ItemStack(Material.GOLD_ORE);
			item = Main.item_updater(item,player);
			item = Main.item_updater(item,player);
			if(player.getItemInHand().getEnchantments().containsKey(SkyblockEnchants.TELIKINESIS))
				player.addItem(item);
				else
				event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(),item);	
			new BukkitRunnable() {
				
				@Override
				public void run() {
					
						event.getBlock().setType(Material.GOLD_ORE);
				}
			}.runTaskLater(Main.getMain(), 5*20);
		
		}
		if(event.getBlock().getType() == Material.GLOWSTONE) {
			event.getBlock().setType(Material.AIR);

			player.addSkillXp(7, Skills.Mining);
			ItemStack item = new ItemStack(Material.GLOWSTONE_DUST);
			Random rand = new Random();
			int amount = rand.nextInt(2)+2;
			item.setAmount(amount);
			item = Main.item_updater(item,player);
			item = Main.item_updater(item,player);
			if(player.getItemInHand().getEnchantments().containsKey(SkyblockEnchants.TELIKINESIS))
				player.addItem(item);
				else
				event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(),item);	
			new BukkitRunnable() {
				
				@Override
				public void run() {
					
						event.getBlock().setType(Material.GLOWSTONE);
				}
			}.runTaskLater(Main.getMain(), 5*20);
		
		}
		if(event.getBlock().getType() == Material.LAPIS_ORE) {
			event.getBlock().setType(Material.BEDROCK);
			ItemStack item = new ItemStack(Material.LAPIS_LAZULI);
			Random rand = new Random();
			int amount = rand.nextInt(2)+2;
			item.setAmount(amount);
			player.addSkillXp(7, Skills.Mining);
			item = Main.item_updater(item,player);
			item = Main.item_updater(item,player);
			if(player.getItemInHand().getEnchantments().containsKey(SkyblockEnchants.TELIKINESIS))
				player.addItem(item);
				else
				event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(),item);	
			new BukkitRunnable() {
				
				@Override
				public void run() {
					
						event.getBlock().setType(Material.LAPIS_ORE);
				}
			}.runTaskLater(Main.getMain(), 5*20);
		
		}
		
		if(event.getBlock().getType() == Material.EMERALD_ORE) {
			event.getBlock().setType(Material.BEDROCK);
			ItemStack item = new ItemStack(Material.EMERALD);
			Random rand = new Random();
			int amount = rand.nextInt(2)+2;
			item.setAmount(amount);
			player.addSkillXp(9, Skills.Mining);
			item = Main.item_updater(item,player);
			item = Main.item_updater(item,player);
			if(player.getItemInHand().getEnchantments().containsKey(SkyblockEnchants.TELIKINESIS))
				player.addItem(item);
				else
				event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(),item);	
			
			new BukkitRunnable() {
				
				@Override
				public void run() {
					
						event.getBlock().setType(Material.EMERALD_ORE);
				}
			}.runTaskLater(Main.getMain(), 5*20);
		
		}
		
		if(event.getPlayer().getServer().getPort() == 25564 || !Main.isLocalHost) {
		
		if(event.getBlock().getType() == Material.GRAY_WOOL) {
			Commission.updateMiningCommission(player, true);
			ItemStack item = Items.Mithril();
			player.addSkillXp(45, Skills.Mining);
			player.playSound(event.getBlock().getLocation(), Sound.BLOCK_WOOD_BREAK, 1, 1);

			Random r = new Random();
			int mithrilpowder = 0;
			if(r.nextBoolean())
				mithrilpowder += 1;
			player.addMithrilPowder(mithrilpowder);
			
			
			
			item.setAmount(dropAmount((int)Main.getPlayerMiningFortune(event.getPlayer()), 1));
			item = Main.item_updater(item,player);
			item = Main.item_updater(item,player);
			if(player.getItemInHand().getEnchantments().containsKey(SkyblockEnchants.TELIKINESIS))
			player.addItem(item);
			else
			event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(),item);	
			
			
			
			//Titanium Spawning System
			
			double chance = player.titaniumchance / 100;
			
			final boolean isTitanium; 
			double i = new Random().nextDouble();
	
			if(i <= chance) {
				isTitanium = true;
				
				
				Location loc = new Location(event.getBlock().getWorld(), (int)event.getBlock().getLocation().getX(), (int)event.getBlock().getLocation().getY(), (int)event.getBlock().getLocation().getZ());
				TitaniumRegen.put(loc, Material.BEDROCK);
				TitaniumObject.put(loc, new Titanium(event.getBlock().getLocation(), Material.GRAY_WOOL));
				
				event.getBlock().setType(Material.POLISHED_DIORITE);
				
			}
			else {
				event.getBlock().setType(Material.BEDROCK);
				isTitanium = false;
				}
			
			
			new BukkitRunnable() {
				
				@Override
				public void run() {
					
					    if(event.getBlock().getLocation().getBlock().getType() == Material.POLISHED_DIORITE && isTitanium == true) {
					    	TitaniumRegen.replace(event.getBlock().getLocation(), Material.GRAY_WOOL);
					    	
					    }else
						event.getBlock().setType(Material.GRAY_WOOL);
				}
			}.runTaskLater(Main.getMain(), 8*20);
		return;
		}
		if(event.getBlock().getType() == Material.CYAN_TERRACOTTA) {
			event.getBlock().setType(Material.BEDROCK);
			Commission.updateMiningCommission(player, true);
			Random r = new Random();
			int mithrilpowder = 0;
			if(r.nextBoolean())
				mithrilpowder += 1;
			player.addMithrilPowder(mithrilpowder);
			ItemStack item = Items.Mithril();
			player.addSkillXp(45, Skills.Mining);
			item.setAmount(dropAmount((int)Main.getPlayerMiningFortune(event.getPlayer()), 1));
			item = Main.item_updater(item,player);
			item = Main.item_updater(item,player);
			if(player.getItemInHand().getEnchantments().containsKey(SkyblockEnchants.TELIKINESIS))
				player.addItem(item);
				else
				event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(),item);	
			player.playSound(event.getBlock().getLocation(), Sound.BLOCK_STONE_BREAK, 1, 1);
//Titanium Spawning System
			
			double chance =player.titaniumchance / 100;
			
			final boolean isTitanium; 
			double i = new Random().nextDouble();
	
			if(i <= chance) {
				isTitanium = true;
				
				
				Location loc = new Location(event.getBlock().getWorld(), (int)event.getBlock().getLocation().getX(), (int)event.getBlock().getLocation().getY(), (int)event.getBlock().getLocation().getZ());
				TitaniumRegen.put(loc, Material.BEDROCK);
				TitaniumObject.put(loc, new Titanium(event.getBlock().getLocation(), Material.CYAN_TERRACOTTA));
				
				event.getBlock().setType(Material.POLISHED_DIORITE);
				
			}
			else {
				event.getBlock().setType(Material.BEDROCK);
				isTitanium = false;
				}
			
			
			new BukkitRunnable() {
				
				@Override
				public void run() {
					
					    if(event.getBlock().getLocation().getBlock().getType() == Material.POLISHED_DIORITE && isTitanium == true) {
					    	TitaniumRegen.replace(event.getBlock().getLocation(), Material.CYAN_TERRACOTTA);
					    	
					    }else
						event.getBlock().setType(Material.CYAN_TERRACOTTA);
					    
					    
				}
			}.runTaskLater(Main.getMain(), 8*20);
			return;
		}
		if(event.getBlock().getType() == Material.PRISMARINE) {
			event.getBlock().setType(Material.BEDROCK);
			Commission.updateMiningCommission(player, true);
			
			Random r = new Random();
			int mithrilpowder = 1;
			if(r.nextBoolean())
				mithrilpowder += 1;
			player.addMithrilPowder(mithrilpowder);
			
			ItemStack item = Items.Mithril();
		
			player.addSkillXp(45, Skills.Mining);
			item.setAmount(dropAmount((int)Main.getPlayerMiningFortune(event.getPlayer()), 2));
			item = Main.item_updater(item,player);
			item = Main.item_updater(item,player);
			if(player.getItemInHand().getEnchantments().containsKey(SkyblockEnchants.TELIKINESIS))
				player.addItem(item);
				else
				event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(),item);	
			player.playSound(event.getBlock().getLocation(), Sound.BLOCK_STONE_BREAK, 1, 1);
//Titanium Spawning System
			
			double chance = player.titaniumchance / 100;
			
			final boolean isTitanium; 
			double i = new Random().nextDouble();
	
			if(i <= chance) {
				isTitanium = true;
				
				
				Location loc = new Location(event.getBlock().getWorld(), (int)event.getBlock().getLocation().getX(), (int)event.getBlock().getLocation().getY(), (int)event.getBlock().getLocation().getZ());
				TitaniumRegen.put(loc, Material.BEDROCK);
				TitaniumObject.put(loc, new Titanium(event.getBlock().getLocation(),Material.PRISMARINE));
				
				event.getBlock().setType(Material.POLISHED_DIORITE);
				
			}
			else {
				event.getBlock().setType(Material.BEDROCK);
				isTitanium = false;
				}
			
			
			new BukkitRunnable() {
				
				@Override
				public void run() {
					
					    if(event.getBlock().getLocation().getBlock().getType() == Material.POLISHED_DIORITE && isTitanium == true) {
					    	TitaniumRegen.replace(event.getBlock().getLocation(), Material.PRISMARINE);
					    	
					    }else
						event.getBlock().setType(Material.PRISMARINE);
				}
			}.runTaskLater(Main.getMain(), 8*20);
			return;
		}
		if(event.getBlock().getType() == Material.DARK_PRISMARINE) {
			event.getBlock().setType(Material.BEDROCK);
			Commission.updateMiningCommission(player, true);
			
			Random r = new Random();
			int mithrilpowder = 1;
			if(r.nextBoolean())
				mithrilpowder += 1;
			player.addMithrilPowder(mithrilpowder);
			
			ItemStack item = Items.Mithril();
			player.addSkillXp(45, Skills.Mining);
			item.setAmount(dropAmount((int)Main.getPlayerMiningFortune(event.getPlayer()), 2));
			item = Main.item_updater(item,player);
			item = Main.item_updater(item,player);
			if(player.getItemInHand().getEnchantments().containsKey(SkyblockEnchants.TELIKINESIS))
				player.addItem(item);
				else
				event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(),item);	
			player.playSound(event.getBlock().getLocation(), Sound.BLOCK_STONE_BREAK, 1, 1);
//Titanium Spawning System
			
			double chance = player.titaniumchance / 100;
			
			final boolean isTitanium; 
			double i = new Random().nextDouble();
	
			if(i <= chance) {
				isTitanium = true;
				
				
				Location loc = new Location(event.getBlock().getWorld(), (int)event.getBlock().getLocation().getX(), (int)event.getBlock().getLocation().getY(), (int)event.getBlock().getLocation().getZ());
				TitaniumRegen.put(loc, Material.BEDROCK);
				TitaniumObject.put(loc, new Titanium(event.getBlock().getLocation(), event.getBlock().getType()));
				
				event.getBlock().setType(Material.POLISHED_DIORITE);
				
			}
			else {
				event.getBlock().setType(Material.BEDROCK);
				isTitanium = false;
				}
			
			
			new BukkitRunnable() {
				
				@Override
				public void run() {
					
					    if(event.getBlock().getLocation().getBlock().getType() == Material.POLISHED_DIORITE && isTitanium == true) {
					    	TitaniumRegen.replace(event.getBlock().getLocation(), Material.DARK_PRISMARINE);
					    	
					    }else
						event.getBlock().setType(Material.DARK_PRISMARINE);
				}
			}.runTaskLater(Main.getMain(), 8*20);
			return;
		}
		if(event.getBlock().getType() == Material.PRISMARINE_BRICKS) {
			event.getBlock().setType(Material.BEDROCK);
			Commission.updateMiningCommission(player, true);
			
			Random r = new Random();
			int mithrilpowder = 1;
			if(r.nextBoolean())
				mithrilpowder += 1;
			player.addMithrilPowder(mithrilpowder);
			
			ItemStack item = Items.Mithril();
			player.addSkillXp(45, Skills.Mining);
			item.setAmount(dropAmount((int)Main.getPlayerMiningFortune(event.getPlayer()), 2));
			item = Main.item_updater(item,player);
			item = Main.item_updater(item,player);
			if(player.getItemInHand().getEnchantments().containsKey(SkyblockEnchants.TELIKINESIS))
				player.addItem(item);
				else
				event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(),item);	
			player.playSound(event.getBlock().getLocation(), Sound.BLOCK_STONE_BREAK, 1, 1);
//Titanium Spawning System
			
			double chance = player.titaniumchance / 100;
			
			final boolean isTitanium; 
			double i = new Random().nextDouble();
	
			if(i <= chance) {
				isTitanium = true;
				
				
				Location loc = new Location(event.getBlock().getWorld(), (int)event.getBlock().getLocation().getX(), (int)event.getBlock().getLocation().getY(), (int)event.getBlock().getLocation().getZ());
				TitaniumRegen.put(loc, Material.BEDROCK);
				TitaniumObject.put(loc, new Titanium(event.getBlock().getLocation(),  Material.DARK_PRISMARINE));
				
				event.getBlock().setType(Material.POLISHED_DIORITE);
				
			}
			else {
				event.getBlock().setType(Material.BEDROCK);
				isTitanium = false;
				}
			
			
			new BukkitRunnable() {
				
				@Override
				public void run() {
					
					    if(event.getBlock().getLocation().getBlock().getType() == Material.POLISHED_DIORITE && isTitanium == true) {
					    	TitaniumRegen.replace(event.getBlock().getLocation(), Material.PRISMARINE_BRICKS);
					    	
					    }else
						event.getBlock().setType(Material.PRISMARINE_BRICKS);
				}
			}.runTaskLater(Main.getMain(), 8*20);
			return;
		}
		if(event.getBlock().getType() == Material.LIGHT_BLUE_WOOL) {
			event.getBlock().setType(Material.BEDROCK);
			Commission.updateMiningCommission(player, true);
			
			Random r = new Random();
			int mithrilpowder = 2;
			if(r.nextBoolean())
				mithrilpowder += 1;
			player.addMithrilPowder(mithrilpowder);
			player.addSkillXp(12, Skills.Mining);
			ItemStack item = Items.Mithril();
			item.setAmount(dropAmount((int)Main.getPlayerMiningFortune(event.getPlayer()), 5));
			item = Main.item_updater(item,player);
			item = Main.item_updater(item,player);
			if(player.getItemInHand().getEnchantments().containsKey(SkyblockEnchants.TELIKINESIS))
				player.addItem(item);
				else
				event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(),item);	
			player.playSound(event.getBlock().getLocation(), Sound.BLOCK_WOOL_BREAK, 1, 1);
//Titanium Spawning System
			
			double chance = player.titaniumchance / 100;
			
			final boolean isTitanium; 
			double i = new Random().nextDouble();
	
			if(i <= chance) {
				isTitanium = true;
				
				
				Location loc = new Location(event.getBlock().getWorld(), (int)event.getBlock().getLocation().getX(), (int)event.getBlock().getLocation().getY(), (int)event.getBlock().getLocation().getZ());
				TitaniumRegen.put(loc, Material.BEDROCK);
				TitaniumObject.put(loc, new Titanium(event.getBlock().getLocation(), Material.LIGHT_BLUE_WOOL));
				
				event.getBlock().setType(Material.POLISHED_DIORITE);
				
			}
			else {
				event.getBlock().setType(Material.BEDROCK);
				isTitanium = false;
				}
			
			
			new BukkitRunnable() {
				
				@Override
				public void run() {
					
					    if(event.getBlock().getLocation().getBlock().getType() == Material.POLISHED_DIORITE && isTitanium == true) {
					    	TitaniumRegen.replace(event.getBlock().getLocation(), Material.LIGHT_BLUE_WOOL);
					    	
					    }else
						event.getBlock().setType(Material.LIGHT_BLUE_WOOL);
				}
			}.runTaskLater(Main.getMain(), 8*20);
			return;
		}
		if(event.getBlock().getType() == Material.POLISHED_DIORITE) {
			player.addSkillXp(100, Skills.Mining);
			Location loc = new Location(event.getBlock().getWorld(), (int)event.getBlock().getX(), (int)event.getBlock().getY(), (int)event.getBlock().getZ());
			
			if(!TitaniumObject.containsKey(loc) )
				
				return;
			if(!TitaniumRegen.containsKey(loc))
				return;
			player.playSound(event.getBlock().getLocation(), Sound.BLOCK_STONE_BREAK, 1, 1);
			Commission.updateMiningCommission(player, false);
			ItemStack item = Items.Titanium();
			item.setAmount(dropAmount((int)Main.getPlayerMiningFortune(event.getPlayer()), 2, 4));
			
			if(player.getItemInHand().getEnchantments().containsKey(SkyblockEnchants.TELIKINESIS))
				player.addItem(item);
				else
				event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(),item);	
			
			Titanium e = TitaniumObject.get(event.getBlock().getLocation());
			e.cancle();
			event.getBlock().setType(TitaniumRegen.get(event.getBlock().getLocation()));
		
		}
		
			
		}*/
	}}
public static int dropAmount(int minigFortune, int lowest, int highest) {
		
		Random rand = new Random();
		int dropBaseAmount =  rand.nextInt(highest-lowest) + lowest;
		if(minigFortune != 0) {
		 if(minigFortune < 100) {
			  Random r = new Random();
			  int low = 1;//includes 1
			  int high = 100;// includes 100
			  int result = r.nextInt(high-low) + low;
			  if(minigFortune >= result) {
				  return dropBaseAmount + rand.nextInt(highest-lowest) + lowest;
				
			  }
		  }else {
			 double guranteed =(double) minigFortune / 100;
			 
			  if(guranteed % 1 == 0) {
				  for(int i =  (int) guranteed; i != 0; i--) {
					  dropBaseAmount += rand.nextInt(highest-lowest) + lowest;
				  }
				  
				  return dropBaseAmount;
				   
				 
			  }else {
				 int minus = (int) ((int)guranteed * 100);
				 double hitchance = (double)minigFortune - (double)minus;
				
				 Random r = new Random();
				  int low = 1;//includes 1
				  int high = 100;// includes 100
				  int result = r.nextInt(high-low) + low;
				 int finalAmount = (int) guranteed;
				  if(hitchance >= result) {
					  finalAmount = finalAmount +1;
				  }
				  for(int i =  (int) finalAmount; i != 0; i--) {
					  dropBaseAmount += rand.nextInt(highest-lowest) + lowest;
				  }
				  
				  return dropBaseAmount;
				
			  }
		  }
		 }
		
		return dropBaseAmount;
		
	}
public static int dropAmount(int minigFortune, int amount) {
	

	int dropBaseAmount =  amount;
	if(minigFortune != 0) {
	 if(minigFortune < 100) {
		  Random r = new Random();
		  int low = 1;//includes 1
		  int high = 100;// includes 100
		  int result = r.nextInt(high-low) + low;
		  if(minigFortune >= result) {
			  return dropBaseAmount +amount;
			
		  }
	  }else {
		 double guranteed =(double) minigFortune / 100;
		 
		  if(guranteed % 1 == 0) {
			  for(int i =  (int) guranteed; i != 0; i--) {
				  dropBaseAmount += amount;
			  }
			  
			  return dropBaseAmount;
			   
			 
		  }else {
			 int minus = (int) ((int)guranteed * 100);
			 double hitchance = (double)minigFortune - (double)minus;
			
			 Random r = new Random();
			  int low = 1;//includes 1
			  int high = 100;// includes 100
			  int result = r.nextInt(high-low) + low;
			 int finalAmount = (int) guranteed;
			  if(hitchance >= result) {
				  finalAmount = finalAmount +1;
			  }
			  for(int i =  (int) finalAmount; i != 0; i--) {
				  dropBaseAmount += amount;
			  }
			  
			  return dropBaseAmount;
			
		  }
	  }
	 }
	
	return amount;
	
}
	@EventHandler
	public void BlockPlace(BlockPlaceEvent event) {
		if(Main.getMain().getConfig().getBoolean("StatSystem") == true) 
			event.setCancelled(true);
		
		if(event.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ability"), PersistentDataType.STRING)!= null &&event.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ability"), PersistentDataType.STRING).equals("orb")) {
			ArmorStand stand = event.getBlock().getWorld().spawn(event.getBlock().getLocation().add(0,-0.5,0), ArmorStand.class, s ->{
				s.setInvisible(true);
				s.setInvulnerable(true);
				s.setGravity(false);
				s.getEquipment().setHelmet(Tools.CustomHeadTexture("https://textures.minecraft.net/texture/1d5a09884cb83ef5c908dddd385f246fefdee221712c010177f54376da238fdd"));
				s.setCustomName("§9Orb.exe");
				s.setCustomNameVisible(true);
			});
			
			orbStuff(stand, true, 15, 15*20);
		}
		
	}
	public void orbStuff(ArmorStand stand, boolean upmotion, int runnable, int timer) {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				
				Location loc = stand.getLocation();
				loc.setYaw((float) (loc.getYaw() + 15));
				if(upmotion) {
					
					stand.teleport(loc.add(0,0.07,0));
				}else {
					stand.teleport(loc.add(0,-0.07,0));
				}
				if(timer%2==0)
				Particles(stand);
				else
					Particles2(stand);
				if(timer != 0) {
					boolean newmotion = upmotion;
					int newrunnable = runnable - 1;
					if(newrunnable == 0) {
						
						if(upmotion) {
							smothDirectionChange(stand, 0.07, timer - 1, upmotion);
						}else
							smothDirectionChange(stand, -0.07, timer - 1, upmotion);
					}else {
					
					orbStuff(stand, newmotion, newrunnable, timer - 1);
					}
				}else {
					stand.remove();
				}
				
				
			}
		}.runTaskLater(Main.getMain(), 1L);
	}
	public void smothDirectionChange(ArmorStand stand, double currmotion, int timer, boolean upmotion) {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				double newMotion = 0;
				if(upmotion) {
					 newMotion =currmotion - 0.005;
				}else {
					 newMotion =currmotion + 0.005;
				}
				Location loc = stand.getLocation();
				loc.setYaw((float) (loc.getYaw() + 15));
				stand.teleport(loc.add(0,newMotion,0));
				if(timer%2==0)
					Particles(stand);
					else
						Particles2(stand);
				if(timer -1 != 0) {
					
				if(newMotion <= -0.07 && upmotion) {
					orbStuff(stand, false, 15 ,timer - 1);
				}else {
					if(newMotion >= 0.07 && !upmotion) {
						orbStuff(stand, true, 15 ,timer - 1);
					}else {
						smothDirectionChange(stand, newMotion, timer - 1, upmotion);
					}
				}
				}else {
					stand.remove();
				}
				
				
				
				
			}
		}.runTaskLater(Main.getMain(), 1L);
	}
	public void Particles(ArmorStand stand) {

		Location loc = stand.getEyeLocation();

		Vector dir =stand.getEyeLocation().getDirection();
		dir.normalize();
		dir.multiply(0.625); //1 blocks a way
		loc.add(dir);

		final Particle.DustOptions dust = new Particle.DustOptions(
                Color.fromBGR(126, 85, 49), 1);
		loc.getWorld().spawnParticle(Particle.REDSTONE, loc.getX(), loc.getY(), loc.getZ(),1, dust);
	}
	public void Particles2(ArmorStand stand) {

		Location loc = stand.getEyeLocation();

		Vector dir =stand.getEyeLocation().getDirection();
		dir.normalize();
		dir.multiply(0.625); //1 blocks a way
		loc.add(dir);
		loc.setY(loc.getY() - 0.04);

		final Particle.DustOptions dust = new Particle.DustOptions(
                Color.fromBGR(131, 119, 58), 1);
		loc.getWorld().spawnParticle(Particle.REDSTONE, loc.getX(), loc.getY(), loc.getZ(),1, dust);
	}
	@EventHandler
	public void interact(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Location loc = player.getEyeLocation();
		Vector dir = loc.getDirection();
		for(int i=1;i<=5;i++) {
		
		dir.normalize();
		dir.multiply(1); //5 blocks a way
		loc.add(dir.getX(),  dir.getY(), dir.getZ());
		if(loc.getBlock().getType() == Material.COMMAND_BLOCK) {
			CommandBlock block = (CommandBlock) loc.getBlock().getState();

			




			if(block.getCommand().equals("maze")) {
				maze.createinventory(loc);
				player.openInventory(maze.mazeterminal);
			}
			if(block.getCommand().equals("order")) {
				order.createInventory();
				player.openInventory(order.orderterminal);
			}
				
			break;
		}
		
			
		}
		
	}
	@EventHandler
	public void move(PlayerMoveEvent e) {

		
		if(Main.beaconOwner.containsKey(e.getPlayer())) {
			Entity boss = Main.beaconOwner.get(e.getPlayer());
			if( Main.beaconPicketUp.get(boss) == false) {
			
			List<Entity> close = (List<Entity>)Main.beaconLocation.get(boss).getWorld().getNearbyEntities(Main.beaconLocation.get(boss), 1.2, 1.2, 1.2);
			if(close.contains(e.getPlayer())) {
				System.out.println("player is close");
				Main.beaconPicketUp.replace(boss, true);
				if(Main.beaconBeforeBlock.get(Main.beaconLocation.get(boss)) != null)
					Main.beaconLocation.get(boss).getBlock().setType(Main.beaconBeforeBlock.get(Main.beaconLocation.get(boss)).getType());
					else
					Main.beaconLocation.get(boss).getBlock().setType(Material.AIR);
				Main.beaconLocation.remove(boss);
			}
		}
		}
		
		
		Player player = e.getPlayer();
		NPC.getNPCs().forEach(npc ->{
			
			Location npcloc = npc.getBukkitEntity().getLocation();
			List<Entity> nearby = npc.getBukkitEntity().getNearbyEntities(5, 5, 5);
			if(nearby.contains(player)) {
			npcloc = npcloc.setDirection(player.getLocation().subtract(npcloc).toVector());
            PlayerConnection connection = ((CraftPlayer) player).getHandle().b;
            float yaw = npcloc.getYaw();
            float pitch = npcloc.getPitch();
            connection.sendPacket(new PacketPlayOutEntity.PacketPlayOutEntityLook(npc.getId(), (byte) ((yaw%360.)*256/360), (byte) ((pitch%360.)*256/360), false));
            connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) ((yaw%360.)*256/360)));
            
			}});
		if(Main.getMain().getConfig().getBoolean("LavaBounce") == true) {
		if(player.getLocation().getBlock().getType() == Material.LAVA) {
			player.setVelocity(player.getLocation().getDirection().multiply(0).setY(2));
		player.getWorld().getEntities().forEach(entity->{
			if(entity.getScoreboardTags() != null)
			entity.getScoreboardTags().forEach(tag->{
				if(tag.startsWith("eye_target:")) {
					Player p = Bukkit.getPlayer(tag.split(":")[1]);
					if(p == player)
					entity.getLocation().setDirection(player.getLocation().subtract(entity.getLocation()).toVector());
				}
			});
		});
		}else {
			
		}}
	}
	@EventHandler
	public void GamemodeEvent(PlayerGameModeChangeEvent event) {
		

		
		
		
		Bukkit.broadcastMessage(ChatColor.GOLD + event.getPlayer().getName() + " just Changed his Gamemode to: " + event.getNewGameMode().toString());
	}
	@EventHandler
	public void onQuid(PlayerQuitEvent event) {
		PacketReader reader = new PacketReader(event.getPlayer());
		reader.uninject(event.getPlayer());
		SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
		player.saveCommissionProgress();
		Main.absorbtion.remove(player);
		Main.absorbtionrunntime.remove(player);
		
		Main.saveCoins(player);
		Main.saveBits(player);
		Main.saveMithrilPowder(player);

		try{
			player.saveInventory();
		}catch(Exception e){e.printStackTrace();}
		if(Main.petstand.containsKey(player))
		Main.petstand.get(player).remove();
		
		
	}
	@EventHandler
	public void respawn(PlayerRespawnEvent event) {
		SkyblockPlayer player =SkyblockPlayer.getSkyblockPlayer( event.getPlayer());
		
		player.setHealth(Main.playerhealthcalc(player));
		
		Main.deathPersons.remove(event.getPlayer());

		if(event.getPlayer().getServer().getPort() == 25565)
			event.setRespawnLocation(new Location(Bukkit.getServer().getWorld("world"), -2.5, 70, -69.5,180, 0));
		if(event.getPlayer().getServer().getPort() == 25564 || !Main.isLocalHost)
			event.setRespawnLocation(new Location(Bukkit.getServer().getWorld("world"), -48.5, 200, -121.5,270, 0));

		if(event.getPlayer().getServer().getPort() == 25570)
			event.setRespawnLocation(new Location(Bukkit.getServer().getWorld("world"), -29.5, 121, 0.5,90, 0));


		//Crimson Isle
		if(event.getPlayer().getServer().getPort() == 25568)
			event.setRespawnLocation(new Location(Bukkit.getServer().getWorld("world_nether"), -360.5, 80, -426.5,180, 0));
		//The Instance
		if(event.getPlayer().getServer().getPort() == 25569)
			event.setRespawnLocation(new Location(Bukkit.getServer().getWorld("world"), -101.5, 41, -185.5,0, 0));
		if(event.getPlayer().getServer().getPort() == 25566)
			event.setRespawnLocation(new Location(Bukkit.getServer().getWorld("dungeon"), 0,0,0,0, 0));
		//F1 Bossroom
		if(event.getPlayer().getServer().getPort() == 25567)
			event.setRespawnLocation(new Location(Bukkit.getServer().getWorld("world"), -42.5,71.5,42.5,180, 0));
		if(SkyblockServer.getServer().getType() == ServerType.F6)
			event.setRespawnLocation(new Location(Bukkit.getServer().getWorld("world"), -8.5,69.5,-0.5,0, 0));

		Main.updatebar(player);
	}
	@EventHandler
	public void StopBurn(EntityCombustEvent event) {
		
		if(event.getEntity() instanceof LivingEntity)
		
		if(event.getEntityType() != EntityType.ZOMBIE) {
			 ItemStack helmet = ((LivingEntity) event.getEntity()).getEquipment().getHelmet();

        if(helmet != null && helmet.getType() != Material.AIR) {
            // Mob has a helmet.
            return;
        }else
			event.setCancelled(true);}
	}
	@EventHandler
	public void onItemSpawn(ItemSpawnEvent event){
	    if(event.getEntity().getItemStack().getType() == Material.BEACON && event.getEntity().getScoreboardTags() != null && !event.getEntity().getScoreboardTags().contains("player")) {
	    	final Location BEACON_LOCATION = event.getLocation().getBlock().getLocation().add(0,1,0);
	    	Main.beaconBeforeBlock.put(BEACON_LOCATION, BEACON_LOCATION.getBlock());
	    	ArrayList<Entity> e = new ArrayList<>();
	    	Main.beaconThrown.forEach((entity, throwe) ->{
	    		if(throwe == true) {
	    			Main.beaconLocation.put(entity, BEACON_LOCATION);
	    			e.add(entity);
	    		}
	    	});
	    	Entity entity = e.get(0);
	    	Main.beaconOnGround.put(entity, true);
	    	Main.beaconPicketUp.put(entity, false);
	    	Main.beaconThrown.remove(entity);
	    	entity.getScoreboardTags().forEach(tag ->{
	    		if(tag.startsWith("owner:")) {
	    			Main.beaconOwner.put(Bukkit.getPlayer(tag.split(":")[1]), entity);
	    		}
	    	});
	    	Main.voidgloom_kill_beacon((Enderman) entity);
	    	BEACON_LOCATION.getBlock().setType(Material.BEACON);
	    	System.out.println(BEACON_LOCATION + " By SpawnEvent");
	    	event.getEntity().remove();
	    }
	        
	}
	@EventHandler
	public void stopKuudraSplit(SlimeSplitEvent e) {
			e.setCancelled(true);
	}
	@EventHandler
	public void SoulSystem(EntityDeathEvent e) {
		if(e.getEntity() instanceof FallingBlock) {
			if(e.getEntity().getScoreboardTags().contains("voidgloom_beacon"))
			e.getEntity().getLocation();
		}
		
		
		if(e.getEntityType() != EntityType.PLAYER && e.getEntityType() != EntityType.DROPPED_ITEM) {

			 if(e.getEntity() instanceof LivingEntity) {
				 if(e.getEntity() instanceof EnderDragon) {
					 e.setDroppedExp(0);
				 }

				 if(SkyblockEntity.livingEntity.containsKey(e.getEntity())) {
					 SkyblockEntity.livingEntity.get(e.getEntity()).kill();
				 }
				 if(Main.dinnerboneNametags.containsKey(e.getEntity())) {
					 Main.dinnerboneNametags.get(e.getEntity()).remove();
					 Main.dinnerboneNametags.remove(e.getEntity());
				 }
				 
					 Main.WitherSmallStuff.remove(e.getEntity());
				 
				 
			e.getEntity().setCustomNameVisible(false);
			Main.currentityhealth.remove(e.getEntity());
			Main.baseentityhealth.remove(e.getEntity());
			
			if(Main.entitydamage.containsKey(e.getEntity()))
				Main.entitydamage.remove(e.getEntity());
			if(e.getEntity().getScoreboardTags().contains("revslayert4")) {
				e.getEntity().getScoreboardTags().forEach(tag ->{
					if(tag.startsWith("killer:")) {
						new DropSystem(e.getEntity(), Bukkit.getServer().getPlayer(tag.split(":")[1]), e);
					}
				});
			}
			if(SkyblockEntity.livingEntity.containsKey((LivingEntity) e.getEntity())){
				for(String tag : e.getEntity().getScoreboardTags())
					if(tag.startsWith("killer:")) {
						new DropSystem(e.getEntity(), Bukkit.getServer().getPlayer(tag.split(":")[1]), e);
						break;
					}
			}


			
			
			if(e.getEntity().getScoreboardTags().contains("voidgloomt2")) {
				e.getDrops().clear();
				
				e.getEntity().getScoreboardTags().forEach(tag ->{
					
					if(tag.startsWith("owner:")) {
						
						new DropSystem(e.getEntity(), Bukkit.getServer().getPlayer(tag.split(":")[1]), e);
					}
					if(tag.startsWith("rider:")) {
						Bukkit.getEntity(UUID.fromString(tag.split(":")[1])).remove();
					}
				});
			}
			
			e.getEntity().getScoreboardTags().forEach(tag ->{
				
				
				if(tag.startsWith("rider:")) {
					Bukkit.getEntity(UUID.fromString(tag.split(":")[1])).remove();
				}
			});
			
			  ArrayList<Player> p = new ArrayList<>();
			e.getEntity().getScoreboardTags().forEach(tag ->{

				if(tag.startsWith("killer:")) {
				
				  p.add(Bukkit.getServer().getPlayer(tag.split(":")[1]));
				  }
					
			});
				 
			if(!p.isEmpty()) {
			SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(p.get(0));




			for(String tag :e.getEntity().getScoreboardTags())
				if(tag.startsWith("combatxp:"))
					SkyblockPlayer.getSkyblockPlayer(player).addSkillXp(Double.parseDouble(tag.split(":")[1]), Skills.Combat);
			if(!p.isEmpty() && p.get(0) != null && player.getItemInHand().getItemMeta() != null && player.getItemInHand().getItemMeta().getEnchants().containsKey(SkyblockEnchants.TELIKINESIS)) {
				for(ItemStack d : e.getDrops()) {
					d = Main.item_updater(d,SkyblockPlayer.getSkyblockPlayer(player));
					d = Main.item_updater(d,SkyblockPlayer.getSkyblockPlayer(player));
					player.addItem(d);
					
				}
				e.getDrops().clear();
			}
			}
			
			
			
				
			Main.EntityDeath(e.getEntity());
		}}else {
			if(e.getEntityType() == EntityType.PLAYER) {
				
				
				
				
				
			}
		}
		if(Main.getsoulchalangeboolean() == true) {
		
		

        //Get Killer
        Entity killer = e.getEntity().getKiller();
        if (e.getEntity().getScoreboardTags().contains("SoulSheep")) {
    		Location dropLocation = e.getEntity().getLocation();
    		((CraftWorld)dropLocation.getWorld()).dropItem(dropLocation, getSoulFragment());
    		e.getEntity().getKiller().sendMessage("§a§lSoul Fragment Drop! §r" + e.getEntity().getName() + " dropped you a Soul Fragment");
    	}
        if (e.getEntity().getType() == EntityType.VILLAGER) {
    		Location dropLocation = e.getEntity().getLocation();
    		((CraftWorld)dropLocation.getWorld()).dropItem(dropLocation, getSoulShop());
    	}
        
        if(e.getEntity().getType() == EntityType.WITHER) {
        	int Drop = (int )(Math.random() * 100 + 1);
        	if(Drop <= 40) {
        	Location dropLocation = e.getEntity().getLocation();

    		Bukkit.broadcastMessage("§l§6Rare Drop! §r" + killer.getName() + " just got an " + ChatColor.DARK_PURPLE + "Necrons Handle");
        }}
        if(killer instanceof Player)
        {
        	
        	Player player = ((Player) killer).getPlayer();
        int SoulDrop = (int )(Math.random() * 100 + 1);
        ConfigFile.reload();
        	if (SoulDrop <= ConfigFile.get().getInt(player.getUniqueId().toString() + ".soulchance")) {
        		((Player) killer).getPlayer().sendMessage("§a§lSoul Drop! §r" + e.getEntity().getName() + " dropped you a soul");
        		
        		int souls = ConfigFile.get().getInt(player.getUniqueId().toString() + ".souls");
        		
        		souls++;
        		ConfigFile.get().set(player.getUniqueId().toString() + ".souls", souls);
        		ConfigFile.save();
        		ConfigFile.reload();
        		((Player) killer).getPlayer().sendMessage("You have " + souls + " §lSouls§r");
        		
        	
        
        	}
        	SoulDrop = (int )(Math.random() * 100 + 1);
        	if(SoulDrop <= 7) {
        		Location dropLocation = e.getEntity().getLocation();
        		((CraftWorld)dropLocation.getWorld()).dropItem(dropLocation, getSoulFragment());
        		player.sendMessage("§a§lSoul Fragment Drop! §r" + e.getEntity().getName() + " dropped you a Soul Fragment");
        	}
        	
	}
		}
	}
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (event.getItem() == null) return;
		
		
		ItemStack itemStack = event.getItem();
		
		if (itemStack.getType() == Material.EMERALD) {
			if (itemStack.hasItemMeta()) {
			if(itemStack.getItemMeta().hasCustomModelData()) {

			if (itemStack.getItemMeta().getCustomModelData() == 10) {
				createInventory(event.getPlayer());
				event.getPlayer().openInventory(SoulShopInv);

			}
			}
			}
		}
		if (itemStack.getType() == Material.NETHER_STAR) {
			if (itemStack.hasItemMeta()) {
				if(itemStack.getItemMeta().getPersistentDataContainer() != null) {
					if(itemStack.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ability"), PersistentDataType.STRING) != null && itemStack.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ability"), PersistentDataType.STRING).equals("skyblockmenu")) {

			
				OpenMenu.createInventory(event.getPlayer());
				event.getPlayer().openInventory(OpenMenu.skyblockmenu);
			
			}
			}
			}
		}
		if (itemStack.getType() == Material.GHAST_TEAR) {
			if (itemStack.hasItemMeta()) {
			if(itemStack.getItemMeta().hasCustomModelData()) {

			if (itemStack.getItemMeta().getCustomModelData() == 10) {
				try {
				
				 itemStack.setAmount(itemStack.getAmount() - 1);
				event.getPlayer().updateInventory();
			    Player player = event.getPlayer();
			    ConfigFile.reload();
				int soulchance = ConfigFile.get().getInt(player.getUniqueId().toString() + ".soulchance");
				soulchance++;
				ConfigFile.get().set(player.getUniqueId().toString() + ".soulchance", soulchance);
				ConfigFile.save();
				ConfigFile.reload();
				event.getPlayer().sendMessage("Your §5§lSoulchance§r has been increat by 1. You have now §5§l" + soulchance + "% §rto drop a §lSoul§r");
				}catch (Exception e){
					Bukkit.broadcastMessage("error");
					System.out.println(e);
				}
			}
			}
			}
		}

		if(   event.getAction() == Action.RIGHT_CLICK_BLOCK ||  event.getAction() == Action.RIGHT_CLICK_AIR ) {
		}}
	public static void teleport(Player player) {
		for(int i=1;i<=10;i++) {
		Location loc = player.getLocation();
		Vector dir = loc.getDirection();
		dir.normalize();
		dir.multiply(1); //1 blocks a way
		loc.add(dir);

		if(loc.getBlock().isEmpty() || loc.getBlock().isLiquid() || loc.getBlock().isPassable()) {
		player.teleport(loc);
	}}}
	@EventHandler
	public void MobSpawnEvent(EntitySpawnEvent event) {
		if(event.getEntity().getScoreboardTags().contains("npc"))
			return;
		Entity entity = event.getEntity();
		if(!(entity instanceof Player) && !(entity.getType() == EntityType.DROPPED_ITEM)&& !(entity.getType() == EntityType.ARMOR_STAND)&& !(entity.getType() == EntityType.WITHER_SKULL) ) {
			 if(entity instanceof LivingEntity) {
				 LivingEntity e = (LivingEntity) entity;
			if(Main.baseentityhealth.get(entity) == null) {
			  
			  Main.baseentityhealth.put(e, (int)e.getMaxHealth()*5);
			  Main.currentityhealth.put(e, (int)e.getMaxHealth()*5);
			  
			  entity.setCustomNameVisible(true);
			  e.setHealth(e.getMaxHealth());
			  if(e.getScoreboardTags() == null) {
				  e.addScoreboardTag("combatxp:5");
				 
			  }else {
				  ArrayList<String> check = new ArrayList<>();
				  e.getScoreboardTags().forEach(str ->{
					 
					  if(str.startsWith("combatxp:")) {
						  check.add("yes");
						  
					  }
				  });
				 
				  if(!check.contains("yes")) {
					  e.addScoreboardTag("combatxp:5");
					  
				  }
			  }
		  }Main.updateentitystats(e);
		  
		  }}
		

		}
	public SkyblockRemakeEvents getEvents() {
		return Events;
	}
	public static void createInventory(Player player) {
		ConfigFile.reload();
		Inventory inv = Bukkit.createInventory(null, 36, ChatColor.DARK_GRAY + "Soul Menü");
		ItemStack item = new ItemStack(Material.AIR);
		ItemMeta meta = item.getItemMeta();
		
		inv.setItem(0, item);
		
		item.setType(Material.PLAYER_HEAD);
		SkullMeta Smeta = (SkullMeta) item.getItemMeta();
		
		List<String> lore = new ArrayList<>();
		lore.add(" ");
		lore.add(ChatColor.GREEN + "Your Soul drop Chance: §r§l" + ConfigFile.get().get(player.getUniqueId().toString() + ".soulchance") + "%");
		Smeta.setLore(lore);
		lore.clear();
		Smeta.setOwner(player.getName().toString());
		Smeta.setDisplayName(ChatColor.GREEN + "Your Souls: "+ ChatColor.WHITE + "§l" +  ConfigFile.get().get(player.getUniqueId().toString() + ".souls"));
		Smeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(Smeta);
		inv.setItem(4, item);
		
		
		item.setType(Material.ANVIL);
		meta = item.getItemMeta();
		lore.clear();
	    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	    meta.setDisplayName("§r§lSoul §rReforging");
	    meta.setLore(lore);
	    item.setItemMeta(meta);
	    inv.setItem(20, item);
	    
	    item.setType(Material.EMERALD);
		meta = item.getItemMeta();
		lore.clear();
	    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	    meta.setDisplayName("§r§lSouls §rItem Shop");
	    meta.setLore(lore);
	    item.setItemMeta(meta);
	    inv.setItem(24, item);
		
		SoulShopInv = inv;
	}
	@EventHandler
	public void InventoryClickLEL(InventoryClickEvent event) {
		if(event.getClickedInventory() != null && event.getClickedInventory().getType() != null && event.getClickedInventory().getType() == InventoryType.PLAYER) {
			if(event.getSlot() == 8) {
				event.setCancelled(true);
				OpenMenu.createInventory((Player) event.getWhoClicked());
				event.getWhoClicked().openInventory(OpenMenu.skyblockmenu);
			}
		}
	}
	@EventHandler
	public void ItemsMenu(InventoryClickEvent event) {
		  if (!event.getView().getTitle().contains("Custom Items Menu - Page "))
			  return;
		  if (event.getCurrentItem() == null)
			  return;
		  if (event.getCurrentItem().getItemMeta() == null)
			  return;
		  
		  Player player = (Player) event.getWhoClicked();
		  event.setCancelled(true);
		  
		  if(event.getClickedInventory().getType() == InventoryType.PLAYER)
			  return;
		  
		  
		  
		  
		  int page = event.getView().getTopInventory().getItem(53).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "page"), PersistentDataType.INTEGER); 
		  
		  if(event.getSlot() == 53) {
				 
			  if(page  < itemCMD.items.size()) {
				  
  player.openInventory(itemCMD.items.get(page));
			  player.updateInventory();}
			  return;
		  }
		  if(event.getSlot() == 49) {
				 
			  SkyblockPlayer.getSkyblockPlayer(player).setSearching(SearchTopic.CustomItems);
			  player.closeInventory();

			  new SignGUI(new SignManager(), e -> {
				  Bukkit.getScheduler().runTask(Main.getMain(), ()-> ItemsSearch.buildInventory(SkyblockPlayer.getSkyblockPlayer(player), e.lines()[0] + e.lines()[1]));
				  

			  })
					  .withLines("", "", "^^^^^^^^^^^^^^^", "Enter Item Name")
					  .open(SkyblockPlayer.getSkyblockPlayer(player));
		  
			  return;
		  }
		  if(event.getSlot() == 45) {
			  
			  if(page != 1) {
			
			  player.openInventory(itemCMD.items.get(page - 2));
			  player.updateInventory();
			  }
			  return;
		  }
		  if(event.getSlot() > 45 ) {
				 return; 
			  }
		  if (event.getCurrentItem().getType() == Material.AIR)
			  return;
		  player.getInventory().addItem(event.getCurrentItem());
		  player.updateInventory();
	
	}
	@EventHandler
	public void ShopMenu(InventoryClickEvent event) {
		  if (!event.getView().getTitle().contains("Soul Shop"))
			  return;
		  if (event.getCurrentItem() == null)
			  return;
		  if (event.getCurrentItem().getItemMeta() == null)
			  return;
		  
		  Player player = (Player) event.getWhoClicked();
		  event.setCancelled(true);
		  
		  if(event.getClickedInventory().getType() == InventoryType.PLAYER)
			  return;
		  if (event.getSlot() == 22) {
			  player.sendMessage("there is sadly nothing");
		  }
		  
	
	}
	public boolean hasfreeSlot(Player p){
		 return Arrays.asList(p.getInventory().getStorageContents()).contains(null);
		}
	@EventHandler
	public void onclickShopMenu(InventoryClickEvent event) {
		  if (!event.getView().getTitle().contains("Soul Menü"))
			  return;
		  if (event.getCurrentItem() == null)
			  return;
		  if (event.getCurrentItem().getItemMeta() == null)
			  return;
		  
		  Player player = (Player) event.getWhoClicked();
		  event.setCancelled(true);
		  
		  if(event.getClickedInventory().getType() == InventoryType.PLAYER)
			  return;
		  if (event.getSlot() == 20) {

			  createSoulReforgeInventory(player);
			  player.openInventory(SoulReforging);
			  
			  return;
			  
		  
		  }
		  if (event.getSlot() == 24) {

			  createSoulShopInventory(player);
			  player.openInventory(SoulBuyInv);
			  
			  return;
			  
		  
		  }
		  
	
	}
	@EventHandler
	public void pickupevent(PlayerPickupItemEvent  event) {
		
		Player player = event.getPlayer();

		ItemStack item = player.getItemInHand();event.getItem().setItemStack(Main.item_updater(event.getItem().getItemStack(),SkyblockPlayer.getSkyblockPlayer(player)));
		if(item.getItemMeta() == null || item.getItemMeta().getPersistentDataContainer() == null || item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ability"), PersistentDataType.STRING) == null || !item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ability"), PersistentDataType.STRING).contains("shortbow")) 
			return;
				ItemStack i = event.getItem().getItemStack();
				if(i == null)
					return;
			if(i.getType() == Material.ARROW) {
				
				
				
				player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1F, 1F);
				if(i.getItemMeta().hasDisplayName()) {
					
					 item = Items.FakeArrow(i.getItemMeta().getDisplayName());
					item.setAmount(i.getAmount());
					
					event.getItem().setItemStack(item);
					player.updateInventory();
				}else {
					 item =  Items.FakeArrow("Arrow");
					item.setAmount(i.getAmount());
					event.getItem().setItemStack(item);
					player.updateInventory();
				}
				
		}else {
		
        
		}
			
		
		event.getPlayer().updateInventory();
	}
	public static ItemStack getHyperion() {
		ItemStack item = new ItemStack(Material.IRON_SWORD);
		ItemMeta meta = item.getItemMeta();
		
		
		PersistentDataContainer data = meta.getPersistentDataContainer();
		data.set(new NamespacedKey(Main.getMain(), "ability"), PersistentDataType.STRING, "hyperion");
		data.set(new NamespacedKey(Main.getMain(), "HYPERION"), PersistentDataType.STRING, "HYPERION");
		data.set(new NamespacedKey(Main.getMain(), "mana"), PersistentDataType.INTEGER, 350);
		data.set(new NamespacedKey(Main.getMain(), "manacost"), PersistentDataType.INTEGER, 300);
		data.set(new NamespacedKey(Main.getMain(), "dmg"), PersistentDataType.STRING, "260");
		data.set(new NamespacedKey(Main.getMain(), "strength"), PersistentDataType.INTEGER, 150);
		data.set(new NamespacedKey(Main.getMain(), "baseabilitydamage"), PersistentDataType.INTEGER, 10000);
		data.set(new NamespacedKey(Main.getMain(), "abilityscaling"), PersistentDataType.FLOAT, 0.3f);
		meta.setDisplayName("§6Hyperion");
		List<String> lore = new ArrayList<>();
		lore.add(" ");
		lore.add("§aScroll Abilities:");
		lore.add("§6Ability: Wither Impact " + ChatColor.BOLD + "§eRIGHT CLICK");
		lore.add("§7Teleports §a10 blocks §7ahead of");
		lore.add("§7you. Then implode dealing");
		lore.add("§7§c10000 §7damage to nearby");
		lore.add("§7enemies. Also applies the wither");
		lore.add("§7shield scroll ability reducing");
		lore.add("§7damage taken and granting an");
		lore.add("§7absorption shield for §e5 §7seconds.");
		lore.add("§8Mana Cost: §b300");
		meta.setLocalizedName("HYPERION");
		meta.setLore(lore);
		meta.setUnbreakable(true);
		lore.clear();
		item.setItemMeta(meta);

		net.minecraft.world.item.ItemStack nmsApple = CraftItemStack.asNMSCopy(item);
		NBTTagCompound applecompound = (nmsApple.hasTag()) ? nmsApple.getTag() : new NBTTagCompound();




		meta.setLocalizedName("HYPERION");
		
		applecompound.setString("SKYBLOCK", "HYPERION");
		nmsApple.setTag(applecompound);
		nmsApple.a("HYPERION");
		return CraftItemStack.asBukkitCopy(nmsApple);
	}
	@EventHandler
	public void inventoryClose(InventoryCloseEvent event) {
		if (!event.getView().getTitle().contains("Soul Reforging")) {
			return;
		}
		if(iteminforge == true) {
			ItemStack getback = event.getInventory().getItem(22);
			if(hasfreeSlot((Player)event.getPlayer()) == true) {
			event.getPlayer().getInventory().addItem(getback);
			}else {
				((CraftWorld)event.getPlayer().getLocation().getWorld()).dropItem(event.getPlayer().getLocation(), getback);
			}
			iteminforge = false;
		
		}
		}
	@EventHandler
	public void onDraw(PlayerInteractEvent event) {
	      //On interact
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) 
		      if(event.getItem() != null && event.getItem().getType() == Material.BOW) {
		    	  if(Items.SkyblockItems.get(event.getItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING)) != null && Items.SkyblockItems.get(event.getItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING)).customDataContainer.containsValue("term"))
		    	  if(Main.termhits.get(event.getPlayer()) >= 3) {
		    		  final Particle.DustOptions dust = new Particle.DustOptions(
		    	                Color.fromRGB((int) 255, (int) 0, (int) 0), 1);
		    		  
						
						for (double i = 0; i <= 15; i+=0.5) { //iterate up to but not beyond point b
							
						    Vector point = event.getPlayer().getEyeLocation().getDirection().multiply(i);
						    Location loc = event.getPlayer().getEyeLocation();
						    loc.add(point);
						    event.getPlayer().getLocation().getWorld().spawnParticle(Particle.DRIP_LAVA, loc.getX(), loc.getY(), loc.getZ(),1);
						    event.getPlayer().getLocation().getWorld().spawnParticle(Particle.REDSTONE, loc.getX(), loc.getY(), loc.getZ(),1,dust);
						}
						
						ArrayList<Entity> alrHitEntitys = new ArrayList<>();
						
						for (double i = 0; i <= 15; i+=0.1) {
							if(event.getPlayer().getWorld().getNearbyEntities(  event.getPlayer().getEyeLocation().add(event.getPlayer().getEyeLocation().getDirection().multiply(i)),0.2, 0.2, 0.2).isEmpty())
								continue;
						
							for(Entity entity: event.getPlayer().getWorld().getNearbyEntities(  event.getPlayer().getEyeLocation().add(event.getPlayer().getEyeLocation().getDirection().multiply(i)),1, 1, 1)) {
								if(entity instanceof LivingEntity && !(entity instanceof ArmorStand) && entity != event.getPlayer() && !alrHitEntitys.contains(entity)) {
									LivingEntity e =  (LivingEntity) entity;
									e.damage(0.0000001);
									double stre = Main.playerstrengthcalc(event.getPlayer());
									double dmg = Main.weapondamage(event.getItem());
									double cd = Main.playercdcalc(event.getPlayer());
									double damage = (5 + (float)dmg) * (1+((float)stre/100)) * (1+((float)cd/100));
									damage *= 2;
									if(SkyblockEntity.livingEntity.containsKey(e))
										if(SkyblockEntity.livingEntity.get(e).getHealth() - damage < 0){
											SkyblockEntity.livingEntity.get(e).setHealth(0);
									}else
										if(Main.currentityhealth.get(e) != null)
									if(Main.currentityhealth.get(e) - damage < 0 ) {
										Main.currentityhealth.replace(e, 0);


									}else
										if(!SkyblockEntity.livingEntity.containsKey(e))
										Main.currentityhealth.replace(e,(int) (Main.currentityhealth.get(e) -damage));
										else
											SkyblockEntity.livingEntity.get(e).setHealth((int) (SkyblockEntity.livingEntity.get(e).getHealth() - damage));
									Main.updateentitystats(e);
									alrHitEntitys.add(entity);
									final int FINAL_DAMAGE = (int)damage;
									Location loc = new Location(e.getWorld(), e.getLocation().getX() ,e.getLocation().getY() + 0.5 , e.getLocation().getZ());
									ArmorStand stand = (ArmorStand) e.getWorld().spawn(loc, ArmorStand.class, armorstand ->{armorstand.setVisible(false);
									armorstand.setGravity(false);
									   
									armorstand.setCustomNameVisible(true);
										
									armorstand.setInvulnerable(true);
										
											String name = "§f✧";
											String num = "" + (int) FINAL_DAMAGE;
											int col =1;
											int coltype = 1;
											String colstr = "§f";
											
											for (char x : num.toCharArray()) {
												name = name + colstr + x;
												++col;
												if(col ==2) {
													col = 0;
													++coltype;
													switch(coltype) {
													case 1:
														colstr = "§f";
														break;
													case 2:
														colstr = "§e";
														break;
													case 3:
														colstr = "§6";
														coltype = 0;
														break;
														
													}
													
												}
											}
											String x = "✧";
											name = name + colstr + x;
											armorstand.setCustomName(name);
											
										
										armorstand.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 999999));
										armorstand.addScoreboardTag("damage_tag");
										armorstand.setArms(false);
										armorstand.setBasePlate(false);
										armorstand.setMarker(true);});
										Main.getMain().killarmorstand(stand);
									  stand.setCustomNameVisible(true);
									
								}
							}
						}
						Main.termhits.replace(event.getPlayer(), 0);
						Main.updatebar(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()) );
						return;
		    	  }
		      }
		
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR|| event.getAction() == Action.LEFT_CLICK_BLOCK) 
	      if(event.getItem() != null && event.getItem().getType() == Material.BOW) {
	             ItemStack item = event.getItem();
	             if(item.getItemMeta() == null || item.getItemMeta().getPersistentDataContainer() == null || item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ability"), PersistentDataType.STRING) == null || !item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ability"), PersistentDataType.STRING).contains("shortbow"))
	            	 return;
	             
	            
	            event.setCancelled(true);
	            final  boolean infinity;
	            if(item.getEnchantments().containsKey(Enchantment.ARROW_INFINITE)) {
	            	 infinity = true;
	            	}else {
	            		 infinity = false;
	            		}
	            if(!Main.shortbow_cd.get(event.getPlayer()))
	            try {
	            	
	            event.getPlayer().getInventory().forEach(i ->{
	            	if(i != null) {
	            	if(i.getItemMeta() == null || i.getItemMeta().getPersistentDataContainer() == null || i.getItemMeta().getPersistentDataContainer().get(NamespacedKey.minecraft("spetial"), PersistentDataType.STRING) == null || !i.getItemMeta().getPersistentDataContainer().get(NamespacedKey.minecraft("spetial"), PersistentDataType.STRING).contains("fakearrow")) {
	            		
	            	}
	            	else {
	            		if(!infinity)
	            		i.setAmount(i.getAmount() -1);
	            		event.getPlayer().updateInventory();
	            		SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer()) ;
	            		 Arrow arrow_entity = event.getPlayer().launchProjectile(Arrow.class);
	            		 
	            		 
	            		 
	            		 int cc = (int)Main.playercccalc(player);
	            		 
	            			

	            			  int stre =(int) Main.playerstrengthcalc(player);
	            			  double cd = Main.playercdcalc(player);
	            			  int weapondmg =(int) Main.weapondamage(event.getItem());
	            			  int ferocity = (int)Main.playerferocitycalc(player);

	            			 
	            			  arrow_entity.addScoreboardTag("cd:" + cd);
	            			  arrow_entity.addScoreboardTag("cc:" + cc);
	            			  arrow_entity.addScoreboardTag("strength:" + stre);
	            			  arrow_entity.addScoreboardTag("ferocity:" + ferocity);
	            			  arrow_entity.addScoreboardTag("dmg:" + weapondmg);
	            			  
	            			  if(item.getEnchantments().containsKey(Enchantment.ARROW_DAMAGE)) {
	            				  arrow_entity.addScoreboardTag("power:" + item.getEnchantments().get(Enchantment.ARROW_DAMAGE));
	            			  }
							  for(Enchantment enchantment : item.getItemMeta().getEnchants().keySet()){
								  arrow_entity.addScoreboardTag(enchantment.getKey().getKey() + ":" + item.getItemMeta().getEnchantLevel(enchantment));
							  }
	            			  
	            			  arrow_entity.setDamage(1);
	            			 
								
								if(HydraStrike.hasHydraStrike(player)) {
									arrow_entity.setVelocity(arrow_entity.getVelocity().multiply(HydraStrike.get(player).getFlySpeed())); 
									
							
								if(HydraStrike.get(player).stacks == 10) {
		            				  Arrow arrow_entity2 = event.getPlayer().launchProjectile(Arrow.class);
		 	            			 arrow_entity2.setVelocity(arrow_entity.getVelocity().rotateAroundY(Math.toRadians(5)));
		 	            			arrow_entity2.setDamage(1);
		 	            			Arrow arrow_entity3 = event.getPlayer().launchProjectile(Arrow.class);
			            			 arrow_entity3.setVelocity(arrow_entity.getVelocity().rotateAroundY(Math.toRadians(-5)));
			            			 arrow_entity3.setDamage(1);
			            			 arrow_entity.addScoreboardTag("term:" + player.getName());
			            			 arrow_entity.getScoreboardTags().forEach(tag->{
			            				 arrow_entity2.addScoreboardTag(tag);
			            				 arrow_entity3.addScoreboardTag(tag);
			            			 });
		            		 }}
	            			  
	            			  player.playSound(player.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1, 1);
	            			  if(item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey( Main.getMain(), "shoottype"), PersistentDataType.STRING) != null && item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey( Main.getMain(), "shoottype"), PersistentDataType.STRING).equals("term")) {
	            				  Arrow arrow_entity2 = event.getPlayer().launchProjectile(Arrow.class);
	 	            			 arrow_entity2.setVelocity(arrow_entity.getVelocity().rotateAroundY(Math.toRadians(5)));
	 	            			arrow_entity2.setDamage(1);
	 	            			Arrow arrow_entity3 = event.getPlayer().launchProjectile(Arrow.class);
		            			 arrow_entity3.setVelocity(arrow_entity.getVelocity().rotateAroundY(Math.toRadians(-5)));
		            			 arrow_entity3.setDamage(1);
		            			 arrow_entity.addScoreboardTag("term:" + player.getName());
		            			 arrow_entity.getScoreboardTags().forEach(tag->{
		            				 arrow_entity2.addScoreboardTag(tag);
		            				 arrow_entity3.addScoreboardTag(tag);
		            			 });
	            		 }
	            			  
	            			  
	            		 Main.getMain().juju_cooldown(event.getPlayer());
	            		 
	            		 throw new RuntimeException();
	            	}
	            	}
	            	

	            });
	            }
	            catch(RuntimeException e) {
	            	
	            }
	           
	            
	      }
	}
	@EventHandler
	public void ReplaceArrows(PlayerSwapHandItemsEvent event) {
		event.setCancelled(true);
	}
	@EventHandler
	public void swap(PlayerItemHeldEvent event) {

		 Main.item_updater(event.getPlayer().getInventory().getItem(event.getNewSlot()),SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
		event.getPlayer().updateInventory();
		Player player = event.getPlayer();
		player.updateInventory();
		ItemStack item = player.getInventory().getItem(event.getNewSlot());
		
		if(item == null || item.getItemMeta() == null || item.getItemMeta().getPersistentDataContainer() == null || item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ability"), PersistentDataType.STRING) == null || !item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ability"), PersistentDataType.STRING).contains("shortbow")) {
       
       		for(int slot = 0; slot<player.getInventory().getSize()-1; ++slot){
    			ItemStack i = player.getInventory().getItem(slot);
    			if(i == null)
    				continue;
    			
    			if(i.getItemMeta() == null || i.getItemMeta().getPersistentDataContainer() == null || i.getItemMeta().getPersistentDataContainer().get(NamespacedKey.minecraft("spetial"), PersistentDataType.STRING) == null || !i.getItemMeta().getPersistentDataContainer().get(NamespacedKey.minecraft("spetial"), PersistentDataType.STRING).contains("fakearrow"))
	            	 continue;
    			
    			
    				if(i.getItemMeta().hasDisplayName()) {
    					player.getInventory().setItem(slot, new ItemStack(Material.ARROW));
    					player.getInventory().getItem(slot).setAmount(i.getAmount());
    					player.getInventory().getItem(slot).getItemMeta().setDisplayName(i.getItemMeta().getDisplayName());;
    					player.updateInventory();
    				}else {
    					player.getInventory().setItem(slot, new ItemStack(Material.ARROW));
    					player.getInventory().getItem(slot).setAmount(i.getAmount());
    					player.updateInventory();
    				}
    			
    			
    		
       	 }
			
			
       	 }
		else {
		
		for(int slot = 0; slot<player.getInventory().getSize()-1; ++slot){
			ItemStack i = player.getInventory().getItem(slot);
			if(i != null)
			if(i.getType() == Material.ARROW) {
				if(i.getItemMeta().hasDisplayName()) {
					player.getInventory().setItem(slot, Items.FakeArrow(i.getItemMeta().getDisplayName()));
					player.getInventory().getItem(slot).setAmount(i.getAmount());
					player.updateInventory();
				}else {
					player.getInventory().setItem(slot, Items.FakeArrow("Arrow"));
					player.getInventory().getItem(slot).setAmount(i.getAmount());
					player.updateInventory();
				}
			}
			
		}
		}
	}
	@EventHandler
	public void stopTeleport(EntityTeleportEvent event) {
		Entity e = event.getEntity();
		if(e.getScoreboardTags().contains("voidgloomt1") || e.getScoreboardTags().contains("voidgloomt2") || e.getScoreboardTags().contains("voidgloomt3") || e.getScoreboardTags().contains("voidgloomt4")) {
			event.setCancelled(true);
			
		}
	}
	@SuppressWarnings("unused")
	@EventHandler
	public void bowshootevent(EntityShootBowEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			return;
		}
		SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer( ((Player) event.getEntity()).getPlayer());
		
		Entity arrow_entity = event.getProjectile();

		  
		  int cc =(int) Main.playercccalc(player);
		 
		float damage;

		  int stre = (int)Main.playerstrengthcalc(player);
		  double cd = Main.playercdcalc(player);
		  int weapondmg = (int)Main.weapondamage(event.getBow());

		  if(event.getForce() != 1.0)
			  cc = 0;
		  arrow_entity.addScoreboardTag("cd:" + cd);
		  arrow_entity.addScoreboardTag("cc:" + cc);
		  arrow_entity.addScoreboardTag("strength:" + stre);
		  arrow_entity.addScoreboardTag("dmg:" + weapondmg);
		  arrow_entity.addScoreboardTag("ferocity:" + Main.playerferocitycalc(player));
		for(Enchantment enchantment : event.getBow().getItemMeta().getEnchants().keySet()){
			arrow_entity.addScoreboardTag(enchantment.getKey().getKey() + ":" + event.getBow().getItemMeta().getEnchantLevel(enchantment));
		}
		  if(HydraStrike.hasHydraStrike(player)) {
				arrow_entity.setVelocity(arrow_entity.getVelocity().multiply(HydraStrike.get(player).getFlySpeed())); 
				
		
			if(HydraStrike.get(player).stacks == 10) {
				  Arrow arrow_entity2 = player.launchProjectile(Arrow.class);
   			 arrow_entity2.setVelocity(arrow_entity.getVelocity().rotateAroundY(Math.toRadians(5)));
   			arrow_entity2.setDamage(1);
   			Arrow arrow_entity3 = player.launchProjectile(Arrow.class);
  			 arrow_entity3.setVelocity(arrow_entity.getVelocity().rotateAroundY(Math.toRadians(-5)));
  			 arrow_entity3.setDamage(1);
  			 arrow_entity.addScoreboardTag("term:" + player.getName());
  			 arrow_entity.getScoreboardTags().forEach(tag->{
  				 arrow_entity2.addScoreboardTag(tag);
  				 arrow_entity3.addScoreboardTag(tag);
  			 });
		 }}
		  
		
		ItemStack bow = event.getBow();
		if(bow.getItemMeta() == null)
			  return;
		ItemMeta meta = bow.getItemMeta();
		PersistentDataContainer data = meta.getPersistentDataContainer();
		if(data == null)
			return;
		String reforge = data.get(new NamespacedKey(Main.getMain(), "reforge"), PersistentDataType.STRING);
		if(reforge == null)
			return;
		if(reforge.equalsIgnoreCase("spiritual")) {

				
			
				
			
				

				try {
					player.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, player.getLocation().add(0, 0.5, 0), 5, 0, 0, 0, 3, null, true);
					Location loc = player.getLocation();
					List<Entity> close = (List<Entity>) player.getWorld().getNearbyEntities(loc, 4, 4, 4);
					close.remove(player);
					for(Entity target : close){
				         if(target instanceof LivingEntity){
				      ((LivingEntity) target).damage(5, player);
				         }
					}
					
				
   
			}catch (Exception e) {
				System.out.println(e);
			}
			event.getProjectile().addScoreboardTag("spiritual");
			if(!(bow.getItemMeta().getEnchantLevel(Enchantment.ARROW_INFINITE) >= 1)){
			if ((int) (Math.random() * 4) == 1) {
				ItemStack arrow = event.getConsumable();
				int ammount = arrow.getAmount();
				if(ammount >= 64) {
					player.getInventory().addItem(newarrow(arrow.getType(), arrow));
				}else {
				arrow.setAmount(ammount + 1);
			}
				player.updateInventory();
			
			}}
			
		}}
	public static ItemStack newarrow(Material material, @NotNull ItemStack old) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		  
		if(old.getItemMeta().getDisplayName() != "") {
		  meta.setDisplayName(old.getItemMeta().getDisplayName());
		}

		  item.setItemMeta(meta);
		  ItemStack arrow = item;
		
		return arrow;
	}
	@EventHandler
	public void ProjectileHitEvent(ProjectileHitEvent e) {
		if(e.getHitBlock() != null){
			e.getEntity().remove();
			if(e.getEntity() instanceof FishHook &&  LavaFishingHook.contains((FishHook)e.getEntity()))
				LavaFishingHook.get((FishHook) e.getEntity()).remove();
		}
		if(e.getHitEntity() != null && e.getHitEntity().getScoreboardTags().contains("npc")){
			e.setCancelled(true);
			return;
		}
if(e.getEntity().getShooter() instanceof Player) {
	if(e.getHitEntity() != null && e.getHitEntity() instanceof Player){
		e.setCancelled(true);
		return;
	}
	if(e.getHitEntity() == null)
		return;
	

		 HashMap<String, Integer> stats = new HashMap<String, Integer>();
		 SkyblockPlayer player = null;
		int power = 0;double cd = 0;
		for(String str : e.getEntity().getScoreboardTags()) {
if(str.startsWith("cd:")) {
			String[] num = str.split(":");
			cd = Double.parseDouble(num[1]);
			}
if(str.startsWith("cc:")) {
	String[] num = str.split(":");
	 stats.put("cc", Integer.parseInt(num[1])) ;
}
if(str.startsWith("strength:")) {
	String[] num = str.split(":");
	 stats.put("stre", Integer.parseInt(num[1])) ;
}
if(str.startsWith("dmg:")) {
	String[] num = str.split(":");
	stats.put("dmg", Integer.parseInt(num[1]));
}
if(str.startsWith("ferocity:")) {
	String[] num = str.split(":");
	stats.put("ferocity", Integer.parseInt(num[1]));
}
if(str.startsWith("term:")) {
	stats.put("term", 1);
}	
if(str.startsWith("power:"))
	power = Integer.parseInt(str.split(":")[1]);

}
		player =  SkyblockPlayer.getSkyblockPlayer((Player) e.getEntity().getShooter());

		if(e.getEntity().getScoreboardTags().contains("hit:" + e.getHitEntity().getEntityId()))
			return;
		e.getEntity().addScoreboardTag("hit:" + e.getHitEntity().getEntityId());





	 
		
		int cc;
		int stre;
		int dmg;
		int ferocity  = 0;
		
		
		if(stats.containsKey("cc")) {
			 cc = stats.get("cc");
		}else {
			 cc = 0;
		}
		if(stats.containsKey("stre")) {
			stre = stats.get("stre");
		}else {
			stre = 0;
		}
		if(stats.containsKey("dmg")) {
			dmg = stats.get("dmg");
		}else {
			dmg = 0;
		}
		if(stats.containsKey("ferocity")) {
			ferocity = stats.get("ferocity");
		}else {
			ferocity = 0;
		}

		
	
		if(e.getHitEntity() != null) {
			
			if(e.getHitEntity().getScoreboardTags().contains("invinc")) {
				e.setCancelled(true);
				return;
			}
			
			if(Main.baseentityhealth.containsKey(e.getHitEntity()) || SkyblockEntity.livingEntity.containsKey(e.getHitEntity())) {
		
		int cccalc = (int )(Math.random() * 100 + 1);
		Calculator calculator = new Calculator(e.getEntity());
		calculator.playerToEntityDamage((LivingEntity) e.getHitEntity(), player);

    		double mult = 1;
    		double extramult = 0;
    		if(HydraStrike.hasHydraStrike(player)) {
    			extramult = 0.02*HydraStrike.get(player).stacks;
    		}
    		mult += extramult;
    		
    		mult += power*0.08;

				calculator.damage *= mult;

				calculator.damageEntity((LivingEntity) e.getHitEntity(), player, DamageCause.PROJECTILE);

    	/*if(SkyblockEntity.livingEntity.containsKey(e.getHitEntity()))
    		SkyblockEntity.livingEntity.get(e.getHitEntity()).damage( damage,player);
    	else
    	Main.currentityhealth.replace((LivingEntity) e.getHitEntity(), (int) (Main.currentityhealth.get(e.getHitEntity()) - damage));
		  if((!SkyblockEntity.livingEntity.containsKey(e.getHitEntity()) &&Main.currentityhealth.get(e.getHitEntity()) <= 0) || (SkyblockEntity.livingEntity.containsKey(e.getHitEntity()) &&SkyblockEntity.livingEntity.get(e.getHitEntity()).getHealth() <= 0)  ) {
			  LivingEntity entity = (LivingEntity) e.getHitEntity();
			  Main.EntityDeath(entity);
			  entity.addScoreboardTag("arrowkill:" + player.getName());
			  entity.setHealth(1);
			  entity.damage(1);
			  if(SkyblockEntity.livingEntity.containsKey(e.getHitEntity()))
				  SkyblockEntity.livingEntity.remove(e.getHitEntity());
			  
		  }
		  else
		  Main.updateentitystats((LivingEntity)e.getHitEntity());
		  */
		  double damage = calculator.damage;

		  if(calculator.getResult().isCancelled())
			  return;

		  if(stats.containsKey("term")) {
			  
			  for(String tag : e.getEntity().getScoreboardTags())
				  if(tag.startsWith("term:"))
					  player = SkyblockPlayer.getSkyblockPlayer( Bukkit.getPlayer(tag.split(":")[1]));
			  if(player != null)
			  Main.termhits.replace(player, Main.termhits.get(player) + 1);
			  Main.updatebar(SkyblockPlayer.getSkyblockPlayer(player));
		  }
		  
		final double FINAL_DAMAGE = damage;
		
		if(ferocity != 0) {
			
			  
			  
			  if(ferocity < 100) {
				  Random r = new Random();
				  int low = 1;//includes 1
				  int high = 100;// includes 100
				  int result = r.nextInt(high-low) + low;
				  if(ferocity >= result) {
					  
					  Ferocity.hit((LivingEntity) e.getHitEntity(),(int) damage, cccalc <= cc, player);
					  Main.updateentitystats((LivingEntity)e.getHitEntity());
				  }
			  }else {
				 double hits =(double) ferocity / 100;
				 
				  if(hits % 1 == 0) {
					  
					  ferocity_call((Entity) e.getHitEntity(), damage, cccalc, cc, player, (int)hits);
					   
					 
				  }else {
					 int minus = (int) ((int)hits * 100);
					 double hitchance = (double)ferocity - (double)minus;
					
					 Random r = new Random();
					  int low = 1;//includes 1
					  int high = 100;// includes 100
					  int result = r.nextInt(high-low) + low;
					 
					  if(hitchance >= result) {
						  hits = hits +1;
					  }
					  ferocity_call((Entity) e.getHitEntity(), damage, cccalc, cc, player, (int)hits);
				  }
			  }
			  }
		  
		
		Location loc = new Location(e.getEntity().getWorld(), e.getEntity().getLocation().getX() ,e.getEntity().getLocation().getY() + 0.5 , e.getEntity().getLocation().getZ());
		ArmorStand stand = (ArmorStand) e.getEntity().getWorld().spawn(loc, ArmorStand.class, armorstand ->{armorstand.setVisible(false);
		armorstand.setGravity(false);
		   
		armorstand.setCustomNameVisible(true);
			
		armorstand.setInvulnerable(true);
			if(cccalc <= cc) {
				String name = "§f✧";
				String num = "" + (int) FINAL_DAMAGE;
				int col =1;
				int coltype = 1;
				String colstr = "§f";
				
				for (char x : num.toCharArray()) {
					name = name + colstr + x;
					++col;
					if(col ==2) {
						col = 0;
						++coltype;
						switch(coltype) {
						case 1:
							colstr = "§f";
							break;
						case 2:
							colstr = "§e";
							break;
						case 3:
							colstr = "§6";
							coltype = 0;
							break;
							
						}
						
					}
				}
				String x = "✧";
				name = name + colstr + x;
				armorstand.setCustomName(name);
				
			}else
				armorstand.setCustomName("§7" + (int)FINAL_DAMAGE);
			armorstand.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 999999));
			armorstand.addScoreboardTag("damage_tag");
			armorstand.setArms(false);
			armorstand.setBasePlate(false);
			armorstand.setMarker(true);});
			Main.getMain().killarmorstand(stand);
		  stand.setCustomNameVisible(true);
		  
		  
		  
		}else{
				if(e.getHitEntity() instanceof CraftEnderDragonPart){
					Calculator calculator = new Calculator(e.getEntity());
					calculator.playerToEntityDamage(((CraftEnderDragonPart) e.getHitEntity()).getParent(), player);

					double mult = 1;
					double extramult = 0;
					if(HydraStrike.hasHydraStrike(player)) {
						extramult = 0.02*HydraStrike.get(player).stacks;
					}
					mult += extramult;

					mult += power*0.08;

					calculator.damage *= mult;

					calculator.damageEntity(((CraftEnderDragonPart) e.getHitEntity()).getParent(), player, DamageCause.PROJECTILE);
					calculator.showDamageTag(e.getHitEntity());
				}
			}

		}else {
			e.getEntity().remove();
		}
		}
		  
		  
		


		if(e.getHitEntity() == null)
			return;
		
		
		
		
		if(!(e.getEntity().getShooter() instanceof Player)) {



			if(e.getHitEntity() == null || !(e.getHitEntity() instanceof Player)) 
				return;
			SkyblockPlayer p = SkyblockPlayer.getSkyblockPlayer((Player) e.getHitEntity());

			if(p.getGameMode() == GameMode.CREATIVE) {
				e.setCancelled(true);
				return;
			}
			
			LivingEntity entity = (LivingEntity) e.getEntity().getShooter();
			if(SkyblockEntity.livingEntity.containsKey(entity)){
				
				e.getEntity().addScoreboardTag("hit:" + p.getName());
				float ehp = (float) (float)Main.playerhealthcalc(p)*(1+((float)Main.playerdefcalc(p)/100));
				float effectivedmg = (float)Main.playerhealthcalc(p)/(float)ehp ;
				int totaldmg = (int) ((int) SkyblockEntity.livingEntity.get(entity).getDamage()*effectivedmg);
				
					SkyblockEntity se = SkyblockEntity.livingEntity.get(entity);
					System.out.println(se.getDamage());
					int truedamage = se.getTrueDamage();
					if(truedamage != 0) {
						float trueehp = (float) (float)Main.playerhealthcalc(p)*(1+((float)Main.playertruedefense(p)/100));
						float effectivetruedmg = (float)Main.playerhealthcalc(p)/(float)trueehp;
						totaldmg += (int) ((int) truedamage*effectivetruedmg);
					
				}
				
				
				
				if(Main.absorbtion.get(p) - totaldmg  < 0) {
					float restdamage =   (float)totaldmg - (float) Main.absorbtion.get(p);
					Main.absorbtion.replace(p, 0);
					p.setHealth( p.currhealth  - (int)restdamage, HealthChangeReason.Damage);
				}else {
					Main.absorbtion.replace(p, Main.absorbtion.get(p) - totaldmg);
				}
				

				Main.updatebar(p);
				
				
			}
			
		}

		

	}
	@EventHandler
	public void DamageEvent(org.bukkit.event.entity.EntityDamageEvent  event) {
		if(Main.entitydead.containsKey(event.getEntity()))
			return;
		if(event.getEntity().getScoreboardTags().contains("npc")){
			if(!EntityNPC.isKillable){
				event.setCancelled(true);
			}
			return;
		}
		
		if(event.getEntity() instanceof Player) {
			SkyblockPlayer player =SkyblockPlayer.getSkyblockPlayer((Player) event.getEntity()) ;
			
			if(Main.getMain().getConfig().getBoolean("StatSystem")) {
				if (event.getCause() == DamageCause.FALL) {
					event.setDamage(0);
					
					int falldistance = (int) player.getFallDistance();	
					
					int damage = (int) (falldistance * 5) - 15;
					if(damage < 0)
						damage = 0;
					if(Main.absorbtion.get(player) - damage < 0) {
						int restdamage =  damage-Main.absorbtion.get(player) ;
						Main.absorbtion.replace(player, 0);
						if(player.currhealth - restdamage < 0)
							restdamage = player.currhealth;
						player.setHealth(player.currhealth- restdamage, HealthChangeReason.Damage);
					}else {
						Main.absorbtion.replace(player, Main.absorbtion.get(player) - damage);
					}
					
					if(player.currhealth <= 0) {
						player.getPlayer().setHealth(0);
						
					}else
					Main.updatebar(player);
				}
				if(event.getCause() == DamageCause.VOID)
					player.setHealth(0, HealthChangeReason.Creative);
				Main.updatebar(player);
				
				
			}
				
				}else {
				
					if(event.getEntityType() != EntityType.ARMOR_STAND && !(event.getEntity().getType() == EntityType.WITHER_SKULL)) {
						 if(event.getEntity() instanceof LivingEntity) {
							 if(event.getCause() != DamageCause.CUSTOM && event.getCause() != DamageCause.PROJECTILE) {
					LivingEntity e = (LivingEntity) event.getEntity();
					int damage = (int) event.getDamage();
					event.setDamage(0D);
						if(event.getCause() != DamageCause.ENTITY_ATTACK && event.getCause() != DamageCause.ENTITY_SWEEP_ATTACK) {
							
							if(Main.entitydead.containsKey(event.getEntity()))
								return;
							if(!Main.currentityhealth.containsKey(event.getEntity()))
								return;
						Main.currentityhealth.replace(event.getEntity(), (int) (Main.currentityhealth.get(event.getEntity()) - (damage * 5)));
						
					   Main.updateentitystats((LivingEntity)event.getEntity());
					   Location fakeloc = new Location(event.getEntity().getWorld(), 0,0,0);
						Location loc = new Location(event.getEntity().getWorld(), event.getEntity().getLocation().getX() ,event.getEntity().getLocation().getY() + 0.5 , event.getEntity().getLocation().getZ());
						ArmorStand stand = (ArmorStand) event.getEntity().getWorld().spawnEntity(fakeloc, EntityType.ARMOR_STAND);stand.setVisible(false);
						   stand.setGravity(false);
						   stand.teleport(loc);
						stand.setCustomNameVisible(true);
						

						stand.setInvulnerable(true);
						stand.setCustomName("§7" + (int)damage * 5);
						stand.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 999999));
						stand.addScoreboardTag("damage_tag");
						stand.setArms(false);
						stand.setBasePlate(false);
						stand.setMarker(true);
						
						Main.getMain().killarmorstand(stand);

					  e.setCustomNameVisible(true);
						}}else {
							
							Main.updateentitystats((LivingEntity) event.getEntity());
							
							
						}
							 }else {
								 event.setDamage(0);
						}
						 
					
					}}
				
	}
	 @EventHandler
	 public void onEntityCombust(EntityCombustEvent event){
	 if(event.getEntity() instanceof Zombie||event.getEntity() instanceof Skeleton){
	 event.setCancelled(true);
	  
	 }
	 if(event.getEntity().getScoreboardTags().contains("npc")){
		 event.setCancelled(true);
	 }
	 }
	@EventHandler
	public void EntityDamageEvent(EntityDamageByEntityEvent event) {
		if(event.getEntity().getScoreboardTags().contains("npc")){
			event.setCancelled(true);
			return;
		}
		if(event.getEntity() instanceof Player && event.getDamager() instanceof Player){
			event.setDamage(0);
		    event.setCancelled(true);
			return;
		}

		Entity damager = event.getDamager();

		if(damager instanceof Player) {
			Player player = ((Player) damager).getPlayer();

			ItemStack curenditem = player.getItemInHand();
			if(curenditem.getItemMeta() != null) {
				  
			ItemMeta meta = curenditem.getItemMeta();
			PersistentDataContainer data = meta.getPersistentDataContainer();
			if(data != null) {
				
			String reforge = data.get(new NamespacedKey(Main.getMain(), "reforge"), PersistentDataType.STRING);
			if(reforge != null) {
				
			if(reforge.equalsIgnoreCase("souled")) {
				double abs = event.getDamage();
				abs = abs*0.25;
				double absadd = player.getAbsorptionAmount();
				abs = abs + absadd;
				if(abs > 20) {
					abs = 20;
				}
				player.setAbsorptionAmount(abs);
			}}}}
		}
		if(event.getEntity() instanceof Player && event.getDamager() instanceof LivingEntity ) {
			SkyblockPlayer player =SkyblockPlayer.getSkyblockPlayer((Player) event.getEntity());
			if(SkyblockEntity.isOnCooldown((LivingEntity)event.getDamager())){
				event.setCancelled(true);
				return;
			}
			SkyblockEntity.setOnCooldown((LivingEntity)event.getDamager());

			
			if(Main.getMain().getConfig().getBoolean("StatSystem")) {
				if (event.getCause() == DamageCause.FALL) {
					event.setCancelled(true);
					
					int falldistance = (int) player.getFallDistance();	
					
					int damage = (int) (falldistance * 5) - 15;
					if(damage < 0)
						damage = 0;
					if(Main.absorbtion.get(player) - damage < 0) {
						int restdamage = damage-Main.absorbtion.get(player);
						Main.absorbtion.replace(player, 0);
						player.setHealth(player.currhealth- restdamage, HealthChangeReason.Damage);
					}else {
						Main.absorbtion.replace(player, Main.absorbtion.get(player) - damage);
					}

					Main.updatebar(player);
					
					
						
					
				}else {
					if(event.getDamager() instanceof Player) {
						event.setDamage(0);
						event.setCancelled(true);
						return;
					}else {
					
					
					
					
					float damage = (float) (event.getDamage() * 8);
				event.setDamage(0);
				event.setCancelled(true);
				if(Main.entitydamage.containsKey(event.getDamager()))
					damage = Main.entitydamage.get(event.getDamager());
				if(SkyblockEntity.livingEntity.containsKey(event.getDamager()))
					damage = SkyblockEntity.livingEntity.get(event.getDamager()).getDamage();
				
				float ehp = (float) (float)Main.playerhealthcalc(player)*(1+((float)Main.playerdefcalc(player)/100));
				float effectivedmg = (float)Main.playerhealthcalc(player)/(float)ehp ;
				int totaldmg = (int) (damage*effectivedmg);
				
				if(SkyblockEntity.livingEntity.containsKey(event.getDamager())) {
					SkyblockEntity se = SkyblockEntity.livingEntity.get(event.getDamager());
					int truedamage = se.getTrueDamage();
					if(truedamage != 0) {
						float trueehp = (float) (float)Main.playerhealthcalc(player)*(1+((float)Main.playertruedefense(player)/100));
						float effectivetruedmg = (float)Main.playerhealthcalc(player)/(float)trueehp;
						totaldmg += (int) ((int) truedamage*effectivetruedmg);
					}
				}
					
				
				
				
				if(Main.absorbtion.get(player) - totaldmg  < 0) {
					float restdamage =   (float)totaldmg - (float) Main.absorbtion.get(player);
					Main.absorbtion.replace(player, 0);
					player.setHealth( player.currhealth  - (int)restdamage, HealthChangeReason.Damage);
				}else {
					Main.absorbtion.replace(player, Main.absorbtion.get(player) - totaldmg);
				}
				

				Main.updatebar(player);
				
				System.out.println(event.getDamager().getName() + "<-- Criminal");
				if(Main.zombySlayerLiveDrainready.containsKey(event.getDamager()) && Main.zombySlayerLiveDrainready.get(event.getDamager())) {
					Main.zombySlayerLiveDrainready.replace(event.getDamager(), false);
					if(Main.currentityhealth.get(event.getDamager()) + damage > Main.baseentityhealth.get(event.getDamager()))
						Main.currentityhealth.replace(event.getDamager(), Main.baseentityhealth.get(event.getDamager()));
					else {
						Main.currentityhealth.replace(damager, (int) (Main.currentityhealth.get(damager) + damage));
					Main.updateentitystats((LivingEntity) damager);	
					}
				}
				
				}}
			}
		}else {
			
			if(event.getEntityType() != EntityType.DROPPED_ITEM && event.getEntityType() != EntityType.ARMOR_STAND && !(event.getEntity().getType() == EntityType.WITHER_SKULL) && event.getCause() != DamageCause.PROJECTILE) {
				if(event.getEntity() instanceof LivingEntity) {
				LivingEntity e = (LivingEntity) event.getEntity();
				if(Main.entitydead.containsKey(e))
					return;
				
				event.setDamage(0D);
					  if(event.getDamager() instanceof Player) {
						  if(e instanceof Player){
							  event.setCancelled(true);
							  return;
						  }

						  Player player = (Player) event.getDamager();
						  int cc = (int)Main.playercccalc(player);

						  double damage;
						  Calculator calculator = new Calculator();
						   calculator.playerToEntityDamage(e,SkyblockPlayer.getSkyblockPlayer(player));
						  int cccalc = calculator.cccalc;
						  SkyblockDamageEvent eventt = new SkyblockDamageEvent(SkyblockPlayer.getSkyblockPlayer(player), e, calculator, SkyblockDamageEvent.DamageType.PlayerToEntity, event.getCause());
						  Bukkit.getPluginManager().callEvent(eventt);
						  if(event.isCancelled())
							  return;
						  damage = calculator.damage;


						  boolean voidgloom = false;
						  
						 
						  if(player.getItemInHand().getType() == Material.BOW) {
							  return;
						  }
						 
						
						  event.setDamage(0);
						  if(e.getScoreboardTags().contains("invinc")) {
							  event.setCancelled(true);
							  return;
						  }
						  if(e.getScoreboardTags().contains("voidgloomt1") || e.getScoreboardTags().contains("voidgloomt2") || e.getScoreboardTags().contains("voidgloomt3") || e.getScoreboardTags().contains("voidgloomt4")) {
							  voidgloom = true;
							  final Vector vec = new Vector();
							    e.setVelocity(vec);

							              new BukkitRunnable() {
							                 public void run() {
							                     e.setVelocity(vec);
							                 }
							               }.runTaskLater(Main.getMain(), 1l);

						  /*int stre = Main.playerstrengthcalc(player);
						  double cd = Main.playercdcalc(player);
						  int weapondmg = Main.weapondamage(player.getItemInHand());
						  
						  float preMultiplier = (float) additiveMultiplier(player);
						  
						  
						  if(!Main.voidgloomHitphase.containsKey(e) || !Main.voidgloomHitphase.get(e)) {
						 
				        	if(cccalc <= cc) {
				        		damage = (5 + (float)weapondmg) * (1+((float)stre/100)) * (1+((float)cd/100)) * (1+(preMultiplier)) * (1+(SkyblockPlayer.getSkyblockPlayer(player).getAdititveMultiplier()));
				        		
				        	}else {
				        		damage = (5 + (float)weapondmg) * (1+((float)stre/100))* (1+(preMultiplier));
				        	}*/
							  if(Main.voidgloomHitphase.get(e)){
							  Main.voidgloomHitphaseHits.replace(e, Main.voidgloomHitphaseHits.get(e) - 1);damage = 0;
							  }
						  }else {
							  voidgloom = false;


						  }
				        	if(SkyblockEntity.livingEntity.containsKey(e)) {
				        		SkyblockEntity se = SkyblockEntity.livingEntity.get(e);
				        		se.damage((int)damage,SkyblockPlayer.getSkyblockPlayer(player));
				        		if(se.hasNoKB()) {
				        			new BukkitRunnable() {
										
										@Override
										public void run() {
											// TODO Auto-generated method stub
											e.setVelocity(new Vector(0, 0, 0));
										}
									}.runTaskLater(Main.getMain(), 1);}
				        			
				        		
				        	}else {
				        	int live = Main.currentityhealth.get(event.getEntity()) - (int) damage;
				        	Main.currentityhealth.replace(e, live);}

						  
						 
								
						  
				        	if((SkyblockEntity.livingEntity.containsKey(e) && SkyblockEntity.livingEntity.get(e).getHealth() <= 0) || (!SkyblockEntity.livingEntity.containsKey(e)&&Main.currentityhealth.get(event.getEntity()) <= 0) ) {
				        		e.addScoreboardTag("killer:" + player.getName());
				        		Main.EntityDeath(e);
				        		e.damage(9999999,player);
				        		
				        		if(SkyblockEntity.livingEntity.containsKey(e))
				        		SkyblockEntity.livingEntity.remove(e);
				        		
				        		if(e.getScoreboardTags() != null) {
				        			Set<String> scores = e.getScoreboardTags();
				        			ArrayList<Player> owners = new ArrayList<>();
				        			scores.forEach(tag ->{
										
										if(tag.startsWith("combatxp:")) {
											


											if(Main.SlayerCurrXp.containsKey(player) == true && Main.SlayerName.containsKey(player) == true && Main.SlayerName.get(player).equals("Revenant Horror") && event.getEntityType() == EntityType.ZOMBIE) {
												Main.SlayerCurrXp.replace(player, Main.SlayerCurrXp.get(player) + Integer.parseInt(tag.split(":")[1]));
												SkyblockScoreboard.updateScoreboard(player);
												 Random r = new Random();
												  int low = 0;//includes 1
												  int high = 100;// includes 100
												  int result = r.nextInt(high-low) + low;
												  if(result <= 15) {
													  if(Main.SlayerLevel.get(player) == 4) {
													 low = 1;
													 high = 5;
													 result = r.nextInt(high-low) + low;
													 if(result == 5) {
														 SpawnEggEntitys.SummonRevT4MiniBoss2(event.getEntity().getLocation());
													 }else {
														 SpawnEggEntitys.SummonRevT4MiniBoss1(event.getEntity().getLocation());
													 }
													 }else {
														 SpawnEggEntitys.SummonRevT3MiniBoss1(event.getEntity().getLocation());
													 }
												  }
													
											if(Main.SlayerCurrXp.get(player) >= Main.SlayerRequireXp.get(player)) {
											Main.SlayerCurrXp.remove(player);
											BukkitRunnable runnable =new BukkitRunnable() {
												@Override
												public void run() {
													System.out.println("runnn");
												if(Main.SlayerLevel.get(player) == 1)
													SpawnEggEntitys.SummonT1Rev(event.getEntity().getLocation(), player.getName());
												if(Main.SlayerLevel.get(player) == 2)
													SpawnEggEntitys.SummonT2Rev(event.getEntity().getLocation(), player.getName());
												if(Main.SlayerLevel.get(player) == 3)
													SpawnEggEntitys.SummonT3Rev(event.getEntity().getLocation(), player.getName());
												if(Main.SlayerLevel.get(player) == 4)
													SpawnEggEntitys.SummonT4Rev(event.getEntity().getLocation(), player.getName());
												
												}
												};runnable.runTaskLater(Main.getMain(), 2*20);
											
												
											}
											
											}
										}
										if(tag.startsWith("revslayer")) {
											
											scores.forEach(tags ->{
												if(tags.startsWith("owner")) {
													Player owner = Bukkit.getServer().getPlayer(tags.split(":")[1]);
													owner.sendMessage("Your Rev slayer has ben killed");
													owners.add(owner);
													if(Main.SlayerName.containsKey(owner)) {
													Main.SlayerName.remove(owner);
													Main.SlayerLevel.remove(owner);
													Main.SlayerRequireXp.remove(owner);}
													SkyblockScoreboard.updateScoreboard(player);
												}
											});
										}
										if(tag.startsWith("voidgloomt2")) {
											if( Main.beaconPicketUp.containsKey(e) && Main.beaconPicketUp.get(e) == false) {
											if(Main.beaconBeforeBlock.get(Main.beaconLocation.get(e)) != null)
											Main.beaconLocation.get(e).getBlock().setType(Main.beaconBeforeBlock.get(Main.beaconLocation.get(e)).getType());
											else
											Main.beaconLocation.get(e).getBlock().setType(Material.AIR);
											}
											if(Main.beaconThrown.containsKey(e) && Main.beaconThrown.get(e) == true)
												kill_voidgloom_beacon(e);
											Main.beaconBeforeBlock.remove(Main.beaconLocation.get(e));
											Main.beaconLocation.remove(e);
											Main.beaconOnGround.remove(e);
											Main.beaconOwner.remove(player);
											Main.beaconPicketUp.remove(e);
											Main.beaconThrown.remove(e);
											
											
										}
									});
				        			
				        			if(owners != null) {
				        				owners.forEach(owner->{
				        					event.getEntity().addScoreboardTag("killer:" + owner.getName());
				        				});
				        			}
								}
				        		
				        		if(!e.getScoreboardTags().contains("killer")) {
				        			event.getEntity().addScoreboardTag("killer:" + player.getName());
				        		}
				        		
				        	}
				        	
				        		
					  Main.updateentitystats((LivingEntity)event.getEntity());
					 
						Location loc = new Location(event.getEntity().getWorld(), event.getEntity().getLocation().getX() ,event.getEntity().getLocation().getY() + 0.5 , event.getEntity().getLocation().getZ());

						 
						final String str = String.format("%.0f", (Tools.round(damage, 0)));
						


						ArmorStand stand = (ArmorStand) event.getEntity().getWorld().spawn(loc, ArmorStand.class, armorstand ->{
							armorstand.setVisible(false);
						
							armorstand.setGravity(false);
							armorstand.setMarker(true);
							
						  
							armorstand.setCustomNameVisible(true);
						
							armorstand.setInvulnerable(true);
						if(calculator.isCrit) {
							String name = "§f✧";
							String num = "" + str;
							int col =1;
							int coltype = 1;
							String colstr = "§f";
							
							for (char x : num.toCharArray()) {
								name = name + colstr + x;
								++col;
								if(col ==2) {
									col = 0;
									++coltype;
									switch(coltype) {
									case 1:
										colstr = "§f";
										break;
									case 2:
										colstr = "§e";
										break;
									case 3:
										colstr = "§6";
										coltype = 0;
										break;
										
									}
									
								}
							}
							String x = "✧";
							name = name + colstr + x;
							armorstand.setCustomName(name);
						}else
							armorstand.setCustomName("§7" + str);
						
						armorstand.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 999999));
						armorstand.addScoreboardTag("damage_tag");
						armorstand.setArms(false);
						
						armorstand.setBasePlate(false);});
						
						Main.getMain().killarmorstand(stand);
					  e.setCustomNameVisible(true);
					  if(Main.playerferocitycalc(player) != 0) {
						  int ferocity =(int) Main.playerferocitycalc(player);
						  if(voidgloom) {
							  ferocity = (int) ((double)ferocity*0.25D);
							
						  }
						  
						  if(ferocity < 100) {
							  Random r = new Random();
							  int low = 1;//includes 1
							  int high = 100;// includes 100
							  int result = r.nextInt(high-low) + low;
							  if(ferocity >= result) {
								  
								  Ferocity.hit(e,(int) damage, cccalc <= cc, player);
								  Main.updateentitystats((LivingEntity)e);
							  }
						  }else {
							 double hits =(double) ferocity / 100;
							  if(hits % 1 == 0) {
								  
								  ferocity_call(e, damage, cccalc, cc, player, (int)hits);
								   
								 
							  }else {
								 int minus = (int) ((int)hits * 100);
								 double hitchance = (double)ferocity - (double)minus;
								
								 Random r = new Random();
								  int low = 1;//includes 1
								  int high = 100;// includes 100
								  int result = r.nextInt(high-low) + low;
								 
								  if(hitchance >= result) {
									  hits = hits +1;
								  }
								  ferocity_call(e, damage, cccalc, cc, player, (int)hits);
							  }
						  }
					  }
					  }
				
			}}
				event.setDamage(0);
		}
		
		
		

			if(event.getEntity() instanceof Player) {
				Player player = (Player) event.getEntity();
				ItemStack useitem = player.getItemInUse();
				if(useitem == null)
					return;
				if(useitem.getItemMeta() == null)
					  return;
				ItemMeta meta = useitem.getItemMeta();
				
				PersistentDataContainer data = meta.getPersistentDataContainer();
				if(data == null)
					return;
				String reforge = data.get(new NamespacedKey(Main.getMain(), "reforge"), PersistentDataType.STRING);
				if(reforge == null)
					return;
				if(reforge.equalsIgnoreCase("protective")) {

				Double newdamage = event.getDamage()/2;
				
				
				      event.setDamage(newdamage);
				      
				         

				 

				}
			
		
	}}
	public static double additiveMultiplier(Player player) {
		double multi = 0;
		if(player.getItemInHand() != null && player.getItemInHand().getItemMeta() != null && player.getItemInHand().getItemMeta().getEnchants() != null) {
			
			//Check weapon enchants
			for(Enchantment ench : player.getItemInHand().getItemMeta().getEnchants().keySet()) {
				
				if(ench.getName().equals(Enchantment.DAMAGE_ALL.getName())) {
				
					switch(player.getItemInHand().getItemMeta().getEnchantLevel(ench)) {
					case 1:
						multi = multi + 0.05;
						break;
					case 2:
						multi = multi + 0.1;
						break;
					case 3:
						multi = multi + 0.15;
						break;
					case 4:
						multi = multi + 0.20;
						break;
					case 5:
						multi = multi + 0.30;
						break;
					case 6:
						multi = multi + 0.45;
						break;
					case 7:
						multi = multi + 0.65;
						break;
						
						
						
					}
				}
			}
		}
		
		System.out.println(multi);
		return multi;
	}
	public static void kill_voidgloom_beacon(Entity e) {
		e.getWorld().getEntities().forEach(entity->{
			if(entity.getScoreboardTags() != null) {
				entity.getScoreboardTags().forEach(tag->{
					if(tag.startsWith("entity:")) {
						if(Bukkit.getEntity(UUID.fromString(tag.split(":")[1])) == e) {
							entity.remove();
							}
						}
				});
			}
		});
	}
	@EventHandler
	public void DetectBeacon(EntityChangeBlockEvent event) {
		
		if(event.getEntity() instanceof FallingBlock) {
			FallingBlock block = (FallingBlock) event.getEntity();
			if(block.getScoreboardTags().contains("voidgloom_beacon"))
			System.out.println(block.getLocation());
			block.getScoreboardTags().forEach(tag ->{
				if(tag.startsWith("entity:")) {
					Main.beaconBeforeBlock.put(event.getBlock().getLocation(), null);
					Main.beaconLocation.put(Bukkit.getEntity(UUID.fromString(tag.split(":")[1])), event.getBlock().getLocation());
					Main.beaconOnGround.put(Bukkit.getEntity(UUID.fromString(tag.split(":")[1])), true);
					Main.beaconPicketUp.put(Bukkit.getEntity(UUID.fromString(tag.split(":")[1])), false);
					Main.beaconThrown.remove(Bukkit.getEntity(UUID.fromString(tag.split(":")[1])));
					Bukkit.getEntity(UUID.fromString(tag.split(":")[1])).getScoreboardTags().forEach(tags ->{
						
					
					if(tags.startsWith("owner:")) {
			    			Main.beaconOwner.put(Bukkit.getPlayer(tags.split(":")[1]), Bukkit.getEntity(UUID.fromString(tag.split(":")[1])));
			    			System.out.println(tags.split(":")[1]);
			    			}
			    		});
			    	
					Main.voidgloom_kill_beacon((Enderman) Bukkit.getEntity(UUID.fromString(tag.split(":")[1])));
				}
				
			});
		}
	}
	public static void ferocity_call(Entity e, double damage, int cccalc, int cc, Player player, int times) {  
		 HashMap<Player,Integer> hits = new HashMap<>();
		 hits.put(player, times);
		if(!hits.containsKey(player))
			return;
		  BukkitRunnable runnable = new BukkitRunnable(){
			  @Override
			  public void run(){
				  Ferocity.hit((LivingEntity) e,(int) damage, cccalc <= cc, player);
				  
				  Main.updateentitystats((LivingEntity)e);
				  if( getEntityHealth((LivingEntity)e) <= 0 )
					  hits.replace(player,  1);
				  hits.replace(player, hits.get(player)-1);
				  if(hits.get(player) != 0)
					  ferocity_call(e, damage, cccalc, cc, player, hits.get(player) );
				  else
					  hits.remove(player);
			  }
		  };runnable.runTaskLater(Main.getMain(), 2);
		}
		private static int getEntityHealth(LivingEntity entity){
		if(SkyblockEntity.livingEntity.containsKey(entity))
			return SkyblockEntity.livingEntity.get(entity).getHealth();
		if(Main.currentityhealth.containsKey(entity))
			return Main.currentityhealth.get(entity);


		return 0;
		}
	public static void ferocity_Player_call(Player e, double damage, int cccalc, int cc, Player player, int times, EntityDamageByEntityEvent event) {  
		 HashMap<Player,Integer> hits = new HashMap<>();
		 hits.put(player, times);
		if(!hits.containsKey(player))
			return;
		  BukkitRunnable runnable = new BukkitRunnable(){
			  @Override
			  public void run(){
				  Ferocity.playerhit(e,(int) damage, cccalc <= cc, event);
				  
				  Main.updatebar(SkyblockPlayer.getSkyblockPlayer(e));
				  if( SkyblockPlayer.getSkyblockPlayer(e) == null|| SkyblockPlayer.getSkyblockPlayer(e).currhealth <= 0 )
					  hits.replace(player,  1);
				  hits.replace(player, hits.get(player)-1);
				  if(hits.get(player) != 0)
					  ferocity_call(e, damage, cccalc, cc, player, hits.get(player) );
				  else
					  hits.remove(player);
			  }
		  };runnable.runTaskLater(Main.getMain(), 2);
		}
	@EventHandler
	public void inventoryUpdate(InventoryOpenEvent event) {
		Player player = (Player) event.getPlayer();
		HashMap<Player, Integer> slot = new HashMap<>();
		slot.put(player, 0);
		player.getInventory().forEach(item->{
			Main.item_updater(item,SkyblockPlayer.getSkyblockPlayer(player));
		});
	}
	@EventHandler
	public void inventoryUpdate(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		HashMap<Player, Integer> slot = new HashMap<>();
		slot.put(player, 0);
		new BukkitRunnable(){
		@Override
		public void run()
		{
		player.getInventory().forEach(item->{
			if(slot.get(player) != event.getSlot())
			  Main.item_updater(item,SkyblockPlayer.getSkyblockPlayer(player));
			slot.put(player, slot.get(player) + 1);
		});
		}
		}.runTaskLater(Main.getMain(), 1L);
		
	}
	@EventHandler
	public void Reforging(InventoryClickEvent event) {
		  if (!event.getView().getTitle().contains("Soul Reforging"))
			  return;
		  if (event.getCurrentItem() == null)
			  return;
		  if (event.getCurrentItem().getItemMeta() == null)
			  return;
		  
		  Player player = (Player) event.getWhoClicked();
		  event.setCancelled(true);
		  
		  if(event.getClickedInventory().getType() == InventoryType.PLAYER) {
			  if(iteminforge == false) {
				  ItemStack item = event.getCurrentItem();
				  
				  ItemMeta meta = item.getItemMeta();
				  
				  PersistentDataContainer data = meta.getPersistentDataContainer();
				  
				  if (!data.has(new NamespacedKey(Main.getMain(), "reforge"), PersistentDataType.STRING)) {
				  

					  if(event.getCurrentItem().getType() == Material.WOODEN_AXE) {
							 ItemStack baseItem = event.getCurrentItem();
							 SoulReforging.setItem(22, baseItem);
							 baseItem.setAmount(0);
							 
							 SoulReforging.setItem(31, getConfirmButton(3));
							 soulcost = 3;
							 iteminforge = true;
							 
							 return;
						 }
					  if(event.getCurrentItem().getType() == Material.STONE_AXE) {
							 ItemStack baseItem = event.getCurrentItem();
							 SoulReforging.setItem(22, baseItem);
							 baseItem.setAmount(0);
							 
							 SoulReforging.setItem(31, getConfirmButton(5));
							 soulcost = 5;
							 iteminforge = true;
							 
							 return;
						 }
					  if(event.getCurrentItem().getType() == Material.GOLDEN_AXE) {
							 ItemStack baseItem = event.getCurrentItem();
							 SoulReforging.setItem(22, baseItem);
							 baseItem.setAmount(0);
							 
							 SoulReforging.setItem(31, getConfirmButton(5));
							 soulcost = 5;
							 iteminforge = true;
							 
							 return;
						 }
					  if(event.getCurrentItem().getType() == Material.IRON_AXE) {
							 ItemStack baseItem = event.getCurrentItem();
							 SoulReforging.setItem(22, baseItem);
							 baseItem.setAmount(0);
							 
							 SoulReforging.setItem(31, getConfirmButton(7));
							 soulcost = 7;
							 iteminforge = true;
							 
							 return;
						 }
					  if(event.getCurrentItem().getType() == Material.DIAMOND_AXE) {
							 ItemStack baseItem = event.getCurrentItem();
							 SoulReforging.setItem(22, baseItem);
							 baseItem.setAmount(0);
							 
							 SoulReforging.setItem(31, getConfirmButton(9));
							 soulcost = 9;
							 iteminforge = true;
							 
							 return;
						 }
					  if(event.getCurrentItem().getType() == Material.NETHERITE_AXE) {
							 ItemStack baseItem = event.getCurrentItem();
							 SoulReforging.setItem(22, baseItem);
							 baseItem.setAmount(0);
							 
							 SoulReforging.setItem(31, getConfirmButton(10));
							 soulcost = 10;
							 iteminforge = true;
							 
							 return;
						 }
					  if(event.getCurrentItem().getType() == Material.BOW) {
							 ItemStack baseItem = event.getCurrentItem();
							 SoulReforging.setItem(22, baseItem);
							 baseItem.setAmount(0);
							 
							 SoulReforging.setItem(31, getConfirmButton(6));
							 soulcost = 6;
							 iteminforge = true;
							 
							 return;
						 }
					  if(event.getCurrentItem().getType() == Material.SHIELD) {
							 ItemStack baseItem = event.getCurrentItem();
							 SoulReforging.setItem(22, baseItem);
							 baseItem.setAmount(0);
							 
							 SoulReforging.setItem(31, getConfirmButton(5));
							 soulcost = 5;
							 iteminforge = true;
							 
							 return;
						 }
			 
			 return;
				  }else player.sendMessage("This item has already a Reforge");
			 
			 
			 return;
			  
			  }
			  
			  
			  return;
		  }
		  if (event.getSlot() == 22) {

			  if(iteminforge == true) {
				  ItemStack getback = event.getInventory().getItem(22);
				  if(hasfreeSlot(player) == true)
				  player.getInventory().addItem(getback);
				  else
					  ((CraftWorld)player.getLocation().getWorld()).dropItem(player.getLocation(), getback);
				  SoulReforging.setItem(22, getDenyButton());
				  SoulReforging.setItem(31, getDenyfield());
				  iteminforge = false;
				  
			  }
			  
			  return;
			  
		  
		  }
		  if(event.getSlot() == 31) {
			  if(iteminforge == true) {
				  ConfigFile.reload();
				 int souls = (int) ConfigFile.get().get(player.getUniqueId().toString() + ".souls");
				 if(souls >= soulcost) {
					 souls = souls - soulcost;
					 ConfigFile.get().set(player.getUniqueId().toString() + ".souls", souls);
					 ConfigFile.save();
					 ConfigFile.reload();
					 ItemStack original = event.getInventory().getItem(22);
					 ItemStack newitem = reforge(original);
					 
					 if(hasfreeSlot(player) == true)
					 player.getInventory().addItem(newitem);
					 else
						 ((CraftWorld)player.getLocation().getWorld()).dropItem(player.getLocation(), newitem);
					 
					 ItemStack item = new ItemStack(Material.PLAYER_HEAD);
						SkullMeta Smeta = (SkullMeta) item.getItemMeta();
					  List<String> lore = new ArrayList<>();
						lore.add(" ");
						lore.add(ChatColor.GREEN + "Your Soul drop Chance: §r§l" + ConfigFile.get().get(player.getUniqueId().toString() + ".soulchance") + "%");
						Smeta.setLore(lore);
						lore.clear();
						Smeta.setOwner(player.getName().toString());
						Smeta.setDisplayName(ChatColor.GREEN + "Your Souls: "+ ChatColor.WHITE + "§l" +  ConfigFile.get().get(player.getUniqueId().toString() + ".souls"));
						Smeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
						item.setItemMeta(Smeta);
						SoulReforging.setItem(4, item);
						player.updateInventory();
					 SoulReforging.setItem(22, getDenyButton());
					  SoulReforging.setItem(31, getDenyfield());
					  iteminforge = false;
					 
					 
				 }else player.sendMessage("Not Enouth Souls");
			  }
			  
			  
			  return;
		  }
		  
	
	}
	//reforge item process
	public static ItemStack reforge(ItemStack original) {
		ItemStack item = original;
		ItemMeta meta = item.getItemMeta();
		PersistentDataContainer data = meta.getPersistentDataContainer();
		if(item.getType() == Material.WOODEN_AXE || item.getType() == Material.STONE_AXE || item.getType() == Material.GOLDEN_AXE || item.getType() == Material.IRON_AXE || item.getType() == Material.DIAMOND_AXE || item.getType() == Material.NETHERITE_AXE) {
		if(meta.getDisplayName() == "") {
			meta.setDisplayName("§r§lSouled §r" + getItem(original));
		}else {
		meta.setDisplayName("§r§lSouled §r" + original.getItemMeta().getDisplayName());
		

		}
		data.set(new NamespacedKey(Main.getMain(), "reforge"), PersistentDataType.STRING, "souled");
		item.setItemMeta(meta);
		return item;
		}
		if(item.getType() == Material.BOW) {
			if(meta.getDisplayName() == "") {
				meta.setDisplayName("§r§lSpiritual §r" + getItem(original));
			}else {
			meta.setDisplayName("§r§lSpiritual §r" + original.getItemMeta().getDisplayName());

			}
			data.set(new NamespacedKey(Main.getMain(), "reforge"), PersistentDataType.STRING, "spiritual");
			item.setItemMeta(meta);
			return item;
			}
		if(item.getType() == Material.SHIELD) {
			if(meta.getDisplayName() == "") {
				meta.setDisplayName("§r§lProtective §r" + getItem(original));
			}else {
			meta.setDisplayName("§r§lProtective §r" + original.getItemMeta().getDisplayName());

			}
			data.set(new NamespacedKey(Main.getMain(), "reforge"), PersistentDataType.STRING, "protective");
			item.setItemMeta(meta);
			return item;
			}
		
		
		
		
		
		
		return null;
	}
	public static String getItem(ItemStack item){
		String itemname = "Error";
		if(item.getType() == Material.WOODEN_AXE) itemname = "Wooden Axe";
		if(item.getType() == Material.STONE_AXE) itemname = "Stone Axe";
		if(item.getType() == Material.GOLDEN_AXE) itemname = "Golden Axe";
		if(item.getType() == Material.IRON_AXE) itemname = "Iron Axe";
		if(item.getType() == Material.DIAMOND_AXE) itemname = "Diamond Axe";
		if(item.getType() == Material.NETHERITE_AXE) itemname = "Netherite Axe";
		if(item.getType() == Material.BOW) itemname = "Bow";
		if(item.getType() == Material.SHIELD) itemname = "Shield";
		
			
		return itemname;
	}
	//soul shop item item stack
		 public static ItemStack getSoulShop() {
		  ItemStack sword = new ItemStack(Material.EMERALD);
		  ItemMeta meta = sword.getItemMeta();
		  
		  meta.setDisplayName("§7§lSoul §r§aShop");
		  meta.addEnchant(Enchantment.DURABILITY, 1, true);
		  meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		  meta.setCustomModelData(10);
		  sword.setItemMeta(meta);
		  
		  return sword;

	}
		 //soul reforge confirm button
		 public static ItemStack getConfirmButton(int SoulCost) {
			 ItemStack item = new ItemStack(Material.GREEN_CONCRETE);
			 ItemMeta meta = item.getItemMeta();
			 List<String> lore = new ArrayList<String>();
			 meta.setDisplayName("§r§aConfirm!");
			 meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			 lore.add(ChatColor.WHITE + "Soul Cost: " + SoulCost);
			 lore.add("");
			 lore.add("§7Click to Reforge!");
			 meta.setLore(lore);
			 item.setItemMeta(meta);
			 return item;
		 }
		 //deny field item stack
		 public static ItemStack getDenyButton() {
			 ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE);
			 ItemMeta meta = item.getItemMeta();			
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName("§r" + ChatColor.RED + "Put an Item in the slots");
			    item.setItemMeta(meta);
			 return item;
		 }
		 //click item field
		 public static ItemStack getDenyfield() {
			 ItemStack item = new ItemStack(Material.BARRIER);
			 ItemMeta meta = item.getItemMeta();			
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName("§rKlick on an Sword/Axe/Armor to Reforge it");
			   
			    item.setItemMeta(meta);
			 return item;
		 }
		 //soul fragment item stack
		 public static ItemStack getSoulFragment() {
			 ItemStack item = new ItemStack(Material.GHAST_TEAR, 1);
				ItemMeta meta = item.getItemMeta();
				List<String> lore = new ArrayList<String>();
				meta.setDisplayName("§7§lSoul " + ChatColor.WHITE + "Fragment");
				lore.add(ChatColor.GRAY + " ");
				lore.add(ChatColor.GRAY + "Increases the Soul Dropp Chance by 1.");
				meta.setLore(lore);
				lore.clear();
				meta.setCustomModelData(10);
				meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				item.setItemMeta(meta);
				
				return item;
		 }
		 
		 //Soul reforge inventory

			public static void createSoulReforgeInventory(Player player) {
			 ConfigFile.reload();
				Inventory inv = Bukkit.createInventory(null, 36, ChatColor.DARK_GRAY + "Soul Reforging");
				ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
				ItemMeta meta = item.getItemMeta();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    item.setItemMeta(meta);
				inv.setItem(0, item);
				
				item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    item.setItemMeta(meta);
			    inv.setItem(1, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    item.setItemMeta(meta);
			    inv.setItem(2, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    item.setItemMeta(meta);
			    inv.setItem(3, item);
				
				item.setType(Material.PLAYER_HEAD);
				SkullMeta Smeta = (SkullMeta) item.getItemMeta();
				
				List<String> lore = new ArrayList<>();
				lore.add(" ");
				lore.add(ChatColor.GREEN + "Your Soul drop Chance: §r§l" + ConfigFile.get().get(player.getUniqueId().toString() + ".soulchance") + "%");
				Smeta.setLore(lore);
				lore.clear();
				Smeta.setOwner(player.getName().toString());
				Smeta.setDisplayName(ChatColor.GREEN + "Your Souls: "+ ChatColor.WHITE + "§l" +  ConfigFile.get().get(player.getUniqueId().toString() + ".souls"));
				Smeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				item.setItemMeta(Smeta);
				inv.setItem(4, item);
				
				item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(5, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(6, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(7, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(8, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(9, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(10, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(11, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(12, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(13, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(14, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(15, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(16, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(17, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(18, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(19, item);
				
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(20, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(21, item);
			    
			    item.setType(Material.RED_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName("§rKlick on an Sword/Axe/Armor to Reforge it");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(22, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(23, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(24, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(25, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(26, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(27, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(28, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(29, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(30, item);
			    
			    item.setType(Material.BARRIER);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName("§r" + ChatColor.RED + "Put an Item in the slots");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(31, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(32, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(33, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(34, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(35, item);
			    
			    
			    
			    
				
				SoulReforging = inv;
			}

			public static void createSoulShopInventory(Player player) {
			 ConfigFile.reload();
				Inventory inv = Bukkit.createInventory(null, 36, ChatColor.DARK_GRAY + "Soul Shop");
				ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
				ItemMeta meta = item.getItemMeta();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    item.setItemMeta(meta);
				inv.setItem(0, item);
				
				item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    item.setItemMeta(meta);
			    inv.setItem(1, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    item.setItemMeta(meta);
			    inv.setItem(2, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    item.setItemMeta(meta);
			    inv.setItem(3, item);
				
				item.setType(Material.PLAYER_HEAD);
				SkullMeta Smeta = (SkullMeta) item.getItemMeta();
				
				List<String> lore = new ArrayList<>();
				lore.add(" ");
				lore.add(ChatColor.GREEN + "Your Soul drop Chance: §r§l" + ConfigFile.get().get(player.getUniqueId().toString() + ".soulchance") + "%");
				Smeta.setLore(lore);
				lore.clear();
				Smeta.setOwner(player.getName().toString());
				Smeta.setDisplayName(ChatColor.GREEN + "Your Souls: "+ ChatColor.WHITE + "§l" +  ConfigFile.get().get(player.getUniqueId().toString() + ".souls"));
				Smeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				item.setItemMeta(Smeta);
				inv.setItem(4, item);
				
				item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(5, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(6, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(7, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(8, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(9, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(10, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(11, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(12, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(13, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(14, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(15, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(16, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(17, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(18, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(19, item);
				
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(20, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(21, item);
			    
			    item.setType(Material.RED_STAINED_GLASS);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName("§cNothing :/");
			    lore.add("");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(22, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(23, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(24, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(25, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(26, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(27, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(28, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(29, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(30, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(31, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(32, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(33, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(34, item);
			    
			    item.setType(Material.BLACK_STAINED_GLASS_PANE);
				meta = item.getItemMeta();
				lore.clear();
			    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			    meta.setDisplayName(" ");
			    meta.setLore(lore);
			    item.setItemMeta(meta);
			    inv.setItem(35, item);
			    
			    
			    
			    
				
				SoulBuyInv = inv;
			}
		 
		 
		       
		    }
			 
	
	



