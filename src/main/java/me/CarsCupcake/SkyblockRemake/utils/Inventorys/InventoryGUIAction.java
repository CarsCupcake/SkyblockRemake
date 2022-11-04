package me.CarsCupcake.SkyblockRemake.utils.Inventorys;

import org.bukkit.event.inventory.ClickType;

public interface InventoryGUIAction {
    void run(int slot, GUI.GUIActions actionType, ClickType type);
}
