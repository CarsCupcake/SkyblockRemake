package me.CarsCupcake.SkyblockRemake.Items.farming;

import me.CarsCupcake.SkyblockRemake.Items.farming.crops.*;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Collections.ICollection;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Material;
import org.bukkit.block.data.Ageable;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class FarmingUtils implements Listener {
    public static final HashMap<Material, Double> farmingXp = new HashMap<>();
    public static final HashMap<Material, Crop> crops = new HashMap<>();
    public FarmingUtils(){
        registerCrop(new Potato(), 4);
        registerCrop(new Carrot(), 4);
        registerCrop(new Cocoa(), 4);
        registerCrop(new Melon(), 4);
        registerCrop(new Pumpkin(), 4.5);
        registerCrop(new Wheat(), 4);
    }
    private void registerCrop(Crop crop, double xp){
        farmingXp.put(crop.getBlockType(), xp);
        crops.put(crop.getBlockType(), crop);
    }
    public static int getLogarithmicCounter(int i){
        int lenght = (i + "").length();
        return ((lenght < 4) ? 0 : lenght - 4) * 16;
    }
    public static int getCollectionAnalysis(ICollection collection){
        int lenght = (collection.getCollected() + "").length();
        return ((lenght < 4) ? 0 : lenght - 4) * 8;
    }
    public static void cropBreak(BlockBreakEvent event){
        event.setDropItems(false);

        if(event.getBlock().getBlockData() instanceof Ageable ageable)
            if(ageable.getAge() < 7)
                return;

        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
        ItemStack item = crops.get(event.getBlock().getType()).makeDrop(player, event.getBlock());
        if(player.isAutoPickup())
            player.addItem(item, true);
        else
            event.getBlock().getWorld().dropItemNaturally(Tools.getAsLocation(event.getBlock()), item);
    }
}
