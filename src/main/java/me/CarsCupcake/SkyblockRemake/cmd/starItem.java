package me.CarsCupcake.SkyblockRemake.cmd;

import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.StarHandler;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class starItem implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player p))
            return false;

        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(p);
        if(strings.length == 2 || strings[0].equals("master"))
            try{
                StarHandler.setMasterStars(player.getItemInHand(), Integer.parseInt(strings[1]));
            }catch (Exception e){
                player.sendMessage("§c" +  e.getMessage() + " §7(" + e.getClass().getSimpleName() + ")");
            }
        else if(ItemHandler.hasPDC("id", player.getItemInHand(), PersistentDataType.STRING))
            try{
                StarHandler.setStars(player.getItemInHand(), Integer.parseInt(strings[0]));
            }catch (Exception e){
                player.sendMessage("§c" +  e.getMessage() + " §7(" + e.getClass().getSimpleName() + ")");
            }

        return false;
    }
}
