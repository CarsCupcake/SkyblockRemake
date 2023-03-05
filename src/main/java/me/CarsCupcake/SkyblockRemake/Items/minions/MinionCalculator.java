package me.CarsCupcake.SkyblockRemake.Items.minions;

import java.util.Date;

public class MinionCalculator {
    public static long getSteps(long oldMs, long timeBetweenActions){
        long timeInBetween = new Date().getTime() - oldMs;
        long seconds = timeInBetween/1000;
        long ticks = seconds * 20;
        return (ticks/timeBetweenActions)/2;
    }

}
