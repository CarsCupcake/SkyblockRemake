package me.CarsCupcake.SkyblockRemake.End.Dragon;

import me.CarsCupcake.SkyblockRemake.End.Dragon.DragonAi.Loot;
import me.CarsCupcake.SkyblockRemake.End.Dragon.DragonAi.SkyblockDragon;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class StartFight {
    public static boolean fightActive = false;
    public static HashMap<Player, Double> playerDMG = new HashMap<>();
    public static Location spawnLoc = new Location(Bukkit.getWorlds().get(0), -671, 80, -277);
    public static HashMap<Location, SkyblockPlayer> placedEyes = new HashMap<>();
    public static SkyblockDragon activeDrag;
    public static SkyblockEntity entityDragon;
    public static HashMap<Player, Double> weight = new HashMap<>();
    public static HashMap<Player, Double> aotdChance = new HashMap<>();
    public static double maxDragHealth;
    public static double dragonHealth;
    public static ArmorStand as;



    public static void startDragonFight() {
        playerDMG.clear();
        Loot.damage.clear();
        HashMap<Location, Player> placedEyesClone = new HashMap<>(placedEyes);
        for (Location loc : placedEyesClone.keySet()) {
            Player p = placedEyes.get(loc);
            placedEyes.remove(loc);
            if (aotdChance.containsKey(p)) {
                aotdChance.put(p, aotdChance.get(p) + 10D);
            } else {
                aotdChance.put(p, 10D);
            }
            if (weight.containsKey(p)) {
                if (weight.get(p) < 400) {
                    weight.put(p, weight.get(p) + 100);

                }
            } else {
                weight.put(p, 100D);
            }

        }
        System.out.println(weight);
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH, 5, 1);
            playerDMG.put(p,0D);
        }
        fightActive = true;

        new BukkitRunnable() {
            SkyblockDragon dragon = null;

            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    new BukkitRunnable() {
                        int i = 0;

                        @Override
                        public void run() {
                            if (i == 3) {
                                this.cancel();
                            }
                            i++;
                            p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 2, 1);
                        }
                    }.runTaskTimer(Main.getMain(), 0, 3);


                }


                int dragType = (int) (Math.random() * 100);

                /*if (dragType >= 96) {
                    dragon = (SkyblockDragon) DragonTypes.SUPERIOR.spawnEntity(spawnLoc);
                } else if (dragType >= 80) {
                    dragon = (SkyblockDragon) DragonTypes.STRONG.spawnEntity(spawnLoc);
                } else if (dragType >= 64) {
                    dragon= (SkyblockDragon) DragonTypes.WISE.spawnEntity(spawnLoc);
                } else if (dragType >= 48) {
                    dragon = (SkyblockDragon) DragonTypes.YOUNG.spawnEntity(spawnLoc);

                } else if (dragType >= 32) {
                    double random = Math.random() * 100;
                    if (random > 60) {
                        dragon = (SkyblockDragon) DragonTypes.UNSTABLE.spawnEntity(spawnLoc);
                            *//*new BukkitRunnable() {
                                @Override
                                public void run() {
                                    {
                                        if(!(activeDrag.isAlive())) {
                                            this.cancel();
                                        }
                                        PathFind.randomizeMovement(dragon);
                                    }
                                }
                            }.runTaskTimer(Items.getInstance(),50L,150L);*//*
                    } else {
                        dragon = (SkyblockDragon) DragonTypes.UNSTABLE.spawnEntity(spawnLoc);
                    }

                } else if (dragType >= 16) {
                    dragon = (SkyblockDragon) DragonTypes.PROTECTOR.spawnEntity(spawnLoc);
                } else if (dragType >= 0) {
                    dragon = (SkyblockDragon) DragonTypes.OLD.spawnEntity(spawnLoc);

                }*/
                entityDragon = (SkyblockEntity) DragonTypes.SUPERIOR.spawnEntity(spawnLoc);
                dragon = ((CustomDragon)entityDragon).getSkyblockDragon();
                activeDrag = dragon;
                //PathFind.goToLocation(activeDrag, as);

            }

        }.runTaskLater(Main.getMain(), 20 /* * 10*/);


    }
}
