package me.CarsCupcake.SkyblockRemake.Commission;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Tools;
import me.CarsCupcake.SkyblockRemake.Configs.MiningSystem;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class CommissionInv {
public static  Inventory getInv(SkyblockPlayer player) {
	Inventory inv = Bukkit.createInventory(null, 36, "§7Commissions");
	ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
	ItemMeta meta = item.getItemMeta();
	meta.setDisplayName(" ");
	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	item.setItemMeta(meta);
	
	for(int i = 0; i < 30; i++) {
		inv.setItem(i, item);
	}
	for(int i = 32; i < 36;  i++) {
		inv.setItem(i, item);
	}
	
	item = new ItemStack(Material.FILLED_MAP);
	meta = item.getItemMeta();
	ArrayList<String> lore = new ArrayList<>();

	lore.add("View milestone Progress and");
	lore.add("rewards!");
	lore.add(" ");
	lore.add("§7Progress to milestone -: §r-§6%");
	lore.add("§7-------------------- " + MiningSystem.get().getInt(player.getUniqueId() + ".commissions.completet"));
	lore.add(" ");
	lore.add("§eClick to view!");
	meta.setLore(lore);
	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	item.setItemMeta(meta);
	inv.setItem(30, item);
	
	item = new ItemStack(Material.BARRIER);
	meta = item.getItemMeta();
	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	meta.setDisplayName("§cClose");
	item.setItemMeta(meta);
	inv.setItem(31, item);
	
	if(player.commissionsSlots == 2) {
		inv.setItem(11, createCommissionTemplate(player, 0));
		inv.setItem(15, createCommissionTemplate(player, 1));
	}
	
	return inv;
}
private static ItemStack createCommissionTemplate(SkyblockPlayer player, int comslot) {
	ItemStack item = new ItemStack(Material.WRITABLE_BOOK);
	ItemMeta meta = item.getItemMeta();
	PersistentDataContainer data = meta.getPersistentDataContainer();
	meta.setDisplayName("§6Commission #" +(comslot+ 1));
	ArrayList<String> lore = new ArrayList<>();
		lore.add("§7Commissions are tasks given");
	lore.add("§7directly by the king which give");
	lore.add("§7bountful rewards.");
	lore.add(" ");
	lore.add("§9" + player.Comms.get(comslot).getComm().toString());
	Commission com = player.Comms.get(comslot);
	for(String str : com.getComm().getDesciption()) {
		lore.add(str);
	}
	lore.add(" ");
	lore.add("§9Rewards");
	lore.add("§7- §5100 HotM Exp");
	lore.add("§7- §2400 Mithril Powder");
	lore.add("§7- §3+8k Mining Exp");
	lore.add(" ");
	lore.add("§9Progress");
	int colored =(int) ((int) 20*(com.getPersentage()));
	String line = "";
	for(int i = 0; i < 20; i++) {
	if(colored > i)
		line = line + "§9";
	else
		line = line + "§7";
	line += "-";
	
	}
	
	line += "§9" + Tools.round(com.getPersentage() * 100, 1) + "%";
	lore.add(line);
	meta.setLore(lore);
	
	data.set(new NamespacedKey(Main.getMain(), "internalID"), PersistentDataType.INTEGER, comslot);
	item.setItemMeta(meta);
	return item;
}
}
