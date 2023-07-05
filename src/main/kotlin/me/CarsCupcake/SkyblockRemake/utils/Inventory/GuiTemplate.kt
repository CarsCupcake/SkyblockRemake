package me.CarsCupcake.SkyblockRemake.utils.Inventory

import me.CarsCupcake.SkyblockRemake.utils.Inventories.*
import org.bukkit.inventory.Inventory

abstract class GuiTemplate {
    abstract fun getInventory(): Inventory
    abstract fun getSlotActions(gui: GUI): Map<Int, GUIAction>?
    fun getClosingAction(gui: GUI): GUIAction? {
        return null;
    }
    fun getGeneralAction(gui: GUI): InventoryGUIAction? {
        return null;
    }
    open fun isCanceled():Boolean{
        return false
    }
}