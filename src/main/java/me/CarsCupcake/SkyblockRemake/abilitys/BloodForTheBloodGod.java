package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Particle;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class BloodForTheBloodGod implements AbilityManager<PlayerInteractEvent> {
    @Override
    public boolean executeAbility(PlayerInteractEvent event) {
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
        new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {
                i++;
                player.getWorld().spawnParticle(Particle.FLAME, player.getLocation().add(0, 0.2, 0), 15 ,0.25, 0.7, 0.25, 0, null);
                if(i >= 20*5)
                    cancel();
            }
        }.runTaskTimer(Main.getMain(), 0, 1);

        return false;
    }

}
