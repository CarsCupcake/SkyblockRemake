package me.CarsCupcake.SkyblockRemake.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SettingsTAB implements TabCompleter {
    List<String> arguments = new ArrayList<>();
    @Nullable
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        List<String> result = new ArrayList<>();
        if(arguments.isEmpty()) {
            arguments.add("bazaar");
            arguments.add("killnpcs");
            arguments.add("setDataPath");
            arguments.add("ah");
        }

        if(args.length == 1) {
            for (String a : arguments) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);
            }
            return result;
        }

        if (args.length == 2) {
            switch (args[0]){
                case "bazaar", "killnpcs", "ah" ->{
                    ArrayList<String> ar = new ArrayList<>();
                    ar.add("enable");
                    ar.add("disable");
                    for (String a : ar) {
                        if (a.toLowerCase().startsWith(args[1].toLowerCase()))
                            result.add(a);
                    }
                }
                default -> {
                    for (String a : arguments) {
                        a = "";
                        result.add(a);
                    }
                }
            }


            return result;
        }
        if (args.length >= 3) {
            for (String a : arguments) {
                a = "";
                result.add(a);
            }
            return result;
        }



        return null;
    }
}
