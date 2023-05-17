package me.CarsCupcake.SkyblockRemake.Items.Enchantments.UltEnchants;

import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.SkyblockDamagePlayerToEntityExecuteEvent;
import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.UltimateEnchant;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
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
        return false;
    }

    private final static HashMap<SkyblockPlayer, Double> lastDuplexDamage = new HashMap<>();

    @EventHandler
    public void onProjectileLounch(ProjectileLaunchEvent event) {
        if (event.getEntity().getShooter() != null && event.getEntity().getShooter() instanceof Player p) {
            SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(p);
            new BukkitRunnable() {
                public void run() {
                    if (event.getEntity().getScoreboardTags().contains("custom_arrow")) return;

                    if (ItemHandler.hasEnchantment(SkyblockEnchants.DUPLEX, player.getItemInHand())) {
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
                }
            }.runTaskLater(Main.getMain(), 1);
        }
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
}
