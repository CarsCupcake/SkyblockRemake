package me.CarsCupcake.SkyblockRemake.utils.Inventories;

import me.CarsCupcake.SkyblockRemake.utils.Inventories.Items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public enum TemplateItems {

    EmptySlot(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).setName(" ").build()),
    Close(new ItemBuilder(Material.BARRIER).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).setName("§cClose").build()),
    BackArrow(new ItemBuilder(Material.ARROW).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addLoreRow("§7Click to get back").setName("§aBack").build()),
    NextArrow(new ItemBuilder(Material.ARROW).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addLoreRow("§7Click to get to the next page").setName("§aNext").build());
    private final ItemStack item;
    TemplateItems(ItemStack item) {
        this.item = item;
    }
    public ItemStack getItem(){
        return item;
    }
}
