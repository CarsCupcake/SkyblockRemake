package me.CarsCupcake.SkyblockRemake.Slayer.Enderman;

import me.CarsCupcake.SkyblockRemake.Crafting.CraftingObject;
import me.CarsCupcake.SkyblockRemake.Crafting.ShapeEncoder;
import me.CarsCupcake.SkyblockRemake.Crafting.SkyblockRecipe;
import me.CarsCupcake.SkyblockRemake.Crafting.SkyblockShapedRecipe;
import me.CarsCupcake.SkyblockRemake.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Gemstones.GemstoneSlot;
import me.CarsCupcake.SkyblockRemake.Gemstones.SlotType;
import me.CarsCupcake.SkyblockRemake.Items.*;
import me.CarsCupcake.SkyblockRemake.Stats;
import me.CarsCupcake.SkyblockRemake.abilitys.Soulcry;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class EndermanSlayerItems {
    public EndermanSlayerItems(){
        t1();
    }
    private void t1(){
        ItemManager manager = NullSphere();
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = VoidwalkerKatana();
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = VoidedgeKatana();
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = VorpalKatana();
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = AtomsplitKatana();
        Items.SkyblockItems.put(manager.itemID, manager);
    }
    public static ItemManager NullSphere(){
        ItemManager manager = new ItemManager("Null Sphere", "NULL_SPHERE", ItemType.Non, null, null, null, null, 0, 0,0, 0, Material.FIREWORK_STAR, ItemRarity.UNCOMMON);
        manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
        return manager;
    }

    public static ItemManager VoidwalkerKatana(){
        ItemManager manager = new ItemManager("Voidwalker Katana", "VOIDWALKER_KATANA", ItemType.Sword, new ArrayList<>(List.of("§7Deal §a+150% §7damage to Endermen.", "§7Receive §a3% less damage", "§7from Endermen when held.")), null, null, null, 0, 0,0, 0, Material.IRON_SWORD, ItemRarity.UNCOMMON);
        manager.setDamage(80);
        manager.setStat(Stats.Strength, 40);
        manager.setStat(Stats.CritDamage, 10);

        SkyblockShapedRecipe recipe = new SkyblockShapedRecipe(manager.itemID, manager, 1);
        ShapeEncoder encoder = new ShapeEncoder(" v ", " v ", " s ");
        encoder.setKey('v', new CraftingObject(NullSphere(), 16));
        encoder.setKey('s', new CraftingObject(Items.SkyblockItems.get(Material.STICK.toString()), 1));
        recipe.setRecipe(encoder.encode());
        SkyblockRecipe.recipes.add(recipe);

        return manager;
    }
    public static ItemManager VoidedgeKatana(){
        ItemManager manager = new ItemManager("Voidedge Katana", "VOIDEDGE_KATANA", ItemType.Sword, new ArrayList<>(List.of("§7Deal §a+250% §7damage to Endermen.", "§7Receive §a6% less damage", "§7from Endermen when held."))
                , "Soulcry", "null", new ArrayList<>(List.of("§7Gain §c+200 ⫽ Ferocity §7against", "§7Endermen for §a4s")), 200, 4,0, 0, Material.DIAMOND_SWORD, ItemRarity.RARE);
        manager.setDamage(125);
        manager.setStat(Stats.Strength, 60);
        manager.setStat(Stats.Inteligence, 50);
        manager.setStat(Stats.CritDamage, 20);
        manager.getFlags().add(ItemFlag.SPECIAL_MATERIAL_GRABBER);
        manager.addSlot(new GemstoneSlot(SlotType.Jasper));
        manager.addSlot(new GemstoneSlot(SlotType.Sapphire));
        manager.setAbility(new Soulcry(), AbilityType.RightClick);
        manager.setMaterialGrabber(new Soulcry());
        return manager;
    }

    public static ItemManager VorpalKatana(){
        ItemManager manager = new ItemManager("Vorpal Katana", "VORPAL_KATANA", ItemType.Sword, new ArrayList<>(List.of("§7Deal §a+350% §7damage to Endermen.", "§7Receive §a9% less damage", "§7from Endermen when held."))
                , "Soulcry", "null", new ArrayList<>(List.of("§7Gain §c+300 ⫽ Ferocity §7against", "§7Endermen for §a4s")), 200, 4,0, 0, Material.DIAMOND_SWORD, ItemRarity.EPIC);
        manager.setDamage(125);
        manager.setStat(Stats.Strength, 80);
        manager.setStat(Stats.Inteligence, 200);
        manager.setStat(Stats.CritDamage, 25);
        manager.getFlags().add(ItemFlag.SPECIAL_MATERIAL_GRABBER);
        manager.addSlot(new GemstoneSlot(SlotType.Jasper));
        manager.addSlot(new GemstoneSlot(SlotType.Sapphire));
        manager.setAbility(new Soulcry(), AbilityType.RightClick);
        manager.setMaterialGrabber(new Soulcry());
        return manager;
    }

    public static ItemManager AtomsplitKatana(){
        ItemManager manager = new ItemManager("Atomsplit Katana", "ATOMSPLIT_KATANA", ItemType.Sword, new ArrayList<>(List.of("§7Deal §a+450% §7damage to Endermen.", "§7Receive §a12% less damage", "§7from Endermen when held."))
                , "Soulcry", "null", new ArrayList<>(List.of("§7Gain §c+400 ⫽ Ferocity §7against", "§7Endermen for §a4s")), 200, 4,0, 0, Material.DIAMOND_SWORD, ItemRarity.LEGENDARY);
        manager.setDamage(245);
        manager.setStat(Stats.Strength, 100);
        manager.setStat(Stats.Inteligence, 300);
        manager.setStat(Stats.CritDamage, 30);
        manager.getFlags().add(ItemFlag.SPECIAL_MATERIAL_GRABBER);
        manager.addSlot(new GemstoneSlot(SlotType.Jasper));
        manager.addSlot(new GemstoneSlot(SlotType.Sapphire));manager.addSlot(new GemstoneSlot(SlotType.Sapphire));
        manager.setAbility(new Soulcry(), AbilityType.RightClick);
        manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
        manager.setMaterialGrabber(new Soulcry());
        return manager;
    }
}
