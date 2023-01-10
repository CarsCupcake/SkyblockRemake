package me.CarsCupcake.SkyblockRemake.API;

import lombok.Getter;

public enum HellionShield {
Ashen("§7§lAshen"),
Spirit("§f§lSpirit"),
Auric("§e§lAuric"),
Crystal("§b§lCrystal");
@Getter
        private final String name;
    HellionShield(String name) {
    this.name = name;
    }
}
