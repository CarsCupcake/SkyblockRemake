package me.CarsCupcake.SkyblockRemake.cmd.impl.generals;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.NPC.EntityNPC;
import me.CarsCupcake.SkyblockRemake.Settings.ServerSettings;
import me.CarsCupcake.SkyblockRemake.cmd.enhancedCommand.CommandArgument;
import me.CarsCupcake.SkyblockRemake.utils.log.DebugLogger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

@CommandArgument(args = {"bazaar", "ah", "killnpcs", "clickCooldown", "setDataPath", "setUnlimitedMinions", "respawnBoss", "debug", "miningfatuige", "movementlag", "packetlog", "ignorecooldown", "lag", "debugMessengerChannel"})
@CommandArgument(lastArg = {"bazaar", "ah", "killnpcs", "clickCooldown", "setUnlimitedMinions", "debug", "miningfatuige", "movementlag", "packetlog", "ignorecooldown", "lag", "debugMessengerChannel"}, args = {"enable", "disable"}, position = 1)
@CommandArgument(lastArg = "respawnBoss", args = {"Bladesoul", "BarbarianDukeX", "MageOutlaw", "Ashfang", "MagmaBoss"}, position = 1)
@CommandArgument(lastArg = "packetlog", args = {"in", "out", "name", "reset", "toggledetailed"}, position = 1)
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

                ServerSettings.setBazaarEnabled(b);
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

                ServerSettings.setAhEnabled(b);
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
                ServerSettings.setClickCooldownEnabled(b);
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
                ServerSettings.setValue("debug", b);
                commandSender.sendMessage("Debuging is now " + strings[1]);
                DebugLogger.setDebug(b);
            }
            case "miningfatuige" -> {
                boolean b;
                if (strings[1].equals("enable")) {
                    b = true;
                } else if (strings[1].equals("disable")) {
                    b = false;
                } else {
                    commandSender.sendMessage("§cInvalid args!");
                    return false;
                }
                ServerSettings.setValue("miningfatuige", b);
                ServerSettings.setMiningFatuigeEnable(b);
                commandSender.sendMessage("Mining Fatuige is now " + strings[1]);
            }
            case "movementlag" -> {
                boolean b;
                if (strings[1].equals("enable")) {
                    b = true;
                } else if (strings[1].equals("disable")) {
                    b = false;
                } else {
                    commandSender.sendMessage("§cInvalid args!");
                    return false;
                }
                ServerSettings.setValue("movementlag", b);
                ServerSettings.setMovementLag(b);
                commandSender.sendMessage("Movement Lag is now " + strings[1] + " " + ((b) ? "the value is around 200ms" : ""));
            }
            case "packetlog" -> {
                boolean b;
                if (strings[1].equals("enable")) {
                    b = true;
                } else if (strings[1].equals("disable")) {
                    b = false;
                } else {
                    switch (strings[1]){
                        case "in" -> ServerSettings.getPacketLogFilter().toggleIn();
                        case "out" -> ServerSettings.getPacketLogFilter().toggleOut();
                        case "name" -> ServerSettings.getPacketLogFilter().setSearch((strings.length >= 3) ? strings[2] : null);
                        case "reset" -> ServerSettings.getPacketLogFilter().reset();
                        case "toggledetailed" -> ServerSettings.getPacketLogFilter().detailed();
                        default -> commandSender.sendMessage("§cInvalid args!");
                    }
                    return false;
                }
                ServerSettings.setValue("packetlog", b);
                ServerSettings.setPacketLog(b);
                commandSender.sendMessage("Packet Log is now " + strings[1]);
            }
            case "lag" -> {
                boolean b;
                if (strings[1].equals("enable")) {
                    b = true;
                } else if (strings[1].equals("disable")) {
                    b = false;
                } else {
                    commandSender.sendMessage("Wrong arg");
                    return true;
                }
                ServerSettings.setValue("lag", b);
                ServerSettings.setLag(b);
                commandSender.sendMessage("lag is now " + strings[1]);
            }
            case "ignorecooldown" -> {
                boolean b;
                if (strings[1].equals("enable")) {
                    b = true;
                } else if (strings[1].equals("disable")) {
                    b = false;
                } else {
                    commandSender.sendMessage("§cInvalid args!");
                    return false;
                }
                ServerSettings.setValue("ignorecooldown", b);
                ServerSettings.setIgnoreCooldown(b);
                commandSender.sendMessage("Ignore Cooldown is now " + strings[1]);
            }
            case "debugMessengerChannel" -> {
                boolean b;
                if (strings[1].equals("enable")) {
                    b = true;
                } else if (strings[1].equals("disable")) {
                    b = false;
                } else {
                    commandSender.sendMessage("§cInvalid args!");
                    return false;
                }
                ServerSettings.setValue("debugMessengerChannel", b);
                commandSender.sendMessage("Debug Messenger Channel is now " + strings[1]);
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
