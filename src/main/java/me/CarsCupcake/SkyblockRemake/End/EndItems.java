package me.CarsCupcake.SkyblockRemake.End;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Crafting.CraftingObject;
import me.CarsCupcake.SkyblockRemake.Crafting.ShapeEncoder;
import me.CarsCupcake.SkyblockRemake.Crafting.SkyblockRecipe;
import me.CarsCupcake.SkyblockRemake.Crafting.SkyblockShapedRecipe;
import me.CarsCupcake.SkyblockRemake.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Gemstones.GemstoneSlot;
import me.CarsCupcake.SkyblockRemake.Gemstones.SlotType;
import me.CarsCupcake.SkyblockRemake.Items.*;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Stats;
import me.CarsCupcake.SkyblockRemake.abilitys.*;
import org.bukkit.Color;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.UUID;

public class EndItems {
    public static EndItems instance;
    public EndItems(){
        instance = this;
        ItemManager manager = summoningEye();
        me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.put(manager.itemID, manager);
        manager = infinityEye();
        me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.put(manager.itemID, manager);
        manager = sleepingEye();
        me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.put(manager.itemID, manager);
        manager = remnantOfTheEye();
        me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.put(manager.itemID, manager);
        manager = aspectOfTheDragons();
        me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.put(manager.itemID, manager);
        manager = aspectOfTheDraconic();
        me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.put(manager.itemID, manager);

        //Superior Items
        manager = superiorDragonFragment();
        me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.put(manager.itemID, manager);
        manager = superiorChestplate();
        me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.put(manager.itemID, manager);
        manager = superiorLeggings();
        me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.put(manager.itemID, manager);
        manager = superiorHelmet();
        me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.put(manager.itemID, manager);
        manager = superiorBoots();
        me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.put(manager.itemID, manager);

        //Protector Items
        ProtectiveBlood.addItems();

        //Old Items
        OldBlood.addItems();

        //Strong Items
        StrongBlood.addItems();

        //Wise Items
        WiseBlood.addItems();

        //Unstable Items
        UnstableBlood.addItems();

        //Young Items
        YoungBlood.addItems();

        manager = enchantedEnderPearl();
        me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.put(manager.itemID, manager);


    }
    private ItemManager summoningEye(){
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§7Use this at §5Ender Altar");
        lore.add("§7in the §7Dragon's Nest §7to");
        lore.add("§7summon Ender Dragons!");
        ItemManager manager = new ItemManager("Summoning Eye", "SUMMONING_EYE", ItemType.Non, lore, null, null, null, 0, 0,0,0, ItemRarity.EPIC
                ,"https://textures.minecraft.net/texture/daa8fc8de6417b48d48c80b443cf5326e3d9da4dbe9b25fcd49549d96168fc0");
        manager.setUnstackeble(true);
        return manager;
    }
    private ItemManager infinityEye(){
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§7Use this at §5Ender Altar");
        lore.add("§7in the §7Dragon's Nest §7to");
        lore.add("§7summon Ender Dragons!");
        ItemManager manager = new ItemManager("Infinity Eye", "INFINITY_EYE", ItemType.Non, lore, null, null, null, 0, 0,0,0, ItemRarity.EPIC
                ,"https://textures.minecraft.net/texture/aba9647ec7c8f359d8d0952bdbf72cbb4b5743cf8455dcb7664e2bf9bdf8b718");
        manager.setUnstackeble(true);
        return manager;
    }
    private ItemManager sleepingEye(){
        ItemManager manager = new ItemManager("Sleeping Eye", "SLEEPING_EYE", ItemType.Non, null, null, null, null, 0, 0,0,0, ItemRarity.EPIC
                ,"https://textures.minecraft.net/texture/37c0d010dd0e512ffea108d7c5fe69d576c31ec266c884b51ec0b28cc457");
        manager.setUnstackeble(true);
        return manager;
    }
    private ItemManager remnantOfTheEye(){
        ItemManager manager = new ItemManager("Remnant of the Eye", "REMNANT_OF_THE_EYE", ItemType.Non, null, null, null, null, 0, 0,0,0, ItemRarity.EPIC
                ,"https://textures.minecraft.net/texture/7d389c55ecf7db572d6961ce3d57b572e761397b67a2d6d94c72fc91dddd74");
        manager.setUnstackeble(true);
        return manager;
    }
    private ItemManager aspectOfTheDragons(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7All Monsters in front of you");
        abilityLore.add("§7take §a%dmg% §7damage. Hit");
        abilityLore.add("§7monsters take large knockback.");
        ItemManager manager = new ItemManager("Aspect Of The Dragons", "ASPECT_OF_THE_DRAGON", ItemType.Sword, null, "Dragon Rage", null, null, 100, 30,0,0, Material.DIAMOND_SWORD, ItemRarity.LEGENDARY);
        manager.setNpcSellPrice(100000);
        manager.setDamage(225);
        manager.setStat(Stats.Strength, 100);
        AbilityLore lore = new AbilityLore(abilityLore, "%dmg%", new Bundle<>(12000d, 0.1));
        manager.setAbility(new DragonsRage(), AbilityType.RightClick);
        manager.setAbilityLore(lore);

        return manager;
    }

