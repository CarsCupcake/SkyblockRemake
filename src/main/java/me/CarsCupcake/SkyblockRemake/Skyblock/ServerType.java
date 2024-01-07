package me.CarsCupcake.SkyblockRemake.Skyblock;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.jetbrains.annotations.Contract;

public enum ServerType {

    Hub("hub", 25565, new Location(Bukkit.getServer().getWorld("world"), -2.5, 70, -69.5, 180, 0), "hub"),
    Non("", 0, Bukkit.getWorlds().get(0).getSpawnLocation(), "non"),
    DwarvenMines("dwarven", 25564, new Location(Bukkit.getServer().getWorld("world"), -48.5, 200, -121.5, 270, 0), "mines"),
    CrimsonIsle("crimsonisle", 25568, new Location(Bukkit.getServer().getWorld("world_nether"), -360.5, 80, -426.5, 180, 0), "nether"),
    TheInstance("kuudra", 25569, new Location(Bukkit.getServer().getWorld("world"), -101.5, 79.25, -187.5, 0, 0), "kuudra"),
    DungeonHub("dh", 25570, new Location(Bukkit.getServer().getWorld("world"), -29.5, 121, 0.5, 90, 0), "dungeon_hub"),
    F1("f1", 25567, new Location(Bukkit.getServer().getWorld("world"), -42.5, 71.5, 42.5, 180, 0), "f1"),
    F7("f7", 25567, new Location(Bukkit.getServer().getWorld("world"), -73.5, 221.5, -15.5, 0, 0), "f7"),
    F6("f6",25572, new Location(Bukkit.getServer().getWorld("world"), -8.5, 69.5, -0.5, 0, 0), "f6"),
    SpidersDen("spidersden",25573, new Location(Bukkit.getServer().getWorld("world"), -202.5, 83.5, -233.5, 0, 0), "spider"),
    End("EndIsle", 25571, new Location(Bukkit.getServer().getWorld("world"), -500, 101.5, -275, 0, 0), "end"),
    //Standart Loc = 7.5, 100, 7.5
    PrivateIsle("private", 25574, null, "private_isle"),
    F3("f3", 255575, new Location(Bukkit.getServer().getWorld("world"), 1.5, 69.5, -30.5, 0, 0), "f3"),
    Dungeon("dungeon", 25576, new Location(Bukkit.getWorld("world"), 0, 100, 0), "dungeon"),
    Rift("rift", 25575, new Location(Bukkit.getWorld("world_the_end"), -47.5, 122, 68.5), "rift");
    public final String s;
    public final int ip;
    @Getter
    private final Location location;
    @Getter
    private final String name;
    ServerType(String s, int i, Location spawnLocation, String name){
        this.s = s;
        ip = i;
        this.location = spawnLocation;
        this.name = name;
    }

    @Contract(pure = true)
    public static ServerType getFromString(String s){
        ServerType type = Non;

        for (ServerType types : ServerType.values())
            if(types.s.equalsIgnoreCase(s))
                type = types;

        return type;
    }

    @Contract(pure = true)
    public static ServerType fromName(String s){
        ServerType type = Non;

        for (ServerType types : ServerType.values())
            if(types.name.equalsIgnoreCase(s))
                type = types;

        return type;
    }

    public static ServerType getServerByPort(int port){
        ServerType type = null;
        for (ServerType types : ServerType.values())
            if(types.ip == port)
                type = types;
        return type;
    }

    public static ServerType getActiveType(){
        return SkyblockServer.getServer().type();
    }
}
