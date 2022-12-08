package me.CarsCupcake.SkyblockRemake.WinterIsle;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Items.*;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skills;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Stats;
import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class WinterItems {
    public static final HashMap<ItemGenerator, Double> whiteGiftLootTable = new HashMap<>();
    public WinterItems(){

        HashMap<ItemGenerator, Double> greenGiftLootTable = new HashMap<>();
        HashMap<ItemGenerator, Double> redGiftLootTable = new HashMap<>();
        whiteGiftLootTable.put(new CoinsItem(1000), 0.1487);
        whiteGiftLootTable.put(new CoinsItem(2000), 0.1487);
        whiteGiftLootTable.put(new CoinsItem(5000), 0.1487);
        whiteGiftLootTable.put(new CoinsItem(10000), 0.0744);
        ItemManager manager = new ItemManager("Gift The Fish", "GIFT_THE_FISH", ItemType.Non, new ArrayList<>(List.of("§7I'm empty inside")), null, null, null, 0,0,0,0, Material.PUFFERFISH, ItemRarity.SPECIAL);
        manager.setUnstackeble(true);
        manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
        Items.SkyblockItems.put(manager.itemID, manager);
        whiteGiftLootTable.put(manager, 0.0006);
        greenGiftLootTable.put(manager, 0.0008);
        redGiftLootTable.put(manager, 0.0015);
        whiteGiftLootTable.put(new SkillXpItem(null, 100), 0.1487);
        whiteGiftLootTable.put(new SkillXpItem(null, 250), 0.0744);
        whiteGiftLootTable.put(new SkillXpItem(null, 500), 0.0297);
        //Skill xp potions here
        //minionskinns here
        manager = new ItemManager("Snow Suit Helmet", "SNOW_SUIT_HELMET", ItemType.Helmet, null, null, null, null, 0, 0 ,0 ,0, ItemRarity.EPIC, "http://textures.minecraft.net/texture/3d1119643cecfbe004141a1ea4062f8315ca78e503958ac50505e8592c44a601");
        manager.setUnstackeble(true);
        manager.setStat(Stats.Health, 70);
        manager.setStat(Stats.Defense, 30);
        manager.setNpcSellPrice(1000);
        Items.SkyblockItems.put(manager.itemID, manager);
        whiteGiftLootTable.put(manager, 0.0003);

        manager = new ItemManager("Snow Suit Leggings", "SNOW_SUIT_LEGGINGS", ItemType.Leggings, null, null,
                null, null, 0, 0 ,0 ,0,Material.LEATHER_LEGGINGS, Color.fromRGB(0xFFFFFF),ItemRarity.EPIC);
        manager.setUnstackeble(true);
        manager.setStat(Stats.Health, 100);
        manager.setStat(Stats.Defense, 40);
        manager.setNpcSellPrice(1000);
        Items.SkyblockItems.put(manager.itemID, manager);
        whiteGiftLootTable.put(manager, 0.0004);

        manager = new ItemManager("Snow Suit Chestplate", "SNOW_SUIT_CHESTPLATE", ItemType.Chestplate, null, null,
                null, null, 0, 0 ,0 ,0,Material.LEATHER_CHESTPLATE, Color.fromRGB(0xFFFFFF),ItemRarity.EPIC);
        manager.setUnstackeble(true);
        manager.setStat(Stats.Health, 75);
        manager.setStat(Stats.Defense, 30);
        manager.setNpcSellPrice(1000);
        Items.SkyblockItems.put(manager.itemID, manager);
        whiteGiftLootTable.put(manager, 0.0004);

        manager = new ItemManager("Snow Suit Boots", "SNOW_SUIT_BOOTS", ItemType.Boots, null, null,
                null, null, 0, 0 ,0 ,0,Material.LEATHER_BOOTS, Color.fromRGB(0xFFFFFF),ItemRarity.EPIC);
        manager.setUnstackeble(true);
        manager.setStat(Stats.Health, 65);
        manager.setStat(Stats.Defense, 25);
        manager.setNpcSellPrice(1000);
        Items.SkyblockItems.put(manager.itemID, manager);
        whiteGiftLootTable.put(manager, 0.0003);

        manager = new ItemManager("White Gift", "WHITE_GIFT", ItemType.Non, new ArrayList<>(List.of("§7Click a stramger while holding", "§7to gift! Both players get the", "§7rewards!")), null, null, null, 0,0,0,0,
                ItemRarity.COMMON, "http://textures.minecraft.net/texture/a5c6944593820d13d7d47db2abcfcbf683bb74a07e1a982db9f32e0a8b5dc466");
        Items.SkyblockItems.put(manager.itemID, manager);
        Bukkit.getPluginManager().registerEvents(new LootGift(), Main.getMain());




}

static class GiftData {
    @Getter
    private final SkyblockPlayer gifter;
    @Getter
    private final SkyblockPlayer reciver;
    private final ArrayList<ArmorStand> stands;
    private final HashMap<ItemGenerator, Double> lootTable;
    private final int gifttype;

    public GiftData(SkyblockPlayer gifter, SkyblockPlayer reciver, HashMap<ItemGenerator, Double> lootTable, ArrayList<ArmorStand> stands, int gifttype) {
        this.gifter = gifter;
        this.reciver = reciver;
        this.lootTable = lootTable;
        this.stands = stands;
        this.gifttype = gifttype;
    }

    public void open() {
        ItemGenerator generator = Tools.getOneItemFromLootTable(lootTable);
        if (generator instanceof CoinsItem c) {
            c.add(gifter);
            c.add(reciver);
            gifter.sendMessage("You got §6" + String.format("%.0f", c.getValue()) + " coins");
            reciver.sendMessage("You got §6" + String.format("%.0f", c.getValue()) + " coins");
        } else if (generator instanceof SkillXpItem s) {
            List<Skills> l = List.of(Skills.Alchemy, Skills.Combat, Skills.Enchanting, Skills.Farming, Skills.Fishing, Skills.Foraging, Skills.Mining);
            Collections.shuffle(l);
            SkillXpItem xp = new SkillXpItem(l.get(0), s.getValue());
            xp.add(gifter);
            xp.add(reciver);
            gifter.sendMessage("You got §3" + String.format("%.0f", s.getValue()) + " " + xp.getSkill());
            reciver.sendMessage("You got §3" + String.format("%.0f", s.getValue()) + " " + xp.getSkill());
        } else {
            gifter.addItem(Main.item_updater(generator.createNewItemStack(), gifter));
            reciver.addItem(Main.item_updater(generator.createNewItemStack(), reciver));
            if (generator instanceof ItemManager m) {
                gifter.sendMessage("You got " + m.rarity.getPrefix() + m.name);
                reciver.sendMessage("You got " + m.rarity.getPrefix() + m.name);
            }
        }
        stands.forEach(Entity::remove);
    }

    public void cancle() {
        stands.forEach(ArmorStand::remove);
        switch (gifttype) {
            case 0 -> gifter.addItem(Items.SkyblockItems.get("WHITE_GIFT"));
        }

    }
}}
