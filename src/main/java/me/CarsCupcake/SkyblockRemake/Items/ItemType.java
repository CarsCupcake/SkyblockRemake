package me.CarsCupcake.SkyblockRemake.Items;


public enum ItemType {

    Helmet,
    Chestplate,
    Leggings,
    Boots,
    Sword,
    Bow,
    Non,
    EnchantBook,
    PotatoBook,
    Recom,
    Pickaxe,
    Drill,
    Rune,
    Gauntlet,
    Pet,
    Gemstone,
    FuelTank,
    DrillEngine,
    UpgradeModule,
    DrillFuel,
    Accessory,
    Necklace,
    Cloak,
    Belt,
    Gloves,
    Bracelet,
    PowerStone,
    Deployable,
    FishingRod,
    Axe,
    Wand,
    Longsword,
    Minion,
    Hoe;


    ItemType() {
    }

    public String toString() {
        return switch (this) {
            case Helmet -> "Helmet";
            case Chestplate -> "Chestplate";
            case Leggings -> "Leggings";
            case Boots -> "Boots";
            case Sword -> "Sword";
            case Bow -> "Bow";
            case Pickaxe -> "Pickaxe";
            case Drill -> "Drill";
            case Rune -> "Rune";
            case Gauntlet -> "Gauntlet";
            case Pet -> "Pet";
            case Accessory -> "Accessory";
            case Necklace -> "Necklace";
            case Cloak -> "Cloak";
            case Belt -> "Belt";
            case Gloves -> "Gloves";
            case Bracelet -> "Bracelet";
            case Deployable -> "Deployable";
            case FishingRod -> "Fishing Rod";
            case Axe -> "Axe";
            case Wand -> "Wand";
            case Longsword -> "Longsword";
            case Hoe -> "Hoe";
            default -> "";
        };

    }


}
