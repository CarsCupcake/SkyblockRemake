package me.CarsCupcake.SkyblockRemake.cmd;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.CarsCupcake.SkyblockRemake.SkyblockRemakeEvents;
import me.CarsCupcake.SkyblockRemake.ItemsSearch;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class itemCMD implements CommandExecutor{
	public static Inventory Items;
	public static Inventory Items2;
	public static ArrayList<Inventory> items = new ArrayList<>();
	public static ArrayList<ItemManager> customItems = new ArrayList<>();
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("item")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Du kannst das net");
				return true;
			}
			Player player = (Player) sender;
			if(args.length == 1)
				ItemsSearch.buildInventory(SkyblockPlayer.getSkyblockPlayer(player), args[0]);
			else
			
			player.openInventory(items.get(0));
			player.updateInventory();
			return true;
			
		}
		
		return false;
	}
	
	public static void createItemInventory() {
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_GRAY + "Custom Items Menu - Page 1");
		ItemStack item = new ItemStack(Material.EMERALD);
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<>();

		meta.setDisplayName("§r§7§lSoul §r§aShop");
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		meta.setCustomModelData(10);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		inv.setItem(0, item);
		
		item.setType(Material.GHAST_TEAR);
		meta = item.getItemMeta();
		meta.setDisplayName("§7§lSoul " + ChatColor.WHITE + "Fragment");
		lore.add(ChatColor.GRAY + " ");
		lore.add(ChatColor.GRAY + "Increases the Soul Dropp Chance by 1.");
		meta.setLore(lore);
		lore.clear();
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(1, item);
		
		inv.setItem(2, SkyblockRemakeEvents.getHyperion());


		inv.setItem(6, me.CarsCupcake.SkyblockRemake.Items.Items.wither_googles());
		inv.setItem(7, me.CarsCupcake.SkyblockRemake.Items.Items.storm_chestplate());
		inv.setItem(8, me.CarsCupcake.SkyblockRemake.Items.Items.storm_leggings());
		inv.setItem(9, me.CarsCupcake.SkyblockRemake.Items.Items.storm_boots());
		inv.setItem(10, me.CarsCupcake.SkyblockRemake.Items.Items.live_steal_book());
		inv.setItem(11, me.CarsCupcake.SkyblockRemake.Items.Items.juju_shortbow());
		inv.setItem(12, me.CarsCupcake.SkyblockRemake.Items.Items.FakeArrow("§rFake Arrow"));
		inv.setItem(13, me.CarsCupcake.SkyblockRemake.Items.Items.SummoningEye());
		inv.setItem(14, me.CarsCupcake.SkyblockRemake.Items.Items.terminator());
		inv.setItem(15, me.CarsCupcake.SkyblockRemake.Items.Items.Hyperion());
		inv.setItem(16, me.CarsCupcake.SkyblockRemake.Items.Items.TestItem());
		inv.setItem(17, me.CarsCupcake.SkyblockRemake.Items.Items.protection_book());
		inv.setItem(18, me.CarsCupcake.SkyblockRemake.Items.Items.Bonemerang());
		inv.setItem(19, me.CarsCupcake.SkyblockRemake.Items.Items.HotPotatoBook());
		inv.setItem(20, me.CarsCupcake.SkyblockRemake.Items.Items.Recombobulator3000());
		inv.setItem(21, me.CarsCupcake.SkyblockRemake.Items.Items.Divans_Drill());
		inv.setItem(22, me.CarsCupcake.SkyblockRemake.Items.Items.Gemstone_Gauntlet());
		inv.setItem(23, me.CarsCupcake.SkyblockRemake.Items.Items.SwordOfTheUniverse());
		inv.setItem(24, me.CarsCupcake.SkyblockRemake.Items.Items.CannonTestItem());
		
		inv.setItem(25, me.CarsCupcake.SkyblockRemake.Items.Items.RoughRuby());
		inv.setItem(26, me.CarsCupcake.SkyblockRemake.Items.Items.FlawedRuby());
		inv.setItem(27, me.CarsCupcake.SkyblockRemake.Items.Items.FineRuby());
		inv.setItem(28, me.CarsCupcake.SkyblockRemake.Items.Items.FlawlessRuby());
		inv.setItem(29, me.CarsCupcake.SkyblockRemake.Items.Items.PerfectRuby());
		
		inv.setItem(30, me.CarsCupcake.SkyblockRemake.Items.Items.RoughAmber());
		inv.setItem(31, me.CarsCupcake.SkyblockRemake.Items.Items.FlawedAmber());
		inv.setItem(32, me.CarsCupcake.SkyblockRemake.Items.Items.FineAmber());
		inv.setItem(33, me.CarsCupcake.SkyblockRemake.Items.Items.FlawlessAmber());
		inv.setItem(34, me.CarsCupcake.SkyblockRemake.Items.Items.PerfectAmber());
		
		inv.setItem(35, me.CarsCupcake.SkyblockRemake.Items.Items.RoughJade());
		inv.setItem(36, me.CarsCupcake.SkyblockRemake.Items.Items.FlawedJade());
		inv.setItem(37, me.CarsCupcake.SkyblockRemake.Items.Items.FineJade());
		inv.setItem(38, me.CarsCupcake.SkyblockRemake.Items.Items.FlawlessJade());
		inv.setItem(39, me.CarsCupcake.SkyblockRemake.Items.Items.PerfectJade());
		
		inv.setItem(40, me.CarsCupcake.SkyblockRemake.Items.Items.RoughSapphire());
		inv.setItem(41, me.CarsCupcake.SkyblockRemake.Items.Items.FlawedSapphire());
		inv.setItem(42, me.CarsCupcake.SkyblockRemake.Items.Items.FineSapphire());
		inv.setItem(43, me.CarsCupcake.SkyblockRemake.Items.Items.FlawlessSapphire());
		inv.setItem(44, me.CarsCupcake.SkyblockRemake.Items.Items.PerfectSapphire());
		
		 item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		 meta = item.getItemMeta();
		 meta.setDisplayName(" ");
		 meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		for(int i = 45; i < 53; i++) {
			inv.setItem(i, item);
		}
		item.setType(Material.ARROW);
		meta.setDisplayName("§aNext Page");
		 meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(53, item);
		
		Items = inv;
	}
	
	public static void createItem2Inventory() {
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_GRAY + "Custom Items Menu - Page 2");
		ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		 ItemMeta meta = item.getItemMeta();
		 meta.setDisplayName(" ");
		 meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		for(int i = 46; i < 54; i++) {
			inv.setItem(i, item);
		}
		item.setType(Material.ARROW);
		meta.setDisplayName("§aNext Page");
		 meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(45, item);
		
		
		inv.setItem(0, me.CarsCupcake.SkyblockRemake.Items.Items.RoughAmethyst());
		inv.setItem(1, me.CarsCupcake.SkyblockRemake.Items.Items.FlawedAmethyst());
		inv.setItem(2, me.CarsCupcake.SkyblockRemake.Items.Items.FineAmethyst());
		inv.setItem(3, me.CarsCupcake.SkyblockRemake.Items.Items.FlawlessAmethyst());
		inv.setItem(4, me.CarsCupcake.SkyblockRemake.Items.Items.PerfectAmethyst());
		
		inv.setItem(5, me.CarsCupcake.SkyblockRemake.Items.Items.RoughJasper());
		inv.setItem(6, me.CarsCupcake.SkyblockRemake.Items.Items.FlawedJasper());
		inv.setItem(7, me.CarsCupcake.SkyblockRemake.Items.Items.FineJasper());
		inv.setItem(8, me.CarsCupcake.SkyblockRemake.Items.Items.FlawlessJasper());
		inv.setItem(9, me.CarsCupcake.SkyblockRemake.Items.Items.PerfectJasper());
		
		inv.setItem(10, me.CarsCupcake.SkyblockRemake.Items.Items.RoughTopaz());
		inv.setItem(11, me.CarsCupcake.SkyblockRemake.Items.Items.FlawedTopaz());
		inv.setItem(12, me.CarsCupcake.SkyblockRemake.Items.Items.FineTopaz());
		inv.setItem(13, me.CarsCupcake.SkyblockRemake.Items.Items.FlawlessTopaz());
		inv.setItem(14, me.CarsCupcake.SkyblockRemake.Items.Items.PerfectTopaz());
		
		inv.setItem(15, me.CarsCupcake.SkyblockRemake.Items.Items.SorrowHelmet());
		inv.setItem(16, me.CarsCupcake.SkyblockRemake.Items.Items.SorrowChestplate());
		inv.setItem(17, me.CarsCupcake.SkyblockRemake.Items.Items.SorrowLeggings());
		inv.setItem(18, me.CarsCupcake.SkyblockRemake.Items.Items.SorrowBoots());
		inv.setItem(19, me.CarsCupcake.SkyblockRemake.Items.Items.ManaFluxPowerOrb());
		inv.setItem(20, me.CarsCupcake.SkyblockRemake.Items.Items.Volta());
		inv.setItem(21, me.CarsCupcake.SkyblockRemake.Items.Items.MithrilInfusedFuelTank());
		inv.setItem(22, me.CarsCupcake.SkyblockRemake.Items.Items.TitaniumInfusedFuelTank());
		inv.setItem(23, me.CarsCupcake.SkyblockRemake.Items.Items.GemstoneFuelTank());
		inv.setItem(24, me.CarsCupcake.SkyblockRemake.Items.Items.PerfectlyCutFuelTank());
		inv.setItem(25, me.CarsCupcake.SkyblockRemake.Items.Items.MithrilPlatedDrillEngine());
		inv.setItem(26, me.CarsCupcake.SkyblockRemake.Items.Items.TitaniumPlatedDrillEngine());
		inv.setItem(27, me.CarsCupcake.SkyblockRemake.Items.Items.RubyPolishedDrillEngine());
		inv.setItem(28, me.CarsCupcake.SkyblockRemake.Items.Items.SapphirePolishedDrillEngine());
		inv.setItem(29, me.CarsCupcake.SkyblockRemake.Items.Items.AmberPolishedDrillEngine());
		inv.setItem(30, me.CarsCupcake.SkyblockRemake.Items.Items.JerryChineGun());
		inv.setItem(31, me.CarsCupcake.SkyblockRemake.Items.Items.EnderDragonLegendary());
		inv.setItem(32, me.CarsCupcake.SkyblockRemake.Items.Items.CarsCupcakeSpetial());
		
		inv.setItem(33, me.CarsCupcake.SkyblockRemake.Items.Items.GemstoneGolemCommon());
		inv.setItem(34, me.CarsCupcake.SkyblockRemake.Items.Items.GemstoneGolemUncommon());
		inv.setItem(35, me.CarsCupcake.SkyblockRemake.Items.Items.GemstoneGolemRare());
		inv.setItem(36, me.CarsCupcake.SkyblockRemake.Items.Items.GemstoneGolemEpic());
		inv.setItem(37, me.CarsCupcake.SkyblockRemake.Items.Items.GemstoneGolemLegendary());
		
		
	Items2 = inv;	
	}
	
	public static void createItemInvs() {
		
		
		ArrayList<ItemManager> managers =   new ArrayList<>() ;
		ArrayList<String> strings =   new ArrayList<>() ;
		
		for(String str: me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.keySet()) {
			if(!me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.get(str).isBaseItem) {
				strings.add(str);
				
			}
		}
		Collections.sort(strings);
		for(int i = 0; i < strings.size(); i++) {
			managers.add(me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.get(strings.get(i)));
			customItems.add(me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.get(strings.get(i)));
		}
		int invs =1+  (managers.size() / 45);
	
	
		int list = 0;
		int invCount = 1;
		for(invCount = 1; invCount <= invs; invCount++) {
			Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_GRAY + "Custom Items Menu - Page " + invCount);
			ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
			ItemMeta meta = item.getItemMeta();
			PersistentDataContainer data = meta.getPersistentDataContainer();
			 meta.setDisplayName(" ");
			 meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			item.setItemMeta(meta);
			for(int i = 45; i < 53; i++) {
				inv.setItem(i, item);
			}
			if(invCount != invs) {
			item.setType(Material.ARROW);
			
			data.set(new NamespacedKey(Main.getMain(), "page"), PersistentDataType.INTEGER, invCount);
			meta.setDisplayName("§aNext Page");
			 meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			item.setItemMeta(meta);
			inv.setItem(53, item);
			}else {
				data.set(new NamespacedKey(Main.getMain(), "page"), PersistentDataType.INTEGER, invCount);
				item.setItemMeta(meta);
				inv.setItem(53, item);
			}
			if(invCount != 1) {
				item.setType(Material.ARROW);
			meta.setDisplayName("§aLast Page");
			 meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			item.setItemMeta(meta);
			inv.setItem(45, item);
			}
			item.setType(Material.OAK_SIGN);
			meta.setDisplayName("§aSearch");
			 meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			item.setItemMeta(meta);
			inv.setItem(49, item);
			int i = 0;
			
			while(i < 45){


				if(!(list >= managers.size())) {
					if(!managers.get(list).hasEdition)
					inv.setItem(i, Main.item_updater(managers.get(list).getRawItemStack(),null) );
					else
						i -= 1;
					}
					
				
			
				
			i += 1;
				list++;
				
			}
			
			items.add(inv);
			
		}
	}
	
	
	
	

}
