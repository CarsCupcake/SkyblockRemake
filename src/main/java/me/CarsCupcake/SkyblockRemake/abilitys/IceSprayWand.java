package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.DamagePrepairEvent;
import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Main;
import net.minecraft.core.Vector3f;
import net.minecraft.nbt.NBTTagList;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftArmorStand;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IceSprayWand implements AbilityManager<PlayerInteractEvent>, Listener {
    private static final HashMap<LivingEntity, BukkitRunnable> freezedEntitys = new HashMap<>();

    @Override
    public boolean executeAbility(PlayerInteractEvent event) {
        Location location = event.getPlayer().getEyeLocation();
        Vector dir = event.getPlayer().getLocation().getDirection();
        event.getPlayer().playSound(location, Sound.ENTITY_ENDER_DRAGON_AMBIENT, 1,1);

        List<LivingEntity> entities = new ArrayList<>();

        for(int i = 0; i < 7; i++){
            Vector newDir = dir.clone().multiply(1);
            location = location.add(newDir);

            for(Entity e : location.getWorld().getNearbyEntities(location,1,1,1)){
                if(e instanceof LivingEntity && !(e instanceof Player) && !(e instanceof ArmorStand) && !entities.contains(e) && !freezedEntitys.containsKey(e))
                    entities.add((LivingEntity) e);

            }
        }
        for(LivingEntity entity : entities)
            freeze(entity);


        return false;
    }

    private void freeze(final LivingEntity entity){
        ArmorStand stand = entity.getWorld().spawn(entity.getEyeLocation().add(-0.2,-1.5,0.2), ArmorStand.class, s ->{
            s.setGravity(false);
            s.setMarker(true);
            s.setInvisible(true);
            s.getEquipment().setItemInMainHand(new ItemStack(Material.PACKED_ICE));
            s.setRightArmPose(fromNMS(new Vector3f(-584.84375f - 360, 11.90625f, -316.8125f)));

        });
        ArmorStand stand1 = entity.getWorld().spawn(entity.getEyeLocation().add(-0.2,-1.3,0.2), ArmorStand.class, y ->{
            y.setGravity(false);
            y.setMarker(true);
            y.setInvisible(true);
            y.getEquipment().setItemInMainHand(new ItemStack(Material.PACKED_ICE));
            y.setRightArmPose(fromNMS(new Vector3f(-585.15625f - 360, 11.6875f, -317.03125f)));
        });
        ArmorStand stand2 = entity.getWorld().spawn(entity.getEyeLocation().add(0,-1.5,0.2), ArmorStand.class, s2 ->{
            s2.setGravity(false);
            s2.setMarker(true);
            s2.setInvisible(true);
            s2.getEquipment().setItemInMainHand(new ItemStack(Material.PACKED_ICE));
            s2.setRightArmPose(fromNMS(new Vector3f(-585f - 360, 11.90625f, -317.125f)));
        });
        ArmorStand stand3 = entity.getWorld().spawn(entity.getEyeLocation().add(0,-1.3,0.2), ArmorStand.class, s2 ->{
            s2.setGravity(false);
            s2.setMarker(true);
            s2.setInvisible(true);
            s2.getEquipment().setItemInMainHand(new ItemStack(Material.PACKED_ICE));
            s2.setRightArmPose(fromNMS(new Vector3f(-584.90625f - 360, 11.78125f, -317.0625f)));
        });
        Attributable attributable = entity;
        final AttributeInstance attribute = attributable.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);


        freezedEntitys.put(entity, new BukkitRunnable() {
            private int i = 0;
            private double beforeSpeed = 0;
            @Override
            public void run() {
                if(i >= 20*5){
                    cancel();
                }
                else
                if(attribute.getValue() != 0) {
                    beforeSpeed = attribute.getBaseValue();
                    attribute.setBaseValue(0);
                }
                i++;
                stand.teleport(entity.getEyeLocation().add(-0.2,-1.5,0));
                stand1.teleport(entity.getEyeLocation().add(-0.2,-1.3,0));
                stand2.teleport(entity.getEyeLocation().add(0,-1.5,0));
                stand3.teleport(entity.getEyeLocation().add(0,-1.3,0));


            }
            @Override
            public synchronized void cancel() throws IllegalStateException {
                Bukkit.getScheduler().cancelTask(this.getTaskId());
                freezedEntitys.remove(entity);
                stand.remove();
                stand1.remove();
                stand2.remove();
                stand3.remove();
                attribute.setBaseValue(beforeSpeed);
            }
        });
        freezedEntitys.get(entity).runTaskTimer(Main.getMain(), 0,1);

    }


    @EventHandler
    public void extraDamageEvent(DamagePrepairEvent event) {
        if(freezedEntitys.containsKey(event.getEntity())) {
            event.addPreMultiplier(0.1);
        }
    }
    @EventHandler
    public void ondeath(EntityDeathEvent event){
        if(freezedEntitys.containsKey(event.getEntity()))
            freezedEntitys.get(event.getEntity()).cancel();
    }
    private EulerAngle fromNMS(Vector3f old) {
        return new EulerAngle(Math.toRadians(old.getX()), Math.toRadians(old.getY()), Math.toRadians(old.getZ()));
    }

    }
