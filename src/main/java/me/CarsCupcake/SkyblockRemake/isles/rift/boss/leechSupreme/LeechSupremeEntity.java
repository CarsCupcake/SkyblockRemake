package me.CarsCupcake.SkyblockRemake.isles.rift.boss.leechSupreme;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.NPC.DiguestMobsManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.rift.entitys.RiftEntity;
import me.CarsCupcake.SkyblockRemake.utils.runnable.EntityRunnable;
import net.minecraft.network.protocol.game.*;
import net.minecraft.network.syncher.DataWatcher;
import net.minecraft.network.syncher.DataWatcherRegistry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.level.WorldServer;
import net.minecraft.server.network.PlayerConnection;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;

public class LeechSupremeEntity extends RiftEntity {
    private static final String texture = "ewogICJ0aW1lc3RhbXAiIDogMTYwNDYxNDc0Mjg3NywKICAicHJvZmlsZUlkIiA6ICI0ZDcwNDg2ZjUwOTI0ZDMzODZiYmZjOWMxMmJhYjRhZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJzaXJGYWJpb3pzY2hlIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2Q5NzZmZmRkMjkyYzM0ZmRhYmIyYWNmYTYyZGJmZDRmMGQxMzAwMDg3ODU4MDliZTAxNmQzZDcyNTdiYjMxZjIiCiAgICB9CiAgfQp9";
    private static final String signature = "OUZ7cpgqW4m9lxL155ISzLjbkSjzn41asRnWsffNTaHdLI5ImVtaHAVeIh8C5eSSi/lelmBpcsrB+3VWXWwicWcCcczXan5Ztps6Jp0uY9FlwqCrH7faGDBz8qXe5KL98Lox0ceJQc4SMQfUpmbEHdKIwCCSWosm4RbS2GoPji9k5q0tbzPpvxuHGYM8DvSk19qZ+p/NmooAFJ6cZxd4eDgJjQ8cbxMK77Tb17alkrubi7Eh346sDz2H1U622GnVMV0zKuUpao19V7QXyo4rglLcIx7OcshlTS3d0XgLhzRxD0OGQYD91jYy4TM8aedvNsH+qtX2vgmgt75ZUUEFE91NCsBfR5y7TfoN4yIdTuCMqtTahJZV7Qs0TFSofj+KLEmdv1lCwdqukBO8HAtIzDS9f6llVA+pa35xkiSH+hIkO4OYnUCs37t68rL+2o/emUx8AJX3DyemBWS8nvvY8dQAUdOSwa/4de5ewEcPPigZhKFCZcDBmyrEq+cj187zXDeWcZ0Rv4sL5A1HUYWsOD/GxxYk0on02nk30X4nGfgjo3hexFwxdJdZKSUbHcPztFk1y9rI/dAJSWK1+8MYXpjXqqbIPKBPZsupQLI99nol2IJ2aecL0uTPwrBW6tITZr9JfiBkqVyVbZIEEewAmdikPFv1bqblIzpBGSHn4fg=";
    public boolean isInAbility = false;
    private int step = 0;
    private static final int[] steps = {640, 480, 320, 160};
    private static final Runnable[] stepRunnables = {() -> LeechFightManager.getInstance().slimePound(), () -> LeechFightManager.getInstance().wickedBombs(),
            () -> LeechFightManager.getInstance().leechSwarm(), () -> LeechFightManager.getInstance().mortiferousLazer()};
    private LivingEntity entity;

    @Override
    public int getMaxHealth() {
        return 800;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public void spawn(Location loc) {
        entity = loc.getWorld().spawn(loc, Zombie.class, (e) -> {
            e.setRemoveWhenFarAway(false);
            e.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4);
        });
        DiguestMobsManager.createEntity(entity, SkyblockEntity.getBaseName(this), texture, signature);
        SkyblockEntity.livingEntity.addEntity(entity, this);
        Main.updateentitystats(entity);
        entity.setCustomNameVisible(true);
        new EntityRunnable() {
            @Override
            public void run() {
                if(isInAbility) return;

                if(LeechFightManager.getMiddle().distance(entity.getLocation()) > 15)
                    entity.setVelocity(LeechFightManager.getMiddle().toVector().subtract(entity.getLocation().toVector()).normalize().setY(0.5));
            }
        }.runTaskTimer(Main.getMain(), 0, 1);
    }

    @Override
    public String getName() {
        return "Leech Supreme";
    }

    @Override
    public HashMap<ItemManager, Integer> getDrops(SkyblockPlayer player) {
        return null;
    }

    @Override
    public boolean hasNoKB() {
        return false;
    }

