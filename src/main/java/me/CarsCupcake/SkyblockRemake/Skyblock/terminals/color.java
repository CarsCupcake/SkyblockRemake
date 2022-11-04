package me.CarsCupcake.SkyblockRemake.Skyblock.terminals;



import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class color implements Listener {
	public static Inventory colorterminal;
	public static int l = 0;
	
	public static void createColorTerminal(){
		l = 0;
		Inventory inv = Bukkit.createInventory(null, 54, "Select all the WHITE items!");
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

		item.setType(Material.WHITE_CONCRETE);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "White Concrete");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(10, item);
		
		item.setType(Material.GREEN_CONCRETE);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Green Concrete");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(11, item);
		
		item.setType(Material.GREEN_DYE);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Green Dye");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(12, item);
		
		item.setType(Material.WHITE_STAINED_GLASS);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "White Stained Glass");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(13, item);
		
		item.setType(Material.GREEN_CONCRETE);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Green Concrete");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(14, item);
		
		item.setType(Material.LIGHT_BLUE_DYE);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Light Blue Dye");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(15, item);
		
		item.setType(Material.WHITE_STAINED_GLASS);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "White Stained Glass");
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
		
		item.setType(Material.GRAY_CONCRETE);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Gray Concrete");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(19, item);
		
		item.setType(Material.WHITE_DYE);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "White Dye");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(20, item);
		
		item.setType(Material.PURPLE_TERRACOTTA);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Purple Terracotta");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(21, item);
		
		item.setType(Material.LIGHT_BLUE_CONCRETE);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Light Blue Concrete");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(22, item);
		
		item.setType(Material.WHITE_STAINED_GLASS);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "White Stained Glass");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(24, item);
		
		item.setType(Material.WHITE_CONCRETE);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "White Concrete");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(25, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(26, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(27, item);
		
		item.setType(Material.LIGHT_BLUE_DYE);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Light Blue Dye");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(23, item);
		
		item.setType(Material.TERRACOTTA);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Terracotta");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(28, item);
		
		item.setType(Material.PURPLE_STAINED_GLASS);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Purple Stained Glass");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(29, item);
		
		item.setType(Material.PURPLE_DYE);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Purple Dye");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(30, item);
		
		item.setType(Material.GREEN_TERRACOTTA);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Green Terracotta");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(31, item);
		
		item.setType(Material.GREEN_TERRACOTTA);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Green Terracotta");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(32, item);
		
		item.setType(Material.WHITE_TERRACOTTA);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "White Terracotta");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(33, item);
		
		item.setType(Material.LIGHT_BLUE_DYE);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Light Blue Dye");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(34, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(35, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(36, item);
		
		item.setType(Material.BLACK_STAINED_GLASS);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Black Stained Glass");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(37, item);
		
		item.setType(Material.GRAY_TERRACOTTA);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Gray Terracotta");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(38, item);
		
		item.setType(Material.LIGHT_BLUE_DYE);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Light Blue Dye");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(39, item);
		
		item.setType(Material.BLUE_STAINED_GLASS);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Blue Stained Glass");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(40, item);
		
		item.setType(Material.PURPLE_STAINED_GLASS);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Purple Stained Glass");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(41, item);
		
		item.setType(Material.BLACK_STAINED_GLASS);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Black Stained Glass");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(42, item);
		
		item.setType(Material.GREEN_TERRACOTTA);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Green Terracotta");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(43, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(44, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(45, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(46, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(47, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(48, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(49, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(50, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(51, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(52, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(53, item);
		
		
		
		colorterminal = inv;
	}
	
	
	@EventHandler
	public void onClickNPC(InventoryClickEvent event) {
		  if (!event.getView().getTitle().contains("Select all the WHITE items!"))
			  return;
		  if (event.getCurrentItem() == null)
			  return;
		  if (event.getCurrentItem().getItemMeta() == null)
			  return;
		  
		  Player player = (Player) event.getWhoClicked();
		  event.setCancelled(true);
		  
		  if(event.getClickedInventory().getType() == InventoryType.PLAYER)
			  return;
		  if (event.getSlot() == 11) {
			  player.sendMessage("§cWrong!");
			  player.closeInventory();
			  player.updateInventory();
			  
			  return;
			  
		  
		  }
		  
		  if (event.getSlot() == 12) {
			  player.sendMessage("§cWrong!");
			  player.closeInventory();
			  player.updateInventory();
			  
			  return;
			  
		  
		  }
		  if (event.getSlot() == 14) {
			  player.sendMessage("§cWrong!");
			  player.closeInventory();
			  player.updateInventory();
			  
			  return;
			  
		  
		  }
		  if (event.getSlot() == 15) {
			  player.sendMessage("§cWrong!");
			  player.closeInventory();
			  player.updateInventory();
			  
			  return;
			  
		  
		  }
		  if (event.getSlot() == 19) {
			  player.sendMessage("§cWrong!");
			  player.closeInventory();
			  player.updateInventory();
			  
			  return;
			  
		  
		  }
		  if (event.getSlot() == 21) {
			  player.sendMessage("§cWrong!");
			  player.closeInventory();
			  player.updateInventory();
			  
			  return;
			  
		  
		  }
		  if (event.getSlot() == 22) {
			  player.sendMessage("§cWrong!");
			  player.closeInventory();
			  player.updateInventory();
			  
			  return;
			  
		  
		  }
		  
		  if (event.getSlot() == 23) {
			  player.sendMessage("§cWrong!");
			  player.closeInventory();
			  player.updateInventory();
			  
			  return;
			  
		  
		  }
		  if (event.getSlot() == 28) {
			  player.sendMessage("§cWrong!");
			  player.closeInventory();
			  player.updateInventory();
			  
			  return;
			  
		  
		  }
		  if (event.getSlot() == 29) {
			  player.sendMessage("§cWrong!");
			  player.closeInventory();
			  player.updateInventory();
			  
			  return;
			  
		  
		  }
		  if (event.getSlot() == 30) {
			  player.sendMessage("§cWrong!");
			  player.closeInventory();
			  player.updateInventory();
			  
			  return;
			  
		  
		  }
		  if (event.getSlot() == 31) {
			  player.sendMessage("§cWrong!");
			  player.closeInventory();
			  player.updateInventory();
			  
			  return;
			  
		  
		  }
		  
		  if (event.getSlot() == 32) {
			  player.sendMessage("§cWrong!");
			  player.closeInventory();
			  player.updateInventory();
			  
			  return;
			  
		  
		  }
		  if (event.getSlot() == 34) {
			  player.sendMessage("§cWrong!");
			  player.closeInventory();
			  player.updateInventory();
			  
			  return;
			  
		  
		  }
		  
		  if (event.getSlot() == 37) {
			  player.sendMessage("§cWrong!");
			  player.closeInventory();
			  player.updateInventory();
			  
			  return;
			  
		  
		  }
		  if (event.getSlot() == 38) {
			  player.sendMessage("§cWrong!");
			  player.closeInventory();
			  player.updateInventory();
			  
			  return;
			  
		  
		  }
		  
		  if (event.getSlot() == 39) {
			  player.sendMessage("§cWrong!");
			  player.closeInventory();
			  player.updateInventory();
			  
			  return;
			  
		  
		  }
		  
		  if (event.getSlot() == 40) {
			  player.sendMessage("§cWrong!");
			  player.closeInventory();
			  player.updateInventory();
			  
			  return;
			  
		  
		  }
		  
		  if (event.getSlot() == 41) {
			  player.sendMessage("§cWrong!");
			  player.closeInventory();
			  player.updateInventory();
			  
			  return;
			  
		  
		  }
		  
		  if (event.getSlot() == 42) {
			  player.sendMessage("§cWrong!");
			  player.closeInventory();
			  player.updateInventory();
			  
			  return;
			  
		  
		  }
		  
		  if (event.getSlot() == 43) {
			  player.sendMessage("§cWrong!");
			  player.closeInventory();
			  player.updateInventory();
			  
			  return;
			  
		  
		  }
		  
		  if (event.getSlot() == 13) {
			  if(colorterminal.getItem(13).getEnchantments().isEmpty()) {
			  ItemStack item = new ItemStack(Material.WHITE_STAINED_GLASS);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.GREEN + "White Stained Glass");
				meta.addEnchant(Enchantment.DURABILITY, 1, true);
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				item.setItemMeta(meta);
				colorterminal.setItem(13, item);
				
				l = l+1;
				
			  }


			  player.updateInventory();
			  if(l == 7) {
				  player.closeInventory();
				  player.updateInventory();
				  player.sendMessage("You done the terminal");
				  
				  
			  }
			  
			  return;
			  
		  
		  }
		  
		  if (event.getSlot() == 10) {
			  if(colorterminal.getItem(10).getEnchantments().isEmpty()) {
			  ItemStack item = new ItemStack(Material.WHITE_CONCRETE);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.GREEN + "White Concrete");
				meta.addEnchant(Enchantment.DURABILITY, 1, true);
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				item.setItemMeta(meta);
				colorterminal.setItem(10, item);
				
				l = l+1;
				
			  }


			  player.updateInventory();
			  if(l == 7) {
				  player.closeInventory();
				  player.updateInventory();
				  player.sendMessage("You done the terminal");
				  
				  
			  }
			  
			  return;
			  
		  
		  }
		  
		  
		  if (event.getSlot() == 16) {
			  if(colorterminal.getItem(16).getEnchantments().isEmpty()) {
			  ItemStack item = new ItemStack(Material.WHITE_STAINED_GLASS);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.GREEN + "White Stained Glass");
				meta.addEnchant(Enchantment.DURABILITY, 1, true);
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				item.setItemMeta(meta);
				colorterminal.setItem(16, item);
				
				l = l+1;
				
			  }


			  player.updateInventory();
			  if(l == 7) {
				  player.closeInventory();
				  player.updateInventory();
				  player.sendMessage("You done the terminal");
				  
				  
			  }
			  
			  return;
			  
		  
		  }
		  
		  
		  if (event.getSlot() == 24) {
			  if(colorterminal.getItem(24).getEnchantments().isEmpty()) {
			  ItemStack item = new ItemStack(Material.WHITE_STAINED_GLASS);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.GREEN + "White Stained Glass");
				meta.addEnchant(Enchantment.DURABILITY, 1, true);
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				item.setItemMeta(meta);
				colorterminal.setItem(24, item);
				
				l = l+1;
				
			  }


			  player.updateInventory();
			  if(l == 7) {
				  player.closeInventory();
				  player.updateInventory();
				  player.sendMessage("You done the terminal");
				  
				  
			  }
			  
			  return;
			  
		  
		  }
		  
		  if (event.getSlot() == 25) {
			  if(colorterminal.getItem(25).getEnchantments().isEmpty()) {
			  ItemStack item = new ItemStack(Material.WHITE_CONCRETE);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.GREEN + "White Concrete");
				meta.addEnchant(Enchantment.DURABILITY, 1, true);
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				item.setItemMeta(meta);
				colorterminal.setItem(25, item);
				
				l = l+1;
				
			  }


			  player.updateInventory();
			  if(l == 7) {
				  player.closeInventory();
				  player.updateInventory();
				  player.sendMessage("You done the terminal");
				  
				  
			  }
			  
			  return;
			  
		  
		  }
		  
		  if (event.getSlot() == 20) {
			  if(colorterminal.getItem(20).getEnchantments().isEmpty()) {
			  ItemStack item = new ItemStack(Material.WHITE_DYE);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.GREEN + "White Dye");
				meta.addEnchant(Enchantment.DURABILITY, 1, true);
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				item.setItemMeta(meta);
				colorterminal.setItem(20, item);
				
				l = l+1;
				
			  }


			  player.updateInventory();
			  if(l == 7) {
				  player.closeInventory();
				  player.updateInventory();
				  player.sendMessage("You done the terminal");
				  
				  
			  }
			  
			  return;
			  
		  
		  }
		  
		  if (event.getSlot() == 33) {
			  if(colorterminal.getItem(33).getEnchantments().isEmpty()) {
			  ItemStack item = new ItemStack(Material.WHITE_TERRACOTTA);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.GREEN + "White Terracotta");
				meta.addEnchant(Enchantment.DURABILITY, 1, true);
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				item.setItemMeta(meta);
				colorterminal.setItem(33, item);
				
				l = l+1;
				
			  }


			  player.updateInventory();
			  if(l == 7) {
				  player.closeInventory();
				  player.updateInventory();
				  player.sendMessage("You done the terminal");
				  l = 0;
				  
				  
			  }
			  
			  return;
			  
		  
		  }
		  
		  
		  
			 
	}

}
