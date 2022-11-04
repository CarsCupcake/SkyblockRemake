package me.CarsCupcake.SkyblockRemake.Slayer.Enderman;

import me.CarsCupcake.SkyblockRemake.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import org.bukkit.Material;

public class EndermanSlayerItems {
    public EndermanSlayerItems(){
        t1();
    }
    private void t1(){
        ItemManager manager = NullSphere();
        Items.SkyblockItems.put(manager.itemID, manager);
    }
    public static ItemManager NullSphere(){
        ItemManager manager = new ItemManager("Null Sphere", "NULL_SPHERE", ItemType.Non, null, null, null, null, 0, 0,0, 0, Material.FIREWORK_STAR, ItemRarity.UNCOMMON);
        manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
        return manager;
    }
}
