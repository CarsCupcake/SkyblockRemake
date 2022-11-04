package me.CarsCupcake.SkyblockRemake.Items;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.inventory.ItemStack;

public interface UpdateFlag {
    String getReplaceValue(SkyblockPlayer player, ItemStack itemStack);
}
