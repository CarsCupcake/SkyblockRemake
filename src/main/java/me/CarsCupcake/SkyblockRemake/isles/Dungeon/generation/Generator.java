package me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.block.BlockTypes;
import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.DungeonRoomsTypes;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Rooms.lShaped.RoomLShManager;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Rooms.r1x1.Room1x1Manager;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Rooms.r1x2.Room1x2Manager;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Rooms.r1x3.Room1x3Manager;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Rooms.r1x4.Room1x4Manager;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Rooms.r2x2.Room2x2Manager;
import me.CarsCupcake.SkyblockRemake.utils.maps.CountMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public final class Generator extends MapRenderer {
    private boolean done = false;
    private BufferedImage image;
    @Getter
    private final LocationMap map;
    @Getter
    private static Generator generator;
    private Map<DungeonRoomsTypes, RoomManager> managers = Map.of(DungeonRoomsTypes.r1x1, new Room1x1Manager(), DungeonRoomsTypes.r1x2, new Room1x2Manager(), DungeonRoomsTypes.r1x3, new Room1x3Manager(),
            DungeonRoomsTypes.r1x4, new Room1x4Manager(), DungeonRoomsTypes.r2x2, new Room2x2Manager(), DungeonRoomsTypes.rLShaped, new RoomLShManager());

    public Generator() {
        generator = this;
        // 0.5 1.5
        // 0.4 1.4
        // 0.3 1.3
        // 0.2 1.2
        // 0.1 1.1
        // 0.0 1.0
        map = new LocationMap();
        Location2d red = new Location2d(new Random().nextInt(6), 5);
        map.put(new Room(DungeonRoomsTypes.red, red, null, 0));
        Location2d green = new Location2d(new Random().nextInt(6), 0);
        map.put(new Room(DungeonRoomsTypes.green, green, null, 0));
        Set<Location2d> freeRoom = map.getEmpty();
        Location2d l = map.getGreen().getLocation();
        l.setX(1);
        freeRoom.remove(l);
        l = map.getRed().getLocation();
        l.setX(4);
        freeRoom.remove(l);
        List<Location2d> rL = new ArrayList<>(freeRoom);
        Collections.shuffle(rL);
        map.put(rL.get(0), new Room(DungeonRoomsTypes.fairy, rL.get(0), null, 0));
        map.put(rL.get(1), new Room(DungeonRoomsTypes.miniboss, rL.get(1), null, 0));
        for (int i = 2; i < 5; i++)
            map.put(new Room(DungeonRoomsTypes.puzzle, rL.get(i), null, 0));
        CountMap<DungeonRoomsTypes> setAmount = new CountMap<>();
        for (Location2d loc : map.getEmpty()) {
            if (map.containsKey(loc))
                continue;

            DungeonRoomsTypes type = DungeonRoomsTypes.getRandom();
            int rotation = new Random().nextInt(0, 4);
            if (bruteforce(type, loc, rotation, new HashSet<>())) {
                setAmount.add(type, 1, 0);
            }
        }
        for (Location2d loc : map.getEmpty()) {
            if (map.containsKey(loc))
                continue;

            DungeonRoomsTypes type = DungeonRoomsTypes.getRandom();
            int rotation = new Random().nextInt(0, 4);
            if (bruteforce(type, loc, rotation, new HashSet<>())) {
                {
                    setAmount.add(type, 1, 0);
                }
            } else
                map.put(loc, new Room(DungeonRoomsTypes.r1x1, loc, managers.get(DungeonRoomsTypes.r1x1).getNewRandom(new HashSet<>()), rotation));
        }
        for (Location2d loc : map.getEmpty()) {
            map.put(loc, new Room(DungeonRoomsTypes.r1x1, loc, managers.get(DungeonRoomsTypes.r1x1).getNewRandom(new HashSet<>()), new Random().nextInt(0, 4)));
        }

    }

    private boolean bruteforce(DungeonRoomsTypes type, Location2d loc, int rotation, HashSet<Integer> alreadySet) {
        for (int i = 0; i < 4; i++) {
            if (isFitting(type, loc, (i + rotation > 4) ? ((i + rotation) - 4) : (i + rotation))) {
                map.put(new Room(type, loc, managers.get(type).getNewRandom(alreadySet), (i + rotation > 4) ? ((i + rotation) - 4) : (i + rotation)));
                return true;
            }
        }
        return false;
    }

    public boolean isFitting(DungeonRoomsTypes types, Location2d l, int rotation) {
        l = l.clone();
        switch (types) {
            case r1x1, green, red, fairy, miniboss, Trap, puzzle -> {
                return check(l);
            }
            case r1x2 -> {
                switch (rotation) {
                    case 0 -> {
                        return check(l) && check(l.clone().addY(1));
                    }
                    case 1 -> {
                        return check(l) && check(l.clone().addX(1));
                    }
                    case 2 -> {
                        return check(l) && check(l.clone().addY(-1));
                    }
                    case 3 -> {
                        return check(l) && check(l.clone().addX(-1));
                    }
                }
            }
            case r1x3 -> {
                switch (rotation) {
                    case 0 -> {
                        return check(l) && check(l.clone().addY(1)) && check(l.clone().addY(2));
                    }
                    case 1 -> {
                        return check(l) && check(l.clone().addX(1)) && check(l.clone().addX(2));
                    }
                    case 2 -> {
                        return check(l) && check(l.clone().addY(-1)) && check(l.clone().addY(-2));
                    }
                    case 3 -> {
                        return check(l) && check(l.clone().addX(-1)) && check(l.clone().addX(-2));
                    }
                }
            }
            case r1x4 -> {
                switch (rotation) {
                    case 0 -> {
                        return check(l) && check(l.clone().addY(1)) && check(l.clone().addY(2)) && check(l.clone().addY(3));
                    }
                    case 1 -> {
                        return check(l) && check(l.clone().addX(1)) && check(l.clone().addX(2)) && check(l.clone().addX(3));
                    }
                    case 2 -> {
                        return check(l) && check(l.clone().addY(-1)) && check(l.clone().addY(-2)) && check(l.clone().addY(-3));
                    }
                    case 3 -> {
                        return check(l) && check(l.clone().addX(-1)) && check(l.clone().addX(-2)) && check(l.clone().addX(-3));
                    }
                }
            }
            case r2x2 -> {

                Location2d tL = l.clone().setX(l.getX() + 1);
                return check(l) && check(l.clone().addY(1)) && check(tL) && check(tL.clone().addY(1));

            }
            case rLShaped -> {
                Location2d tL = l.clone().setX(l.getX() + 1);
                return check(l) && check(tL) && check(tL.clone().addY(1));
                /*switch (rotation){
                    case 0 -> {
                        Location2d tL = l.clone().setX(l.getX() + 1);
                        return check(l) && check(tL) && check(tL.clone().addY(1));
                    }
                    case 1 -> {
                        Location2d tL = l.clone().setX(l.getX() - 1);
                        return check(l) && check(tL) && check(l.clone().addY(-1));
                    }
                    case 3 -> {
                        Location2d tL = l.clone().setX(l.getX() - 1);
                        return check(l) && check(tL) && check(l.clone().addY(-1));
                    }
                }*/
            }
        }

        return false;
    }

    @Deprecated
    public Generator(int size, LocationMap map) {
        this();
    }

    private boolean check(Location2d l) {
        return !map.containsKey(l) && !l.isOutOfBounds();
    }

    public BufferedImage generateMap(World world) {
        image = new BufferedImage(128, 128, BufferedImage.TYPE_INT_RGB);

        MapView view = Bukkit.createMap(world);
        view.getRenderers().clear();
        view.setTrackingPosition(false);
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        Color c1 = new Color(183, 165, 130, 255);
        Color c2 = new Color(161, 144, 114, 255);
        Color trap = new Color(0xe68a00);
        Color puzzle = Color.MAGENTA;
        Color yellow = Color.YELLOW;
        for (int l = 0; l < 128; l++)
            for (int i = 0; i < 128; i++) {
                if (i % 2 == 0)
                    g2.setColor(c1);
                else
                    g2.setColor(c2);


                g2.drawRect(i, l, i + 1, l + 1);

            }
        for (Location2d l : map.keySet()) {
            Room room = map.get(l);
            int curry = l.getMapY();
            int currx = l.getMapX();

            if (room != null) {
                g2.setColor(new Color(113, 67, 27, 255));
                if (room.getType() == DungeonRoomsTypes.fairy)
                    g2.setColor(new Color(200, 106, 138, 255));

                if (room.getType() == DungeonRoomsTypes.Trap)
                    g2.setColor(trap);

                if (room.getType() == DungeonRoomsTypes.puzzle)
                    g2.setColor(puzzle);

                if (room.getType() == DungeonRoomsTypes.miniboss)
                    g2.setColor(yellow);

                if (room.getType() == DungeonRoomsTypes.red)
                    g2.setColor(Color.red);

                if (room.getType() == DungeonRoomsTypes.green)
                    g2.setColor(Color.green);


                g2.fillRect(currx, curry, 14, 14);

            }
            if (room.getType() == DungeonRoomsTypes.r2x2 && !room.isSub()) {
                g2.setColor(new Color(113, 67, 27, 255));
                g2.fillRect(currx, curry + 14, 6, 6);
            }

        }
        g2.setColor(new Color(113, 67, 27, 255));
        for (Bundle<Location2d, Integer> bundle : map.getBridgeMap()) {
            int curry = bundle.getFirst().getMapY();
            int currx = bundle.getFirst().getMapX();
            switch (bundle.getLast()) {
                case 0 -> g2.fillRect(currx + 14, curry, 6, 14);

                case 1 -> g2.fillRect(currx, curry + 14, 14, 6);

                case 2 -> g2.fillRect(currx + 14, curry + 14, 6, 6);

            }
        }
        g2.dispose();
        return image;
    }

    @Override
    public void render(@NotNull MapView view, @NotNull MapCanvas canvas, @NotNull Player player) {
        if (done)
            return;

        canvas.drawImage(0, 0, image);

        done = true;


    }

    public static Location to3d(Location2d location2d) {
        return new Location(Bukkit.getWorld("world"), location2d.getMapX(), 0, location2d.getMapY());
    }

    public static void place() {
        new BukkitRunnable() {
            public void run() {
                long before = System.currentTimeMillis();
                long steps = before;
                CuboidRegion region = new CuboidRegion(BlockVector3.at(-103, 0, -103), BlockVector3.at(259, 150, 259));
                region.setWorld(BukkitAdapter.adapt(Bukkit.getWorld("world")));
                EditSession session = WorldEdit.getInstance().newEditSession(region.getWorld());
                session.setBlocks((Region) region, BlockTypes.AIR);
                session.close();
                Bukkit.broadcastMessage("§aClearing blocks took " + (System.currentTimeMillis() - steps) + "ms");
                steps = System.currentTimeMillis();

                new Generator();

                Bukkit.broadcastMessage("§aGenerating took " + (System.currentTimeMillis() - steps) + "ms");
                steps = System.currentTimeMillis();

                for (Location2d l : generator.map.getMains()) {
                    Room room = generator.map.get(l);
                    if (room.isSub()) continue;
                    if (room.getRoom() == null) continue;
                    room.getRoom().place(l, room.getRotation());
                }
                Bukkit.broadcastMessage("§aPlacing rooms took " + (System.currentTimeMillis() - steps) + "ms");
                Bukkit.broadcastMessage("§aAll operations took " + (System.currentTimeMillis() - before) + "ms");
                Bukkit.broadcastMessage("\nThe map:");
                for (int i = 5; i > -1; i--) {
                    StringBuilder builder = new StringBuilder();
                    for (int j = 5; j > -1; j--)
                        builder.append(generator.map.get(new Location2d(j, i)).getType() + " ");
                    Bukkit.broadcastMessage(builder + "");
                }
            }
        }.runTaskAsynchronously(Main.getMain());
    }
}