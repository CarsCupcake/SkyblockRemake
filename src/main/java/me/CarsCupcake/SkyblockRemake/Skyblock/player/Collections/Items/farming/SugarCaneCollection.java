package me.CarsCupcake.SkyblockRemake.Skyblock.player.Collections.Items.farming;

import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Collections.CollectHandler;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Collections.ICollection;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Collections.ItemCollection;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.GUI;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.InventoryBuilder;
import org.bukkit.Material;

public class SugarCaneCollection extends ItemCollection {
    public SugarCaneCollection(SkyblockPlayer player) {
        super(player);
    }

    @Override
    public int getMaxLevels() {
        return 9;
    }
    @Override
    public String getName() {
        return "Sugar Cane Collection";
    }
    @Override
    public int[] collectAmount() {
        return new int[]{
                50,
                250,
                500,
                1000,
                2500,
                5000,
                10000,
                25000,
                50000
        };
    }

    @Override
    public GUI getInventory() {
        InventoryBuilder builder = CollectHandler.getBaseInventory("Sugar Cane", this, Items.SkyblockItems.get(Material.SUGAR_CANE + "").createNewItemStack());
        int iI = 18;
        for (int i = 1; i <= getMaxLevels(); i++){
            builder.setItem(CollectHandler.getProgressItem(this, i, "Sugar Cane").build(),iI);
            iI++;
        }

        GUI gui = new GUI(builder.build());
        gui.setCanceled(true);
        gui.inventoryClickAction(49, type -> gui.closeInventory());
        return gui;
    }

    @Override
    public void sendLevelUpMessage(int level) {
        player.sendMessage("§d§lBoop! §fYou leveled Sugar Cane up to level: " + level);
    }

    @Override
    public ICollection makeNew(SkyblockPlayer player) {
        return new SugarCaneCollection(player);
    }

    @Override
    public String getCollecteItemId() {
        return Material.SUGAR_CANE + "";
    }
}
