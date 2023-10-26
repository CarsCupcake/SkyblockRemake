package me.CarsCupcake.SkyblockRemake.Slayer.sven.entity;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Skyblock.FinalDamageDesider;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.runnable.EntityRunnable;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ArmorStand;

import java.util.HashSet;
import java.util.Set;

public class SvenSlayerT3 extends SvenSlayerT2 implements FinalDamageDesider {
    @Getter
    private Set<Pup> pups = new HashSet<>();
    private boolean invin = false;
    private boolean hasTriggered = false;

    public SvenSlayerT3(SkyblockPlayer player) {
        super(player);
    }

    @Override
    public int getMaxHealth() {
        return 750_000;
    }

    @Override
    public int getTrueDamage() {
        return 180 + 50;
    }

    @Override
    public void damage(double damage, SkyblockPlayer player) {
        super.damage(damage, player);
        getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);
        ArmorStand stand = getEntity().getWorld().spawn(getEntity().getLocation(), ArmorStand.class, armorStand -> {
            armorStand.setInvisible(true);
            armorStand.setBasePlate(false);
            armorStand.setCustomName("Call the Pups!");
            armorStand.setCustomNameVisible(true);
            armorStand.setMarker(true);
        });
        if (!hasTriggered && (double) getHealth() / (double)  getMaxHealth() <= 0.5) {
            hasTriggered = true;
            //Call the pups!
            invin = true;
            new EntityRunnable() {
                int i = 0;

                @Override
                public void run() {
                    pups.add(makeNew());
                    i++;
                    if (i == 5) {
                        invin = false;
                        stand.setCustomName("§3Protected!");
                        follow(stand);
                        cancel();
                        getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(MOVEMENT_SPEED);
                    }
                }
            }.runTaskTimer(this, 5, 10);
        }
    }

    private void follow(ArmorStand stand) {
        new EntityRunnable() {
            @Override
            public void run() {
                stand.teleport(getEntity().getLocation());
            }

            @Override
            public synchronized void cancel() throws IllegalStateException {
                super.cancel();
                stand.remove();
            }
        }.runTaskTimer(this, 0, 1);
    }

    @Override
    public void kill() {
        super.kill();
        for (Pup p : new HashSet<>(pups)) p.remove();
    }

    protected Pup makeNew() {
        return new PupT3(this);
    }

    @Override
    public double getFinalDamage(SkyblockPlayer player, double damage) {
        return (invin) ? 0 : damage;
    }
}
