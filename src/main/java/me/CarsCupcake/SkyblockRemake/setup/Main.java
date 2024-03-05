package me.CarsCupcake.SkyblockRemake.setup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    private static final List<String> possibleTypes = List.of("Hub", "Crimson Isle", "Dwarven Mines", "End Isle", "F1", "F6", "F7", "Rift");
    public static final String spigotFileDownload = "https://download1322.mediafire.com/qxktx5nc4f8gc2YgJ4bLOc5RepbtBll8OnJHzyTpcADY5RKuGRu-hCeHo0rp1R2bM7UfSJpn91zrP5cg8vuhhkTcfM2nWr6PqLes8vWOAK0ijiVfALNRPsEZho-4g2z6XCrohOHIyPiWmohv-EsF0AnV9tRnqZK-hFU7fUxLjLv_uM8/h23yhnvc8vrykfk/spigot-1.17.1.jar";
    public static void main(String[] args) throws IOException {
        //TODO Add server manager/installer
        System.out.print("Intalationprocess for Skyblock Remake. \nPossible server types: Hub, Crimson Isle, Dwarven Mines, End Isle, F1, F6, F7, Rift\nEnter the server you want to install:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String in = reader.readLine();
            if (possibleTypes.contains(in)) {
                new DataFetcher(toType(in)).install();
                break;
            }
            System.out.println("Not a valid input!");
        }
    }
    private static me.CarsCupcake.SkyblockBungee.features.ServerType toType(String s) {
        return switch (s) {
            case "Hub" -> me.CarsCupcake.SkyblockBungee.features.ServerType.Hub;
            case "Crimson Isle" -> me.CarsCupcake.SkyblockBungee.features.ServerType.CrimsonIsle;
            case "Dwarven Mines" -> me.CarsCupcake.SkyblockBungee.features.ServerType.DwarvenMines;
            case "End Isle" -> me.CarsCupcake.SkyblockBungee.features.ServerType.End;
            case "F1" -> me.CarsCupcake.SkyblockBungee.features.ServerType.F1;
            case "F6" -> me.CarsCupcake.SkyblockBungee.features.ServerType.F6;
            case "F7" -> me.CarsCupcake.SkyblockBungee.features.ServerType.F7;
            case "Rift" -> me.CarsCupcake.SkyblockBungee.features.ServerType.Rift;
            default -> null;
        };
    }
}
