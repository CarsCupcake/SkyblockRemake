package me.CarsCupcake.SkyblockRemake.Items.Attributes;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public record AppliedAttribute(Attribute attribute, int level) {
    public boolean allowedToUse(SkyblockPlayer player) {
        return attribute.isAllowed(player);
    }
}
