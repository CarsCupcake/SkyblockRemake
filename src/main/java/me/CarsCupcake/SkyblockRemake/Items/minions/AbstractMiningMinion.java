package me.CarsCupcake.SkyblockRemake.Items.minions;

import me.CarsCupcake.SkyblockRemake.utils.Assert;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractMiningMinion implements IMinion{
    public abstract Material representiveBlock();
    public static void startMinion(AbstractMiningMinion minion, int level, Location location){
        Assert.isTrue(level <= minion.getLevels(), "Minion level out of range!");

    }

    @Override
    public ItemStack getItemInHand() {
        return new ItemStack(Material.WOODEN_PICKAXE);
    }
}
