package me.CarsCupcake.SkyblockRemake.Slayer.spider.item;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import org.bukkit.Material;

public enum SpiderSlayerItems {
    TarantularWeb("TARANTULA_WEB");
    private final String id;

    SpiderSlayerItems(String str) {
        id = str;
    }

    public ItemManager getItem() {
        return Items.SkyblockItems.get(id);
    }

    public static void init() {
        ItemManager manager = new ItemManager("Tarantula Web", TarantularWeb.id, ItemType.Non, Material.STRING, ItemRarity.UNCOMMON);
        manager.setNpcSellPrice(1);
        manager.setGlint();
    }
}
