package me.CarsCupcake.SkyblockRemake.Skyblock.terminals;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class order implements Listener{
	public static Inventory orderterminal;
	
	public static void createInventory() {
	Inventory inv = Bukkit.createInventory(null, 36, "Click in order!");
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
	
	item.setType(Material.RED_STAINED_GLASS_PANE);
	meta = item.getItemMeta();
	meta.setDisplayName(ChatColor.GREEN + "11");
	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	item.setAmount(11);
	item.setItemMeta(meta);
	inv.setItem(10, item);
	
	item.setType(Material.RED_STAINED_GLASS_PANE);
	meta = item.getItemMeta();
	meta.setDisplayName(ChatColor.GREEN + "4");
	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	item.setAmount(4);
	item.setItemMeta(meta);
	inv.setItem(11, item);
	
	item.setType(Material.RED_STAINED_GLASS_PANE);
	meta = item.getItemMeta();
	meta.setDisplayName(ChatColor.GREEN + "5");
	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	item.setAmount(5);
	item.setItemMeta(meta);
	inv.setItem(12, item);
	
	item.setType(Material.RED_STAINED_GLASS_PANE);
	meta = item.getItemMeta();
	meta.setDisplayName(ChatColor.GREEN + "14");
	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	item.setAmount(14);
	item.setItemMeta(meta);
	inv.setItem(13, item);
	
	item.setType(Material.RED_STAINED_GLASS_PANE);
	meta = item.getItemMeta();
	meta.setDisplayName(ChatColor.GREEN + "12");
	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	item.setAmount(12);
	item.setItemMeta(meta);
	inv.setItem(14, item);
	
	item.setType(Material.RED_STAINED_GLASS_PANE);
	meta = item.getItemMeta();
	meta.setDisplayName(ChatColor.GREEN + "9");
	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	item.setAmount(9);
	item.setItemMeta(meta);
	inv.setItem(15, item);
	
	item.setType(Material.RED_STAINED_GLASS_PANE);
	meta = item.getItemMeta();
	meta.setDisplayName(ChatColor.GREEN + "1");
	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	item.setAmount(1);
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
	
	item.setType(Material.RED_STAINED_GLASS_PANE);
	meta = item.getItemMeta();
	meta.setDisplayName(ChatColor.GREEN + "3");
	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	item.setAmount(3);
	item.setItemMeta(meta);
	inv.setItem(19, item);
	
	item.setType(Material.RED_STAINED_GLASS_PANE);
	meta = item.getItemMeta();
	meta.setDisplayName(ChatColor.GREEN + "8");
	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	item.setAmount(8);
	item.setItemMeta(meta);
	inv.setItem(20, item);
	
	item.setType(Material.RED_STAINED_GLASS_PANE);
	meta = item.getItemMeta();
	meta.setDisplayName(ChatColor.GREEN + "10");
	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	item.setAmount(10);
	item.setItemMeta(meta);
	inv.setItem(21, item);
	
	item.setType(Material.RED_STAINED_GLASS_PANE);
	meta = item.getItemMeta();
	meta.setDisplayName(ChatColor.GREEN + "7");
	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	item.setAmount(7);
	item.setItemMeta(meta);
	inv.setItem(22, item);
	
	item.setType(Material.RED_STAINED_GLASS_PANE);
	meta = item.getItemMeta();
	meta.setDisplayName(ChatColor.GREEN + "13");
	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	item.setAmount(13);
	item.setItemMeta(meta);
	inv.setItem(23, item);
	
	item.setType(Material.RED_STAINED_GLASS_PANE);
	meta = item.getItemMeta();
	meta.setDisplayName(ChatColor.GREEN + "2");
	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	item.setAmount(2);
	item.setItemMeta(meta);
	inv.setItem(24, item);
	
	item.setType(Material.RED_STAINED_GLASS_PANE);
	meta = item.getItemMeta();
	meta.setDisplayName(ChatColor.GREEN + "6");
	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	item.setAmount(6);
	item.setItemMeta(meta);
	inv.setItem(25, item);
	
	item.setType(Material.BLACK_STAINED_GLASS_PANE);
	meta = item.getItemMeta();
	meta.setDisplayName(" ");
	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	item.setAmount(1);
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
	inv.setItem(33, item);
	
	item.setType(Material.BLACK_STAINED_GLASS_PANE);
	meta = item.getItemMeta();
	meta.setDisplayName(" ");
	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	item.setItemMeta(meta);
	inv.setItem(34, item);
	
	item.setType(Material.BLACK_STAINED_GLASS_PANE);
	meta = item.getItemMeta();
	meta.setDisplayName(" ");
	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	item.setItemMeta(meta);
	inv.setItem(35, item);
	
	
	
	
	
	
	
	
	
	
	orderterminal = inv;
	
	
	}
	
	@EventHandler
	  public void onClickTerminals(InventoryClickEvent event) {
		  if (!event.getView().getTitle().contains("Click in order!"))
			  return;
		  if (event.getCurrentItem() == null)
			  return;
		  if (event.getCurrentItem().getItemMeta() == null)
			  return;		  
		  Player player = (Player) event.getWhoClicked();
		  event.setCancelled(true);		  
		  if(event.getClickedInventory().getType() == InventoryType.PLAYER)
			  return;
		  if (event.getSlot() == 16) {
			  ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.GREEN + "1");
				meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				meta.setCustomModelData(1);
				item.setAmount(1);
				item.setItemMeta(meta);
				orderterminal.setItem(16, item);
				
				player.updateInventory();
			}
		  if (event.getSlot() == 24) {
			  if(orderterminal.getItem(16).getType() == Material.LIME_STAINED_GLASS_PANE) {
			  ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.GREEN + "2");
				meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				meta.setCustomModelData(1);
				item.setAmount(2);
				item.setItemMeta(meta);
				orderterminal.setItem(24, item);
			  }else {
				  player.sendTitle(ChatColor.RED + player.getName() + " messed the Order puzzle up",ChatColor.DARK_RED + "How bad...", 15, 50, 15);
				  player.closeInventory();
			  }
				player.updateInventory();
			}
		  if (event.getSlot() == 19) {
			  if(orderterminal.getItem(24).getType() == Material.LIME_STAINED_GLASS_PANE) {
			  ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.GREEN + "3");
				meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				meta.setCustomModelData(1);
				item.setAmount(3);
				item.setItemMeta(meta);
				orderterminal.setItem(19, item);
			  }else {
				  player.sendTitle(ChatColor.RED + player.getName() + " messed the Order puzzle up",ChatColor.DARK_RED + "How bad...", 15, 50, 15);
				  player.closeInventory();
			  }
				player.updateInventory();
			}
		  if (event.getSlot() == 11) {
			  if(orderterminal.getItem(19).getType() == Material.LIME_STAINED_GLASS_PANE) {
			  ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.GREEN + "4");
				meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				meta.setCustomModelData(1);
				item.setAmount(4);
				item.setItemMeta(meta);
				orderterminal.setItem(11, item);
			  }else {
				  player.sendTitle(ChatColor.RED + player.getName() + " messed the Order puzzle up",ChatColor.DARK_RED + "How bad...", 15, 50, 15);
				  player.closeInventory();
			  }
				player.updateInventory();
			}
		  if (event.getSlot() == 12) {
			  if(orderterminal.getItem(11).getType() == Material.LIME_STAINED_GLASS_PANE) {
			  ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.GREEN + "5");
				meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				meta.setCustomModelData(1);
				item.setAmount(5);
				item.setItemMeta(meta);
				orderterminal.setItem(12, item);
			  }else {
				  player.sendTitle(ChatColor.RED + player.getName() + " messed the Order puzzle up",ChatColor.DARK_RED + "How bad...", 15, 50, 15);
				  player.closeInventory();
			  }
				player.updateInventory();
			}
		  if (event.getSlot() == 25) {
			  if(orderterminal.getItem(12).getType() == Material.LIME_STAINED_GLASS_PANE) {
			  ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.GREEN + "6");
				meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				meta.setCustomModelData(1);
				item.setAmount(6);
				item.setItemMeta(meta);
				orderterminal.setItem(25, item);
			  }else {
				  player.sendTitle(ChatColor.RED + player.getName() + " messed the Order puzzle up",ChatColor.DARK_RED + "How bad...", 15, 50, 15);
				  player.closeInventory();
			  }
				player.updateInventory();
			}
		  if (event.getSlot() == 22) {
			  if(orderterminal.getItem(25).getType() == Material.LIME_STAINED_GLASS_PANE) {
			  ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.GREEN + "7");
				meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				meta.setCustomModelData(1);
				item.setAmount(7);
				item.setItemMeta(meta);
				orderterminal.setItem(22, item);
			  }else {
				  player.sendTitle(ChatColor.RED + player.getName() + " messed the Order puzzle up",ChatColor.DARK_RED + "How bad...", 15, 50, 15);
				  player.closeInventory();
			  }
				player.updateInventory();
			}
		  if (event.getSlot() == 20) {
			  if(orderterminal.getItem(22).getType() == Material.LIME_STAINED_GLASS_PANE) {
			  ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.GREEN + "8");
				meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				meta.setCustomModelData(1);
				item.setAmount(8);
				item.setItemMeta(meta);
				orderterminal.setItem(20, item);
			  }else {
				  player.sendTitle(ChatColor.RED + player.getName() + " messed the Order puzzle up",ChatColor.DARK_RED + "How bad...", 15, 50, 15);
				  player.closeInventory();
			  }
				player.updateInventory();
			}
		  if (event.getSlot() == 15) {
			  if(orderterminal.getItem(20).getType() == Material.LIME_STAINED_GLASS_PANE) {
			  ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.GREEN + "9");
				meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				meta.setCustomModelData(1);
				item.setAmount(9);
				item.setItemMeta(meta);
				orderterminal.setItem(15, item);
			  }else {
				  player.sendTitle(ChatColor.RED + player.getName() + " messed the Order puzzle up",ChatColor.DARK_RED + "How bad...", 15, 50, 15);
				  player.closeInventory();
			  }
				player.updateInventory();
			}
		  if (event.getSlot() == 21) {
			  if(orderterminal.getItem(15).getType() == Material.LIME_STAINED_GLASS_PANE) {
			  ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.GREEN + "10");
				meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				meta.setCustomModelData(1);
				item.setAmount(10);
				item.setItemMeta(meta);
				orderterminal.setItem(21, item);
			  }else {
				  player.sendTitle(ChatColor.RED + player.getName() + " messed the Order puzzle up",ChatColor.DARK_RED + "How bad...", 15, 50, 15);
				  player.closeInventory();
			  }
				player.updateInventory();
			}
		  if (event.getSlot() == 10) {
			  if(orderterminal.getItem(21).getType() == Material.LIME_STAINED_GLASS_PANE) {
			  ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.GREEN + "11");
				meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				meta.setCustomModelData(1);
				item.setAmount(11);
				item.setItemMeta(meta);
				orderterminal.setItem(10, item);
			  }else {
				  player.sendTitle(ChatColor.RED + player.getName() + " messed the Order puzzle up",ChatColor.DARK_RED + "How bad...", 15, 50, 15);
				  player.closeInventory();
			  }
				player.updateInventory();
			}
		  if (event.getSlot() == 14) {
			  if(orderterminal.getItem(10).getType() == Material.LIME_STAINED_GLASS_PANE) {
			  ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.GREEN + "12");
				meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				meta.setCustomModelData(1);
				item.setAmount(12);
				item.setItemMeta(meta);
				orderterminal.setItem(14, item);
			  }else {
				  player.sendTitle(ChatColor.RED + player.getName() + " messed the Order puzzle up",ChatColor.DARK_RED + "How bad...", 15, 50, 15);
				  player.closeInventory();
			  }
				player.updateInventory();
			}
		  if (event.getSlot() == 23) {
			  if(orderterminal.getItem(14).getType() == Material.LIME_STAINED_GLASS_PANE) {
			  ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.GREEN + "13");
				meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				meta.setCustomModelData(1);
				item.setAmount(13);
				item.setItemMeta(meta);
				orderterminal.setItem(23, item);
			  }else {
				  player.sendTitle(ChatColor.RED + player.getName() + " messed the Order puzzle up",ChatColor.DARK_RED + "How bad...", 15, 50, 15);
				  player.closeInventory();
			  }
				player.updateInventory();
			}
		  if (event.getSlot() == 13) {
			  if(orderterminal.getItem(23).getType() == Material.LIME_STAINED_GLASS_PANE) {
				  player.sendTitle(ChatColor.GREEN + player.getName() + " done the Order terminal §r(§a1§r/§a1§r)", "I am so proud of you rn.", 15, 50, 15);
				  player.closeInventory();
			  }else {
				  player.sendTitle(ChatColor.RED + player.getName() + " messed the Order puzzle up",ChatColor.DARK_RED + "How bad...", 15, 50, 15);
				  player.closeInventory();
			  }
				player.updateInventory();
			}
		  
	}
}
