package me.CarsCupcake.SkyblockRemake.Items.farming.items.hoes;

import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

import static me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems;

public class CactusKnife implements Listener {
    public CactusKnife() {
        ItemManager manager = new ItemManager("Cactus Knife", "CACTUS_KNIFE", ItemType.Hoe, Material.GOLDEN_HOE, ItemRarity.EPIC);
        manager.setLore(new ArrayList<>(List.of("§7Slices through §acactus §7instantly!")));
        SkyblockItems.put(manager.itemID, manager);
    }

    @EventHandler
    public void blockDamage(BlockDamageEvent event) {
        if (event.getBlock().getType() != Material.CACTUS) return;
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
        ItemStack item = player.getItemInHand();
        if (!item.hasItemMeta()) return;
        if (!ItemHandler.getPDC("id", item, PersistentDataType.STRING).equals("CACTUS_KNIFE")) return;
        event.setInstaBreak(true);
    }
}
