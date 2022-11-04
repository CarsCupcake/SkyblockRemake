package me.CarsCupcake.SkyblockRemake.Equipment;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class EquipmentInvListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(!event.getView().getTitle().equalsIgnoreCase("Your Equipment and Stats"))
            return;
        if(event.getClickedInventory() == null)
            return;

    }
}