    private ItemManager aspectOfTheDraconic(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7All Monsters in front of you");
        abilityLore.add("§7take §a%dmg% §7damage. Hit");
        abilityLore.add("§7monsters take large knockback.");
        ItemManager manager = new ItemManager("Aspect Of The Dragons", "ASPECT_OF_THE_DRACONIC", ItemType.Sword, null, "Draconic Rage", null, null, 100, 0,0,0, Material.DIAMOND_SWORD, ItemRarity.LEGENDARY);
        manager.setNpcSellPrice(100000);
        manager.setDamage(260);
        manager.setStat(Stats.Strength, 150);
        AbilityLore lore = new AbilityLore(abilityLore, "%dmg%", new Bundle<>(20000d, 0.1));
        manager.setAbility(new DragonsRage(), AbilityType.RightClick);
        manager.setAbilityLore(lore);

        return manager;
    }
    private ItemManager superiorDragonFragment(){
        ItemManager manager = new ItemManager("Superior Dragon Fragment", "SUPERIOR_FRAGMENT", ItemType.Non, null, null, null, null, 0, 0,0,0, ItemRarity.EPIC
                ,"https://textures.minecraft.net/texture/6f89b150be9c4c5249f355f68ea0c4391300a9be1f260d750fc35a1817ad796e", UUID.fromString("86541f16-3bdf-4d08-871a-dcdf8ba373f5"));
        return manager;
    }
    private ItemManager superiorHelmet(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Most of your stats are increased");
        abilityLore.add("§7by §a5% §7and §6Aspect of the");
        abilityLore.add("§6Dragons §7Ability deals §a50%");
        abilityLore.add("§7more damage.");
        ItemManager manager = new ItemManager("Superior Dragon Helmet", "SUPERIOR_DRAGON_HELMET", ItemType.Helmet, null,
                "Superior Blood", null, null, 0, 0,0,0, ItemRarity.LEGENDARY, "https://textures.minecraft.net/texture/278bc4b2564f1aef2939d5aa285f3afae02e9d9f08243b2579913fd8feddcb56");
        manager.setNpcSellPrice(100000);
        manager.setStat(Stats.Strength, 10);
        manager.setStat(Stats.Health, 90);
        manager.setStat(Stats.Defense, 130);
        manager.setStat(Stats.Inteligence, 25);
        manager.setStat(Stats.Speed, 3);
        manager.setStat(Stats.CritChance, 2);
        manager.setStat(Stats.CritDamage, 10);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));
        AbilityLore lore = new AbilityLore(abilityLore);
        manager.setFullSetBonus(Bonuses.SuperiorBlood, "Superior Blood");
        manager.setAbilityLore(lore);
        manager.setUnstackeble(true);

        SkyblockShapedRecipe recipe = new SkyblockShapedRecipe("SUPERIOR_DRAGON_HELMET", manager, 1);
        ShapeEncoder encoder = new ShapeEncoder("sss", "s s", "   ");
        encoder.setKey('s', new CraftingObject(Items.SuperiorDragonFragment.getItem(), 10));
        recipe.setRecipe(encoder.encode());
        SkyblockRecipe.recipes.add(recipe);

        return manager;
    }
    private ItemManager superiorChestplate(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Most of your stats are increased");
        abilityLore.add("§7by §a5% §7and §6Aspect of the");
        abilityLore.add("§6Dragons §7Ability deals §a50%");
        abilityLore.add("§7more damage.");
        ItemManager manager = new ItemManager("Superior Dragon Chestplate", "SUPERIOR_DRAGON_CHESTPLATE", ItemType.Chestplate, null,
                "Superior Blood", null, null, 0, 0,0,0,Material.LEATHER_CHESTPLATE, Color.fromRGB(0xF2DF11) ,ItemRarity.LEGENDARY);
        manager.setNpcSellPrice(100000);
        manager.setStat(Stats.Strength, 10);
        manager.setStat(Stats.Health, 150);
        manager.setStat(Stats.Defense, 190);
        manager.setStat(Stats.Inteligence, 25);
        manager.setStat(Stats.Speed, 3);
        manager.setStat(Stats.CritChance, 2);
        manager.setStat(Stats.CritDamage, 10);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));
        AbilityLore lore = new AbilityLore(abilityLore);
        manager.setFullSetBonus(Bonuses.SuperiorBlood, "Superior Blood");
        manager.setAbilityLore(lore);

        SkyblockShapedRecipe recipe = new SkyblockShapedRecipe("SUPERIOR_DRAGON_CHESTPLATE", manager, 1);
        ShapeEncoder encoder = new ShapeEncoder("s s", "sss", "sss");
        encoder.setKey('s', new CraftingObject(Items.SuperiorDragonFragment.getItem(), 10));
        recipe.setRecipe(encoder.encode());
        SkyblockRecipe.recipes.add(recipe);

        return manager;
    }
    private ItemManager superiorLeggings(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Most of your stats are increased");
        abilityLore.add("§7by §a5% §7and §6Aspect of the");
        abilityLore.add("§6Dragons §7Ability deals §a50%");
        abilityLore.add("§7more damage.");
        ItemManager manager = new ItemManager("Superior Dragon Leggings", "SUPERIOR_DRAGON_LEGGINGS", ItemType.Leggings, null,
                "Superior Blood", null, null, 0, 0,0,0,Material.LEATHER_LEGGINGS, Color.fromRGB(0xF2DF11) ,ItemRarity.LEGENDARY);
        manager.setNpcSellPrice(100000);
        manager.setStat(Stats.Strength, 10);
        manager.setStat(Stats.Health, 130);
        manager.setStat(Stats.Defense, 170);
        manager.setStat(Stats.Inteligence, 25);
        manager.setStat(Stats.Speed, 3);
        manager.setStat(Stats.CritChance, 2);
        manager.setStat(Stats.CritDamage, 10);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));
        AbilityLore lore = new AbilityLore(abilityLore);
        manager.setFullSetBonus(Bonuses.SuperiorBlood, "Superior Blood");
        manager.setAbilityLore(lore);

        SkyblockShapedRecipe recipe = new SkyblockShapedRecipe("SUPERIOR_DRAGON_LEGGINGS", manager, 1);
        ShapeEncoder encoder = new ShapeEncoder("sss", "s s", "s s");
        encoder.setKey('s', new CraftingObject(Items.SuperiorDragonFragment.getItem(), 10));
        recipe.setRecipe(encoder.encode());
        SkyblockRecipe.recipes.add(recipe);

        return manager;
    }
    private ItemManager superiorBoots(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Most of your stats are increased");
        abilityLore.add("§7by §a5% §7and §6Aspect of the");
        abilityLore.add("§6Dragons §7Ability deals §a50%");
        abilityLore.add("§7more damage.");
        ItemManager manager = new ItemManager("Superior Dragon Boots", "SUPERIOR_DRAGON_BOOTS", ItemType.Boots, null,
                "Superior Blood", null, null, 0, 0,0,0,Material.LEATHER_BOOTS, Color.fromRGB(0xF25D18) ,ItemRarity.LEGENDARY);
        manager.setNpcSellPrice(100000);
        manager.setStat(Stats.Strength, 10);
        manager.setStat(Stats.Health, 80);
        manager.setStat(Stats.Defense, 110);
        manager.setStat(Stats.Inteligence, 25);
        manager.setStat(Stats.Speed, 3);
        manager.setStat(Stats.CritChance, 2);
        manager.setStat(Stats.CritDamage, 10);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));
        AbilityLore lore = new AbilityLore(abilityLore);
        manager.setFullSetBonus(Bonuses.SuperiorBlood, "Superior Blood");
        manager.setAbilityLore(lore);

        SkyblockShapedRecipe recipe = new SkyblockShapedRecipe("SUPERIOR_DRAGON_BOOTS", manager, 1);
        ShapeEncoder encoder = new ShapeEncoder("   ", "s s", "s s");
        encoder.setKey('s', new CraftingObject(Items.SuperiorDragonFragment.getItem(), 10));
        recipe.setRecipe(encoder.encode());
        SkyblockRecipe.recipes.add(recipe);

        return manager;
    }
    private ItemManager enchantedEnderPearl(){
        ItemManager manager = new ItemManager("Enchanted Ender Pearl", "ENCHANTED_ENDER_PEARL", ItemType.Non, null,
                null, null, null, 0, 0,0,0,Material.ENDER_PEARL ,ItemRarity.UNCOMMON);
        manager.setNpcSellPrice(140);
        manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
        return manager;
    }

    public enum Items{
        SummoningEye("SUMMONING_EYE"),
        InfinityEye("INFINITY_EYE"),
        SleepingEye("SLEEPING_EYE"),
        RemnantOfTheEye("REMNANT_OF_THE_EYE"),
        AspectOfTheDragons("ASPECT_OF_THE_DRAGON"),
        SuperiorDragonFragment("SUPERIOR_FRAGMENT"),
        SuperiorHelmet("SUPERIOR_DRAGON_HELMET"),
        SuperiorChestplate("SUPERIOR_DRAGON_CHESTPLATE"),
        SuperiorLeggings("SUPERIOR_DRAGON_LEGGINGS"),
        SuperiorBoots("SUPERIOR_DRAGON_BOOTS"),
        ProtectorDragonFragment("PROTECTOR_FRAGMENT"),
        ProtectorHelmet("PROTECTOR_DRAGON_HELMET"),
        ProtectorChestplate("PROTECTOR_DRAGON_CHESTPLATE"),
        ProtectorLeggings("PROTECTOR_DRAGON_LEGGINGS"),
        ProtectorBoots("PROTECTOR_DRAGON_BOOTS"),
        OldDragonFragment("OLD_FRAGMENT"),
        OldHelmet("OLD_DRAGON_HELMET"),
        OldChestplate("OLD_DRAGON_CHESTPLATE"),
        OldLeggings("OLD_DRAGON_LEGGINGS"),
        OldBoots("OLD_DRAGON_BOOTS"),

        StrongDragonFragment("STRONG_FRAGMENT"),
        StrongHelmet("STRONG_DRAGON_HELMET"),
        StrongChestplate("STRONG_DRAGON_CHESTPLATE"),
        StrongLeggings("STRONG_DRAGON_LEGGINGS"),
        StrongBoots("STRONG_DRAGON_BOOTS"),

        WiseDragonFragment("WISE_FRAGMENT"),
        WiseHelmet("WISE_DRAGON_HELMET"),
        WiseChestplate("WISE_DRAGON_CHESTPLATE"),
        WiseLeggings("WISE_DRAGON_LEGGINGS"),
        WiseBoots("WISE_DRAGON_BOOTS"),

        UnstableDragonFragment("UNSTABLE_FRAGMENT"),
        UnstableHelmet("UNSTABLE_DRAGON_HELMET"),
        UnstableChestplate("UNSTABLE_DRAGON_CHESTPLATE"),
        UnstableLeggings("UNSTABLE_DRAGON_LEGGINGS"),
        UnstableBoots("UNSTABLE_DRAGON_BOOTS"),

        YoungDragonFragment("YOUNG_FRAGMENT"),
        YoungHelmet("YOUNG_DRAGON_HELMET"),
        YoungChestplate("YOUNG_DRAGON_CHESTPLATE"),
        YoungLeggings("YOUNG_DRAGON_LEGGINGS"),
        YoungBoots("YOUNG_DRAGON_BOOTS"),
        EnchantedEnderPearl("ENCHANTED_ENDER_PEARL");


        private final String id;
        Items(String id) {
        this.id = id;
        }
        public ItemManager getItem(){
            return me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.get(id);
        }
        public String getId(){
            return id;
        }
    }
}
