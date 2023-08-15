package me.CarsCupcake.SkyblockRemake.isles.Dungeon.helper;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class DungeonStickCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(strings[0].equals("clear")) {
            DungeonStickAbility.entrys.clear();
        }else if(strings[0].equals("undo")) {
            DungeonStickAbility.entrys.remove(UUID.fromString(strings[1]));
        }else commandSender.sendMessage("§cInvalid Arguments!");
        return false;
    }
}
