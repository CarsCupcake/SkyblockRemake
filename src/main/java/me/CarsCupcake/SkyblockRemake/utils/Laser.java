package me.CarsCupcake.SkyblockRemake.utils;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Squid;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Laser{
	@Getter
	private final LaserProvider start;
	@Getter
	private final LaserReciever end;
	private static final HashMap<LivingEntity, Squid> lasers = new HashMap<>();
	public Laser(Location guardian, Location squid){
		guardian.setPitch(0);
		squid.setPitch(0);

		Assert.isTrue(guardian.getWorld() == squid.getWorld(), "The world are not allowed to be different!");



		start = new LaserProvider();
		start.spawn(guardian);

		end = new LaserReciever();
		end.spawn(squid);

		start.g.setTarget(end.getEntity());

		lasers.put(start.g, end.g);

	}
	public void stop(){
		lasers.remove(start.g);

		start.getEntity().remove();
		end.getEntity().remove();
	}

	public static class LaserListener implements Listener {
		@EventHandler
		public void targetSwap(EntityTargetLivingEntityEvent event){
			if(lasers.containsKey(event.getEntity()) && lasers.get(event.getEntity()) != event.getTarget())
				event.setCancelled(true);


		}
	}

	public static class LaserReciever extends SkyblockEntity{
		private Squid g;

		@Override
		public int getMaxHealth() {
			return 100;
		}

		@Override
		public LivingEntity getEntity() {
			return g;
		}

		@Override
		public int getDamage() {
			return 0;
		}

		@Override
		public void spawn(Location loc) {
			g = loc.getWorld().spawn(loc, Squid.class, a -> {
				a.setInvisible(true);
				a.setInvulnerable(true);
				a.setAI(false);
				a.setSilent(true);
				a.setGravity(false);
				a.addScoreboardTag("invinc");
				a.setCollidable(false);
			});
			SkyblockEntity.livingEntity.put(g, this);
			Main.updateentitystats(g);
		}

		@Override
		public String getName() {
			return null;
		}

		@Override
		public HashMap<ItemManager, Integer> getDrops(SkyblockPlayer player) {
			return null;
		}

		@Override
		public void updateNameTag() {

		}

		@Override
		public void kill() {

		}

		@Override
		public void damage(double damage, SkyblockPlayer player) {

		}

		@Override
		public boolean hasNoKB() {
			return true;
		}

		@Override
		public int getTrueDamage() {
			return 0;
		}
	}

	public static class LaserProvider extends SkyblockEntity {
		private Guardian g;

		@Override
		public int getMaxHealth() {
			return 100;
		}

		@Override
		public LivingEntity getEntity() {
			return g;
		}

		@Override
		public int getDamage() {
			return 0;
		}

		@Override
		public void spawn(Location loc) {
			g = loc.getWorld().spawn(loc, Guardian.class, a -> {
				a.setInvisible(true);
				a.setInvulnerable(true);
				a.setLaser(true);
				a.setSilent(true);
				a.setGravity(false);
				a.addScoreboardTag("invinc");
				a.setCollidable(false);
			});
			SkyblockEntity.livingEntity.put(g, this);
			Main.updateentitystats(g);
		}

		@Override
		public String getName() {
			return null;
		}

		@Override
		public HashMap<ItemManager, Integer> getDrops(SkyblockPlayer player) {
			return null;
		}

		@Override
		public void updateNameTag() {

		}

		@Override
		public void kill() {

		}

		@Override
		public void damage(double damage, SkyblockPlayer player) {

		}

		@Override
		public boolean hasNoKB() {
			return true;
		}

		@Override
		public int getTrueDamage() {
			return 0;
		}
	}
}