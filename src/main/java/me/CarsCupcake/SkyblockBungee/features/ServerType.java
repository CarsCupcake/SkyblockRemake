package me.CarsCupcake.SkyblockBungee.features;

import lombok.Getter;
import org.jetbrains.annotations.Contract;

public enum ServerType {

    Hub("hub", 25565, "hub", "resources/worlds/hub.zip"),
    Non("", 0, "non"),
    DwarvenMines("dwarven", 25564, "mines", ""),
    CrimsonIsle("crimsonisle", 25568, "nether", "resources/worlds/CrimsonIsle.zip"),
    TheInstance("kuudra", 25569, "kuudra"),
    DungeonHub("dh", 25570, "dungeon_hub"),
    F1("f1", 25567, "f1", "resources/worlds/f1.zip"),
    F7("f7", 25567, "f7", "resources/worlds/f7.rar"),
    F6("f6",25572, "f6", "resources/worlds/f6.zip"),
    SpidersDen("spidersden",25573,  "spider"),
    End("EndIsle", 25571, "end", "resources/worlds/EndIsle.zip"),
    //Standart Loc = 7.5, 100, 7.5
    PrivateIsle("private", 25574, "private_isle"),
    F3("f3", 255575, "f3"),
    Dungeon("dungeon", 25576, "dungeon"),
    Rift("rift", 25575, "rift", "resources/worlds/RiftIlse.zip");
    public final String s;
    public final int ip;
    @Getter
    private final String name;
    @Getter
    private final String githubPath;
    ServerType(String s, int i, String name){
        this(s, i, name, null);
    }
    ServerType(String s, int i, String name, String githubPath){
        this.s = s;
        ip = i;
        this.name = name;
        this.githubPath = githubPath;
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
            if(types.s.equalsIgnoreCase(s))
                type = types;

        return type;
    }

}
