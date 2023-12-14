package me.CarsCupcake.SkyblockRemake.Slayer.vampire;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Items.*;
import me.CarsCupcake.SkyblockRemake.Items.Crafting.CraftingObject;
import me.CarsCupcake.SkyblockRemake.Items.Crafting.ShapeEncoder;
import me.CarsCupcake.SkyblockRemake.Items.Crafting.SkyblockShapedRecipe;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.Slayer.vampire.itemAbilities.MelonAbility;
import me.CarsCupcake.SkyblockRemake.Slayer.vampire.itemAbilities.SteakStake;
import me.CarsCupcake.SkyblockRemake.Slayer.vampire.itemAbilities.VampireSword;
import me.CarsCupcake.SkyblockRemake.abilities.ABILITIES;
import me.CarsCupcake.SkyblockRemake.utils.Factory;
import org.bukkit.Material;

import java.util.List;

@Getter
public enum VampireItems implements Factory<String, ItemManager> {
    CovenSeal("COVEN_SEAL") {
        @Override
        public ItemManager factor(String id) {
            return new ItemManager("Coven Seal", id, ItemType.Non, Material.NETHER_WART, ItemRarity.UNCOMMON);
        }
    },
    Bloodbadge("BLOODBADGE") {
        @Override
        public ItemManager factor(String id) {
            return new ItemManager("Bloodbadge", id, ItemType.Non, Material.RED_DYE, ItemRarity.RARE);
        }
    },
    SilverLacedKarambit("SILVER_LACED_KARAMBIT") {
        @Override
        public ItemManager factor(String id) {
            ItemManager manager = new ItemManager("Silver-Laced Karambit", id, ItemType.Sword, Material.IRON_SWORD, ItemRarity.EPIC);
            manager.setStat(Stats.RiftDamage, 5);
            manager.setStat(Stats.Speed, 10);
            return manager;
        }
    },
    SilverTwistKarambit("SILVERTWIST_KARAMBIT") {
        @Override
        public ItemManager factor(String id) {
            ItemManager manager = new ItemManager("Silver-Twist Karambit", id, ItemType.Sword, Material.IRON_SWORD, ItemRarity.EPIC);
            manager.setStat(Stats.RiftDamage, 6);
            manager.setStat(Stats.Speed, 15);
            manager.setGlint();
            return manager;
        }
    },
    SteakStake("STEAK_STAKE") {
        @Override
        public ItemManager factor(String obj) {
            ItemManager manager = new ItemManager("Steak Stake", obj, ItemType.Sword, Material.COOKED_MUTTON, ItemRarity.RARE);
            manager.addAbility(new SteakStake(), AbilityType.RiftHit, "A bit of Impalement", new AbilityLore(List.of("§7Stab a §4Vampire §7at §c20%" + Stats.Hearts.getSymbol(), "§7to instantly vanquish it.", "§8(Does not actually hurt the vampire)")));
            manager.setUnstackeble(true);
            return manager;
        }
    },
    VampiricMelons("VAMPIRIC_MELON") {
        @Override
        public ItemManager factor(String obj) {
            return new ItemManager("Vampiric Melon", obj, ItemType.Non, Material.MELON, ItemRarity.UNCOMMON);
        }
    },
    HealingMelon("HEALING_MELON") {
        @Override
        public ItemManager factor(String obj) {
            ItemManager manager = new ItemManager("Healing Melon", obj, ItemType.Non, Material.GLISTERING_MELON_SLICE, ItemRarity.UNCOMMON);
            manager.addAbility(new MelonAbility(7), AbilityType.RightClick, "Succulence", new AbilityLore(List.of("§7Take a bite and heal §c7" + Stats.Hearts.getSymbol())), 50, 0);
            manager.setUnstackeble(true);
            SkyblockShapedRecipe skyblockShapedRecipe = new SkyblockShapedRecipe(manager, 1);
            ShapeEncoder encoder = new ShapeEncoder("m  ", "mm ", "mmm");
            encoder.setKey('m', new CraftingObject(VampiricMelons.manager, 2));
            skyblockShapedRecipe.setRecipe(encoder.encode());
            return manager;
        }
    },
    JuicyHealingMelon("JUICY_HEALING_MELON") {
        @Override
        public ItemManager factor(String obj) {
            ItemManager manager = new ItemManager("Juicy Healing Melon", obj, ItemType.Non, Material.MELON_SLICE, ItemRarity.EPIC);
            manager.addAbility(new MelonAbility(9), AbilityType.RightClick, "Succulence", new AbilityLore(List.of("§7Take a bite and heal §c9" + Stats.Hearts.getSymbol())), 50, 0);
            manager.setUnstackeble(true);
            SkyblockShapedRecipe skyblockShapedRecipe = new SkyblockShapedRecipe(manager, 1);
            ShapeEncoder encoder = new ShapeEncoder("ccc", "cmc", "ccc");
            encoder.setKey('m', new CraftingObject(HealingMelon.manager, 1));
            encoder.setKey('c', new CraftingObject(CovenSeal.manager, 2));
            skyblockShapedRecipe.setRecipe(encoder.encode());
            return manager;
        }
    },
    LusciousHealingMelon("LUSCIOUS_HEALING_MELON") {
        @Override
        public ItemManager factor(String obj) {
            ItemManager manager = new ItemManager("Luscious Healing Melon", obj, ItemType.Non, Material.MELON_SLICE, ItemRarity.LEGENDARY);
            manager.setGlint();
            manager.addAbility(new MelonAbility(12), AbilityType.RightClick, "Succulence", new AbilityLore(List.of("§7Take a bite and heal §c12" + Stats.Hearts.getSymbol())), 50, 0);
            manager.setUnstackeble(true);
            SkyblockShapedRecipe skyblockShapedRecipe = new SkyblockShapedRecipe(manager, 1);
            ShapeEncoder encoder = new ShapeEncoder(" b ", " m ", " b ");
            encoder.setKey('m', new CraftingObject(JuicyHealingMelon.manager, 1));
            encoder.setKey('b', new CraftingObject(Bloodbadge.manager, 1));
            skyblockShapedRecipe.setRecipe(encoder.encode());
            return manager;
        }
    };
    private final ItemManager manager;
    private final String id;
    VampireItems(String id) {
        manager = (Items.SkyblockItems.containsKey(id)) ? Items.SkyblockItems.get(id) : this.factor(id);
        this.id = id;
    }
    public static void init() {
        ABILITIES.registerEvent(new VampireSword());
    }
}
