package me.CarsCupcake.SkyblockRemake.Slayer.vampire;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Items.*;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
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
            manager.addAbility(new SteakStake(), AbilityType.RiftHit, " A bit of Impalement", new AbilityLore(List.of("§7Stab a §4Vampire §7at §c20%" + Stats.Hearts.getSymbol(), "§7to instantly vanquish it.", "§8(Does not actually hurt the vampire)")));
            manager.setUnstackeble(true);
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
