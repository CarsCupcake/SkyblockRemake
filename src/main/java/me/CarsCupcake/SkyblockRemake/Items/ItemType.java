package me.CarsCupcake.SkyblockRemake.Items;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public enum ItemType {

    Helmet(Type.Armor),
    Chestplate(Type.Armor),
    Leggings(Type.Armor),
    Boots(Type.Armor),
    Sword(Type.Sword),
    Bow(Type.Bow),
    Non,
    EnchantBook,
    Pickaxe(Type.Tool),
    Drill(Type.Tool),
    Rune,
    Gauntlet(Type.Tool),
    Pet,
    Gemstone,
    FuelTank,
    DrillEngine,
    UpgradeModule,
    DrillFuel,
    Accessory,
    Necklace(Type.Equipment),
    Cloak(Type.Equipment),
    Belt(Type.Equipment),
    Gloves(Type.Equipment),
    Bracelet(Type.Equipment),
    PowerStone,
    Deployable,
    FishingRod(Type.Fishing),
    Axe(Type.Tool),
    Wand(Type.Wand),
    Longsword(Type.Sword),
    Minion,
    Hoe(Type.Tool);



    ItemType(Type type) {
        type.getTypeList().add(this);
    }
    ItemType() {}


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

    @Getter
    public enum Type {
        Sword,
        Bow,
        Armor,
        Tool,
        Fishing,
        Wand,
        Equipment;
        private final List<ItemType> typeList = new ArrayList<>();
        @Getter
        private static final List<ItemType> combat = new ArrayList<>();
    }


}
