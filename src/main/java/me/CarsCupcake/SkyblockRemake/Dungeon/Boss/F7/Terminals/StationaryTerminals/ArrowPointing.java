package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminals.StationaryTerminals;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.F7Phase3;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminal;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArrowPointing extends Terminal implements Listener {
    private static final ArrayList<String[]> possibles = new ArrayList<>(List.of(
            new String[]{"s   s", "d   d", "rrfll", "", ""}, new String[]{"", "", "rrfll", "u   u", "s   s"}, new String[]{"srd  ", "  rrf", "sru  ", "  rrf", "sru  "}, new String[]{"s   s", "d   d", "d rrf", "d u  ", "rru  "}
    ));
    @Getter
    private final ArrowPointingPattern pattern;
    public ArrowPointing(F7Phase3 phase, int terminalId) {
        super(phase, terminalId);
        pattern = new ArrowPointingPattern(possibles.get(new Random().nextInt(possibles.size())));
    }

    @Override
    public void open(@NotNull SkyblockPlayer player) {
        finish(player);
        pattern.remove();
        if(F7Phase3.activePhase != null)
            F7Phase3.arrowPoint = null;

    }

    @EventHandler(ignoreCancelled = true)
    public void onInteract(PlayerInteractAtEntityEvent event){
        if(event.getRightClicked() instanceof ItemFrame frame)

        if(event.getRightClicked() instanceof ItemFrame && F7Phase3.activePhase != null && F7Phase3.arrowPoint != null){
            event.setCancelled(false);
            new BukkitRunnable() {
                public void run () {
            if(F7Phase3.arrowPoint.pattern.checkForFinish(frame.getLocation().getBlock(), frame.getRotation())) {
                event.setCancelled(true);

                    F7Phase3.arrowPoint.open(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
                }
                }
            }.runTaskLater(Main.getMain(), 2);
        }
    }
}
