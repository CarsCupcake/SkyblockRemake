package me.CarsCupcake.SkyblockRemake.cmd.impl.admin;

import me.CarsCupcake.SkyblockRemake.Skyblock.ServerType;
import me.CarsCupcake.SkyblockRemake.cmd.enhancedCommand.CommandArgument;
import me.CarsCupcake.SkyblockRemake.isles.rift.RiftPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
@CommandArgument(args = {"add", "pause", "set", "resume"})
public class RiftTimeCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(ServerType.getActiveType() != ServerType.Rift){
            commandSender.sendMessage("§cYou are not in the rift!");
            return false;
        }
        RiftPlayer player = RiftPlayer.getRiftPlayer((Player) commandSender);
        if(strings.length == 0 || strings.length > 2){
            player.sendMessage("§c/" + s + " <add|pause|set|resume> <amount?>");
            return false;
        }
        switch (strings[0]){
            case "add" -> {
                if(strings.length != 2){
                    player.sendMessage("§c/" + s + " add <amount>");
                    return false;
                }
                try {
                    player.addRiftTime(Long.parseLong(strings[1]));
                }catch (Exception e){
                    e.printStackTrace();
                    player.sendMessage("§cNot a number!");
                }
            }
            case "set" -> {
                if(strings.length != 2){
                    player.sendMessage("§c/" + s + " set <amount>");
                    return false;
                }
                try {
                    player.setRiftTime(Long.parseLong(strings[1]));
                }catch (Exception e){
                    e.printStackTrace();
                    player.sendMessage("§cNot a number!");
                }
            }
            case "pause" -> {
                if(player.getTimer().isRunning()){
                    player.getTimer().pause();
                }
                if(!player.getTimer().isAdminPause()){
                    player.getTimer().setAdminPause(true);
                    return false;
                }
                player.sendMessage("§cTimer is already Admin paused!");
            }
            case "resume" -> {
                if(!player.getTimer().isAdminPause()){
                    player.sendMessage("§cTimer is not admin paused!");
                    return false;
                }
            }
        }
        player.getTimer().setAdminPause(false);
        if(player.getTimer().canRun()) player.getTimer().start();
        return false;
    }
}
