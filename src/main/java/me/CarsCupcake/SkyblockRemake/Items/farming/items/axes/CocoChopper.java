package me.CarsCupcake.SkyblockRemake.Items.farming.items.axes;

import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.PlayerFarmEvent;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

import static me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems;

public class CocoChopper implements Listener {
    public CocoChopper() {
        ItemManager manager = new ItemManager("Coco Chopper", "COCO_CHOPPER", ItemType.Axe, Material.GOLDEN_AXE, ItemRarity.EPIC);
        manager.setLore(new ArrayList<>(List.of("§7Increases Cocoa Bean drops by", "§a20% §7but cannot break logs.")));
        SkyblockItems.put(manager.itemID, manager);
    }

    @EventHandler
    public void onFarm(PlayerFarmEvent event) {
        if (event.getBlock().getType() != Material.COCOA) return;
        ItemStack item = event.getPlayer().getItemInHand();
        if (!item.hasItemMeta()) return;
        if (ItemHandler.getPDC("id", item, PersistentDataType.STRING).equals("COCO_CHOPPER"))
            event.setFarmingFortune(event.getFarmingFortune() + 20);
    }

    @EventHandler
    public void onBlockBreakFarm(BlockBreakEvent event) {
        Material m = event.getBlock().getType();
        if (!(m == Material.ACACIA_WOOD || m == Material.BIRCH_WOOD || m == Material.DARK_OAK_WOOD || m == Material.JUNGLE_WOOD || m == Material.OAK_WOOD || m == Material.SPRUCE_WOOD))
            return;
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
        ItemStack item = player.getItemInHand();
        if (!item.hasItemMeta()) return;
        if (ItemHandler.getPDC("id", item, PersistentDataType.STRING).equals("COCO_CHOPPER")) event.setCancelled(true);
    }
}
