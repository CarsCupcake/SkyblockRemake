package me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation;

import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.BuiltInClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.math.transform.AffineTransform;
import com.sk89q.worldedit.session.ClipboardHolder;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.DungeonMob;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

public abstract class IRoom {
    private int star = 0;
    private final Set<Runnable> discoverEvent = new HashSet<>();

    public abstract String fileLocation();
    public InputStream fileStram() {
        return Main.getMain().getResource(fileLocation());
    }

    public abstract void init(int rotation, Location2d base);

    public abstract Set<Location2d> getNextLocations(Location2d base, int rotation);

    /*public Location relativeToActual(Location relative, int rotation, Location2d locationOfCorner) {
        Vector v = relative.toVector();
        v.rotateAroundY(Math.toRadians(rotation * 90));
        Location l = locationOfCorner.asLocation(relative.getWorld(), 0).add(v);
        return l;
    }*/

    public static Location relativeToActual(Location relative, int cornerDirection, Location2d locationOfCorner) {
        Location corner = Generator.to3d(locationOfCorner);
        double x = 0;
        double z = switch (cornerDirection) {
            case 0 -> {
                x = relative.getX() + locationOfCorner.getMapX();
                yield relative.getZ() + locationOfCorner.getMapY(); //.getY in a point is the MC Z coord
            }
            case 1 -> {
                x = -(relative.getZ() - locationOfCorner.getMapX());
                yield relative.getX() + locationOfCorner.getMapY();
            }
            case 2 -> {
                x = -(relative.getX() - locationOfCorner.getMapX());
                yield -(relative.getZ() - locationOfCorner.getMapY());
            }
            case 3 -> {
                x = relative.getZ() + locationOfCorner.getMapX();
                yield -(relative.getX() - locationOfCorner.getMapY());
            }
            default -> 0;
        };
        return new Location(relative.getWorld(), x, relative.getY(), z);
    }

    public void discover() {
        for (Runnable r : discoverEvent) r.run();
    }

    public synchronized void registerDiscoverEvent(Runnable runnable) {
        discoverEvent.add(runnable);
    }

    protected void onClear() {
        Bukkit.broadcastMessage("Cleared!");
    }

    public void addStar() {
        star++;
    }


    public void place(Location2d location2d, int rotation) {
        rotation = makeRotation(rotation);
        if (rotation == 1) rotation = 3;
        else if (rotation == 3) rotation = 1;
        Location base = rotationCorner(Generator.to3d(location2d), rotation);
        System.out.println(base);
        InputStream stream = fileStram();
        try {
            ClipboardFormat format = BuiltInClipboardFormat.MCEDIT_SCHEMATIC;
            ClipboardReader reader = format.getReader(stream);
            Clipboard clipboard = reader.read();
            ClipboardHolder clipboardHolder = new ClipboardHolder(clipboard);
            clipboardHolder.setTransform(clipboardHolder.getTransform().combine(new AffineTransform().rotateY(rotation * 90)));
            EditSession editSession = WorldEdit.getInstance().newEditSession(BukkitAdapter.adapt(base.getWorld()));
            Operation operation = clipboardHolder.createPaste(editSession).copyEntities(false).to(BlockVector3.at(base.getX(), base.getY(), base.getZ())).build();
            Operations.complete(operation);
            editSession.close();
        } catch (Exception e) {
            Bukkit.broadcastMessage("§c A schematic failed to load");
            e.printStackTrace(System.out);
        } finally {
            try {
                stream.close();
            } catch (Exception e) {
                if (!(e instanceof JsonSyntaxException || e instanceof MalformedJsonException))
                    e.printStackTrace(System.out);
            }
        }
        final int finalRotation = rotation;
        new BukkitRunnable() {
            @Override
            public void run() {
                init(finalRotation, new Location2d(base.getBlockX(), base.getBlockZ()));
            }
        }.runTask(Main.getMain());

        //WIP: May be changet
        // 0 = 0°
        // 1 = 90°
        // 2 = 180°
        // 3 = -90° / 270°
    }

    public Location rotationCorner(Location l, int rotation) {
        return switch (rotation) {
            case 1 -> new Location(l.getWorld(), l.getX(), l.getY(), l.getZ() + 30);
            case 2 -> new Location(l.getWorld(), l.getX() + 30, l.getY(), l.getZ() + 30);
            case 3 -> new Location(l.getWorld(), l.getX() + 30, l.getY(), l.getZ());
            default -> l;
        };
    }

    public int baseRotation() {
        return 4;
    }

    public int makeRotation(int r) {
        int i = baseRotation() - r;
        if (i < 0) i += 4;
        if (i > 3) i -= 4;
        System.out.println("rotation: " + i + " because: " + baseRotation() + " - " + r + " = " + i);
        return i;
    }

    public void killStarMob(DungeonMob mob) {
        star--;
        if (star == 0) onClear();
    }

    public abstract String getId();
}
