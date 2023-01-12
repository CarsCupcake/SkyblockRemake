package me.CarsCupcake.SkyblockRemake.cmd;

import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6.Entitys.*;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.MastermodeNecron;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Necron;
import me.CarsCupcake.SkyblockRemake.End.Dragon.SuperiorDragon;
import me.CarsCupcake.SkyblockRemake.Entitys.IceWalker;
import me.CarsCupcake.SkyblockRemake.FishingSystem.FishingLoottables.LavaFishingSeaCreatures.LordJawbus;
import me.CarsCupcake.SkyblockRemake.FishingSystem.FishingLoottables.LavaFishingSeaCreatures.Thunder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnEntity implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(s.equals("mob")){
            switch (strings[0]){
                case "ICE_WALKER" ->{
                    IceWalker walker = new IceWalker();
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "THUNDER" ->{
                    Thunder walker = new Thunder();
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "LORD_JAWBUS" ->{
                    LordJawbus walker = new LordJawbus();
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "LASR" ->{
                    GiantLASR walker = new GiantLASR();
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "BIGFOOT" ->{
                    GiantBigFoot walker = new GiantBigFoot();
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "DIAMOND" ->{
                    GiantDiamond walker = new GiantDiamond();
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "JOLLY" ->{
                    GiantJollyPink walker = new GiantJollyPink();
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "SADAN" ->{
                    Sadan walker = new Sadan();
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "NECRON" ->{
                    Necron walker = new Necron();
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "MASTERMODE_NECRON" ->{
                    MastermodeNecron walker = new MastermodeNecron();
                    walker.spawn(((Player)commandSender).getLocation());
                }

                default -> commandSender.sendMessage("§cMob not found");
            }
        }
        return false;
    }
}
