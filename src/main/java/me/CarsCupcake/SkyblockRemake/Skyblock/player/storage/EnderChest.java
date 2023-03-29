package me.CarsCupcake.SkyblockRemake.Skyblock.player.storage;

import me.CarsCupcake.SkyblockRemake.Configs.CustomConfig;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.GUI;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.InventoryBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.TemplateItems;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.scheduler.BukkitRunnable;

public class EnderChest {
    public EnderChest(SkyblockPlayer player, int page) {
        InventoryBuilder builder = new InventoryBuilder(6, "Ender Chest (" + page + "/9)").fill(TemplateItems.EmptySlot.getItem(), 0, 8);
        CustomConfig config = new CustomConfig(player, "enderchest\\" + page);
        ConfigurationSection section = config.get().getConfigurationSection("slots");
        if (section != null && section.getKeys(false) != null) {
            for (String key : section.getKeys(false)) {
                int slot = Integer.parseInt(key);
                builder.setItem(config.get().getItemStack("slots." + key), slot);
            }
        }
        GUI gui = new GUI(builder.build());
        gui.setGeneralAction((slot, actionType, type) -> {
            if (actionType != GUI.GUIActions.Click) return false;
            return slot <= 8;
        });
        gui.closeAction(type -> new BukkitRunnable() {
            @Override
            public void run() {
                CustomConfig c = new CustomConfig(player, "enderchest\\" + page);
                for (int i = 9; i < 54; i++) {
                    if (gui.getInventory().getItem(i) == null) continue;
                    if (gui.getInventory().getItem(i).getType() == Material.AIR) c.get().set("slots." + i, null);
                    c.get().set("slots." + i, gui.getInventory().getItem(i));
                }
                c.save();
            }
        }.runTaskAsynchronously(Main.getMain()));
        gui.showGUI(player);
    }
}
