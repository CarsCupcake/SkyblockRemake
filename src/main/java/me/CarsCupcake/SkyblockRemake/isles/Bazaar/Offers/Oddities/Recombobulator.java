package me.CarsCupcake.SkyblockRemake.isles.Bazaar.Offers.Oddities;

import me.CarsCupcake.SkyblockRemake.isles.Bazaar.BazaarOffer;
import me.CarsCupcake.SkyblockRemake.isles.Bazaar.BazaarType;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.Items;

public class Recombobulator extends BazaarOffer {
    @Override
    public BazaarType getType() {
        return BazaarType.Oddities;
    }

    @Override
    public ItemManager getItem() {
        return Items.SkyblockItems.get("RECOMBOBULATOR_3000");
    }
    @Override
    public int[] getItemSizes(){
        return new int[]{
                1,
                4,
                32
        };
    }

}
