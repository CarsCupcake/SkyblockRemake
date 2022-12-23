package me.CarsCupcake.SkyblockRemake.Enchantments.NormalEnchants;

import me.CarsCupcake.SkyblockRemake.API.ItemEvents.GetStatFromItemEvent;
import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.DamagePrepairEvent;
import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Stats;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Vicious extends Enchantment implements Listener {
    public Vicious() {
        super(new NamespacedKey(Main.getMain(), "Vicious"));
    }

    @NotNull
    @Override
    public String getName() {
        return "Vicious";
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getStartLevel() {
        return 3;
    }

    @NotNull
    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.BOW;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(@NotNull Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(@NotNull ItemStack itemStack) {
        return true;
    }


    @EventHandler
    public void onStatsGain(GetStatFromItemEvent event){
        if(event.getStat() == Stats.Ferocity){
            if(ItemHandler.hasEnchantment(SkyblockEnchants.VICIOUS, event.getItem()))
                event.setValue(event.getValue() + event.getItem().getItemMeta().getEnchants().get(SkyblockEnchants.VICIOUS));
        }
    }

}
