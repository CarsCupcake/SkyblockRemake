package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7;

import com.comphenix.protocol.PacketType;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;

public class Pillar implements Listener {
    private static final ArrayList<Pillar> pillarList = new ArrayList<>();
    private final Location baseLocation;
    private int level;
    private boolean moveDown = true;

    private BukkitRunnable moveRunnable;
    private final Location pad;
    public SkyblockPlayer pillarPusher;
    public boolean isDestroyed = false;

    public Pillar (Location basicLocation, Location pad) {
        this.baseLocation = basicLocation;
        this.pad = pad;
        this.level = -14;

        pillarList.add(this);

        for (int i = 0; i < 21; i++){

            if(i <= 14){
                placePillarLayer(i * -1,Material.POLISHED_DIORITE);
            }else
                placePillarLayer(i * -1,Material.AIR);

        }

    }
    public Pillar(){this.baseLocation = null; this.pad = null;


        new Pillar(new Location(Bukkit.getWorld("world"),46,189,65), new Location(Bukkit.getWorld("world"),32,169,94));
        new Pillar(new Location(Bukkit.getWorld("world"),46,189,41), new Location(Bukkit.getWorld("world"),32,169,12));
        new Pillar(new Location(Bukkit.getWorld("world"),100,189,65), new Location(Bukkit.getWorld("world"),114,169,94));

    }

    public void startMove(SkyblockPlayer pillarPusher) {
        this.pillarPusher = pillarPusher;
        //max extention 20
        if(moveRunnable == null || moveRunnable.isCancelled()) {
        moveRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()) {
                    p.playSound(p.getLocation(), Sound.BLOCK_PISTON_EXTEND, 1, 0.75f);

                }

                int modify = (moveDown) ? -1 : 1;
                level += modify;
                if(moveDown)
                    placePillarLayer(level, Material.POLISHED_DIORITE);
                else
                    placePillarLayer(level -1 , Material.AIR);
                if(level == -20)
                    moveDown = false;
                if(level == 0)
                    moveDown = true;


            }
        };
        moveRunnable.runTaskTimer(Main.getMain(),5,5);}
    }
    private void placePillarLayer(int level, Material type){
        Location location = baseLocation.clone().add(0,level,0);
        location.getBlock().setType(type);
        for(int i = 1; i < 4; i++){
            location.clone().add(i,0,0).getBlock().setType(type);
            location.clone().subtract(i,0,0).getBlock().setType(type);
            location.clone().add(0,0,i).getBlock().setType(type);
            location.clone().subtract(0,0,i).getBlock().setType(type);
            if(i != 3){
                location.clone().add(i,0,i).getBlock().setType(type);
                location.clone().subtract(i,0,i).getBlock().setType(type);
                location.clone().add(i,0,-i).getBlock().setType(type);
                location.clone().subtract(i,0,-i).getBlock().setType(type);
            }
            if(3 != i){
                location.clone().add(1,0,1+i).getBlock().setType(type);
                location.clone().add(1,0,(1+i)*-1).getBlock().setType(type);
                location.clone().add(-1,0,1+i).getBlock().setType(type);
                location.clone().add(-1,0,(1+i)*-1).getBlock().setType(type);

                location.clone().add(1+i,0,1).getBlock().setType(type);
                location.clone().add((1+i)*-1,0,1).getBlock().setType(type);
                location.clone().add(1+i,0,-1).getBlock().setType(type);
                location.clone().add((1+i)*-1,0,-1).getBlock().setType(type);
            }
        }

    }
    private ArrayList<Location> getPillar(){
        ArrayList<Location> l = new ArrayList<>();
        for(int i = 1; i < 4; i++){
            l.add(baseLocation.clone().add(i,0,0));
            l.add(baseLocation.clone().subtract(i,0,0));
            l.add(baseLocation.clone().add(0,0,i));
            l.add(baseLocation.clone().subtract(0,0,i));
            if(i != 3){
                l.add(baseLocation.clone().add(i,0,i));
                l.add(baseLocation.clone().subtract(i,0,i));
                l.add(baseLocation.clone().add(i,0,-i));
                l.add(baseLocation.clone().subtract(i,0,-i));
            }
            if(3 != i){
                l.add(baseLocation.clone().add(1,0,1+i));
                l.add(baseLocation.clone().add(1,0,(1+i)*-1));
                l.add(baseLocation.clone().add(-1,0,1+i));
                l.add(baseLocation.clone().add(-1,0,(1+i)*-1));

                l.add(baseLocation.clone().add(1+i,0,1));
                l.add(baseLocation.clone().add((1+i)*-1,0,1));
                l.add(baseLocation.clone().add(1+i,0,-1));
                l.add(baseLocation.clone().add((1+i)*-1,0,-1));
            }
        }
        return l;
    }
    public void checkIfPlayerCrushed(int level){
        for(Location location :getPillar() ){
            for(Player p : Bukkit.getOnlinePlayers())
                if(p.getEyeLocation().distance(location) < 1){
                SkyblockPlayer.getSkyblockPlayer(p).setHealth(0);
                Main.updatebar(SkyblockPlayer.getSkyblockPlayer(p));
                }
        }
    }

    public void stopMove() {
        try {
            moveRunnable.cancel();
        }catch (Exception ignore) {

        }
        pillarPusher = null;
    }
    public boolean isInPadArea(Player player) {
        return isInRect(player, pad.clone().add(3,3,3),pad.clone().subtract(3,0,3));
    }

    public void destroy(LivingEntity entity) {
        isDestroyed = true;
        if(!moveRunnable.isCancelled()){
            moveRunnable.cancel();
        }
    }

    private boolean isInRect(Player player, Location loc1, Location loc2)
    {
        double[] dim = new double[2];

        dim[0] = loc1.getX();
        dim[1] = loc2.getX();
        Arrays.sort(dim);
        if(player.getLocation().getX() > dim[1] || player.getLocation().getX() < dim[0])
            return false;

        dim[0] = loc1.getZ();
        dim[1] = loc2.getZ();
        Arrays.sort(dim);
        if(player.getLocation().getZ() > dim[1] || player.getLocation().getZ() < dim[0])
            return false;

        dim[0] = loc1.getY();
        dim[1] = loc2.getY();
        Arrays.sort(dim);
        if(player.getLocation().getY() > dim[1] || player.getLocation().getY() < dim[0])
            return false;



        return true;
    }


    @EventHandler
    public void playerSneakEvent(PlayerToggleSneakEvent event){
        new BukkitRunnable() {
            @Override
            public void run() {
                System.out.println(event.getPlayer().isSneaking());
                if(event.getPlayer().isSneaking() ){
                    for (Pillar pillar : pillarList)
                        if(pillar.isInPadArea(event.getPlayer()) && !pillar.isDestroyed)
                            pillar.startMove(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));

                }else{
                    for (Pillar pillar : pillarList)
                        if(pillar.pillarPusher != null && pillar.pillarPusher.equals(SkyblockPlayer.getSkyblockPlayer(event.getPlayer())))
                            pillar.stopMove();
                }
            }
        }.runTaskLater(Main.getMain(),5);

    }
    @EventHandler
    public void playerMoveEvent(PlayerMoveEvent event){
        if(event.getPlayer().isSneaking()){
            for (Pillar pillar : pillarList)
                if(pillar.pillarPusher != null && pillar.pillarPusher.equals(SkyblockPlayer.getSkyblockPlayer(event.getPlayer())) && !pillar.isInPadArea(event.getPlayer()))
                    pillar.stopMove();

        }
        }

}
