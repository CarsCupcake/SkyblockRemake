package me.CarsCupcake.SkyblockRemake.abilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import me.CarsCupcake.SkyblockRemake.API.HealthChangeReason;
import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.Skyblock.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import me.CarsCupcake.SkyblockRemake.SkyblockRemakeEvents;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;

public class AxeOfTheShredded extends ExtraDamageAbility implements AbilityManager<PlayerInteractEvent> {
    private static final String itemID = "AXE_OF_THE_SHREDDED";

    @Override
    public boolean triggerAbility(PlayerInteractEvent event) {
        int multi;
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
        if (!AbilityManager.additionalMana.get(player).containsKey(itemID)) {
            AbilityManager.additionalMana.get(player).put(itemID, new AdditionalManaCosts(player, itemID, 20, 60, -1));

            multi = 1;
        } else {
            switch (AbilityManager.additionalMana.get(player).get(itemID).amount) {
                case 20 -> {
                    AbilityManager.additionalMana.get(player).get(itemID).addAmount(20);
                    multi = 2;
                }
                case 40 -> {
                    AbilityManager.additionalMana.get(player).get(itemID).addAmount(20);
                    multi = 4;
                }
                case 60 -> {
                    AbilityManager.additionalMana.get(player).get(itemID).addAmount(80);
                    multi = 8;
                }
                default -> {
                    AbilityManager.additionalMana.get(player).get(itemID).resetTimer();
                    multi = 16;
                }
            }
        }
        thrower(player, multi);
        return false;
    }

    private void thrower(final SkyblockPlayer p, int multi) {
        Location throwLoc = p.getLocation().add(0, 1.2, 0);
        Vector throwVec = p.getLocation().add(p.getLocation().getDirection().multiply(10)).toVector().subtract(p.getLocation().toVector()).normalize().multiply(1.2D);

        ArmorStand armorStand = p.getWorld().spawn(throwLoc, ArmorStand.class, stand -> {

            stand.getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_AXE));
            stand.setInvulnerable(true);
            stand.setInvisible(true);
        });
        final Vector[] previousVector = {throwVec};
        Collection<Entity> damaged = new ArrayList<>();
        new BukkitRunnable() {
            private int run = -1;
            private final SkyblockPlayer player = p;

            @SuppressWarnings("deprecation")
            @Override
            public void run() {
                run++;
                if (run > 100) {
                    cancel();
                    return;
                }
                if (!armorStand.getLocation().getBlock().getType().isTransparent() || armorStand.isOnGround()) {
                    armorStand.remove();
                    cancel();
                    return;
                }

                double xPos = armorStand.getRightArmPose().getX();
                armorStand.setRightArmPose(new EulerAngle(xPos + 0.6D, 0.0D, 0.0D));

                Vector newVector = new Vector(throwVec.getX(), previousVector[0].getY() - 0.03D, throwVec.getZ());
                previousVector[0] = newVector;
                armorStand.setVelocity(newVector);

                for (Entity e : armorStand.getNearbyEntities(1, 1, 1)) {
                    if (e instanceof LivingEntity entity && e.getType() != EntityType.ARMOR_STAND && !(e instanceof Player)) {
                        if (damaged.contains(e)) continue;
                        damaged.add(e);
                        Calculator calculator = new Calculator();
                        calculator.playerToEntityDamage(entity, player);
                        calculator.damage *= 0.1;
                        calculator.damage *= multi;
                        calculator.damageEntity(entity, player);
                        calculator.showDamageTag(entity);
                    }
                }
            }
        }.runTaskTimer(Main.getMain(), 0, 2);
    }

    @Override
    public void extraDamageEvent(SkyblockDamageEvent event) {
        int newHealth = event.getPlayer().currhealth + 50;
        double health = Main.getPlayerStat(event.getPlayer(), Stats.Health);
        if (newHealth > health)
            event.getPlayer().setHealth(health, HealthChangeReason.Ability);
        else
            event.getPlayer().setHealth(newHealth, HealthChangeReason.Ability);
    }
}
