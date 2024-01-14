package me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.kuudra.pickups;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.runnable.RunnableWithParam;
import org.bukkit.Material;

public enum PickupTypes implements RunnableWithParam<SkyblockPlayer> {
    Creates("Create", Material.CHEST) {

        @Override
        public void run(SkyblockPlayer player) {

        }
    };

    PickupTypes(String name, Material itemTypes) {

    }
}
