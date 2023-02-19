package me.CarsCupcake.SkyblockRemake.utils.Inventory

import me.CarsCupcake.SkyblockRemake.utils.Inventorys.GUIAction
import org.bukkit.inventory.Inventory

interface GuiTemplate {
    fun getInventory(): Inventory
    fun getSlotActions(): Map<Int, GUIAction>
}