package me.CarsCupcake.SkyblockRemake.elements;

import lombok.Getter;
import org.bukkit.ChatColor;

public enum Element {
    Pyro("\uD83D\uDD25", ChatColor.RED),
    Dendro("❣", ChatColor.DARK_GREEN),
    Cryo("❆", ChatColor.AQUA),
    Hydro("\uD83C\uDF0A", ChatColor.BLUE),
    Geo("✧", ChatColor.GOLD),
    Electro("⚡", ChatColor.DARK_PURPLE),
    Anemo("⚚", ChatColor.GREEN);
    @Getter
    private final String symbol;
    @Getter
    private final ChatColor color;

    Element(String sign, ChatColor chatColor) {
        symbol = sign;
        color = chatColor;
    }
}
