package me.CarsCupcake.SkyblockRemake.NPC;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class NPCEventHandler implements Listener {
    @EventHandler
    public void  getInteraction(PlayerInteractAtEntityEvent event){

        if(event.getRightClicked() instanceof LivingEntity)
          if(EntityNPC.getNPCs().containsKey((LivingEntity) event.getRightClicked())){
              EntityNPCInteractionEvent e = new EntityNPCInteractionEvent(EntityNPC.getNPCs().get((LivingEntity) event.getRightClicked()), SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
              Bukkit.getPluginManager().callEvent(e);
              event.setCancelled(e.isCancelled());

          }
    }
}
