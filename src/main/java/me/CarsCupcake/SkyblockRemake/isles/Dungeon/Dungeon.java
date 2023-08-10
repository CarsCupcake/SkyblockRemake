package me.CarsCupcake.SkyblockRemake.isles.Dungeon;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.IRoom;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Secrets.Secret;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.miniboss.MiniBoss;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.DungeonMob;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.special.Fel;
import me.CarsCupcake.SkyblockRemake.utils.Assert;
import me.CarsCupcake.SkyblockRemake.utils.ReflectionUtils;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.DungeonSkeleton.skeletorSkulls;

public record Dungeon(int floor, boolean mastermode) {
    public static final Set<SkyblockEntity> mobs = new HashSet<>();
    public static boolean clearing = false;
    public static Dungeon INSTANCE;

    public Dungeon(int floor, boolean mastermode) {
        //TODO: add generator
        this.floor = floor;
        this.mastermode = mastermode;
        INSTANCE = this;
    }

    public void doSecret(Secret secret) {

    }

    public void spawnMob(Class<? extends DungeonMob> mobType, Location location, IRoom room, MobAttributes... attributes) {
        try {
            if (contains(attributes, MobAttributes.YellowMini))
                Assert.state(mobType.isAnnotationPresent(MiniBoss.class), "Not a miniboss!");
            DungeonMob mob = (!contains(attributes, MobAttributes.Strict) && new Random().nextDouble() <= 0.06) ?
                    new Fel(floor, mastermode)
                    : ((contains(attributes, MobAttributes.YellowMini)) ?
                    ReflectionUtils.accessibleConstructor(mobType, Integer.TYPE, Boolean.TYPE, Boolean.TYPE).newInstance(floor, mastermode, true)
                    : ReflectionUtils.accessibleConstructor(mobType, Integer.TYPE, Boolean.TYPE).newInstance(floor, mastermode));
            if (contains(attributes, MobAttributes.Star)) mob.setStared(room);
            mob.spawn(location);
            mob.getEntity().setAI(false);
            room.registerDiscoverEvent(() -> mob.getEntity().setAI(true));
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public enum MobAttributes {
        Star,
        YellowMini,
        Strict
    }

    private <T> boolean contains(T[] t, T obj) {
        for (T a : t)
            if (a.hashCode() == obj.hashCode() && a.equals(obj)) return true;
        return false;
    }
    public void clear() {
        clearing = true;
        for (SkyblockEntity mob : mobs)
            SkyblockEntity.killEntity(mob, null);
        mobs.clear();
        skeletorSkulls.forEach(Entity::remove);
        skeletorSkulls.clear();
    }
}
