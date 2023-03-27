package me.CarsCupcake.SkyblockRemake.Skyblock;

import lombok.Getter;
import lombok.Setter;
import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.API.CalculatorException;
import me.CarsCupcake.SkyblockRemake.API.HealthChangeReason;
import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.DamagePrepairEvent;
import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.SkyblockDamagePlayerToEntityExecuteEvent;
import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.SkyblockRemakeEvents;
import me.CarsCupcake.SkyblockRemake.abilitys.Ferocity;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class Calculator {
    public boolean isCrit = false;
    private boolean isCanceled = false;
    public int cccalc = 0;
    public double damage = 0;
    @Getter
    private SkyblockDamageEvent.DamageType type;
    private LivingEntity e;
    private Projectile projectile;
    private SkyblockDamageEvent result;
    private boolean isMagic = false;
    private String abilityName;
    private double abilityScaling = 1;
    private boolean isFerocity = false;
    @Getter
    @Setter
    private boolean overload = false;
    @Setter
    private boolean applyFerocity = true;

    public Calculator() {
    }

    public Calculator(boolean setFerocity) {
        isFerocity = setFerocity;
    }

    public Calculator(Projectile p) {
        projectile = p;
    }

    public boolean isFerocity() {
        return isFerocity;
    }

    public void setFerocity(boolean b) {
        isFerocity = b;
    }

    public double playerToEntityDamage(LivingEntity e, SkyblockPlayer player) {
        return playerToEntityDamage(e, player, new Bundle<>(1d, 1d));
    }

    public double playerToEntityDamage(LivingEntity e, SkyblockPlayer player, Bundle<Double, Double> multipyers) {
        return playerToEntityDamage(e, player, new HashMap<>(), multipyers, true);
    }

    public double playerToEntityDamage(LivingEntity e, SkyblockPlayer player, HashMap<Stats, Double> stats, boolean fillMissing) {
        return playerToEntityDamage(e, player, stats, new Bundle<>(1d, 1d), fillMissing);
    }

    public HashMap<Stats, Double> getIfMissing(HashMap<Stats, Double> stats, SkyblockPlayer player) {
        if (!stats.containsKey(Stats.Strength)) stats.put(Stats.Strength, Main.getPlayerStat(player, Stats.Strength));

        if (!stats.containsKey(Stats.CritChance)) stats.put(Stats.CritChance, Main.getPlayerStat(player, Stats.CritChance));

        if (!stats.containsKey(Stats.CritDamage)) stats.put(Stats.CritDamage, Main.getPlayerStat(player, Stats.CritDamage));

        return stats;
    }

    public HashMap<Stats, Double> fillWithNull(HashMap<Stats, Double> stats) {
        if (!stats.containsKey(Stats.Strength)) stats.put(Stats.Strength, 0d);

        if (!stats.containsKey(Stats.CritChance)) stats.put(Stats.CritChance, 0d);

        if (!stats.containsKey(Stats.CritDamage)) stats.put(Stats.CritDamage, 0d);

        return stats;
    }

    public double playerToEntityDamage(LivingEntity e, SkyblockPlayer player, double weapondamage) {
        return playerToEntityDamage(e, player, new HashMap<>(), Main.weapondamage(player.getItemInHand()), new Bundle<>(1d, 1d), true);
    }

    public double playerToEntityDamage(LivingEntity e, SkyblockPlayer player, HashMap<Stats, Double> stats, Bundle<Double, Double> multipliers, boolean fillMissing) {
        return playerToEntityDamage(e, player, stats, Main.weapondamage(player.getItemInHand()), multipliers, fillMissing);
    }

    public double playerToEntityDamage(LivingEntity e, SkyblockPlayer player, HashMap<Stats, Double> stats, double weapondamage, Bundle<Double, Double> multipliers, boolean fillMissing) {
        this.e = e;
        if (e.getScoreboardTags().contains("npc")) return 0d;
        type = SkyblockDamageEvent.DamageType.PlayerToEntity;

        double weapondmg = weapondamage;
        weapondmg *= player.getRawDamageMult();
        if (fillMissing) stats = getIfMissing(stats, player);
        else stats = fillWithNull(stats);
        DamagePrepairEvent event = new DamagePrepairEvent(player, e, this, multipliers.getFirst(), multipliers.getLast(), stats, weapondamage);
        event.addPreMultiplier(SkyblockPlayer.getSkyblockPlayer(player).getAdititveMultiplier() - 1);
        Bukkit.getPluginManager().callEvent(event);
        weapondmg = event.getWeaponDamage();
        stats = event.getStats();
        double stre = stats.get(Stats.Strength);
        double cd = stats.get(Stats.CritDamage);
        double cc = stats.get(Stats.CritChance);
        cccalc = (int) (Math.random() * 100 + 1);

        double preMultiplier = event.getPreMultiplier();
        double postMult = event.getPostMultiplier();

        double damage;
        if (cccalc <= cc) {
            isCrit = true;
            damage = (5 + (float) weapondmg) * (1 + ((float) stre / 100)) * (1 + ((float) cd / 100)) * (1 + (preMultiplier)) * (1 + (postMult));

        } else {
            damage = (5 + (float) weapondmg) * (1 + ((float) stre / 100)) * (1 + (preMultiplier)) * (1 + (postMult));
        }
        if (SkyblockEntity.livingEntity.containsKey(e)) {
            SkyblockEntity entity = SkyblockEntity.livingEntity.get(e);
            if (entity instanceof Defensive ed) {
                double defense = ed.getDefense();
                double ehp = entity.getMaxHealth() * (1 + (defense / 100));
                double effectivedmg = entity.getMaxHealth() / ehp;
                damage *= effectivedmg;
            }
        }
        this.damage = damage;
        return damage;
    }


    //bundle has Damage - True Damage
    public void entityToPlayerDamage(SkyblockEntity entity, SkyblockPlayer player) {
        entityToPlayerDamage(entity, player, new Bundle<>(entity.getDamage(), entity.getTrueDamage()));
    }

    public void entityToPlayerDamage(SkyblockEntity entity, SkyblockPlayer player, Bundle<Integer, Integer> stats) {
        if (entity != null) e = entity.getEntity();
        type = SkyblockDamageEvent.DamageType.EntityToPlayer;
        double damage = stats.getFirst();
        double health = Main.getPlayerStat(player, Stats.Health);
        double defense = Main.getPlayerStat(player, Stats.Defense);
        float ehp = (float) health  * (1 + ((float) defense/ 100));
        float effectivedmg = (float) health / ehp;
        int totaldmg = (int) ((int) damage * effectivedmg);


        int truedamage = stats.getLast();
        if (truedamage != 0) {
            float trueehp = (float) health * (1 + ((float) Main.getPlayerStat(player, Stats.TrueDefense) / 100));
            float effectivetruedmg = (float) health/ trueehp;
            totaldmg += (int) (truedamage * effectivetruedmg);
        }
        this.damage = totaldmg;
    }

    public void damagePlayer(SkyblockPlayer player) {
        damagePlayer(player, EntityDamageEvent.DamageCause.CUSTOM);
    }

    public void damagePlayer(SkyblockPlayer player, EntityDamageEvent.DamageCause cause) {

        if (projectile == null) result = new SkyblockDamageEvent(player, e, this, type, cause);
        else result = new SkyblockDamageEvent(player, e, this, type, cause, projectile);
        Bukkit.getPluginManager().callEvent(result);

        if (result.isCancelled()) return;

        if (damage > 0) player.damage(0.0001);

        if (Main.absorbtion.get(player) - damage < 0) {
            float restdamage = (float) damage - (float) Main.absorbtion.get(player);
            Main.absorbtion.replace(player, 0);
            player.setHealth(player.currhealth - (int) restdamage, HealthChangeReason.Damage);
        } else {
            Main.absorbtion.replace(player, Main.absorbtion.get(player) - (int) damage);
        }


        Main.updatebar(player);
    }

    public void damageEntity(LivingEntity e, SkyblockPlayer player) {
        damageEntity(e, player, EntityDamageEvent.DamageCause.CUSTOM);
    }

    public void damageEntity(LivingEntity e, SkyblockPlayer player, EntityDamageEvent.DamageCause cause) {
        if (Main.entitydead.containsKey(e)) return;
        if (e.getScoreboardTags().contains("npc")) return;
        if (projectile == null) result = new SkyblockDamageEvent(player, e, this, type, cause);
        else result = new SkyblockDamageEvent(player, e, this, type, cause, projectile);
        Bukkit.getPluginManager().callEvent(result);

        isCanceled = result.isCancelled();
        if (result.isCancelled()) return;


        if (e.getScoreboardTags().contains("invinc")) {
            return;
        }
        int newHealth;
        if (SkyblockEntity.livingEntity.containsKey(e)) {
            SkyblockEntity se = SkyblockEntity.livingEntity.get(e);
            if (se instanceof FinalDamageDesider desider) damage = desider.getFinalDamage(player, damage);

            se.damage(damage, SkyblockPlayer.getSkyblockPlayer(player));
            newHealth = se.getHealth();


        } else {
            int live = Main.currentityhealth.get(e) - (int) damage;
            Main.currentityhealth.replace(e, live);
            newHealth = Main.currentityhealth.get(e);
        }

        e.damage(0.00001, player);
        if (SkyblockEntity.livingEntity.containsKey(e) && SkyblockEntity.livingEntity.get(e).hasNoKB())
            e.setVelocity(new Vector(0, 0, 0));

        SkyblockDamagePlayerToEntityExecuteEvent event = new SkyblockDamagePlayerToEntityExecuteEvent(player, e, this);
        Bukkit.getPluginManager().callEvent(event);


        if (newHealth <= 0) e.addScoreboardTag("killer:" + player.getName());
        else Main.updateentitystats(e);
        double ferocity = Main.getPlayerStat(player, Stats.Ferocity);
        if (!isMagic && !isFerocity && projectile == null && applyFerocity) if (ferocity > 0) {
            if (ferocity < 100) {
                Random r = new Random();
                int low = 1;//includes 1
                int high = 100;// includes 100
                int result = r.nextInt(high - low) + low;
                if (ferocity >= result) {

                    Ferocity.hit(e, damage, cccalc <= Main.getPlayerStat(player, Stats.CritChance), player);
                    Main.updateentitystats(e);
                }
            } else {
                double hits = (double) ferocity / 100;
                if (hits % 1 == 0) {

                    SkyblockRemakeEvents.ferocity_call(e, damage, cccalc, (int) Main.getPlayerStat(player, Stats.CritChance), player, (int) hits);


                } else {
                    int minus = ((int) hits * 100);
                    double hitchance = (double) ferocity - (double) minus;

                    Random r = new Random();
                    int low = 1;//includes 1
                    int high = 100;// includes 100
                    int result = r.nextInt(high - low) + low;

                    if (hitchance >= result) {
                        hits = hits + 1;
                    }
                    SkyblockRemakeEvents.ferocity_call(e, damage, cccalc, (int) Main.getPlayerStat(player, Stats.CritChance), player, (int) hits);
                }
            }
        }


        if ((SkyblockEntity.livingEntity.containsKey(e) && SkyblockEntity.livingEntity.get(e).getHealth() <= 0) || (Main.currentityhealth.containsKey(e) && Main.currentityhealth.get(e) <= 0)) {
            e.addScoreboardTag("killer:" + player.getName());
            Main.EntityDeath(e);
            e.damage(9999999, player);
            if (e instanceof EnderDragon) e.setHealth(0);

            SkyblockEntity.livingEntity.remove(e);

            if (e.getScoreboardTags() != null) {
                Set<String> scores = e.getScoreboardTags();
                ArrayList<Player> owners = new ArrayList<>();
                scores.forEach(tag -> {


                });

                if (owners != null) {
                    owners.forEach(owner -> e.addScoreboardTag("killer:" + owner.getName()));
                }
            }

            if (!e.getScoreboardTags().contains("killer")) {
                e.addScoreboardTag("killer:" + player.getName());
            }
            if (projectile != null) {
                e.addScoreboardTag("arrowkill:" + player.getName());


            }
            if (isMagic) e.addScoreboardTag("abilitykill");
            Main.updateentitystats(e);
        }


    }

    public void showDamageTag(Entity e) {
        Location loc = new Location(e.getWorld(), e.getLocation().getX(), e.getLocation().getY() + 0.7, e.getLocation().getZ());
        showDamageTag(loc);

    }

    public void showDamageTag(Location loc) {
        if (isCanceled) return;
        if (result != null && result.isCancelled()) return;
        loc = loc.clone().add(new Random().nextDouble(0.4) - 0.2, new Random().nextDouble(0.4) - 0.2, new Random().nextDouble(0.4) - 0.2);
        final String str = String.format("%.0f", (Tools.round(damage, 0)));


        ArmorStand stand = loc.getWorld().spawn(loc, ArmorStand.class, armorstand -> {
            armorstand.setVisible(false);

            armorstand.setGravity(false);
            armorstand.setMarker(true);


            armorstand.setCustomNameVisible(true);

            armorstand.setInvulnerable(true);
            if (isCrit) {
                StringBuilder name = new StringBuilder("§f" + ((isOverload()) ? "✯" : "✧"));
                String num = "" + str;
                int col = 1;
                int coltype = 1;
                String colstr = "§f";

                for (char x : num.toCharArray()) {
                    name.append(colstr).append(x);
                    ++col;
                    if (col == 2) {
                        col = 0;
                        ++coltype;
                        switch (coltype) {
                            case 1 -> colstr = "§f";
                            case 2 -> colstr = "§e";
                            case 3 -> {
                                colstr = "§6";
                                coltype = 0;
                            }
                        }

                    }
                }
                String x = ((isOverload()) ? "✯" : "✧");
                name.append(colstr).append(x);
                armorstand.setCustomName(name.toString());
            } else armorstand.setCustomName("§7" + str);

            armorstand.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 999999));
            armorstand.addScoreboardTag("damage_tag");
            armorstand.setArms(false);

            armorstand.setBasePlate(false);
        });

        Main.getMain().killarmorstand(stand);

    }

    public void setProjectile(Projectile p) {
        projectile = p;
    }

    public void playerToEntityMagicDamage(SkyblockPlayer player, @Nullable LivingEntity e, double magicDamage) {
        this.e = e;
        if (e != null && e.getScoreboardTags().contains("npc")) return;

        if (e != null && e.getScoreboardTags().contains("abilityimun")) return;

        double abilityDamage = Main.getPlayerStat(player, Stats.AbilityDamage);
        double inteligens =Main.getPlayerStat(player, Stats.Inteligence);

        double baseMult = 0;
        damage = magicDamage * (1 + (inteligens / 100) * abilityScaling) * (1 + (baseMult / 100)) * (1 + (abilityDamage / 100));

    }

    public void setMagic(String str) {
        isMagic = true;
        abilityName = str;
    }

    public void setMagic(String str, double abilityScaling) {
        isMagic = true;
        abilityName = str;
        this.abilityScaling = abilityScaling;
    }

    public void sendMagicMessage(int entityAmount, SkyblockPlayer player) {
        if (!isMagic) throw new CalculatorException("There is no magic");
        player.sendMessage("§7Your " + abilityName + " hit §c" + entityAmount + "§7 enemy" + ((entityAmount > 1) ? "s" : "") + " for §c" + String.format("%.0f", damage) + " §7damage.");
    }

    public boolean isMagic() {
        return isMagic;
    }

    private int getSkyblockEntityHealth(LivingEntity e) {
        return SkyblockEntity.livingEntity.get(e).getHealth();
    }

    private int getBaseEntity(LivingEntity e) {
        return Main.currentityhealth.get(e);
    }

    public SkyblockDamageEvent getResult() {
        return result;
    }

    public Projectile getProjectile() {
        return projectile;
    }

}
