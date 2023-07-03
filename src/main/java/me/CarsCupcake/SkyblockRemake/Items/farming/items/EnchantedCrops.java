package me.CarsCupcake.SkyblockRemake.Items.farming.items;

import me.CarsCupcake.SkyblockRemake.Items.Crafting.CraftingObject;
import me.CarsCupcake.SkyblockRemake.Items.Crafting.SkyblockShapelessRecipe;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.abilities.SuperCompactor;
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
        SuperCompactor.registerRecipe(new SuperCompactor.SuperCompactorRecipe(Material.MELON_SLICE + "", manager.itemID, 160));

        manager = new ItemManager("Enchanted Melon Block", "ENCHANTED_MELON_BLOCK", ItemType.Non, Material.MELON, ItemRarity.UNCOMMON);
        manager.setNpcSellPrice(25600);
        manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
        SkyblockItems.put(manager.itemID, manager);
        shapelessRecipe = new SkyblockShapelessRecipe("", manager);
        shapelessRecipe.addIngredient(new CraftingObject(SkyblockItems.get("ENCHANTED_MELON"), 160));
        shapelessRecipe.register();
        SuperCompactor.registerRecipe(new SuperCompactor.SuperCompactorRecipe("ENCHANTED_MELON", manager.itemID, 160));

        manager = new ItemManager("Enchanted Pumpkin", "ENCHANTED_PUMPKIN", ItemType.Non, Material.PUMPKIN, ItemRarity.UNCOMMON);
        manager.setNpcSellPrice(640);
        manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
        SkyblockItems.put(manager.itemID, manager);
        shapelessRecipe = new SkyblockShapelessRecipe("", manager);
        shapelessRecipe.addIngredient(new CraftingObject(SkyblockItems.get(Material.PUMPKIN + ""), 160));
        shapelessRecipe.register();
        SuperCompactor.registerRecipe(new SuperCompactor.SuperCompactorRecipe(Material.PUMPKIN + "", manager.itemID, 160));

        manager = new ItemManager("Enchanted Nether Wart", "ENCHANTED_NETHER_STALK", ItemType.Non, Material.NETHER_WART, ItemRarity.UNCOMMON);
        manager.setNpcSellPrice(320);
        manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
        SkyblockItems.put(manager.itemID, manager);
        shapelessRecipe = new SkyblockShapelessRecipe("", manager);
        shapelessRecipe.addIngredient(new CraftingObject(SkyblockItems.get(Material.NETHER_WART + ""), 160));
        shapelessRecipe.register();
        SuperCompactor.registerRecipe(new SuperCompactor.SuperCompactorRecipe(Material.NETHER_WART + "", manager.itemID, 160));

        manager = new ItemManager("Mutant Nether Wart", "MUTANT_NETHER_STALK", ItemType.Non, ItemRarity.RARE, "http://textures.minecraft.net/texture/111a3cec7aaf904212ccf93bb67a3caf3d649783ba90b8b60bb63c7687eb39f");
        manager.setNpcSellPrice(51200);
        manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
        SkyblockItems.put(manager.itemID, manager);
        shapelessRecipe = new SkyblockShapelessRecipe("", manager);
        shapelessRecipe.addIngredient(new CraftingObject(SkyblockItems.get("ENCHANTED_NETHER_STALK"), 160));
        shapelessRecipe.register();
        SuperCompactor.registerRecipe(new SuperCompactor.SuperCompactorRecipe("ENCHANTED_NETHER_STALK", manager.itemID, 160));

        manager = new ItemManager("Enchanted Sugar", "ENCHANTED_SUGAR", ItemType.Non, Material.SUGAR, ItemRarity.UNCOMMON);
        manager.setNpcSellPrice(320);
        manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
        SkyblockItems.put(manager.itemID, manager);
        shapelessRecipe = new SkyblockShapelessRecipe("", manager);
        shapelessRecipe.addIngredient(new CraftingObject(SkyblockItems.get(Material.SUGAR_CANE + ""), 160));
        shapelessRecipe.register();
        SuperCompactor.registerRecipe(new SuperCompactor.SuperCompactorRecipe(Material.SUGAR_CANE + "", manager.itemID, 160));

        manager = new ItemManager("Enchanted Sugar Cane", "ENCHANTED_SUGAR_CANE", ItemType.Non, Material.SUGAR_CANE, ItemRarity.RARE);
        manager.setNpcSellPrice(51200);
        manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
        SkyblockItems.put(manager.itemID, manager);
        shapelessRecipe = new SkyblockShapelessRecipe("", manager);
        shapelessRecipe.addIngredient(new CraftingObject(SkyblockItems.get("ENCHANTED_SUGAR"), 160));
        shapelessRecipe.register();
        SuperCompactor.registerRecipe(new SuperCompactor.SuperCompactorRecipe("ENCHANTED_SUGAR", manager.itemID, 160));

        manager = new ItemManager("Enchanted Potato", "ENCHANTED_POTATO", ItemType.Non, Material.POTATO, ItemRarity.UNCOMMON);
        manager.setNpcSellPrice(160);
        manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
        SkyblockItems.put(manager.itemID, manager);
        shapelessRecipe = new SkyblockShapelessRecipe("", manager);
        shapelessRecipe.addIngredient(new CraftingObject(SkyblockItems.get(Material.POTATO + ""), 160));
        shapelessRecipe.register();
        SuperCompactor.registerRecipe(new SuperCompactor.SuperCompactorRecipe(Material.POTATO + "", manager.itemID, 160));
    }
}
