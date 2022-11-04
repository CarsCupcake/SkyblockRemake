package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Collection;
import java.util.Random;

public class BonzoStaffProjectile {
private BukkitRunnable run;
private final ArmorStand stand;
private final LivingEntity shooter;

public BonzoStaffProjectile(Location loc, LivingEntity shooter) {
	this.shooter = shooter;
	stand = loc.getWorld().spawn(loc, ArmorStand.class, s ->{
		s.setInvisible(true);
		s.setInvulnerable(true);
		s.getEquipment().setHelmet(balloon());
	});
	
	
	
	
	run = new BukkitRunnable() {
		private int rune = 0;
		@Override
		public void run() {
			rune++;
			stand.teleport(loc.add(loc.getDirection().multiply(1)));

			if(!stand.getEyeLocation().getBlock().isPassable() || !stand.getEyeLocation().getWorld().getNearbyEntities(stand.getEyeLocation(), 1, 1, 1).stream()
				      .filter(e -> !(e instanceof Player) && !(e instanceof ArmorStand)).toList().isEmpty()) {
				explode();
				
			}
			
			if(rune == 20*15)
				explode();
			
		}
	};
	run.runTaskTimer(Main.getMain(), 0, 1);
	
}

public void explode() {
	try {
		run.cancel();
	} catch (Exception ignored) {
		
	}
	stand.remove();
	stand.getEyeLocation().getWorld().spawn(stand.getEyeLocation(), Firework.class, f->{
		FireworkMeta meta = f.getFireworkMeta();
		meta.setPower(3);
		meta.addEffect(FireworkEffect.builder().withColor(Color.fromRGB(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255))).withFade(Color.fromRGB(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255))).with(Type.BALL).trail(true).build());
		f.setFireworkMeta(meta);
		f.detonate();
	});
	Collection<Entity> n = stand.getWorld().getNearbyEntities(stand.getEyeLocation(),2, 2, 2);
	for(Entity e : n) {
		//TODO add damage Calculation!

		/*e.getLocation().toVector().multiply(stand.getEyeLocation().toVector())..toLocation(e.getWorld()).getDirection().multiply(2).setY(0.5)*/
		if(e instanceof Player) {
			e.setVelocity(genVec(stand.getEyeLocation(),e.getLocation()).multiply(1).add(e.getVelocity()));
		}
	}
	}
	private Vector genVec(Location a, Location b) {
		double dX = a.getX() - b.getX();
		double dY = a.getY() - b.getY();
		double dZ = a.getZ() - b.getZ();
		double yaw = Math.atan2(dZ, dX);
		double pitch = Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + Math.PI;
		double x = Math.sin(pitch) * Math.cos(yaw);
		double y = Math.sin(pitch) * Math.sin(yaw);
		double z = Math.cos(pitch);

		Vector vector = new Vector(x, z, y);
		//If you want to: vector = vector.normalize();

		return vector;
	}


private ItemStack balloon() {
	
	Random r = new Random();
	int low = 1;
	  int high = 5;
	int result = r.nextInt(high-low) + low;
	switch(result) {
	case 1:
		return Tools.CustomHeadTexture("https://textures.minecraft.net/texture/52dd11da04252f76b6934bc26612f54f264f30eed74df89941209e191bebc0a2");
	case 2:
		return Tools.CustomHeadTexture("https://textures.minecraft.net/texture/76387fc246893d92a6dd9ea1b52dcd581e991eeee2e263b27fff1bcf1b154eb7");
	case 3:
		return Tools.CustomHeadTexture("https://textures.minecraft.net/texture/f868e6a5c4a445d60a3050b5bec1d37af1b25943745d2d479800c8436488065a");
	case 4:
		return Tools.CustomHeadTexture("https://textures.minecraft.net/texture/a26ec7cd3b6ae249997137c1b94867c66e97499da071bf50adfd37034132fa03");
	case 5:
		return Tools.CustomHeadTexture("https://textures.minecraft.net/texture/eef162def845aa3dc7d46cd08a7bf95bbdfd32d381215aa41bffad5224298728");
	default:
		return new ItemStack(Material.PLAYER_HEAD);
	}
	
}
}
