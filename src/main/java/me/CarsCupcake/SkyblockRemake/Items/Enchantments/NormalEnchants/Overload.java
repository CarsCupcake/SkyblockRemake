package me.CarsCupcake.SkyblockRemake.Items.Enchantments.NormalEnchants;

import me.CarsCupcake.SkyblockRemake.API.ItemEvents.GetStatFromItemEvent;
import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.DamagePrepairEvent;
import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Overload extends CustomEnchantment implements Listener {
    public Overload() {
        super(new NamespacedKey(Main.getMain(), "Overload"));
    }

    @NotNull
    @Override
    public String getName() {
        return "Overload";
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return new ItemType[]{ItemType.Bow};
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Increases §9☠ Crit Damage §7by", "§a%level%% §7and §9☣ Crit Chance §7by", "§a%level%%§7. Having a Critical chance",
                "§7above §9100% §7grants a chance", "§7to perform a Mega Critical Hit", "§7dealing §9%level%0%§7 extra damage.")
                .addPlaceholder("%level%", (player, itemStack) -> String.valueOf(ItemHandler.getEnchantmentLevel(this, itemStack)));
    }

    @EventHandler
    public void onHit(DamagePrepairEvent event) {
        if (event.getCalculator().getType() == SkyblockDamageEvent.DamageType.PlayerToEntity && event.getCalculator().getProjectile() != null) {
            if (ItemHandler.hasEnchantment(SkyblockEnchants.OVERLOAD, event.getCalculator().getProjectile())) {
                double cc = Main.getPlayerStat(event.getPlayer(), Stats.CritChance);
                if (cc > 100) {
                    Random r = new Random();
                    if (r.nextDouble() <= cc / 100) {
                        event.getCalculator().setOverload(true);
                        event.addPreMultiplier((double) ItemHandler.getEnchantmentLevel(SkyblockEnchants.OVERLOAD, event.getCalculator().getProjectile()) / 10);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onStatsGain(GetStatFromItemEvent event) {
        if (event.getStat() == Stats.CritDamage || event.getStat() == Stats.CritChance) {
            if (ItemHandler.hasEnchantment(SkyblockEnchants.OVERLOAD, event.getItem()))
                event.setValue(event.getValue() + event.getItem().getItemMeta().getEnchants().get(SkyblockEnchants.OVERLOAD));
        }
    }

}
