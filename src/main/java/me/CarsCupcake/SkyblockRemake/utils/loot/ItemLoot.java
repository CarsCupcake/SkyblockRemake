package me.CarsCupcake.SkyblockRemake.utils.loot;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.Random;

/**
 *
 * @param manager the item that will get rolled
 * @param min the minimum amount (inclusive)
 * @param max the maximum amount (exclusive)
 */
public record ItemLoot(ItemManager manager, int min, int max) implements Loot{
    public ItemLoot(String material) {
        this(Items.SkyblockItems.get(material), 1);
    }
    public ItemLoot(String material, int count) {
        this(Items.SkyblockItems.get(material), count);
    }
    public ItemLoot(String material, int min, int max) {
        this(Objects.requireNonNull(Items.SkyblockItems.get(material)), min, max);
    }
    public ItemLoot(Material material) {
        this(ItemManager.getPrimitive(material), 1);
    }
    public ItemLoot(Material material, int count) {
        this(ItemManager.getPrimitive(material), count);
    }
    public ItemLoot(Material material, int min, int max) {
        this(ItemManager.getPrimitive(material), min, max);
    }
    public ItemLoot(ItemManager manager) {
        this(manager, 1);
    }
    public ItemLoot(ItemManager manager, int amount) {
        this(Objects.requireNonNull(manager), amount, amount + 1);
    }
    @Override
    public void consume(SkyblockPlayer killer, Location dropLocation, boolean toPlayer) {
        int amount = ((max - min == 0) ? 0 : new Random().nextInt(max - min)) + min;
        if (amount == 0) return;
        ItemStack item = manager.createNewItemStack();
        if (item.getType() == Material.AIR) return;
        item.setAmount(amount);
        Main.itemUpdater(item, killer);
        if (item.getType() == Material.AIR) return;
        if(toPlayer && killer != null) {
            killer.addItem(item, true);
        } else dropLocation.getWorld().dropItemNaturally(dropLocation, item);
    }

    @Override
    @SuppressWarnings("deprecation")
    public String name() {
        return manager.getRarity().getPrefix() + manager.name;
    }

    @Override
    public String toString() {
        return name() + ", " + min + ", " + max;
    }
}
