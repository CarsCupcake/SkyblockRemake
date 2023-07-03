package me.CarsCupcake.SkyblockRemake.Slayer

import me.CarsCupcake.SkyblockRemake.Items.*
import me.CarsCupcake.SkyblockRemake.abilities.MaddoxBatPhoneAbility

class MaddoxBatphone{
    init {
        val manager = ItemManager("Maddox Batphone", "AATROX_BATPHONE", ItemType.Non, ItemRarity.UNCOMMON, "http://textures.minecraft.net/texture/9336d7cc95cbf6689f5e8c954294ec8d1efc494a4031325bb427bc81d56a484d")
        manager.isUnstackeble = true
        manager.addAbility(
            MaddoxBatPhoneAbility(), AbilityType.RightClick, "Whassup?",
            AbilityLore(arrayListOf("§7Lets you call §7Maddox§7, when", "§7he's not busy.")), 0, 0
        )
        Items.SkyblockItems[manager.itemID] = manager
    }
}