package me.CarsCupcake.SkyblockRemake.Items.Enchantments.UltEnchants;

import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.GetTotalStatEvent;
import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class FatalTempoListner implements Listener {

    @EventHandler
    public void onHit(SkyblockDamageEvent event){
        if(event.getType() == SkyblockDamageEvent.DamageType.PlayerToEntity){
            int level = (event.getProjectile() != null) ?
                    ItemHandler.getEnchantmentLevel(SkyblockEnchants.FATAL_TEMPO, event.getProjectile()) :
                    ItemHandler.getEnchantmentLevel(SkyblockEnchants.FATAL_TEMPO, event.getPlayer().getEquipment().getItemInMainHand());
            if(level < 0)
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
        double pers = (10*tempos.getLevel())* tempos.getHits();
        if(pers > 200)
            pers = 200;
        event.setValue(event.getValue() + pers);

    }
}
