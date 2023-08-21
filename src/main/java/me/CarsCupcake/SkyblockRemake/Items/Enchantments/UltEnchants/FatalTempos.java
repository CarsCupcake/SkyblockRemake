package me.CarsCupcake.SkyblockRemake.Items.Enchantments.UltEnchants;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class FatalTempos {
    private static final HashMap<SkyblockPlayer, FatalTempos> temposHashMap = new HashMap<>();
    private BukkitRunnable runnable;
    @Getter
    private int hits = 0;
    @Getter
    private int level;
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

    public static FatalTempos getInstance(SkyblockPlayer player){
        if(temposHashMap.containsKey(player))
            return temposHashMap.get(player);
        else {
            FatalTempos tempos = new FatalTempos();
            temposHashMap.put(player, tempos);
            return tempos;
        }
    }
}
