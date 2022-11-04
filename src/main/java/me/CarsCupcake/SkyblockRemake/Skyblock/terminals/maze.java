package me.CarsCupcake.SkyblockRemake.Skyblock.terminals;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.CarsCupcake.SkyblockRemake.Main;




public class maze implements Listener {
	public static Inventory mazeterminal;


	
	
	public static void createinventory(Location loc){
		Inventory inv = Bukkit.createInventory(null, 54, "Navigate the maze!");
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
		
		item.setType(Material.RED_STAINED_GLASS_PANE);
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
		PersistentDataContainer data = meta.getPersistentDataContainer();
		data.set(new NamespacedKey(Main.getMain(), "x"), PersistentDataType.INTEGER, loc.getBlockX());
		data.set(new NamespacedKey(Main.getMain(), "y"), PersistentDataType.INTEGER, loc.getBlockY());
		data.set(new NamespacedKey(Main.getMain(), "z"), PersistentDataType.INTEGER, loc.getBlockZ());
		item.setItemMeta(meta);
		inv.setItem(11, item);
		
		item.setType(Material.WHITE_STAINED_GLASS_PANE);
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
		inv.setItem(14, item);
		
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
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(19, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(20, item);
		
		item.setType(Material.WHITE_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(21, item);
		
		item.setType(Material.WHITE_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(22, item);
		
		item.setType(Material.WHITE_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(23, item);
		
		item.setType(Material.WHITE_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(24, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
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
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(28, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(29, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(30, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(31, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(32, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(34, item);
		
		item.setType(Material.WHITE_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(33, item);
		
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
		
		item.setType(Material.WHITE_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(37, item);
		
		item.setType(Material.WHITE_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(38, item);
		
		item.setType(Material.WHITE_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(39, item);
		
		item.setType(Material.WHITE_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(40, item);
		
		item.setType(Material.WHITE_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(41, item);
		
		item.setType(Material.WHITE_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(42, item);
		
		item.setType(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(" ");
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
		
		item.setType(Material.LIME_STAINED_GLASS_PANE);
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
		
		
		
		mazeterminal = inv;
		
		
		
	}
	
	
	
	
	
	
	
	@EventHandler
	  public void onClickTerminals(InventoryClickEvent event) {
		  if (!event.getView().getTitle().contains("Navigate the maze!"))
			  return;
		  if (event.getCurrentItem() == null)
			  return;
		  if (event.getCurrentItem().getItemMeta() == null)
			  return;		  
		  Player player = (Player) event.getWhoClicked();
		  event.setCancelled(true);		  
		  if(event.getClickedInventory().getType() == InventoryType.PLAYER)
			  return;	  
		  if (event.getSlot() == 37) {
			  ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(" ");
				meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				meta.setCustomModelData(1);
				item.setItemMeta(meta);
				mazeterminal.setItem(37, item);
				
				player.updateInventory();
			  		  }
if (event.getSlot() == 38) {
			  
			  if(mazeterminal.getItem(37).getType() == Material.LIME_STAINED_GLASS_PANE) {
			  
			  ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(" ");
				meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				item.setItemMeta(meta);
				mazeterminal.setItem(38, item);
				
				player.updateInventory();
			  }
			  }

if (event.getSlot() == 39) {
	  
	  if(mazeterminal.getItem(38).getType() == Material.LIME_STAINED_GLASS_PANE) {
	  
	  ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		mazeterminal.setItem(39, item);
		
		player.updateInventory();
	  }
	  }

if (event.getSlot() == 40) {
	  
	  if(mazeterminal.getItem(39).getType() == Material.LIME_STAINED_GLASS_PANE) {
	  
	  ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		mazeterminal.setItem(40, item);
		
		player.updateInventory();
	  }
	  }

if (event.getSlot() == 41) {
	  
	  if(mazeterminal.getItem(40).getType() == Material.LIME_STAINED_GLASS_PANE) {
	  
	  ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		mazeterminal.setItem(41, item);
		
		player.updateInventory();
	  }
	  }

if (event.getSlot() == 42) {
	  
	  if(mazeterminal.getItem(41).getType() == Material.LIME_STAINED_GLASS_PANE) {
	  
	  ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		mazeterminal.setItem(42, item);
		
		player.updateInventory();
	  }
	  }

if (event.getSlot() == 33) {
	  
	  if(mazeterminal.getItem(42).getType() == Material.LIME_STAINED_GLASS_PANE) {
	  
	  ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		mazeterminal.setItem(33, item);
		
		player.updateInventory();
	  }
	  }

if (event.getSlot() == 24) {
	  
	  if(mazeterminal.getItem(33).getType() == Material.LIME_STAINED_GLASS_PANE) {
	  
	  ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		mazeterminal.setItem(24, item);
		
		player.updateInventory();
	  }
	  }

if (event.getSlot() == 23) {
	  
	  if(mazeterminal.getItem(24).getType() == Material.LIME_STAINED_GLASS_PANE) {
	  
	  ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		mazeterminal.setItem(23, item);
		
		player.updateInventory();
	  }
	  }

if (event.getSlot() == 22) {
	  
	  if(mazeterminal.getItem(23).getType() == Material.LIME_STAINED_GLASS_PANE) {
	  
	  ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		mazeterminal.setItem(22, item);
		
		player.updateInventory();
	  }
	  }

if (event.getSlot() == 21) {
	  
	  if(mazeterminal.getItem(22).getType() == Material.LIME_STAINED_GLASS_PANE) {
	  
	  ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		mazeterminal.setItem(21, item);
		
		player.updateInventory();
	  }
	  }

if (event.getSlot() == 12) {
	  
	  if(mazeterminal.getItem(21).getType() == Material.LIME_STAINED_GLASS_PANE) {
	  ItemStack olditem = mazeterminal.getItem(11);
	  PersistentDataContainer data = olditem.getItemMeta().getPersistentDataContainer();
	  ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		

				
		if(data == null)
			return;
		Integer x = data.get(new NamespacedKey(Main.getMain(), "x"), PersistentDataType.INTEGER);
		Integer y = data.get(new NamespacedKey(Main.getMain(), "y"), PersistentDataType.INTEGER);
		Integer z = data.get(new NamespacedKey(Main.getMain(), "z"), PersistentDataType.INTEGER);
		Bukkit.broadcastMessage(x + " " + y + " " + z);

		mazeterminal.setItem(12, item);

		Double newx = null;

		
		if (player.getLocation().getBlockZ() > z) {
			 newx = (double) (z + 1);
		}else {
			newx = (double) (z);
		}
		
		Location loc = new Location(event.getWhoClicked().getWorld(), x + 0.5, y+0.25, newx);
		ArmorStand stand = player.getWorld().spawn(loc, ArmorStand.class);
		stand.setGravity(false);
		stand.setCustomNameVisible(true);
		stand.setVisible(false);
		stand.setInvulnerable(true);
		stand.setCustomName("§l§aTerminal Completed");
		stand.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 999999));
		stand.addScoreboardTag("tcomp");
		stand.setMarker(true);
		player.closeInventory();
		player.updateInventory();
		player.sendMessage("You done the Terminal");
		for(Player ply : Bukkit.getOnlinePlayers()) {
		ply.sendTitle(ChatColor.GREEN + player.getName() + " done the Maze terminal §r(§a1§r/§a1§r)", "WOW", 15, 50, 15);
		}
		
	  }
	  }
		  

	}
	

}
