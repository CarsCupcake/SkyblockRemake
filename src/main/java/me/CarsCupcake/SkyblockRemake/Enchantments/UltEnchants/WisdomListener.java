package me.CarsCupcake.SkyblockRemake.Enchantments.UltEnchants;

import me.CarsCupcake.SkyblockRemake.API.ItemEvents.GetStatFromItemEvent;
import me.CarsCupcake.SkyblockRemake.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Stats;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class WisdomListener implements Listener {
    @EventHandler
    public void updateStat(GetStatFromItemEvent event){
        if(event.getPlayer() == null)
            return;
        if(event.getItem().getItemMeta() == null)
            return;
        if(event.getStat() == Stats.Inteligence)
           if(event.getItem().getItemMeta().getEnchants().containsKey(SkyblockEnchants.WISDOM)) {
               int extra = event.getItem().getItemMeta().getEnchantLevel(SkyblockEnchants.WISDOM) * event.getPlayer().getLevel();
               if(extra > 20 * event.getItem().getItemMeta().getEnchantLevel(SkyblockEnchants.WISDOM))
                   extra = 20 * event.getItem().getItemMeta().getEnchantLevel(SkyblockEnchants.WISDOM);

               event.setValue(event.getValue() + extra);
           }
    }
}
