package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.DamagePrepairEvent;
import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Slayer.Enderman.EndermanSlayerItems;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Enderman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class KatanaDamagingAbilitys implements Listener {
    @EventHandler
    public void damage(DamagePrepairEvent event){
        if(!(event.getEntity() instanceof Enderman))
            return;
        ItemStack item = event.getPlayer().getItemInHand();
        if(item == null || item.getItemMeta() == null || item.getItemMeta().getPersistentDataContainer() == null)
            return;
        String id = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING);
        if(id.equals(EndermanSlayerItems.VoidwalkerKatana().itemID)){
            event.addPreMultiplier(1.5);
        }

        if(id.equals(EndermanSlayerItems.VoidedgeKatana().itemID)){
            event.addPreMultiplier(2.5);
        }

        if(id.equals(EndermanSlayerItems.VorpalKatana().itemID)){
            event.addPreMultiplier(3.5);
        }


        if(id.equals(EndermanSlayerItems.AtomsplitKatana().itemID)){
            event.addPreMultiplier(4.5);
        }
    }

    @EventHandler
    public void damage(SkyblockDamageEvent event){
        if(event.getType() != SkyblockDamageEvent.DamageType.EntityToPlayer)
            return;
        if(!(event.getEntity() instanceof Enderman))
            return;

        ItemStack item = event.getPlayer().getItemInHand();
        if(item == null || item.getItemMeta() == null || item.getItemMeta().getPersistentDataContainer() == null)
            return;
        String id = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING);
        if(id.equals(EndermanSlayerItems.VoidwalkerKatana().itemID)){
            event.getCalculator().damage *= 0.97;
        }

        if(id.equals(EndermanSlayerItems.VoidedgeKatana().itemID)){
            event.getCalculator().damage *= 0.94;
        }

        if(id.equals(EndermanSlayerItems.VorpalKatana().itemID)){
            event.getCalculator().damage *= 0.91;
        }

        if(id.equals(EndermanSlayerItems.AtomsplitKatana().itemID)){
            event.getCalculator().damage *= 0.88;
        }
    }
}
