package me.CarsCupcake.SkyblockRemake.AuctionHouse;

import me.CarsCupcake.SkyblockRemake.utils.Inventorys.InventoryBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.InventoryTemplate;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.Items.ItemBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.TemplateItems;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public class AuctionHouseTemplate implements InventoryTemplate {
    @Override
    public @Range(from = 1, to = 6) int getRows() {
        return 4;
    }

    @Override
    public @NotNull Inventory getCompiledItems() {
        return new InventoryBuilder(getRows(), getName())
                .fill(TemplateItems.EmptySlot.getItem())
                .setItem(new ItemBuilder(Material.GOLD_BLOCK)
                        .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                        .setName("§6Auctions Browser")
                        .addLoreRow("§7Find items for sale by players")
                        .addLoreRow("§7across Hypixel Skyblock! §8(No clickbait)")
                        .addLoreRow(" ")
                        .addLoreRow("§7Items offered here are for")
                        .addLoreRow("§6auction§7, meaning you have to")
                        .addLoreRow("§7place the top bid to acquire")
                        .addLoreRow("§7them!")
                        .addLoreRow(" ")
                        .addLoreRow("§eClick to browse!")
                        .build(), 11)
                .setItem(new ItemBuilder(Material.GOLDEN_CARROT)
                        .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                        .setName("§eView Bids")
                        .addLoreRow("§7You don't have any outstanding")
                        .addLoreRow("§7bids.")
                        .addLoreRow(" ")
                        .addLoreRow("§7Use the §6Auction Browser §7to")
                        .addLoreRow("§7find some cool items.")
                        .build(), 13)
                .setItem(new ItemBuilder(Material.GOLDEN_HORSE_ARMOR)
                        .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                        .setName("§aCreate Auction")
                        .addLoreRow("§7Set your own items on auction")
                        .addLoreRow("§7for other players to purchase.")
                        .addLoreRow(" ")
                        .addLoreRow("§eClick to become rich!")
                        .build(), 15)
                .build();
    }

    @Override
    public @NotNull String getName() {
        return "Auction Hause";
    }
}
