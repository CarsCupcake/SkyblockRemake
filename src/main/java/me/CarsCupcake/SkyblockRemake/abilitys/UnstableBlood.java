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

public class UnstableBlood implements FullSetBonus, Listener {
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
        return Bonuses.UnstableBlood;
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
        ItemManager manager = new ItemManager("Unstable Dragon Fragment", "UNSTABLE_FRAGMENT", ItemType.Non, null, null, null, null, 0, 0,0,0, ItemRarity.EPIC
                ,"https://textures.minecraft.net/texture/98228c234c3903c512a5a0aa45260e7b567e0e20eefc7d561ccec97b295871af", UUID.fromString("86541f16-3bdf-4d08-871a-dcdf8ba373f5"));
        return manager;
    }
    private static ItemManager protectorHelmet(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Sometimes strikes nearby mobs");
        abilityLore.add("§7with lightning. It's unstable!");
        ItemManager manager = new ItemManager("Unstable Dragon Helmet", "UNSTABLE_DRAGON_HELMET", ItemType.Helmet, null,
                "Unstable Blood", null, null, 0, 0,0,0, ItemRarity.LEGENDARY,
                "https://textures.minecraft.net/texture/2922b5f8d554ca923f96832a5a4e9169bc2cdb360a2b06ebec09b6a6af4618e3");
        manager.setNpcSellPrice(100000);
        manager.setStat(Stats.Health, 70);
        manager.setStat(Stats.Defense, 110);
        manager.setStat(Stats.CritChance, 5);
        manager.setStat(Stats.CritDamage, 15);
        manager.setStat(Stats.Inteligence, 25);
        manager.addSlot(new GemstoneSlot(SlotType.Offensive));
        AbilityLore lore = new AbilityLore(abilityLore);
        manager.setFullSetBonus(Bonuses.UnstableBlood, "Unstable Blood");
        manager.setAbilityLore(lore);
        manager.setUnstackeble(true);

        SkyblockShapedRecipe recipe = new SkyblockShapedRecipe("UNSTABLE_DRAGON_HELMET", manager, 1);
        ShapeEncoder encoder = new ShapeEncoder("sss", "s s", "   ");
        encoder.setKey('s', new CraftingObject(EndItems.Items.UnstableDragonFragment.getItem(), 10));
        recipe.setRecipe(encoder.encode());
        SkyblockRecipe.recipes.add(recipe);

        return manager;
    }
    private static ItemManager protectorChestplate(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Sometimes strikes nearby mobs");
        abilityLore.add("§7with lightning. It's unstable!");
        ItemManager manager = new ItemManager("Unstable Dragon Chestplate", "UNSTABLE_DRAGON_CHESTPLATE", ItemType.Chestplate, null,
                "Unstable Blood", null, null, 0, 0,0,0, Material.LEATHER_CHESTPLATE, Color.fromRGB(0xB212E3),ItemRarity.LEGENDARY);
        manager.setNpcSellPrice(100000);
        manager.setStat(Stats.Health, 120);
        manager.setStat(Stats.Defense, 160);
        manager.setStat(Stats.CritChance, 5);
        manager.setStat(Stats.CritDamage, 15);
        manager.addSlot(new GemstoneSlot(SlotType.Offensive));
        AbilityLore lore = new AbilityLore(abilityLore);
        manager.setFullSetBonus(Bonuses.UnstableBlood, "Unstable Blood");
        manager.setAbilityLore(lore);
        manager.setUnstackeble(true);

        SkyblockShapedRecipe recipe = new SkyblockShapedRecipe("UNSTABLE_DRAGON_CHESTPLATE", manager, 1);
        ShapeEncoder encoder = new ShapeEncoder("s s", "sss", "sss");
        encoder.setKey('s', new CraftingObject(EndItems.Items.UnstableDragonFragment.getItem(), 10));
        recipe.setRecipe(encoder.encode());
        SkyblockRecipe.recipes.add(recipe);

        return manager;
    }
    private static ItemManager protectorLeggings(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Sometimes strikes nearby mobs");
        abilityLore.add("§7with lightning. It's unstable!");
        ItemManager manager = new ItemManager("Unstable Dragon Leggings", "UNSTABLE_DRAGON_LEGGINGS", ItemType.Leggings, null,
                "Unstable Blood", null, null, 0, 0,0,0,Material.LEATHER_LEGGINGS, Color.fromRGB(0xB212E3),ItemRarity.LEGENDARY);
        manager.setNpcSellPrice(100000);
        manager.setStat(Stats.Health, 100);
        manager.setStat(Stats.Defense, 140);
        manager.setStat(Stats.CritChance, 5);
        manager.setStat(Stats.CritDamage, 15);
        manager.addSlot(new GemstoneSlot(SlotType.Offensive));
        AbilityLore lore = new AbilityLore(abilityLore);
        manager.setFullSetBonus(Bonuses.UnstableBlood, "Unstable Blood");
        manager.setAbilityLore(lore);
        manager.setUnstackeble(true);

        SkyblockShapedRecipe recipe = new SkyblockShapedRecipe("UNSTABLE_DRAGON_LEGGINGS", manager, 1);
        ShapeEncoder encoder = new ShapeEncoder("sss", "s s", "s s");
        encoder.setKey('s', new CraftingObject(EndItems.Items.UnstableDragonFragment.getItem(), 10));
        recipe.setRecipe(encoder.encode());
        SkyblockRecipe.recipes.add(recipe);

        return manager;
    }
    private static ItemManager protectorBoots(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Sometimes strikes nearby mobs");
        abilityLore.add("§7with lightning. It's unstable!");
        ItemManager manager = new ItemManager("Unstable Dragon Boots", "UNSTABLE_DRAGON_BOOTS", ItemType.Boots, null,
                "Unstable Blood", null, null, 0, 0,0,0,Material.LEATHER_BOOTS, Color.fromRGB(0xB212E3),ItemRarity.LEGENDARY);
        manager.setNpcSellPrice(100000);
        manager.setStat(Stats.Health, 60);
        manager.setStat(Stats.Defense, 90);
        manager.setStat(Stats.CritChance, 5);
        manager.setStat(Stats.CritDamage, 15);
        manager.addSlot(new GemstoneSlot(SlotType.Offensive));
        AbilityLore lore = new AbilityLore(abilityLore);
        manager.setFullSetBonus(Bonuses.UnstableBlood, "Unstable Blood");
        manager.setAbilityLore(lore);
        manager.setUnstackeble(true);

        SkyblockShapedRecipe recipe = new SkyblockShapedRecipe("UNSTABLE_DRAGON_BOOTS", manager, 1);
        ShapeEncoder encoder = new ShapeEncoder("   ", "s s", "s s");
        encoder.setKey('s', new CraftingObject(EndItems.Items.UnstableDragonFragment.getItem(), 10));
        recipe.setRecipe(encoder.encode());
        SkyblockRecipe.recipes.add(recipe);

        return manager;
    }

}
