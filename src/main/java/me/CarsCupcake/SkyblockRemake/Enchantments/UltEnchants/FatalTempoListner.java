package me.CarsCupcake.SkyblockRemake.Enchantments.UltEnchants;

import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.GetTotalStatEvent;
import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Stats;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class FatalTempoListner implements Listener {

    @EventHandler
    public void onHit(SkyblockDamageEvent event){
        if(event.getType() == SkyblockDamageEvent.DamageType.PlayerToEntity && event.getCalculator().getProjectile() != null){
            Projectile projectile = event.getProjectile();
            boolean hasFatalTempo = false;
            int level = 0;
            for (String s : projectile.getScoreboardTags())
                if(s.startsWith(SkyblockEnchants.FATAL_TEMPO.getKey().getKey())) {
                    hasFatalTempo = true;
                    level = Integer.parseInt(s.split(":")[1]);
                }
            if(!hasFatalTempo)
                return;
            FatalTempos.getInstance(event.getPlayer()).hit(level);
        }
    }
    @EventHandler
    public void onUpdate(GetTotalStatEvent event){
        if(event.getStat() != Stats.Ferocity)
            return;

        FatalTempos tempos = FatalTempos.getInstance(event.getPlayer());
        if(tempos.getHits() <= 0)
            return;
        double pers = (0.1*tempos.getLevel())* tempos.getHits();
        if(pers > 2)
            pers = 2;
        pers++;
        event.setValue(event.getValue() * pers);

    }
}
