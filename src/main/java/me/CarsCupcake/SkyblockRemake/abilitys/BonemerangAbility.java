package me.CarsCupcake.SkyblockRemake.abilitys;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import me.CarsCupcake.SkyblockRemake.Main;

public class BonemerangAbility implements Listener{
@EventHandler
public void Ability(PlayerInteractEvent event) {
	ItemStack bone = event.getPlayer().getInventory().getItemInMainHand();
	if(bone.getItemMeta() != null && bone.getItemMeta().getPersistentDataContainer() != null && bone.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ability"), PersistentDataType.STRING) != null && bone.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ability"), PersistentDataType.STRING).equals("swing")) { 
	if(event.getAction() != Action.RIGHT_CLICK_AIR) return;
    if(!boneCheck(event.getPlayer() )) return;

    Player player =event.getPlayer();
    

    ArmorStand stand = (ArmorStand) player.getWorld().spawn(player.getLocation().add(0, 1, 0), ArmorStand.class, st->{
    	st.setInvisible(true);
    	st.setInvulnerable(true);
    });
    Vector teleportTo = player.getLocation().getDirection().normalize().multiply(1);

    @SuppressWarnings("unused")
	ItemStack item = new ItemStack(bone);

   
    stand.setGravity(false);

    stand.setArms(false);
    stand.getEquipment().setItemInMainHand(new ItemStack(Material.BONE));
    stand.setRightArmPose(new EulerAngle(Math.toRadians(350), Math.toRadians(120), Math.toRadians(0)));
    stand.setBasePlate(false);

    player.playSound(player.getLocation(), Sound.BLOCK_BONE_BLOCK_BREAK, 2.0f, 2.0f);

    new BukkitRunnable() {
        public int ran = 0;

        @SuppressWarnings("deprecation")
		@Override
        public void run() {
            ran++;

            if(ran == 26) {
                stand.remove();
                returnBone(bone);

                cancel();
                return;
            }

            int i = ran;
            int num = 120;
            int angle;
            boolean back;

            Location loc = null;

            if(i < 13) {
                angle = (i * 20) + num;
                back = false;
            } else {
                angle = (i * 20) - num;
                back = true;

                loc = player.getLocation();
                loc.setDirection(teleportTo);
            }

            if(!stand.getLocation().getBlock().getType().isTransparent()) {
                stand.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, stand.getLocation(), 3);

                stand.remove();
                new BukkitRunnable() {
                    @Override
                    public void run() {
                      
                            returnBone(bone);
                            cancel();
                        
                    }
                }.runTaskLater(Main.getMain(), 20*3L);

                cancel();
                return;
            }

            stand.setRightArmPose(new EulerAngle(Math.toRadians(0), Math.toRadians(angle), Math.toRadians(380)));

            if(i % 2 == 0 && i < 13) {
                stand.teleport(stand.getLocation().add(teleportTo).multiply(1));
                stand.teleport(stand.getLocation().add(teleportTo).multiply(1));
            } else if(i % 2 == 0) {
                stand.teleport(stand.getLocation().subtract(loc.getDirection().normalize().multiply(1)));
                stand.teleport(stand.getLocation().subtract(loc.getDirection().normalize().multiply(1)));
            }

            for(Entity e : stand.getNearbyEntities(1, 1, 1)) {
                if(e instanceof Damageable && e != player) {
                    Damageable entity = (Damageable) e;
                    if(entity instanceof ArmorStand) return;
                    if(entity instanceof Player && !entity.hasMetadata("NPC")) return;

                    
                    if(!(entity instanceof LivingEntity)) return;

                    entity.setLastDamageCause(new EntityDamageByEntityEvent(player, e, EntityDamageEvent.DamageCause.CUSTOM, 0));
                   
                    if(back) {
                        
                    }

                }
            }

        }

    }.runTaskTimer(Main.getMain(), 2L, 0L);
	}
}

private void returnBone(ItemStack item) {
    if(item.getType() == Material.AIR) return;

    
    item.setType(Material.BONE);
}
private boolean boneCheck(Player player) {
    ItemStack item = player.getInventory().getItemInMainHand();

    

   
        item.setType(Material.GHAST_TEAR);
       
        return true;
    }



}
