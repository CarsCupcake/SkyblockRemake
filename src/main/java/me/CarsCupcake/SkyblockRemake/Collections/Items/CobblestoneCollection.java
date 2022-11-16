package me.CarsCupcake.SkyblockRemake.Collections.Items;

import me.CarsCupcake.SkyblockRemake.Collections.CollectHandler;
import me.CarsCupcake.SkyblockRemake.Collections.ICollection;
import me.CarsCupcake.SkyblockRemake.Collections.ItemCollection;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.GUI;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.InventoryBuilder;
import org.bukkit.Material;

public class CobblestoneCollection extends ItemCollection {
    public CobblestoneCollection(SkyblockPlayer player) {
        super(player);
    }

    @Override
    public int getMaxLevels() {
        return 10;
    }

    @Override
    public int[] collectAmount() {
        return new int[]{50,100, 250,1000,2500,5000,10000,25000,40000,70000};
    }

    @Override
    public GUI getInventory() {
        InventoryBuilder builder = CollectHandler.getBaseInventory("Cobblestone", this, Items.SkyblockItems.get(Material.COBBLESTONE.toString()).createNewItemStack());
        int iI = 18;
        for (int i = 1; i <= getMaxLevels(); i++){
            builder.setItem(CollectHandler.getProgressItem(this, i, "Cobblestone").build(),iI);
            iI++;
        }

        GUI gui = new GUI(builder.build());
        gui.setCanceled(true);
        gui.inventoryClickAction(49, type -> gui.closeInventory());
        return gui;
    }

    @Override
    public void sendLevelUpMessage(int level) {
        player.sendMessage("§d§lBoop! §fYou leveled cobble up to level: " + level);
    }

    @Override
    public ICollection makeNew(SkyblockPlayer player) {
        return new CobblestoneCollection(player);
    }

    @Override
    public String getCollecteItemId() {
        return Material.COBBLESTONE.toString();
    }
}
