package me.CarsCupcake.SkyblockRemake.abilities

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer
import me.CarsCupcake.SkyblockRemake.Slayer.MaddoxMenu
import org.bukkit.event.player.PlayerInteractEvent

class MaddoxBatPhoneAbility: AbilityManager<PlayerInteractEvent> {
    override fun triggerAbility(event: PlayerInteractEvent?): Boolean {
        MaddoxMenu.openMenu(SkyblockPlayer.getSkyblockPlayer(event?.player))
        return true
    }
}