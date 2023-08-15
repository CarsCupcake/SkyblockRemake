package me.CarsCupcake.SkyblockRemake.isles.Dungeon.helper;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.IRoom;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Location2d;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Rooms.lShaped.RoomLSh;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Rooms.r1x1.Room1x1;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Rooms.r1x2.Room1x2;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Rooms.r1x3.Room1x3;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Rooms.r1x4.Room1x4;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Rooms.r2x2.Room2x2;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CompileCommand implements CommandExecutor {
    public static File current = null;
    private static ArrayDeque<File> room = null;
    public static String type = "1x1";
    public static String name = "N/A";
    public static String secretCount = "0";
    public static int rotation = 0;

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length >= 1 && strings[0].equals("load")) {
            if (room == null) load();
            if (room.isEmpty()) {
                commandSender.sendMessage("§aRooms loading! :D");
                return true;
            }
            if (current == null)
                current = room.pop();
            if (strings.length >= 2)
                rotation = Integer.parseInt(strings[1]);
            else
                rotation = 0;
            String[] args = current.getName().split("-");
            type = args[0];
            var room = getRoom(type);
            if (type.equals("L")) {
                type = "L-shape";
                name = args[2];
                secretCount = args[3].split("\\.")[0];
            } else {
                name = args[1];
                secretCount = args[2];
            }
            room.place(new Location2d(0, 0), (strings.length == 3) ? Integer.parseInt(strings[2]) : 0);
        } else {
            if (current == null) return true;
            Map<String, String> placeholders = new HashMap<>();
            placeholders.put("roomType", "r" + type);
            placeholders.put("roomName", name + secretCount);
            placeholders.put("roomTypeShort", type);
            placeholders.put("roomId", name + "-" + secretCount);
            placeholders.put("filename", current.getName());
            StringBuilder sa = new StringBuilder("{");
            StringBuilder na = new StringBuilder("{");
            StringBuilder sm = new StringBuilder("{");
            StringBuilder um = new StringBuilder("{");
            StringBuilder si = new StringBuilder("{");
            StringBuilder ui = new StringBuilder("{");
            for (DungeonStickAbility.EntityHolder holder : DungeonStickAbility.entrys.values()) {
                switch (holder.type()) {
                    case Melee -> {
                        if (holder.isStared()) sm.append(serializeLocation(holder.location()));
                        else um.append(serializeLocation(holder.location()));
                    }
                    case Arch -> {
                        if (holder.isStared()) sa.append(serializeLocation(holder.location()));
                        else na.append(serializeLocation(holder.location()));
                    }
                    case MiniBoss -> {
                        if (holder.isStared()) si.append(serializeLocation(holder.location()));
                        else ui.append(serializeLocation(holder.location()));
                    }
                }
            }
            String as = ((sa.length() > 2) ? sa.substring(0, sa.length() - 2) : "{") + "}";
            String an = ((na.length() > 2) ? na.substring(0, na.length() - 2) : "{") + "}";
            String ms = ((sm.length() > 2) ? sm.substring(0, sm.length() - 2) : "{") + "}";
            String mn = ((um.length() > 2) ? um.substring(0, um.length() - 2) : "{") + "}";
            String is = ((si.length() > 2) ? si.substring(0, si.length() - 2) : "{") + "}";
            String in = ((ui.length() > 2) ? ui.substring(0, ui.length() - 2) : "{") + "}";
            placeholders.put("unstaredArch", an);
            placeholders.put("staredArch", as);
            placeholders.put("unstaredMelee", mn);
            placeholders.put("staredMelee", ms);
            placeholders.put("unstaredMini", in);
            placeholders.put("staredMini", is);
            File dir = new File(Main.getMain().getDataFolder(), "saves");
            if (!dir.exists()) dir.mkdir();
            File out = new File(dir, name + secretCount + ".java");
            try {
                out.createNewFile();
                Writer writer = new BufferedWriter(new FileWriter(out));
                new BufferedReader(new InputStreamReader(Main.getMain().getResource("assets/schematics/dungeon/helper/template.txt"))).lines().forEach(line -> {
                    for (String key : placeholders.keySet()) {
                        line = key.replace(key, placeholders.get(key));
                    }
                    try {
                        writer.write(line);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                writer.close();
                current.delete();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            current = null;
        }
        return false;
    }
    private String serializeLocation(Location location) {
        return "new Location(Main.getMain().getServer().getWorld(\"" + location.getWorld().getName() + "\"), " + location.getX() + ", " + location.getY() + ", " + location.getZ() + "), ";
    }

    public void load() {
        room = new ArrayDeque<>(Arrays.asList(new File(Main.getMain().getDataFolder(), "load").listFiles()));
    }

    public IRoom getRoom(String type) {
        return switch (type) {
            case "1x1" -> new Room1x1() {
                @Override
                public String fileLocation() {
                    return current.getPath();
                }

                @Override
                public InputStream fileStram() {
                    try {
                        return new FileInputStream(current);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public int baseRotation() {
                    return rotation;
                }

                @Override
                public void init(int rotation, Location2d base) {
                }

                @Override
                public String getId() {
                    return "IGNORE";
                }

            };
            case "1x2" -> new Room1x2() {
                @Override
                public String fileLocation() {
                    return current.getPath();
                }

                @Override
                public InputStream fileStram() {
                    try {
                        return new FileInputStream(current);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public void init(int rotation, Location2d base) {
                }

                @Override
                public String getId() {
                    return "IGNORE";
                }

                @Override
                public int baseRotation() {
                    return rotation;
                }

            };
            case "1x3" -> new Room1x3() {
                @Override
                public String fileLocation() {
                    return current.getPath();
                }

                @Override
                public int baseRotation() {
                    return rotation;
                }

                @Override
                public InputStream fileStram() {
                    try {
                        return new FileInputStream(current);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public void init(int rotation, Location2d base) {
                }

                @Override
                public String getId() {
                    return "IGNORE";
                }

            };
            case "1x4" -> new Room1x4() {
                @Override
                public String fileLocation() {
                    return current.getPath();
                }

                @Override
                public int baseRotation() {
                    return rotation;
                }

                @Override
                public InputStream fileStram() {
                    try {
                        return new FileInputStream(current);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public void init(int rotation, Location2d base) {
                }

                @Override
                public String getId() {
                    return "IGNORE";
                }

            };
            case "2x2" -> new Room2x2() {
                @Override
                public String fileLocation() {
                    return current.getPath();
                }

                @Override
                public int baseRotation() {
                    return rotation;
                }

                @Override
                public InputStream fileStram() {
                    try {
                        return new FileInputStream(current);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public void init(int rotation, Location2d base) {
                }

                @Override
                public String getId() {
                    return "IGNORE";
                }

            };
            case "L" -> new RoomLSh() {
                @Override
                public String fileLocation() {
                    return current.getPath();
                }

                @Override
                public int baseRotation() {
                    return rotation;
                }

                @Override
                public InputStream fileStram() {
                    try {
                        return new FileInputStream(current);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public void init(int rotation, Location2d base) {
                }

                @Override
                public String getId() {
                    return "IGNORE";
                }

            };
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
