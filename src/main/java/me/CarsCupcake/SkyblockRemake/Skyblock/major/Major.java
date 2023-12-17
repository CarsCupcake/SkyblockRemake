package me.CarsCupcake.SkyblockRemake.Skyblock.major;

import java.util.ArrayList;
import java.util.List;

public record Major(Majors major, int... perks) {
    public void launch() {
        major.run(this);
    }
    public static List<Major> generate() {
        List<Major> majors = new ArrayList<>();
        //TODO propper gen rules!
        return List.of(new Major(Majors.Diana, 1, 2, 3));
    }
}
