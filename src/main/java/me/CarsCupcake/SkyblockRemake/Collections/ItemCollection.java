package me.CarsCupcake.SkyblockRemake.Collections;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

import java.util.HashMap;

public abstract class ItemCollection extends ICollection{
    public static final HashMap<SkyblockPlayer, HashMap<String, ItemCollection>> itemCollections = new HashMap<>();

    protected ItemCollection(SkyblockPlayer player) {
        super(player);
    }
    public abstract String getCollecteItemId();

    @Override
    public String getId() {
        return getCollecteItemId();
    }
}
