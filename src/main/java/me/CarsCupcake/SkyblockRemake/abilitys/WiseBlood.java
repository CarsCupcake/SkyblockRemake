package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.API.ItemEvents.GetStatFromItemEvent;
import me.CarsCupcake.SkyblockRemake.Crafting.CraftingObject;
import me.CarsCupcake.SkyblockRemake.Crafting.ShapeEncoder;
import me.CarsCupcake.SkyblockRemake.Crafting.SkyblockRecipe;
import me.CarsCupcake.SkyblockRemake.Crafting.SkyblockShapedRecipe;
import me.CarsCupcake.SkyblockRemake.End.EndItems;
import me.CarsCupcake.SkyblockRemake.Gemstones.GemstoneSlot;
import me.CarsCupcake.SkyblockRemake.Gemstones.SlotType;
import me.CarsCupcake.SkyblockRemake.Items.*;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Stats;
import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.UUID;

public class WiseBlood implements FullSetBonus, Listener {
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
        return Bonuses.WiseBlood;
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
        ItemManager manager = new ItemManager("Wise Dragon Fragment", "WISE_FRAGMENT", ItemType.Non, null, null, null, null, 0, 0,0,0, ItemRarity.EPIC
                ,"https://textures.minecraft.net/texture/1d7620b2e4934963bb12508310d05494c067dc33e008cecf2cd7b4549654fab3", UUID.fromString("86541f16-3bdf-4d08-871a-dcdf8ba373f5"));
        return manager;
    }
    private static ItemManager protectorHelmet(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Abilities have §a2/3 §7of the");
        abilityLore.add("§7mana cost.");
        ItemManager manager = new ItemManager("Wise Dragon Helmet", "WISE_DRAGON_HELMET", ItemType.Helmet, null,
                "Wise Blood", null, null, 0, 0,0,0, ItemRarity.LEGENDARY,
                "https://textures.minecraft.net/texture/5a2984cf07c48da9724816a8ff0864bc68bce694ce8bd6db2112b6ba031070de");
        manager.setNpcSellPrice(100000);
        manager.setStat(Stats.Health, 70);
        manager.setStat(Stats.Defense, 110);
        manager.setStat(Stats.Inteligence, 125);
        manager.addSlot(new GemstoneSlot(SlotType.Sapphire));
        AbilityLore lore = new AbilityLore(abilityLore);
        manager.setFullSetBonus(Bonuses.WiseBlood, "Wise Blood");
        manager.setAbilityLore(lore);
        manager.setUnstackeble(true);

        SkyblockShapedRecipe recipe = new SkyblockShapedRecipe("WISE_DRAGON_HELMET", manager, 1);
        ShapeEncoder encoder = new ShapeEncoder("sss", "s s", "   ");
        encoder.setKey('s', new CraftingObject(EndItems.Items.WiseDragonFragment.getItem(), 10));
        recipe.setRecipe(encoder.encode());
        SkyblockRecipe.recipes.add(recipe);

        return manager;
    }
    private static ItemManager protectorChestplate(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Abilities have §a2/3 §7of the");
        abilityLore.add("§7mana cost.");
        ItemManager manager = new ItemManager("Wise Dragon Chestplate", "WISE_DRAGON_CHESTPLATE", ItemType.Chestplate, null,
                "Wise Blood", null, null, 0, 0,0,0, Material.LEATHER_CHESTPLATE, Color.fromRGB(0x29F0E9),ItemRarity.LEGENDARY);
        manager.setNpcSellPrice(100000);
        manager.setStat(Stats.Health, 120);
        manager.setStat(Stats.Defense, 160);
        manager.setStat(Stats.Inteligence, 75);
        manager.addSlot(new GemstoneSlot(SlotType.Sapphire));
        AbilityLore lore = new AbilityLore(abilityLore);
        manager.setFullSetBonus(Bonuses.WiseBlood, "Wise Blood");
        manager.setAbilityLore(lore);
        manager.setUnstackeble(true);

        SkyblockShapedRecipe recipe = new SkyblockShapedRecipe("WISE_DRAGON_CHESTPLATE", manager, 1);
        ShapeEncoder encoder = new ShapeEncoder("s s", "sss", "sss");
        encoder.setKey('s', new CraftingObject(EndItems.Items.WiseDragonFragment.getItem(), 10));
        recipe.setRecipe(encoder.encode());
        SkyblockRecipe.recipes.add(recipe);

        return manager;
    }
    private static ItemManager protectorLeggings(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Abilities have §a2/3 §7of the");
        abilityLore.add("§7mana cost.");
        ItemManager manager = new ItemManager("Wise Dragon Leggings", "WISE_DRAGON_LEGGINGS", ItemType.Leggings, null,
                "Wise Blood", null, null, 0, 0,0,0,Material.LEATHER_LEGGINGS, Color.fromRGB(0x29F0E9),ItemRarity.LEGENDARY);
        manager.setNpcSellPrice(100000);
        manager.setStat(Stats.Health, 100);
        manager.setStat(Stats.Defense, 140);
        manager.setStat(Stats.Inteligence, 75);
        manager.addSlot(new GemstoneSlot(SlotType.Sapphire));
        AbilityLore lore = new AbilityLore(abilityLore);
        manager.setFullSetBonus(Bonuses.WiseBlood, "Wise Blood");
        manager.setAbilityLore(lore);
        manager.setUnstackeble(true);

        SkyblockShapedRecipe recipe = new SkyblockShapedRecipe("WISE_DRAGON_LEGGINGS", manager, 1);
        ShapeEncoder encoder = new ShapeEncoder("sss", "s s", "s s");
        encoder.setKey('s', new CraftingObject(EndItems.Items.WiseDragonFragment.getItem(), 10));
        recipe.setRecipe(encoder.encode());
        SkyblockRecipe.recipes.add(recipe);

        return manager;
    }
    private static ItemManager protectorBoots(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Abilities have §a2/3 §7of the");
        abilityLore.add("§7mana cost.");
        ItemManager manager = new ItemManager("Wise Dragon Boots", "WISE_DRAGON_BOOTS", ItemType.Boots, null,
                "Wise Blood", null, null, 0, 0,0,0,Material.LEATHER_BOOTS, Color.fromRGB(0x29F0E9),ItemRarity.LEGENDARY);
        manager.setNpcSellPrice(100000);
        manager.setStat(Stats.Health, 60);
        manager.setStat(Stats.Defense, 90);
        manager.setStat(Stats.Inteligence, 75);
        manager.addSlot(new GemstoneSlot(SlotType.Sapphire));
        AbilityLore lore = new AbilityLore(abilityLore);
        manager.setFullSetBonus(Bonuses.WiseBlood, "Wise Blood");
        manager.setAbilityLore(lore);
        manager.setUnstackeble(true);

        SkyblockShapedRecipe recipe = new SkyblockShapedRecipe("WISE_DRAGON_BOOTS", manager, 1);
        ShapeEncoder encoder = new ShapeEncoder("   ", "s s", "s s");
        encoder.setKey('s', new CraftingObject(EndItems.Items.WiseDragonFragment.getItem(), 10));
        recipe.setRecipe(encoder.encode());
        SkyblockRecipe.recipes.add(recipe);

        return manager;
    }

}
