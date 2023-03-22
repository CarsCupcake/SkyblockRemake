package me.CarsCupcake.SkyblockRemake.Items.requirements;

import me.CarsCupcake.SkyblockRemake.Skyblock.Skills.Skills;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.inventory.ItemStack;

public class SkillRequirement implements Requirement{
    private final Skills skill;
    private final int level;
    public SkillRequirement(Skills skill, int level){
        this.skill = skill;
        this.level = level;
    }
    @Override
    public boolean isAllowedToWear(SkyblockPlayer player, ItemStack item) {
        return player.getSkill(skill).getLevel() >= level;
    }
}
