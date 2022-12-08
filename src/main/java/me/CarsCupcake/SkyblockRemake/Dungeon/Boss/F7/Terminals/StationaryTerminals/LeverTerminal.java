package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminals.StationaryTerminals;

import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.F7Phase3;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminal;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LeverTerminal extends Terminal {
    private final static List<Location> leverLocs = List.of(new Location(Main.getMain().getServer().getWorld("world"), 106, 124.0, 113),new Location(Main.getMain().getServer().getWorld("world"), 94, 124.0, 113),new Location(Main.getMain().getServer().getWorld("world"), 23, 132.0, 138),new Location(Main.getMain().getServer().getWorld("world"), 27, 124.0, 127),new Location(Main.getMain().getServer().getWorld("world"), 2, 122.0, 55),new Location(Main.getMain().getServer().getWorld("world"), 14, 122.0, 55),new Location(Main.getMain().getServer().getWorld("world"), 84, 121.0, 34),new Location(Main.getMain().getServer().getWorld("world"), 86, 128.0, 46));

    public LeverTerminal(F7Phase3 phase, int terminalId) {
        super(phase, terminalId);
    }


    @Override
    public void open(@NotNull SkyblockPlayer player) {
        finish(player);
    }
    public static void registerAll(){
        for (Location l : leverLocs){
            F7Phase3.activePhase.terminals.put(l.getBlock(), new LeverTerminal(F7Phase3.activePhase, 0));
        }

    }
}
