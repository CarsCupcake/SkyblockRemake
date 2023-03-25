package me.CarsCupcake.SkyblockRemake.Skyblock.player.Collections.Items.farming;

import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Collections.CollectHandler;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Collections.ICollection;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Collections.ItemCollection;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.GUI;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.InventoryBuilder;
import org.bukkit.Material;

public class PotatoCollection extends ItemCollection {
    public PotatoCollection(SkyblockPlayer player) {
        super(player);
    }

    @Override
    public int getMaxLevels() {
        return 9;
    }
    @Override
    public String getName() {
        return "Potato Collection";
    }
    @Override
    public int[] collectAmount() {
        return new int[]{
                100,
                250,
                500,
                1700,
                5000,
                10000,
                25000,
                50000,
                100000
        };
    }

    @Override
    public GUI getInventory() {
        InventoryBuilder builder = CollectHandler.getBaseInventory("Potato", this, Items.SkyblockItems.get("POTATO").createNewItemStack());
        int iI = 18;
        for (int i = 1; i <= getMaxLevels(); i++){
            builder.setItem(CollectHandler.getProgressItem(this, i, "Potato").build(),iI);
            iI++;
        }

        GUI gui = new GUI(builder.build());
        gui.setCanceled(true);
        gui.inventoryClickAction(49, type -> gui.closeInventory());
        return gui;
    }

    @Override
    public void sendLevelUpMessage(int level) {
        player.sendMessage("§d§lBoop! §fYou leveled Potato up to level: " + level);
    }

    @Override
    public ICollection makeNew(SkyblockPlayer player) {
        return new PotatoCollection(player);
    }

    @Override
    public String getCollecteItemId() {
        return Material.POTATO + "";
    }
}
