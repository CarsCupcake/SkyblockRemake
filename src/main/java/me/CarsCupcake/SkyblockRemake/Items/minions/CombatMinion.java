package me.CarsCupcake.SkyblockRemake.Items.minions;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;

import java.util.UUID;

public class CombatMinion extends AbstractMinion {
    private static final int maxEntityAmount = 6;
    private final AbstractCombatMinion minion;

    /**
     * This constructor provides the basic values
     *
     * @param level            is the level of the minion
     * @param base             is the base item of the minion
     * @param location         is the location where the minion is placed
     * @param minionIdentifier is a string for the minion. This is a random UUID from the method {@link UUID#randomUUID()} and is also used to load the minion from the file
     * @param placer           is the player who owns the isle
     */
    public CombatMinion(int level, AbstractCombatMinion base, Location location, String minionIdentifier, SkyblockPlayer placer) {
        super(level, base, location, minionIdentifier, placer);
        minion = base;
    }

    @Override
    void startGetAnimation() {
        //TODO add animation + functionality
    }

    @Override
    boolean startGenerateAnimation() {
        //TODO add animation + functionality
        return isMaxGenerated();
    }

    @Override
    boolean isMaxGenerated() {
        return location.getWorld().getNearbyEntities(location, 5, 5, 5).stream().filter(entity -> entity.getType() == minion.getEntity().getEntity().getType()).toList().size() >= maxEntityAmount;
    }

    @Override
    int settableSpace() {
        int i = location.getWorld().getNearbyEntities(location, 5, 5, 5).stream().filter(entity -> entity.getType() == minion.getEntity().getEntity().getType()).toList().size();
        if (i > maxEntityAmount) return 0;
        else return maxEntityAmount - i;
    }

}
