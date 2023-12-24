package me.CarsCupcake.SkyblockRemake.Items.Pets;

import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.configs.ConfigFile;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.CarsCupcake.SkyblockRemake.Main;

import java.util.ArrayList;
import java.util.List;

public class PetMenuListener implements Listener {
    @EventHandler
    public void addToPetMenu(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getItem() != null && event.getItem().getItemMeta() != null && ItemHandler.getItemManager(event.getItem()) instanceof Pet pet) {
                SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
                ConfigFile configFile = new ConfigFile(player, "pet", true);
                List<Integer> intList = new ArrayList<>();
                int lowest = 1;
                int highest = 1;
                for (String str : configFile.get().getKeys(false)) {
                    if (str.equals("equiped")) continue;
                    try {
                        int i = Integer.parseInt(str);
                        if (i <= lowest) lowest = i - 1;
                        if (i >= highest) highest = i + 1;
                    } catch (Exception e) {
                        e.printStackTrace(System.err);
                    }
                }
                int var = (lowest == 0) ? highest : lowest;
                configFile.get().set(var + ".id", pet.itemID);
                configFile.get().set(var + ".level", ItemHandler.getOrDefaultPDC("level", event.getItem(), PersistentDataType.INTEGER, 1));
                configFile.get().set(var + ".currxp", ItemHandler.getOrDefaultPDC("currxp", event.getItem(), PersistentDataType.DOUBLE, 0d));
                configFile.get().set(var + ".equiped", false);
                configFile.save();
                event.getItem().setAmount(0);
                player.updateInventory();
            }
        }
    }

    @EventHandler
    public void inventoryClickEvent(InventoryClickEvent event) {
        if (event.getView().getTitle() == null || !event.getView().getTitle().contains("Pets")) {
            return;
        }
        event.setCancelled(true);
        if (event.getClickedInventory() != null && event.getClickedInventory().getType() == InventoryType.PLAYER) {
            return;
        }
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer((Player) event.getWhoClicked());
        ConfigFile configFile = new ConfigFile(player, "pet", true);
        Inventory inv = event.getView().getTopInventory();
        ItemStack item = inv.getItem(event.getSlot());
        if (item != null && item.getItemMeta() != null && item.getItemMeta().getPersistentDataContainer() != null) {
            PersistentDataContainer data = item.getItemMeta().getPersistentDataContainer();
            if (data.get(new NamespacedKey(Main.getMain(), "pet"), PersistentDataType.STRING) != null) {
                int petfile = data.get(new NamespacedKey(Main.getMain(), "fileid"), PersistentDataType.INTEGER);
                configFile.reload();
                if (petfile == configFile.get().getInt("equiped")) {
                    //despawn pet
                    event.getWhoClicked().sendMessage("§cPet got despawned");
                    event.getWhoClicked().closeInventory();
                    configFile.get().set("equiped", 0);
                    configFile.get().set(petfile + ".equiped", false);
                    configFile.save(false);
                    player.getPetFollowRunner().remove();
                    player.setPetFollowRunner(null);
                    if (player.getPetEquip() != null) player.getPetEquip().despawn();
                } else {
                    //pet not the spawned pet/no pet equiped

                    //check if the player has a pet equiped
                    if (0 != configFile.get().getInt("equiped", 0)) {
                        //despawn old pet spawn new pet
                        event.getWhoClicked().closeInventory();

                        configFile.get().set(configFile.get().getInt("equiped") + ".equiped", false);
                        configFile.get().set("equiped", petfile);
                        configFile.get().set(petfile + ".equiped", true);
                        configFile.save(false);
                        event.getWhoClicked().sendMessage("§aSpawned new Pet");
                        player.getPetFollowRunner().remove();
                        player.setPetFollowRunner(null);
                        if (player.getPetEquip() != null) player.getPetEquip().despawn();

                    } else {
                        //spawn new pet
                        event.getWhoClicked().closeInventory();
                        configFile.get().set("equiped", petfile);
                        configFile.get().set(petfile + ".equiped", true);
                        configFile.save(false);
                        event.getWhoClicked().sendMessage("§aSpawned new Pet");
                    }
                    new PetFollowRunner(player, (Pet) Items.SkyblockItems.get(configFile.get().getString(petfile + ".id")), petfile);
                    new PetEquip(player);
                }
            }
        }


    }
}
