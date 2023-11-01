package me.CarsCupcake.SkyblockRemake.Slayer.vampire;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Items.*;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.Slayer.vampire.itemAbilities.VampireSword;
import me.CarsCupcake.SkyblockRemake.abilities.ABILITIES;
import me.CarsCupcake.SkyblockRemake.utils.Factory;
import org.bukkit.Material;

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
