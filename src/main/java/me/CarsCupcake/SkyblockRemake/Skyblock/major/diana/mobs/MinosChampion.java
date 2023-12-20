package me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.mobs;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.MythologicalPerk;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import me.CarsCupcake.SkyblockRemake.utils.runnable.EntityRunnable;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class MinosChampion extends SkyblockEntity implements Listener {
    private int maxHealth = 1;
    private final int damage;
    private final boolean legendary;
    private double damageMultiplier = 0d;
    private final MythologicalPerk perk;
    public MinosChampion(ItemRarity rarity, MythologicalPerk perk) {
        if (Objects.requireNonNull(rarity) == ItemRarity.LEGENDARY) {
            maxHealth = 12_000_000;
            damage = 3_800;
        } else {
            maxHealth = 2_000_000;
            damage = 2_700;
        }
        health = maxHealth;
        this.legendary = rarity == ItemRarity.LEGENDARY;
        this.perk = perk;
    }
    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public LivingEntity getEntity() {
        return zombie;
    }
    private Zombie zombie;
    private ArmorStand damageStand;

    @Override
    public void spawn(Location loc) {
        zombie = loc.getWorld().spawn(loc, Zombie.class);
        zombie.setCustomNameVisible(true);
        damageStand = zombie.getWorld().spawn(loc.add(0,0.7,0), ArmorStand.class, s ->{
            s.setGravity(false);
            s.setInvisible(true);
            s.setInvulnerable(true);
            s.setCustomNameVisible(true);
            s.addScoreboardTag("remove");
        });
        updateTag();
        new EntityRunnable() {
            int i = 0;
            @Override
            public void run() {
                damageStand.teleport(zombie.getLocation().add(0,0.7,0));
                i++;
                if (i == 20) {
                    i = 0;
                    if (damageMultiplier != 600d) {
                        damageMultiplier += 5d;
                        updateTag();
                    }
                    List<SkyblockPlayer> players = new LinkedList<>();
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (p.getLocation().distance(zombie.getLocation()) <= 7.5)
                            players.add(SkyblockPlayer.getSkyblockPlayer(p));
                    }
                    int damage = (legendary) ? 200 : 400;
                    for (SkyblockPlayer player : players) {
                        Calculator calculator = new Calculator();
                        calculator.entityToPlayerDamage(MinosChampion.this, player, new Bundle<>(damage, 0));
                    }
                }
            }
        }.runTaskTimer(Main.getMain(), 1, 1);
    }


    @Override
    protected NametagType nametagType() {
        return NametagType.SmallNumber;
    }
    public void updateTag() {
        damageStand.setCustomName("§c+" + Tools.cleanDouble(damageMultiplier) + "% damage");
    }

    @Override
    public String getName() {
        return "§2Minos Champion";
    }

    @Override
    public boolean hasNoKB() {
        return false;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getLevel() {
        return (legendary) ? 175 : 300;
    }

    @Override
    public void kill() {
        super.kill();
        damageStand.remove();
        if (perk != null) perk.kill(this);
    }

    @EventHandler
    public void onDamage(SkyblockDamageEvent event) {
        if (event.getCalculator().getSkyblockEntity() == null) return;
        if (!(event.getCalculator().getSkyblockEntity() instanceof MinosChampion champion)) return;
        double mult = (1 + (champion.damageMultiplier / 100d));
        event.getCalculator().damage *= mult;
    }
}
