package me.CarsCupcake.SkyblockRemake.isles.privateIsle;

import me.CarsCupcake.SkyblockRemake.Configs.CustomConfig;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;

public class PrivateIslandManager {
    public static final HashMap<SkyblockPlayer, Location> baseLocations = new HashMap<>();
    //Max travel distance: 120, Every so + 300 for a new isle
    public static void addToIsle(SkyblockPlayer player){
        CustomConfig config = new CustomConfig(player, "PrivateIsle");
        ConfigurationSection section = config.get().getConfigurationSection("privateSpace");
        Location isleLocation;
        if(section == null){
            isleLocation = createNewIsle();
            config.get().set("privateSpace.x", isleLocation.getX());
            config.get().set("privateSpace.y", isleLocation.getY());
            config.get().set("privateSpace.z", isleLocation.getZ());
            config.save();
        }else
            isleLocation = Location.deserialize(section.getValues(false));
        player.teleport(isleLocation);
        baseLocations.put(player, isleLocation);
    }

    private static Location createNewIsle(){
        CustomConfig config = new CustomConfig("privateIsle");
        int distance = config.get().getInt("distanceBetweenLocs", 0);
        config.get().set("distanceBetweenLocs", distance + 300);
        config.save();
        Location base = new Location(Bukkit.getWorld("world"),7.5 + distance,100,7.5);
        Tools.loadShematic("assets/schematics/privateIsle.schem", base);
        return base;
    }
}
