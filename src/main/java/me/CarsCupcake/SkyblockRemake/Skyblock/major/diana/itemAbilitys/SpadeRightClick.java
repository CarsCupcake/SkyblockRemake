package me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.itemAbilitys;

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.ServerType;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.MythologicalPerk;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public class SpadeRightClick implements AbilityManager<PlayerInteractEvent> {
    @Override
    public boolean triggerAbility(PlayerInteractEvent event) {
        if (ServerType.getActiveType() != ServerType.Hub) return false;
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
        Block b = event.getClickedBlock();
        if (b == null) {
            trace(player);
            return true;
        }
        MythologicalPerk perk = MythologicalPerk.getPlayer(event.getPlayer());
        if (perk == null || !perk.isBlock(event.getClickedBlock())) {
            Location l = Tools.getAsLocation(event.getClickedBlock()).add(0, 1, 0);
            Particle.DustOptions dustOptions = new Particle.DustOptions(Color.GRAY, 1);
            event.getPlayer().spawnParticle(Particle.REDSTONE, l, 1, dustOptions);
            trace(player);
            return true;
        }
        perk.dig(event.getClickedBlock());
        return true;
    }
    private void trace(SkyblockPlayer player) {
        MythologicalPerk.BurrowChain tracked = MythologicalPerk.getPlayer(player).getTracked();
        Location pL = player.getEyeLocation();
        Location target = Tools.getAsLocation(tracked.getBlock());
        Vector dir = target.toVector().subtract(pL.toVector()).normalize();
        int stepps = (int) (pL.distance(target));
        new BukkitRunnable() {
            Location current = pL;
            int i = 0;
            @Override
            public void run() {
                if (current.distance(player.getLocation()) > 30) {
                    cancel();
                    return;
                }
                current.add(dir);
                i++;
                if (i >= stepps) {
                    current = target;
                }
                double y = getY((double) i / (double) stepps);
                Location rel = current.clone().add(0, y, 0);
                player.spawnParticle(Particle.DRIP_LAVA, rel, 1,0,0,0, 0);
                player.spawnParticle(Particle.FIREWORKS_SPARK, rel, 1,0,0,0, 0);
                player.playSound(rel, (new Random().nextDouble() <= .1) ? Tools.randomEnum(Sound.class) : Sound.BLOCK_NOTE_BLOCK_PLING, 1F, (float) (0.5f + ((double) i / (double) stepps)));
                if (i >= stepps) {
                    cancel();
                }
            }
        }.runTaskTimer(Main.getMain(), 0, 1);
    }

    private double getY(double x) {
        return 5d * (-Math.pow(2 * x-1d, 4d) + 1d);
    }
}
