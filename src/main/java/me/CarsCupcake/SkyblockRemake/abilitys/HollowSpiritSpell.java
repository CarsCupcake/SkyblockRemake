package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Zombie;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;

public class HollowSpiritSpell {
    private static final HashMap<SkyblockPlayer, HollowSpiritSpell> spells = new HashMap<>();
    private ArrayList<Stack> stacks = new ArrayList<>(4);
    private final SkyblockPlayer player;
    private int remainingTime = 40;

    /**
     * This Class is used for spells which get activated by the 'Hollow Wand'
     * @param player is the player which is the owner of the wand
     */
    public HollowSpiritSpell(SkyblockPlayer player){
        spells.put(player,this);
        this.player = player;


    }
    public void addSack(Stack stack){
        if(stacks.size() != 4)
            stacks.add(stack);
        if(stacks.size() == 1)
            start();
        displayStacks();
    }
    private void displayStacks(){
        StringBuilder builder = new StringBuilder("§8[");
        Iterator<Stack> stackIterator = ((ArrayList<Stack>)stacks.clone()).iterator();
        while (stackIterator.hasNext()){
            Stack stack = stackIterator.next();
            builder.append(stack.getDisplay());
            if(stackIterator.hasNext())
                builder.append(" ");
        }
        builder.append("§8]");
        player.sendTitle(builder.toString(), "", 0, remainingTime, 0);
    }
    private void start(){
        new BukkitRunnable() {
            @Override
            public void run() {
                remainingTime--;
                if(remainingTime == 0){
                    cancel();
                    activate();
                }
            }
        }.runTaskTimer(Main.getMain(), 1, 1);
    }
    public void activate(){
        spells.remove(player);
        if(stacks.size() == 1){
            if(stacks.get(0) == Stack.Spirit){

                player.sendMessage("§eCasting Spell: Spirit Spark");
                spiritSpark();

            }else {
                hollowesShield();
                player.sendMessage("§eCasting Spell: Hollowed Shield");
            }
        }else if(stacks.size() == 3){
            if(stacks.get(0) == Stack.Spirit && stacks.get(1) == Stack.Spirit && stacks.get(2) == Stack.Hollowed){

                player.sendMessage("§eSpirit Barage");

            }else if(stacks.get(0) == Stack.Hollowed && stacks.get(1) == Stack.Hollowed && stacks.get(2) == Stack.Spirit){

                player.sendMessage("§eHollowed Barage");

            }else player.sendMessage("§cYou activated no spell!");

        }else if(stacks.size() == 4){

            if(stacks.get(0) == Stack.Spirit && stacks.get(1) == Stack.Spirit && stacks.get(2) == Stack.Spirit && stacks.get(3) == Stack.Spirit){

                player.sendMessage("§eDivine Spark");

            }else player.sendMessage("§cYou activated no spell!");

        }else player.sendMessage("§cYou activated no spell!");

    }
    public static HollowSpiritSpell getSpell(SkyblockPlayer player){
        if(spells.containsKey(player))
            return spells.get(player);
        else
            return new HollowSpiritSpell(player);
    }

