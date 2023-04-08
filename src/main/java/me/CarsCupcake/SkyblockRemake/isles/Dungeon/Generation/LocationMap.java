package me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation;

import lombok.Getter;
import lombok.Setter;
import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.DungeonRoomsTypes;
import org.bukkit.Bukkit;

import java.util.*;

public class LocationMap extends HashMap<Location2d, Room> {
    @Getter
    private final Collection<Location2d> mains = new HashSet<>();
    @Getter
    private final Collection<Location2d> subs = new HashSet<>();
    // 0 -> x + 14 and with 6 heigh 14 | 1 -> y + 14 with 14 heigh 6 | 2 -> x && y + 14 with && heigh 6
    @Getter
    private final Collection<Bundle<Location2d, Integer>> bridgeMap = new HashSet<>();
    @Getter
    @Setter
    private Room green;
    @Getter
    @Setter
    private Room red;
    @Getter
    @Setter
    private Room fairy;

    public Collection<Location2d> getByX(int x) {
        Set<Location2d> keySet = this.keySet();
        return keySet.stream().filter(t -> t.getX() == x).toList();
    }

    public Collection<Location2d> getByY(int y) {
        Set<Location2d> keySet = this.keySet();
        return keySet.stream().filter(t -> t.getY() == y).toList();
    }

    public Room put(Room k) {
        return put(k.getLocation(), k);
    }

    @Override
    public Room put(Location2d l, Room k) {
        try {
            if (!k.isSub()) generate(l, k);
        } catch (Exception e) {
            if (e.getMessage().equals("Not Fitting!"))
                Bukkit.broadcastMessage("§cTryed to gen a false room! (" + k.getType() + " " + k.getLocation() + " " + k.getRotation());
            new RuntimeException(e);
        }
        if (k.isSub()) subs.add(l);
        else mains.add(l);
        if (k.getType() == DungeonRoomsTypes.fairy) fairy = k;
        if (k.getType() == DungeonRoomsTypes.green) green = k;
        if (k.getType() == DungeonRoomsTypes.red) red = k;
        System.out.println("Put");
        return super.put(l, k);
    }

