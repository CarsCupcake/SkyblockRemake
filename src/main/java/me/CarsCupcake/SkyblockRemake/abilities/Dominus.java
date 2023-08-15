package me.CarsCupcake.SkyblockRemake.abilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Items.Bonuses;
import me.CarsCupcake.SkyblockRemake.Items.FullSetBonus;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class Dominus implements FullSetBonus {


    private SkyblockPlayer player;
    public int stacks;
    private BukkitRunnable stackRunner;
    //in seconds
    private int stackRuntime;
    public static HashMap<Player, Dominus> playerDominus = new HashMap<>();

    public static void getKillEvent(EntityDeathEvent event) {
        if (event.getEntity().getKiller() != null) {
            if (playerDominus.containsKey(event.getEntity().getKiller())) {
                playerDominus.get(event.getEntity().getKiller()).setKill(event);
            }
        } else if (!event.getEntity().getScoreboardTags().isEmpty())
            for (String str : event.getEntity().getScoreboardTags())
                if (str.startsWith("killer:")) if (playerDominus.containsKey(Bukkit.getPlayer(str.split(":")[1]))) {
                    if (event.getEntity().getScoreboardTags().contains("abilitykill")) return;

                    playerDominus.get(Bukkit.getPlayer(str.split(":")[1])).setKill(event);
                    return;
                }

    }

    public static void setEvent() {
        Main.getMain().getServer().getPluginManager().registerEvents(new DominusListener(), Main.getMain());
    }

    public void setKill(EntityDeathEvent event) {

        if (player.bonusAmounts.get(Bonuses.Dominus) == 2) stackRuntime = 4;
        else if (player.bonusAmounts.get(Bonuses.Dominus) == 3) stackRuntime = 7;
        else stackRuntime = 10;

        if (stackRunner != null) try {
            stackRunner.cancel();
        } catch (Exception ignored) {

        }
        stackRunner = new BukkitRunnable() {

            @Override
            public void run() {

                stackRuntime -= 1;

                if (stackRuntime == 0) {
                    stacks -= 1;
                    Main.updatebar(player);
                    if (stacks == 0) stackRunner.cancel();
                    else {
                        if (player.bonusAmounts.get(Bonuses.Dominus) == 2) stackRuntime = 4;
                        else if (player.bonusAmounts.get(Bonuses.Dominus) == 3) stackRuntime = 7;
                        else stackRuntime = 10;
                    }

                }

            }
        };
        stackRunner.runTaskTimer(Main.getMain(), 0, 20);


        Main.updatebar(player);

        if (stacks < 10) stacks += 1;
    }


    public void dominusStrike(Location loc, Entity killedEntity) {
        player.playSound(player.getLocation(), Sound.BLOCK_PISTON_EXTEND, 1, 1);

        int yaw = new Random().nextInt(360) - 180;

        Vector dir = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ(), yaw, 0).getDirection();
        Location tempLocation = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ(), yaw, 0);
        dir.multiply(3);
        Location loc1 = tempLocation.add(dir);
        dir = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ(), yaw, 0).getDirection();
        dir.multiply(-3);
        Location loc2 = loc.add(dir);
        loc2.setY(loc.getY() + 2);

        Vector a = loc1.toVector();
        Vector b = loc2.toVector();

        Vector between = b.subtract(a); //get the vector between two points
        double length = between.length(); //get the length of the distance
        between.normalize().multiply(0.4); //normalize our vector and specify the spacing
        double steps = length / 0.4; //determine number of steps
        ArrayList<Entity> alrHitEntitys = new ArrayList<>();
        for (int i = 0; i < steps; i++) { //iterate up to but not beyond point b
            Vector point = a.add(between);

            Location l = new Location(loc.getWorld(), point.getX(), point.getY(), point.getZ());
            loc.getWorld().spawnParticle(Particle.FLAME, l, 1, 0, 0, 0, 0);


            for (Entity entity : player.getWorld().getNearbyEntities(player.getEyeLocation().add(player.getEyeLocation().getDirection().multiply(i)), 1, 1, 1)) {
                if(entity.isDead()) continue;
                if (entity instanceof LivingEntity e && !(entity instanceof ArmorStand) && entity != player && !alrHitEntitys.contains(entity) && !(entity instanceof Player)) {
                    Calculator calculator = new Calculator();
                    calculator.playerToEntityDamage(e, player);
                    calculator.damageEntity(e, player);
                    calculator.showDamageTag(e);
                    alrHitEntitys.add(entity);
                }
            }


        }


    }


    @Override
    public void start() {

        playerDominus.put(player, this);


    }

    @Override
    public void stop() {
        playerDominus.remove(player);

    }

    @Override
    public int getPieces() {

        return 2;
    }

    @Override
    public void setPlayer(SkyblockPlayer player) {

        this.player = player;

    }

    @Override
    public Bonuses getBonus() {

        return Bonuses.Dominus;
    }

    @Override
    public int getMaxPieces() {
        return 4;
    }


}
