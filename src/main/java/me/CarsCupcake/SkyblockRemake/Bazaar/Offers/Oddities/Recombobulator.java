package me.CarsCupcake.SkyblockRemake.Bazaar.Offers.Oddities;

import me.CarsCupcake.SkyblockRemake.Bazaar.BazaarOffer;
import me.CarsCupcake.SkyblockRemake.Bazaar.BazaarType;
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
