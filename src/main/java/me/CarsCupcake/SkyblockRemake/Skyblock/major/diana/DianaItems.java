package me.CarsCupcake.SkyblockRemake.Skyblock.major.diana;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.utils.Factory;
import org.bukkit.Material;

@Getter
public enum DianaItems implements Factory<String, ItemManager> {
    AncestralSpade("ANCESTRAL_SPADE") {
        @Override
        public ItemManager factor(String s) {
            ItemManager manager = new ItemManager("Ancestral Spade", s, ItemType.Non, Material.WOODEN_SHOVEL, ItemRarity.RARE);
            return manager;
        }
    };
    private final ItemManager item;
    DianaItems(String id) {
        item = this.factor(id);
    }
}
