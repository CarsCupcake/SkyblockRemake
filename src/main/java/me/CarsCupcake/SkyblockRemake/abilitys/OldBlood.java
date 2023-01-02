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

public class OldBlood implements FullSetBonus {
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
        return Bonuses.OldBlood;
    }
    public static void addItems(){
        ItemManager manager = protectorDragonFragment();
        me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.put(manager.itemID, manager);
        manager = protectorLeggings();
        me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.put(manager.itemID, manager);
        manager = protectorHelmet();
        me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.put(manager.itemID, manager);
        manager = protectorChestplate();
        me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.put(manager.itemID, manager);
        manager = protectorBoots();
        me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.put(manager.itemID, manager);
    }

    private static ItemManager protectorDragonFragment(){
        ItemManager manager = new ItemManager("Old Dragon Fragment", "OLD_FRAGMENT", ItemType.Non, null, null, null, null, 0, 0,0,0, ItemRarity.EPIC
                ,"https://textures.minecraft.net/texture/7aa09ad177fbccc53fa316cc04bdd2c9366baed889df76c5a29defea8170def5", UUID.fromString("86541f16-3bdf-4d08-871a-dcdf8ba373f5"));
        return manager;
    }
    private static ItemManager protectorHelmet(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Increases the strength of");
        abilityLore.add("§9Growth§7, §9Protection§7,");
        abilityLore.add("§9Feather Falling§7, §9Sugar");
        abilityLore.add("§9Rush§7, and §9True Protection");
        abilityLore.add("§7while worn.");
        ItemManager manager = new ItemManager("Old Dragon Helmet", "OLD_DRAGON_HELMET", ItemType.Helmet, null,
                "Old Blood", null, null, 0, 0,0,0, ItemRarity.LEGENDARY,
                "https://textures.minecraft.net/texture/59e9e5600410c1d0254474a81fecfb3885c1cf6f504190d856f0ec7c9f055c42");
        manager.setNpcSellPrice(100000);
        manager.setStat(Stats.Health, 90);
        manager.setStat(Stats.Defense, 110);
        manager.addSlot(new GemstoneSlot(SlotType.Defensive));
        AbilityLore lore = new AbilityLore(abilityLore);
        manager.setFullSetBonus(Bonuses.OldBlood, "Old Blood");
        manager.setAbilityLore(lore);
        manager.setUnstackeble(true);

        SkyblockShapedRecipe recipe = new SkyblockShapedRecipe("OLD_DRAGON_HELMET", manager, 1);
        ShapeEncoder encoder = new ShapeEncoder("sss", "s s", "   ");
        encoder.setKey('s', new CraftingObject(EndItems.Items.OldDragonFragment.getItem(), 10));
        recipe.setRecipe(encoder.encode());
        SkyblockRecipe.recipes.add(recipe);

        return manager;
    }
    private static ItemManager protectorChestplate(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Increases the strength of");
        abilityLore.add("§9Growth§7, §9Protection§7,");
        abilityLore.add("§9Feather Falling§7, §9Sugar");
        abilityLore.add("§9Rush§7, and §9True Protection");
        abilityLore.add("§7while worn.");
        ItemManager manager = new ItemManager("Old Dragon Chestplate", "OLD_DRAGON_CHESTPLATE", ItemType.Chestplate, null,
                "Old Blood", null, null, 0, 0,0,0, Material.LEATHER_CHESTPLATE, Color.fromRGB(0xF0E6AA),ItemRarity.LEGENDARY);
        manager.setNpcSellPrice(100000);
        manager.setStat(Stats.Health, 150);
        manager.setStat(Stats.Defense, 160);
        manager.addSlot(new GemstoneSlot(SlotType.Defensive));
        AbilityLore lore = new AbilityLore(abilityLore);
        manager.setFullSetBonus(Bonuses.OldBlood, "Old Blood");
        manager.setAbilityLore(lore);
        manager.setUnstackeble(true);

        SkyblockShapedRecipe recipe = new SkyblockShapedRecipe("OLD_DRAGON_CHESTPLATE", manager, 1);
        ShapeEncoder encoder = new ShapeEncoder("s s", "sss", "sss");
        encoder.setKey('s', new CraftingObject(EndItems.Items.OldDragonFragment.getItem(), 10));
        recipe.setRecipe(encoder.encode());
        SkyblockRecipe.recipes.add(recipe);

        return manager;
    }
    private static ItemManager protectorLeggings(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Increases the strength of");
        abilityLore.add("§9Growth§7, §9Protection§7,");
        abilityLore.add("§9Feather Falling§7, §9Sugar");
        abilityLore.add("§9Rush§7, and §9True Protection");
        abilityLore.add("§7while worn.");
        ItemManager manager = new ItemManager("Old Dragon Leggings", "OLD_DRAGON_LEGGINGS", ItemType.Leggings, null,
                "Old Blood", null, null, 0, 0,0,0,Material.LEATHER_LEGGINGS, Color.fromRGB(0xF0E6AA),ItemRarity.LEGENDARY);
        manager.setNpcSellPrice(100000);
        manager.setStat(Stats.Health, 130);
        manager.setStat(Stats.Defense, 140);
        manager.addSlot(new GemstoneSlot(SlotType.Defensive));
        AbilityLore lore = new AbilityLore(abilityLore);
        manager.setFullSetBonus(Bonuses.OldBlood, "Old Blood");
        manager.setAbilityLore(lore);
        manager.setUnstackeble(true);

        SkyblockShapedRecipe recipe = new SkyblockShapedRecipe("OLD_DRAGON_LEGGINGS", manager, 1);
        ShapeEncoder encoder = new ShapeEncoder("sss", "s s", "s s");
        encoder.setKey('s', new CraftingObject(EndItems.Items.OldDragonFragment.getItem(), 10));
        recipe.setRecipe(encoder.encode());
        SkyblockRecipe.recipes.add(recipe);

        return manager;
    }
    private static ItemManager protectorBoots(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Increases the strength of");
        abilityLore.add("§9Growth§7, §9Protection§7,");
        abilityLore.add("§9Feather Falling§7, §9Sugar");
        abilityLore.add("§9Rush§7, and §9True Protection");
        abilityLore.add("§7while worn.");
        ItemManager manager = new ItemManager("Old Dragon Boots", "OLD_DRAGON_BOOTS", ItemType.Boots, null,
                "Old Blood", null, null, 0, 0,0,0,Material.LEATHER_BOOTS, Color.fromRGB(0xF0E6AA),ItemRarity.LEGENDARY);
        manager.setNpcSellPrice(100000);
        manager.setStat(Stats.Health, 80);
        manager.setStat(Stats.Defense, 90);
        manager.addSlot(new GemstoneSlot(SlotType.Defensive));
        AbilityLore lore = new AbilityLore(abilityLore);
        manager.setFullSetBonus(Bonuses.OldBlood, "Old Blood");
        manager.setAbilityLore(lore);
        manager.setUnstackeble(true);

        SkyblockShapedRecipe recipe = new SkyblockShapedRecipe("OLD_DRAGON_BOOTS", manager, 1);
        ShapeEncoder encoder = new ShapeEncoder("   ", "s s", "s s");
        encoder.setKey('s', new CraftingObject(EndItems.Items.OldDragonFragment.getItem(), 10));
        recipe.setRecipe(encoder.encode());
        SkyblockRecipe.recipes.add(recipe);

        return manager;
    }
}
