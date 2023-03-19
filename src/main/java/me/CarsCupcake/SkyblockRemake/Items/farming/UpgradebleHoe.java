package me.CarsCupcake.SkyblockRemake.Items.farming;

import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class UpgradebleHoe implements ItemManager.SpecialRarityGrabber {
    @Override
    public ItemRarity getRarity(@NotNull ItemRarity rarity, @NotNull ItemStack item, @Nullable SkyblockPlayer player) {
        int counter = ItemHandler.getOrDefaultPDC("counter", item, PersistentDataType.INTEGER, 0);
        if(counter >= 100000)
            rarity = rarity.getNext();
        if(counter >= 10000000)
            rarity = rarity.getNext();
        return rarity;
    }
}
