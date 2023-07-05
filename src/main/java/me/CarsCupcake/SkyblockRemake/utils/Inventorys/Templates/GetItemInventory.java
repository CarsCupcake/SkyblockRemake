package me.CarsCupcake.SkyblockRemake.utils.Inventorys.Templates;

import me.CarsCupcake.SkyblockRemake.utils.Assert;
import me.CarsCupcake.SkyblockRemake.utils.Consumable;
import me.CarsCupcake.SkyblockRemake.utils.Inventory.GuiTemplate;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.GUI;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.GUIAction;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.InventoryBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.TemplateItems;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class GetItemInventory extends GuiTemplate {
    private final List<Consumable> required;
    private final ItemStack item;
    public GetItemInventory(@NotNull ItemStack item,@Nullable List<Consumable> required){
        Assert.notNull(item, "item is null");
        Assert.state(item.getType() != Material.AIR, "Item cannot be air!");
        this.item = item;
        this.required = (required.isEmpty()) ? null : required;
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return new InventoryBuilder(6, item.getItemMeta().getDisplayName()).fill(TemplateItems.EmptySlot.getItem()).setItem(item, 22).setItem(TemplateItems.Close.getItem(), 49).build();
    }

    @Nullable
    @Override
    public Map<Integer, GUIAction> getSlotActions(@NotNull GUI gui) {
        return Tools.mapOf(List.of(49, 22),
                List.of((type) -> gui.closeInventory(), (type -> {
                    for (Consumable consumable : required)
                        if(!consumable.has(gui.getPlayer())){
                            gui.getPlayer().sendMessage("§cYou do not have the required items!");
                            return;
                        }
                    for (Consumable consumable : required)
                        consumable.consume(gui.getPlayer());
                    gui.closeInventory();
                    gui.getPlayer().addItem(item, false);
                    gui.getPlayer().sendMessage("§aYou collected " + item.getItemMeta().getDisplayName());
                })));
    }
}
