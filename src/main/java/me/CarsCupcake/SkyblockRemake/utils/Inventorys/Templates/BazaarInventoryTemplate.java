package me.CarsCupcake.SkyblockRemake.utils.Inventorys.Templates;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.InventoryBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.InventoryTemplate;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.Items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public class BazaarInventoryTemplate implements InventoryTemplate {
    private final String name;
    private final InventoryBuilder builder;
    public BazaarInventoryTemplate(String name, Material color, int i, SkyblockPlayer player){
        this.name = name;
        int orders = 0;

        builder = new InventoryBuilder(6,name).fill(new ItemBuilder(color).setName(" ").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build(),1,8)
                .setItems(new ItemBuilder(color).setName(" ").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build(), 10, 19, 28, 37, 46, 17, 26, 35,44, 53)
                .setItem(new ItemBuilder(Material.CHEST).setName("§aSell Inventory Now").build(), 47)
                .setItem(new ItemBuilder(Material.BOOK)
                        .setName("§aManage Oders")
                        .addLoreRow("§7Orders: §e" + Main.bazaarFile.get().getInt(player.getUniqueId() + ".orders"))
                        .addLoreRow(" ")
                        .addLoreRow("§eClick to manage!")
                        .build(),50);

    }
    @Override
    @Range(from = 1,to = 6)
    public int getRows() {
        return 6;
    }

    @Override
    @NotNull
    public Inventory getCompiledItems() {
        return builder.build();
    }

    @Override
    @NotNull
    public String getName() {
        return name;
    }
}
