package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.*;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class F7Phase1 implements Listener {
    private final Location christal1Location = new Location(Bukkit.getWorld("world"), 64.5,238,50.5);
    private final Location christal2Location = new Location(Bukkit.getWorld("world"), 82.5,238,50.5);
    private final Location christal1placeLocation = new Location(Bukkit.getWorld("world"), 52.5,224,41.5);
    private final Location christal2placeLocation = new Location(Bukkit.getWorld("world"), 94.5,224,41.5);
    private final Location laserBaseLocation = new Location(Bukkit.getWorld("world"),73, 224, 73);
    public Entity christal1;
    public Entity christal2;
    public SkyblockPlayer christal1Owner;
    public SkyblockPlayer christal2Owner;
    boolean christal1Placed = false;
    boolean christal2Placed = false;
    boolean laserActive = false;
    private BukkitRunnable laserRunnable;
    final Maxor maxor;
    private ArrayList<Location> archerLocations = new ArrayList<Location>();
    private ArrayList<Location> minerLocations = new ArrayList<Location>();
    public static F7Phase1 instance;
    public F7Phase1(Maxor maxor) {
        this.maxor = maxor;
        summonCrystals();
        instance = this;
        init();
        for(Location l : archerLocations)
            new WitherGuard().spawn(l);
        for(Location l : minerLocations)
            new WitherMiner().spawn(l);
    }

    private void init() {
        World world = Bukkit.getWorld("world");
        archerLocations.add(new Location(world,84.5,238,50.5));
        archerLocations.add(new Location(world,80.5,238,50.5));
        archerLocations.add(new Location(world,66.5,238,50.5));
        archerLocations.add(new Location(world,62.5,238,50.5));

        archerLocations.add(new Location(world,54.5,238,40.5));
        archerLocations.add(new Location(world,54.5,238,42.5));
        archerLocations.add(new Location(world,92.5,238,40.5));
        archerLocations.add(new Location(world,92.5,238,42.5));

        archerLocations.add(new Location(world,76.5,224,68.5));
        archerLocations.add(new Location(world,74.5,224,68.5));
        archerLocations.add(new Location(world,72.5,224,68.5));
        archerLocations.add(new Location(world,70.5,224,68.5));

        minerLocations.add(new Location(world,61.5,221,51.5));
        minerLocations.add(new Location(world,55.5,221,51.5));
        minerLocations.add(new Location(world,60.5,221,47.5));
        minerLocations.add(new Location(world,56.5,221,46.5));
        minerLocations.add(new Location(world,60.5,221,43.5));

        minerLocations.add(new Location(world,69.5,221,35.5));
        minerLocations.add(new Location(world,69.5,221,39.5));
        minerLocations.add(new Location(world,69.5,221,43.5));
        minerLocations.add(new Location(world,69.5,221,47.5));
        minerLocations.add(new Location(world,77.5,221,35.5));
        minerLocations.add(new Location(world,77.5,221,39.5));
        minerLocations.add(new Location(world,77.5,221,43.5));
        minerLocations.add(new Location(world,77.5,221,47.5));

        minerLocations.add(new Location(world,69.5,225,71.5));
        minerLocations.add(new Location(world,71.5,225,71.5));
        minerLocations.add(new Location(world,73.5,225,71.5));
        minerLocations.add(new Location(world,75.5,225,71.5));
        minerLocations.add(new Location(world,70.5,225,73.5));
        minerLocations.add(new Location(world,71.5,225,73.5));
        minerLocations.add(new Location(world,74.5,225,73.5));
        minerLocations.add(new Location(world,76.5,225,73.5));

        minerLocations.add(new Location(world,47.5,221,37.5));
        minerLocations.add(new Location(world,44.5,221,36.5));
        minerLocations.add(new Location(world,46.5,221,34.5));
        minerLocations.add(new Location(world,49.5,221,35.5));

        minerLocations.add(new Location(world,87.5,221,45.5));
        minerLocations.add(new Location(world,86.5,221,45.5));
        minerLocations.add(new Location(world,83.5,221,45.5));
        minerLocations.add(new Location(world,86.5,221,43.5));
        minerLocations.add(new Location(world,87.5,221,43.5));

        minerLocations.add(new Location(world,100.5,221,49.5));
        minerLocations.add(new Location(world,100.5,221,48.5));
        minerLocations.add(new Location(world,100.5,221,47.5));
        minerLocations.add(new Location(world,100.5,221,46.5));
        minerLocations.add(new Location(world,100.5,221,45.5));



    }

    private void summonCrystals(){
        christal1 =  christal1Location.getWorld().spawn(christal1Location.clone().add(0,0.4,0), EnderCrystal.class,c->{
            c.setInvulnerable(true);
        });
        christal2 =  christal2Location.getWorld().spawn(christal2Location.clone().add(0,0.4,0), EnderCrystal.class,c->{
            c.setInvulnerable(true);
        });

    }
    public void respawnAll() {if(christal1 != null )
        christal1.remove();
        if(christal2 != null )
            christal2.remove();

        christal1Placed = false;
        christal2Placed = false;
        summonCrystals();
        laserBaseLocation.getBlock().setType(Material.BLACK_STAINED_GLASS);

    }

    public void christalPickup(int num, SkyblockPlayer player){
        if(num == 1){
            if(christal1Owner != null)
                return;
            if(christal1Placed)
                return;
            christal1Owner = player;
            christal1.remove();
            christal1 =null;

        }
        if(num == 2){
            if(christal2Owner != null)
                return;
            if(christal2Placed)
                return;
            christal2Owner = player;
            christal2.remove();
            christal2 =null;


        }
    }
    public void cristalPlaceCheck(SkyblockPlayer player){
        if(christal1Owner != null && christal1Owner.equals(player) && !christal1Placed) {
            if(!christal1placeLocation.getWorld().getNearbyEntities(christal1placeLocation,2,2,2).contains(player))
                return;
            christal1Owner = null;
            christal1 =  christal1Location.getWorld().spawn(christal1placeLocation.clone().add(0,0.4,0), EnderCrystal.class,c->{
                c.setInvulnerable(true);
            });
            christal1Placed = true;
            if(christal2Placed){
                for(Player p : Bukkit.getOnlinePlayers()){
                    p.sendTitle("§a2/2 Enegry Crystals are now active!" , null, 0,60,0);

                }

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        for(Player p : Bukkit.getOnlinePlayers()){
                            p.sendTitle("§aThe Energy Laser is charging up!" , null, 0,60,0);
                            laserBaseLocation.getBlock().setType(Material.YELLOW_STAINED_GLASS);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    laserBaseLocation.getBlock().setType(Material.RED_STAINED_GLASS);
                                    laserActive = true;
                                    startLaserChecker();
                                }
                            }.runTaskLater(Main.getMain(), 60);
                        }
                    }
                }.runTaskLater(Main.getMain(),40);
            }else
                for(Player p : Bukkit.getOnlinePlayers()){
                    p.sendTitle("§c1/§a2 Enegry Crystals are now active!" , null, 0,60,0);

                }

        }
        if(christal2Owner != null &&christal2Owner.equals(player)&& !christal2Placed) {
            if(!christal2placeLocation.getWorld().getNearbyEntities(christal2placeLocation,2,2,2).contains(player))
               return;

            christal2Owner = null;
            christal2 =  christal1Location.getWorld().spawn(christal2placeLocation.clone().add(0,0.4,0), EnderCrystal.class,c->{
                c.setInvulnerable(true);
            });
            christal2Placed = true;
            if(christal1Placed){
                for(Player p : Bukkit.getOnlinePlayers()){
                    p.sendTitle("§a2/2 Enegry Crystals are now active!" , null, 0,60,0);

                }

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        for(Player p : Bukkit.getOnlinePlayers()){
                            p.sendTitle("§aThe Energy Laser is charging up!" , null, 0,60,0);
                            laserBaseLocation.getBlock().setType(Material.YELLOW_STAINED_GLASS);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    laserBaseLocation.getBlock().setType(Material.RED_STAINED_GLASS);
                                    laserActive = true;
                                    startLaserChecker();
                                }
                            }.runTaskLater(Main.getMain(), 60);
                        }
                    }
                }.runTaskLater(Main.getMain(),40);
            }else
                for(Player p : Bukkit.getOnlinePlayers()){
                    p.sendTitle("§c1/§a2 Enegry Crystals are now active!" , null, 0,60,0);

                }
        }
    }

    public void removeAll(){
        if(christal1 != null )
            christal1.remove();
        if(christal2 != null )
            christal2.remove();
        laserBaseLocation.getBlock().setType(Material.BLACK_STAINED_GLASS);
        laserActive = false;
        HandlerList.unregisterAll(this);
    }

    @EventHandler
    public void crystalClicked(PlayerInteractAtEntityEvent event) {
        if(event.getRightClicked().equals(F7Phase1.instance.christal1) ){
            event.setCancelled(true);
            event.getRightClicked().remove();
            F7Phase1.instance.christalPickup(1,SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
        }
        if(event.getRightClicked().equals(F7Phase1.instance.christal2)){
            event.setCancelled(true);
            event.getRightClicked().remove();
            F7Phase1.instance.christalPickup(2,SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
        }
    }
    @EventHandler
    public void tntExplode(EntityExplodeEvent event) {
        event.setCancelled(true);
        if(event.getEntity() instanceof TNTPrimed && event.getEntity().getScoreboardTags().contains("maxor")){

            event.getEntity().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, event.getEntity().getLocation().add(0, 0.5, 0), 6, 0, 0, 0, 6, null, true);
            for(Player p : Bukkit.getOnlinePlayers())
                p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
            List<Entity> entities = event.getEntity().getNearbyEntities(10,10,10).stream().filter(entity -> entity instanceof Player).toList();
            if(!entities.isEmpty()){
                for(Entity entity : entities){
                    Player player = (Player) entity;
                    player.damage(0.1);


                    Calculator calc = new Calculator();
                    calc.entityToPlayerDamage(maxor, SkyblockPlayer.getSkyblockPlayer(player));
                    calc.damagePlayer(SkyblockPlayer.getSkyblockPlayer(player));


                }
            }

        }
    }
    @EventHandler
    public void crystalClicked(EntityDamageByEntityEvent event) {
        if(event.getEntity().equals(F7Phase1.instance.christal1) ){
            event.setCancelled(true);
            event.getEntity().remove();
            F7Phase1.instance.christalPickup(1,SkyblockPlayer.getSkyblockPlayer((Player) event.getDamager()));
        }
        if(event.getEntity().equals(F7Phase1.instance.christal2)){
            event.setCancelled(true);
            event.getEntity().remove();
            F7Phase1.instance.christalPickup(2,SkyblockPlayer.getSkyblockPlayer((Player) event.getDamager()));
        }
    }
    @EventHandler
    public void crystalClicked(PlayerMoveEvent event) {
        F7Phase1.instance.cristalPlaceCheck(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
    }

    private void startLaserChecker(){
        Location location = new Location(Bukkit.getWorld("world"), 73.5,227,73.5);
        laserRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                for (Entity entity : location.getWorld().getNearbyEntities(location,2,5,2))
                    System.out.println(entity.getType());
                if(location.getWorld().getNearbyEntities(location,2,5,2).contains(maxor.getEntity())){
                    laserActive = false;
                    maxor.setLaser();
                    cancel();
                }
            }
        };
        laserRunnable.runTaskTimer(Main.getMain(),0,1);
    }

}
