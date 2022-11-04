package me.CarsCupcake.SkyblockRemake.FishingSystem;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public interface FishingLoottable {
    LivingEntity summonSeaCreature(SkyblockPlayer player, Location spawnLoc, Vector vector);
    ItemStack getDrop(SkyblockPlayer player);
}
