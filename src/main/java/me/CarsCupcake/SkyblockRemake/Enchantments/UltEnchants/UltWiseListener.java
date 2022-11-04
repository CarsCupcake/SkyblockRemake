package me.CarsCupcake.SkyblockRemake.Enchantments.UltEnchants;

import me.CarsCupcake.SkyblockRemake.API.ItemEvents.ManaUpdateEvent;
import me.CarsCupcake.SkyblockRemake.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Items.AbilityPreExecuteEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class UltWiseListener implements Listener {
    @EventHandler
    public void manaUpdate(ManaUpdateEvent event){
        if(event.getMana() != 0 && event.getItem().getItemMeta().getEnchants().containsKey(SkyblockEnchants.ULTIMATE_WISE)){
            event.setMana(event.getMana() - (int) (event.getMana() * SkyblockEnchants.ULTIMATE_WISE.getPersentage(event.getItem().getItemMeta().getEnchantLevel(SkyblockEnchants.ULTIMATE_WISE))));
        }

    }

    @EventHandler
    public void manaReduce(AbilityPreExecuteEvent event){
        if(event.getPayedMana() > 0 && !event.isCancelled()){
            event.setPayedMana(event.getPayedMana() - (int) (event.getPayedMana() * SkyblockEnchants.ULTIMATE_WISE.getPersentage(event.getPlayer().getItemInHand().getItemMeta().getEnchantLevel(SkyblockEnchants.ULTIMATE_WISE))));
        }
    }

}
