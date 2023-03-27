package me.CarsCupcake.SkyblockRemake.Skyblock.player.AccessoryBag;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.inventory.ItemStack;

public abstract class ArtifactAbility {
    public abstract void start(SkyblockPlayer player, ItemStack item);
    public abstract void stop(SkyblockPlayer player);

}
