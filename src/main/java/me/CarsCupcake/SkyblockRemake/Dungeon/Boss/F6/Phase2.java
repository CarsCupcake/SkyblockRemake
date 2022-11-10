package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6;

import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6.Entitys.GiantBigFoot;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6.Entitys.GiantDiamond;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6.Entitys.GiantJollyPink;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6.Entitys.GiantLASR;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class Phase2 {
    private int giants = 4;
    private final BossBar bar;
    public Phase2(){
        bar = Bukkit.createBossBar("§cSadan's Giants", BarColor.PURPLE, BarStyle.SOLID);
        for (Player p : Bukkit.getOnlinePlayers())
            bar.addPlayer(p);
        GiantJollyPink jollyPink = new GiantJollyPink(this);
        jollyPink.spawn(new Location(Bukkit.getWorld("world"), -16.5, 69, 53.5));

        GiantBigFoot bigFoot = new GiantBigFoot(this);
        bigFoot.spawn(new Location(Bukkit.getWorld("world"), -0.5, 69, 79.5));

        GiantDiamond diamond = new GiantDiamond(this);
        diamond.spawn(new Location(Bukkit.getWorld("world"), -16.5, 69, 79.5));

        GiantLASR lasr = new GiantLASR(this);
        lasr.spawn(new Location(Bukkit.getWorld("world"), -0.5, 69, 53.5));


    }
    public void killGiant(){
        giants--;
        if(giants != 0){
            bar.setProgress(giants/4d);
        }else {
            bar.removeAll();
            new Phase3();
        }

    }
}
