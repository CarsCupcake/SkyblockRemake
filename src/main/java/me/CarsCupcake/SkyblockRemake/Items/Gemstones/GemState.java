package me.CarsCupcake.SkyblockRemake.Items.Gemstones;

import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;

public enum GemState {
Rough(),
Flawed(),
Fine(),
Flawless(),
Perfect();

public ItemRarity getRarity() {
    return switch (this) {
        case Rough -> ItemRarity.COMMON;
        case Flawed -> ItemRarity.UNCOMMON;
        case Fine -> ItemRarity.RARE;
        case Flawless -> ItemRarity.EPIC;
        case Perfect -> ItemRarity.LEGENDARY;
    };
}
public String toString() {
    return switch (this) {
        case Fine -> "Fine";
        case Flawed -> "Flawed";
        case Flawless -> "Flawless";
        case Perfect -> "Perfect";
        case Rough -> "Rought";
    };
}

}
