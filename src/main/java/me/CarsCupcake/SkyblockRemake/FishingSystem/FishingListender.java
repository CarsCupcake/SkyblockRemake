package me.CarsCupcake.SkyblockRemake.FishingSystem;



import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.ServerType;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockServer;
import me.CarsCupcake.SkyblockRemake.Stats;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import java.util.Random;


public class FishingListender implements Listener {

@EventHandler
public void fishingPullout(PlayerFishEvent event) {

event.getHook().setApplyLure(false);


    if (event.getState().equals(PlayerFishEvent.State.FISHING)){


        double fishingSpeed = 400d;
        Random r = new Random();
        int low = 200;
        int high = (int)fishingSpeed;
        fishingSpeed = r.nextInt(high-low) + low;
        int BaseTicks = (int) (fishingSpeed - ((Main.getPlayerStat(SkyblockPlayer.getSkyblockPlayer(SkyblockPlayer.getSkyblockPlayer(event.getPlayer())), Stats.FishingSpeed)/((SkyblockServer.getServer().getType() == ServerType.CrimsonIsle) ? 350 : 300))*fishingSpeed));


        if(BaseTicks < 2)
            BaseTicks = 2;


        event.getHook().setMinWaitTime(BaseTicks - 1);
        event.getHook().setMaxWaitTime( BaseTicks +1);
    if(Items.SkyblockItems.get(event.getPlayer().getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(
                Main.getMain(), "id"
        ), PersistentDataType.STRING)).getRodType() == RodType.LavaRod){
            event.getHook().setVisualFire(false);
            event.getHook().setFireTicks(0);
            new LavaFishingHook(event.getHook(), SkyblockPlayer.getSkyblockPlayer(event.getPlayer()), BaseTicks);
        }
    }





    if (event.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)){
        if(event.getHook().getLocation().getBlock().getType() != Material.LAVA && Items.SkyblockItems.get(event.getPlayer().getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(
                Main.getMain(), "id"
        ), PersistentDataType.STRING)).getRodType() == RodType.LavaRod)
            event.setCancelled(true);

        event.getCaught().remove();

        int random = new Random().nextInt(100);
        if(random < Main.playerseacreaturechance(event.getPlayer())){
            Entity z =	SeaCreatures.roolNormalFishingDice(event.getHook().getLocation().add(0,2,0), event.getPlayer());
            Vector vec = event.getPlayer().getEyeLocation().toVector().subtract(event.getHook().getLocation().toVector());
            vec.multiply(0.15);
            z.setVelocity(vec);}else {
            System.out.println(random);
        }
    }
    if(event.getState() == PlayerFishEvent.State.REEL_IN){
        if(LavaFishingHook.contains(event.getHook()))
        LavaFishingHook.get(event.getHook()).reelIn();
    }
}



}

