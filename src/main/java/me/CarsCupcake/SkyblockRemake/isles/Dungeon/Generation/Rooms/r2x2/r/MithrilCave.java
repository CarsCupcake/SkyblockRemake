package me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Rooms.r2x2.r;

import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Location2d;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Rooms.r2x2.Room2x2;

public class MithrilCave extends Room2x2 {
    @Override
    public String fileLocation() {
        return "assets/schematics/dungeon/rooms/2x2/2x2-Mithril-Cave-10.schematic";
    }

    @Override
    public void init(int rotation, Location2d base) {

    }

    @Override
    public String getId() {
        return "Mithril-Cave-10";
    }
}
