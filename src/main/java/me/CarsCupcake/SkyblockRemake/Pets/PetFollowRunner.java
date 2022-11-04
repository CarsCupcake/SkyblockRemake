package me.CarsCupcake.SkyblockRemake.Pets;

import org.bukkit.Location;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Configs.PetMenus;

public class PetFollowRunner {
	private BukkitRunnable run;
	private final Player owner;
	private Pet pet;
	public ArmorStand stand;
	public ArmorStand nameTag;
	private final int slot;
	private int runnable = 0;
	private boolean upmotion;
	private double currmotion;

	@SuppressWarnings("deprecation")
	public PetFollowRunner(Player owner, Pet pet, int petslot) {
		this.owner = owner;
		this.pet = pet;
		slot = petslot;
		stand = owner.getWorld().spawn(owner.getLocation(), ArmorStand.class, s -> {
			s.setItemInHand(pet.getRawItemStack());
			s.setCustomNameVisible(false);
			s.setMarker(true);

			s.setInvisible(true);
			s.setRightArmPose(new EulerAngle(5.497787143782138, 4.066617157157146788, 0));
		});
		nameTag = stand.getWorld().spawn(makeNameTagLocation(), ArmorStand.class, s -> {
			s.setCustomNameVisible(true);
			s.setMarker(true);
			s.setCustomName("§7[Lvl " + PetMenus.get().getInt(owner.getUniqueId() + "." + petslot + ".level") + "] "
					+ pet.rarity.getPrefix() + owner.getName() + "'s " + pet.name);
			s.setInvisible(true);
		});
		Main.petstand.put(owner, this);
		idle();

	}

	private Location makeNameTagLocation() {
		Location loc = stand.getLocation();
		loc.setPitch(0);
		Vector dir = loc.getDirection();
		dir.multiply(-0.3);
		loc = loc.add(dir);
		loc = loc.add(0, 1, 0);
		return loc;

	}

	@SuppressWarnings("deprecation")
	public void updatePet(Pet pet) {
		this.pet = pet;

		stand.setItemInHand(pet.getRawItemStack());

		stand.setCustomName("§7[Lvl " + PetMenus.get().getInt(owner.getUniqueId() + "." + slot + ".level") + "] "
				+ pet.rarity.getPrefix() + owner.getName() + "'s " + pet.name);

	}

	public void updatePet() {
		nameTag.setCustomName("§7[Lvl " + PetMenus.get().getInt(owner.getUniqueId() + "." + slot + ".level") + "] "
				+ pet.rarity.getPrefix() + owner.getName() + "'s " + pet.name);
	}
	

	public void remove() {
		stand.remove();
		nameTag.remove();
		run.cancel();
	}

	private void idle() {
		run = new BukkitRunnable() {

			@Override
			public void run() {
				if (stand.getNearbyEntities(1.3, 1.3, 1.3).contains(owner)) {

					Location loc = stand.getLocation();

					int newrunnable = runnable - 1;
					runnable -= 1;

					if (newrunnable <= 0) {

						if (runnable == 0)
							if (upmotion) {
								currmotion = 0.04;

							} else
								currmotion = -0.04;

						double newMotion = 0;
						if (upmotion) {
							newMotion = currmotion - 0.002;
						} else {
							newMotion = currmotion + 0.002;
						}

						currmotion = newMotion;
						stand.teleport(loc.add(0, newMotion, 0));
						nameTag.teleport(makeNameTagLocation());

						if (newMotion <= -0.04 && upmotion) {
							upmotion = false;
							runnable = 5;
						} else {
							if (newMotion >= 0.04 && !upmotion) {
								upmotion = true;
								runnable = 5;
							}
						}

					} else {
						if (upmotion) {

							stand.teleport(loc.add(0, 0.04, 0));
							nameTag.teleport(makeNameTagLocation());
						} else {
							stand.teleport(loc.add(0, -0.04, 0));
							nameTag.teleport(makeNameTagLocation());
						}
					}

				} else {

					runnable = 5;
					upmotion = true;

					run.cancel();
					follow();

				}

			}
		};
		run.runTaskTimer(Main.getMain(), 1, 0);

	}

	private void follow() {
		run = new BukkitRunnable() {

			@Override
			public void run() {
				if (!stand.getWorld().getNearbyEntities(stand.getEyeLocation(), 1, 0.1, 1).contains(owner)) {

					stand.setHeadPose(setHeadPos(stand,
							stand.getLocation()
									.setDirection(owner.getLocation().add(0, 0.5, 0).toVector()
											.subtract(stand.getEyeLocation().getDirection()))
									.getYaw(),
							getPitchFromTo(stand.getEyeLocation(), owner.getLocation().add(0, 0.5, 0))));

					Location loc = stand.getEyeLocation();
					loc.setPitch(getPitchFromTo(loc, owner.getLocation().add(0, 0.5, 0)));
					loc = loc.setDirection(
							owner.getLocation().add(0, 0.5, 0).toVector().subtract(stand.getEyeLocation().toVector()));
					Vector dir = loc.getDirection();

					dir.normalize();
					dir.multiply(0.4);
					loc.add(dir);
					stand.teleport(loc);
					nameTag.teleport(makeNameTagLocation());

				} else {

					run.cancel();
					idle();

				}

			}
		};
		run.runTaskTimer(Main.getMain(), 1, 0);

	}

	private float getPitchFromTo(Location armorstandLoc, Location direction) {

		double dy = direction.getY() - armorstandLoc.getY();
		double dx = direction.getX() - armorstandLoc.getX();
		double hypo = Math.sqrt(dy * dy + dx * dx);
		float angle = (float) Math.toDegrees(Math.cos(dy / hypo));

		if (angle < 0.0F) {
			angle += 180.0F;
		}

		return angle;
	}

	private EulerAngle setHeadPos(ArmorStand as, double yaw, double pitch) {
		double xint = Math.cos(yaw / Math.PI);
		double zint = Math.sin(yaw / Math.PI);
		// This will convert the yaw to a xint and zint between -1 and 1. Here are some
		// examples of how the yaw changes:
		/*
		 * yaw = 0 : xint = 1. zint = 0; East yaw = 90 : xint = 0. zint = 1; South yaw =
		 * 180: xint = -1. zint = 0; North yaw = 270 : xint = 0. zint = -1; West
		 */
		double yint = Math.sin(pitch / Math.PI);
		// This converts the pitch to a yint
		EulerAngle ea = as.getHeadPose();
		ea.setX(xint);
		ea.setY(yint);
		ea.setZ(zint);
		return ea;
		// This gets the EulerAngle of the armorStand, sets the values, and then updates
		// the armorstand.
	}

}
