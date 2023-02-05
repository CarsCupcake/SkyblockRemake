package me.CarsCupcake.SkyblockRemake.Skyblock;


import me.CarsCupcake.SkyblockRemake.Skyblock.Skills.Skills;

import java.util.Map;

public interface SkillDroper {
    Map<Skills, Double> getSkillXp();
}
