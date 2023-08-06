package me.CarsCupcake.SkyblockRemake.Items.requirements;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Slayer.Slayers;
import org.bukkit.inventory.ItemStack;

public class SlayerRequirement implements Requirement{
    private final Slayers slayer;
    private final int level;
    public SlayerRequirement(Slayers slayer, int level) {
        this.slayer = slayer;
        this.level = level;
    }

    @Override
    public boolean isAllowedToWear(SkyblockPlayer player, ItemStack item) {
        return true;
    }

    @Override
    public String requirementNotification() {
        return "§4☠ §cRequires §5" + slayer.getName() + " " + level + ".";
    }
}
