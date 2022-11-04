package me.CarsCupcake.SkyblockRemake.cmd;

import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.WitherGuard;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.WitherMiner;
import me.CarsCupcake.SkyblockRemake.abilitys.EntityLocationSetter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class selectMobPlacer implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {


        if(args.length == 1){
            if(args[0].equals("witherguard")){
                EntityLocationSetter.setEntity(WitherGuard.class);
            }
            if(args[0].equals("witherminer")){
                EntityLocationSetter.setEntity(WitherMiner.class);
            }
        }


        return false;
    }
}
