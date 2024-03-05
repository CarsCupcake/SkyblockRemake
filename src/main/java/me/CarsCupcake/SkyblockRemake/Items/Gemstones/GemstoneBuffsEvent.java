package me.CarsCupcake.SkyblockRemake.Items.Gemstones;

import me.CarsCupcake.SkyblockRemake.API.ItemEvents.GetStatFromItemEvent;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GemstoneBuffsEvent implements Listener {
    @EventHandler
    public void onGemstoneBuff(GetStatFromItemEvent event) {
        for (GemstoneSlot s : GemstoneSlot.getCurrGemstones(ItemHandler.getItemManager(event.getItem()), event.getItem().getItemMeta().getPersistentDataContainer())) {
            if (s.currGem != null) {
                if (s.currGem.getStat() == event.getStat())
                    event.addValue(s.currGem.getDoubleStatBoost(ItemHandler.getItemManager(event.getItem()).getRarity(event.getItem(), event.getPlayer())));
            }
        }
    }
}
