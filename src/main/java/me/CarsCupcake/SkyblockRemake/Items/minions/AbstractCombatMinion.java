package me.CarsCupcake.SkyblockRemake.Items.minions;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractCombatMinion implements IMinion {
    protected abstract SkyblockEntity getEntity();

    @Override
    public ItemStack getItemInHand() {
        return new ItemStack(Material.WOODEN_SWORD);
    }

}
