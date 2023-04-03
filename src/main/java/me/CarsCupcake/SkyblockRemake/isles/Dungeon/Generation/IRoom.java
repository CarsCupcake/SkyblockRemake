package me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation;

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
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.InputStream;
import java.util.Set;

public interface IRoom {
    String fileLocation();
    void init(int rotation);
    Set<Location2d> getNextLocations(Location2d base, int rotation);
    /**
     * From DungeonRoomsMod by Quantizr(_risk)
     */
    default Location relativeToActual(Location relative, int rotation, Location2d locationOfCorner) {
        double x = 0;
        double z = 0;
        switch (rotation) {
            case 0 -> {
                x = relative.getX() + locationOfCorner.getMapX();
                z = relative.getZ() + locationOfCorner.getMapY(); //.getY in a point is the MC Z coord
            }
            case 3 -> {
                x = -(relative.getZ() - locationOfCorner.getMapX());
                z = relative.getX() + locationOfCorner.getMapY();
            }
            case 2 -> {
                x = -(relative.getX() - locationOfCorner.getMapX());
                z = -(relative.getZ() - locationOfCorner.getMapY());
            }
            case 1 -> {
                x = relative.getZ() + locationOfCorner.getMapX();
                z = -(relative.getX() - locationOfCorner.getMapY());
            }
        }
        return new Location(relative.getWorld(), x, relative.getY(), z);
    }

    default void place(Location2d location2d, int rotation){
        rotation = makeRotation(rotation);
        Location base = rotationCorner(Generator.getGenerator().to3d(location2d), rotation);
        {
            InputStream stream = Main.getMain().getResource(fileLocation());
            try {
                ClipboardFormat format = BuiltInClipboardFormat.MCEDIT_SCHEMATIC;
                ClipboardReader reader = format.getReader(stream);
                Clipboard clipboard = reader.read();
                ClipboardHolder clipboardHolder = new ClipboardHolder(clipboard);
                clipboardHolder.setTransform(clipboardHolder.getTransform().combine(new AffineTransform().rotateY(rotation * 90)));
                EditSession editSession = WorldEdit.getInstance().newEditSession(BukkitAdapter.adapt(base.getWorld()));
                Operation operation = clipboardHolder.createPaste(editSession).to(BlockVector3.at(base.getX(), base.getY(), base.getZ())).build();
                Operations.complete(operation);
                editSession.close();
            } catch (Exception e) {
                Bukkit.broadcastMessage("§c A schematic failed to load");
                e.printStackTrace();
            } finally {
                try {
                    stream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //WIP: Will be changet TODO
        // 0 = 0°
        // 1 = 90°
        // 2 = 180°
        // 3 = -90° / 270°
    }
    Location rotationCorner(Location l, int rotation);
    default int baseRotation(){
        return 0;
    }
    default int makeRotation(int r){
        r = 4 - baseRotation() + r;
        if(r < 0)
            r += 4;
        if(r > 3)
            r -= 4;
        return r;
    }
    String getId();
}
