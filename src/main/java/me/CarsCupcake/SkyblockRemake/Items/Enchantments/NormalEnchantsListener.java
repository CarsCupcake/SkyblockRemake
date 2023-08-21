package me.CarsCupcake.SkyblockRemake.Items.Enchantments;

import me.CarsCupcake.SkyblockRemake.API.HealthChangeReason;
import me.CarsCupcake.SkyblockRemake.API.ItemEvents.GetStatFromItemEvent;
import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.DamagePrepairEvent;
import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.SkyblockDamagePlayerToEntityExecuteEvent;
import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.BaseEnchants.BaneOfArthropods;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.BaseEnchants.Sharpness;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class NormalEnchantsListener implements Listener {
    private final List<Class<? extends LivingEntity>> banseOfAtropods = List.of(Spider.class, CaveSpider.class, Silverfish.class);
    private final List<Class<? extends LivingEntity>> cubism = List.of(Slime.class, MagmaCube.class, Creeper.class);
    private final List<Class<? extends LivingEntity>> enderSlayer = List.of(Enderman.class, EnderDragon.class);
    @EventHandler
    public void sharpness(DamagePrepairEvent event) {
        if (event.getCalculator().getType() != SkyblockDamageEvent.DamageType.PlayerToEntity)
            return;
        int level = ItemHandler.getEnchantmentLevel( SkyblockEnchants.SHARPNESS, event.getPlayer().getEquipment().getItemInMainHand());
        if (level > 0)
            event.addPreMultiplier(Sharpness.getAmount(level));
        level = ItemHandler.getEnchantmentLevel( SkyblockEnchants.BANE_OF_ARTHROPODS, event.getPlayer().getEquipment().getItemInMainHand());
        if (level > 0 && banseOfAtropods.contains(event.getEntity().getClass()))
            event.addPreMultiplier(BaneOfArthropods.getAmount(level));
        level = ItemHandler.getEnchantmentLevel( SkyblockEnchants.CUBISM, event.getPlayer().getEquipment().getItemInMainHand());
        if (level > 0 && cubism.contains(event.getEntity().getClass()))
            event.addPreMultiplier(SkyblockEnchants.CUBISM.getPers(level));
        level = ItemHandler.getEnchantmentLevel( SkyblockEnchants.DRAGON_HUNTER, event.getPlayer().getEquipment().getItemInMainHand());
        if (level > 0 && event.getEntity().getType() == EntityType.ENDER_DRAGON)
            event.addPreMultiplier(8 * ItemHandler.getEnchantmentLevel(SkyblockEnchants.DRAGON_HUNTER, event.getPlayer().getEquipment().getItemInMainHand()));
        level = ItemHandler.getEnchantmentLevel( SkyblockEnchants.ENDER_SLAYER, event.getPlayer().getEquipment().getItemInMainHand());
        if (level > 0 && enderSlayer.contains(event.getEntity().getClass()))
            event.addPreMultiplier(SkyblockEnchants.ENDER_SLAYER.getStatBoost(level));
        SkyblockEntity entity = SkyblockEntity.livingEntity.getSbEntity(event.getEntity());
        double persantage = Tools.round(((double) entity.getHealth() / (double) entity.getMaxHealth()) * 100, 0);
        level = ItemHandler.getEnchantmentLevel( SkyblockEnchants.EXECUTE, event.getPlayer().getEquipment().getItemInMainHand());
        if(level > 0) {
            double per = 100 - persantage;
            event.addPreMultiplier(per * SkyblockEnchants.EXECUTE.getPers(level));
        }
        level = ItemHandler.getEnchantmentLevel( SkyblockEnchants.PROSECUTE, event.getPlayer().getEquipment().getItemInMainHand());
        if(level > 0) {
            event.addPreMultiplier(persantage * SkyblockEnchants.PROSECUTE.getPers(level));
        }
    }

    @EventHandler
    public void ev(SkyblockDamagePlayerToEntityExecuteEvent event) {
        int level = ItemHandler.getEnchantmentLevel( SkyblockEnchants.CLEAVE, event.getPlayer().getEquipment().getItemInMainHand());
        if(level > 0 && !event.getCalculator().getTags().contains("cleave")) {
            for (Entity e : event.getEntity().getNearbyEntities(SkyblockEnchants.CLEAVE.getBlockRange(level), SkyblockEnchants.CLEAVE.getBlockRange(level), SkyblockEnchants.CLEAVE.getBlockRange(level))) {
                if (!(e instanceof LivingEntity entity) || e instanceof Player) continue;
                Calculator calculator = new Calculator();
                calculator.damage = event.getCalculator().damage * ((double) SkyblockEnchants.CLEAVE.getPersentage(level) / 100);
                calculator.getTags().add("cleave");
                calculator.damageEntity(entity, event.getPlayer());
            }
        }
    }

    @EventHandler
    public void syphon(SkyblockDamageEvent event) {
        if (event.getType() != SkyblockDamageEvent.DamageType.PlayerToEntity)
            return;
        if (event.getPlayer().getItemInHand() != null && event.getPlayer().getItemInHand().getItemMeta() != null) {
            if (event.getPlayer().getItemInHand().getItemMeta().getEnchants().containsKey(SkyblockEnchants.SYPHON)
                    && event.getPlayer().getItemInHand().getItemMeta().getEnchants().get(SkyblockEnchants.SYPHON) != 0) {
                double crit = Main.getPlayerStat(event.getPlayer(), Stats.CritDamage);
                double health = Main.getPlayerStat(event.getPlayer(), Stats.Health);
                if (crit > 1000)
                    crit = 1000;
                crit /= 100;

                double baseMult = 0.001 * event.getPlayer().getItemInHand().getItemMeta().getEnchants().get(SkyblockEnchants.SYPHON) + 0.001;
                baseMult *= crit;
                baseMult += 1;
                int h = (int) (baseMult * health);
                h += event.getPlayer().currhealth;
                if (h > health)
                    h = (int) health;
                event.getPlayer().setHealth(h, HealthChangeReason.Ability);
                Main.updatebar(event.getPlayer());
            }
        }
    }

    @EventHandler
    public void onStat(GetStatFromItemEvent event) {
        if (event.getStat() == Stats.CritDamage) {
            int level = ItemHandler.getEnchantmentLevel(SkyblockEnchants.CRITICAL, event.getItem());
            if (level > 0) event.addValue(SkyblockEnchants.CRITICAL.getBoost(level));
        }
        if (event.getStat() == Stats.MagicFind) {
            int level = ItemHandler.getEnchantmentLevel(SkyblockEnchants.DIVINE_GIFT, event.getItem());
            if (level > 0) event.addValue(level * 2);
        }
    }
}


