package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6.Entitys;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6.Phase1;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class TerraTransition {
    private int phase = 0;
    private final BukkitRunnable run;
    @Getter
    private final Location l;
    private final ArrayList<Bundle<Block, Material>> dataHolder = new ArrayList<>();

    public TerraTransition(Location location){
        l = location.getBlock().getLocation().clone();
        nextStep();

        boolean isInTheWay = false;
        for (TerraTransition tt : Phase1.getPhase().dead)
            if(tt.isInTheWay(l)) {
                isInTheWay = true;
                break;
            }
        if(isInTheWay){
            run = new BukkitRunnable() {
                @Override
                public void run() {
                    remove();
                    Terracotta terracotta = new Terracotta();
                    terracotta.spawn(l);
                    Phase1.getPhase().terracottas.add(terracotta);
                    Phase1.getPhase().dead.remove(TerraTransition.this);
                }
            };
            run.runTaskLater(Main.getMain(), 200);
        }else {


            run = new BukkitRunnable() {
                @Override
                public void run() {
                    nextStep();

                }
            };
            run.runTaskTimer(Main.getMain(), 50, 50);
        }
    }
    @SuppressWarnings("Deprecation")
    private void nextStep(){
        phase++;
        if(phase == 4){
            remove();
            Terracotta terracotta = new Terracotta();
            terracotta.spawn(l);
            Phase1.getPhase().terracottas.add(terracotta);
            Phase1.getPhase().dead.remove(this);
        }
        switch (phase){
            case 1 ->{
                dataHolder.add(new Bundle<>(l.getBlock(), l.getBlock().getType()));
                Block b = l.getBlock();
                b.setType(Material.FLOWER_POT);
                b.getState().update(true, false);
            }
            case 2 -> l.getBlock().setType(Material.BROWN_TERRACOTTA);
            case 3 -> {
                dataHolder.add(new Bundle<>(l.clone().add(0,1,0).getBlock(), l.clone().add(0,1,0).getBlock().getType()));
                l.clone().add(0,1,0).getBlock().setType(Material.PLAYER_HEAD);
                ItemStack item = Tools.CustomHeadTexture("https://textures.minecraft.net/texture/22bbcc3786777230631a1607562b78a90a498f0e24e43fa06d27775dca15d77d");
                l.clone().add(0,1,0).getBlock().getState().setData(item.getData());l.clone().add(0,1,0).getBlock().getState().update();
            }
        }
        //Poppy topf on ground
        //brown terracotta
        //add head on terracotta
        //respawn
    }


    public void remove(){
        if(!dataHolder.isEmpty()){
            Bundle<Block, Material> bundle = dataHolder.get(0);
            bundle.getFirst().setType(bundle.getLast());
            if(dataHolder.size() == 2){
                bundle = dataHolder.get(1);
                bundle.getFirst().setType(bundle.getLast());
            }
        }
        dataHolder.clear();
        run.cancel();
    }
    public boolean isInTheWay(Location l){
        return l.clone().distance(this.l.clone()) < 1 || l.clone().add(0, 1, 0).distance(this.l.clone()) < 1 || this.l.clone().add(0, 1, 0).distance(l.clone()) < 1;
    }
}
