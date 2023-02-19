package me.CarsCupcake.SkyblockRemake.abilitys

import me.CarsCupcake.SkyblockRemake.Items.*
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer
import me.CarsCupcake.SkyblockRemake.Slayer.MaddoxMenu
import org.bukkit.event.player.PlayerInteractEvent

class MaddoxBatphone{
    init {
        val manager = ItemManager("Maddox Batphone", "AATROX_BATPHONE", ItemType.Non, ItemRarity.UNCOMMON, "http://textures.minecraft.net/texture/9336d7cc95cbf6689f5e8c954294ec8d1efc494a4031325bb427bc81d56a484d")
        manager.isUnstackeble = true
        manager.setAbility(
            AbilityManager<PlayerInteractEvent>{
                 MaddoxMenu.openMenu(SkyblockPlayer.getSkyblockPlayer(it?.player))
                true
        }, AbilityType.RightClick, "Whassup?", 0, 0
        )
        manager.abilityLore = arrayListOf("§7Lets you call §7Maddox§7, when", "§7he's not busy.")
        Items.SkyblockItems[manager.itemID] = manager
    }
}