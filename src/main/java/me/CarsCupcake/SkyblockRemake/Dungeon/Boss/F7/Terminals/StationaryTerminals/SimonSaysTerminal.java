package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminals.StationaryTerminals;

import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.F7Phase3;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminal;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimonSaysTerminal extends Terminal implements Listener {
    private static final ArrayList<Location> possibleLocs = new ArrayList<>(List.of(
            new Location(Main.getMain().getServer().getWorld("world"), 110, 120.0, 92),new Location(Main.getMain().getServer().getWorld("world"), 110, 120.0, 93),
            new Location(Main.getMain().getServer().getWorld("world"), 110, 120.0, 94), new Location(Main.getMain().getServer().getWorld("world"), 110, 120.0, 95),
            new Location(Main.getMain().getServer().getWorld("world"), 110, 121.0, 92),new Location(Main.getMain().getServer().getWorld("world"), 110, 121.0, 93),
            new Location(Main.getMain().getServer().getWorld("world"), 110, 121.0, 94),new Location(Main.getMain().getServer().getWorld("world"), 110, 121.0, 95),
            new Location(Main.getMain().getServer().getWorld("world"), 110, 122.0, 92),new Location(Main.getMain().getServer().getWorld("world"), 110, 122.0, 93),
            new Location(Main.getMain().getServer().getWorld("world"), 110, 122.0, 94),new Location(Main.getMain().getServer().getWorld("world"), 110, 122.0, 95),
            new Location(Main.getMain().getServer().getWorld("world"), 110, 123.0, 92),new Location(Main.getMain().getServer().getWorld("world"), 110, 123.0, 93),
            new Location(Main.getMain().getServer().getWorld("world"), 110, 123.0, 94),new Location(Main.getMain().getServer().getWorld("world"), 110, 123.0, 95)
    ));
    private final ArrayList<Location> order = new ArrayList<>();
    private int await;
    private int current = 0;
    private boolean isInShowMode;
    public SimonSaysTerminal(F7Phase3 phase, int terminalId) {
        super(phase, terminalId);
    }

    @Override
    public void open(@NotNull SkyblockPlayer player) {
        order.clear();
        for (int i = 0; i < 5; i++)
            order.add(possibleLocs.get(new Random().nextInt(possibleLocs.size())));
        show(1);
        await = 1;
    }
    private void show(int i){
        for (Location l : possibleLocs)
            l.getBlock().setType(Material.AIR);

        isInShowMode = true;
        new BukkitRunnable() {
            private int run = 0;
            @Override
            public void run() {
                if(run != 0)
                    order.get(run - 1).clone().add(1,0,0).getBlock().setType(Material.OBSIDIAN);
                if(run == i) {
                    cancel();
                    isInShowMode = false;
                    for (Location l : possibleLocs) {
                        l.getBlock().setType(Material.STONE_BUTTON);
                        Directional data = (Directional)l.getBlock().getBlockData();
                        data.setFacing(BlockFace.WEST);
                        l.getBlock().setBlockData(data);
                    }
                    return;
                }
                order.get(run).clone().add(1,0,0).getBlock().setType(Material.SEA_LANTERN);




                run++;
            }
        }.runTaskTimer(Main.getMain(), 0, 10);
    }
    public boolean press(Block block, Player player){
        if(isInShowMode)
            return false;

        if(block.getLocation().equals(order.get(current))){
            current++;
            if(current == await) {
                await++;
                if(await == 6){
                    finish(SkyblockPlayer.getSkyblockPlayer(player));
                    isOpen = false;
                    F7Phase3.simonSaysTerminal = null;
                    for (Location l : possibleLocs)
                        l.getBlock().setType(Material.AIR);
                    return true;
                }
                show(await);
                current = 0;
                return true;
            }
        }else {
            Bukkit.broadcastMessage("§cYou failed!");
            for (Location l : possibleLocs)
                l.getBlock().setType(Material.AIR);
            isOpen = false;
            order.clear();
            current = 0;
            return true;
        }
        return false;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if(event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.STONE_BUTTON && possibleLocs.contains(event.getClickedBlock().getLocation()) && F7Phase3.simonSaysTerminal != null)
            event.setCancelled(F7Phase3.simonSaysTerminal.press(event.getClickedBlock(), event.getPlayer()));


    }
}
