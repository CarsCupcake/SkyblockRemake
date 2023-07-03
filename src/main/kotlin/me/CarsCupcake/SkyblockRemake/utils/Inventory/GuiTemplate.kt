package me.CarsCupcake.SkyblockRemake.utils.Inventory

import me.CarsCupcake.SkyblockRemake.utils.Inventories.GUIAction
import org.bukkit.inventory.Inventory

abstract class GuiTemplate {
    abstract fun getInventory(): Inventory
    abstract fun getSlotActions(): Map<Int, GUIAction>
    open fun isCanceled():Boolean{
        return false
    }
}