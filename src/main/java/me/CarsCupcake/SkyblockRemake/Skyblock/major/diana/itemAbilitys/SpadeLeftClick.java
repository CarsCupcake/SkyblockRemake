package me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.itemAbilitys;

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.MythologicalPerk;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.event.player.PlayerInteractEvent;

import java.awt.*;

public class SpadeLeftClick implements AbilityManager<PlayerInteractEvent> {
    @Override
    public boolean triggerAbility(PlayerInteractEvent event) {
        if (Tools.isBlock(event.getAction())) {
            MythologicalPerk perk = MythologicalPerk.getPlayer(event.getPlayer());
            if (perk == null || !perk.isBlock(event.getClickedBlock())) {
                Location l = Tools.getAsLocation(event.getClickedBlock()).add(0, 1, 0);
                Particle.DustOptions dustOptions = new Particle.DustOptions(Color.GRAY, 1);
                event.getPlayer().spawnParticle(Particle.REDSTONE, l, 1, dustOptions);
                return true;
            }
            perk.dig(event.getClickedBlock());
        }
        return true;
    }
}
