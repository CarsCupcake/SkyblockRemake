package me.CarsCupcake.SkyblockRemake.Dungeon.Generation;

import com.mojang.authlib.GameProfile;
import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Dungeon.DungeonRoomsTypes;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.profile.PlayerProfile;

import java.util.Random;

public class Generator {
    @Getter
    private final LocationMap map;
    public Generator(){
        // 0.5 1.5
        // 0.4 1.4
        // 0.3 1.3
        // 0.2 1.2
        // 0.1 1.1
        // 0.0 1.0
        map = new LocationMap();

        map.setRed(new Room(DungeonRoomsTypes.red, new Location(new Random().nextInt(6),5)));
        map.setGreen(new Room(DungeonRoomsTypes.red, new Location(new Random().nextInt(6),5)));

    }
    @Deprecated
    public Generator(int size){
        this();
    }
}
