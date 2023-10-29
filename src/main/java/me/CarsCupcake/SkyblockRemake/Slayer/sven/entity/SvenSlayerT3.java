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
    public int getDamage() {
        return 180;
    }

    @Override
    public int getTrueDamage() {
        return 50;
    }
    public void removePup(Pup pup) {
        pups.remove(pup);
        if (pups.isEmpty() && !invin)
            runnable.cancel();
    }

    @Override
    public void damage(double damage, SkyblockPlayer player) {
        super.damage(damage, player);
        if (!hasTriggered && (double) getHealth() / (double) getMaxHealth() <= 0.5) {
            //Call the pups!
            getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);
            ArmorStand stand = getEntity().getWorld().spawn(getEntity().getLocation().add(0, 1, 0), ArmorStand.class, armorStand -> {
                armorStand.setInvisible(true);
                armorStand.setBasePlate(false);
                armorStand.setCustomName("§fCall the Pups!");
                armorStand.setCustomNameVisible(true);
                armorStand.setMarker(true);
                armorStand.addScoreboardTag("remove");
            });
            follow(stand);
            getEntity().setAI(false);
            hasTriggered = true;
            invin = true;
            new EntityRunnable() {
                int i = 0;

                @Override
                public void run() {
                    pups.add(makeNew());
                    i++;
                    if (i == 5) {
                        getEntity().setAI(true);
                        invin = false;
                        stand.setCustomName("§3Protected!");
                        cancel();
                        getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(MOVEMENT_SPEED);
                    }
                }
            }.runTaskTimer(this, 5, 10);
        }
    }
    private EntityRunnable runnable;

    private void follow(ArmorStand stand) {
         runnable = new EntityRunnable() {
            @Override
            public void run() {
                stand.teleport(getEntity().getLocation().add(0, 1, 0));
            }

            @Override
            public synchronized void cancel() throws IllegalStateException {
                super.cancel();
                stand.remove();
            }
        };
        runnable.runTaskTimer(this, 0, 1);
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
