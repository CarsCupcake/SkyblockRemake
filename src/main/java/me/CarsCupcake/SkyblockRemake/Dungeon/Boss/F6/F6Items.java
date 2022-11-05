package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import org.bukkit.Material;

public class F6Items {
    public F6Items(){
        ItemManager manager = acientRose();
        me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.put(manager.itemID, manager);

    }
    private ItemManager acientRose(){
        ItemManager manager = new ItemManager("Ancient Rose", "GOLEM_POPPY", ItemType.Non, null, null, null, null, 0, 0 ,0 ,0 , Material.POPPY, ItemRarity.RARE);
        return manager;
    }

    public enum Items{

        AncientRose("GOLEM_POPPY");

    private final String id;
    Items(String id) {
        this.id = id;
    }
    public ItemManager getItem(){
        return me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.get(id);
    }
    public String getId(){
        return id;
    }
}
}
