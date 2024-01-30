package me.CarsCupcake.SkyblockRemake.Skyblock;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.jetbrains.annotations.Contract;

public enum ServerType {

    Hub("hub", 25565, new Location(Bukkit.getServer().getWorld("world"), -2.5, 70, -69.5, 180, 0), "hub", "https://www.mediafire.com/file/gqtcg573rctpba0/hub.zip/file"),
    Non("", 0, Bukkit.getWorlds().get(0).getSpawnLocation(), "non"),
    DwarvenMines("dwarven", 25564, new Location(Bukkit.getServer().getWorld("world"), -48.5, 200, -121.5, 270, 0), "mines", "https://www.mediafire.com/file/wi0cx6tu3tnhj0h/DwarvenMines.zip/file"),
    CrimsonIsle("crimsonisle", 25568, new Location(Bukkit.getServer().getWorld("world_nether"), -360.5, 80, -426.5, 180, 0), "nether", "https://www.mediafire.com/file/uix7mk88qxq92e7/CrimsonIsle.zip/file"),
    TheInstance("kuudra", 25569, new Location(Bukkit.getServer().getWorld("world"), -101.5, 79.25, -187.5, 0, 0), "kuudra", null),
    DungeonHub("dh", 25570, new Location(Bukkit.getServer().getWorld("world"), -29.5, 121, 0.5, 90, 0), "dungeon_hub", ""),
    F1("f1", 25567, new Location(Bukkit.getServer().getWorld("world"), -42.5, 71.5, 42.5, 180, 0), "f1", "https://www.mediafire.com/file/1mnpq0zc3g6f6dm/f1.zip/file"),
    F7("f7", 25567, new Location(Bukkit.getServer().getWorld("world"), -73.5, 221.5, -15.5, 0, 0), "f7", "https://www.mediafire.com/file/kmyj2ee1zlz88z3/f7.rar/file"),
    F6("f6",25572, new Location(Bukkit.getServer().getWorld("world"), -8.5, 69.5, -0.5, 0, 0), "f6", "https://www.mediafire.com/file/qz14snmrel1o2o5/f6.zip/file"),
    SpidersDen("spidersden",25573, new Location(Bukkit.getServer().getWorld("world"), -202.5, 83.5, -233.5, 0, 0), "spider"),
    End("EndIsle", 25571, new Location(Bukkit.getServer().getWorld("world"), -500, 101.5, -275, 0, 0), "end", "https://www.mediafire.com/file/ekkbqgl0ijeq1uv/EndIsle.zip/file"),
    //Standart Loc = 7.5, 100, 7.5
    PrivateIsle("private", 25574, null, "private_isle"),
    F3("f3", 255575, new Location(Bukkit.getServer().getWorld("world"), 1.5, 69.5, -30.5, 0, 0), "f3"),
    Dungeon("dungeon", 25576, new Location(Bukkit.getWorld("world"), 0, 100, 0), "dungeon"),
    Rift("rift", 25575, new Location(Bukkit.getWorld("world_the_end"), -47.5, 122, 68.5), "rift", "https://www.mediafire.com/file/pdznua1y1fnw1a3/RiftIlse.zip/file");
    public final String s;
    public final int ip;
    @Getter
    private final Location location;
    @Getter
    private final String name;
    @Getter
    private final String url;
    ServerType(String s, int i, Location spawnLocation, String name, String downloadUrl){
        this.s = s;
        ip = i;
        this.location = spawnLocation;
        this.name = name;
        url = downloadUrl;
    }
    ServerType(String s, int i, Location spawnLocation, String name){
        this(s, i, spawnLocation, name, null);
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
