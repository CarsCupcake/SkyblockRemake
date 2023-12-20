package me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.mobs;

import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.GetTotalStatEvent;
import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.NPC.disguise.PlayerDisguise;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.MythologicalPerk;
import me.CarsCupcake.SkyblockRemake.utils.Pair;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import me.CarsCupcake.SkyblockRemake.utils.runnable.EntityRunnable;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class MinosInquisitor extends SkyblockEntity implements Listener {
    public static final Set<MinosInquisitor> spawnedEntitys = new HashSet<>();
    private static final String texture = "ewogICJ0aW1lc3RhbXAiIDogMTY0OTAxNTQwNDgxOCwKICAicHJvZmlsZUlkIiA6ICIzYTdhMDVjMDc0MTI0N2Q2YWVmMDMzMDNkOWNlMjMzNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJzcXJ0IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2YzNWZiOGNmMmE1YjAyNmRhOTNhMWQ2Y2FhNDdmNmRlN2Y5YWRmZTM0Y2FlMGEyOTBiMzczZWFiNjJjY2E3MzAiCiAgICB9CiAgfQp9";
    private static final String signature = "lEd/XCWAIzJHfRowetK/KPlNnOHO5GORhrUrdBoE0nAQdhrl9mPVw5JfltwR8veHDdmtXuZxT0PMlhZH0kEGrwlQF8vNCcASa37CDW0pJ5FUIfdZJunMYMl8IDus/2RajJf81LWr8GddnZo0tZ9OuMLLb/MXovT6gj1VDrwTv6cAjMBdF7CK6jMgdZjRsaY3cd21abVSvBuX0C9y/+3WcCNDqLPA0ARHaAaqkvJo8JVTEGRvmF3/6+X/ksIVEqFQsGdkOpnW5Ug1cGKjLyNJN5ae3hwTC2PI/F836iaVxOqCvB7bmlqYJe7odtuV0INalkJxlo8oGfysjAHazlx55Sha8Ig4qRqVqbUgGKBwPmUV/DT/U0Bxb/1RbeTstQJxCpPnbz/Tc0xbeS+Cw0bisHGbc+Abk1tI19m5JJ+SFdESEATiFRdttZ+5pemP1bWaaKD3BIFjON0dIOL0ioZlN/ZVS2wmt5uL52aXb40CvCBeL716iOcuHqxLdA1QBwldndx1Yiv1Wzk8lrNTCGBjZ6dNTcoG8w6sPYVqgfPt1Q7vfDSXD100sVsjbKHU3KKIWjaMD8jZT/dn8V5GVKurySyiJQ3YvOSrXuUupS52xNXJ3hoeAS7gCpdMwGDhmLjmSkSnLF4LltGM9DaxmnBkDEp+GYueDgc1U2InAWMarkk=";
    private final MythologicalPerk perk;
    public final Set<SkyblockPlayer> affectedPlayers = new HashSet<>();
    private double damageMult = 0d;
    public MinosInquisitor(MythologicalPerk perk) {
        this.perk = perk;
    }
    @Override
    public int getMaxHealth() {
        return 40_000_000;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }
    private LivingEntity entity;
    private ArmorStand damageStand;
    @Override
    public void spawn(Location loc) {
        entity = loc.getWorld().spawn(loc, Zombie.class, zombie -> zombie.getEquipment().setItemInMainHand(new ItemStack(Material.ARMOR_STAND)));
        new PlayerDisguise(entity, texture, signature);
        damageStand = entity.getWorld().spawn(loc.add(0,0.7,0), ArmorStand.class, s ->{
            s.setGravity(false);
            s.setInvisible(true);
            s.setInvulnerable(true);
            s.setCustomNameVisible(true);
            s.addScoreboardTag("remove");
        });
        updateTag();
        spawnedEntitys.add(this);
        new EntityRunnable() {
            int i = 0;
            @Override
            public void run() {
                damageStand.teleport(entity.getLocation().add(0,0.7,0));
                i++;
                if (i == 20) {
                    if (damageMult == 600) {
                        kill();
                        entity.remove();
                        if (perk != null) perk.kill(MinosInquisitor.this);
                    }
                    i = 0;
                     Bukkit.getOnlinePlayers().stream().filter(player -> player.getLocation().distance(entity.getLocation()) <= 7.5).map(SkyblockPlayer::getSkyblockPlayer)
                            .forEach(player -> {
                                affectedPlayers.add(player);
                                Calculator calculator = new Calculator();
                                calculator.entityToPlayerDamage(MinosInquisitor.this, player, new Pair<>(250, 0));
                                calculator.damagePlayer(player);
                                calculator.showDamageTag(player);
                            });
                     damageMult += 8;
                    updateTag();

                }
            }
        }.runTaskTimer(this, 1, 1);
    }
    public void updateTag() {
        damageStand.setCustomName("§c+" + Tools.cleanDouble(damageMult) + "% damage");
    }

    @Override
    public String getName() {
        return "§2Minos Inquisitor";
    }

    @Override
    public boolean hasNoKB() {
        return false;
    }

    @Override
    public int getDamage() {
        return 850;
    }

    @Override
    public void kill() {
        super.kill();
        if (perk != null) perk.kill(this);
        spawnedEntitys.remove(this);
        affectedPlayers.clear();
        damageStand.remove();
    }

    @Override
    protected NametagType nametagType() {
        return NametagType.SmallNumber;
    }

    @Override
    public int getXpDrop() {
        return 100;
    }

    @EventHandler
    public void onStatGet(GetTotalStatEvent event) {
        if (event.getStat() != Stats.Health && event.getStat() != Stats.Inteligence && event.getStat() != Stats.Speed && event.getStat() != Stats.Vitality) return;
        for (MinosInquisitor inquisitor : spawnedEntitys) {
            if (inquisitor.affectedPlayers.contains(event.getPlayer())) {
                event.addMultiplier(0.33);
                return;
            }
        }
    }

    @EventHandler
    public void onDamage(SkyblockDamageEvent event) {
        if (event.getCalculator().getSkyblockEntity() == null) return;
        if (!(event.getCalculator().getSkyblockEntity() instanceof MinosInquisitor champion)) return;
        double mult = (1 + (champion.damageMult / 100d));
        event.getCalculator().damage *= mult;
    }
}
