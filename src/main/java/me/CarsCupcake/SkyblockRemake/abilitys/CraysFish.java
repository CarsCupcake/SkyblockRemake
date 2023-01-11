package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Ferocity;
import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.AdditionalManaCosts;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.SkyblockRemakeEvents;
import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class CraysFish implements AbilityManager<PlayerInteractEvent> {
    private static final String itemID = "AXE_OF_THE_SHREDDED";
    @Override
    public boolean executeAbility(PlayerInteractEvent event) {

        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());

        thrower(player);
        return false;
    }

    private void thrower(final SkyblockPlayer p) {
        Location throwLoc = p.getLocation().add(0, 1.2, 0);
        Vector throwVec = p.getLocation().add(p.getLocation().getDirection().multiply(10)).toVector().subtract(p.getLocation().toVector()).normalize().multiply(1.2D);

        ArmorStand armorStand =  p.getWorld().spawn(throwLoc, ArmorStand.class, stand ->{

            stand.getEquipment().setItemInMainHand(new ItemStack(Material.TROPICAL_FISH));
            stand.setInvulnerable(true);
            stand.setInvisible(true);
        });
        final Vector[] previousVector = {throwVec};
        Collection<Entity> damaged = new ArrayList<>();
        new BukkitRunnable() {
            private int run = -1;
            private SkyblockPlayer player = p;

            @SuppressWarnings("deprecation")
            @Override
            public void run() {
                run++;
                if(run > 100) {
                    cancel();
                    return;
                }
                if(!armorStand.getLocation().getBlock().getType().isTransparent() || armorStand.isOnGround()) {
                    armorStand.remove();
                    cancel();
                    return;
                }

                double xPos = armorStand.getRightArmPose().getX();
                armorStand.setRightArmPose(new EulerAngle(xPos + 0.6D, 0.0D, 0.0D));

                Vector newVector = new Vector(throwVec.getX(), previousVector[0].getY() - 0.03D, throwVec.getZ());
                previousVector[0] = newVector;
                armorStand.setVelocity(newVector);

                for(Entity e : armorStand.getNearbyEntities(1, 1, 1)) {
                    if(e instanceof LivingEntity entity&& e.getType() != EntityType.ARMOR_STAND && !(e instanceof Player)) {
                        if(damaged.contains(e)) continue;
                        damaged.add(e);
                        Calculator calculator = new Calculator();
                        calculator.playerToEntityDamage(entity, player);
                        calculator.damageEntity(entity, player);
                        calculator.showDamageTag(entity);
                    }






                }
            }



        }.runTaskTimer(Main.getMain(),0, 2);
    }







}
