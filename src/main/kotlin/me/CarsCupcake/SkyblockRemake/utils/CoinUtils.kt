package me.CarsCupcake.SkyblockRemake.utils

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer
import me.CarsCupcake.SkyblockRemake.utils.Exceptions.NotEnouthCoinsException

object CoinUtils {
    /**
     * @param player the player
     * @param amount the amount of coins
     * @return if the transaction was possible
     */
    fun spendIfEnouth(player: SkyblockPlayer, amount: Double): Boolean{
        if (amount > player.coins)
            return false
        else
            spend(player, amount)
        return true
    }

    fun spend(player: SkyblockPlayer, amount: Double, tax: Double = 1.0){
        val finalAmount = amount * tax
        NotEnouthCoinsException.precheck(player, finalAmount)
        player.coins -= finalAmount
    }
}