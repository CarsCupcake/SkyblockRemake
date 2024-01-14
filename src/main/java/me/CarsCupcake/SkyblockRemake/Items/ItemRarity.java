package me.CarsCupcake.SkyblockRemake.Items;

import lombok.Getter;

public enum ItemRarity {
    UNDEFINED("§f§lUNDEFINED", "§f"),
    COMMON("§f§lCOMMON", "§f"),
    UNCOMMON("§a§lUNCOMMON", "§a"),
    RARE("§9§lRARE", "§9"),
    EPIC("§5§lEPIC", "§5"),
    LEGENDARY("§6§lLEGENDARY", "§6"),
    MYTHIC("§d§lMYTHIC", "§d"),
    DIVINE("§b§lDIVINE", "§b"),
    SPECIAL("§c§lSPECIAL", "§c"),
    VERY_SPECIAL("§c§lVERY SPECIAL", "§c"),
    SUPREME("§4§lSUPREME", "§4"),
    ADMIN("§4§lADMIN", "§4");
    private final String name;
    @Getter
    private final String prefix;

    ItemRarity(String name, String prefix) {
        this.name = name;
        this.prefix = prefix;
    }

    public String getRarityName() {
        return name;
    }

    public ItemRarity getNext() {
        return switch (this) {
            case COMMON -> UNCOMMON;
            case DIVINE -> SPECIAL;
            case EPIC -> LEGENDARY;
            case LEGENDARY -> MYTHIC;
            case RARE -> EPIC;
            case SPECIAL -> VERY_SPECIAL;
            case SUPREME, VERY_SPECIAL -> SUPREME;
            case UNCOMMON -> RARE;
            case MYTHIC -> DIVINE;
            case ADMIN -> ADMIN;
            default -> UNDEFINED;
        };
    }

    public ItemRarity getBefore() {
        return switch (this) {
            case COMMON -> COMMON;
            case DIVINE -> MYTHIC;
            case EPIC -> RARE;
            case LEGENDARY -> EPIC;
            case RARE -> UNCOMMON;
            case SPECIAL -> DIVINE;
            case SUPREME, VERY_SPECIAL -> SPECIAL;
            case UNCOMMON -> COMMON;
            case MYTHIC -> LEGENDARY;
            case ADMIN -> ADMIN;
            default -> UNDEFINED;
        };
    }
}
