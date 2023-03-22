package me.CarsCupcake.SkyblockRemake.Items.requirements;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.inventory.ItemStack;

public interface Requirement {
    boolean isAllowedToWear(SkyblockPlayer player, ItemStack item);
}
