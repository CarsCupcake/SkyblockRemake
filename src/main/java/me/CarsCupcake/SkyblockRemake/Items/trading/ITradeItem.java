package me.CarsCupcake.SkyblockRemake.Items.trading;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.inventory.ItemStack;

public interface ITradeItem {
    ItemStack show();
    void onClaim(SkyblockPlayer player);
    void takeBack(SkyblockPlayer player);
    void takeIn(SkyblockPlayer player);
    boolean check(SkyblockPlayer player);
}
