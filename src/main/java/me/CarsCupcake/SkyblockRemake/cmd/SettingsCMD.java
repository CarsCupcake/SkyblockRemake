package me.CarsCupcake.SkyblockRemake.cmd;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.NPC.EntityNPC;
import me.CarsCupcake.SkyblockRemake.Settings.InfoManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class SettingsCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(strings.length < 2)
            return false;

        switch (strings[0]){
            case "bazaar" ->{

                boolean b;
                if(strings[1].equals("enable")){
                    b = true;
                }else if(strings[1].equals("disable")){
                    b = false;
                }else {

                    commandSender.sendMessage("§cInvalid args!");
                    return false;

                }

                InfoManager.setBazaarEnabled(b);
                if(b)
                    commandSender.sendMessage("§eBazzar is now enabled!");
                else
                    commandSender.sendMessage("§eBazzar is now disabled!");

            }
            case "ah" ->{

                boolean b;
                if(strings[1].equals("enable")){
                    b = true;
                }else if(strings[1].equals("disable")){
                    b = false;
                }else {

                    commandSender.sendMessage("§cInvalid args!");
                    return false;

                }

                InfoManager.setAhEnabled(b);
                if(b)
                    commandSender.sendMessage("§aAh is now enabled!");
                else
                    commandSender.sendMessage("§cAh is now disabled!");

            }
            case "killnpcs" ->{

                boolean b;
                if(strings[1].equals("enable")){
                    b = true;
                }else if(strings[1].equals("disable")){
                    b = false;
                }else {

                    commandSender.sendMessage("§cInvalid args!");
                    return false;

                }

                EntityNPC.isKillable = b;

            }
            case "setDataPath" ->{
                if(!commandSender.isOp())
                    return false;

                Main.getMain().reloadConfig();
                String path = getStringFromArgs(Arrays.copyOfRange(strings, 1, strings.length - 1));
                Main.getMain().getConfig().set("SkyblockDataPath", path);
                Main.getMain().saveConfig();

                commandSender.sendMessage("§aDatapath is now " + path);

            }
            default -> commandSender.sendMessage("§cThe setting: " + strings[0] + " does not exist.");
        }

        return false;
    }
    private String getStringFromArgs(String[] args){
        StringBuilder builder = new StringBuilder();
        for(String s : args){
            builder.append(s).append(" ");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}
