package me.CarsCupcake.SkyblockRemake.Skyblock.Jerry;

import me.CarsCupcake.SkyblockRemake.configs.ConfigFile;
import me.CarsCupcake.SkyblockRemake.Skyblock.Jerry.Deliveries.CoinsDelivery;
import me.CarsCupcake.SkyblockRemake.Skyblock.Jerry.Deliveries.ItemDelivery;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

@SuppressWarnings("unused")
public interface IDelivery {
    ItemStack getShowItem();

    void claim();

    void save();

    @NotNull ArrayList<IDelivery> getDeliverys(SkyblockPlayer player);


    ConfigFile config = new ConfigFile("deliverys");

    static ArrayList<IDelivery> getAll(SkyblockPlayer player) {
        ArrayList<IDelivery> deliveries = new ItemDelivery().getDeliverys(player);
        deliveries.addAll(new CoinsDelivery().getDeliverys(player));
        return deliveries;
    }
}
