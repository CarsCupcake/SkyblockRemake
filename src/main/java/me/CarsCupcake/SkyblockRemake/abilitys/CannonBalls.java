package me.CarsCupcake.SkyblockRemake.abilitys;



import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Tools;


public class CannonBalls implements Listener{

	@SuppressWarnings("deprecation")
	@EventHandler
	public void cannonBall(PlayerInteractEvent event) {
		if(event.getPlayer().getItemInHand() != null) {
			ItemStack bone = event.getPlayer().getInventory().getItemInMainHand();
			if(bone.getItemMeta() != null && bone.getItemMeta().getPersistentDataContainer() != null && bone.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ability"), PersistentDataType.STRING) != null && bone.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ability"), PersistentDataType.STRING).equals("cannon")) { 
			if(event.getAction() != Action.RIGHT_CLICK_AIR) return;
			
			Player player = event.getPlayer();
			float yaw = player.getLocation().getYaw();
			float pitch = player.getLocation().getPitch();

			System.out.println("cannon_shoot");
			
			ArmorStand stand = player.getWorld().spawn(player.getLocation(), ArmorStand.class, s ->{
			s.setInvisible(true);
			s.setInvulnerable(true);
			s.setGravity(false);
			
			ItemStack ball = Tools.CustomHeadTexture("https://textures.minecraft.net/texture/25dffc5e53c837385f5d7863935bc5afa3e4a8176ae52e2f8d7d797c952e3f");
			s.getEquipment().setHelmet(ball);
			s.teleport(new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), yaw,pitch));
			
			
			});
			
			stand.setHeadPose(setHeadPos(stand, yaw, pitch));
			CannonBallTeleportation(stand, 0);
			
			}
		}
	}
	
	public void CannonBallTeleportation(ArmorStand stand,  int runntime) {
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				Location loc = stand.getLocation();
				Vector dir = loc.getDirection();
				dir.normalize();
				dir.multiply(1); //1 blocks a way
				loc.add(dir);
				stand.teleport(loc);
				
				if(runntime == 20*5) {
					stand.remove();
					return;
				}else {
					CannonBallTeleportation(stand, runntime + 1);
				}
			}
		}.runTaskLater(Main.getMain(), 1);
	}
	
	public EulerAngle setHeadPos(ArmorStand as, double yaw, double pitch){
		double xint = Math.cos(yaw/Math.PI);
		double zint = Math.sin(yaw/Math.PI);
		//This will convert the yaw to a xint and zint between -1 and 1. Here are some examples of how the yaw changes:
		/*
		yaw = 0 : xint = 1. zint = 0;  East
		yaw = 90 : xint = 0. zint = 1; South
		yaw = 180: xint = -1. zint = 0; North
		yaw = 270 : xint = 0. zint = -1; West
		*/
		double yint = Math.sin(pitch/Math.PI);
		//This converts the pitch to a yint
		EulerAngle ea = as.getHeadPose();
		ea.setX(xint);
		ea.setY(yint);
		ea.setZ(zint);
		return ea;
		//This gets the EulerAngle of the armorStand, sets the values, and then updates the armorstand.
		}
	
}
