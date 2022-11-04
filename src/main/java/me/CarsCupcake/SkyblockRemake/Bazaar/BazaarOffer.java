package me.CarsCupcake.SkyblockRemake.Bazaar;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;

public abstract class BazaarOffer {
    public abstract BazaarType getType();
    public abstract ItemManager getItem();
    //every price is for orders not instas!
    public int[] getItemSizes(){
        return new int[]{

                64,
                160,
                1024
        };

    }
}
