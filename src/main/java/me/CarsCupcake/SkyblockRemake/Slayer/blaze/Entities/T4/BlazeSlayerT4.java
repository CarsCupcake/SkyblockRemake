package me.CarsCupcake.SkyblockRemake.Slayer.blaze.Entities.T4;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.API.HellionShield;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.*;
import me.CarsCupcake.SkyblockRemake.Slayer.blaze.Entities.FirePillar;
import me.CarsCupcake.SkyblockRemake.Slayer.blaze.Entities.FirePits;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public class BlazeSlayerT4 extends Slayer implements FinalDamageDesider, FirePillar.PillarThrower, FirePits.FirePitExecuter {
    private LivingEntity entity;
    public SkyblockPlayer owner;
    private BukkitRunnable run;
    private int ticks = 0;
    public int time = 260;
    private ArmorStand stand;
    private boolean demonsplitHasActivated = false;
    private int demonsplitPhase = 0;
    private boolean pillarActive = false;
    @Getter
    private boolean quaziiAlive = false;
    @Getter
    private boolean typhoeusAlive = false;

    private boolean isInvincible = false;

    private BukkitRunnable teleportRunnable;
    @Getter
    private QuaziiT4 quazii;
    @Getter
    private TyphoesT4 typhoes;

    private BukkitRunnable aoeRunner;
    private FirePits firePit;
    private HellionShield shield = HellionShield.Ashen;
    private int hits = 8;

    public BlazeSlayerT4(SkyblockPlayer player) {
        super(player);
    }

    @Override
    public int getMaxHealth() {
        return 150000000;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public void spawn(Location loc) {
        entity = loc.getWorld().spawn(loc, Blaze.class, blaze -> {
            blaze.setCustomNameVisible(true);
            blaze.addScoreboardTag("combatxp:200");
            blaze.addScoreboardTag("abilityimun");
            blaze.setRemoveWhenFarAway(false);
        });
        startAoe();
        SkyblockEntity.livingEntity.addEntity(entity, this);

        stand = entity.getWorld().spawn(loc, ArmorStand.class, s -> {
            s.setGravity(false);
            s.setInvisible(true);
            s.setInvulnerable(true);
            s.setCustomNameVisible(true);
            s.setCustomName(shield.getName() + " " + hits + " §c" + shortInteger(time));
        });
        timeTag();
        Main.updateentitystats(entity);

    }

    public void setOwner(SkyblockPlayer player) {
        owner = player;
    }


    @Override
    public String getName() {
        return "Inferno Demonlord";
    }

    @Override
    public void updateNameTag() {
        String name = "Inferno Demonlord";
        if (health > 999) {
            if (health > 9999) {
                if (health > 999999) {
                    if (health > 9999999) {

                        entity.setCustomName("§c" + Character.toChars(9760)[0] + " §b" + name + " §a" + health / 1000000 + "m§c§?§");

                    } else {

                        entity.setCustomName("§c" + Character.toChars(9760)[0] + " §b" + name + " §a" + Tools.round((float) ((float) health / 1000000), 1) + "m§c§?§");

                    }
                } else {

                    entity.setCustomName("§c" + Character.toChars(9760)[0] + " §b" + name + " §a" + health / 1000 + "k§c§?§");
                    ;

                }
            } else {

                entity.setCustomName("§c" + Character.toChars(9760)[0] + " §b" + name + " §a" + Tools.round(((float) health / 1000), 1) + "k§c§?§");

            }
        } else entity.setCustomName("§c" + Character.toChars(9760)[0] + " §b" + name + " §a" + health + "§c§?§");

    }

    @Override
    public void kill() {
        super.kill();
        run.cancel();
        stand.remove();
        aoeRunner.cancel();
        if (firePit != null) firePit.remove();

    }

    private void timeTag() {
        run = new BukkitRunnable() {

            @Override
            public void run() {
                ticks += 1;
                if (ticks == 20) {
                    ticks = 0;
                    time -= 1;
                    if (time == 0) {
                        kill();
                        entity.remove();
                        if (stand != null) stand.remove();
                        if (quaziiAlive) {
                            quazii.kill();
                            quazii.getEntity().remove();
                        }
                        if (typhoeusAlive) {
                            typhoes.kill();
                            typhoes.getEntity().remove();
                        }
                        cancel();
                        return;
                    } else {
                        if (stand != null)
                            stand.setCustomName(shield.getName() + " " + hits + " §c" + shortInteger(time));
                        if (quaziiAlive) quazii.updateTimer();
                        if (typhoeusAlive) typhoes.updateTimer();
                    }
                }
                if (stand != null) stand.teleport(entity.getLocation().add(0, 0.25, 0));

            }
        };
        run.runTaskTimer(Main.getMain(), 1, 1);
    }


    public void demonsplit() {
        if (!demonsplitHasActivated && health > 0) {
            if ((health <= getMaxHealth() * 0.66 && demonsplitPhase == 0) || (health <= getMaxHealth() * 0.33 && demonsplitPhase == 1)) {
                demonsplitHasActivated = true;
                isInvincible = true;
                if (demonsplitPhase == 1) new FirePits(BlazeSlayerT4.this, owner, false);

                entity.getLocation().getWorld().spawnParticle(Particle.EXPLOSION_HUGE, entity.getLocation(), 0, 0, 0, 2, 0, null);
                stayTeleport(entity.getLocation().add(0, 2, 0));
                new BukkitRunnable() {

                    @SuppressWarnings("deprecation")
                    @Override
                    public void run() {
                        Location quazziLoc = entity.getLocation().clone();
                        float quazziYaw = quazziLoc.getYaw();
                        quazziYaw += 90;
                        if (quazziYaw > 180) quazziYaw -= 360;
                        quazziLoc.setYaw(quazziYaw);
                        quazziLoc.setPitch(0);
                        Vector quaziiDir = quazziLoc.getDirection();
                        quaziiDir.multiply(2);
                        quazziLoc = quazziLoc.add(quaziiDir);


                        final ArmorStand quaziiStand = entity.getLocation().getWorld().spawn(quazziLoc, ArmorStand.class, s -> {
                            s.setInvisible(true);
                            s.setInvulnerable(true);
                            s.setGravity(false);

                        });
                        entity.getLocation().getWorld().spawnParticle(Particle.EXPLOSION_HUGE, quaziiStand.getLocation().add(0, 0.5, 0), 0, 0, 0, 5, 0, null);
                        Location typhoeusLoc = entity.getLocation().clone();
                        float typhoeusYaw = typhoeusLoc.getYaw();
                        typhoeusYaw -= 90;
                        if (typhoeusYaw < -180) typhoeusYaw += 360;
                        typhoeusLoc.setYaw(typhoeusYaw);
                        typhoeusLoc.setPitch(0);
                        Vector typhoeusDir = typhoeusLoc.getDirection();
                        typhoeusDir.multiply(2);
                        typhoeusLoc = typhoeusLoc.add(typhoeusDir);
                        final ArmorStand typhoeusStand = entity.getLocation().getWorld().spawn(typhoeusLoc, ArmorStand.class, s -> {
                            s.setInvisible(true);
                            s.setInvulnerable(true);
                            s.setGravity(false);

                        });
                        entity.getLocation().getWorld().spawnParticle(Particle.EXPLOSION_HUGE, typhoeusStand.getLocation().add(0, 0.5, 0), 0, 0, 0, 5, 0, null);

                        QuaziiT4 quazii = new QuaziiT4();
                        quazii.setBlazeSlayer(BlazeSlayerT4.this);
                        quazii.spawn(quazziLoc);
//					quazii.startAttack(SkyblockPlayer.getSkyblockPlayer(owner));
                        quaziiAlive = true;
                        BlazeSlayerT4.this.quazii = quazii;
                        quaziiStand.setPassenger(quazii.getEntity());

                        TyphoesT4 typhoes = new TyphoesT4();
                        typhoes.setBlazeSlayer(BlazeSlayerT4.this);

                        typhoes.spawn(typhoeusLoc);
//					typhoes.startAttack(SkyblockPlayer.getSkyblockPlayer(owner));
                        typhoeusAlive = true;
                        typhoeusStand.setPassenger(typhoes.getEntity());
                        BlazeSlayerT4.this.typhoes = typhoes;
                        boolean bol = new Random().nextBoolean();
                        System.out.println(bol);
                        if (bol) {
                            quazii.setInvinceble(true);
                            typhoes.setInvinceble(false);
                        } else {
                            quazii.setInvinceble(false);
                            typhoes.setInvinceble(true);
                        }
                        aoeRunner.cancel();
                        teleportRunnable.cancel();
                        entity.remove();
                        stand.remove();
                        stand = null;
                        SkyblockEntity.livingEntity.remove(entity);
                        new BukkitRunnable() {

                            @Override
                            public void run() {
                                quaziiStand.remove();
                                typhoeusStand.remove();

                            }
                        }.runTaskLater(Main.getMain(), 40);


                    }
                }.runTaskLater(Main.getMain(), 20 * 3);


            }
        }
    }

    private void stayTeleport(final Location loc) {
        teleportRunnable = new BukkitRunnable() {

            @Override
            public void run() {
                entity.teleport(loc);

            }
        };
        teleportRunnable.runTaskTimer(Main.getMain(), 0, 1);
    }


    public void setQuaziiKilled() {
        quaziiAlive = false;
        if (!typhoeusAlive) {
            summounBlazeSlayerBack(quazii);
        } else typhoes.setInvinceble(false);
    }

    public void setTyphoeusKilled() {
        typhoeusAlive = false;
        if (!quaziiAlive) {
            summounBlazeSlayerBack(typhoes);
        } else quazii.setInvinceble(false);
    }

    private void summounBlazeSlayerBack(SkyblockEntity e) {
        isInvincible = false;
        Location loc = e.getEntity().getLocation();
        loc.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, loc, 0, 0, 0, 1, 0, null);
        entity = loc.getWorld().spawn(loc, Blaze.class, blaze -> {
            blaze.setCustomNameVisible(true);
            blaze.addScoreboardTag("combatxp:50");
            blaze.addScoreboardTag("abilityimun");
            blaze.setRemoveWhenFarAway(false);
        });
        startAoe();
        SkyblockEntity.livingEntity.addEntity(entity, this);

        stand = entity.getWorld().spawn(loc, ArmorStand.class, s -> {
            s.setGravity(false);
            s.setInvisible(true);
            s.setInvulnerable(true);
            s.setCustomNameVisible(true);
            s.setCustomName(shield.getName() + " " + hits + " §c" + shortInteger(time));
        });
        Main.updateentitystats(entity);
        demonsplitHasActivated = false;
        demonsplitPhase++;
        if (demonsplitPhase == 2) {
            new FirePits(BlazeSlayerT4.this, owner, true);
        }

        if (pillarActive) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (entity == null || entity.isDead()) return;
                    new FirePillar(BlazeSlayerT4.this, owner);
                }
            }.runTaskLater(Main.getMain(), 60);
        }
    }


    private String shortInteger(int duration) {
        String string = "";
        int hours = 0;
        int minutes = 0;
        int seconds = 0;
        if (duration / 60 / 60 / 24 >= 1) {
            duration -= duration / 60 / 60 / 24 * 60 * 60 * 24;
        }
        if (duration / 60 / 60 >= 1) {
            hours = duration / 60 / 60;
            duration -= duration / 60 / 60 * 60 * 60;
        }
        if (duration / 60 >= 1) {
            minutes = duration / 60;
            duration -= duration / 60 * 60;
        }
        if (duration >= 1) seconds = duration;
        if (hours == 0) {
            string = string;
        } else {
            if (hours <= 9) {
                string = string + "0" + hours + ":";
            } else {
                string = string + hours + ":";
            }
        }
        if (minutes != 0) {
            if (minutes <= 9) {
                string = string + "0" + minutes + ":";
            } else {
                string = string + minutes + ":";
            }
        }

        if (seconds <= 9) {
            string = string + "0" + seconds;
        } else {
            string += seconds;
        }

        return string;
    }

    @Override
    public boolean hasNoKB() {
        return true;
    }

    @Override
    public void damage(double damage, SkyblockPlayer player) {
        if (isInvincible) return;
        health -= (int) damage;
        if (health > 0) {
            demonsplit();
            if (!pillarActive && health <= getMaxHealth() / 2) {
                pillarActive = true;
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (entity == null || entity.isDead()) return;
                        new FirePillar(BlazeSlayerT4.this, owner);
                    }
                }.runTaskLater(Main.getMain(), 60);
            }

            if (!ItemHandler.hasPDC("attuned", player.getItemInHand(), PersistentDataType.STRING)) {
                player.sendMessage("§cYour hit was reduced by Hellion Shield");
                player.sendMessage("§cStrike using the " + shield.getName() + " §cattunement on your dagger!");
                return;
            }
            HellionShield hellionShield = getShieldFromString(ItemHandler.getPDC("attuned", player.getItemInHand(), PersistentDataType.STRING));
            if (hellionShield != shield) {
                player.sendMessage("§cYour hit was reduced by Hellion Shield");
                player.sendMessage("§cStrike using the " + shield.getName() + " §cattunement on your dagger!");
                return;
            }
            hits--;
            if (hits == 0) {
                hits = 8;
                shield = getNext();
            }
            stand.setCustomName(shield.getName() + " " + hits + " §c" + shortInteger(time));

        }

    }

    @Override
    public int getTrueDamage() {
        if (owner == null) return 100;
        double mult;
        if (getMaxHealth() * 0.33 > health) mult = 0.5;
        else if (getMaxHealth() * 0.66 > health) mult = 0.3;
        else mult = 0.2;
        return (int) (100 + (Main.getPlayerStat(owner, Stats.Health) * mult));
    }

    private void startAoe() {
        aoeRunner = new BukkitRunnable() {

            @Override
            public void run() {
                owner.damage(0.1);
                Calculator c = new Calculator();
                c.entityToPlayerDamage(BlazeSlayerT4.this, owner);
                c.damagePlayer(owner);
                c.showDamageTag(owner);
            }
        };
        aoeRunner.runTaskTimer(Main.getMain(), 20, 20);
    }

    @Override
    public double getFinalDamage(SkyblockPlayer player, double damage) {
        if (!ItemHandler.hasPDC("attuned", player.getItemInHand(), PersistentDataType.STRING)) return damage * 0.01;
        HellionShield hellionShield = getShieldFromString(ItemHandler.getPDC("attuned", player.getItemInHand(), PersistentDataType.STRING));
        if (hellionShield == shield) return damage;

        return 0.01 * damage;
    }

    private HellionShield getShieldFromString(String str) {
        switch (str) {
            case "ashen" -> {
                return HellionShield.Ashen;
            }
            case "auric" -> {
                return HellionShield.Auric;
            }
            case "crystal" -> {
                return HellionShield.Crystal;
            }
            case "spirit" -> {
                return HellionShield.Spirit;
            }
            default -> {

                return null;
            }
        }
    }

    private HellionShield getNext() {
        switch (shield) {
            case Ashen -> {
                return HellionShield.Spirit;
            }
            case Spirit -> {
                return HellionShield.Auric;
            }
            case Auric -> {
                return HellionShield.Crystal;
            }
            default -> {
                return HellionShield.Ashen;
            }
        }
    }

    @Override
    public void setPillarDestroied() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (entity == null || entity.isDead()) return;
                new FirePillar(BlazeSlayerT4.this, owner);
            }
        }.runTaskLater(Main.getMain(), 200);
    }

    @Override
    public void setPillarExploded() {
        kill();
        entity.remove();
        owner.sendMessage("§cYou failed!");
    }

    @Override
    public void finishPit() {
        firePit = null;
        if (entity == null || entity.isDead()) return;
        new BukkitRunnable() {
            @Override
            public void run() {
                if (entity == null || entity.isDead()) return;
                new FirePits(BlazeSlayerT4.this, owner, true);
            }
        }.runTaskLater(Main.getMain(), 60);
    }

    @Override
    public Location getLocation() {
        return entity.getLocation();
    }
}
