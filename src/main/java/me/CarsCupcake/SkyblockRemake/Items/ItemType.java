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
    Pet(false),
    Gemstone,
    FuelTank(false),
    DrillEngine(false),
    UpgradeModule(false),
    DrillFuel(false),
    Accessory(false),
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

    @Getter
    private final Type type;
    private final boolean inHandStat;
    ItemType(Type type) {
        type.getTypeList().add(this);
        this.type = type;
        inHandStat = type.inHandStat;
        if (type == Type.Sword || type == Type.Bow) Type.getCombat().add(this);
        if (type != null) Type.getAvaidable().add(this);
    }
    ItemType() {
        type = null;
        inHandStat = true;
    }
    ItemType(boolean inHandStat) {
        type = null;
        this.inHandStat = inHandStat;
    }
    public boolean hasInHandStats() {
        return inHandStat;
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

    @Getter
    public enum Type {
        Sword(true),
        Bow(true),
        Armor(false),
        Tool(true),
        Fishing(true),
        Wand(true),
        Equipment(false);
        @Getter
        private final boolean inHandStat;
        Type(boolean inHandStat) {
            this.inHandStat = inHandStat;
        }
        private final List<ItemType> typeList = new ArrayList<>();
        @Getter
        private static final List<ItemType> combat = new ArrayList<>();
        @Getter
        private static final List<ItemType> avaidable = new ArrayList<>();
    }


}
