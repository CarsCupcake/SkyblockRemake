package me.CarsCupcake.SkyblockRemake.Items.Gemstones;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum SlotType {
    Mining(GemstoneType.Jade, GemstoneType.Amber, GemstoneType.Topaz),
    Offensive(GemstoneType.Sapphire, GemstoneType.Jasper),
    Combat(GemstoneType.Sapphire, GemstoneType.Amethyst, GemstoneType.Ruby, GemstoneType.Jasper),
    Defensive(GemstoneType.Amethyst, GemstoneType.Ruby, GemstoneType.Opal),
    Universal(GemstoneType.Jade, GemstoneType.Amber, GemstoneType.Topaz, GemstoneType.Sapphire, GemstoneType.Amethyst, GemstoneType.Jasper, GemstoneType.Ruby, GemstoneType.Opal),
    Ruby(GemstoneType.Ruby),
    Amber(GemstoneType.Amber),
    Topaz(GemstoneType.Topaz),
    Jade(GemstoneType.Jade),
    Sapphire(GemstoneType.Sapphire),
    Amethyst(GemstoneType.Amethyst),
    Jasper(GemstoneType.Jasper),
    Opal(GemstoneType.Opal);
    private final GemstoneType[] types;

    SlotType(GemstoneType... types) {
        this.types = types;
    }

    public String getSymbol() {

        return switch (this) {
            case Amber -> "⸕";
            case Amethyst -> "❈";
            case Combat -> "⚔";
            case Defensive -> "☤";
            case Jade -> "☘";
            case Jasper -> "❁";
            case Mining -> "✦";
            case Offensive -> "☠";
            case Opal, Universal -> "❂";
            case Ruby -> "❤";
            case Sapphire -> "✎";
            case Topaz -> "✧";
        };


    }

    public GemstoneType[] getValidTypes() {
        return types;
    }

    public ItemStack getGlassPane() {
        return switch (this) {
            case Amber, Topaz -> new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
            case Amethyst, Mining -> new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
            case Combat, Ruby -> new ItemStack(Material.RED_STAINED_GLASS_PANE);
            case Defensive, Jade -> new ItemStack(Material.LIME_STAINED_GLASS_PANE);
            case Jasper -> new ItemStack(Material.PINK_STAINED_GLASS_PANE);
            case Offensive -> new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
            case Opal, Universal -> new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
            case Sapphire -> new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
        };
    }

    public String getPrefix() {
        return switch (this) {
            case Amber -> "§6";
            case Amethyst, Mining -> "§5";
            case Combat -> "§4";
            case Defensive, Jade -> "§a";
            case Jasper -> "§d";
            case Offensive -> "§9";
            case Ruby -> "§c";
            case Sapphire -> "§b";
            case Topaz -> "§e";
            default -> "§f";
        };
    }


}
