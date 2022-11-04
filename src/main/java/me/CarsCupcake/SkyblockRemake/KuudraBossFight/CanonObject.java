package me.CarsCupcake.SkyblockRemake.KuudraBossFight;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.world.entity.EnumMoveType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftArmorStand;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import org.spigotmc.event.entity.EntityDismountEvent;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Tools;




public class CanonObject implements Listener {
public static ArrayList<CanonObject> Canons = new ArrayList<>();
public static HashMap<Player, CanonObject> players = new HashMap<>();
public List<ArmorStand> armorStands = new ArrayList<>();
public ArmorStand mountArmorStand;
public ArmorStand canonclick;
public Location loc;
public boolean inUse;
public Player rider;
public BukkitRunnable cannonCooldown;
public BukkitRunnable TurnTurret;



@SuppressWarnings("deprecation")
public CanonObject(Location loc) {
	Main.getMain().getServer().getPluginManager().registerEvents(this,Main.getMain());
	Canons.add(this);
	this.loc = loc;
	inUse = false;
	rider = null;
	
	loc = loc.add(0,-1.4,0);
	loc.setPitch(0);
	loc.setYaw(0);
	
	ArmorStand stand =  loc.getWorld().spawn(loc, ArmorStand.class,s->{
		s.setInvulnerable(true);
		s.setInvisible(true);
		s.setGravity(false);
	});
	canonclick = stand;
	
	//0
	 stand =  loc.getWorld().spawn(loc, ArmorStand.class,s->{
		s.setInvulnerable(true);
		s.setInvisible(true);
		s.setGravity(false);
		s.setHelmet(new ItemStack(Material.NETHER_BRICK_SLAB));
	});
	
	armorStands.add(stand);
	//1
	stand =  loc.getWorld().spawn(loc.add(0,0,0.625), ArmorStand.class,s->{
		s.setInvulnerable(true);
		s.setInvisible(true);
		s.setGravity(false);
		s.setHelmet(new ItemStack(Material.NETHER_BRICK_SLAB));
	});
	armorStands.add(stand);
	//2
	loc.add(0,0,-0.625);
	stand =  loc.getWorld().spawn(loc.add(0,0,-0.625), ArmorStand.class,s->{
		s.setInvulnerable(true);
		s.setInvisible(true);
		s.setGravity(false);
		s.setHelmet(new ItemStack(Material.NETHER_BRICK_SLAB));
	});
	armorStands.add(stand);
	//3
	stand =  loc.getWorld().spawn(loc.add(0,0,-0.625), ArmorStand.class,s->{
		s.setInvulnerable(true);
		s.setInvisible(true);
		s.setGravity(false);
		s.setHelmet(new ItemStack(Material.NETHER_BRICK_SLAB));
	});
	//4
	armorStands.add(stand);
	stand =  loc.getWorld().spawn(loc.add(0,0.3105,0), ArmorStand.class,s->{
		s.setInvulnerable(true);
		s.setInvisible(true);
		s.setGravity(false);
		s.setHelmet(new ItemStack(Material.BEDROCK));
	});
	armorStands.add(stand);
	
	ArmorStand chicken =  loc.getWorld().spawn(loc.add(0,2,0), ArmorStand.class,s->{
		s.setInvulnerable(true);
		s.setInvisible(true);
		s.setGravity(false);

		
	});

	mountArmorStand = chicken;
	
	loc.add(0,-2.3105,0.625*2);
	
	//5
	stand =  loc.getWorld().spawn(loc.add(0.625,0,0), ArmorStand.class,s->{
		s.setInvulnerable(true);
		s.setInvisible(true);
		s.setGravity(false);
		s.setHelmet(new ItemStack(Material.NETHER_BRICK_SLAB));
	});
	loc.add(-0.625,0,0);
	
	armorStands.add(stand);
	//7
	stand =  loc.getWorld().spawn(loc.add(-0.625,0,0), ArmorStand.class,s->{
		s.setInvulnerable(true);
		s.setInvisible(true);
		s.setGravity(false);
		s.setHelmet(new ItemStack(Material.NETHER_BRICK_SLAB));
	});
	armorStands.add(stand);
	loc.add(0.625,0,0);
	
	//8
	stand =  loc.getWorld().spawn(loc.add(0.625,1,0), ArmorStand.class,s->{
		s.setInvulnerable(true);
		s.setInvisible(true);
		s.setGravity(false);
		s.setSmall(true);
		s.setHelmet(new ItemStack(Material.NETHER_BRICKS));
	});
	armorStands.add(stand);
	//9
	stand =  loc.getWorld().spawn(loc.add(0,0.4375,0), ArmorStand.class,s->{
		s.setInvulnerable(true);
		s.setInvisible(true);
		s.setGravity(false);
		s.setSmall(true);
		s.setHelmet(new ItemStack(Material.NETHER_BRICKS));
	});
	armorStands.add(stand);
	//10
	loc.add(-0.625, -1.4375,0);
	loc.add(-0.625, 0, 0);
	stand =  loc.getWorld().spawn(loc.add(0,1,0), ArmorStand.class,s->{
		s.setInvulnerable(true);
		s.setInvisible(true);
		s.setGravity(false);
		s.setSmall(true);
		s.setHelmet(new ItemStack(Material.NETHER_BRICKS));
	});
	
	armorStands.add(stand);
	//11
	stand =  loc.getWorld().spawn(loc.add(0,0.4375,0), ArmorStand.class,s->{
		s.setInvulnerable(true);
		s.setInvisible(true);
		s.setGravity(false);
		s.setSmall(true);
		s.setHelmet(new ItemStack(Material.NETHER_BRICKS));
	});
	armorStands.add(stand);
	
	loc.add(0.625,0,0);
	stand =  loc.getWorld().spawn(loc.add(0,-0.740625,0), ArmorStand.class,s->{
		s.setInvulnerable(true);
		s.setInvisible(true);
		s.setGravity(false);
		
		s.setHelmet(new ItemStack(Material.COAL_BLOCK));
	});
	
	armorStands.add(stand);
	stand =  loc.getWorld().spawn(loc.add(0,0,0.625), ArmorStand.class,s->{
		s.setInvulnerable(true);
		s.setInvisible(true);
		s.setGravity(false);
		s.setHelmet(new ItemStack(Material.COAL_BLOCK));
	});
	armorStands.add(stand);
	stand =  loc.getWorld().spawn(loc.add(0,0,0.625), ArmorStand.class,s->{
		s.setInvulnerable(true);
		s.setInvisible(true);
		s.setGravity(false);
		s.setHelmet(new ItemStack(Material.COAL_BLOCK));
	});
	armorStands.add(stand);
	canonTurnRunnable();
	
}

public void removeCanon() {
	armorStands.forEach(stand->{
		stand.remove();
	});
	armorStands.clear();
	mountArmorStand.remove();
	Canons.remove(this);
}
public static void removeAll() {
	if(!Canons.isEmpty())
	Canons.forEach(canon->{
		canon.mountArmorStand.remove();
		canon.armorStands.forEach(stand->{
			stand.remove();
		});
		
		canon.armorStands.clear();
		
		
		
	});
	Canons.clear();
}

public void mountPlayer(Player player) {
	rider = player;
	inUse =true;
   
  
}
@SuppressWarnings("deprecation")
@EventHandler
public void mountListender(PlayerInteractAtEntityEvent  event) {
if(event.getRightClicked() instanceof ArmorStand) {
	event.setCancelled(true);
	if((armorStands.contains(event.getRightClicked()) || event.getRightClicked() == mountArmorStand || event.getRightClicked() == canonclick )&& !inUse) {
		mountPlayer(event.getPlayer());
		mountArmorStand.setPassenger(event.getPlayer());
	}
}
}
@EventHandler
public void dismountEvent(EntityDismountEvent event) {
	if(event.getDismounted() instanceof ArmorStand && event.getDismounted() == mountArmorStand) {



		
		inUse = false;
		rider = null;
	}

}

public void canonTurnRunnable(){
	TurnTurret = new BukkitRunnable() {
		
		@SuppressWarnings("deprecation")
		@Override
		public void run() {
			if(mountArmorStand.getPassenger() != null) {
				Player player = (Player)mountArmorStand.getPassenger();
			ArmorStand stand = armorStands.get(1);
			
			TelportInfrontPlayer(player, canonclick);
			
			
			Location loc = armorStands.get(0).getLocation();
			loc.setYaw(player.getLocation().getYaw());
			armorStands.get(0).teleport(loc);
			teleportRaycats(player,(CraftArmorStand)stand,armorStands.get(0).getEyeLocation(), 0.625);
			stand = armorStands.get(2);
			teleportRaycats(player,(CraftArmorStand)stand,armorStands.get(0).getEyeLocation(), -0.625);
			stand = armorStands.get(4);
			teleportRaycats(player,(CraftArmorStand)stand,armorStands.get(0).getEyeLocation(), -0.625*2);
			stand = armorStands.get(3);
			teleportRaycats(player,(CraftArmorStand)stand,armorStands.get(0).getEyeLocation(), -0.625*2);
			
			
			
			
			 
			
			//Coal stands: 12,13,14
			ArmorStand coal1 = armorStands.get(11);
			coal1.getLocation().setYaw(player.getLocation().getYaw());
			coal1.setHeadPose(new EulerAngle(player.getLocation().getDirection().getY()*(-1),0,0));
			teleportRaycats(player,(CraftArmorStand)coal1,armorStands.get(0).getEyeLocation(), 0D);
			Location l = coal1.getLocation();
			l.setPitch(player.getLocation().getPitch());
			coal1.teleport(l);
			ArmorStand coal2 = armorStands.get(12);
			coal2.getLocation().setYaw(player.getLocation().getYaw());
			coal2.setHeadPose(new EulerAngle(player.getEyeLocation().getDirection().getY()*(-1),0,0));
			CoalReaycast(coal1,coal2, 0.625);
			
			
			
			ArmorStand coal3 = armorStands.get(13);
			coal3.getLocation().setYaw(player.getLocation().getYaw());
			coal3.setHeadPose(new EulerAngle(player.getEyeLocation().getDirection().getY()*(-1),0,0));
			CoalReaycast(coal2,coal3, 0.625);
			
			
			loc.setYaw(loc.getYaw() + 90);
			stand = armorStands.get(5);
			teleportRaycats(player,(CraftArmorStand)stand,loc, -0.625);
			loc = armorStands.get(0).getLocation();
			loc.setYaw(loc.getYaw() + 90);
			stand = armorStands.get(6);
			teleportRaycats(player,(CraftArmorStand)stand,loc, 0.625);
			loc = armorStands.get(0).getLocation();
			loc.setYaw(loc.getYaw() + 90);
			stand = armorStands.get(7);
			teleportRaycats(player,(CraftArmorStand)stand,loc, -0.625);
			
			loc = armorStands.get(0).getLocation();
			loc.setYaw(loc.getYaw() + 90);
			stand = armorStands.get(8);
			teleportRaycats(player,(CraftArmorStand)stand,loc, -0.625);
			
			loc = armorStands.get(0).getLocation();
			loc.setYaw(loc.getYaw() + 90);
			stand = armorStands.get(9);
			teleportRaycats(player,(CraftArmorStand)stand,loc, 0.625);
			
			loc = armorStands.get(0).getLocation();
			loc.setYaw(loc.getYaw() + 90);
			stand = armorStands.get(10);
			teleportRaycats(player,(CraftArmorStand)stand,loc, 0.625);
			
			
			//last step!!!!
			
			playerstandRaycats(player,(CraftArmorStand)mountArmorStand,armorStands.get(0).getEyeLocation(), -0.625*2);
			
			}
		}
		
	};
	TurnTurret.runTaskTimer(Main.getMain(), 0, 1);
}
public void teleportRaycats(Player rider,CraftArmorStand stand,Location c, double i) {
	double Y = stand.getLocation().getY();
	Location loc = c;
	Location temp = rider.getEyeLocation();
	temp.setPitch(0);
	temp.setYaw(c.getYaw());
	Vector dir =temp.getDirection();
	dir.normalize();
	dir.multiply(i); //1 blocks a way

	loc.add(dir);

	loc.setY(Y);
	stand.teleport(loc);
	
}

public void playerstandRaycats(Player rider,CraftArmorStand stand,Location c, double i) {
	double Y = stand.getLocation().getY();
	Location loc = c;
	Location temp = rider.getLocation();
	temp.setPitch(0);
	Vector dir =temp.getDirection();
	dir.normalize();
	dir.multiply(i); //1 blocks a way

	loc.add(dir);

	loc.setY(Y);
	Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp " + stand.getUniqueId().toString() + " " + loc.getX() + " " + loc.getY() + " " + loc.getZ());
}


public void TelportInfrontPlayer(Player player, ArmorStand stand) {
	Location loc = player.getEyeLocation();
	loc.setPitch(rider.getLocation().getPitch());
		Vector dir =player.getLocation().getDirection();
		dir.normalize();
		dir.multiply(0.625); //1 blocks a way
		loc.add(dir);

		
		stand.teleport(loc);	
}


public void CoalReaycast(ArmorStand lastCoal,ArmorStand stand, double i) {

	Location loc = lastCoal.getLocation();
loc.setPitch(rider.getLocation().getPitch());
	Vector dir =lastCoal.getLocation().getDirection();
	dir.normalize();
	dir.multiply(i); //1 blocks a way
	loc.add(dir);

	
	stand.teleport(loc);
}

public Location ShootPosition(ArmorStand lastCoal, double i) {

	Location loc = lastCoal.getLocation();
loc.setPitch(rider.getLocation().getPitch());
	Vector dir =lastCoal.getLocation().getDirection();
	dir.normalize();
	dir.multiply(i); //1 blocks a way
	loc.add(dir);

	
	return loc;
}

@EventHandler
public void cannonBall(PlayerInteractAtEntityEvent event) {
	System.out.println("lel");

		if(inUse && rider == event.getPlayer()) { 

		
		ArmorStand front = armorStands.get(13);
		
		float yaw = front.getLocation().getYaw();
		float pitch = front.getLocation().getPitch();

		
		Location newL = ShootPosition(front, 0.625);
		ArmorStand stand = front.getWorld().spawn(newL, ArmorStand.class, s ->{
		s.setInvisible(true);
		s.setInvulnerable(true);
		s.setGravity(false);
		
		ItemStack ball = Tools.CustomHeadTexture("https://textures.minecraft.net/texture/25dffc5e53c837385f5d7863935bc5afa3e4a8176ae52e2f8d7d797c952e3f");
		s.getEquipment().setHelmet(ball);
		s.teleport(new Location(newL.getWorld(), newL.getX(), newL.getY(), newL.getZ(), yaw,pitch));
		s.setHeadPose(setHeadPos(s, yaw, pitch));
		
		});
		
		
		CannonBallTeleportation(stand, 0,null);
		
		
	}
}

public void CannonBallTeleportation(ArmorStand stand,  int runntime,ArrayList<Tentacles> prehits) {
	ArrayList<Tentacles> hitTentacles;
	if(prehits != null)
	hitTentacles = prehits;
	else
		hitTentacles = new ArrayList<>();
	
		
	new BukkitRunnable() {
		
		@Override
		public void run() {
			Location loc = stand.getLocation();
			Vector dir = loc.getDirection();
			dir.normalize();
			dir.multiply(1); //1 blocks a way
			loc.add(dir);
			stand.teleport(loc);
			
			ArrayList<Tentacles> targets =  Tentacles.nearEntitys(stand);
			if(targets != null) {
				for(Tentacles t : targets) {
					System.out.println("..");
					if(!hitTentacles.contains(t)) {
						Bukkit.broadcastMessage(t.hp + " " + hitTentacles.contains(t));
						hitTentacles.add(t);
						t.setHP(t.hp - 100);
						
						
					}
				}
			}
			
			System.out.println(".");
			if(runntime == 20*5) {
				stand.remove();
				return;
			}else {
				CannonBallTeleportation(stand, runntime + 1, hitTentacles);
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
