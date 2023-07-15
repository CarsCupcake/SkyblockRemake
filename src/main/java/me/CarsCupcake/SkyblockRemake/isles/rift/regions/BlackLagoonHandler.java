package me.CarsCupcake.SkyblockRemake.isles.rift.regions;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.isles.rift.RiftPlayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashSet;
import java.util.Set;

public class BlackLagoonHandler implements Listener {
    private static final Location[] BROWN_MUSHROOM = {new Location(Main.getMain().getServer().getWorld("world_the_end"), -124.5, 69.0, 51.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -101.5, 72.0, 52.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -100.5, 72.0, 46.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -93.5, 73.0, 45.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -90.5, 75.0, 51.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -112.5, 69.0, 44.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -119.5, 69.0, 30.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -128.5, 69.0, 30.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -137.5, 69.0, 36.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -143.5, 70.0, 26.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -147.5, 70.0, 23.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -118.5, 71.0, 18.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -103.5, 72.0, 10.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -94.5, 73.0, 17.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -89.5, 74.0, 24.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -100.5, 70.0, 34.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -101.5, 71.0, 25.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -146.5, 69.0, 19.5)};
    private static final Location[] RED_MUSHROOM = {new Location(Main.getMain().getServer().getWorld("world_the_end"), -122.5, 69.0, 48.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -129.5, 69.0, 37.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -135.5, 68.0, 42.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -87.5, 76.0, 48.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -77.5, 78.0, 44.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -85.5, 73.0, 35.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -93.5, 73.0, 22.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -81.5, 73.0, 14.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -93.5, 73.0, 5.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -109.5, 72.0, 14.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -112.5, 69.0, 38.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -105.5, 69.0, 35.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -134.5, 70.0, 25.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -128.5, 71.0, 20.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -135.5, 71.0, 18.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -143.5, 70.0, 20.5),new Location(Main.getMain().getServer().getWorld("world_the_end"), -138.5, 71.0, 11.5)};
    private static final double threshold = 5;
    private final Set<Mushroom> mushrooms = new HashSet<>();
    public BlackLagoonHandler(){
        for (Location l : BROWN_MUSHROOM) mushrooms.add(new Mushroom(l, Material.BROWN_MUSHROOM_BLOCK, Material.BROWN_MUSHROOM));
        for (Location l : RED_MUSHROOM) mushrooms.add(new Mushroom(l, Material.RED_MUSHROOM_BLOCK, Material.RED_MUSHROOM));
    }
    @EventHandler
    public void onMove(PlayerMoveEvent event){
        RiftPlayer player = RiftPlayer.getRiftPlayer(event.getPlayer());
        if(!player.getRegion().name().contains("Black Lagoon")) return;
        Location l = player.getLocation();
        for (Mushroom mushroom : mushrooms){
            if(mushroom.l.distance(l) > threshold) {
                if(mushroom.in.contains(player)) mushroom.remove(player);
                continue;
            }
            if(!mushroom.in.contains(player)) mushroom.add(player);
        }
    }
    private static class Mushroom{
        private final Location l;
        private final Material m;
        private final Material base;
        private final Set<RiftPlayer> in = new HashSet<>();
        public Mushroom(Location l, Material m, Material base){
            this.l = l;
            this.m = m;
            this.base = base;
        }
        public void add(RiftPlayer player){
            if(in.size() == 0){
                for (int i = (int) l.getY();i < l.getY() + 7; i++){
                    new Location(l.getWorld(), l.getX(), i, l.getZ()).getBlock().setType(Material.MUSHROOM_STEM);
                }
                for (int i = (int) l.getX() - 3; i <= l.getX() + 3; i++)
                    for (int j = (int) l.getZ() - 2; j <= l.getZ() + 2; j++)
                        new Location(l.getWorld(), i, l.getY() + 7, j).getBlock().setType(m);
                for (int i = (int) l.getZ() - 2; i <= l.getZ() + 2; i++){
                    new Location(l.getWorld(), l.getX() + 3, l.getY() + 7, i).getBlock().setType(m);
                    new Location(l.getWorld(), l.getX() - 3, l.getY() + 7, i).getBlock().setType(m);
                }
            }
            in.add(player);
        }
        public void remove(RiftPlayer player){
            in.remove(player);
            if(in.size() == 0){
                for (int i = (int) l.getY();i < l.getY() + 7; i++){
                    new Location(l.getWorld(), l.getX(), i, l.getZ()).getBlock().setType(Material.AIR);
                }
                for (int i = (int) l.getX() - 3; i <= l.getX() + 3; i++)
                    for (int j = (int) l.getZ() - 2; j <= l.getZ() + 2; j++)
                        new Location(l.getWorld(), i, l.getY() + 7, j).getBlock().setType(Material.AIR);
                for (int i = (int) l.getZ() - 2; i <= l.getZ() + 2; i++){
                    new Location(l.getWorld(), l.getX() + 3, l.getY() + 7, i).getBlock().setType(Material.AIR);
                    new Location(l.getWorld(), l.getX() - 3, l.getY() + 7, i).getBlock().setType(Material.AIR);
                }
                l.getBlock().setType(base);
            }
        }
    }
}
