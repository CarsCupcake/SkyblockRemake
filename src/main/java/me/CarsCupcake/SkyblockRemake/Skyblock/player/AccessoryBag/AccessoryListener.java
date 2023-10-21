package me.CarsCupcake.SkyblockRemake.Skyblock.player.AccessoryBag;


import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.persistence.PersistentDataType;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.configs.AccessoryBag;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class AccessoryListener implements Listener {
    public static HashMap<SkyblockPlayer, Set<ArtifactAbility>> activeAbilitys = new HashMap<>();

    @EventHandler
    public void accessoryBagSiteSwapping(InventoryClickEvent event) {
        if (!event.getView().getTitle().contains("Accessory Bag (")) return;
        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getItemMeta() == null) return;

        Player player = (Player) event.getWhoClicked();


        if (event.getClickedInventory().getType() == InventoryType.PLAYER) {
            if (Items.SkyblockItems.get(event.getClickedInventory().getItem(event.getSlot()).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING)).type == ItemType.Accessory)
                return;
            else event.setCancelled(true);
            player.sendMessage("§c§lHey! §r§cYou are only able to put Accessorys in!");
            return;
        }

        if (event.getView().getTopInventory().getItem(event.getSlot()).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "border"), PersistentDataType.STRING) != null && event.getView().getTopInventory().getItem(event.getSlot()).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "border"), PersistentDataType.STRING).equals("true")) {
            event.setCancelled(true);
        }


        int page = event.getView().getTopInventory().getItem(53).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "page"), PersistentDataType.INTEGER);

        if (event.getSlot() == 53) {
            event.setCancelled(true);
            if (page < AccessoryInventory.activeInvs.get(player).invs.size()) {
                saveInventory(player, page, event.getView().getTopInventory());
                player.openInventory(AccessoryInventory.activeInvs.get(player).invs.get(page));
                player.updateInventory();
            }
            return;
        }

        if (event.getSlot() == 45) {
            event.setCancelled(true);
            if (page != 1) {


                saveInventory(player, page, event.getView().getTopInventory());
                player.openInventory(AccessoryInventory.activeInvs.get(player).invs.get(page - 2));
                player.updateInventory();
            }
            return;
        }
        if (event.getSlot() > 45) {
            event.setCancelled(true);

        }


    }


    private void saveInventory(Player p, int page, Inventory inv) {
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(p);

        int startSlot = (45 * page) - 45;
        int invslot = 0;
        AccessoryBag.reload();
        for (int i = startSlot; i < startSlot + 45; i++) {
            String baseString = player.getUniqueId() + ".SLOT_" + i;
            if (inv.getItem(invslot) != null && !(inv.getItem(invslot).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "border"), PersistentDataType.STRING) != null && inv.getItem(invslot).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "border"), PersistentDataType.STRING).equals("true"))) {
                String id = inv.getItem(invslot).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING);

                Integer recomed = inv.getItem(invslot).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "recomed"), PersistentDataType.INTEGER);
                AccessoryBag.get().set(baseString, true);
                AccessoryBag.get().set(baseString + ".id", id);
                AccessoryBag.get().set(baseString + ".item", inv.getItem(invslot));
                if (recomed == null || recomed == 0) {
                    AccessoryBag.get().set(baseString + ".recom", false);
                } else {
                    AccessoryBag.get().set(baseString + ".recom", true);
                }
            } else {
                if (!(inv.getItem(invslot) != null && inv.getItem(invslot).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "border"), PersistentDataType.STRING) != null && inv.getItem(invslot).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "border"), PersistentDataType.STRING).equals("true"))) {
                    AccessoryBag.get().set(baseString, false);
                }
            }

            invslot++;
        }
        AccessoryBag.save();
        AccessoryBag.reload();
        Main.calculateMagicalPower(player);
    }

    @EventHandler
    public void saveInventoryOnCLose(InventoryCloseEvent event) {
        if (!event.getView().getTitle().contains("Accessory Bag (")) return;


        Player player = (Player) event.getPlayer();
        int page = event.getView().getTopInventory().getItem(53).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "page"), PersistentDataType.INTEGER);
        saveInventory(player, page, event.getView().getTopInventory());
        new BukkitRunnable() {
            @Override
            public void run() {
                startupAbilitys(SkyblockPlayer.getSkyblockPlayer(player));
            }
        }.runTaskAsynchronously(Main.getMain());
    }

    public static void startupAbilitys(SkyblockPlayer player) {
        if (activeAbilitys.containsKey(player)) {
            for (ArtifactAbility ability : activeAbilitys.get(player))
                ability.stop(player);
            activeAbilitys.remove(player);
        }

        AccessoryBag.reload();
        ConfigurationSection section = AccessoryBag.get().getConfigurationSection(player.getUniqueId() + "");
        if (section == null) return;
        Set<String> keys = section.getKeys(false);
        if (keys == null) return;
        Set<ArtifactAbility> abilities = new HashSet<>();
        for (String s : keys) {
            if (!s.startsWith("SLOT_")) continue;
            String id = section.getString(s + ".id");
            if (id == null || id.equals("")) continue;
            ItemManager manager = Items.SkyblockItems.get(id);
            if (manager == null) continue;
            if (manager.getArtifactAbility() == null) continue;
            Main.getMain().getServer().getScheduler().runTask(Main.getMain(), () -> manager.getArtifactAbility().start(SkyblockPlayer.getSkyblockPlayer(player), section.getItemStack(s + ".item")));
        }
        activeAbilitys.put(player, abilities);
    }

}
