package me.CarsCupcake.SkyblockRemake.Items.farming.items.axes;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.PlayerFarmEvent;
import me.CarsCupcake.SkyblockRemake.Items.*;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems;

public class MelonDicer implements Listener {
    public static void init() {
        ItemManager manager = new ItemManager("Melon Dicer", "MELON_DICER", ItemType.Axe, Material.DIAMOND_AXE, ItemRarity.EPIC);
        manager.setLore(new ArrayList<>(List.of(
                "§7Gain " + ChatColor.DARK_AQUA + "+5☯ Farming Wisdom",
                "§7while harvesting melons.",
                " ",
                "§6Ability: Roll em'",
                "§7Every melon you break, you",
                "§7make a wish to " + ChatColor.LIGHT_PURPLE + "RNGesus",
                "§7which may grant you with a",
                "§7few or TONS of extra melons!"
        )));
        SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Melon Dicer 2.0", "MELON_DICER_2", ItemType.Axe, Material.DIAMOND_AXE, ItemRarity.EPIC);
        manager.setLore(new ArrayList<>(List.of(
                "§7Gain " + ChatColor.DARK_AQUA + "+7.5☯ Farming Wisdom",
                "§7while harvesting melons.",
                " ",
                "§6Ability: Roll em'",
                "§7Every melon you break, you",
                "§7make a wish to " + ChatColor.LIGHT_PURPLE + "RNGesus",
                "§7which may grant you with a",
                "§7few or TONS of extra melons!",
                "§7§oSame as the Melon Dicer 1.0,",
                "§7§ojust better!"
        )));
        SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Melon Dicer 3.0", "MELON_DICER_3", ItemType.Axe, Material.DIAMOND_AXE, ItemRarity.LEGENDARY);
        manager.setLore(new ArrayList<>(List.of(
                "§7Gain " + ChatColor.DARK_AQUA + "+10☯ Farming Wisdom",
                "§7while harvesting melons.",
                " ",
                "§6Ability: Roll em'",
                "§7Every melon you break, you",
                "§7make a wish to " + ChatColor.LIGHT_PURPLE + "RNGesus",
                "§7which may grant you with a",
                "§7few or TONS of extra melons!",
                "§7§oAlmost identical to the Melon",
                "§7§oDicer 2.0, but it has 3.0 in the",
                "§7§oname!"
        )));
        SkyblockItems.put(manager.itemID, manager);
    }

    @EventHandler
    public void onFarm(PlayerFarmEvent event) {
        if (event.getBlock().getType() != Material.MELON) return;
        ItemStack item = event.getPlayer().getItemInHand();
        if (!item.hasItemMeta()) return;
        ItemManager manager = Items.SkyblockItems.get(ItemHandler.getPDC("id", item, PersistentDataType.STRING));
        if (manager == null) return;
        if (!manager.itemID.startsWith("MELON_DICER")) return;
        HashMap<Bundle<ItemManager, Integer>, Double> lootTable = new HashMap<>();
        lootTable.put(new Bundle<>(SkyblockItems.get("ENCHANTED_MELON"), 1), 0.001547);
        lootTable.put(new Bundle<>(SkyblockItems.get("ENCHANTED_MELON"), 5), 0.0004491);
        lootTable.put(new Bundle<>(SkyblockItems.get("ENCHANTED_MELON"), 50), 0.0000699);
        lootTable.put(new Bundle<>(SkyblockItems.get("ENCHANTED_MELON_BLOCK"), 2), 0.0000099792);

        switch (manager.itemID){
            case "MELON_DICER" -> event.setFarmingWisdom(event.getFarmingWisdom() + 5);
            case "MELON_DICER_2" -> event.setFarmingWisdom(event.getFarmingWisdom() + 7.5);
            case "MELON_DICER_3" -> event.setFarmingWisdom(event.getFarmingWisdom() + 10);
            default -> {
                return;
            }
        }

        List<Bundle<ItemManager, Integer>> bundles = Tools.generateItems(lootTable);
        if (bundles.isEmpty())
            return;
        event.getPlayer().addItem(bundles.get(0).getFirst(), bundles.get(0).getLast());
    }
}