    /**
     * This is the method for the Spell 'Spirit Spark'
     */
    private void spiritSpark(){
        List<Entity> entities = player.getNearbyEntities(10,10,10);
        TreeMap<Double, Zombie> zombieTreeMap = new TreeMap<>();
        for(Entity entity : entities)
            if(entity instanceof Zombie)
                zombieTreeMap.put(entity.getLocation().distance(player.getLocation()), (Zombie) entity);
        if(zombieTreeMap.isEmpty()){
            player.sendMessage("§cNo nearby players!");
            return;
        }

        Map.Entry<Double, Zombie> closest = zombieTreeMap.firstEntry();


        double ticksMult = getTeleportMult(closest.getKey());
        final Location finalLocation = closest.getValue().getEyeLocation().clone();

        new BukkitRunnable() {
            int i = 0;
            Location loc = player.getEyeLocation();
            @Override
            public void run() {
                if(i == 0) {
                    loc.setDirection(finalLocation.clone().subtract(loc.clone()).toVector());
                }
                i++;
                if(i > 20)
                    cancel();


                loc = loc.add(loc.getDirection().normalize().multiply(ticksMult));


                double offset = getOffset((i*0.1)*Math.PI, 0.5);
                player.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, loc.clone().add(0,offset,0), 0,0,0,1, 0, null);

            }
        }.runTaskTimer(Main.getMain(), 0, 1);
    }

    /**
     * This is the method for the Spell 'Hollowed Shield'
     */
    private void hollowesShield(){
        List<Entity> entities = player.getNearbyEntities(10,10,10);
        TreeMap<Double, Zombie> zombieTreeMap = new TreeMap<>();
        for(Entity entity : entities)
            if(entity instanceof Zombie)
                zombieTreeMap.put(entity.getLocation().distance(player.getLocation()), (Zombie) entity);
        if(zombieTreeMap.isEmpty()){
            player.sendMessage("§cNo nearby players!");
            return;
        }

        Map.Entry<Double, Zombie> closest = zombieTreeMap.firstEntry();
        double ticksMult = getTeleportMult(closest.getKey());
        final Location finalLocation = closest.getValue().getEyeLocation().clone();

        new BukkitRunnable() {
            int i = 0;
            Location loc = player.getEyeLocation();
            @Override
            public void run() {
                if(i == 0) {
                    loc.setDirection(finalLocation.clone().subtract(loc.clone()).toVector());
                }
                i++;
                if(i > 20) {
                    cancel();
                    shield(closest.getValue().getLocation());
                }


                loc = loc.add(loc.getDirection().normalize().multiply(ticksMult));


                double offset = getOffset((i*0.1)*Math.PI, 0.5);
                player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, loc.clone().add(0,offset,0), 0,0,0,1, 0, null);

            }
        }.runTaskTimer(Main.getMain(), 0, 1);
    }

    /**
     * this method displays an animation for the shield wich is given for the targeted player
     * @param base is the base Location for the animation
     */
    private void shield(Location base){
        new BukkitRunnable() {
            private int ticks = 0;
            private Location corner1 = base.clone().add(0.5,0.1,0.5);
            private Location corner2 = base.clone().add(-0.5,0.1,0.5);
            private Location corner3 = base.clone().add(0.5,0.1,-0.5);
            private Location corner4 = base.clone().add(-0.5,0.1,-0.5);
            private final Particle.DustOptions color1 = new Particle.DustOptions(Color.fromRGB(0x78def5), 1.5f);
            private final Particle.DustOptions color2 = new Particle.DustOptions(Color.fromRGB(0x2ab2d1), 1.5f);
            private final Particle.DustOptions color3 = new Particle.DustOptions(Color.fromRGB(0x02c6f2), 1.5f);
            @Override
            public void run() {

                if(ticks == 0){
                    for(double i = 0; i > -1; i -= 0.1) {
                        base.getWorld().spawnParticle(Particle.REDSTONE, corner1.clone().add(i, 0, 0), 1, getRandom());
                        base.getWorld().spawnParticle(Particle.REDSTONE, corner1.clone().add(0, 0, i), 1, getRandom());
                        base.getWorld().spawnParticle(Particle.REDSTONE, corner4.clone().add(-i, 0, 0), 1, getRandom());
                        base.getWorld().spawnParticle(Particle.REDSTONE, corner4.clone().add(0, 0,-i), 1, getRandom());
                    }
                }else{
                    corner1 = corner1.add(0,0.1,0);
                    corner2 = corner2.add(0,0.1,0);
                    corner3 = corner3.add(0,0.1,0);
                    corner4 = corner4.add(0,0.1,0);
                }
                if(ticks == 20){
                    cancel();
                    for(double i = 0; i > -1; i -= 0.1) {
                        base.getWorld().spawnParticle(Particle.REDSTONE, corner1.clone().add(i, 0, 0), 1, getRandom());
                        base.getWorld().spawnParticle(Particle.REDSTONE, corner1.clone().add(0, 0, i), 1, getRandom());
                        base.getWorld().spawnParticle(Particle.REDSTONE, corner4.clone().add(-i, 0, 0), 1, getRandom());
                        base.getWorld().spawnParticle(Particle.REDSTONE, corner4.clone().add(0, 0, -i), 1, getRandom());
                    }

                }
                base.getWorld().spawnParticle(Particle.REDSTONE, corner1,1, getRandom());
                base.getWorld().spawnParticle(Particle.REDSTONE, corner2,1, getRandom());
                base.getWorld().spawnParticle(Particle.REDSTONE, corner3,1, getRandom());
                base.getWorld().spawnParticle(Particle.REDSTONE, corner4,1, getRandom());





                ticks++;
            }
            private Particle.DustOptions getRandom(){
                //Should be a random number between 0-2
                int num = new Random().nextInt(3);
                switch (num){
                    case 0 -> {
                        return color1;
                    }
                    case 1 -> {
                        return color2;
                    }
                    case 2 -> {
                        return color3;
                    }
                    default -> throw new IllegalArgumentException("Number is somehow out of bounds: " + num);
                }

            }
        }.runTaskTimer(Main.getMain(), 0, 1);

    }
    private double getOffset(double i, double mult){
        return Math.sin(i*mult) + 0.5;
    }
    private double getTeleportMult(double block){
        return 0.05*block;
    }



    public enum Stack{
        Spirit("§c✤"),
        Hollowed("§b✦");

        private final String display;

        Stack(String s) {
            display = s;
        }
        public String getDisplay(){
            return display;
        }
    }
}
