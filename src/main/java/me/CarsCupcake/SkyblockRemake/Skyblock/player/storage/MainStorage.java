package me.CarsCupcake.SkyblockRemake.Skyblock.player.storage;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.GUI;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.InventoryBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.Items.ItemBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.TemplateItems;
import org.bukkit.Material;

public class MainStorage {
    public MainStorage(SkyblockPlayer player){
        InventoryBuilder builder = new InventoryBuilder(6, "Storage").fill(TemplateItems.EmptySlot.getItem())
                .setItem(new ItemBuilder(Material.PURPLE_STAINED_GLASS_PANE).setAmount(1).setName("§aEnder Chest Page 1").build(), 9)
                .setItem(new ItemBuilder(Material.PURPLE_STAINED_GLASS_PANE).setAmount(2).setName("§aEnder Chest Page 2").build(), 10)
                .setItem(new ItemBuilder(Material.PURPLE_STAINED_GLASS_PANE).setAmount(3).setName("§aEnder Chest Page 3").build(), 11)
                .setItem(new ItemBuilder(Material.PURPLE_STAINED_GLASS_PANE).setAmount(4).setName("§aEnder Chest Page 4").build(), 12)
                .setItem(new ItemBuilder(Material.PURPLE_STAINED_GLASS_PANE).setAmount(5).setName("§aEnder Chest Page 5").build(), 13)
                .setItem(new ItemBuilder(Material.PURPLE_STAINED_GLASS_PANE).setAmount(6).setName("§aEnder Chest Page 6").build(), 14)
                .setItem(new ItemBuilder(Material.PURPLE_STAINED_GLASS_PANE).setAmount(7).setName("§aEnder Chest Page 7").build(), 15)
                .setItem(new ItemBuilder(Material.PURPLE_STAINED_GLASS_PANE).setAmount(8).setName("§aEnder Chest Page 8").build(), 16)
                .setItem(new ItemBuilder(Material.PURPLE_STAINED_GLASS_PANE).setAmount(9).setName("§aEnder Chest Page 9").build(), 17);
        GUI gui = new GUI(builder.build());
        gui.setGeneralAction((slot, actionType, type) -> {
            if (actionType != GUI.GUIActions.Click) return true;
            int page = slot - 8;
            if(page < 1 || page > 9) return true;
            new EnderChest(player, page);
            return true;
        });
        gui.setCanceled(true);
        gui.showGUI(player);
    }
}
