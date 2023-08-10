package me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Dungeon;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.ranged.ScaredSkeleton;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.special.Skeletor;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.special.SkeletorPrime;
import me.CarsCupcake.SkyblockRemake.utils.Assert;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;

public abstract class DungeonSkeleton extends DungeonMob{
    public final static Set<ArmorStand> skeletorSkulls = new HashSet<>();
    public DungeonSkeleton(int floor, boolean master) {
        super(floor, master);
    }

    @Override
    public void kill() {
        super.kill();
        ArmorStand stand = getEntity().getWorld().spawn(getEntity().getLocation().subtract(0, 1.325, 0), ArmorStand.class, s -> {
            s.setGravity(false);
            s.setCollidable(false);
            s.setInvisible(true);
            s.setBasePlate(false);
            s.getEquipment().setHelmet(new ItemStack(Material.SKELETON_SKULL));
            s.addScoreboardTag("remove");
        });
        List<ArmorStand> stands = new ArrayList<>();
        for (ArmorStand s : skeletorSkulls) {
            if(s.getEyeLocation().distance(stand.getEyeLocation()) <= 5) {
                stands.add(s);
                if(stands.size() == 2) break;
            }
        }
        if(stands.size() == 2) {
            ArmorStand stand2 = stands.get(0);
            skeletorSkulls.remove(stand2);
            ArmorStand stand3 = stands.get(1);
            skeletorSkulls.remove(stand3);
            Location middle = getTriangleCenter(stand.getEyeLocation(), stand2.getEyeLocation(), stand3.getEyeLocation());
            List<ArmorStand> bones = new ArrayList<>();
            List<Integer> yaws = new ArrayList<>();
            yaws.add(0);
            yaws.add(120);
            yaws.add(240);
            for (int i : yaws) {
                Vector v = new Vector(1.2, -0.25, 0).rotateAroundY(Math.toRadians(i));
                bones.add(middle.getWorld().spawn(middle.clone().add(v), ArmorStand.class,s -> {
                    s.setInvisible(true);
                    s.setBasePlate(false);
                    s.setGravity(false);
                    s.getEquipment().setItemInMainHand(new ItemStack(Material.BONE));
                    s.addScoreboardTag("remove");
                }));
            }
            new BukkitRunnable() {
                double distance = 1;
                @Override
                public void run() {
                    if(stand.isDead()) {
                        cancel();
                        return;
                    }
                    distance -= 0.015;
                    if(distance <= 0) {
                        cancel();
                        switch (Dungeon.INSTANCE.floor()) {
                            case 0, 1, 2-> Dungeon.INSTANCE.spawnMob(ScaredSkeleton.class, middle.add( new Vector().setX(0.5)), null, Dungeon.MobAttributes.Strict);
                            case 3, 4, 5 -> Dungeon.INSTANCE.spawnMob(Skeletor.class, middle.add( new Vector().setX(0.5)), null, Dungeon.MobAttributes.Strict);
                            case 6 -> Dungeon.INSTANCE.spawnMob((new Random().nextBoolean()) ? Skeletor.class : SkeletorPrime.class, middle.add( new Vector().setX(0.5)), null, Dungeon.MobAttributes.Strict);
                            case 7 -> Dungeon.INSTANCE.spawnMob(SkeletorPrime.class, middle.add( new Vector().setX(0.5)), null, Dungeon.MobAttributes.Strict);
                        }
                        return;
                    }
                    int i = 0;
                    for (int yaw : new ArrayList<>(yaws)) {
                        yaw += 25;
                        yaws.set(i, yaw);
                        ArmorStand stand = bones.get(i);
                        Location newLoc = middle.clone().add(new Vector(distance, (stand.getLocation().getY() - middle.getY()) + 0.015, 0).rotateAroundY(Math.toRadians(yaw)));
                        newLoc.setYaw(-yaw);
                        stand.teleport(newLoc);
                        i++;
                    }
                }

                @Override
                public synchronized void cancel() throws IllegalStateException {
                    super.cancel();
                    if(!stand.isDead()) stand.remove();
                    if(!stand2.isDead()) stand2.remove();
                    if(!stand3.isDead()) stand3.remove();
                    bones.forEach(Entity::remove);
                }
            }.runTaskTimer(Main.getMain(), 0, 1);
        } else skeletorSkulls.add(stand);
    }
    private Location getTriangleCenter(Location l1, Location l2, Location l3) {
        Assert.state(l1.getWorld().equals(l2.getWorld()) && l2.getWorld().equals(l3.getWorld()), "Not the same world!");
        return new Location( l1.getWorld(), (l1.getX() + l2.getX() + l3.getX()) / 3, (l1.getY() + l2.getY() + l3.getY()) / 3, (l1.getZ() + l2.getZ() + l3.getZ()) / 3);
    }
}
