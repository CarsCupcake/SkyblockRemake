package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F1;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import me.CarsCupcake.SkyblockRemake.API.HealthChangeReason;
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
import me.CarsCupcake.SkyblockRemake.Tools;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class BonzoPrejectile {
private BukkitRunnable run;
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
	} catch (Exception e) {
		
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
			
			float ehp = (float) (float)Main.playerhealthcalc(p)*(1+((float)Main.playerdefcalc(p)/100));
			float effectivedmg = (float)Main.playerhealthcalc(p)/(float)ehp ;
			int totaldmg = (int) ((int) SkyblockEntity.livingEntity.get(shooter).getDamage()*effectivedmg);
			
				SkyblockEntity se = SkyblockEntity.livingEntity.get(shooter);
				int truedamage = se.getTrueDamage();
				if(truedamage != 0) {
					float trueehp = (float) (float)Main.playerhealthcalc(p)*(1+((float)Main.playertruedefense(p)/100));
					float effectivetruedmg = (float)Main.playerhealthcalc(p)/(float)trueehp;
					totaldmg += (int) ((int) truedamage*effectivetruedmg);
				
			}
			
			
			
			if(Main.absorbtion.get(p) - totaldmg  < 0) {
				float restdamage =   (float)totaldmg - (float) Main.absorbtion.get(p);
				Main.absorbtion.replace(p, 0);
				p.setHealth( p.currhealth  - (int)restdamage, HealthChangeReason.Damage);
			}else {
				Main.absorbtion.replace(p, Main.absorbtion.get(p) - totaldmg);
			}
			

			Main.updatebar(p);
			System.out.println(p.getName() + " got damaged :P");
		}
	}
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
