package me.CarsCupcake.SkyblockRemake.isles.Dungeon.Boss.F1;

import java.util.Collection;
import java.util.Random;

import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class BonzoPrejectile {
private final BukkitRunnable run;
private final ArmorStand stand;
private final LivingEntity shooter;

public BonzoPrejectile(Location loc, LivingEntity shooter) {
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
				      .filter(e -> e instanceof Player).toList().isEmpty()) {
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
	stand.getEyeLocation().getWorld().spawn(stand.getLocation(), Firework.class, f->{
		FireworkMeta meta = f.getFireworkMeta();
		meta.setPower(0);
		meta.addEffect(FireworkEffect.builder().withColor(Color.fromRGB(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255))).withFade(Color.fromRGB(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255))).with(Type.BALL).trail(true).build());
		f.setFireworkMeta(meta);
		f.detonate();
	});
	Collection<Entity> n = stand.getWorld().getNearbyEntities(stand.getEyeLocation(),1, 1, 1);
	for(Entity e : n) {
		if(e instanceof Player) {
			SkyblockPlayer p = SkyblockPlayer.getSkyblockPlayer((Player)e);
			Calculator c = new Calculator();
			c.entityToPlayerDamage(SkyblockEntity.livingEntity.getSbEntity(shooter), p);
			c.damagePlayer(p);
			c.showDamageTag(p);
		}
	}
	}


private ItemStack balloon() {
	
	Random r = new Random();
	int low = 1;
	  int high = 5;
	int result = r.nextInt(high-low) + low;
	return switch (result) {
		case 1 ->
				Tools.CustomHeadTexture("https://textures.minecraft.net/texture/52dd11da04252f76b6934bc26612f54f264f30eed74df89941209e191bebc0a2");
		case 2 ->
				Tools.CustomHeadTexture("https://textures.minecraft.net/texture/76387fc246893d92a6dd9ea1b52dcd581e991eeee2e263b27fff1bcf1b154eb7");
		case 3 ->
				Tools.CustomHeadTexture("https://textures.minecraft.net/texture/f868e6a5c4a445d60a3050b5bec1d37af1b25943745d2d479800c8436488065a");
		case 4 ->
				Tools.CustomHeadTexture("https://textures.minecraft.net/texture/a26ec7cd3b6ae249997137c1b94867c66e97499da071bf50adfd37034132fa03");
		case 5 ->
				Tools.CustomHeadTexture("https://textures.minecraft.net/texture/eef162def845aa3dc7d46cd08a7bf95bbdfd32d381215aa41bffad5224298728");
		default -> new ItemStack(Material.PLAYER_HEAD);
	};
	
}
}
