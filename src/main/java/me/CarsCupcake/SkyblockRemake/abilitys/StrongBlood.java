package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Crafting.CraftingObject;
import me.CarsCupcake.SkyblockRemake.Crafting.ShapeEncoder;
import me.CarsCupcake.SkyblockRemake.Crafting.SkyblockRecipe;
import me.CarsCupcake.SkyblockRemake.Crafting.SkyblockShapedRecipe;
import me.CarsCupcake.SkyblockRemake.End.EndItems;
import me.CarsCupcake.SkyblockRemake.Gemstones.GemstoneSlot;
import me.CarsCupcake.SkyblockRemake.Gemstones.SlotType;
import me.CarsCupcake.SkyblockRemake.Items.*;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Stats;
import org.bukkit.Color;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.UUID;

public class StrongBlood implements FullSetBonus {
    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public int getPieces() {
        return 4;
    }

    @Override
    public int getMaxPieces() {
        return 4;
    }

    @Override
    public void setPlayer(SkyblockPlayer player) {

    }

    @Override
    public Bonuses getBonus() {
        return Bonuses.StrongBlood;
    }
    public static void addItems(){
        ItemManager manager = protectorDragonFragment();
        Items.SkyblockItems.put(manager.itemID, manager);
        manager = protectorLeggings();
        Items.SkyblockItems.put(manager.itemID, manager);
        manager = protectorHelmet();
        Items.SkyblockItems.put(manager.itemID, manager);
        manager = protectorChestplate();
        Items.SkyblockItems.put(manager.itemID, manager);
        manager = protectorBoots();
        Items.SkyblockItems.put(manager.itemID, manager);
    }

