package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Items.AbilityPreExecuteEvent;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class AspectOfTheVoid extends PreAbilityExecution implements AbilityManager<PlayerInteractEvent> {
    @Override
    public boolean executeAbility(PlayerInteractEvent event) {
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
        Block block = player.getTargetBlockExact(57);
        if(block == null)
            return false;
        if(checkValid(block)) {
            Location loc = block.getLocation().add(0.5, 1, 0.5);
            loc.setYaw(player.getLocation().getYaw());
            loc.setPitch(player.getLocation().getPitch());
            player.teleport(loc);
        }

        return false;
    }
    private boolean checkValid(Block block){

        Location loc1 = block.getLocation().clone().add(0,1,1);
        Location loc2 = block.getLocation().clone().add(0,2,1);
        if((loc1.getBlock().isPassable() || loc1.getBlock().isEmpty() || loc1.getBlock().isLiquid()) && (loc2.getBlock().isPassable() || loc2.getBlock().isEmpty() || loc2.getBlock().isLiquid()))
          return true;
        return false;
    }





    @Override
    public void preEvent(AbilityPreExecuteEvent event) {
        if((event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) && event.getPlayer().isSneaking()) {
            SkyblockPlayer player = event.getPlayer();
            Block block = player.getTargetBlockExact(57);
            if (block == null) {
                event.setCancelled(true);
                player.sendMessage("§cNo free block");
                return;
            }
            if (!checkValid(block)) {
                event.setCancelled(true);
                player.sendMessage("§cNo free block");
            }
        }
    }
}
