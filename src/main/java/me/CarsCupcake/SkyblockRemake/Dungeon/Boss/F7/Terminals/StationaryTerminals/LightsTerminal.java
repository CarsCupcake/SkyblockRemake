package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminals.StationaryTerminals;

import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.F7Phase3;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminal;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Lightable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class LightsTerminal extends Terminal implements Listener {
    private static final ArrayList<Location> levers = new ArrayList<>(List.of(new Location(Main.getMain().getServer().getWorld("world"), 62, 133.0, 142), new Location(Main.getMain().getServer().getWorld("world"), 61, 133.0, 142), new Location(Main.getMain().getServer().getWorld("world"), 60, 133.0, 142), new Location(Main.getMain().getServer().getWorld("world"), 59, 133.0, 142), new Location(Main.getMain().getServer().getWorld("world"), 58, 133.0, 142), new Location(Main.getMain().getServer().getWorld("world"), 58, 134.0, 142), new Location(Main.getMain().getServer().getWorld("world"), 59, 134.0, 142), new Location(Main.getMain().getServer().getWorld("world"), 60, 134.0, 142), new Location(Main.getMain().getServer().getWorld("world"), 61, 134.0, 142), new Location(Main.getMain().getServer().getWorld("world"), 62, 134.0, 142), new Location(Main.getMain().getServer().getWorld("world"), 62, 135.0, 142), new Location(Main.getMain().getServer().getWorld("world"), 61, 135.0, 142), new Location(Main.getMain().getServer().getWorld("world"), 60, 135.0, 142), new Location(Main.getMain().getServer().getWorld("world"), 59, 135.0, 142), new Location(Main.getMain().getServer().getWorld("world"), 58, 135.0, 142), new Location(Main.getMain().getServer().getWorld("world"), 58, 136.0, 142), new Location(Main.getMain().getServer().getWorld("world"), 59, 136.0, 142), new Location(Main.getMain().getServer().getWorld("world"), 60, 136.0, 142), new Location(Main.getMain().getServer().getWorld("world"), 61, 136.0, 142), new Location(Main.getMain().getServer().getWorld("world"), 62, 136.0, 142)));

    public LightsTerminal(F7Phase3 phase, int terminalId) {
        super(phase, terminalId);
        for (Location l : levers) {
            l.getBlock().setType(Material.LEVER);
            Directional data = (Directional)l.getBlock().getBlockData();
            data.setFacing(BlockFace.NORTH);
            l.getBlock().setBlockData(data);
            ((Lightable)l.clone().add(0,0,1).getBlock().getBlockData()).setLit(false);
        }
    }

    @Override
    public void open(@NotNull SkyblockPlayer player) {}

    public void flick(SkyblockPlayer player){
        new BukkitRunnable(){
            public void run(){
                for (Location l : levers)
                    if (!((Lightable) l.clone().add(0, 0, 1).getBlock().getBlockData()).isLit())
                        return;
                finish(player);
                F7Phase3.lightsTerminal = null;
                for (Location l : levers)
                    l.getBlock().setType(Material.AIR);
            }
        }.runTaskLater(Main.getMain(), 1);
    }

    @EventHandler
    public void leverFlick(PlayerInteractEvent event){
        if(event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.LEVER && levers.contains(event.getClickedBlock().getLocation()) && F7Phase3.lightsTerminal != null){
            F7Phase3.lightsTerminal.flick(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
        }
    }
}
