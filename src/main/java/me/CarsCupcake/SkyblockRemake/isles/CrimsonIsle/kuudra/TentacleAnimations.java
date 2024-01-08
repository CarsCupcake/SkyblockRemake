package me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.kuudra;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.runnable.EntityRunnable;
import me.CarsCupcake.SkyblockRemake.utils.runnable.RunnableWithParam;
import me.CarsCupcake.SkyblockRemake.utils.runnable.SequencedEntityRunnable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.EntityArmorStand;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftArmorStand;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.Vector;

import java.util.Random;

public enum TentacleAnimations implements RunnableWithParam<KuudraTentacle> {
    Idle {
        @Override
        public void run(KuudraTentacle tentacle) {
            new EntityRunnable() {
                private Location idleTarget;
                private Vector targetDir;

                @Override
                public void run() {
                    if (tentacle.getAnimations() != Idle) {
                        cancel();
                        return;
                    }
                    if (idleTarget == null) {
                        idleTarget = nextIdle(tentacle.getEntity().getLocation());
                        targetDir = idleTarget.toVector().subtract(tentacle.getMovementPointer().toVector()).normalize().multiply(0.1);
                    } else if (idleTarget.distance(tentacle.getMovementPointer()) <= 0.2) {
                        tentacle.setMovementPointer(idleTarget);
                        idleTarget = null;
                        return;
                    }
                    tentacle.setMovementPointer(tentacle.getMovementPointer().add(targetDir));
                    if (tentacle.getMovementPointer().getY() <= 123.5) tentacle.setMovementPointer(idleTarget);
                }
            }.runTaskTimer(tentacle, 0, 1);
        }

        private Location nextIdle(Location base) {
            Location next = base.clone();
            next.setY(124);
            Vector v = new Vector(14, 0, 0);
            v.rotateAroundY(Math.toRadians(new Random().nextInt(360)));
            return next.add(v);
        }
    },
    AttackPlayer {
        @Override
        public void run(KuudraTentacle tentacle) {
            Vector dir = tentacle.getTarget().toVector().subtract(tentacle.getEntity().getLocation().toVector());
            dir.setY(0);
            dir.normalize().multiply(60);
            Location taget = tentacle.getEntity().getLocation();
            taget.setY(79);
            taget.add(dir);
            dir = taget.toVector().subtract(tentacle.getMovementPointer().toVector());
            double len = dir.length();
            dir.normalize();
            dir.multiply(len / 20d);
            Vector finalDir = dir;
            new EntityRunnable() {
                int i = 0;

                @Override
                public void run() {
                    if (i == 19) {
                        tentacle.setMovementPointer(taget);
                        cancel();
                        new EntityRunnable() {
                            @Override
                            public void run() {
                                retrect(tentacle, taget);
                            }
                        }.runTaskLater(tentacle, 60);
                        return;
                    }
                    tentacle.setMovementPointer(tentacle.getMovementPointer().add(finalDir));
                    i++;
                }
            }.runTaskTimer(tentacle, 0, 1);
        }

        public void retrect(KuudraTentacle tentacle, Location target) {
            Location base = tentacle.getKuudraLocation().clone();
            base.setY(126);
            Vector dir = base.toVector().subtract(target.toVector());
            double d = dir.length();
            dir.normalize().multiply(d / 20);
            new EntityRunnable() {
                int i = 0;
                final Vector v = dir;

                @Override
                public void run() {
                    if (i >= 19) {
                        tentacle.setMovementPointer(base);
                        cancel();
                        tentacle.setAnimations(Idle);
                        return;
                    }
                    tentacle.setMovementPointer(tentacle.getMovementPointer().add(v));
                    i++;
                }
            }.runTaskTimer(tentacle, 0, 1);
        }
    },
    SpawnMobs {
        @Override
        public void run(KuudraTentacle tentacle) {
            SequencedEntityRunnable sequencedEntityRunnable = new SequencedEntityRunnable(tentacle);
            Location prepLoc = tentacle.getEntity().getLocation().add(tentacle.getEntity().getLocation().toVector().subtract(center.clone()).setY(0).normalize().multiply(14));
            prepLoc.setY(124);
            Vector prepDir = prepLoc.toVector().subtract(tentacle.getMovementPointer().toVector());
            prepDir.multiply(1d / 20);
            sequencedEntityRunnable.addRepeatingRunnable(new Runnable() {
                int i = 0;

                @Override
                public void run() {
                    if (tentacle.getAnimations() != SpawnMobs) {
                        sequencedEntityRunnable.setCancelled();
                        return;
                    }
                    if (i == 19) {
                        sequencedEntityRunnable.next();
                        tentacle.setMovementPointer(prepLoc);
                        tentacle.setMirroredPoints(false);
                        return;
                    }
                    tentacle.setMovementPointer(tentacle.getMovementPointer().add(prepDir));
                    i++;
                }
            }, 1);
            Vector centerDirVec = center.clone().subtract(tentacle.getEntity().getLocation().toVector()).setY(0).normalize().multiply(1);
            sequencedEntityRunnable.addRepeatingRunnable(new Runnable() {
                int i = 0;

                @Override
                public void run() {
                    if (tentacle.getAnimations() != SpawnMobs) {
                        tentacle.setMirroredPoints(true);
                        sequencedEntityRunnable.setCancelled();
                        return;
                    }
                    if (i == 19) {
                        sequencedEntityRunnable.next();
                        for (SkyblockEntity e : tentacle.getSpawnableEntities()) {
                            e.spawn(tentacle.getMovementPointer());
                            e.getEntity().setVelocity(center.clone().subtract(e.getEntity().getLocation().toVector()).setY(3).normalize().multiply(4));
                        }
                        tentacle.getSpawnableEntities().clear();
                        return;
                    }
                    centerDirVec.rotateAroundX(Math.toRadians(90d/20d));
                    tentacle.setMovementPointer(tentacle.getMovementPointer().add(centerDirVec.clone().setY(-1)));
                    i++;
                }
            }, 1);
            Location next = tentacle.getEntity().getLocation();
            next.setY(127);
            Vector v = new Vector(14, 0, 0);
            v.rotateAroundY(Math.toRadians(new Random().nextInt(360)));
            next.add(v);
            sequencedEntityRunnable.addRepeatingRunnable(new Runnable() {
                int i = 0;
                Vector targetDir;
                @Override
                public void run() {
                    if (i == 0) {
                        targetDir = next.toVector().subtract(tentacle.getMovementPointer().toVector()).multiply(1d / 30d);
                    }
                    if (tentacle.getAnimations() != SpawnMobs) {
                        tentacle.setMirroredPoints(true);
                        sequencedEntityRunnable.setCancelled();
                        return;
                    }
                    if (i == 29) {
                        sequencedEntityRunnable.next();
                        tentacle.setMovementPointer(next);
                        tentacle.setMirroredPoints(true);
                        tentacle.setAnimations(Idle);
                        return;
                    }
                    tentacle.setMovementPointer(tentacle.getMovementPointer().add(targetDir));
                    i++;
                }
            }, 1);
            sequencedEntityRunnable.start();
        }
    },
    ThrowPlayerAway {
        @Override
        public void run(KuudraTentacle tentacle) {
            SkyblockPlayer target = tentacle.getPlayerGrabTarget();
            Location targetLocation = target.getEyeLocation();
            Location prepLoc = tentacle.getEntity().getLocation().add(tentacle.getEntity().getLocation().toVector().subtract(targetLocation.toVector()).setY(0).normalize().multiply(14));
            prepLoc.setY(124);
            Vector prepDir = prepLoc.toVector().subtract(tentacle.getMovementPointer().toVector());
            prepDir.multiply(1d / 20);
            CraftArmorStand playerRider = (CraftArmorStand) targetLocation.getWorld().spawn(targetLocation, ArmorStand.class, armorStand -> {
                armorStand.setInvisible(true);
                armorStand.setBasePlate(false);
                armorStand.setAI(false);
                armorStand.addScoreboardTag("remove");
                armorStand.setGravity(false);
            });
            EntityArmorStand nmsArmorStand = playerRider.getHandle();
            SequencedEntityRunnable sequencedEntityRunnable = new SequencedEntityRunnable(tentacle);
            sequencedEntityRunnable.addCancelAction(() -> {
                nmsArmorStand.setRemoved(Entity.RemovalReason.a);
                playerRider.remove();
            });
            sequencedEntityRunnable.addRepeatingRunnable(new Runnable() {
                int i = 0;

                @Override
                public void run() {
                    if (tentacle.getAnimations() != ThrowPlayerAway) {
                        sequencedEntityRunnable.setCancelled();
                        return;
                    }
                    if (i == 19) {
                        sequencedEntityRunnable.next();
                        tentacle.setMovementPointer(prepLoc);
                        tentacle.setMirroredPoints(false);
                        return;
                    }
                    tentacle.setMovementPointer(tentacle.getMovementPointer().add(prepDir));
                    i++;
                }
            }, 1);
            Vector prepToTarget = targetLocation.toVector().subtract(prepLoc.toVector());
            prepToTarget.multiply(1d / 40d);
            sequencedEntityRunnable.addRepeatingRunnable(new Runnable() {
                int i = 0;

                @Override
                public void run() {
                    if (tentacle.getAnimations() != ThrowPlayerAway) {
                        tentacle.setMirroredPoints(true);
                        sequencedEntityRunnable.setCancelled();
                        return;
                    }
                    if (i == 39) {
                        sequencedEntityRunnable.next();
                        tentacle.setMovementPointer(targetLocation);
                        playerRider.addPassenger(target);
                        return;
                    }
                    tentacle.setMovementPointer(tentacle.getMovementPointer().add(prepToTarget));
                    i++;
                }
            }, 1);
            Vector centerDirVec = center.clone().subtract(tentacle.getEntity().getLocation().toVector()).setY(0).normalize().multiply(5);
            Location throwPrepLocation = tentacle.getEntity().getLocation().add(centerDirVec);
            throwPrepLocation.setY(97);
            Vector throwPrepVec = throwPrepLocation.toVector().subtract(targetLocation.toVector());
            throwPrepVec.multiply(1d / 20d);
            sequencedEntityRunnable.addRepeatingRunnable(new Runnable() {
                int i = 0;

                @Override
                public void run() {
                    if (tentacle.getAnimations() != ThrowPlayerAway) {
                        tentacle.setMirroredPoints(true);
                        sequencedEntityRunnable.setCancelled();
                        return;
                    }
                    if (i == 19) {
                        sequencedEntityRunnable.next();
                        playerRider.removePassenger(target);
                        playerRider.remove();
                        target.setVelocity(center.clone().subtract(target.getLocation().toVector()).setY(3).normalize().multiply(5));
                        return;
                    }
                    tentacle.setMovementPointer(tentacle.getMovementPointer().add(throwPrepVec));
                    nmsArmorStand.setPositionRaw(nmsArmorStand.locX() + throwPrepVec.getX(), nmsArmorStand.locY() + throwPrepVec.getY(), nmsArmorStand.locZ() + throwPrepVec.getZ());
                    target.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, target.getLocation(), 1);
                    i++;
                }
            }, 1);
            Location next = tentacle.getEntity().getLocation();
            next.setY(127);
            Vector v = new Vector(14, 0, 0);
            v.rotateAroundY(Math.toRadians(new Random().nextInt(360)));
            next.add(v);
            sequencedEntityRunnable.addRepeatingRunnable(new Runnable() {
                int i = 0;
                Vector targetDir;

                @Override
                public void run() {
                    if (i == 0) {
                        targetDir = next.toVector().subtract(tentacle.getMovementPointer().toVector()).multiply(1d / 30d);
                    }
                    if (tentacle.getAnimations() != ThrowPlayerAway) {
                        tentacle.setMirroredPoints(true);
                        sequencedEntityRunnable.setCancelled();
                        return;
                    }
                    if (i == 29) {
                        sequencedEntityRunnable.next();
                        tentacle.setMovementPointer(next);
                        tentacle.setMirroredPoints(true);
                        tentacle.setAnimations(Idle);
                        return;
                    }
                    tentacle.setMovementPointer(tentacle.getMovementPointer().add(targetDir));
                    i++;
                }
            }, 1);
            sequencedEntityRunnable.start();
        }
    };
    private static final Vector center = new Vector(-100.5, 79, -108.5);
}
