package me.CarsCupcake.SkyblockRemake.Skyblock.major.diana;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Items.*;
import me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.itemAbilitys.SpadeLeftClick;
import me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.itemAbilitys.SpadeRightClick;
import me.CarsCupcake.SkyblockRemake.utils.Factory;
import org.bukkit.Material;

@Getter
public enum DianaItems implements Factory<String, ItemManager> {
    AncientClaw("ANCIENT_CLAW"){
        @Override
        public ItemManager factor(String obj) {
            return new ItemManager("Ancient Claw", obj, ItemType.Non, Material.FLINT, ItemRarity.RARE);
        }
    },
    EnchantedAncientClaw("ENCHANTED_ANCIENT_CLAW"){
        @Override
        public ItemManager factor(String obj) {
            ItemManager manager = new  ItemManager("Enchanted Ancient Claw", obj, ItemType.Non, Material.FLINT, ItemRarity.EPIC);
            manager.setGlint();
            return manager;
        }
    },
    AncestralSpade("ANCESTRAL_SPADE") {
        @Override
        public ItemManager factor(String s) {
            ItemManager manager = new ItemManager("Ancestral Spade", s, ItemType.Non, Material.WOODEN_SHOVEL, ItemRarity.RARE);
            manager.addAbility(new SpadeLeftClick(), AbilityType.LeftClick, null, new AbilityLore("§7Hold in your hand to reveal and", "§7dig out §eGriffin Burrows §7in", "§7the hub, which hold both", "§6treasures §7and §cdangers§7."));
            manager.addAbility(new SpadeRightClick(), AbilityType.RightClick, "Echo", new AbilityLore("§7Show the way to the next or", "§7nearby Griffin burrow"), 100, 3);
            return manager;
        }
    };
    private final ItemManager item;
    DianaItems(String id) {
        item = this.factor(id);
    }
    public static void init() {
        for (DianaItems items : values()) {
            Items.SkyblockItems.put(items.item.itemID, items.item);
        }
    }
}
