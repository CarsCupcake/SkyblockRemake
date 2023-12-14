package me.CarsCupcake.SkyblockRemake.cmd;

import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Items.reforges.RegisteredReforges;
import org.jetbrains.annotations.NotNull;

public class AddReforge implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender arg0, @NotNull Command arg1, @NotNull String arg2, String[] arg3) {
        Player player = (Player) arg0;
        if (arg3.length != 1)
            return false;
        if (arg3[0].equals("remove")) {
            ItemStack item = player.getEquipment().getItemInMainHand();
            ItemHandler.removePDC("reforge", item);
            Main.itemUpdater(item, SkyblockPlayer.getSkyblockPlayer(player));
            return false;
        }
        if (RegisteredReforges.reforges.containsKey(arg3[0])) {
            ItemStack item = player.getEquipment().getItemInMainHand();
            ItemHandler.setPDC("reforge", item, PersistentDataType.STRING, arg3[0]);
            Main.itemUpdater(item, SkyblockPlayer.getSkyblockPlayer(player));
        }else player.sendMessage("Illegal reforge");
        return false;
    }

}
