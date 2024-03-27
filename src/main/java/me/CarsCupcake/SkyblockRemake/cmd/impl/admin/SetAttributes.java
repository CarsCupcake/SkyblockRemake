package me.CarsCupcake.SkyblockRemake.cmd.impl.admin;

import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.attributes.Attribute;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SetAttributes implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length != 4) return false;
        Attribute at1 = Attribute.getAttribute(strings[0]);
        Attribute at2 = Attribute.getAttribute(strings[2]);
        if (at1 == null || at2 == null) {
            commandSender.sendMessage("§cNot registered attributes!");
            return false;
        }
        int lvl1;
        int lvl2;
        try {
            lvl1 = Integer.parseInt(strings[1]);
            lvl2 = Integer.parseInt(strings[3]);
        } catch (Exception ignored) {
            commandSender.sendMessage("§cNot a number!");
            return false;
        }
        if (!(Tools.isInRange(0, 11, lvl1) && Tools.isInRange(0, 11, lvl2))) {
            commandSender.sendMessage("§cLevel is not in range!");
            return false;
        }
        ItemManager manager = ItemHandler.getItemManager(((Player) commandSender).getEquipment().getItemInMainHand());
        if (manager == null) return false;
        if (!manager.isAttributable()) {
            commandSender.sendMessage("§cYou cannot apply attributes to that item");
            return false;
        }
        Attribute.applyAttrebute( new Attribute[]{at1, at2},new int[] {lvl1, lvl2}, ((Player) commandSender).getEquipment().getItemInMainHand());
        Main.itemUpdater(((Player) commandSender).getEquipment().getItemInMainHand(), SkyblockPlayer.getSkyblockPlayer((Player) commandSender));
        return true;
    }
    public static class Tab implements TabCompleter {
        @Nullable
        @Override
        public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
            if (!(args.length == 1 || args.length == 3)) {
                return List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
            }
            List<String> result = new ArrayList<>();
            Set<String> solutions = Attribute.getAttributeIds();

            if(args.length < 5) {
                for (String a : solutions) {
                    if (a.toLowerCase().startsWith(args[args.length - 1].toLowerCase()))
                        result.add(a);
                }
            } else  {
                result.add("");
                return result;
            }
            result.sort(String::compareTo);
            return result;
        }

    }
}
