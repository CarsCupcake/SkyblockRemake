package me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Secrets;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Room;
import me.CarsCupcake.SkyblockRemake.utils.Assert;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public abstract class Secret {
    private static final JsonObject secrets = new Gson().fromJson(new BufferedReader(new InputStreamReader(Main.getMain().getResource("assets/schematics/dungeon/rooms/secretlocations.json"))), JsonObject.class);
    protected final Room room;

    public Secret(@NotNull Room room) {
        Assert.notNull(room);
        Assert.isTrue(!room.isSub(), "Cannot use subrooms!");
        this.room = room;
    }

    public abstract boolean isCompleted();

    public final Room getRoom() {
        return room;
    }

    public static void initSecretsFromJson(Room room, InitLever lever) {
        HashMap<Integer, Bundle<Block, ChestSecret>> levers = new HashMap<>();
        for (JsonElement element : secrets.get(room.getRoom().getId()).getAsJsonArray()){
            JsonObject object = element.getAsJsonObject();
             switch (object.get("category").getAsString()){
                 case "lever" -> levers.put(Integer.parseInt(String.valueOf(object.get("secretName").getAsString().charAt(0))) , new Bundle<>(new Location(Bukkit.getWorld("world"), object.get("x").getAsInt(),object.get("y").getAsInt(),object.get("z").getAsInt()).getBlock(), null));
                 case "chest" -> {
                     if(levers.containsKey(Integer.parseInt(String.valueOf(object.get("secretName").getAsString().charAt(0))))){
                         ChestSecret secret = new ChestSecret(room.getRoom().relativeToActual(new Location(Bukkit.getWorld("world"), object.get("x").getAsInt(),object.get("y").getAsInt(),object.get("z").getAsInt()), room.getRotation(), room.getLocation()).getBlock(),
                                 true, room);
                         int num = Integer.parseInt(String.valueOf(object.get("secretName").getAsString().charAt(0)));
                         Bundle<Block, ChestSecret> b = levers.get(num);
                         b.setLast(secret);
                         levers.put(num, b);
                     }else
                         new ChestSecret(room.getRoom().relativeToActual(new Location(Bukkit.getWorld("world"), object.get("x").getAsInt(),object.get("y").getAsInt(),object.get("z").getAsInt()), room.getRotation(), room.getLocation()).getBlock(),
                                 room);
                 }
                 case "Bat" -> new BatSecret(room, room.getRoom().relativeToActual(new Location(Bukkit.getWorld("world"), object.get("x").getAsInt(),object.get("y").getAsInt(),object.get("z").getAsInt()), room.getRotation(), room.getLocation()));
                 case "item" -> {

                 }
                 case "wither" -> {

                 }
             }
        }
        for (Bundle<Block, ChestSecret> secret : levers.values())
            lever.initLever(secret.getLast(), secret.getFirst(), room);
    }
    public interface InitLever{
        void initLever(@Nullable ChestSecret chest, Block lever, Room base);
    }
}
