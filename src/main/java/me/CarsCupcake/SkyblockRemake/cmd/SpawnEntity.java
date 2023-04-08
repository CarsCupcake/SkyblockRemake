package me.CarsCupcake.SkyblockRemake.cmd;

import me.CarsCupcake.SkyblockRemake.Entities.Idiot;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Boss.F6.Entitys.*;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Boss.F7.MastermodeNecron;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Boss.F7.Necron;
import me.CarsCupcake.SkyblockRemake.Entities.IceWalker;
import me.CarsCupcake.SkyblockRemake.FishingSystem.FishingLoottables.LavaFishingSeaCreatures.LordJawbus;
import me.CarsCupcake.SkyblockRemake.FishingSystem.FishingLoottables.LavaFishingSeaCreatures.Thunder;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.MiniBoss.AngryArcheologist;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.MiniBoss.LostAdventurers.HolyAdventurer;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.MiniBoss.LostAdventurers.SuperiorAdventurer;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.MiniBoss.LostAdventurers.UnstableAdventurer;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.MiniBoss.LostAdventurers.YoungAdventurer;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.SoulEater;
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
                case "SOUL_EATER" -> {
                    SoulEater walker = new SoulEater(7, false);
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "MASTERMODE_SOUL_EATER" -> {
                    SoulEater walker = new SoulEater(7, true);
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "IDIOT" -> {
                    Idiot walker = new Idiot(7, true);
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "MASTERMODE_ANGRY_ARCHAEOLOGIST" -> {
                    AngryArcheologist walker = new AngryArcheologist(10, true);
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "ANGRY_ARCHAEOLOGIST" -> {
                    AngryArcheologist walker = new AngryArcheologist(10, false);
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "MASTERMODE_LOST_ADVENTURER_UNSTABLE" -> {
                    UnstableAdventurer walker = new UnstableAdventurer(10, true);
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "LOST_ADVENTURER_UNSTABLE" -> {
                    UnstableAdventurer walker = new UnstableAdventurer(10, false);
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "MASTERMODE_LOST_ADVENTURER_SUPERIOR" -> {
                    SuperiorAdventurer walker = new SuperiorAdventurer(10, true);
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "LOST_ADVENTURER_SUPERIOR" -> {
                    SuperiorAdventurer walker = new SuperiorAdventurer(10, false);
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "MASTERMODE_LOST_ADVENTURER_YOUNG" -> {
                    YoungAdventurer walker = new YoungAdventurer(10, true);
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "LOST_ADVENTURER_YOUNG" -> {
                    YoungAdventurer walker = new YoungAdventurer(10, false);
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "MASTERMODE_LOST_ADVENTURER_HOLY" -> {
                    HolyAdventurer walker = new HolyAdventurer(10, true);
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "LOST_ADVENTURER_HOLY" -> {
                    HolyAdventurer walker = new HolyAdventurer(10, false);
                    walker.spawn(((Player)commandSender).getLocation());
                }

                default -> commandSender.sendMessage("§cMob not found");
            }
        }
        return false;
    }
}
