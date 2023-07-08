package me.CarsCupcake.SkyblockRemake.isles.rift.items;

import me.CarsCupcake.SkyblockRemake.API.ItemEvents.GetStatFromItemEvent;
import me.CarsCupcake.SkyblockRemake.Items.*;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.abilities.ABILITIES;
import me.CarsCupcake.SkyblockRemake.utils.SignGUI.SignGUI;
import me.CarsCupcake.SkyblockRemake.utils.SignGUI.SignManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class AdminItems implements Listener {
    public static final ItemManager RIFT_ADMIN_SWORD = new ItemManager("§ce§4s§cr§6e§ev§ai§bnU §9e§dh§9T §bfO §ad§er§6o§cw§4S", "RIFT_ADMIN_SWORD", ItemType.Sword, Material.GOLDEN_SWORD, ItemRarity.ADMIN);

    public static void init() {
        ABILITIES.registerEvent(new AdminItems());
        RIFT_ADMIN_SWORD.addAbility(new RiftAdminSword(), AbilityType.SneakRightClick, "Choose Damage", new AbilityLore(List.of("§7You can choose the amount of", "§5Rift Damage §7of this sword")), 0, 0);
    }
    @EventHandler
    public void onStatsChange(GetStatFromItemEvent event){
        if(event.getStat() != Stats.RiftDamage) return;
        if (ItemHandler.getPDC("id", event.getItem(), PersistentDataType.STRING).equals(RIFT_ADMIN_SWORD.itemID))
            event.addValue(ItemHandler.getOrDefaultPDC("rds", event.getItem(), PersistentDataType.DOUBLE, 0d));
    }

    public static class RiftAdminSword implements AbilityManager<PlayerInteractEvent> {

        @Override
        public boolean triggerAbility(PlayerInteractEvent event) {
            ItemStack item = event.getItem();
            new SignGUI(new SignManager(), e -> {
                try {
                    double d = Double.parseDouble(e.lines()[0]);
                    ItemHandler.setPDC("rds", item, PersistentDataType.DOUBLE, d);
                }catch (Exception ignored){
                    event.getPlayer().sendMessage("§cThis is not a valid number!");
                }
            }).withLines("", "^^^^^^^^", "Enter Amount", "here").open(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
            return false;
        }
    }
}
