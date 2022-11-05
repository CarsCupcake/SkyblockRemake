package me.CarsCupcake.SkyblockRemake.End.Dragon;

import me.CarsCupcake.SkyblockRemake.End.Dragon.DragonAi.Loot;
import me.CarsCupcake.SkyblockRemake.End.Dragon.DragonAi.PathFind;
import me.CarsCupcake.SkyblockRemake.End.Dragon.DragonAi.SkyblockDragon;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Random;

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
        Loot.resetBlocks(Loot.reset, Loot.resetData);
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
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH, 5, 1);
            playerDMG.put(p,0D);
        }
        fightActive = true;

        new BukkitRunnable() {

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
                if(4 >= dragType){
                    entityDragon = (SkyblockEntity) DragonTypes.SUPERIOR.spawnEntity(spawnLoc);
                }else{
                    int select = new Random().nextInt(6);
                    switch (select){
                        case 0 -> entityDragon = (SkyblockEntity) DragonTypes.STRONG.spawnEntity(spawnLoc);
                        case 1 -> entityDragon= (SkyblockEntity) DragonTypes.WISE.spawnEntity(spawnLoc);
                        case 2 -> entityDragon = (SkyblockEntity) DragonTypes.YOUNG.spawnEntity(spawnLoc);
                        case 3 ->{
                            double random = Math.random() * 100;
                            entityDragon = (SkyblockEntity) DragonTypes.UNSTABLE.spawnEntity(spawnLoc);
                            if (random > 60) {
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        {
                                            if(!(activeDrag.isAlive())) {
                                                this.cancel();
                                            }
                                            PathFind.randomizeMovement(((CustomDragon)entityDragon).getSkyblockDragon());
                                        }
                                    }
                                }.runTaskTimer(Main.getMain(),50L,150L);
                            }
                        }
                        case 4 -> entityDragon = (SkyblockEntity) DragonTypes.PROTECTOR.spawnEntity(spawnLoc);
                        default ->  entityDragon = (SkyblockEntity) DragonTypes.OLD.spawnEntity(spawnLoc);
                    }
                }
                activeDrag = ((CustomDragon)entityDragon).getSkyblockDragon();
                as = ((CustomDragon)entityDragon).getFollower();
                maxDragHealth = entityDragon.getMaxHealth();
                dragonHealth = entityDragon.getHealth();


            }

        }.runTaskLater(Main.getMain(), 20 /* * 10*/);


    }
}
