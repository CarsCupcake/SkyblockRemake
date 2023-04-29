package me.CarsCupcake.SkyblockRemake.Items.minions;

import me.CarsCupcake.SkyblockRemake.Entities.MinionEntity;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractCombatMinionData implements IMinionData {
    protected abstract MinionEntity getEntity();

    @Override
    public ItemStack getItemInHand() {
        return new ItemStack(Material.WOODEN_SWORD);
    }

}
