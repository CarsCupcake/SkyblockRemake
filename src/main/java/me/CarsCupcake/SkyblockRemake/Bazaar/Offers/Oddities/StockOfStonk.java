package me.CarsCupcake.SkyblockRemake.Bazaar.Offers.Oddities;

import me.CarsCupcake.SkyblockRemake.Bazaar.BazaarOffer;
import me.CarsCupcake.SkyblockRemake.Bazaar.BazaarType;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.Items;

public class StockOfStonk extends BazaarOffer {
    @Override
    public BazaarType getType() {
        return BazaarType.Oddities;
    }

    @Override
    public ItemManager getItem() {
        return Items.SkyblockItems.get("STOCK_OF_STONKS");
    }
}
