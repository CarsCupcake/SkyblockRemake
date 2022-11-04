package me.CarsCupcake.SkyblockRemake.Enchantments.UltEnchants;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class FatalTempos {
    private static final HashMap<SkyblockPlayer, FatalTempos> temposHashMap = new HashMap<>();
    private final SkyblockPlayer player;
    private BukkitRunnable runnable;
    private int hits = 0;
    private int level;
    public FatalTempos(SkyblockPlayer player){
        this.player = player;
    }
    public void hit(int level){
        this.level = level;
        try {
            runnable.cancel();
        }catch (Exception ignored){

        }
        hits++;
        runnable = new BukkitRunnable() {
            @Override
            public void run() {
                hits = 0;
            }
        };
        runnable.runTaskLater(Main.getMain(), 3*20);
    }
    public int getHits(){
        return hits;
    }
    public int getLevel(){
        return level;
    }
    public static FatalTempos getInstance(SkyblockPlayer player){
        if(temposHashMap.containsKey(player))
            return temposHashMap.get(player);
        else {
            FatalTempos tempos = new FatalTempos(player);
            temposHashMap.put(player, tempos);
            return tempos;
        }
    }
}
