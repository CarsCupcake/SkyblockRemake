package me.CarsCupcake.SkyblockRemake.Items.farming;

import me.CarsCupcake.SkyblockRemake.Skyblock.player.Collections.ICollection;
import org.bukkit.Material;
import org.bukkit.event.Listener;

import java.util.HashMap;

public class FarmingUtils implements Listener {
    public static final HashMap<Material, Double> farmingXp = new HashMap<>();
    public FarmingUtils(){
        farmingXp.put(Material.WHEAT, 4d);
        farmingXp.put(Material.PUMPKIN, 4.5);
        farmingXp.put(Material.MELON, 4d);
        farmingXp.put(Material.POTATO, 4d);
        farmingXp.put(Material.CARROT, 4d);
        farmingXp.put(Material.SUGAR_CANE, 2d);
        farmingXp.put(Material.RED_MUSHROOM, 6d);
        farmingXp.put(Material.BROWN_MUSHROOM, 6d);
        farmingXp.put(Material.BROWN_MUSHROOM_BLOCK, 2d);
        farmingXp.put(Material.RED_MUSHROOM_BLOCK, 2d);
        farmingXp.put(Material.CACTUS, 2d);
        farmingXp.put(Material.COCOA, 4d);
    }
    public static int getLogarithmicCounter(int i){
        int lenght = (i + "").length();
        return ((lenght < 4) ? 0 : lenght - 4) * 16;
    }
    public static int getCollectionAnalysis(ICollection collection){
        int lenght = (collection.getCollected() + "").length();
        return ((lenght < 4) ? 0 : lenght - 4) * 8;
    }

}
