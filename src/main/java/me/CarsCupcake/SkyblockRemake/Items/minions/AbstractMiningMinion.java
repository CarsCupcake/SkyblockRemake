package me.CarsCupcake.SkyblockRemake.Items.minions;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractMiningMinion implements IMinion{
    public abstract Material representiveBlock();

    @Override
    public ItemStack getItemInHand() {
        return new ItemStack(Material.WOODEN_PICKAXE);
    }
}
