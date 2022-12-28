package me.CarsCupcake.SkyblockRemake.End.Dragon;

import me.CarsCupcake.SkyblockRemake.End.Dragon.DragonAi.Loot;
import me.CarsCupcake.SkyblockRemake.End.Dragon.DragonAi.PathFind;
import me.CarsCupcake.SkyblockRemake.End.Dragon.DragonAi.SkyblockDragon;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Defensive;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import net.minecraft.world.entity.EntityTypes;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.boss.CraftBossBar;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.HashMap;

public class StrongDragon extends SkyblockEntity implements Defensive, CustomDragon {
    private int health = 9000000;
    private EnderDragon entity;
    private ArmorStand as;
    private SkyblockDragon dragon;
    private BossBar bar;
    private SkyblockPlayer lastHit;
    @Override
    public double getDefense() {
        return 25;
    }

    @Override
    public int getMaxHealth() {
        return 9000000;
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
        return 1100;
    }

    @Override
    public void spawn(Location loc) {
        /*entity = loc.getWorld().spawn(loc, EnderDragon.class, enderDragon -> {
            enderDragon.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(1.8);
            enderDragon.addScoreboardTag("combatxp:300");
            enderDragon.
        });*/
        dragon = new SkyblockDragon(EntityTypes.v, ((CraftWorld)loc.getWorld()).getHandle());
        dragon.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());

        StartFight.maxDragHealth = getMaxHealth();
        StartFight.dragonHealth = getHealth();
        ((CraftWorld) loc.getWorld()).getHandle().addEntity(dragon);

        dragon.getBukkitEntity().setMetadata(getName(), new FixedMetadataValue(Main.getMain(), this));
        entity = (EnderDragon) dragon.getBukkitEntity();
        as = loc.getWorld().spawn(loc, ArmorStand.class, armorStand -> {
            armorStand.setVisible(false);
            armorStand.setGravity(false);
            armorStand.setCustomName("follower");
            armorStand.setCustomNameVisible(false);
        });
        bar = new CraftBossBar("§c" + getName(), BarColor.RED, BarStyle.SOLID);
        for (Player p : Bukkit.getOnlinePlayers())
            bar.addPlayer(p);
        PathFind.getDragDirection(dragon, as, 1.4);
        SkyblockEntity.livingEntity.put(entity, this);
    }


    @Override
    public String getName() {
        return "Strong Dragon";
    }

    @Override
    public HashMap<ItemManager, Integer> getDrops(SkyblockPlayer player) {
        return null;
    }

    @Override
    public void updateNameTag() {
        entity.setCustomName(getName());
        bar.setProgress((double) getHealth() /(double) getMaxHealth());
    }

    @Override
    public void kill() {
        as.remove();
        bar.removeAll();
        Loot.dragonDownMessage(dragon, lastHit, entity.getLocation());
    }

    @Override
    public void damage(double damage, SkyblockPlayer player) {
        health -= damage;
        StartFight.playerDMG.put(player, StartFight.playerDMG.getOrDefault(player, 0d) + damage);
        Loot.damage.put(player, Loot.damage.getOrDefault(player, 0d) + damage);
        lastHit = player;
    }

    @Override
    public boolean hasNoKB() {
        return true;
    }

    @Override
    public int getTrueDamage() {
        return 0;
    }

    @Override
    public SkyblockDragon getSkyblockDragon() {
        return dragon;
    }
    @Override
    public ArmorStand getFollower() {
        return as;
    }
}
