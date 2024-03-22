package me.CarsCupcake.SkyblockRemake.utils.Inventories

import me.CarsCupcake.SkyblockRemake.API.Bundle
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer
import me.CarsCupcake.SkyblockRemake.utils.Assert
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class MultipleGui(
    private val inventorys: List<Inventory>, private val nextPageSlot: Int = 53, private val previousInventorySlot: Int = 45
) : GUI(inventorys[0]) {
    private var currentPage: Int = 0
    private var nextItem: ItemStack = TemplateItems.NextArrow.item
    private var prevItem: ItemStack = TemplateItems.BackArrow.item
    private var specialSlotSwap: HashMap<Int, Bundle<Int, Int>> = HashMap()

    init {
        Assert.isTrue(inventorys.isNotEmpty())
    }

    fun getCurrentPage(): Int {
        return currentPage
    }

    fun openPage(page: Int): Inventory {
        Assert.isTrue(page < inventorys.size, "Trying to get page $page from a max amount of ${inventorys.size} pages")
        val inventory = inventorys[page]
        currentPage = page
        super.swapInventory(inventory)
        prepPage()
        return inventory
    }

    fun addSpecialSlotSwap(page: Int, nextPageSlot: Int, previousInventorySlot: Int) {
        specialSlotSwap[page] = Bundle(nextPageSlot, previousInventorySlot)
    }

    override fun showGUI(player: SkyblockPlayer?) {
        prepPage()
        super.showGUI(player)
    }
    private fun prepPage() {
        var nextPageSlot = nextPageSlot
        var previousInventorySlot = previousInventorySlot

        if (specialSlotSwap.containsKey(currentPage)) {
            nextPageSlot = specialSlotSwap[currentPage]?.first ?: this.nextPageSlot
            previousInventorySlot = specialSlotSwap[currentPage]?.last ?: this.previousInventorySlot
        }

        inventoryClickAction.remove(nextPageSlot)
        if (inventorys.size - 1 != currentPage) {
            inventory.setItem(nextPageSlot, nextItem)
            inventoryClickAction(nextPageSlot) {
                openPage(currentPage + 1)
            }
        } else if (inventoryClickAction.containsKey(nextPageSlot)) inventoryClickAction.remove(nextPageSlot)
        inventoryClickAction.remove(previousInventorySlot)
        if (currentPage != 0) {
            inventory.setItem(previousInventorySlot, prevItem)
            inventoryClickAction(previousInventorySlot) {
                openPage(currentPage - 1)
            }
        } else if (inventoryClickAction.containsKey(previousInventorySlot)) inventoryClickAction.remove(
            previousInventorySlot
        )
    }
    fun getInventories() : List<Inventory> {
        return inventorys
    }
}