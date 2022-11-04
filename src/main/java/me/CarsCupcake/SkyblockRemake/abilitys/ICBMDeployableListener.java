package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ICBMDeployableListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(event.getView().getTitle().equals("Your Missle:")){
            event.setCancelled(true);
            if(event.getClickedInventory() == null){
                return;
            }
            final SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer((Player) event.getWhoClicked());
            ItemStack clickedItem = event.getView().getBottomInventory().getItem(event.getSlot());
            final int fuelAmount = player.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fueling"), PersistentDataType.INTEGER);
            //Check if the inventory is the players one
            if(event.getClickedInventory().equals(event.getView().getBottomInventory())){

                if(clickedItem == null || clickedItem.getItemMeta() == null || clickedItem.getItemMeta().getPersistentDataContainer() == null)
                    return;

                if(fuelAmount >= 5) {
                    player.sendMessage("§cYou already have the max fuel amount!");
                    return;
                }
                String id = clickedItem.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING);
                if(!id.equals(Items.uraniumFuel().itemID)) {
                    player.sendMessage("§cThis is not a fuel!");

                    return;
                }
                ItemStack item = player.getItemInHand();
                ItemMeta meta = item.getItemMeta();
                PersistentDataContainer data = meta.getPersistentDataContainer();
                data.set(new NamespacedKey(Main.getMain(), "fueling"), PersistentDataType.INTEGER, fuelAmount +1 );
                System.out.println(data.get(new NamespacedKey(Main.getMain(), "fueling"), PersistentDataType.INTEGER ));
                item.setItemMeta(meta);
                player.setItemInHand(item);
                player.sendMessage("§aFuel has been added to your missle!");
                clickedItem.setAmount(clickedItem.getAmount() - 1);
                player.getInventory().setItem(event.getSlot(), clickedItem);
                ICBMDeployerAbility.updatePlayersFuelInventory(player);
                return;


            }
            //Check if the inventory is the missle one
            if(event.getClickedInventory().equals(event.getView().getTopInventory())){
                if(!Tools.isInRange(10, 15, event.getSlot()))
                    return;
                if(fuelAmount <= 0){
                    player.sendMessage("§cThere is no fuel to take!");
                    return;
                }
                if(!Tools.isInRange(10, 11+ fuelAmount, event.getSlot())){
                    player.sendMessage("§cThere is no fuel to take!");
                    return;
                }
                if(!Tools.hasFreeSlot(player)){
                    player.sendMessage("§cNo inventory space!");
                    return;
                }

                ItemStack item = player.getItemInHand();
                ItemMeta meta = item.getItemMeta();
                PersistentDataContainer data = meta.getPersistentDataContainer();
                data.set(new NamespacedKey(Main.getMain(), "fueling"), PersistentDataType.INTEGER, fuelAmount -1 );
                item.setItemMeta(meta);
                player.setItemInHand(item);
                player.sendMessage("§aFuel has been removed from your missle!");
                ICBMDeployerAbility.updatePlayersFuelInventory(player);
                player.getInventory().addItem(Items.uraniumFuel().getRawItemStack());


            }
        }
    }
    @EventHandler
    public void armorstandInteractionEvent(PlayerInteractAtEntityEvent event) {
        if(event.getRightClicked().getType() == EntityType.ARMOR_STAND)
            event.setCancelled(true);
    }


}
