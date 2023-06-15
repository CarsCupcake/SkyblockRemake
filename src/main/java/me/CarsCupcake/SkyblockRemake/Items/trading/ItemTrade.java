package me.CarsCupcake.SkyblockRemake.Items.trading;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.inventory.ItemStack;

public class ItemTrade implements ITradeItem{
    private ItemStack item;
    private final int slot;
    public ItemTrade(int invSlot){
        this.slot = invSlot;
    }
    @Override
    public ItemStack show() {
        return item;
    }

    @Override
    public void onClaim(SkyblockPlayer player) {
        player.addItem(item, false);
    }

    @Override
    public void takeBack(SkyblockPlayer player) {
        player.addItem(item, false);
    }

    @Override
    public void takeIn(SkyblockPlayer player) {
        item = player.getInventory().getItem(slot).clone();
        player.getInventory().getItem(slot).setAmount(0);
    }

    @Override
    public boolean check(SkyblockPlayer player) {
        return player.getInventory().getItem(slot) != null;
    }
}
