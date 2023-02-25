package me.CarsCupcake.SkyblockRemake.utils.Exceptions;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Assert;
import org.jetbrains.annotations.NotNull;

public class NotEnouthCoinsException extends RuntimeException{
    public NotEnouthCoinsException(SkyblockPlayer player, double amount){
        super("Player '" + player.getName() + "' tried to buy something for "+amount+", but only had " + player.coins);
    }
    public static void precheck(@NotNull SkyblockPlayer player, double amount)throws NotEnouthCoinsException{
        Assert.notNull(player);
        if(player.coins < amount)
            throw new NotEnouthCoinsException(player, amount);
    }
}
