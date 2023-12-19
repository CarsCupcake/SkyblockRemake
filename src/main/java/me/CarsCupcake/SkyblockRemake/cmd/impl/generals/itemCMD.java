package me.CarsCupcake.SkyblockRemake.cmd.impl.generals;

import java.util.ArrayList;

import java.util.Collections;
import java.util.TreeMap;

import me.CarsCupcake.SkyblockRemake.Items.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.CarsCupcake.SkyblockRemake.Items.ItemsSearch;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class itemCMD implements CommandExecutor {
    public static ArrayList<ItemManager> customItems = new ArrayList<>();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("item")) {
            if (customItems.isEmpty()) {
                ArrayList<String> strings = new ArrayList<>();
                for (String str : me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.keySet()) {
                    ItemManager manager = Items.SkyblockItems.get(str);
                    if (manager.isBaseItem || manager.hasEdition) continue;
                    strings.add(str);
                }
                Collections.sort(strings);
                for (String s : strings)
                    customItems.add(Items.SkyblockItems.get(s));
            }
            if (!(sender instanceof Player)) {
                sender.sendMessage("Du kannst das net");
                return true;
            }
            Player player = (Player) sender;
            if (args.length == 1)
                ItemsSearch.buildInventory(SkyblockPlayer.getSkyblockPlayer(player), args[0]);
            else
                ItemsSearch.buildInventory(SkyblockPlayer.getSkyblockPlayer(player), "");
            player.updateInventory();
            return true;
        }
        return false;
    }


}
