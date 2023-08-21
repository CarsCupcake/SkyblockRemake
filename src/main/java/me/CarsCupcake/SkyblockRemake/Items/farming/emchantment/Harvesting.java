package me.CarsCupcake.SkyblockRemake.Items.farming.emchantment;

import me.CarsCupcake.SkyblockRemake.API.ItemEvents.GetStatFromItemEvent;
import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class Harvesting extends CustomEnchantment implements Listener {
    public Harvesting() {
        super(new NamespacedKey(Main.getMain(), "harvesting"));
    }

    @NotNull
    @Override
    public String getName() {
        return "Harvesting";
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }
    @EventHandler
    public void onGetFarmingFortune(GetStatFromItemEvent event){
        if(event.getStat() != Stats.FarmingFortune) return;
        if(ItemHandler.hasEnchantment(this, event.getItem()))
            event.addValue(12.5 * ItemHandler.getEnchantmentLevel(this, event.getItem()));
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return new ItemType[]{ItemType.Hoe, ItemType.Axe};
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("Grants §6+%boost% ☘ Farming Fortune§7,", "§7which increases your chance", "§7for multiple crops.")
                .addPlaceholder("%boost%", (player, itemStack) -> (12.5 * ItemHandler.getEnchantmentLevel(this, itemStack)) + "");
    }
}
