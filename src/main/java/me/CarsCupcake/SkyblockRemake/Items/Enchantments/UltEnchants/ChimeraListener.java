package me.CarsCupcake.SkyblockRemake.Items.Enchantments.UltEnchants;

import me.CarsCupcake.SkyblockRemake.API.ItemEvents.GetStatFromItemEvent;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChimeraListener implements Listener {
    @EventHandler
    public void getStat(GetStatFromItemEvent event){
        if(event.getPlayer() == null)
            return;
        if(event.getItem().getItemMeta() == null)
            return;
        if(!ItemHandler.hasEnchantment(SkyblockEnchants.CHIMERA, event.getItem()))
            return;
        if (event.getPlayer().getPetEquip() != null) {
            event.addValue(getValue(event.getPlayer().getPetEquip().getStat(event.getStat()), ItemHandler.getEnchantmentLevel(SkyblockEnchants.CHIMERA, event.getItem())));
        }
    }
    private double getValue(double petStat, int chimera){
        double pers = 0.2*chimera;
        return petStat*pers;
    }
}
