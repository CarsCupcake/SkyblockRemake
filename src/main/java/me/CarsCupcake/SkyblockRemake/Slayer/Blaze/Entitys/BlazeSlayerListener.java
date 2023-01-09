package me.CarsCupcake.SkyblockRemake.Slayer.Blaze.Entitys;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;

public class BlazeSlayerListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onDamage(BlockDamageEvent event){
        if(FirePillar.pillars.containsKey(event.getBlock())){
            FirePillar pillar = FirePillar.pillars.get(event.getBlock());
            if(pillar.getPlayer() != SkyblockPlayer.getSkyblockPlayer(event.getPlayer()))
                return;
            pillar.hit();
        }
    }
}
