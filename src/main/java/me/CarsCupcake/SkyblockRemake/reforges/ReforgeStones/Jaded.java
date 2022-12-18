package me.CarsCupcake.SkyblockRemake.reforges.ReforgeStones;

import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Stats;
import me.CarsCupcake.SkyblockRemake.reforges.Reforge;
import me.CarsCupcake.SkyblockRemake.reforges.ReforgeStatPackage;

import java.util.ArrayList;

public class Jaded implements Reforge {
    @Override
    public ReforgeStatPackage CommonStats() {
        ReforgeStatPackage statPackage = new ReforgeStatPackage();
        statPackage.addStats(Stats.MiningSpeed, 5);
        statPackage.addStats(Stats.MiningFortune, 5);
        return statPackage;
    }

    @Override
    public ReforgeStatPackage UncommonStats() {
        ReforgeStatPackage statPackage = new ReforgeStatPackage();
        statPackage.addStats(Stats.MiningSpeed, 12);
        statPackage.addStats(Stats.MiningFortune, 10);
        return statPackage;
    }

    @Override
    public ReforgeStatPackage RareStats() {
        ReforgeStatPackage statPackage = new ReforgeStatPackage();
        statPackage.addStats(Stats.MiningSpeed, 20);
        statPackage.addStats(Stats.MiningFortune, 15);
        return statPackage;
    }

    @Override
    public ReforgeStatPackage EpicStats() {
        ReforgeStatPackage statPackage = new ReforgeStatPackage();
        statPackage.addStats(Stats.MiningSpeed, 30);
        statPackage.addStats(Stats.MiningFortune, 20);
        return statPackage;
    }

    @Override
    public ReforgeStatPackage LegendaryStats() {
        ReforgeStatPackage statPackage = new ReforgeStatPackage();
        statPackage.addStats(Stats.MiningSpeed, 45);
        statPackage.addStats(Stats.MiningFortune, 25);
        return statPackage;
    }

    @Override
    public ReforgeStatPackage MythicStats() {
        ReforgeStatPackage statPackage = new ReforgeStatPackage();
        statPackage.addStats(Stats.MiningSpeed, 60);
        statPackage.addStats(Stats.MiningFortune, 30);
        return statPackage;
    }

    @Override
    public ReforgeStatPackage DivineStats() {
        ReforgeStatPackage statPackage = new ReforgeStatPackage();
        statPackage.addStats(Stats.MiningSpeed, 95);
        statPackage.addStats(Stats.MiningFortune, 55);
        return statPackage;
    }

    @Override
    public ItemType[] Reforgable() {
        return new ItemType[0];
    }

    @Override
    public String getName() {
        return "Jaded";
    }

    @Override
    public ArrayList<String> getLore() {
        return null;
    }
}