    private static ItemManager protectorDragonFragment(){
        ItemManager manager = new ItemManager("Strong Dragon Fragment", "STRONG_FRAGMENT", ItemType.Non, null, null, null, null, 0, 0,0,0, ItemRarity.EPIC
                ,"https://textures.minecraft.net/texture/6ee32fbd4c7b03b869078aa1f493a390e6e13b461d613707eafb326dbcd2b4b5", UUID.fromString("86541f16-3bdf-4d08-871a-dcdf8ba373f5"));
        return manager;
    }
    private static ItemManager protectorHelmet(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Improves §9Aspect of the End");
        abilityLore.add("§8⋗ §c+75 Damage");
        abilityLore.add(" ");
        abilityLore.add("§7Instant Transmission:");
        abilityLore.add("§8⋗ §a+2 §7teleport range");
        abilityLore.add("§8⋗ §a+3 §7seconds");
        abilityLore.add("§8⋗ §c+5❁ Strength §7on cast");
        ItemManager manager = new ItemManager("Strong Dragon Helmet", "STRONG_DRAGON_HELMET", ItemType.Helmet, null,
                "Strong Blood", null, null, 0, 0,0,0, ItemRarity.LEGENDARY,
                "https://textures.minecraft.net/texture/efde09603b0225b9d24a73a0d3f3e3af29058d448ccd7ce5c67cd02fab0ff682");
        manager.setNpcSellPrice(100000);
        manager.setStat(Stats.Health, 70);
        manager.setStat(Stats.Defense, 110);
        manager.setStat(Stats.Strength, 25);
        manager.addSlot(new GemstoneSlot(SlotType.Jasper));
        AbilityLore lore = new AbilityLore(abilityLore);
        manager.setFullSetBonus(Bonuses.StrongBlood, "Strong Blood");
        manager.setAbilityLore(lore);
        manager.setUnstackeble(true);

        SkyblockShapedRecipe recipe = new SkyblockShapedRecipe("STRONG_DRAGON_HELMET", manager, 1);
        ShapeEncoder encoder = new ShapeEncoder("sss", "s s", "   ");
        encoder.setKey('s', new CraftingObject(EndItems.Items.StrongDragonFragment.getItem(), 10));
        recipe.setRecipe(encoder.encode());
        SkyblockRecipe.recipes.add( recipe);

        return manager;
    }
    private static ItemManager protectorChestplate(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Improves §9Aspect of the End");
        abilityLore.add("§8⋗ §c+75 Damage");
        abilityLore.add(" ");
        abilityLore.add("§7Instant Transmission:");
        abilityLore.add("§8⋗ §a+2 §7teleport range");
        abilityLore.add("§8⋗ §a+3 §7seconds");
        abilityLore.add("§8⋗ §c+5❁ Strength §7on cast");
        ItemManager manager = new ItemManager("Strong Dragon Chestplate", "STRONG_DRAGON_CHESTPLATE", ItemType.Chestplate, null,
                "Strong Blood", null, null, 0, 0,0,0, Material.LEATHER_CHESTPLATE, Color.fromRGB(0xD91E41),ItemRarity.LEGENDARY);
        manager.setNpcSellPrice(100000);
        manager.setStat(Stats.Health, 120);
        manager.setStat(Stats.Defense, 160);
        manager.setStat(Stats.Strength, 25);
        manager.addSlot(new GemstoneSlot(SlotType.Jasper));
        AbilityLore lore = new AbilityLore(abilityLore);
        manager.setFullSetBonus(Bonuses.StrongBlood, "Strong Blood");
        manager.setAbilityLore(lore);
        manager.setUnstackeble(true);

        SkyblockShapedRecipe recipe = new SkyblockShapedRecipe("STRONG_DRAGON_CHESTPLATE", manager, 1);
        ShapeEncoder encoder = new ShapeEncoder("s s", "sss", "sss");
        encoder.setKey('s', new CraftingObject(EndItems.Items.StrongDragonFragment.getItem(), 10));
        recipe.setRecipe(encoder.encode());
        SkyblockRecipe.recipes.add(recipe);

        return manager;
    }
    private static ItemManager protectorLeggings(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Improves §9Aspect of the End");
        abilityLore.add("§8⋗ §c+75 Damage");
        abilityLore.add(" ");
        abilityLore.add("§7Instant Transmission:");
        abilityLore.add("§8⋗ §a+2 §7teleport range");
        abilityLore.add("§8⋗ §a+3 §7seconds");
        abilityLore.add("§8⋗ §c+5❁ Strength §7on cast");
        ItemManager manager = new ItemManager("Strong Dragon Leggings", "STRONG_DRAGON_LEGGINGS", ItemType.Leggings, null,
                "Strong Blood", null, null, 0, 0,0,0,Material.LEATHER_LEGGINGS, Color.fromRGB(0xE09419),ItemRarity.LEGENDARY);
        manager.setNpcSellPrice(100000);
        manager.setStat(Stats.Health, 100);
        manager.setStat(Stats.Defense, 140);
        manager.setStat(Stats.Strength, 25);
        manager.addSlot(new GemstoneSlot(SlotType.Jasper));
        AbilityLore lore = new AbilityLore(abilityLore);
        manager.setFullSetBonus(Bonuses.StrongBlood, "Strong Blood");
        manager.setAbilityLore(lore);
        manager.setUnstackeble(true);

        SkyblockShapedRecipe recipe = new SkyblockShapedRecipe("STRONG_DRAGON_LEGGINGS", manager, 1);
        ShapeEncoder encoder = new ShapeEncoder("sss", "s s", "s s");
        encoder.setKey('s', new CraftingObject(EndItems.Items.StrongDragonFragment.getItem(), 10));
        recipe.setRecipe(encoder.encode());
        SkyblockRecipe.recipes.add(recipe);

        return manager;
    }
    private static ItemManager protectorBoots(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Improves §9Aspect of the End");
        abilityLore.add("§8⋗ §c+75 Damage");
        abilityLore.add(" ");
        abilityLore.add("§7Instant Transmission:");
        abilityLore.add("§8⋗ §a+2 §7teleport range");
        abilityLore.add("§8⋗ §a+3 §7seconds");
        abilityLore.add("§8⋗ §c+5❁ Strength §7on cast");
        ItemManager manager = new ItemManager("Strong Dragon Boots", "STRONG_DRAGON_BOOTS", ItemType.Boots, null,
                "Strong Blood", null, null, 0, 0,0,0,Material.LEATHER_BOOTS, Color.fromRGB(0xF0D124),ItemRarity.LEGENDARY);
        manager.setNpcSellPrice(100000);
        manager.setStat(Stats.Health, 60);
        manager.setStat(Stats.Defense, 90);
        manager.setStat(Stats.Strength, 25);
        manager.addSlot(new GemstoneSlot(SlotType.Jasper));
        AbilityLore lore = new AbilityLore(abilityLore);
        manager.setFullSetBonus(Bonuses.StrongBlood, "Strong Blood");
        manager.setAbilityLore(lore);
        manager.setUnstackeble(true);

        SkyblockShapedRecipe recipe = new SkyblockShapedRecipe("STRONG_DRAGON_BOOTS", manager, 1);
        ShapeEncoder encoder = new ShapeEncoder("   ", "s s", "s s");
        encoder.setKey('s', new CraftingObject(EndItems.Items.StrongDragonFragment.getItem(), 10));
        recipe.setRecipe(encoder.encode());
        SkyblockRecipe.recipes.add(recipe);

        return manager;
    }
}
