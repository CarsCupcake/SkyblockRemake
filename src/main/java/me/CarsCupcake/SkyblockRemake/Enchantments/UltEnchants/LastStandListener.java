package me.CarsCupcake.SkyblockRemake.Enchantments.UltEnchants;

import me.CarsCupcake.SkyblockRemake.API.ItemEvents.GetStatFromItemEvent;
import me.CarsCupcake.SkyblockRemake.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Stats;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LastStandListener implements Listener {
    @EventHandler
    public void onClalc(GetStatFromItemEvent event){
        if(event.getPlayer() == null)
            return;

        if(event.getItem().getItemMeta() == null)
            return;
        if(event.getStat() == Stats.Defense)
            if(event.getItem().getItemMeta().getEnchants().containsKey(SkyblockEnchants.LAST_STAND))
                if(hppers(event.getPlayer()) < 0.4) {

                    double pers = 0.05 * event.getItem().getItemMeta().getEnchantLevel(SkyblockEnchants.LAST_STAND);
                    pers += 1;
                    event.setValue(event.getValue() * pers);
                }

    }
    private double hppers(SkyblockPlayer player){
        double pers = player.currhealth / Main.playerhealthcalc(player);
        return pers;
    }
}
