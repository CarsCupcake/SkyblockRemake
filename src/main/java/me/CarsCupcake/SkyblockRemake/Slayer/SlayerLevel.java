package me.CarsCupcake.SkyblockRemake.Slayer;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.configs.ConfigFile;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.levels.SkyblockLevelsGetter;

public abstract class SlayerLevel implements SkyblockLevelsGetter {
    @Getter
    private final SkyblockPlayer player;
    private final ConfigFile file;
    public SlayerLevel(SkyblockPlayer player) {
        file = new ConfigFile(player, "slayer");
        this.player = player;
    }
    public int getMaxLevel() {
        return 9;
    }
    protected abstract String getId();
    public abstract String getName();
    public int getLevel() {
        return file.get().getInt(getId() + ".level", 0);
    }
    public int getXp() {
        return file.get().getInt(getId() + ".xp", 0);
    }
    public abstract int[] levelSteps();
    public void addXp(int i) {
        int xp = getXp() + i;
        int level = getLevel();
        file.get().set(getId() + ".xp", xp);
        file.reload();
        if (getMaxLevel() > level && xp > levelSteps()[level]) {
            //Level up
            level++;
            file.get().set(getId() + ".level", level);
            levelUp(level);
        }
        file.save();
    }
    protected abstract void levelUp(int level);

    @Override
    public int getMaxSkyblockXp() {
        return getSbXpBonus(getMaxLevel());
    }

    @Override
    public int getSkyblockXp() {
        return getSbXpBonus(getLevel());
    }
    public static int getSbXpBonus(int level) {
        int xp = 0;
        if (level > 0) xp += 15;
        if (level > 1) xp += 25;
        if (level > 2) xp += 35;
        if (level > 3) xp += 50;
        if (level > 4) xp += 75;
        if (level > 5) xp += 100;
        if (level > 6) xp += 125;
        if (level > 7) xp += 150;
        if (level > 8) xp += 150;
        return xp;
    }
}