    private void generate(Location2d l, Room r) {
        if (!Generator.getGenerator().isFitting(r.getType(), l, r.getRotation())) {
            throw new RuntimeException("Not Fitting!");
        }
        DungeonRoomsTypes types = r.getType();
        switch (types) {
            case r1x2 -> {
                switch (r.getRotation()) {
                    case 0 -> {
                        put(new Room(types, l.clone().addY(1), true, r));
                        bridgeMap.add(new Bundle<>(l, 1));
                    }
                    case 1 -> {
                        put(new Room(types, l.clone().addX(1), true, r));
                        bridgeMap.add(new Bundle<>(l, 0));
                    }
                    case 2 -> {
                        put(new Room(types, l.clone().addY(-1), true, r));
                        bridgeMap.add(new Bundle<>(l.clone().addY(-1), 1));
                    }
                    case 3 -> {
                        put(new Room(types, l.clone().addX(-1), true, r));
                        bridgeMap.add(new Bundle<>(l.clone().addX(-1), 0));
                    }
                }
            }
            case r1x3 -> {
                switch (r.getRotation()) {
                    case 0 -> {
                        put(new Room(types, l.clone().setY(l.getY() + 1), true, r));
                        put(new Room(types, l.clone().setY(l.getY() + 2), true, r));
                        bridgeMap.add(new Bundle<>(l, 1));
                        bridgeMap.add(new Bundle<>(l.clone().addY(1), 1));
                    }
                    case 1 -> {
                        put(new Room(types, l.clone().setX(l.getX() + 1), true, r));
                        put(new Room(types, l.clone().setX(l.getX() + 2), true, r));
                        bridgeMap.add(new Bundle<>(l, 0));
                        bridgeMap.add(new Bundle<>(l.clone().addX(1), 0));
                    }
                    case 2 -> {
                        put(new Room(types, l.clone().setY(l.getY() - 1), true, r));
                        put(new Room(types, l.clone().setY(l.getY() - 2), true, r));
                        bridgeMap.add(new Bundle<>(l.clone().addY(-1), 1));
                        bridgeMap.add(new Bundle<>(l.clone().addY(-2), 1));
                    }
                    case 3 -> {
                        put(l.clone().setX(l.getX() - 1), new Room(types, l.clone().setX(l.getX() - 1), true, r));
                        put(l.clone().setX(l.getX() - 2), new Room(types, l.clone().setX(l.getX() - 2), true, r));
                        bridgeMap.add(new Bundle<>(l.clone().addX(-1), 0));
                        bridgeMap.add(new Bundle<>(l.clone().addX(-2), 0));
                    }
                }
            }
            case r1x4 -> {
                switch (r.getRotation()) {
                    case 0 -> {
                        put(l.clone().setY(l.getY() + 1), new Room(types, l.clone().setY(l.getY() + 1), true, r));
                        put(new Room(types, l.clone().setY(l.getY() + 2), true, r));
                        put(new Room(types, l.clone().setY(l.getY() + 3), true, r));
                        bridgeMap.add(new Bundle<>(l, 1));
                        bridgeMap.add(new Bundle<>(l.clone().addY(1), 1));
                        bridgeMap.add(new Bundle<>(l.clone().addY(2), 1));
                    }
                    case 1 -> {
                        put(l.clone().setX(l.getX() + 1), new Room(types, l.clone().setX(l.getX() + 1), true, r));
                        put(l.clone().setX(l.getX() + 2), new Room(types, l.clone().setX(l.getX() + 2), true, r));
                        put(l.clone().setX(l.getX() + 3), new Room(types, l.clone().setX(l.getX() + 3), true, r));
                        bridgeMap.add(new Bundle<>(l, 0));
                        bridgeMap.add(new Bundle<>(l.clone().addX(1), 0));
                        bridgeMap.add(new Bundle<>(l.clone().addX(2), 0));
                    }
                    case 2 -> {
                        put(l.clone().setY(l.getY() - 1), new Room(types, l.clone().setY(l.getY() - 1), true, r));
                        put(l.clone().setY(l.getY() - 2), new Room(types, l.clone().setY(l.getY() - 2), true, r));
                        put(l.clone().setY(l.getY() - 3), new Room(types, l.clone().setY(l.getY() - 3), true, r));
                        bridgeMap.add(new Bundle<>(l.clone().addY(-1), 1));
                        bridgeMap.add(new Bundle<>(l.clone().addY(-2), 1));
                        bridgeMap.add(new Bundle<>(l.clone().addY(-3), 1));
                    }
                    case 3 -> {
                        put(l.clone().setX(l.getX() - 1), new Room(types, l.clone().setX(l.getX() - 1), true, r));
                        put(l.clone().setX(l.getX() - 2), new Room(types, l.clone().setX(l.getX() - 2), true, r));
                        put(l.clone().setX(l.getX() - 3), new Room(types, l.clone().setX(l.getX() - 3), true, r));
                        bridgeMap.add(new Bundle<>(l.clone().addX(-1), 0));
                        bridgeMap.add(new Bundle<>(l.clone().addX(-2), 0));
                        bridgeMap.add(new Bundle<>(l.clone().addX(-3), 0));
                    }
                }
            }
            case r2x2 -> {
                Location2d tL = l.clone().setX(l.getX() + 1);
                put(l.clone().setY(l.getY() + 1), new Room(types, l.clone().setY(l.getY() + 1), true, r));
                put(tL.clone(), new Room(types, tL.clone(), true, r));
                put(tL.clone().setY(tL.getY() + 1), new Room(types, tL.clone().setY(tL.getY() + 1), true, r));
                bridgeMap.add(new Bundle<>(l, 1));
                bridgeMap.add(new Bundle<>(l.clone().addX(1), 1));
                bridgeMap.add(new Bundle<>(l, 0));
                bridgeMap.add(new Bundle<>(l.clone().addY(1), 0));
                bridgeMap.add(new Bundle<>(l, 2));

            }
            case rLShaped -> {
                Location2d tL = l.clone().setX(l.getX() + 1);
                put(tL.clone(), new Room(types, tL.clone(), true, r));
                put(tL.clone().setY(tL.getY() + 1), new Room(types, tL.clone().setY(tL.getY() + 1), true, r));
                bridgeMap.add(new Bundle<>(l, 0));
                bridgeMap.add(new Bundle<>(l.clone().addX(1), 1));
            }

        }
    }

    @Override
    public boolean containsKey(Object key) {
        if (!(key instanceof Location2d loc)) return false;
        Set<Location2d> keySet = super.keySet();
        assert keySet != null;
        boolean b = false;
        for (Location2d l : keySet)
            if (l.getX() == loc.getX() && l.getY() == loc.getY()) {
                b = true;
                break;
            }

        return b;
    }

    public Set<Location2d> getEmpty() {
        Set<Location2d> keySet = super.keySet();
        Set<Location2d> set = new HashSet<>();
        for (int x = 0; x < 6; x++)
            for (int y = 0; y < 6; y++) {
                boolean b = containsKey(new Location2d(x, y));
                if (!b) set.add(new Location2d(x, y));
            }
        return set;
    }
}
