package me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.mobs;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.NPC.disguise.PlayerDisguise;
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
    private static final String texture = "ewogICJ0aW1lc3RhbXAiIDogMTYyNjcxNDUzNDIxNSwKICAicHJvZmlsZUlkIiA6ICIwNWQ0NTNiZWE0N2Y0MThiOWI2ZDUzODg0MWQxMDY2MCIsCiAgInByb2ZpbGVOYW1lIiA6ICJFY2hvcnJhIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2VlZmNkMTNkNTg3N2VlNGVkZDgzYTM0MjA4ODUyMGE5Y2YyMjY1NDg0YmZiOTNhYTEzNWNhY2Q2NTFiNGZlODUiCiAgICB9CiAgfQp9";
    private static final String signature = "qHoo6mQbM8JCg9gUat1v4D3u6poe/Qrt6pye7mD8OpAhPL4dhLV6obtsHa8MKNJfaoGy0ouSp8WDGrWtrE8VpgbZro33oP2sqTjPbyT9Lw/sZl5NLaajriKLbKbtpM+sk2/HujF36tzvPmIG8ABgI4gD8TLOJ20mRtR68hckYn8Bq/iOJnQuwU3uXStpgPYT1zKIaE+v3jxH8CFnTh2ONUHATQwt321eqD5FMdzSAc6bw2aMv1lVADv3PrUwH2QdqFbbBJwRZwvXsiKXfoINKAo8SldpKHQ7VUYzD4gc8WXt1RM7zSor0ZSojsNhVDKz0dOqZ1M0Fx6Uf+hcBTxAvO6djYXd+Cr+dce436/zba09/MWwh3UQBhlAWZY7h1eL9eT3LjsmsfHG0NuQyezemwZJIw7YIrP+ROtawhnN5zvwYpBjGxkhGz8huRab+VDfQxqnjxkFico87HwlvuHtR1y7Tm3VcbH0senSQ/mjNZ+LZ8tZ8hFbLtlXdn4JZCY1lDJTYCxU1THUmp8BwYnRA3INpAjwEcSbutUnET+7aBt3t+iCjVduaBM6O1OjNPmpSUfe6684gkCbfnnrRdsQ9HTjb+Qjiq4/u+vkpHb07TpTPcxVhg8hVt6+oGDIP11HTmORjq0hWJo8Yy47j1HcWUgMaKxU6AG+rDQRb/evQo4=";
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
        zombie = loc.getWorld().spawn(loc, Zombie.class, Zombie::setAdult);
        zombie.setCustomNameVisible(true);
        new PlayerDisguise(zombie, texture, signature);
        SkyblockEntity.livingEntity.addEntity(zombie, this);
        damageStand = zombie.getWorld().spawn(loc.add(0,0.15,0), ArmorStand.class, s ->{
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
                damageStand.teleport(zombie.getLocation().add(0,0.15,0));
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
    protected NametagType getNametagType() {
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
