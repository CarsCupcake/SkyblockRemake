package me.CarsCupcake.SkyblockRemake.abilitys;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;

/**
 * @author Xrnd#0001
 */
public class UltimatePurge implements AbilityManager<PlayerInteractEvent> {
    private final int duration = 20; // Duration in seconds
    private final int particlesPerSide = 5; // Number of particles per side of the pentagram

    @Override
    public boolean triggerAbility(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        // Play sound and display countdown title to the player
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1f, 1f);
        player.sendTitle(" ", "§cPurging in " + duration + "s", 0, 40, 10); // Modified title settings

        // Start the countdown task
        startCountdown(player);

        return true;
    }


    private void startCountdown(Player player) {
        int taskPeriod = 4; // Particle task period (ticks)

        BukkitTask countdownTask = new BukkitRunnable() {
            private int countdown = duration;
            private double angleOffset = 0;

            @Override
            public void run() {
                if (countdown > 0) {
                    countdown--;

                    // Update the countdown title
                    if (countdown > 0) {
                        player.sendTitle("", "Purging in " + countdown + "s", 0, 40, 10); // Modified title settings
                    } else {
                        player.sendTitle("", "Entities purged!", 0, 40, 10); // Modified title settings
                    }

                    // Display particle effect in a connected pentagram shape
                    Location playerLocation = player.getLocation();
                    double radius = 2.5;
                    double angleIncrement = 2 * Math.PI / particlesPerSide;

                    for (int i = 0; i < particlesPerSide; i++) {
                        double angle = i * angleIncrement + angleOffset;
                        double x = Math.cos(angle) * radius;
                        double z = Math.sin(angle) * radius;
                        Location particleLocation = playerLocation.clone().add(x, 1.5, z);
                        player.spawnParticle(Particle.REDSTONE, particleLocation, 0, new Particle.DustOptions(org.bukkit.Color.RED, 1));
                    }

                    angleOffset += Math.PI / 40; // Adjust the angle offset to create the rotation effect
                } else {
                    // Kill all entities except the player
                    for (Entity entity : player.getWorld().getEntities()) {
                        if (entity != player && entity instanceof LivingEntity) {
                            ((LivingEntity) entity).setHealth(0);
                        }
                    }

                    // Play sound and display completion title to the player
                    player.getWorld().playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1f, 1f);
                    cancel(); // Cancel the countdown task
                }
            }
        }.runTaskTimer(Main.getPlugin(Main.class), 20L, taskPeriod); // Run the task every specified period (ticks)

        // Schedule a task to cancel the countdown task after the countdown ends
        Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), countdownTask::cancel, duration * 20L);
    }
}

