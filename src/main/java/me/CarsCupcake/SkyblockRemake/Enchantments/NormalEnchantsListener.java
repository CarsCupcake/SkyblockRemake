package me.CarsCupcake.SkyblockRemake.Enchantments;

import me.CarsCupcake.SkyblockRemake.API.HealthChangeReason;
import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NormalEnchantsListener implements Listener {
    @EventHandler
    public void sharpness(SkyblockDamageEvent event){
        if(event.getType() != SkyblockDamageEvent.DamageType.PlayerToEntity)
            return;
        if(event.getPlayer().getItemInHand() != null && event.getPlayer().getItemInHand().getItemMeta() != null){
            if(event.getPlayer().getItemInHand().getItemMeta().getEnchants().containsKey(Enchantment.DAMAGE_ALL)
                    && event.getPlayer().getItemInHand().getItemMeta().getEnchants().get(Enchantment.DAMAGE_ALL) != 0) {
                double mult;
                switch (event.getPlayer().getItemInHand().getItemMeta().getEnchantLevel(Enchantment.DAMAGE_ALL)) {
                    case 1 -> mult = 1.05;
                    case 2 -> mult = 1.1;
                    case 3 -> mult = 1.15;
                    case 4 -> mult = 1.2;
                    case 5 -> mult = 1.3;
                    case 6 -> mult = 1.45;
                    case 7 -> mult = 1.65;
                    default ->
                            mult = 0.05 * event.getPlayer().getItemInHand().getItemMeta().getEnchantLevel(Enchantment.DAMAGE_ALL) + 1;
                }
                event.getCalculator().damage *= mult;
            }
        }
    }

    @EventHandler
    public void syphon(SkyblockDamageEvent event){
        if(event.getType() != SkyblockDamageEvent.DamageType.PlayerToEntity)
            return;
        if(event.getPlayer().getItemInHand() != null && event.getPlayer().getItemInHand().getItemMeta() != null){
            if(event.getPlayer().getItemInHand().getItemMeta().getEnchants().containsKey(SkyblockEnchants.SYPHON)
                    && event.getPlayer().getItemInHand().getItemMeta().getEnchants().get(SkyblockEnchants.SYPHON) != 0) {
                double crit = Main.playercdcalc(event.getPlayer());
                if(crit > 1000)
                    crit = 1000;
                crit /= 100;

                double baseMult = 0.001 * event.getPlayer().getItemInHand().getItemMeta().getEnchants().get(SkyblockEnchants.SYPHON) + 0.001;
                baseMult *= crit;
                baseMult += 1;
                int h =(int) (baseMult * Main.playerhealthcalc(event.getPlayer()));
                h += event.getPlayer().currhealth;
                if(h > Main.playerhealthcalc(event.getPlayer()))
                    h =(int) Main.playerhealthcalc(event.getPlayer());
                event.getPlayer().setHealth(h, HealthChangeReason.Ability);
                Main.updatebar(event.getPlayer());
            }
        }
    }
}


