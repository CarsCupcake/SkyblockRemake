package me.CarsCupcake.SkyblockRemake.Slayer.Zombie;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Slayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ZombieT5 extends ZombieSlayer {
    private final Set<TntThrow> tntThrows = new HashSet<>();
    private Zombie entity;
    private final BukkitRunnable healing = new BukkitRunnable() {
        @Override
        public void run() {
            setHealth((getHealth() + getMaxHealth()/1000 > getMaxHealth()) ? getMaxHealth() : getHealth() + getMaxHealth()/1000);
        }
    };
    private BukkitRunnable tntThrower;
    public ZombieT5(SkyblockPlayer player) {
        super(player);
    }

    @Override
    public int getMaxHealth() {
        return 10000000;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public void setHealth(int i) {
        health = i;
    }

    @Override
    public int getDamage() {
        return 2400;
    }

    @Override
    public void spawn(Location loc) {
        healing.runTaskTimer(Main.getMain(), 20, 20);
        restartRotation();
        startTnt();
    }

    private void restartRotation(){

    }

    private Block getNextFittingBlock(Block b){
        if(b.isPassable()){
            while (b.isPassable()){
                b = b.getLocation().subtract(0,1,0).getBlock();
            }
            return b;
        }else {
            while (!b.isPassable()){
                b = b.getLocation().add(0,1,0).getBlock();
            }
            return b.getLocation().subtract(0,1,0).getBlock();
        }
    }

    private void startThunder(){
        final Location l = getNextFittingBlock(entity.getLocation().getBlock()).getLocation().add(0.5, 1, 0.5);
        tntThrower.cancel();
        tntThrows.forEach(TntThrow::remove);
        tntThrows.clear();
        Set<Bundle<Block, Material>> blocks = new HashSet<>();
        blocks.add(new Bundle<>(l.clone().subtract(0,1,0).getBlock(), l.clone().subtract(0,1,0).getBlock().getType()));
        l.clone().subtract(0,1,0).getBlock().setType(Material.BEDROCK);
        tntThrower = new BukkitRunnable() {
            private int i;
            @Override
            public void run() {
                entity.teleport(l);
                i++;
                if(i % 20 == 0){
                    switch (i/20){
                        case 1 -> {

                        }
                    }
                }
            }

            @Override
            public synchronized void cancel() throws IllegalStateException {
                super.cancel();
                blocks.forEach(block -> block.getFirst().setType(block.getLast()));
            }
        };
    }

    private void startTnt(){
        tntThrower = new BukkitRunnable() {
            @Override
            public void run() {
                tntThrows.add(new TntThrow(ZombieT5.this));
            }
        };
        tntThrower.runTaskTimer(Main.getMain(), 20, 20);
    }

    @Override
    public String getName() {
        return "§fAtoned Horror";
    }

    @Override
    public HashMap<ItemManager, Integer> getDrops(SkyblockPlayer player) {
        return null;
    }

    @Override
    public void updateNameTag() {

    }

    @Override
    public void kill() {
        healing.cancel();
        try {
            tntThrower.cancel();
        }catch (Exception ignored){}
        tntThrows.forEach(TntThrow::remove);
    }

    @Override
    public void damage(double damage, SkyblockPlayer player) {
        health -= damage;
    }

    @Override
    public boolean hasNoKB() {
        return true;
    }

    @Override
    public int getTrueDamage() {
        return 0;
    }
    private static class TntThrow{
        private final ZombieT5 entity;
        public TntThrow(ZombieT5 t5){
            entity = t5;
        }
        public void remove(){

        }
    }
}
