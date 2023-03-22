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

public class PumpkinDicer implements Listener {
    public PumpkinDicer() {
        ItemManager manager = new ItemManager("Pumpkin Dicer", "PUMPKIN_DICER", ItemType.Axe, Material.GOLDEN_AXE, ItemRarity.EPIC);
        manager.setLore(new ArrayList<>(List.of(
                "§7Gain " + ChatColor.DARK_AQUA + "+5☯ Farming Wisdom",
                "§7while harvesting pumpkins.",
                " ",
                "§6Ability: Roll em'",
                "§7Every pumpkin you break, you",
                "§7make a wish to " + ChatColor.LIGHT_PURPLE + "RNGesus",
                "§7which may grant you with a",
                "§7few or TONS of extra melons!"
        )));
        SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Pumpkin Dicer 2.0", "PUMPKIN_DICER_2", ItemType.Axe, Material.GOLDEN_AXE, ItemRarity.EPIC);
        manager.setLore(new ArrayList<>(List.of(
                "§7Gain " + ChatColor.DARK_AQUA + "+7.5☯ Farming Wisdom",
                "§7while harvesting pumpkins.",
                " ",
                "§6Ability: Roll em'",
                "§7Every pumpkin you break, you",
                "§7make a wish to " + ChatColor.LIGHT_PURPLE + "RNGesus",
                "§7which may grant you with a",
                "§7few or TONS of extra melons!",
                "§7§oSame as the Melon Dicer 1.0,",
                "§7§ojust better!"
        )));
        SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Pumpkin Dicer 3.0", "PUMPKIN_DICER_3", ItemType.Axe, Material.GOLDEN_AXE, ItemRarity.LEGENDARY);
        manager.setLore(new ArrayList<>(List.of(
                "§7Gain " + ChatColor.DARK_AQUA + "+10☯ Farming Wisdom",
                "§7while harvesting pumpkins.",
                " ",
                "§6Ability: Roll em'",
                "§7Every pumpkin you break, you",
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
        if (event.getBlock().getType() != Material.PUMPKIN) return;
        ItemStack item = event.getPlayer().getItemInHand();
        if (!item.hasItemMeta()) return;
        ItemManager manager = Items.SkyblockItems.get(ItemHandler.getPDC("id", item, PersistentDataType.STRING));
        if (manager == null) return;
        if (!manager.itemID.startsWith("PUMPKIN_DICER")) return;
        HashMap<Bundle<ItemManager, Integer>, Double> lootTable = new HashMap<>();
        lootTable.put(new Bundle<>(SkyblockItems.get(Material.PUMPKIN + ""), 64), 0.000899);
        lootTable.put(new Bundle<>(SkyblockItems.get("ENCHANTED_PUMPKIN"), 1), 0.0003994);
        lootTable.put(new Bundle<>(SkyblockItems.get("ENCHANTED_PUMPKIN"), 10), 0.0000699);
        lootTable.put(new Bundle<>(SkyblockItems.get("ENCHANTED_PUMPKIN"), 64), 0.0000099862);

        switch (manager.itemID){
            case "PUMPKIN_DICER" -> event.setFarmingWisdom(event.getFarmingWisdom() + 5);
            case "PUMPKIN_DICER_2" -> event.setFarmingWisdom(event.getFarmingWisdom() + 7.5);
            case "PUMPKIN_DICER_3" -> event.setFarmingWisdom(event.getFarmingWisdom() + 10);
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
