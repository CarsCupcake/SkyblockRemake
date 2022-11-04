package me.CarsCupcake.SkyblockRemake.cmd;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Skyblock.Jerry.Deliverys.CoinsDelivery;
import me.CarsCupcake.SkyblockRemake.Skyblock.Jerry.Deliverys.ItemDelivery;
import me.CarsCupcake.SkyblockRemake.Skyblock.Jerry.IDelivery;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import javax.tools.Tool;

public class DeliverCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(s.equals("deliver")){
            if(strings[0].equals("coins")){
                IDelivery delivery = new CoinsDelivery(SkyblockPlayer.getSkyblockPlayer(Bukkit.getPlayer(strings[1])), Tools.StringToDouble(strings[2]));
                delivery.save();
                commandSender.sendMessage("§aDelivery was succesfully sent");
            }
            if(strings[0].equals("item")){
                SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(Bukkit.getPlayer(strings[1]));
                ItemManager manager = Items.SkyblockItems.get(strings[2]);
                int amount = Integer.parseInt(strings[3]);
                IDelivery delivery = new ItemDelivery(manager, amount, player);
                delivery.save();
                commandSender.sendMessage("§aDelivery was succesfully sent");
            }
        }
        return false;
    }
}
