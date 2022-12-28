package me.CarsCupcake.SkyblockRemake.Items.ItemRegistration;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Stats;
import org.bukkit.Color;
import org.bukkit.Material;

public class GiantsArmorSets {
    @ItemHandler
    public ItemManager jollyPingHelmet(){
        ItemManager manager = new ItemManager("Jolly Pink Helmet", "JOLLY_PINK_HELMET", ItemType.Helmet, null, null, null, null, 0, 0, 0, 0, Material.LEATHER_HELMET, Color.PURPLE, ItemRarity.EPIC);
        manager.setStat(Stats.Health, 100);
        manager.setStat(Stats.Defense, 100);
        return manager;
    }
}
