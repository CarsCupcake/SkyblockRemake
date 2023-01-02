package me.CarsCupcake.SkyblockRemake.Skyblock;





import java.util.ArrayList;
import java.util.List;


import me.CarsCupcake.SkyblockRemake.Collections.CollectHandler;
import me.CarsCupcake.SkyblockRemake.Equipment.EquipmentInv;
import me.CarsCupcake.SkyblockRemake.Stats;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skills;
import me.CarsCupcake.SkyblockRemake.Tools;
import me.CarsCupcake.SkyblockRemake.AccessoryBag.AccessoryInventory;
import me.CarsCupcake.SkyblockRemake.Configs.AccessoryBag;
import me.CarsCupcake.SkyblockRemake.Configs.PetMenus;
import me.CarsCupcake.SkyblockRemake.Pets.Pet;
import me.CarsCupcake.SkyblockRemake.Pets.PetMenuInv;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;


public class OpenMenu implements CommandExecutor, Listener {
	public static Inventory skyblockmenu;
	public static Inventory skyblockskills;
	public static Inventory skyblockstats;


	
	@SuppressWarnings("deprecation")
	public static void createskyblockstats(Player player) {
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_GRAY + "Your SkyBlock Profile");
		ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(0, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(1, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(2, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(3, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(13, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(5, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(6, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(7, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(8, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(9, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(10, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(11, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();


		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(12, item);
		
		item.setType(Material.PLAYER_HEAD);
		SkullMeta Smeta = (SkullMeta) item.getItemMeta();
		
		List<String> lore = new ArrayList<>();
		lore.add(" §c§?§ Health §f" + Main.playerhealthcalc(player));
		lore.add(" §a§?§ Defense §f" + Main.playerdefcalc(player));
		lore.add(" §b✎ Intelligence §f" + Main.playermanacalc(player));
		lore.add(ChatColor.YELLOW + "Click to view your profile!");
		Smeta.setLore(lore);
		lore.clear();
		Smeta.setOwner(player.getName());
		Smeta.setDisplayName(ChatColor.GREEN + "Your SkyBlock Profile");
		Smeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(Smeta);
		inv.setItem(4, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(14, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(15, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(16, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(17, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(18, item);
		
		item.setType(Material.GOLDEN_APPLE);
		SkyblockPlayer p = SkyblockPlayer.getSkyblockPlayer(player);
		meta = item.getItemMeta();
		meta.setDisplayName("§c§?§ Health §f" + Main.playerhealthcalc(player));
		lore.add("§7Health is your total maximum");	
		lore.add("§7health. Your natural regenaration gives §a" + Tools.round((Main.playerhealthcalc(player) * 0.015), 1));
		lore.add("§7every §a2s.");
		lore.add(" ");
		lore.add("§7Base Health: §a" +p.basehealth  );
		lore.add(" §8§oIncrease your base Health by");
		lore.add(" §8§oleveling your Farming and");
		lore.add(" §8§oFishing Skills and contributing");
		lore.add(" §8§oto the §r§dFairy §8§oin the");
		lore.add(" §2Wilderness§8§o.");
		lore.add(" ");
		lore.add("§7Bonus Health: §8+ §e" + (Main.playerhealthcalc(player) - p.basehealth + "HP"));
		lore.add(" §8§oIncrease your bonus Health");
		lore.add(" §8§oby equiping items and armor,");
		lore.add(" §8§oand storing accesories in your");
		lore.add(" §8§oinventory.");
		meta.setLore(lore);
		lore.clear();
		item.setItemMeta(meta);
		inv.setItem(19, item);
		
		
		skyblockstats = inv;
	}
	
	@SuppressWarnings({ "deprecation"})
	public static void createInventory(Player player) {
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_GRAY + "SkyBlock Menu");
		ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(0, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(1, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(2, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(3, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(4, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(5, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(6, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(7, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(8, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(9, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(10, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(11, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();


		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(12, item);
		
		item.setType(Material.PLAYER_HEAD);
		SkullMeta Smeta = (SkullMeta) item.getItemMeta();
		
		List<String> lore = new ArrayList<>();
		lore.add("§7View your equipment, stats,");
		lore.add("§7and more!");
		lore.add(" ");
		/*lore.add(" §c❤ Health §f" + Main.playerhealthcalc(player));
		lore.add(" §a❈ Defense §f" + Main.playerdefcalc(player));
		lore.add(" §c❁ Strength §f" + Main.playerstrengthcalc(player));
		int speed = (int) Main.playerspeedcalc(player);
		if(speed > 500)
			speed = 500;
		lore.add(" §f✦ Speed §r§f" +speed);
		lore.add(" §9☣ Crit Chance §f" + Main.playercccalc(player));
		lore.add(" §9☠ Crit Damage §f" + Main.playercdcalc(player));
		lore.add(" §b✎ Intelligence §f" + Main.playermanacalc(player));
		lore.add(" §6⸕ Mining Speed §f" + Main.getPlayerMiningSpeed(player));
		lore.add(" §e⚔ Bonus Attack Speed §f" + Main.playerattackspeed(player) + "%");
		lore.add(" §3α Sea Creature Chance §f" + Main.playerseacreaturechance(player) + "%");
		lore.add(" §b✯ Magic Find §f" + Main.playermagicfindcalc(player));
		lore.add(" §f❂ True Defense " + Main.playertruedefense(player));
		lore.add(" §c⫽ Ferocity §f" + Tools.addDigits(Main.playerferocitycalc(player)));
		lore.add(" §c๑ Ability Damage §f" + Main.playerabilitydamagecalc(player) + "%");
		lore.add(" §6☘ Mining Fortune §f" + Main.getPlayerMiningFortune(player));
		lore.add(" §5✧ Pristine §f" + Main.playerpristine(player));*/
		for(Stats s : Stats.values())
			lore.add(" " + s.getColor() + s.getSymbol() + " " + s.getName() + " §f" + Main.getPlayerStat(SkyblockPlayer.getSkyblockPlayer(player), s));
		lore.add(" ");
		lore.add(ChatColor.YELLOW + "Click to view your profile!");
		Smeta.setLore(lore);
		lore.clear();
		Smeta.setOwner(player.getName());
		Smeta.setDisplayName(ChatColor.GREEN + "Your SkyBlock Profile");
		Smeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(Smeta);
		inv.setItem(13, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(14, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(15, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(16, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(17, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(18, item);
		
		item.setType(Material.DIAMOND_SWORD);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Your Skills");
		lore.add(ChatColor.GRAY + "View you Skill progression and");
		lore.add(ChatColor.GRAY + "rewards.");
		lore.add(" ");
		lore.add(ChatColor.GOLD+ "00,0 Skill Avg. " + ChatColor.DARK_GRAY + "(non-cosmetic)");
		lore.add(" ");
		lore.add(ChatColor.YELLOW + "Click to hide rankings!");
		meta.setLore(lore);
		lore.clear();
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(19, item);
		
		item.setType(Material.BOOK);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Recipe Book");
		lore.add(ChatColor.GRAY + "Though your adventure, you will");
		lore.add(ChatColor.GRAY + "unlock recipes for all kinds of");
		lore.add(ChatColor.GRAY + "special items! You can view how");
		lore.add(ChatColor.GRAY + "to craft these items here.");
		lore.add(" ");
		lore.add(ChatColor.GRAY + "Recipe Book Unlocked: " + ChatColor.YELLOW + "0" + ChatColor.GOLD + "%");
		lore.add(ChatColor.GRAY + "-------------------- " + ChatColor.YELLOW + "0" + ChatColor.GOLD + "/" + ChatColor.YELLOW + "719");
		lore.add(" ");
		lore.add(ChatColor.YELLOW + "Click to view!");
		meta.setLore(lore);
		lore.clear();
		item.setItemMeta(meta);
		inv.setItem(21, item);
		
		item.setType(Material.PAINTING);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Collection");
		lore.add(ChatColor.GRAY + "View all of the items available");
		lore.add(ChatColor.GRAY + "in SkyBlock. Collect more af an");
		lore.add(ChatColor.GRAY + "item to unlock rewards on your");
		lore.add(ChatColor.GRAY + "way to becoming a master of");
		lore.add(ChatColor.GRAY + "SkyBlock!");
		lore.add(" ");
		lore.add(ChatColor.GRAY + "Collection Maxed Out: " + ChatColor.YELLOW + "0" + ChatColor.GOLD + "%");
		lore.add(ChatColor.GRAY + "-------------------- " + ChatColor.YELLOW + "0" + ChatColor.GOLD + "/" + ChatColor.YELLOW + "60");
		lore.add(" ");
		lore.add(ChatColor.YELLOW + "Click to view!");
		meta.setLore(lore);
		lore.clear();
		item.setItemMeta(meta);
		inv.setItem(20, item);
		
		item.setType(Material.EMERALD);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Trades");
		lore.add(ChatColor.GRAY + "View your available trades.");
		lore.add(ChatColor.GRAY + "These trades are always");
		lore.add(ChatColor.GRAY + "available an accessible through");
		lore.add(ChatColor.GRAY + "the SkyBlock Menu.");
		lore.add(" ");
		lore.add(ChatColor.GRAY + "Trades Unlocked: " + ChatColor.YELLOW + "0" + ChatColor.GOLD + "%");
		lore.add(ChatColor.GRAY + "-------------------- " + ChatColor.YELLOW + "0" + ChatColor.GOLD + "/" + ChatColor.YELLOW + "60");
		lore.add(" ");
		lore.add(ChatColor.YELLOW + "Click to view!");
		meta.setLore(lore);
		lore.clear();
		item.setItemMeta(meta);
		inv.setItem(22, item);
		
		item.setType(Material.LEGACY_BOOK_AND_QUILL);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Quest Log");
		lore.add(ChatColor.GRAY + "View your active quests,");
		lore.add(ChatColor.GRAY + "progress, and rewards");
		lore.add(" ");
		lore.add(ChatColor.YELLOW + "Click to view!");
		meta.setLore(lore);
		lore.clear();
		item.setItemMeta(meta);
		inv.setItem(23, item);
		
		item.setType(Material.CLOCK);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Calendar and Events");
		lore.add(ChatColor.GRAY + "View the SkyBlock Calendar,");
		lore.add(ChatColor.GRAY + "upcoming events, and event");
		lore.add(ChatColor.GRAY + "rewards!");
		lore.add(" ");
		lore.add(ChatColor.GRAY + "Next Event:" + ChatColor.GOLD + " -");
		lore.add(ChatColor.GRAY + "Starting in:" + ChatColor.YELLOW + " -");
		lore.add(" ");
		lore.add(ChatColor.YELLOW + "Click to view!");
		meta.setLore(lore);
		lore.clear();
		item.setItemMeta(meta);
		inv.setItem(24, item);
		
		item.setType(Material.CHEST);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Storage");
		lore.add(ChatColor.GRAY + "Store global items that you");
		lore.add(ChatColor.GRAY + "want to access at any time");
		lore.add(ChatColor.GRAY + "from anywhere here.");
		lore.add(" ");
		lore.add(ChatColor.YELLOW + "Click to view!");
		meta.setLore(lore);
		lore.clear();
		item.setItemMeta(meta);
		inv.setItem(25, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(26, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(27, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(28, item);
		
		item.setType(Material.GLASS_BOTTLE);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Active Effects");
		lore.add(ChatColor.GRAY + "View and manage all of your");
		lore.add(ChatColor.GRAY + "active potion effects");
		lore.add(" ");
		lore.add(ChatColor.GRAY + "Drink Potions or splash them on the ground to buff yourself!");
		lore.add(" ");
		lore.add(ChatColor.GRAY + "Currently Active: " + ChatColor.YELLOW + "0");
		lore.add(" ");
		lore.add(ChatColor.YELLOW + "Click to view!");
		meta.setLore(lore);
		lore.clear();
		item.setItemMeta(meta);
		inv.setItem(29, item);
		
		item.setType(Material.BONE);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Pets");
		lore.add(ChatColor.GRAY + "View and manage all of your");
		lore.add(ChatColor.GRAY + "Pets.");
		lore.add(" ");
		lore.add(ChatColor.GRAY + "Level up your pets faster by");
		lore.add(ChatColor.GRAY + "gaining xp in ther favorite");
		lore.add(ChatColor.GRAY + "skill!");
		lore.add(" ");
		if(PetMenuInv.getPetName(player) != null)
			lore.add(ChatColor.GRAY + "Selected pet: " + Pet.pets.get(PetMenus.get().getString(player.getUniqueId() + "."  + PetMenus.get().getInt(player.getUniqueId() + ".equiped") + ".id" )).rarity.getPrefix() + PetMenuInv.getPetName(player));
			else
		lore.add(ChatColor.GRAY + "Selected pet: " + ChatColor.RED + "None");
		lore.add(" ");
		lore.add(ChatColor.YELLOW + "Click to view!");
		meta.setLore(lore);
		lore.clear();
		item.setItemMeta(meta);
		inv.setItem(30, item);
		
		item.setType(Material.CRAFTING_TABLE);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Crafting Table");
		lore.add(ChatColor.GRAY + "Opens the crafting grid");
		lore.add(" ");
		lore.add(ChatColor.YELLOW + "Click to view!");
		meta.setLore(lore);
		lore.clear();
		item.setItemMeta(meta);
		inv.setItem(31, item);
		
		ItemStack Litem = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
		LeatherArmorMeta Lmeta = (LeatherArmorMeta) Litem.getItemMeta();
		Lmeta.setColor(org.bukkit.Color.PURPLE);
		Lmeta.setDisplayName(ChatColor.GREEN + "Wardrobe");
		lore.add(ChatColor.GRAY + "Store armor sets and quickly");
		lore.add(ChatColor.GRAY + "swap between them!");
		lore.add(" ");
		lore.add(ChatColor.YELLOW + "Click to view!");
		Lmeta.setLore(lore);
		lore.clear();
		Lmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		Litem.setItemMeta(Lmeta);
		inv.setItem(32, Litem);
		
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(33, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(34, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(35, item);
		
		item.setType(Material.PLAYER_HEAD);
		
		lore.add(ChatColor.GRAY + "View your Hypixel quests,");
		lore.add(ChatColor.GRAY + "achievments, active Network");
		lore.add(ChatColor.GRAY + "Boosters, and more!");
		lore.add(ChatColor.GRAY + " ");
		lore.add(ChatColor.YELLOW + "Click to vierw!");
		Smeta.setLore(lore);
		lore.clear();
		Smeta.setOwner(player.getName());
		Smeta.setDisplayName(ChatColor.GREEN + "My Hypixel Profile");
		Smeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(Smeta);
		inv.setItem(36, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(37, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(38, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(39, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(40, item);
		
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(41, item);
		
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(42, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(43, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(44, item);
		
		item.setType(Material.COMPASS);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Hypixel Game Menu");
		lore.add(ChatColor.GRAY + "View the Hypixel Game Menu.");
		lore.add(" ");
		lore.add(ChatColor.YELLOW + "Click to view!");
		meta.setLore(lore);
		lore.clear();
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(45, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(46, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(51, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(52, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(53, item);
		
		item.setType(Material.PLAYER_HEAD);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.AQUA + "Fast Travel");
		lore.add(ChatColor.GRAY + "Teleport to you've");
		lore.add(ChatColor.GRAY + "already visited.");
		lore.add(" ");
		lore.add(ChatColor.AQUA + "Right-click for hub warp!");
		lore.add(ChatColor.RED + "You haven't unlocked this yet!");
		meta.setLore(lore);
		lore.clear();
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(47, item);
		
		item.setType(Material.NAME_TAG);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Profile Managemant");
		lore.add(ChatColor.GRAY + "You can have multiple");
		lore.add(ChatColor.GRAY + "Skyblock profiles at the");
		lore.add(ChatColor.GRAY + "same time.");
		lore.add(ChatColor.GRAY + " ");
		lore.add(ChatColor.GRAY + "Each Profile has its own");
		lore.add(ChatColor.GRAY + "island, inventory, quest");
		lore.add(ChatColor.GRAY + "log...");
		lore.add(ChatColor.GRAY + " ");
		lore.add(ChatColor.GRAY + "Profiles: " + ChatColor.YELLOW + "-" + ChatColor.GOLD + "/" + ChatColor.YELLOW + "-");
		lore.add(ChatColor.GRAY + "Playing on: " + ChatColor.GREEN + "-");
		lore.add(ChatColor.GRAY + " ");
		lore.add(ChatColor.AQUA + "Play with friends useing /coop");
		lore.add(ChatColor.GRAY + " ");
		lore.add(ChatColor.YELLOW + "Click to manage!");
		meta.setLore(lore);
		lore.clear();
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(48, item);
		
		item.setType(Material.COOKIE);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + "Booster Cookie");
		lore.add(ChatColor.GRAY + "Obtain the" + ChatColor.LIGHT_PURPLE + " Cookie Buff");
		lore.add(ChatColor.GRAY + "from booster cookies in the");
		lore.add(ChatColor.GRAY + "hub's community shop.");
		lore.add(ChatColor.GRAY + " ");
		lore.add(ChatColor.GRAY + "Status: " + ChatColor.RED + "Not active!");
		lore.add(ChatColor.GRAY + "Bits Available: " + ChatColor.AQUA + "0");
		lore.add(ChatColor.GRAY + " ");
		lore.add(ChatColor.YELLOW + "Click to get all info!");
		meta.setLore(lore);
		lore.clear();
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(49, item);
		
		item.setType(Material.REDSTONE_TORCH);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Settings");
		lore.add(ChatColor.GRAY + "View and edit your SkyBlock");
		lore.add(ChatColor.GRAY + "settings.");
		lore.add(ChatColor.GRAY + " ");
		lore.add(ChatColor.YELLOW + "Click to view!");
		meta.setLore(lore);
		lore.clear();
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(50, item);
		
		
		
		
		
		if(AccessoryBag.get().getInt(player.getUniqueId() + ".slots") != 0) {
		item = Tools.CustomHeadTexture("http://textures.minecraft.net/texture/961a918c0c49ba8d053e522cb91abc74689367b4d8aa06bfc1ba9154730985ff");
		meta = item.getItemMeta();
		meta.setDisplayName("§aAccessory Bag");
		lore.clear();
		lore.add("§eMagical Power§7: §e" + SkyblockPlayer.getSkyblockPlayer(player).getMagicalpower());
		meta.setLore(lore);
		item.setItemMeta(meta);
			inv.setItem(53, item);
			}
		
		
		
		
		


		
		
		
		
		skyblockmenu = inv;
		
	}
	
	@SuppressWarnings({ "deprecation"})
	public static void createInventorySkills(SkyblockPlayer player) {
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_GRAY + "Yor Skills");
		ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(0, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(1, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(2, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(3, item);
		
		item.setType(Material.DIAMOND_SWORD);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Your Skills");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY + "View you Skill progression and");
		lore.add(ChatColor.GRAY + "rewards.");
		lore.add(" ");
		lore.add(ChatColor.GOLD+ "00,0 Skill Avg. " + ChatColor.DARK_GRAY + "(non-cosmetic)");
		lore.add(" ");
		lore.add(ChatColor.YELLOW + "Click to show rankings!");
		meta.setLore(lore);
		lore.clear();
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(4, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(5, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(6, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(7, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(8, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(9, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(10, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(11, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(12, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(13, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(14, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(15, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(16, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(17, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(18, item);
		
		item.setType(Material.GOLDEN_HOE);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Farming -");
		lore.add(ChatColor.GRAY + "Harvest crops and shear sheep to");
		lore.add(ChatColor.GRAY + "earn Farming XP!");
		lore.add(" ");
		lore.add(ChatColor.GRAY+ "Progress to Level --:" + ChatColor.YELLOW + " 00.0%");
		lore.add(ChatColor.WHITE + "-------------------- " + ChatColor.YELLOW + "0" + ChatColor.GOLD + "/" + ChatColor.YELLOW + "0");
		lore.add(" ");
		lore.add(ChatColor.GRAY + "Level - Rewards:");
		lore.add(ChatColor.YELLOW + "-");
		lore.add(" ");
		lore.add(ChatColor.YELLOW + "Click to view!");
		meta.setLore(lore);
		lore.clear();
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(19, item);
		
		item.setType(Material.STONE_PICKAXE);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Mining " + player.getSkill(Skills.Mining).getLevel());
		lore.add(ChatColor.GRAY + "Spelunk islands for ores and");
		lore.add(ChatColor.GRAY + "valuble materiols to earn");
		lore.add(ChatColor.GRAY + "Mining XP!");
		lore.add(" ");
		lore.add(ChatColor.GRAY + "Progress to Level "+(player.getSkill(Skills.Mining).getLevel() + 1)+": " + ChatColor.YELLOW + Tools.round((player.getSkill(Skills.Mining).getXp() / (double)Skill.getNextLevelXp(player.getSkill(Skills.Mining).getLevel()))*100, 2) + ChatColor.GOLD + "%");
		lore.add(ChatColor.WHITE + "-------------------- " + ChatColor.YELLOW + Tools.round(player.getSkill(Skills.Mining).getXp(), 1) + ChatColor.GOLD + "/" + ChatColor.YELLOW + Skill.getNextLevelXp(player.getSkill(Skills.Mining).getLevel()));
		lore.add(" ");
		lore.add(ChatColor.GRAY + "Level - Rewards:");
		lore.add(" ");
		lore.add("§e-");
		lore.add(" ");
		lore.add(ChatColor.YELLOW + "Click to view!");
		meta.setLore(lore);
		lore.clear();
		item.setItemMeta(meta);
		inv.setItem(20, item);
		
		item.setType(Material.STONE_SWORD);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Combat -");
		lore.add(ChatColor.GRAY + "Fight mobs and players to earn");
		lore.add(ChatColor.GRAY + "Cambat XP!");
		lore.add(" ");
		lore.add(ChatColor.GRAY + "Progress to Level -: " + ChatColor.YELLOW + "0" + ChatColor.GOLD + "%");
		lore.add(ChatColor.WHITE + "-------------------- " + ChatColor.YELLOW + "0" + ChatColor.GOLD + "/" + ChatColor.YELLOW + "0");
		lore.add(" ");
		lore.add(ChatColor.GRAY + "Level - Rewards:");
		lore.add("§e-");
		lore.add(" ");
		lore.add(ChatColor.YELLOW + "Click to view!");
		meta.setLore(lore);
		lore.clear();
		item.setItemMeta(meta);
		inv.setItem(21, item);
		
		item.setType(Material.EMERALD);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Trades");
		lore.add(ChatColor.GRAY + "View your available trades.");
		lore.add(ChatColor.GRAY + "These trades are always");
		lore.add(ChatColor.GRAY + "available an accessible through");
		lore.add(ChatColor.GRAY + "the SkyBlock Menu.");
		lore.add(" ");
		lore.add(ChatColor.GRAY + "Trades Unlocked: " + ChatColor.YELLOW + "0" + ChatColor.GOLD + "%");
		lore.add(ChatColor.GRAY + "-------------------- " + ChatColor.YELLOW + "0" + ChatColor.GOLD + "/" + ChatColor.YELLOW + "60");
		lore.add(" ");
		lore.add(ChatColor.YELLOW + "Click to view!");
		meta.setLore(lore);
		lore.clear();
		item.setItemMeta(meta);
		inv.setItem(22, item);
		
		item.setType(Material.LEGACY_BOOK_AND_QUILL);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Quest Log");
		lore.add(ChatColor.GRAY + "View your active quests,");
		lore.add(ChatColor.GRAY + "progress, and rewards");
		lore.add(" ");
		lore.add(ChatColor.YELLOW + "Click to view!");
		meta.setLore(lore);
		lore.clear();
		item.setItemMeta(meta);
		inv.setItem(23, item);
		
		item.setType(Material.CLOCK);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Calendar and Events");
		lore.add(ChatColor.GRAY + "View the SkyBlock Calendar,");
		lore.add(ChatColor.GRAY + "upcoming events, and event");
		lore.add(ChatColor.GRAY + "rewards!");
		lore.add(" ");
		lore.add(ChatColor.GRAY + "Next Event:" + ChatColor.GOLD + " -");
		lore.add(ChatColor.GRAY + "Starting in:" + ChatColor.YELLOW + " -");
		lore.add(" ");
		lore.add(ChatColor.YELLOW + "Click to view!");
		meta.setLore(lore);
		lore.clear();
		item.setItemMeta(meta);
		inv.setItem(24, item);
		
		item.setType(Material.CHEST);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Storage");
		lore.add(ChatColor.GRAY + "Store global items that you");
		lore.add(ChatColor.GRAY + "want to access at any time");
		lore.add(ChatColor.GRAY + "from anywhere here.");
		lore.add(" ");
		lore.add(ChatColor.YELLOW + "Click to view!");
		meta.setLore(lore);
		lore.clear();
		item.setItemMeta(meta);
		inv.setItem(25, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(26, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(27, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(28, item);
		
		item.setType(Material.GLASS_BOTTLE);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Active Effects");
		lore.add(ChatColor.GRAY + "View and manage all of your");
		lore.add(ChatColor.GRAY + "active potion effects");
		lore.add(" ");
		lore.add(ChatColor.GRAY + "Drink Potions or splash them on the ground to buff yourself!");
		lore.add(" ");
		lore.add(ChatColor.GRAY + "Currently Active: " + ChatColor.YELLOW + "0");
		lore.add(" ");
		lore.add(ChatColor.YELLOW + "Click to view!");
		meta.setLore(lore);
		lore.clear();
		item.setItemMeta(meta);
		inv.setItem(29, item);
		
		item.setType(Material.BONE);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Pets");
		lore.add(ChatColor.GRAY + "View and manage all of your");
		lore.add(ChatColor.GRAY + "Pets.");
		lore.add(" ");
		lore.add(ChatColor.GRAY + "Level up your pets faster by");
		lore.add(ChatColor.GRAY + "gaining xp in ther favorite");
		lore.add(ChatColor.GRAY + "skill!");
		lore.add(" ");
		lore.add(ChatColor.GRAY + "Selected pet: " + ChatColor.RED + "None");
		lore.add(" ");
		lore.add(ChatColor.YELLOW + "Click to view!");
		meta.setLore(lore);
		lore.clear();
		item.setItemMeta(meta);
		inv.setItem(30, item);
		
		item.setType(Material.CRAFTING_TABLE);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Crafting Table");
		lore.add(ChatColor.GRAY + "Opens the crafting grid");
		lore.add(" ");
		lore.add(ChatColor.YELLOW + "Click to view!");
		meta.setLore(lore);
		lore.clear();
		item.setItemMeta(meta);
		inv.setItem(31, item);
		
		ItemStack Litem = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
		LeatherArmorMeta Lmeta = (LeatherArmorMeta) Litem.getItemMeta();
		Lmeta.setColor(org.bukkit.Color.PURPLE);
		Lmeta.setDisplayName(ChatColor.GREEN + "Wardrobe");
		lore.add(ChatColor.GRAY + "Store armor sets and quickly");
		lore.add(ChatColor.GRAY + "swap between them!");
		lore.add(" ");
		lore.add(ChatColor.YELLOW + "Click to view!");
		Lmeta.setLore(lore);
		lore.clear();
		Lmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		Litem.setItemMeta(Lmeta);
		inv.setItem(32, Litem);
		
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(33, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(34, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(35, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(36, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(37, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(38, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(39, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(40, item);
		
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(41, item);
		
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(42, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(43, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(44, item);
		
		item.setType(Material.COMPASS);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Hypixel Game Menu");
		lore.add(ChatColor.GRAY + "View the Hypixel Game Menu.");
		lore.add(" ");
		lore.add(ChatColor.YELLOW + "Click to view!");
		meta.setLore(lore);
		lore.clear();
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(45, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(46, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(51, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(52, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(53, item);
		
		item.setType(Material.PLAYER_HEAD);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.AQUA + "Fast Travel");
		lore.add(ChatColor.GRAY + "Teleport to you've");
		lore.add(ChatColor.GRAY + "already visited.");
		lore.add(" ");
		lore.add(ChatColor.AQUA + "Right-click for hub warp!");
		lore.add(ChatColor.RED + "You haven't unlocked this yet!");
		meta.setLore(lore);
		lore.clear();
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(47, item);
		
		item.setType(Material.NAME_TAG);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Profile Managemant");
		lore.add(ChatColor.GRAY + "You can have multiple");
		lore.add(ChatColor.GRAY + "Skyblock profiles at the");
		lore.add(ChatColor.GRAY + "same time.");
		lore.add(ChatColor.GRAY + " ");
		lore.add(ChatColor.GRAY + "Each Profile has its own");
		lore.add(ChatColor.GRAY + "island, inventory, quest");
		lore.add(ChatColor.GRAY + "log...");
		lore.add(ChatColor.GRAY + " ");
		lore.add(ChatColor.GRAY + "Profiles: " + ChatColor.YELLOW + "-" + ChatColor.GOLD + "/" + ChatColor.YELLOW + "-");
		lore.add(ChatColor.GRAY + "Playing on: " + ChatColor.GREEN + "-");
		lore.add(ChatColor.GRAY + " ");
		lore.add(ChatColor.AQUA + "Play with friends useing /coop");
		lore.add(ChatColor.GRAY + " ");
		lore.add(ChatColor.YELLOW + "Click to manage!");
		meta.setLore(lore);
		lore.clear();
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(48, item);
		
		item.setType(Material.COOKIE);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + "Booster Cookie");
		lore.add(ChatColor.GRAY + "Obtain the" + ChatColor.LIGHT_PURPLE + " Cookie Buff");
		lore.add(ChatColor.GRAY + "from booster cookies in the");
		lore.add(ChatColor.GRAY + "hub's community shop.");
		lore.add(ChatColor.GRAY + " ");
		lore.add(ChatColor.GRAY + "Status: " + ChatColor.RED + "Not active!");
		lore.add(ChatColor.GRAY + "Bits Available: " + ChatColor.AQUA + "0");
		lore.add(ChatColor.GRAY + " ");
		lore.add(ChatColor.YELLOW + "Click to get all info!");
		meta.setLore(lore);
		lore.clear();
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(49, item);
		
		item.setType(Material.REDSTONE_TORCH);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Settings");
		lore.add(ChatColor.GRAY + "View and edit your SkyBlock");
		lore.add(ChatColor.GRAY + "settings.");
		lore.add(ChatColor.GRAY + " ");
		lore.add(ChatColor.YELLOW + "Click to view!");
		meta.setLore(lore);
		lore.clear();
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(50, item);
		
		skyblockskills = inv;
	}
	
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("e")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Du kannst das net");
				return true;
			}
			Player player = (Player) sender;
		
			if (player.hasPermission("skyblock.beta")) {
				createInventory(player);
				player.openInventory(skyblockmenu);
			return true;
			}
			player.sendMessage(ChatColor.RED + "Du hast ein Beta feture gefunden! Da du keine Person bist die dies benutzen darf wird dir der Zutrit verweigert :(");
			return true;
			
			
		}
		return false;
	}
	
/*	public static ItemStack applySkullTexture() {
		net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(new ItemStack(Material.PLAYER_HEAD, 1));
	    NBTTagCompound tag;
	    if (item.hasTag()) {
	        tag = item.getTag();
	    } else {
	        tag = new NBTTagCompound();
	    }
	    NBTTagCompound skullOwner = new NBTTagCompound();
	    NBTTagCompound properties = new NBTTagCompound();
	    NBTTagList textures = new NBTTagList();
	    NBTTagCompound texture = new NBTTagCompound();

	    texture.setString("Value", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTM2ZTk0ZjZjMzRhMzU0NjVmY2U0YTkwZjJlMjU5NzYzODllYjk3MDlhMTIyNzM1NzRmZjcwZmQ0ZGFhNjg1MiJ9fX0=");
	    textures.add(texture);
	    properties.set("textures", textures);
	    skullOwner.set("Properties", properties);
	    tag.set("SkullOwner", skullOwner);
	    


	    item.setTag(tag);
	    return CraftItemStack.asBukkitCopy(item);
	}*/
	
	@EventHandler
	  public void onClickNPC(InventoryClickEvent event) {
		  if (!event.getView().getTitle().contains("SkyBlock Menu"))
			  return;
		  if (event.getCurrentItem() == null)
			  return;
		  if (event.getCurrentItem().getItemMeta() == null)
			  return;
		  
		  Player player = (Player) event.getWhoClicked();
		  event.setCancelled(true);
		  
		  if(event.getClickedInventory().getType() == InventoryType.PLAYER)
			  return;
		  
		  if (event.getSlot() == 13) {

			  player.updateInventory();
			  EquipmentInv inv = new EquipmentInv(SkyblockPlayer.getSkyblockPlayer(player));
			  inv.openInventory();
			  return;
			  
		  
		  }
		  
		  if (event.getSlot() == 19) {

			  createInventorySkills(SkyblockPlayer.getSkyblockPlayer(player));
			  player.updateInventory();
			  player.openInventory(skyblockskills);
			  return;
			  
		  
		  }
		  if (event.getSlot() == 20){

			  CollectHandler.openAllCollections(SkyblockPlayer.getSkyblockPlayer(player));
			  return;
		  }

		  if (event.getSlot() == 30) {

			  player.updateInventory();
			  player.openInventory(PetMenuInv.createMenu(player));
			  return;
			  
		  
		  }
		  if (event.getSlot() == 53 && AccessoryBag.get().getInt(player.getUniqueId() + ".slots") != 0) {

			  
			  player.updateInventory();
			  AccessoryInventory inv =new AccessoryInventory(player);
			  player.openInventory(inv.invs.get(0));
			  return;
			  
		  
		  }
		  
			 
	}


}