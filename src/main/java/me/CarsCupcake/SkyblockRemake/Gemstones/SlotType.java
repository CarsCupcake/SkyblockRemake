package me.CarsCupcake.SkyblockRemake.Gemstones;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum SlotType  {
Mining,
Offensive,
Combat,
Defensive,
Universal,
Ruby,
Amber,
Topaz,
Jade,
Sapphire,
Amethyst,
Jasper,
Opal;
	
public String getSymbol() {
	
	switch(this) {
	case Amber:
		return "⸕";
	case Amethyst:
		return "❈";
	case Combat:
		return "⚔";
	case Defensive:
		return "☤";
	case Jade:
		return "☘";
	case Jasper:
		return "❁";
	case Mining:
		return "✦";
	case Offensive:
		return "☠";
	case Opal:
		case Universal:
			return "❂";
	case Ruby:
		return "❤";
	case Sapphire:
		return "✎";
	case Topaz:
		return "✧";
		default:
		return "*";
	
	}
	
		
}

public GemstoneType[] getValidTypes() {
	
	switch(this) {
	case Amber:
		GemstoneType[] g = {GemstoneType.Amber};
		return g;
	case Amethyst:
		GemstoneType[] g1 = {GemstoneType.Amethyst};
		return g1;
	case Combat:
		GemstoneType[] g11 = {GemstoneType.Sapphire, GemstoneType.Amethyst, GemstoneType.Jasper, GemstoneType.Ruby};
		return g11;
	case Defensive:
		GemstoneType[] g2 = {GemstoneType.Amethyst, GemstoneType.Ruby};
		return g2;
	case Jade:
		GemstoneType[] g3 = {GemstoneType.Jade};
		return g3;
	case Jasper:
		GemstoneType[] g4 = {GemstoneType.Jasper};
		return g4;
	case Mining:
		GemstoneType[] g5 = {GemstoneType.Jade, GemstoneType.Amber, GemstoneType.Topaz};
		return g5;
	case Offensive:
		GemstoneType[] g6 = {GemstoneType.Sapphire, GemstoneType.Jasper};
		return g6;
	case Opal:
		GemstoneType[] g7 = {GemstoneType.Opal};
		return g7;
	case Ruby:
		GemstoneType[] g8 = {GemstoneType.Ruby};
		return g8;
	case Sapphire:
		GemstoneType[] g9 = {GemstoneType.Sapphire};
		return g9;
	case Topaz:
		GemstoneType[] g10 = {GemstoneType.Topaz};
		return g10;
	case Universal:
		GemstoneType[] g12 = {GemstoneType.Jade, GemstoneType.Amber, GemstoneType.Topaz, GemstoneType.Sapphire, GemstoneType.Amethyst, GemstoneType.Jasper, GemstoneType.Ruby, GemstoneType.Opal};
		return g12;
	default:
		return null;
	
	}
}
public ItemStack getGlassPane() {
	switch(this) {
	case Amber:
		return new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
	case Amethyst:
		return new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
	case Combat:
		return new ItemStack(Material.RED_STAINED_GLASS_PANE);
	case Defensive:
		return new ItemStack(Material.LIME_STAINED_GLASS_PANE);
	case Jade:
		return new ItemStack(Material.LIME_STAINED_GLASS_PANE);
	case Jasper:
		return new ItemStack(Material.PINK_STAINED_GLASS_PANE);
	case Mining:
		return new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
	case Offensive:
		return new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
	case Opal:
		return new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
	case Ruby:
		return new ItemStack(Material.RED_STAINED_GLASS_PANE);
	case Sapphire:
		return new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
	case Topaz:
		return new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
	case Universal:
		return new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
	default:
		return new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
	
	}
}

public String getPrefix() {
	switch(this) {
	case Amber:
		return "§6";
	case Amethyst:
		return "§5";
	case Combat:
		return "§4";
	case Defensive:
		return "§a";
	case Jade:
		return "§a";
	case Jasper:
		return "§d";
	case Mining:
		return "§5";
	case Offensive:
		return "§9";
	case Opal:
		return "§f";
	case Ruby:
		return "§c";
	case Sapphire:
		return "§b";
	case Topaz:
		return "§e";
	case Universal:
		return "§f";
	default:
		return "§f";
	
	}
}


}