    @Override
    public int getRiftTimeDamage() {
        return 0;
    }

    @Override
    public double getHeartsDamage() {
        return 0;
    }

    @Override
    public int getLevel() {
        return 10;
    }

    @Override
    public void damage(double damage, SkyblockPlayer player) {
        if (isInAbility) return;
        if (!(health - damage < 0) && step != steps.length)
            if (health - damage < steps[step]) {
                isInAbility = true;
                stepRunnables[step].run();
                step++;
            }
        super.damage(damage, player);
    }
    private EntityPlayer deadBodySpawn() {
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "§§§§§§");
        gameProfile.getProperties().put("textures", new Property("textures", texture, signature));
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer world = ((CraftWorld) entity.getLocation().getWorld()).getHandle();
        EntityPlayer entityPlayer = new EntityPlayer(server, world, gameProfile);
        entityPlayer.setPosition(entity.getLocation().getX(), entity.getLocation().getY(), entity.getLocation().getZ());
        DataWatcher watcher = entityPlayer.getDataWatcher();
        try {
            byte b = 0x02 | 0x04 | 0x08 | 0x10 | 0x20 | 0x40;
            watcher.set(DataWatcherRegistry.a.a(17), b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        entityPlayer.setCustomNameVisible(false);
        for (Player on : Bukkit.getOnlinePlayers()) {
            PlayerConnection p = ((CraftPlayer) on).getHandle().b;
            p.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, entityPlayer));
            p.sendPacket(new PacketPlayOutNamedEntitySpawn(entityPlayer));
            p.sendPacket(new PacketPlayOutEntityMetadata(entityPlayer.getId(), watcher, false));
        }
        return entityPlayer;
    }


    @Override
    public void kill() {
        super.kill();
        LeechFightManager.getInstance().defeat();
        if(!Main.getMain().isEnabled()) return;
        EntityPlayer fake = deadBodySpawn();
        Location base = entity.getLocation();
        new BukkitRunnable() {
            int exp = 0;
            int tot;
            @Override
            public void run() {
                if(exp == 0){
                    exp = 5;
                    entity.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, base.clone().add(0, 0.05 * tot, 0), 4);
                    entity.getWorld().playSound(base, Sound.ENTITY_WITHER_HURT, 1, 1);
                }
                for (Player p : Bukkit.getOnlinePlayers()) ((CraftPlayer) p).getHandle().b.sendPacket(new PacketPlayOutEntity.PacketPlayOutRelEntityMove(fake.getId(), (short) 0, (short) (0.05 * 32 * 128), (short) 0, false));
                exp--;
                tot++;
                if(tot == 100){
                    cancel();
                }
            }
            @Override
            public synchronized void cancel() throws IllegalStateException {
                super.cancel();
                Set<Entity> e = new HashSet<>();
                for (int i = 0; i < 6; i++){
                    for (Player player : Bukkit.getOnlinePlayers()) ((CraftPlayer) player).getHandle().b.sendPacket(new PacketPlayOutEntityDestroy(fake.getId()));
                    FallingBlock block = entity.getWorld().spawnFallingBlock(base.clone().add(0, 5, 0), Material.GREEN_WOOL.createBlockData());
                    block.setDropItem(false);
                    block.setHurtEntities(false);
                    block.setVelocity(Vector.getRandom().normalize().setY(1));
                    e.add(block);
                }
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        e.forEach(Entity::remove);
                    }
                }.runTaskLater(Main.getMain(), 60);
            }
        }.runTaskTimer(Main.getMain(), 0, 1);
        entity.remove();
    }
    public void move(Location location, Runnable onDone){
        boolean bef = entity.hasAI();
        boolean bef2 = entity.hasGravity();
        entity.setAI(false);
        entity.setGravity(false);
        double speed = entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue();
        Vector dir = location.toVector().subtract(entity.getLocation().toVector()).normalize().multiply(speed);
        new EntityRunnable() {
            @Override
            public void run() {
                entity.teleport(entity.getLocation().add(dir));
                int r = new Random().nextInt(60);
                Color c = Color.fromBGR(r, r, r);
                Particle.DustOptions dustOptions = new Particle.DustOptions(c, 2);
                entity.getWorld().spawnParticle(Particle.REDSTONE, entity.getLocation(), 1, dustOptions);
                if(entity.getLocation().distance(location) <= speed + (speed * 0.5)){
                    cancel();
                    entity.teleport(location);
                    entity.setAI(bef);
                    entity.setGravity(bef2);
                    onDone.run();
                }
            }
        }.runTaskTimer(this, 0, 1);
    }
}
