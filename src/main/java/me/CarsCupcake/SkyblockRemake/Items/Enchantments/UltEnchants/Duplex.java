package me.CarsCupcake.SkyblockRemake.Items.Enchantments.UltEnchants;

import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.BowShootEvent;
import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.SkyblockDamagePlayerToEntityExecuteEvent;
import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.UltimateEnchant;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Duplex extends UltimateEnchant implements Listener {
    public Duplex() {
        super(new NamespacedKey(Main.getMain(), "Duplex"));
    }

    @NotNull
    @Override
    public String getName() {
        return "Duplex";
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    private final static HashMap<SkyblockPlayer, Double> lastDuplexDamage = new HashMap<>();

    @EventHandler
    public void onProjectileLounch(BowShootEvent event) {
        if (!ItemHandler.hasEnchantment(SkyblockEnchants.DUPLEX, event.getBow())) return;
        SkyblockPlayer player = event.getPlayer();
        new BukkitRunnable() {
            public void run() {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Projectile arrow_entity = player.launchProjectile(Arrow.class);
                        int stre = (int) Main.getPlayerStat(player, Stats.Strength);
                        double cd = Main.getPlayerStat(player, Stats.CritDamage);
                        int weapondmg = 0;
                        int ferocity = (int) Main.getPlayerStat(player, Stats.Ferocity);


                        arrow_entity.addScoreboardTag("cd:" + cd);
                        arrow_entity.addScoreboardTag("cc:" + 0);
                        arrow_entity.addScoreboardTag("strength:" + stre);
                        arrow_entity.addScoreboardTag("ferocity:" + ferocity);
                        arrow_entity.addScoreboardTag("dmg:" + weapondmg);

                        arrow_entity.addScoreboardTag("arrow_duplex");
                        arrow_entity.addScoreboardTag("custom_arrow");
                    }
                }.runTaskLater(Main.getMain(), 2);
            }
        }.runTaskLater(Main.getMain(), 1);
    }

    @EventHandler
    public void onSkyblockCalculatoProc(SkyblockDamageEvent event) {
        if (event.getType() != SkyblockDamageEvent.DamageType.PlayerToEntity) return;
        if (event.getCalculator().getProjectile() == null) return;
        if (!event.getCalculator().getProjectile().getScoreboardTags().contains("arrow_duplex")) return;
        event.setCancelled(true);

        if (!lastDuplexDamage.containsKey(event.getPlayer())) return;

        Calculator c = new Calculator();
        c.damage = lastDuplexDamage.get(event.getPlayer());
        c.damageEntity(event.getEntity(), event.getPlayer());

        c.showDamageTag(event.getEntity());
    }

    @EventHandler
    public void onDamageByDuplex(SkyblockDamagePlayerToEntityExecuteEvent event) {
        if (event.getCalculator().getProjectile() == null) return;
        if (!ItemHandler.hasEnchantment(SkyblockEnchants.DUPLEX, event.getCalculator().getProjectile())) return;

        lastDuplexDamage.put(event.getPlayer(), Tools.round((((ItemHandler.getEnchantmentLevel(SkyblockEnchants.DUPLEX, event.getCalculator().getProjectile()) * 4d)) / 100d) * event.getCalculator().damage, 0));
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return ItemType.Type.Bow.getTypeList().toArray(new ItemType[0]);
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Shoot a second arrow dealing §c%dmg%% §7of", "§7the first arrow's damage."
        ,"§7Targets hit take §c1.%level%x §7fire", "§7damage for §a60s§7.").addPlaceholder("%dmg%", (player, itemStack) -> String.valueOf(ItemHandler.getEnchantmentLevel(this,itemStack)*4))
                .addPlaceholder("%level%", (player, itemStack) -> String.valueOf(ItemHandler.getEnchantmentLevel(this,itemStack)));
    }
}
