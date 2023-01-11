package me.CarsCupcake.SkyblockRemake.Skyblock;

import org.jetbrains.annotations.Contract;

public enum ServerType {

    Hub("hub", 25565),
    DwarvenMines("dwarven", 25564),
    CrimsonIsle("crimsonisle", 25568),
    TheInstance("kuudra", 25569),
    DungeonHub("dh", 25570),
    F7("f7", 25567),
    F6("f6",25572),
    SpidersDen("spidersden",25573),
    End("EndIsle", 25571);
    public final String s;
    public final int ip;
    ServerType(String s, int i){
        this.s = s;
        ip = i;

    }

    @Contract(pure = true)
    public static ServerType getFromString(String s){
        ServerType type = null;

        for (ServerType types : ServerType.values())
            if(types.s.equals(s))
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

}
