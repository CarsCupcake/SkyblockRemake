package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminals.StationaryTerminals;

import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.F7Phase3;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminal;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrowShooting extends Terminal implements Listener {
    private static final List<Location> l = List.of(new Location(Main.getMain().getServer().getWorld("world"), 68, 126.0, 50),new Location(Main.getMain().getServer().getWorld("world"), 66, 126.0, 50),new Location(Main.getMain().getServer().getWorld("world"), 64, 126.0, 50),new Location(Main.getMain().getServer().getWorld("world"), 68, 128.0, 50),new Location(Main.getMain().getServer().getWorld("world"), 66, 128.0, 50),new Location(Main.getMain().getServer().getWorld("world"), 64, 128.0, 50),new Location(Main.getMain().getServer().getWorld("world"), 64, 130.0, 50),new Location(Main.getMain().getServer().getWorld("world"), 66, 130.0, 50),new Location(Main.getMain().getServer().getWorld("world"), 68, 130.0, 50));
    private static final Block plate = new Location(Main.getMain().getServer().getWorld("world"), 63.5, 127.0, 35.5).getBlock();
    private final ArrayList<SkyblockPlayer> isOnPlate = new ArrayList<>();
    private final ArrayList<Integer> order = new ArrayList<>();
    private int pointer = 0;
    public ArrowShooting(F7Phase3 phase, int terminalId) {
        super(phase, terminalId);
        order.addAll(List.of(0,1,2,3,4,5,6,7,8));
        Collections.shuffle(order);
    }

    @Override
    public void open(@NotNull SkyblockPlayer player) {}
    public void hitTarget(int i, SkyblockPlayer player){
        if (isOnPlate.isEmpty()) {
            player.sendMessage("§cYou are not allowed to shoot on this target!");
            return;
        }
        if(!isOnPlate.contains(player)){
            player.sendMessage("§cYou are not allowed to shoot on this target!");
            return;
        }
        if(order.get(pointer) != i)
            return;
        pointer++;
        if(pointer < 9){
            l.get(order.get(pointer)).getBlock().setType(Material.EMERALD_BLOCK);
            l.get(order.get(pointer - 1)).getBlock().setType(Material.BLUE_TERRACOTTA);
        }else {
            l.get(order.get(pointer - 1)).getBlock().setType(Material.BLUE_TERRACOTTA);
            finish(player);
        }

    }
    public void addOnPlate(SkyblockPlayer player){
        if(isOnPlate.isEmpty()){
            l.get(order.get(pointer)).getBlock().setType(Material.EMERALD_BLOCK);
        }
        isOnPlate.add(player);
    }
    public void removeFromPlate(SkyblockPlayer player){
        isOnPlate.remove(player);
        if(isOnPlate.isEmpty()){
            l.get(order.get(pointer)).getBlock().setType(Material.BLUE_TERRACOTTA);
            pointer = 0;
            Collections.shuffle(order);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if (e.getTo().getBlock().equals(plate))
            F7Phase3.arrowShoot.addOnPlate(SkyblockPlayer.getSkyblockPlayer(e.getPlayer()));
        if(e.getFrom().getBlock().equals(plate))
            F7Phase3.arrowShoot.removeFromPlate(SkyblockPlayer.getSkyblockPlayer(e.getPlayer()));
    }
    @EventHandler(ignoreCancelled = true)
    public void onWallHit(ProjectileHitEvent event){
        if(event.getHitBlock() == null)
            return;
        int i = 0;
        for (Location l : l) {
            if (l.getBlock().equals(event.getHitBlock())) {
                F7Phase3.arrowShoot.hitTarget(i,SkyblockPlayer.getSkyblockPlayer((Player) event.getEntity().getShooter()));
                return;
            }
            i++;
        }

        
    }
}
