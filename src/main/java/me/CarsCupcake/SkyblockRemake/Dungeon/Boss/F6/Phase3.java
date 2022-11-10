package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6;

import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6.Entitys.Sadan;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Phase3 {
    public Phase3(){
        Sadan sadan = new Sadan(this);
        sadan.spawn(new Location(Bukkit.getWorld("world"), -8.5,69.5,91.5));
    }

    public void end(){
        //Add loot generation here
    }
}
