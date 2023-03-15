package me.CarsCupcake.SkyblockRemake.Items.minions;

import me.CarsCupcake.SkyblockRemake.Skyblock.ServerType;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.privateIsle.PrivateIsle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class MinionListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(ServerType.getActiveType() != ServerType.PrivateIsle)
            return;
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
        PrivateIsle isle = PrivateIsle.isles.get(player);
        for (Minion minion : isle.minions.values()){
            if(minion instanceof MiningMinion miningMinion){
                if(miningMinion.getBlocks().contains(event.getBlock()))
                    miningMinion.checkHasSpace();
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockPlaceEvent event) {
        if(ServerType.getActiveType() != ServerType.PrivateIsle)
            return;
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
        PrivateIsle isle = PrivateIsle.isles.get(player);
        for (Minion minion : isle.minions.values()){
            if(minion instanceof MiningMinion miningMinion){
                if(miningMinion.getBlocks().contains(event.getBlock()))
                    miningMinion.checkHasSpace();
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void interact(PlayerInteractAtEntityEvent event){
        if(ServerType.getActiveType() != ServerType.PrivateIsle)
            return;
        System.out.println(event.getRightClicked());
        if(event.getRightClicked() instanceof ArmorStand stand){
            for (Minion m : PrivateIsle.isles.get(SkyblockPlayer.getSkyblockPlayer(event.getPlayer())).minions.values()){
                if(m.getArmorStand().equals(stand))
                    m.showInventory();
            }
        }
    }
}
