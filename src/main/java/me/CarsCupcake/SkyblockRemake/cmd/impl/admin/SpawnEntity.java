package me.CarsCupcake.SkyblockRemake.cmd.impl.admin;

import me.CarsCupcake.SkyblockRemake.Entities.Idiot;
import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.mobs.GaiaConstruct;
import me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.mobs.MinosChampion;
import me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.mobs.MinosInquisitor;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Boss.F6.Entitys.*;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Boss.F7.MastermodeNecron;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Boss.F7.Necron;
import me.CarsCupcake.SkyblockRemake.Entities.IceWalker;
import me.CarsCupcake.SkyblockRemake.FishingSystem.FishingLoottables.LavaFishingSeaCreatures.LordJawbus;
import me.CarsCupcake.SkyblockRemake.FishingSystem.FishingLoottables.LavaFishingSeaCreatures.Thunder;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.miniboss.AngryArcheologist;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.miniboss.lostAdventurers.HolyAdventurer;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.miniboss.lostAdventurers.SuperiorAdventurer;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.miniboss.lostAdventurers.UnstableAdventurer;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.miniboss.lostAdventurers.YoungAdventurer;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.miniboss.ShadowAssassin;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.melee.CryptDreadlord;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.melee.CryptLurker;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.ranged.SoulEater;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.ranged.Withermancer;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.special.Fel;
import me.CarsCupcake.SkyblockRemake.isles.rift.entitys.crux.Shy;
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
                case "SHADOW_ASSASSIN" ->{
                    ShadowAssassin walker = new ShadowAssassin(10, false);
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "MASTERMODE_SHADOW_ASSASSIN" ->{
                    ShadowAssassin walker = new ShadowAssassin(10, true);
                    walker.spawn(((Player)commandSender).getLocation());
                }
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
                case "SHY" -> {
                    Shy walker = new Shy();
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "FEL" -> {
                    Fel walker = new Fel(7, false);
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "MASTERMODE_FEL" -> {
                    Fel walker = new Fel(7, true);
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "CRYPT_DREADLORD" -> {
                    CryptDreadlord walker = new CryptDreadlord(7, false);
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "MASTERMODE_CRYPT_DREADLORD" -> {
                    CryptDreadlord walker = new CryptDreadlord(7, true);
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "CRYPT_LURKER" -> {
                    CryptLurker walker = new CryptLurker(7, false);
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "MASTERMODE_CRYPT_LURKER" -> {
                    CryptLurker walker = new CryptLurker(7, true);
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "WITHERMANCER" -> {
                    Withermancer walker = new Withermancer(7, false);
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "MASTERMODE_WITHERMANCER" -> {
                    Withermancer walker = new Withermancer(7, true);
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "GAIA_CONSTRUCT" -> {
                    GaiaConstruct walker = new GaiaConstruct(ItemRarity.LEGENDARY, null);
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "MINOS_CHAMPION" -> {
                    MinosChampion walker = new MinosChampion(ItemRarity.LEGENDARY, null);
                    walker.spawn(((Player)commandSender).getLocation());
                }
                case "MINOS_INQUISITOR" -> {
                    MinosInquisitor walker = new MinosInquisitor(null);
                    walker.spawn(((Player)commandSender).getLocation());
                }

                default -> commandSender.sendMessage("§cMob not found");
            }
        }
        return false;
    }
}
