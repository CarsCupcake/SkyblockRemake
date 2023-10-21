package me.CarsCupcake.SkyblockRemake.Slayer.spider;

import me.CarsCupcake.SkyblockRemake.Skyblock.EntitySkillXp;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Slayer.SlayerLevel;
@EntitySkillXp(50)
public class SpiderSlayer extends SlayerLevel {
    public SpiderSlayer(SkyblockPlayer player) {
        super(player);
    }

    @Override
    protected String getId() {
        return "spider";
    }

    @Override
    public String getName() {
        return "Tarantula Broodfather";
    }

    @Override
    public int[] levelSteps() {
        return new int[]{10, 25, 200, 1_000, 5_000, 20_000, 100_000, 400_000, 1_000_000};
    }

    @Override
    protected void levelUp(int level) {
        getPlayer().sendMessage("Spider leveled up to " + level);
    }

    @Override
    public int getLevel() {
        return 10;
    }
}
