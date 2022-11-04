package me.CarsCupcake.SkyblockRemake.Skyblock;

import org.jetbrains.annotations.Contract;

public enum ServerType {

    Hub("hub"),
    DwarvenMines("dwarven"),
    CrimsonIsle("crimsonilse"),
    TheInstance("kuudra"),
    DungeonHub("kuudra"),
    F7("f7"),
    End("EndIsle");
    public final String s;
    ServerType(String s){
        this.s = s;

    }

    @Contract(pure = true)
    public static ServerType getFromString(String s){
        ServerType type;
        switch (s){
            case "hub" -> type = ServerType.Hub;
            case "dwarven" -> type = ServerType.DwarvenMines;
            case "crimsonisle" -> type = ServerType.CrimsonIsle;
            case "EndIsle" -> type = ServerType.End;
            default -> type = null;
        }

        return type;
    }
    public static ServerType getServerByPort(int port){
        ServerType type;
        switch (port){
            case 25565 -> type = ServerType.Hub;
            case 25564 -> type = ServerType.DwarvenMines;
            case 25568 -> type = ServerType.CrimsonIsle;
            case 25567 -> type = ServerType.F7;
            case 25569 -> type = ServerType.TheInstance;
            case 25570 -> type = ServerType.DungeonHub;
            case 25571 -> type = ServerType.End;
            default -> type = null;
        }
        return type;
    }

}
