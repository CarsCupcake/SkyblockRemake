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

public class SugarRush extends CustomEnchantment implements Listener {
    public SugarRush(){
        super(new NamespacedKey(Main.getMain(), "sugar_rush"));
    }
    @NotNull
    @Override
    public String getName() {
        return "Sugar Rush";
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @EventHandler
    public void getStat(GetStatFromItemEvent event){
        if(event.getStat() != Stats.Speed) return;
        if(!ItemHandler.hasEnchantment(this, event.getItem())) return;
        event.addValue(ItemHandler.getEnchantmentLevel(this, event.getItem()) * 2);
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return new ItemType[]{ItemType.Boots};
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Grants §f+%spd% ✧ Speed§7.").addPlaceholder("%spd%", (player, itemStack) -> "" + (ItemHandler.getEnchantmentLevel(this, itemStack) * 2));
    }
}
