package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.utils.Assert;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;

public interface BlockPlaceAbility {
    /**
     * Do something if the minion was placed
     *
     * @param event is the event
     * @return if the event should be cancled
     */
    boolean place(BlockPlaceEvent event);

    HashMap<String, BlockPlaceAbility> abilitys = new HashMap<>();

    static void addEvent(ItemManager manager, BlockPlaceAbility event) {
        Assert.allNotNull("No nulls allowed!", manager, event);
        Assert.state(manager.material.isBlock(), "Material is not a block!");
        Assert.isTrue(!abilitys.containsKey(manager.itemID), "An item with the id " + manager.itemID + " is already registerd");

        abilitys.put(manager.itemID, event);
    }


    class Listener implements org.bukkit.event.Listener {
        @EventHandler(ignoreCancelled = true)
        public void onEvent(BlockPlaceEvent event) {
            if (event.getItemInHand() != null && event.getItemInHand().hasItemMeta()) {
                String id = ItemHandler.getPDC("id", event.getItemInHand(), PersistentDataType.STRING);
                if (id == null) return;
                if (abilitys.containsKey(id)) if (abilitys.get(id).place(event)) event.setCancelled(true);
            }
        }
    }
}
