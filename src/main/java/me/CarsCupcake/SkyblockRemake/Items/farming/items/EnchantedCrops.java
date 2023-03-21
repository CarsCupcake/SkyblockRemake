package me.CarsCupcake.SkyblockRemake.Items.farming.items;

import me.CarsCupcake.SkyblockRemake.Items.Crafting.CraftingObject;
import me.CarsCupcake.SkyblockRemake.Items.Crafting.SkyblockShapelessRecipe;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import org.bukkit.Material;
import static me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems;
public class EnchantedCrops {
    public EnchantedCrops(){
        ItemManager manager = new ItemManager("Enchanted Melon", "ENCHANTED_MELON", ItemType.Non, Material.MELON_SLICE, ItemRarity.UNCOMMON);
        manager.setNpcSellPrice(160);
        manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
        SkyblockItems.put(manager.itemID, manager);

        SkyblockShapelessRecipe shapelessRecipe = new SkyblockShapelessRecipe("", manager);
        shapelessRecipe.addIngredient(new CraftingObject(SkyblockItems.get(Material.MELON_SLICE + ""), 160));
        shapelessRecipe.register();

        manager = new ItemManager("Enchanted Melon Block", "ENCHANTED_MELON_BLOCK", ItemType.Non, Material.MELON, ItemRarity.UNCOMMON);
        manager.setNpcSellPrice(25600);
        manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
        SkyblockItems.put(manager.itemID, manager);

        shapelessRecipe = new SkyblockShapelessRecipe("", manager);
        shapelessRecipe.addIngredient(new CraftingObject(SkyblockItems.get("ENCHANTED_MELON"), 160));
        shapelessRecipe.register();

        manager = new ItemManager("Enchanted Pumpkin", "ENCHANTED_PUMPKIN", ItemType.Non, Material.PUMPKIN, ItemRarity.UNCOMMON);
        manager.setNpcSellPrice(640);
        manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
        SkyblockItems.put(manager.itemID, manager);

        shapelessRecipe = new SkyblockShapelessRecipe("", manager);
        shapelessRecipe.addIngredient(new CraftingObject(SkyblockItems.get(Material.PUMPKIN + ""), 160));
        shapelessRecipe.register();
    }
}
