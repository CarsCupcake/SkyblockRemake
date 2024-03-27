package me.CarsCupcake.SkyblockRemake.Items.attributes;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public record AppliedAttribute(Attribute attribute, int level) {
    @SuppressWarnings("unused")
    public boolean allowedToUse(SkyblockPlayer player) {
        return attribute.isAllowed(player);
    }
}
