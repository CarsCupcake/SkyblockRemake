package me.CarsCupcake.SkyblockBungee.features;

import lombok.Getter;
import org.jetbrains.annotations.Contract;

public enum ServerType {

    Hub("hub", 25565, "hub"),
    Non("", 0, "non"),
    DwarvenMines("dwarven", 25564, "mines"),
    CrimsonIsle("crimsonisle", 25568, "nether"),
    TheInstance("kuudra", 25569, "kuudra"),
    DungeonHub("dh", 25570, "dungeon_hub"),
    F1("f1", 25567, "f1"),
    F7("f7", 25567, "f7"),
    F6("f6",25572, "f6"),
    SpidersDen("spidersden",25573,  "spider"),
    End("EndIsle", 25571, "end"),
    //Standart Loc = 7.5, 100, 7.5
    PrivateIsle("private", 25574, "private_isle"),
    F3("f3", 255575, "f3"),
    Dungeon("dungeon", 25576, "dungeon"),
    Rift("rift", 25575, "rift");
    public final String s;
    public final int ip;
    @Getter
    private final String name;
    ServerType(String s, int i, String name){
        this.s = s;
        ip = i;
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
            if(types.s.equalsIgnoreCase(s))
                type = types;

        return type;
    }

}
