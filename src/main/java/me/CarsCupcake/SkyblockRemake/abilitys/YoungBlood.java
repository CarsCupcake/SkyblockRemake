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
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.UUID;

public class YoungBlood implements FullSetBonus, Listener {
    private SkyblockPlayer player;
    private static final ArrayList<SkyblockPlayer> actives = new ArrayList<>();
    @Override
    public void start() {
        actives.add(player);
    }

    @Override
    public void stop() {
        actives.remove(player);
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
        this.player = player;
    }

    @Override
    public Bonuses getBonus() {
        return Bonuses.YoungBlood;
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
        ItemManager manager = new ItemManager("Young Dragon Fragment", "YOUNG_FRAGMENT", ItemType.Non, null, null, null, null, 0, 0,0,0, ItemRarity.EPIC
                ,"https://textures.minecraft.net/texture/4b5bd6b64e8bd6c58f5cd1e79a5502d4448bafc006d2fe0568f6a0d6b86d449e", UUID.fromString("86541f16-3bdf-4d08-871a-dcdf8ba373f5"));
        return manager;
    }
    private static ItemManager protectorHelmet(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Gain §a+70§7 Walk Speed while");
        abilityLore.add("§7you are above §a50% §7HP.");
        abilityLore.add("§8+100 Walk Speed Cap");
        ItemManager manager = new ItemManager("Young Dragon Helmet", "YOUNG_DRAGON_HELMET", ItemType.Helmet, null,
                "Young Blood", null, null, 0, 0,0,0, ItemRarity.LEGENDARY,
                "https://textures.minecraft.net/texture/ecf6ae4d827b77e33e4310c7fbc8683a81feb2b52ad1f2604c61d336c57a19c0");
        manager.setNpcSellPrice(100000);
        manager.setStat(Stats.Health, 70);
        manager.setStat(Stats.Defense, 110);
        manager.setStat(Stats.Speed, 20);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));
        AbilityLore lore = new AbilityLore(abilityLore);
        manager.setFullSetBonus(Bonuses.YoungBlood, "Young Blood");
        manager.setAbilityLore(lore);
        manager.setUnstackeble(true);

        SkyblockShapedRecipe recipe = new SkyblockShapedRecipe("YOUNG_DRAGON_HELMET", manager, 1);
        ShapeEncoder encoder = new ShapeEncoder("sss", "s s", "   ");
        encoder.setKey('s', new CraftingObject(EndItems.Items.YoungDragonFragment.getItem(), 10));
        recipe.setRecipe(encoder.encode());
        SkyblockRecipe.recipes.add(recipe);

        return manager;
    }
    private static ItemManager protectorChestplate(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Gain §a+70§7 Walk Speed while");
        abilityLore.add("§7you are above §a50% §7HP.");
        abilityLore.add("§8+100 Walk Speed Cap");
        ItemManager manager = new ItemManager("Young Dragon Chestplate", "YOUNG_DRAGON_CHESTPLATE", ItemType.Chestplate, null,
                "Young Blood", null, null, 0, 0,0,0, Material.LEATHER_CHESTPLATE, Color.fromRGB(0x29F0E9),ItemRarity.LEGENDARY);
        manager.setNpcSellPrice(100000);
        manager.setStat(Stats.Health, 120);
        manager.setStat(Stats.Defense, 160);
        manager.setStat(Stats.Speed, 20);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));
        AbilityLore lore = new AbilityLore(abilityLore);
        manager.setFullSetBonus(Bonuses.YoungBlood, "Young Blood");
        manager.setAbilityLore(lore);
        manager.setUnstackeble(true);

        SkyblockShapedRecipe recipe = new SkyblockShapedRecipe("YOUNG_DRAGON_CHESTPLATE", manager, 1);
        ShapeEncoder encoder = new ShapeEncoder("s s", "sss", "sss");
        encoder.setKey('s', new CraftingObject(EndItems.Items.YoungDragonFragment.getItem(), 10));
        recipe.setRecipe(encoder.encode());
        SkyblockRecipe.recipes.add(recipe);

        return manager;
    }
    private static ItemManager protectorLeggings(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Gain §a+70§7 Walk Speed while");
        abilityLore.add("§7you are above §a50% §7HP.");
        abilityLore.add("§8+100 Walk Speed Cap");
        ItemManager manager = new ItemManager("Young Dragon Leggings", "YOUNG_DRAGON_LEGGINGS", ItemType.Leggings, null,
                "Young Blood", null, null, 0, 0,0,0,Material.LEATHER_LEGGINGS, Color.fromRGB(0xDDE4F0),ItemRarity.LEGENDARY);
        manager.setNpcSellPrice(100000);
        manager.setStat(Stats.Health, 100);
        manager.setStat(Stats.Defense, 140);
        manager.setStat(Stats.Speed, 20);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));
        AbilityLore lore = new AbilityLore(abilityLore);
        manager.setFullSetBonus(Bonuses.YoungBlood, "Young Blood");
        manager.setAbilityLore(lore);
        manager.setUnstackeble(true);

        SkyblockShapedRecipe recipe = new SkyblockShapedRecipe("YOUNG_DRAGON_LEGGINGS", manager, 1);
        ShapeEncoder encoder = new ShapeEncoder("sss", "s s", "s s");
        encoder.setKey('s', new CraftingObject(EndItems.Items.YoungDragonFragment.getItem(), 10));
        recipe.setRecipe(encoder.encode());
        SkyblockRecipe.recipes.add(recipe);

        return manager;
    }
    private static ItemManager protectorBoots(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Gain §a+70§7 Walk Speed while");
        abilityLore.add("§7you are above §a50% §7HP.");
        abilityLore.add("§8+100 Walk Speed Cap");
        ItemManager manager = new ItemManager("Young Dragon Boots", "YOUNG_DRAGON_BOOTS", ItemType.Boots, null,
                "Young Blood", null, null, 0, 0,0,0,Material.LEATHER_BOOTS, Color.fromRGB(0xDDE4F0),ItemRarity.LEGENDARY);
        manager.setNpcSellPrice(100000);
        manager.setStat(Stats.Health, 60);
        manager.setStat(Stats.Defense, 90);
        manager.setStat(Stats.Speed, 20);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));
        AbilityLore lore = new AbilityLore(abilityLore);
        manager.setFullSetBonus(Bonuses.YoungBlood, "Young Blood");
        manager.setAbilityLore(lore);
        manager.setUnstackeble(true);

        SkyblockShapedRecipe recipe = new SkyblockShapedRecipe("YOUNG_DRAGON_BOOTS", manager, 1);
        ShapeEncoder encoder = new ShapeEncoder("   ", "s s", "s s");
        encoder.setKey('s', new CraftingObject(EndItems.Items.YoungDragonFragment.getItem(), 10));
        recipe.setRecipe(encoder.encode());
        SkyblockRecipe.recipes.add(recipe);

        return manager;
    }

}
