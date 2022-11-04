package me.CarsCupcake.SkyblockRemake.Pets.Abilitys;

import me.CarsCupcake.SkyblockRemake.Items.UpdateFlag;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Pets.PetAbility;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class EndermanAbility3 extends PetAbility {
    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    public static class Placeholder implements UpdateFlag{
        @Override
        public String getReplaceValue(SkyblockPlayer player, ItemStack item) {
            int level = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "level"), PersistentDataType.INTEGER);
            double ability = 0.25*level;
            return (ability%1==0) ? String.format("%.0f", ability) : ability + "";
        }
    }
}
