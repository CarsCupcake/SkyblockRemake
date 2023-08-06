package me.CarsCupcake.SkyblockRemake.Slayer;

import lombok.Getter;

public enum Slayers {
    Zombie("Revenant Horror"),
    Spider("Tarantular Brothfather"),
    Wolf("Sven Packmaster"),
    Enderman("Voidgloom Seraph"),
    Blaze("Inferno Demonlord");
    @Getter
    private final String name;
    Slayers(String name) {
        this.name = name;
    }
}
