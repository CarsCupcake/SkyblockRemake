package me.CarsCupcake.SkyblockRemake.End.Dragon.DragonAi;

import me.CarsCupcake.SkyblockRemake.End.Dragon.StartFight;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.game.PacketPlayOutWorldParticles;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftEnderDragon;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import static me.CarsCupcake.SkyblockRemake.End.Dragon.StartFight.*;

public class PathFind {
    public static boolean fireball = false;
    static Location loc1 = spawnLoc.clone();
    static Location loc2 = spawnLoc.clone();
    static Location loc3 = spawnLoc.clone();
    static Location loc4 = spawnLoc.clone();
    static boolean cooldwon = false;
    static List<Location> curve = new ArrayList<>();

    public static void move(SkyblockDragon dragon, Location centerLoc, double radius) {
        EnderDragon bukkitDragon = (EnderDragon) dragon.getBukkitEntity();

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!dragon.isAlive()) {
                    this.cancel();
                }

                Location loc = getRandLoc(centerLoc);
                System.out.println(loc);
                System.out.println((loc.distance(centerLoc)));
                if ((loc.distance(bukkitDragon.getLocation()) < 20)) {
                    dragon.setPositionRotation(loc.getX(), loc.getY(), loc.getZ(), bukkitDragon.getLocation().getYaw(), bukkitDragon.getLocation().getPitch());
                }

            }
        }.runTaskTimer(Main.getMain(), 0, 60);

    }

    public static void getDragDirection(SkyblockDragon dragon, ArmorStand as,double moveSpeed) {
        cooldwon = true;
        new BukkitRunnable() {
            @Override
            public void run() {
                cooldwon = false;
            }
        }.runTaskLater(Main.getMain(), 60);
        new BukkitRunnable() {

            @Override
            public void run() {
                double fireballChance = Math.random();
                if (!fireball) {
                    //if(fireballChance<1) {
                    if(!cooldwon){
                        if (fireballChance < 0.002) {
                            //NOT SHOOTING HIM
                            //launching fireballs :V
                            shoot(dragon);
                            fireball = true;
                        } else if (fireballChance < 0.004) {
                            //NOT SHOOTING HIM
                            //LIGHTNING YAY
                            lighting(dragon);
                            fireball = true;
                        }
                    }

                    //}
                    Location origin = dragon.getBukkitEntity().getLocation();
                    Location target = as.getLocation();
                    Vector direction = target.toVector().subtract(origin.toVector()).normalize();
                    if (!(StartFight.activeDrag.isAlive())) {
                        this.cancel();
                        as.remove();
                    }
                    if (dragon.getBukkitEntity().getLocation().distance(target) < 2) {
                        loc1=dragon.getBukkitEntity().getLocation();
                        randomizeAs(as);
                        loc2=as.getLocation();
                        randomizeAs(as);
                        loc3=as.getLocation();
                        curve = bezierCurve(50,loc1,loc2,loc3);
                    }
                    Vector directionforDrag = origin.clone().toVector().subtract(target.clone().toVector());
                    Location rot = dragon.getBukkitEntity().getLocation().setDirection(directionforDrag);
                    dragon.getBukkitEntity().teleport(rot);
                    Location location;
                    Vector testdir = dragon.getBukkitEntity().getLocation().getDirection().clone();
                    if(curve.size()>=1) {
                        testdir = curve.get(0).toVector().subtract(origin.toVector()).normalize();
                    }
                    dragon.getBukkitEntity().teleport(dragon.getBukkitEntity().getLocation().add(testdir.multiply(moveSpeed)));
                    if(curve.size()>=1) {
                        if (dragon.getBukkitEntity().getLocation().distance(curve.get(0)) < 2) {
                            curve.remove(0);
                        }
                    } else {
                        loc1=dragon.getBukkitEntity().getLocation();
                        randomizeAs(as);
                        loc2=as.getLocation();
                        randomizeAs(as);
                        loc3=as.getLocation();
                        curve = bezierCurve(50,loc1,loc2,loc3);
                    }

                    double dX = dragon.getBukkitEntity().getLocation().getX() - as.getLocation().getX();
                    double dY = dragon.getBukkitEntity().getLocation().getY() - as.getLocation().getY();
                    double dZ = dragon.getBukkitEntity().getLocation().getZ() - as.getLocation().getZ();
                    double yaw = Math.atan2(dZ, dX);
                    double pitch = Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + Math.PI;

                } else {
                    dragon.getBukkitEntity().setVelocity(new Vector(0, 0, 0));
                }

            }
        }.runTaskTimer(Main.getMain(), 0, 1);
    }

    static void randomizeAs(ArmorStand as) {
        Random random = new Random();
        Location centerLoc = spawnLoc.clone();
        int minZ = (int) (centerLoc.getZ() - 50);
        int maxZ = (int) (centerLoc.getZ() + 50);
        int minX = (int) (centerLoc.getX() - 50);
        int maxX = (int) (centerLoc.getX() + 50);

        int minZ2 = (int) (activeDrag.getBukkitEntity().getLocation().getDirection().getZ() - 50);
        int maxZ2 = (int) (activeDrag.getBukkitEntity().getLocation().getDirection().getZ()  + 50);
        int minX2 = (int) (activeDrag.getBukkitEntity().getLocation().getDirection().getX() - 50);
        int maxX2 = (int) (activeDrag.getBukkitEntity().getLocation().getDirection().getX() + 50);

        int x = random.nextInt((maxX + 1) - minX) + minX;
        int z = random.nextInt((maxZ + 1) - minZ) + minZ;
        as.teleport(new Location(as.getWorld(), x, random.nextInt(78) + 12, z));
    }
    private static void lighting(SkyblockDragon dragon){
        cooldwon = true;
        new BukkitRunnable() {
            @Override
            public void run() {
                dragon.getBukkitEntity().getWorld().spawn(dragon.getBukkitEntity().getLocation(), LightningStrike.class);
                for(Player p : Bukkit.getOnlinePlayers()){
                    p.sendMessage(ChatColor.RED + "[" + StartFight.activeDrag.getBukkitEntity().getCustomName() + ChatColor.RED +"]" + ChatColor.GOLD + " Lightning Strike!");
                    p.getWorld().spawn(p.getLocation().add(0,0.2, 0), LightningStrike.class);
                    if(p.getLocation().getBlock().getType() == Material.FIRE)
                        p.getLocation().getBlock().setType(Material.AIR);
                    SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(p);
                    Calculator calculator = new Calculator();
                    calculator.entityToPlayerDamage(entityDragon, player);
                    calculator.damagePlayer(player);
                    calculator.showDamageTag(player);
                    fireball = false;
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            cooldwon = false;
                        }
                    }.runTaskLater(Main.getMain(), 100);
                }
            }
        }.runTaskLater(Main.getMain(), 35);
    }

    public static void shoot(SkyblockDragon dragon) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(ChatColor.RED + "[" + StartFight.activeDrag.getBukkitEntity().getCustomName() + ChatColor.RED +"]" + ChatColor.GOLD + " Fireball!");

        }
        new BukkitRunnable() {
            int i = 0;

            @Override
            public void run() {
                i++;
                if (i >= 45) {
                    fireball = false;
                    cooldwon = true;
                    this.cancel();
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            cooldwon = false;
                        }
                    }.runTaskLater(Main.getMain(), 100);
                }
                if (dragon.getBukkitEntity().isDead()) {
                    this.cancel();
                }
                EnderDragon bukkitDragon = (EnderDragon) dragon.getBukkitEntity();
                final Fireball f = bukkitDragon.launchProjectile(Fireball.class);
                f.setShooter(bukkitDragon);
                Player target = (Player) Bukkit.getOnlinePlayers().toArray()[ new Random().nextInt(Bukkit.getOnlinePlayers().size())];
                f.setDirection(((EnderDragon) dragon.getBukkitEntity()).getEyeLocation().toVector().subtract(target.getLocation().toVector()).multiply(-1));
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.playSound(p.getLocation(), Sound.ENTITY_GHAST_SHOOT, 2, 1);
                }

            }
        }.runTaskTimer(Main.getMain(), 0, 5L);

    }


    public static void summonCircle(Location location, int size) {
        for (int d = 0; d <= 90; d += 1) {
            Location particleLoc = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
            particleLoc.setX(location.getX() + Math.cos(d) * size);
            particleLoc.setZ(location.getZ() + Math.sin(d) * size);
            particleLoc.getWorld().spawnParticle(Particle.CLOUD, particleLoc, 0, 0, 0, 0, 1);

        }
    }

    private static Location getRandLoc(Location loc) {
        Random rndGen = new Random();

        int r = rndGen.nextInt(50);
        int x = rndGen.nextInt(r);
        int z = (int) Math.sqrt(Math.pow(r, 2) - Math.pow(x, 2));
        if (rndGen.nextBoolean()) x *= -1;
        if (rndGen.nextBoolean()) z *= -1;

        return new Location(loc.getWorld(), loc.getX() + x, loc.getY(), loc.getZ() + z);
    }

    public static void goToLocation(SkyblockDragon ed, ArmorStand as) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!(StartFight.activeDrag.isAlive())) {
                    this.cancel();
                }
                if (spawnLoc.distance(StartFight.activeDrag.getBukkitEntity().getLocation()) > 500) {
                    StartFight.activeDrag.setHealth(0f);
                    StartFight.fightActive = false;
                    as.remove();
                }
                new EntityTargetEvent(ed.getBukkitEntity(), as, EntityTargetEvent.TargetReason.CUSTOM);
            }
        }.runTaskTimer(Main.getMain(), 0, 1);

    }

    public static void randomizeMovement(SkyblockDragon enderDragon) {

        LivingEntity eDragon = (LivingEntity) enderDragon.getBukkitEntity();
        Location loc = eDragon.getLocation();

        Location loc1 = loc.add(-20, 0, -20);
        Location loc2 = loc.add(20, 0, 20);

        double minX = Math.min(loc1.getX(), loc2.getX());
        double minY = Math.min(5, 40);
        double minZ = Math.min(loc1.getZ(), loc2.getZ());

        double maxX = Math.max(loc1.getX(), loc2.getX());
        double maxY = Math.max(5, 40);
        double maxZ = Math.max(loc1.getZ(), loc2.getZ());

        Location target = new Location(eDragon.getWorld(), randomDouble(minX, maxX), randomDouble(minY, maxY), randomDouble(minZ, maxZ));
        Location point1 = eDragon.getLocation().clone();
        Location point2 = target.clone();
        double interval = 1 / 3D;

        double distance = point1.distance(point2);
        Vector difference = point2.toVector().subtract(point1.toVector());
        double points = Math.ceil(distance / interval);
        difference.multiply(1D / points);

        Location location = point1.clone();

        new BukkitRunnable() {
            int i = 0;

            @Override
            public void run() {
                if (i >= points) {
                    this.cancel();
                }
                eDragon.teleport(location);
                location.add(difference);
                i++;
            }
        }.runTaskTimer(Main.getMain(), 0L, 1L);
        for (int i = 0; i <= points; i++) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 2);

            }
            Bukkit.getServer().getWorld("world").spawnParticle(Particle.PORTAL, location, 0,0,0,0,10);
            // Do stuff with location, then add
            location.add(difference);
        }

        enderDragon.getNavigation().a(new BlockPosition(target.getX(), target.getY(), target.getZ()));
        ((CraftEnderDragon) enderDragon.getBukkitEntity()).getHandle().getNavigation().a(new BlockPosition(target.getX(), target.getY(), target.getZ()));
        System.out.println("[DEBUG] " + target.getX() + ", " + target.getY() + ", " + target.getZ());
    }

    private static double randomDouble(double min, double max) {
        return min + ThreadLocalRandom.current().nextDouble(Math.abs(max - min + 1));
    }

    public static Location bezierPoint(float t, Location p0, Location p1, Location p2)
    {
        float a = (1-t)*(1-t);
        float b = 2*(1-t)*t;
        float c = t*t;

        Location p = p0.clone().multiply(a).add(p1.clone().multiply(b)).add(p2.clone().multiply(c));
        //System.out.println(p);
        return p;
    }

    public static List<Location> bezierCurve(int segmentCount, Location p0, Location p1, Location p2)
    {
        List<Location> points = new ArrayList<Location>();
        for(int i = 1; i < segmentCount; i++)
        {
            float t = i / (float) segmentCount;
            points.add(bezierPoint(t, p0, p1, p2));
        }
        return points;
    }

}
