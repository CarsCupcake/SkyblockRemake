package me.CarsCupcake.SkyblockRemake.AuctionHouse;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Date;

public interface IAuction {
    double getCost();
    Date getExpiringDate();
    ItemStack getItem();
    Player getPlayer();
    int getAuctionPointer();
    void setAuctionPointer(int i);
    ItemStack craftShowItem();
    void openManager(SkyblockPlayer player);
    boolean show();
}
