package me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.DungeonRoomsTypes;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class Generator extends MapRenderer {
    private boolean done = false;
    private BufferedImage image;
    @Getter
    private final LocationMap map;
    @Getter
    private static Generator generator;
    public Generator(){
        generator = this;
        // 0.5 1.5
        // 0.4 1.4
        // 0.3 1.3
        // 0.2 1.2
        // 0.1 1.1
        // 0.0 1.0
        map = new LocationMap();

        map.put(new Location2d(new Random().nextInt(6),5), new Room(DungeonRoomsTypes.red, new Location2d(new Random().nextInt(6),5)));
        map.put(new Location2d(new Random().nextInt(6),0), new Room(DungeonRoomsTypes.green, new Location2d(new Random().nextInt(6),0)));
        Set<Location2d> freeRoom = map.getEmpty();
        Location2d l = map.getGreen().getLocation();
        l.setX(1);
        freeRoom.remove(l);
        l = map.getRed().getLocation();
        l.setX(4);
        freeRoom.remove(l);
        List<Location2d> rL = freeRoom.stream().toList();
        rL = new ArrayList<>(rL);
        Collections.shuffle(rL);
        map.put(rL.get(0), new Room(DungeonRoomsTypes.fairy, rL.get(0)));
        map.put(rL.get(1), new Room(DungeonRoomsTypes.miniboss, rL.get(1)));
        for (int i = 2; i < 4; i++)
            map.put(rL.get(i), new Room(DungeonRoomsTypes.puzzle, rL.get(i)));
        for (Location2d loc : map.getEmpty()){
            if(map.containsKey(loc))
                continue;

            DungeonRoomsTypes type = DungeonRoomsTypes.getRandom();
            if(isFitting(type, loc)){
                Room r = new Room(type, loc);
                    map.put(loc, r);
                    switch (type){
                        case r1x2 -> {
                            map.put(loc.clone().setY(loc.getY() + 1), new Room(type, loc.clone().setY(loc.getY() + 1), true, r));
                            map.getBridgeMap().add(new Bundle<>(loc, 1));
                        }
                        case r1x3 -> {
                            map.put(loc.clone().setY(loc.getY() + 1), new Room(type, loc.clone().setY(loc.getY() + 1), true, r));
                            map.put(loc.clone().setY(loc.getY() + 2), new Room(type, loc.clone().setY(loc.getY() + 2), true, r));
                            map.getBridgeMap().add(new Bundle<>(loc, 1));
                            map.getBridgeMap().add(new Bundle<>(loc.clone().addY(1), 1));
                        }
                        case r1x4 -> {
                            map.put(loc.clone().setY(loc.getY() + 1), new Room(type, loc.clone().setY(loc.getY() + 1), true, r));
                            map.put(loc.clone().setY(loc.getY() + 2), new Room(type, loc.clone().setY(loc.getY() + 2), true, r));
                            map.put(loc.clone().setY(loc.getY() + 3), new Room(type, loc.clone().setY(loc.getY() + 3), true, r));
                            map.getBridgeMap().add(new Bundle<>(loc, 1));
                            map.getBridgeMap().add(new Bundle<>(loc.clone().addY(1), 1));
                            map.getBridgeMap().add(new Bundle<>(loc.clone().addY(2), 1));
                        }
                        case r2x2 -> {
                            Location2d tL = loc.clone().setX(loc.getX() + 1);
                            map.put(loc.clone().setY(loc.getY() + 1), new Room(type, loc.clone().setY(loc.getY() + 1), true, r));
                            map.put(tL.clone(), new Room(type, tL.clone(), true, r));
                            map.put(tL.clone().setY(tL.getY() + 1), new Room(type, tL.clone().setY(tL.getY() + 1), true, r));
                            map.getBridgeMap().add(new Bundle<>(loc, 1));
                            map.getBridgeMap().add(new Bundle<>(loc.clone().addX(1), 1));
                            map.getBridgeMap().add(new Bundle<>(loc, 0));
                            map.getBridgeMap().add(new Bundle<>(loc.clone().addY(1), 0));
                            map.getBridgeMap().add(new Bundle<>(loc, 2));
                        }
                        case rLShaped -> {
                            Location2d tL = loc.clone().setX(loc.getX() + 1);
                            map.put(tL.clone(), new Room(type, tL.clone(), true, r));
                            map.put(tL.clone().setY(tL.getY() + 1), new Room(type, tL.clone().setY(tL.getY() + 1), true, r));
                            map.getBridgeMap().add(new Bundle<>(loc, 0));
                            map.getBridgeMap().add(new Bundle<>(tL.clone(), 1));
                        }


                    }
            }else
                map.put(loc, new Room(DungeonRoomsTypes.r1x1, loc));
        }
        for (Location2d loc : map.getEmpty()){
            if(map.containsKey(loc))
                continue;

            DungeonRoomsTypes type = DungeonRoomsTypes.getRandom();
            if(isFitting(type, loc)){
                {

                    map.put(loc, new Room(type, loc));
                }
            }else
                map.put(loc, new Room(DungeonRoomsTypes.r1x1, loc));
        }
        for (Location2d loc : map.getEmpty()){
            map.put(loc, new Room(DungeonRoomsTypes.r1x1, loc));
        }

    }
    private boolean isFitting(DungeonRoomsTypes types, Location2d l){
        l = l.clone();
        switch (types){
            case r1x1, green, red, fairy, miniboss, Trap, puzzle -> {
                return !map.containsKey(l);
            }
            case r1x2 -> {
                return check(l) && check(l.clone().addY(1));
            }
            case r1x3 -> {
                return check(l) && check(l.clone().addY(1)) && check(l.clone().addY(2));
            }
            case r1x4 -> {
                return check(l) && check(l.clone().addY(1)) && check(l.clone().addY(2)) && check(l.clone().addY(3));
            }
            case r2x2 -> {
                Location2d tL = l.clone().setX(l.getX() + 1);
                return check(l) && check(l.clone().addY(1)) && check(tL) && check(tL.clone().addY(1));
            }
            case rLShaped -> {
                Location2d tL = l.clone().setX(l.getX() + 1);
                return check(l) && check(tL) && check(tL.clone().addY(1));
            }
        }

        return false;
    }
    @Deprecated
    public Generator(int size){
        this();
    }
    private boolean check(Location2d l){
        return !map.containsKey(l) && !l.isOutOfBounds();
    }

    public BufferedImage generateMap(World world) {
        image = new BufferedImage(128, 128, BufferedImage.TYPE_INT_RGB);

        MapView view = Bukkit.createMap(world);
        view.getRenderers().clear();
        view.setTrackingPosition(false);
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        Color c1= new Color(183,165,130,255);
        Color c2 = new Color(161,144,114,255);
        Color trap = new Color(0xe68a00);
        Color puzzle = Color.MAGENTA;
        Color yellow = Color.YELLOW;
        for(int l = 0; l<128; l++)
            for(int i = 0; i <128;i++) {
                if (i% 2 == 0)
                    g2.setColor(c1);
                else
                    g2.setColor(c2);


                g2.drawRect(i, l, i+1, l+1);

            }
        for(Location2d l : map.keySet()) {
            Room room = map.get(l);
            int curry = l.getMapY();
            int currx = l.getMapX();

                if(room != null) {
                    g2.setColor(new Color(113,67,27,255));
                    if(room.getType() == DungeonRoomsTypes.fairy)
                        g2.setColor(new Color(200,106,138,255));

                    if(room.getType() == DungeonRoomsTypes.Trap)
                        g2.setColor(trap);

                    if(room.getType() == DungeonRoomsTypes.puzzle)
                        g2.setColor(puzzle);

                    if(room.getType() == DungeonRoomsTypes.miniboss)
                        g2.setColor(yellow);

                    if(room.getType() == DungeonRoomsTypes.red)
                        g2.setColor(Color.red);

                    if(room.getType() == DungeonRoomsTypes.green)
                        g2.setColor(Color.green);


                    g2.fillRect(currx, curry, 14, 14);

                }
                if(room.getType() == DungeonRoomsTypes.r2x2 && !room.isSub()) {
                    g2.setColor(new Color(113,67,27,255));
                    g2.fillRect(currx, curry + 14, 6, 6);
                }

        }
        g2.setColor(new Color(113,67,27,255));
        for (Bundle<Location2d, Integer> bundle : map.getBridgeMap()){
            int curry = bundle.getFirst().getMapY();
            int currx = bundle.getFirst().getMapX();
            switch (bundle.getLast()){
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
        if(done)
            return;

        canvas.drawImage(0, 0, image);

        done = true;


    }
}
