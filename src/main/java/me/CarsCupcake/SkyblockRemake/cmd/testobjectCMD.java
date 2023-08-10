package me.CarsCupcake.SkyblockRemake.cmd;


import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.NPC.Questing.DialogBuilder;
import me.CarsCupcake.SkyblockRemake.NPC.disguise.PlayerDisguise;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.special.Fel;
import me.CarsCupcake.SkyblockRemake.utils.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class testobjectCMD implements CommandExecutor {
    private PlayerDisguise disguise;
    private static final DialogBuilder testDialog = new DialogBuilder(new Sound(org.bukkit.Sound.ENTITY_VILLAGER_AMBIENT, 1, 1)).withTextPrefix("§e[Joe]§r: ").addText("Hi!").addDialogOption(new Bundle<>("Howdy", new DialogBuilder(new Sound(org.bukkit.Sound.ENTITY_VILLAGER_AMBIENT, 1, 1)).withTextPrefix("§e[Joe]§r: ").addText("Bruh")), new Bundle<>("Hi", new DialogBuilder(new Sound(org.bukkit.Sound.ENTITY_VILLAGER_AMBIENT, 1, 1)).withTextPrefix("§e[Joe]§r: ").addText("Hi")));

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("testobject")) {
            if (!(sender instanceof Player p)) {
                sender.sendMessage("Du kannst das net");
                return true;
            }

            Fel fel = new Fel(7,true);
            fel.spawn(p.getLocation().add(10, 0, 0));

            return true;
        }

        return false;
    }

}
