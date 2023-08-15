package me.CarsCupcake.SkyblockRemake.isles.Dungeon.helper;

import me.CarsCupcake.SkyblockRemake.Items.*;
import org.bukkit.Material;

public class HelperItems {
    public HelperItems() {
        ItemManager manager = new ItemManager("Dungeon Stick", "DUNGEON_INIT_ASSISTANTS", ItemType.Wand, Material.BLAZE_ROD, ItemRarity.ADMIN);
        manager.addAbility(new DungeonStickSelect(), AbilityType.RightClick, "Select Type", 0, 0);
        manager.addAbility(new DungeonStickAbility(), AbilityType.LeftClick, "Select Location", 0, 0);
    }
}
