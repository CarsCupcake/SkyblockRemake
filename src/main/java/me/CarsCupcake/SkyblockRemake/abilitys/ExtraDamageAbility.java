package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.Items.Ability;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;


public abstract class ExtraDamageAbility implements Listener {
    public abstract void extraDamageEvent(SkyblockDamageEvent event);

    @EventHandler
    public final void onSkyblockDamage(SkyblockDamageEvent event){
        SkyblockPlayer player = event.getPlayer();
        ItemStack item = player.getItemInHand();
        if(item != null && item.getItemMeta() != null) {
            ItemManager manager = Items.SkyblockItems.get(item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING));
            if(manager == null)
                return;
            for (Ability ability : manager.getAbilities())
                if(ability.getAbilityManager() instanceof ExtraDamageAbility a)
                    a.extraDamageEvent(event);
        }
    }

}
