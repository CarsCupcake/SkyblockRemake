package me.CarsCupcake.SkyblockRemake.Slayer.Blaze.Entitys;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class FirePits {
    private final HashMap<Block, Material> blocks = new HashMap<>();
    @Getter
    private final FirePitExecuter executer;
    @Getter
    private final SkyblockPlayer player;
    private BukkitRunnable runnable;
    private final boolean withNew;
    public FirePits(FirePitExecuter executer, SkyblockPlayer player, boolean withNew){
        this.withNew = withNew;
        this.executer = executer;
        this.player = player;
        Set<Block> b = getStar(executer.getLocation());
        Location l = executer.getLocation();
        b.addAll(getStar(l.clone().add(3,0,3)));
        b.addAll(getStar(l.clone().add(3,0,-3)));
        b.addAll(getStar(l.clone().add(-3,0,3)));
        b.addAll(getStar(l.clone().add(-3,0,-3)));

        for (Block block : b){
            blocks.put(block, block.getType());
            block.setType(Material.ORANGE_CONCRETE);
        }
        runnable = new BukkitRunnable() {
            @Override
            public void run() {
                blocks.keySet().forEach(block -> block.setType(Material.RED_CONCRETE));
                runnable = new BukkitRunnable() {
                    @Override
                    public void run() {
                        lightUp();
                    }
                };
                runnable.runTaskLater(Main.getMain(), 15);
            }
        };
        runnable.runTaskLater(Main.getMain(), 15);


    }
    private void lightUp(){
        blocks.keySet().forEach(block -> {
            block.setType(blocks.get(block));
            block.getLocation().clone().add(0,1,0).getBlock().setType(Material.FIRE);
        });

        runnable = new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {
                if(isInFire()) {
                    Calculator c = new Calculator();
                    c.entityToPlayerDamage(null, player, new Bundle<>(0, (int) (Main.getPlayerStat(player, Stats.Health) * 2)));
                    c.damagePlayer(player);
                    c.showDamageTag(player);
                }
                i++;
                if(i >= 4){
                    for (Block b : blocks.keySet())
                        b.getLocation().add(0,1,0).getBlock().setType(Material.AIR);

                    cancel();
                    blocks.clear();
                    if(withNew)
                        executer.finishPit();
                }
            }
        };
        runnable.runTaskTimer(Main.getMain(), 0, 20);


    }
    private boolean isInFire(){
        for (Block b : blocks.keySet()){
            if(b.equals(player.getLocation().getBlock()))
                return true;
        }
        return false;
    }



    private Set<Block> getStar(Location l){
        Set<Block> b = new HashSet<>();
        b.add(getNextHighest(l.getBlock()));
        b.add(getNextHighest(l.clone().add(1,0,0).getBlock()));
        b.add(getNextHighest(l.clone().add(-1,0,0).getBlock()));
        b.add(getNextHighest(l.clone().add(0,0,1).getBlock()));
        b.add(getNextHighest(l.clone().add(0,0,-1).getBlock()));
        return b;
    }
    private Block getNextHighest(Block b){
        if(b.isPassable()){
            while (b.isPassable()){
                b = b.getLocation().subtract(0, 1, 0).getBlock();
                if(!b.isPassable())
                    return b;
            }
        }else {
            while (!b.isPassable()) {
                Block newBlock = b.getLocation().add(0, 1, 0).getBlock();
                if (newBlock.isPassable())
                    return b;
                else
                    b = newBlock;
            }
        }
        return b;
    }
    public void remove(){
        for (Block b : blocks.keySet()) {
            b.setType(blocks.get(b));
            b.getLocation().add(0,1,0).getBlock().setType(Material.AIR);
        }
        blocks.clear();
    }
    public interface FirePitExecuter {
        void finishPit();
        Location getLocation();

    }
}
