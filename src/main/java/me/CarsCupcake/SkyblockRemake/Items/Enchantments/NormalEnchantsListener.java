package me.CarsCupcake.SkyblockRemake.Items.Enchantments;

import me.CarsCupcake.SkyblockRemake.API.HealthChangeReason;
import me.CarsCupcake.SkyblockRemake.API.ItemEvents.GetStatFromItemEvent;
import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.DamagePrepairEvent;
import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.SkyblockDamagePlayerToEntityExecuteEvent;
import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.Entities.EntityHandler;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.BaseEnchants.BaneOfArthropods;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.BaseEnchants.FireAspect;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.BaseEnchants.Sharpness;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.NormalEnchants.GiantKiller;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.NormalEnchants.TitanKiller;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.Defensive;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class NormalEnchantsListener implements Listener {
    private final List<Class<? extends LivingEntity>> banseOfAtropods = List.of(Spider.class, CaveSpider.class, Silverfish.class);
    private final List<Class<? extends LivingEntity>> cubism = List.of(Slime.class, MagmaCube.class, Creeper.class);
    private final List<Class<? extends LivingEntity>> enderSlayer = List.of(Enderman.class, EnderDragon.class);
    private final List<Class<? extends LivingEntity>> smite = List.of(Skeleton.class, Zombie.class, ZombieVillager.class, PigZombie.class, Wither.class);

    @EventHandler
    public void sharpness(DamagePrepairEvent event) {
        if (event.getCalculator().getType() != SkyblockDamageEvent.DamageType.PlayerToEntity)
            return;
        int level = ItemHandler.getEnchantmentLevel(SkyblockEnchants.SHARPNESS, event.getPlayer().getEquipment().getItemInMainHand());
        if (level > 0)
            event.addPreMultiplier((double) Sharpness.getAmount(level) / 100);
        level = ItemHandler.getEnchantmentLevel(SkyblockEnchants.BANE_OF_ARTHROPODS, event.getPlayer().getEquipment().getItemInMainHand());
        if (level > 0 && banseOfAtropods.contains(event.getEntity().getClass()))
            event.addPreMultiplier((double) BaneOfArthropods.getAmount(level) / 100);
        level = ItemHandler.getEnchantmentLevel(SkyblockEnchants.CUBISM, event.getPlayer().getEquipment().getItemInMainHand());
        if (level > 0 && cubism.contains(event.getEntity().getClass()))
            event.addPreMultiplier((double) SkyblockEnchants.CUBISM.getPers(level) / 100);
        level = ItemHandler.getEnchantmentLevel(SkyblockEnchants.DRAGON_HUNTER, event.getPlayer().getEquipment().getItemInMainHand());
        if (level > 0 && event.getEntity().getType() == EntityType.ENDER_DRAGON)
            event.addPreMultiplier(((double) (8 * ItemHandler.getEnchantmentLevel(SkyblockEnchants.DRAGON_HUNTER, event.getPlayer().getEquipment().getItemInMainHand())) / 100));
        level = ItemHandler.getEnchantmentLevel(SkyblockEnchants.ENDER_SLAYER, event.getPlayer().getEquipment().getItemInMainHand());
        if (level > 0 && enderSlayer.contains(event.getEntity().getClass()))
            event.addPreMultiplier((double) SkyblockEnchants.ENDER_SLAYER.getStatBoost(level) / 100);
        level = ItemHandler.getEnchantmentLevel(SkyblockEnchants.SMITE, event.getPlayer().getEquipment().getItemInMainHand());
        if (level > 0 && smite.contains(event.getEntity().getClass()))
            event.addPreMultiplier((double) SkyblockEnchants.SMITE.getBoost(level) / 100);
        level = ItemHandler.getEnchantmentLevel(SkyblockEnchants.SMOLDERING, event.getPlayer().getEquipment().getItemInMainHand());
        if (level > 0 && event.getEntity().getClass() == Blaze.class)
            event.addPreMultiplier((double) (level * 3) / 100);
        SkyblockEntity entity = SkyblockEntity.livingEntity.getSbEntity(event.getEntity());
        double persantage = Tools.round(((double) entity.getHealth() / (double) entity.getMaxHealth()) * 100, 0);
        level = ItemHandler.getEnchantmentLevel(SkyblockEnchants.EXECUTE, event.getPlayer().getEquipment().getItemInMainHand());
        if (level > 0) {
            double per = 100 - persantage;
            event.addPreMultiplier((per * SkyblockEnchants.EXECUTE.getPers(level)) / 100);
        }
        level = ItemHandler.getEnchantmentLevel(SkyblockEnchants.PROSECUTE, event.getPlayer().getEquipment().getItemInMainHand());
        if (level > 0) {
            event.addPreMultiplier((persantage * SkyblockEnchants.PROSECUTE.getPers(level)) / 100);
        }
        level = ItemHandler.getEnchantmentLevel(SkyblockEnchants.FIRST_STRIKE, event.getPlayer().getEquipment().getItemInMainHand());
        if (level > 0 && event.getHits() == 0) {
            event.addPreMultiplier((double) (25 * level) / 100);
        }
        level = ItemHandler.getEnchantmentLevel(SkyblockEnchants.TRIPLE_STRIKE, event.getPlayer().getEquipment().getItemInMainHand());
        if (level > 0 && event.getHits() == 0) {
            event.addPreMultiplier((double) (10 * level) / 100);
        }
        level = ItemHandler.getEnchantmentLevel(SkyblockEnchants.GIANT_KILLER, event.getPlayer().getEquipment().getItemInMainHand());
        if (level > 0 && !event.getCalculator().isIgnoreHit()) {
            double p = entity.getHealth() / Main.getPlayerStat(event.getPlayer(), Stats.Health);
            p *= 100;
            if (p > GiantKiller.getTarget(level)) p = GiantKiller.getTarget(level);
            event.addPreMultiplier((GiantKiller.getPers(level) / 100) * p);
        }
        level = ItemHandler.getEnchantmentLevel(SkyblockEnchants.TITAN_KILLER, event.getPlayer().getEquipment().getItemInMainHand());
        if (level > 0 && event instanceof Defensive def && def.getDefense() > 0 && !event.getCalculator().isIgnoreHit()) {
            int defB = (int) (def.getDefense() / 100);
            double boost = TitanKiller.getPers(level) * defB;
            if (boost > TitanKiller.getTarget(level)) boost = TitanKiller.getTarget(level);
            event.addPreMultiplier(boost / 100);
        }
    }

    @EventHandler
    public void ev(SkyblockDamagePlayerToEntityExecuteEvent event) {
        int level = ItemHandler.getEnchantmentLevel(SkyblockEnchants.CLEAVE, event.getPlayer().getEquipment().getItemInMainHand());
        if (level > 0 && !event.getCalculator().getTags().contains("cleave")) {
            for (Entity e : event.getEntity().getNearbyEntities(SkyblockEnchants.CLEAVE.getBlockRange(level), SkyblockEnchants.CLEAVE.getBlockRange(level), SkyblockEnchants.CLEAVE.getBlockRange(level))) {
                if (!(e instanceof LivingEntity entity) || e instanceof Player) continue;
                Calculator calculator = new Calculator();
                calculator.damage = event.getCalculator().damage * ((double) SkyblockEnchants.CLEAVE.getPersentage(level) / 100);
                calculator.getTags().add("cleave");
                calculator.damageEntity(entity, event.getPlayer());
            }
        }
        level = ItemHandler.getEnchantmentLevel(SkyblockEnchants.FIRE_ASPECT, event.getPlayer().getEquipment().getItemInMainHand());
        if (level > 0 && !event.getCalculator().isIgnoreHit()) {
            FireAspect.Fire.fire(SkyblockEntity.livingEntity.getSbEntity(event.getEntity()), level, event.getCalculator().damage, event.getPlayer());
        }
        level = ItemHandler.getEnchantmentLevel(SkyblockEnchants.LIFE_STEAL, event.getPlayer().getEquipment().getItemInMainHand());
        if (level > 0) {
            event.getPlayer().setHealth(event.getPlayer().currhealth + (Main.getPlayerStat(event.getPlayer(), Stats.Health) * (((double) level * 0.5) / 100)), HealthChangeReason.Ability);
        }
        level = ItemHandler.getEnchantmentLevel(SkyblockEnchants.MANA_STEAL, event.getPlayer().getEquipment().getItemInMainHand());
        if (level > 0) {
            double mult = level * 0.0025;
            event.getPlayer().currmana += (int) (Main.getPlayerStat(event.getPlayer(), Stats.Inteligence) * mult);
        }
        level = ItemHandler.getEnchantmentLevel(SkyblockEnchants.THUNDERBOLT, event.getPlayer().getEquipment().getItemInMainHand());
        if (level > 0 && event.getHits() % 3 == 0 && !event.getCalculator().getTags().contains("thunder")) {
            double damage = event.getCalculator().damage * ((double) SkyblockEnchants.THUNDERBOLT.getBoost(level) / 100);
            for (Entity entity : event.getEntity().getNearbyEntities(2, 2, 2)) {
                if (entity instanceof LivingEntity livingEntity && !(entity instanceof Player)) {
                    Calculator calculator = new Calculator();
                    calculator.getTags().add("thunder");
                    calculator.damage = damage;
                    calculator.setIgnoreHit(true);
                    calculator.damageEntity(livingEntity, event.getPlayer());
                    calculator.showThunderDamageTag(entity);
                    entity.getWorld().strikeLightningEffect(entity.getLocation());
                }
            }
        }
        level = ItemHandler.getEnchantmentLevel(SkyblockEnchants.THUNDERLORD, event.getPlayer().getEquipment().getItemInMainHand());
        if (level > 0 && event.getHits() % 3 == 0 && !event.getCalculator().getTags().contains("thunder")) {
            double damage = event.getCalculator().damage * ((double) SkyblockEnchants.THUNDERLORD.getBoost(level) / 100);
            Calculator calculator = new Calculator();
            calculator.getTags().add("thunder");
            calculator.damage = damage;
            calculator.setIgnoreHit(true);
            calculator.damageEntity(event.getEntity(), event.getPlayer());
            calculator.showThunderDamageTag(event.getEntity());
            event.getEntity().getWorld().strikeLightningEffect(event.getEntity().getLocation());
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


