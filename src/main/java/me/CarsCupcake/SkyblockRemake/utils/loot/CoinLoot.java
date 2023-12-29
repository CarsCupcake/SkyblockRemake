package me.CarsCupcake.SkyblockRemake.utils.loot;

import me.CarsCupcake.SkyblockRemake.Items.CoinsItem;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;

public record CoinLoot(double amount) implements Loot {
    @Override
    public void consume(SkyblockPlayer killer, Location dropLocation, boolean toPlayer) {
        if (toPlayer)
            killer.addCoins(amount);
        else dropLocation.getWorld().dropItemNaturally(dropLocation, new CoinsItem(amount).createNewItemStack());
    }

    @Override
    public String name() {
        return "§6" + amount + " coins";
    }
}
