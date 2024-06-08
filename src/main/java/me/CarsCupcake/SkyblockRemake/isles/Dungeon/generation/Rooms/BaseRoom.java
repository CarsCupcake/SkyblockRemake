package me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Rooms;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Dungeon;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.DungeonEnemys;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.DungeonRoomsTypes;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.IRoom;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Location2d;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Location;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class BaseRoom extends IRoom {
    private final String name;
    private final DungeonRoomsTypes type;
    public BaseRoom(String name, DungeonRoomsTypes type) {
        this.name = name;
        this.type = type;
    }
    @Override
    public String fileLocation() {
        return "assets/schematics/dungeon/rooms/" + type.toString() + "/" + name + ".schematic";
    }


    @Override
    public void init(int rotation, Location2d base) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().setLenient().create();
            InputStream stream = Main.getMain().getResource("assets/schematics/dungeon/mobs/" + type.toString() + "/" + name + ".json");
            BufferedReader r = new BufferedReader(new InputStreamReader(stream));
            AtomicReference<String> lines = new AtomicReference<>("");
            r.lines().forEach(l -> lines.set(lines.get() + l));
            JsonParser parser = new JsonParser();
            JsonArray arr = parser.parse(lines.get()).getAsJsonArray();
            r.close();
            boolean hasStarred = false;
            Set<Data> minis = new HashSet<>();
            for (JsonElement e : arr) {
                JsonObject o = e.getAsJsonObject();
                Location location = new Location(Main.getMain().getServer().getWorld("world"), o.get("x").getAsInt(), o.get("Y").getAsInt(), o.get("Z").getAsInt());
                Dungeon.MobAttributes s = Dungeon.MobAttributes.Star;
                Dungeon.MobAttributes[] attributes = new Dungeon.MobAttributes[0];
                if (o.get("starred").getAsBoolean()) {
                    attributes = new Dungeon.MobAttributes[]{s};
                    hasStarred = true;
                }
                switch (o.get("type").getAsString()) {
                    case "melee" -> DungeonEnemys.getFloorPool().spawnMelee(location, this, rotation + 180, base, attributes);
                    case "archer" -> DungeonEnemys.getFloorPool().spawnArcher(location, this, rotation + 180, base, attributes);
                    case "miniboss" -> minis.add(new Data(location, o.get("starred").getAsBoolean()));
                }
            }

            for (Data data : minis) {
                Dungeon.MobAttributes s = Dungeon.MobAttributes.Star;
                Dungeon.MobAttributes[] attributes = new Dungeon.MobAttributes[0];
                if (data.stared || !hasStarred) {
                    attributes = new Dungeon.MobAttributes[]{s};
                }
                DungeonEnemys.getFloorPool().spawnMiniboss(data.location, this, rotation + 180, base, attributes);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    record Data(Location location, boolean stared){}

    @Override
    public Set<Location2d> getNextLocations(Location2d base, int rotation) {
        return Set.of();
    }

    @Override
    public String getId() {
        return name;
    }
}
