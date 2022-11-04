package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FireworkExplodeEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class DeployableListener implements Listener {
    @EventHandler
    public void onFireworkExplode(FireworkExplodeEvent event){
        SkyblockPlayer player = null;
        for (String str : event.getEntity().getScoreboardTags()){
            if(str.startsWith("shooter:")){
                player = SkyblockPlayer.getSkyblockPlayer(Bukkit.getPlayer(str.split(":")[1]));

            }
        }

        if(Deployable.deployables.containsKey(player)){
            Deployable.deployables.get(player).start(event.getEntity().getLocation());
        }

    }
    @EventHandler
    public void addPlayer(PlayerMoveEvent event){
        for (Deployable flare : Deployable.deployables.values()){
            flare.addStatBoost(event);
        }
    }
}
