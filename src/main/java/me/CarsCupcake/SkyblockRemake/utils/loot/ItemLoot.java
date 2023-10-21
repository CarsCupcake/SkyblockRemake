package me.CarsCupcake.SkyblockRemake.utils.loot;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public record ItemLoot(ItemManager manager, int min, int max) implements Loot{
    @Override
    public void consume(SkyblockPlayer killer, Location dropLocation, boolean toPlayer) {
        int amount = ((max - min == 0) ? 0 : new Random().nextInt(max - min)) + min;
        ItemStack item = manager.createNewItemStack();
        if (item.getType() == Material.AIR) return;
        item.setAmount(amount);
        Main.item_updater(item, killer);
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
