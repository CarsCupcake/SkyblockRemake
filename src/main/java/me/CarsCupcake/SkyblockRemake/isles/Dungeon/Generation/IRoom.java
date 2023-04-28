package me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation;

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
import java.util.Set;

public interface IRoom {
    String fileLocation();

    void init(int rotation, Location2d base);

    Set<Location2d> getNextLocations(Location2d base, int rotation);

    default Location relativeToActual(Location relative, int rotation, Location2d locationOfCorner) {
        Vector OA = new Vector(relative.getX() + locationOfCorner.getMapX(), relative.getY(), relative.getZ() + locationOfCorner.getMapY());
        Vector OB = rotationCorner(Generator.to3d(locationOfCorner), rotation).toVector();
        System.out.println(OB);
        Vector BA = OA.subtract(OB);
        System.out.println(BA);
        BA = BA.rotateAroundY(rotation * 90);
        System.out.println(BA);
        System.out.println(OA.subtract(OB).rotateAroundY(rotation * -90));
        return Generator.to3d(locationOfCorner).add(BA);
    }

    default void place(Location2d location2d, int rotation) {
        rotation = makeRotation(rotation);
        if(rotation == 1) rotation = 3;
        else if(rotation == 3) rotation = 1;
        Location base = rotationCorner(Generator.to3d(location2d), rotation);
        InputStream stream = Main.getMain().getResource(fileLocation());
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
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (Exception e) {
                if (!(e instanceof JsonSyntaxException || e instanceof MalformedJsonException)) e.printStackTrace();
            }
        }
        final int finalRotation = rotation;
        new BukkitRunnable() {
            @Override
            public void run () {
            init(finalRotation, location2d);
        }
        }.runTask(Main.getMain());

        //WIP: Will be changet TODO
        // 0 = 0°
        // 1 = 90°
        // 2 = 180°
        // 3 = -90° / 270°
    }

    default Location rotationCorner(Location l, int rotation) {
        return switch (rotation) {
            case 1 -> new Location(l.getWorld(), l.getX(), l.getY(), l.getZ() + 30);
            case 2 -> new Location(l.getWorld(), l.getX() + 30, l.getY(), l.getZ() + 30);
            case 3 -> new Location(l.getWorld(), l.getX() + 30, l.getY(), l.getZ());
            default -> l;
        };
    }

    default int baseRotation() {
        return 4;
    }

    default int makeRotation(int r) {
        int i = baseRotation() - r;
        if (i < 0) i += 4;
        if (i > 3) i -= 4;
        System.out.println("rotation: " + i + " because: " + baseRotation() + " - " + r + " = " + i);
        return i;
    }

    default void killStarMob(DungeonMob mob){

    }

    String getId();
}
