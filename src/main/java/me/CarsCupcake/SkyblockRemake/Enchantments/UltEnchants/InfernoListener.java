package me.CarsCupcake.SkyblockRemake.Enchantments.UltEnchants;

import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class InfernoListener implements Listener {
    private static final HashMap<SkyblockPlayer, Integer> hits = new HashMap<>();
    private static final HashMap<LivingEntity, BukkitRunnable> infernos = new HashMap<>();
    @EventHandler(priority = EventPriority.LOWEST)
    public void onHit(SkyblockDamageEvent event){
        if(event.isCancelled() || event.getType() != SkyblockDamageEvent.DamageType.PlayerToEntity)
            return;
        if(event.getPlayer().getItemInHand() != null && event.getPlayer().getItemInHand().getItemMeta() != null && event.getPlayer().getItemInHand().getItemMeta().getEnchants().containsKey(SkyblockEnchants.INFERNO)) {
            int hits;



            hits = InfernoListener.hits.getOrDefault(event.getPlayer(), 0) + 1;
            if (hits >= 10) {
                final double lastDamage = event.getCalculator().damage;
                final LivingEntity entity = event.getEntity();
                final int enchantLevel = event.getPlayer().getItemInHand().getItemMeta().getEnchantLevel(SkyblockEnchants.INFERNO);
                InfernoListener.hits.remove(event.getPlayer());
                final AttributeInstance attributeSpeed = entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
                InfernoListener.infernos.put(event.getEntity(), new BukkitRunnable() {
                    private int i = 0;
                    private double y = 0;
                    private double beforeSpeed = 0;
                    private float yaw = entity.getLocation().getYaw();

                    @Override
                    public void run() {
                        //2.5 rotations
                        if(i >= 20*5){
                            cancel();
                            Calculator calculator = new Calculator();
                            double mult = 1 + (0.25 * enchantLevel);
                            calculator.damage = (int)(lastDamage * mult);
                            calculator.damageEntity(entity, event.getPlayer());
                            calculator.showDamageTag(entity);
                            attributeSpeed.setBaseValue(beforeSpeed);

                        }else
                        if(attributeSpeed.getBaseValue() != 0) {
                            beforeSpeed = attributeSpeed.getBaseValue();
                            attributeSpeed.setBaseValue(0);
                        }

                        i++;
                        y += 0.02;
                        yaw += 9;
                        Location location1 = event.getEntity().getLocation();
                        location1.setYaw( yaw);
                        location1.setY(location1.getY() + y);

                        Location location2 = event.getEntity().getLocation();
                        location2.setY(location2.getY() + y);
                        location2.setYaw( yaw + 180);

                        Location partLoc = location1.clone();
                        Vector vector = location1.getDirection().clone();
                        vector.multiply(1);
                        partLoc = partLoc.add(vector);
                        partLoc.getWorld().spawnParticle(Particle.FLAME, partLoc, 0, 0, 0, 1, 0, null);
                        partLoc.getWorld().spawnParticle(Particle.DRIP_LAVA, partLoc, 0, 0, 0, 1, 0, null);

                        partLoc = location2.clone();
                        vector = location2.getDirection().clone();
                        vector.multiply(1);
                        partLoc = partLoc.add(vector);
                        partLoc.getWorld().spawnParticle(Particle.FLAME, partLoc, 0, 0, 0, 1, 0, null);
                        partLoc.getWorld().spawnParticle(Particle.DRIP_LAVA, partLoc, 0, 0, 0, 1, 0, null);

                    }
                    @Override
                    public synchronized void cancel() throws IllegalStateException {
                        Bukkit.getScheduler().cancelTask(this.getTaskId());
                        InfernoListener.infernos.remove(entity);
                    }
                });
                InfernoListener.infernos.get(event.getEntity()).runTaskTimer(Main.getMain(), 0,1);

            } else
                InfernoListener.hits.put(event.getPlayer(), hits);

        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event){
        if(InfernoListener.infernos.containsKey(event.getEntity()))
            InfernoListener.infernos.get(event.getEntity()).cancel();
    }
}
