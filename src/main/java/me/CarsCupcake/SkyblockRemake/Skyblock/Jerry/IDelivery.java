package me.CarsCupcake.SkyblockRemake.Skyblock.Jerry;

import me.CarsCupcake.SkyblockRemake.Configs.CustomConfig;
import me.CarsCupcake.SkyblockRemake.Skyblock.Jerry.Deliverys.CoinsDelivery;
import me.CarsCupcake.SkyblockRemake.Skyblock.Jerry.Deliverys.ItemDelivery;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public interface IDelivery {
    ItemStack getShowItem();
    void claim();
    void save();
    @NotNull
    ArrayList<IDelivery> getDeliverys(SkyblockPlayer player);



    CustomConfig config = new CustomConfig("deliverys");

    static ArrayList<IDelivery> getAll(SkyblockPlayer player){
        ArrayList<IDelivery> deliveries =  new ItemDelivery().getDeliverys(player);
        deliveries.addAll(new CoinsDelivery().getDeliverys(player));
        return deliveries;
    }
}
