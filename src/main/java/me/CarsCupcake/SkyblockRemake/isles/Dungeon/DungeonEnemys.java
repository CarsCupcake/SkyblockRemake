package me.CarsCupcake.SkyblockRemake.isles.Dungeon;

import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.IRoom;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Location2d;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.melee.ZombieKnight;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.miniboss.AngryArcheologist;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.miniboss.lostAdventurers.FrozenAdventurer;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.*;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.melee.CryptLurker;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.melee.TankZombie;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.melee.ZombieLord;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.miniboss.lostAdventurers.HolyAdventurer;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.miniboss.lostAdventurers.SuperiorAdventurer;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.miniboss.lostAdventurers.UnstableAdventurer;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.miniboss.lostAdventurers.YoungAdventurer;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.ranged.*;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Location;

public class DungeonEnemys {
    public static final DungeonEnemys FLOOR0 = new DungeonEnemys(new Class[]{TankZombie.class, CryptLurker.class},
            new Class[]{SkeletonSoldier.class, SkeletonGrunt.class, ScaredSkeleton.class, SoulEater.class},
            new Class[]{HolyAdventurer.class, SuperiorAdventurer.class, UnstableAdventurer.class, SuperiorAdventurer.class, YoungAdventurer.class, AngryArcheologist.class});
    public static final DungeonEnemys FLOOR1 = new DungeonEnemys(new Class[]{TankZombie.class, CryptLurker.class},
            new Class[]{SkeletonSoldier.class, SkeletonGrunt.class, ScaredSkeleton.class, SoulEater.class},
            new Class[]{HolyAdventurer.class, SuperiorAdventurer.class, UnstableAdventurer.class, SuperiorAdventurer.class, YoungAdventurer.class, AngryArcheologist.class});
    public static final DungeonEnemys FLOOR2 = new DungeonEnemys(new Class[]{TankZombie.class, CryptLurker.class},
            new Class[]{SkeletonSoldier.class, SkeletonGrunt.class, ScaredSkeleton.class, SoulEater.class},
            new Class[]{HolyAdventurer.class, SuperiorAdventurer.class, UnstableAdventurer.class, SuperiorAdventurer.class, YoungAdventurer.class, AngryArcheologist.class});
    public static final DungeonEnemys FLOOR3 = new DungeonEnemys(new Class[]{TankZombie.class, CryptLurker.class, ZombieKnight.class},
            new Class[]{SkeletonSoldier.class, SkeletonGrunt.class, SoulEater.class},
            new Class[]{HolyAdventurer.class, SuperiorAdventurer.class, UnstableAdventurer.class, SuperiorAdventurer.class, YoungAdventurer.class, AngryArcheologist.class});
    public static final DungeonEnemys FLOOR4 = new DungeonEnemys(new Class[]{CryptLurker.class, ZombieKnight.class},
            new Class[]{SkeletonSoldier.class, Withermancer.class, SoulEater.class},
            new Class[]{HolyAdventurer.class, SuperiorAdventurer.class, UnstableAdventurer.class, SuperiorAdventurer.class, YoungAdventurer.class, AngryArcheologist.class, FrozenAdventurer.class});
    public static final DungeonEnemys FLOOR5 = new DungeonEnemys(new Class[]{CryptLurker.class, ZombieKnight.class},
            new Class[]{SkeletonSoldier.class, Withermancer.class, SoulEater.class},
            new Class[]{HolyAdventurer.class, SuperiorAdventurer.class, UnstableAdventurer.class, SuperiorAdventurer.class, YoungAdventurer.class, AngryArcheologist.class, FrozenAdventurer.class});
    public static final DungeonEnemys FLOOR6 = new DungeonEnemys(new Class[]{CryptLurker.class, ZombieKnight.class},
            new Class[]{SkeletonSoldier.class, Withermancer.class, SoulEater.class},
            new Class[]{HolyAdventurer.class, SuperiorAdventurer.class, UnstableAdventurer.class, SuperiorAdventurer.class, YoungAdventurer.class, AngryArcheologist.class, FrozenAdventurer.class});
    public static final DungeonEnemys FLOOR7 = new DungeonEnemys(new Class[]{ZombieLord.class, CryptLurker.class, ZombieLord.class, ZombieKnight.class},
            new Class[]{SkeletonLord.class, SkeletonSoldier.class, Withermancer.class, SoulEater.class},
            new Class[]{HolyAdventurer.class, SuperiorAdventurer.class, UnstableAdventurer.class, SuperiorAdventurer.class, YoungAdventurer.class, AngryArcheologist.class, FrozenAdventurer.class});
    private final Class<? extends DungeonMob>[] melee;
    private final Class<? extends DungeonMob>[] archer;
    private final Class<? extends DungeonMob>[] miniboss;
    private DungeonEnemys(Class<? extends DungeonMob>[] melee, Class<? extends DungeonMob>[] archer, Class<? extends DungeonMob>[] miniboss) {
        this.melee = melee;
        this.archer = archer;
        this.miniboss = miniboss;
    }
    public void spawnMelee(Location l, IRoom room, int rotation, Location2d base, Dungeon.MobAttributes... attributes) {
        Dungeon.INSTANCE.spawnMob(Tools.getRandom(melee), IRoom.relativeToActual(l, rotation, base), room, attributes);
    }
    public void spawnArcher(Location l, IRoom room, int rotation, Location2d base, Dungeon.MobAttributes... attributes) {
        Dungeon.INSTANCE.spawnMob(Tools.getRandom(archer), IRoom.relativeToActual(l, rotation, base), room, attributes);
    }
    public void spawnMiniboss(Location l, IRoom room, int rotation, Location2d base, Dungeon.MobAttributes... attributes) {
        Dungeon.INSTANCE.spawnMob(Tools.getRandom(miniboss), IRoom.relativeToActual(l, rotation, base), room, attributes);
    }
    public static DungeonEnemys getFloorPool() {
        return switch (Dungeon.INSTANCE.floor()) {
            case 0 -> FLOOR0;
            case 1 -> FLOOR1;
            case 2 -> FLOOR2;
            case 3 -> FLOOR3;
            case 4 -> FLOOR4;
            case 5 -> FLOOR5;
            case 6 -> FLOOR6;
            case 7 -> FLOOR7;
            default -> throw new IllegalStateException("Unexpected value: " + Dungeon.INSTANCE.floor());
        };
    }
}
