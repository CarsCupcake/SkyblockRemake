package me.CarsCupcake.SkyblockRemake.Items.trading;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class TradeMenuListener implements Listener {
    @EventHandler
    public void onInteractr(PlayerInteractAtEntityEvent event){
        if(!event.getPlayer().isSneaking()) return;
        if(!(event.getRightClicked() instanceof Player player)) return;
        new TradeMember(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()), SkyblockPlayer.getSkyblockPlayer(player), true);
    }

}
