package me.CarsCupcake.SkyblockRemake.cmd;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.NPC.EntityNPC;
import me.CarsCupcake.SkyblockRemake.Settings.InfoManager;
import me.CarsCupcake.SkyblockRemake.cmd.enhancedCommand.CommandArgument;
import me.CarsCupcake.SkyblockRemake.utils.log.DebugLogger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

@CommandArgument(args = {"bazaar", "ah", "killnpcs", "clickCooldown", "setDataPath", "setUnlimitedMinions", "respawnBoss", "debug"})
@CommandArgument(lastArg = {"bazaar", "ah", "killnpcs", "clickCooldown", "setUnlimitedMinions", "debug"}, args = {"enable", "disable"}, position = 1)
@CommandArgument(lastArg = "respawnBoss", args = {"Bladesoul", "BarbarianDukeX", "MageOutlaw", "Ashfang", "MagmaBoss"}, position = 1)
public class SettingsCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (strings.length < 2) return false;

        switch (strings[0]) {
            case "bazaar" -> {

                boolean b;
                if (strings[1].equals("enable")) {
                    b = true;
                } else if (strings[1].equals("disable")) {
                    b = false;
                } else {

                    commandSender.sendMessage("§cInvalid args!");
                    return false;

                }

                InfoManager.setBazaarEnabled(b);
                if (b) commandSender.sendMessage("§eBazzar is now enabled!");
                else commandSender.sendMessage("§eBazzar is now disabled!");

            }
            case "ah" -> {

                boolean b;
                if (strings[1].equals("enable")) {
                    b = true;
                } else if (strings[1].equals("disable")) {
                    b = false;
                } else {

                    commandSender.sendMessage("§cInvalid args!");
                    return false;

                }

                InfoManager.setAhEnabled(b);
                if (b) commandSender.sendMessage("§aAh is now enabled!");
                else commandSender.sendMessage("§cAh is now disabled!");

            }
            case "killnpcs" -> {

                boolean b;
                if (strings[1].equals("enable")) {
                    b = true;
                } else if (strings[1].equals("disable")) {
                    b = false;
                } else {

                    commandSender.sendMessage("§cInvalid args!");
                    return false;

                }

                EntityNPC.isKillable = b;

            }
            case "clickCooldown" -> {
                boolean b;
                if (strings[1].equals("enable")) {
                    b = true;
                } else if (strings[1].equals("disable")) {
                    b = false;
                } else {
                    commandSender.sendMessage("§cInvalid args!");
                    return false;

                }
                InfoManager.setClickCooldownEnabled(b);
            }
            case "setDataPath" -> {
                if (!commandSender.isOp()) return false;

                Main.getMain().reloadConfig();
                String path = getStringFromArgs(Arrays.copyOfRange(strings, 1, strings.length));
                Main.getMain().getConfig().set("SkyblockDataPath", path);
                Main.getMain().saveConfig();

                commandSender.sendMessage("§aDatapath is now " + path);

            }

            case "debug" -> {
                boolean b;
                if (strings[1].equals("enable")) {
                    b = true;
                } else if (strings[1].equals("disable")) {
                    b = false;
                } else {
                    commandSender.sendMessage("§cInvalid args!");
                    return false;
                }
                InfoManager.setValue("debug", b);
                commandSender.sendMessage("Debuging is now " + strings[1]);
                DebugLogger.setDebug(b);
            }

            default -> commandSender.sendMessage("§cThe setting: " + strings[0] + " does not exist.");
        }
        return false;
    }

    private String getStringFromArgs(String[] args) {
        StringBuilder builder = new StringBuilder();
        for (String s : args) {
            builder.append(s).append(" ");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}
