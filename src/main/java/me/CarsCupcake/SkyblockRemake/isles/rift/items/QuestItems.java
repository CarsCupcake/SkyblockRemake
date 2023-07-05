package me.CarsCupcake.SkyblockRemake.isles.rift.items;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import org.bukkit.Material;

public class QuestItems {
    public static ItemManager VERY_SCIENTIFIC_PAPER;
    public static void init(){
        VERY_SCIENTIFIC_PAPER = new ItemManager("Very Scientific Paper", "VERY_SCIENTIFIC_PAPER", ItemType.Non, Material.FILLED_MAP, ItemRarity.RARE);
        VERY_SCIENTIFIC_PAPER.setMapImagePath("assets/textures/rift/veryScientificPaper/img.png");
    }
}
