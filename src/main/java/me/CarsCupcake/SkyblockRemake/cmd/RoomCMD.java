package me.CarsCupcake.SkyblockRemake.cmd;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Dungeon;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.IRoom;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Location2d;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Rooms.lShaped.r.Melon7;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Rooms.r1x1.r.Andeside2;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Rooms.r1x2.r.Doors5;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Rooms.r1x3.r.Catwalk6;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Rooms.r1x4.r.QuartsKnight7;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Rooms.r2x2.r.MithrilCave;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RoomCMD implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(Dungeon.INSTANCE == null) new Dungeon(7, true);
        int rotation = 0;
        if (strings.length >= 1) rotation = Integer.parseInt(strings[0]);
        long before = System.currentTimeMillis();
        IRoom room;
        if (strings.length == 2) switch (strings[1]) {
            case "1x2" -> room = new Doors5();
            case "1x3" -> room = new Catwalk6();
            case "1x4" -> room = new QuartsKnight7();
            case "2x2" -> room = new MithrilCave();
            case "LShaped" -> room = new Melon7();
            default -> room = new Andeside2();
        }
        else room = new Andeside2();
        room.place(new Location2d(0, 0), rotation);
        new BukkitRunnable() {
            @Override
            public void run() {
                room.discover();
            }
        }.runTask(Main.getMain());
        commandSender.sendMessage("§aThis operation took " + (System.currentTimeMillis() - before) + "ms");
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length != 2) return new ArrayList<>();
        ArrayList<String> args = new ArrayList<>();
        args.add("1x1");
        args.add("1x2");
        args.add("1x3");
        args.add("1x4");
        args.add("2x2");
        args.add("LShaped");
        ArrayList<String> finnal = new ArrayList<>();
        for (String arg : args) {
            if (arg.startsWith(strings[1])) finnal.add(arg);
        }
        return finnal;
    }
}
