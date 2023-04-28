package me.CarsCupcake.SkyblockRemake.cmd.enhancedCommand;

import me.CarsCupcake.SkyblockRemake.cmd.CommandArgument;
import me.CarsCupcake.SkyblockRemake.utils.Assert;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class TablistBuilder implements TabCompleter {
    public final HashMap<Comparable<String[]>, String[]> args = new HashMap<>();

    public TablistBuilder(Class<?> o) {
        Assert.isTrue(o.isAnnotationPresent(CommandArgument.List.class), "It does not have any command args!");
        for (CommandArgument argument : o.getAnnotationsByType(CommandArgument.class))
            args.put(new Check(argument), argument.args());
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        List<String> l = new ArrayList<>();
        for (Comparable<String[]> c : args.keySet()) {
            if (c.compareTo(strings) > 0) l.addAll(Arrays.asList(args.get(c)));
        }
        return l;
    }

    private static class Check implements Comparable<String[]> {
        private final int place;
        private final String[] hasToBeTheLast;

        public Check(CommandArgument argument) {
            place = argument.position();
            hasToBeTheLast = argument.lastArg();
        }

        @Override
        public int compareTo(String @NotNull [] o) {
            if (place == 0 && o.length == 0) return 1;
            if (place != o.length) return 0;
            for (String s : hasToBeTheLast)
                if (s.equals(o[o.length - 1])) return 1;

            return 0;
        }
    }
}
