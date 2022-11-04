package me.CarsCupcake.SkyblockRemake.Collections;

import me.CarsCupcake.SkyblockRemake.utils.Inventorys.GUI;

public interface Collection {
    int getLevels();
    int[] collectAmount();
    GUI getInventory();
    void collect(CollectionCollectEvent event);
}
