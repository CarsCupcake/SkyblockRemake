package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7;

import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminals.StationaryTerminals.LightsTerminal;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminals.StationaryTerminals.SimonSaysTerminal;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class F7Phase3 implements Listener {
    public static SimonSaysTerminal simonSaysTerminal;
    public static LightsTerminal lightsTerminal;
    public void solveTerminal(Terminal t){
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if(event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.STONE_BUTTON && event.getClickedBlock().getX() == 110
                && event.getClickedBlock().getY() == 121 && event.getClickedBlock().getZ() == 91 && simonSaysTerminal == null) {
            simonSaysTerminal = new SimonSaysTerminal(null, -1);
            simonSaysTerminal.open(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
        }

    }
}
