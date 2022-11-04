package me.CarsCupcake.SkyblockRemake.utils.Inventorys;

import me.CarsCupcake.SkyblockRemake.utils.Inventorys.Items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public enum TemplateItems {

    EmptySlot(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).setName(" ").build()),
    BackArrow(new ItemBuilder(Material.ARROW).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addLoreRow("§7Click to get back").setName("§aBack").build());
    private final ItemStack item;
    TemplateItems(ItemStack item) {
        this.item = item;
    }
    public ItemStack getItem(){
        return item;
    }
}
