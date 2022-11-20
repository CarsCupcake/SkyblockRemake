package me.CarsCupcake.SkyblockRemake.Gemstones;

import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;

public enum GemState {
Rough(0),
Flawed(1),
Fine(2),
Flawless(3),
Perfect(4);

GemState(int i) {
}

public ItemRarity getRarity() {
	switch (this) {
	case Rough:
		return ItemRarity.COMMON;
	case Flawed:
		return ItemRarity.UNCOMMON;
	case Fine:
		return ItemRarity.RARE;
	case Flawless:
		return ItemRarity.EPIC;
	case Perfect:
		return ItemRarity.LEGENDARY;
	default:
		return ItemRarity.UNDEFINED;
		
	
	}
}
public String toString() {
	switch (this) {
	case Fine:
		return "Fine";
	case Flawed:
		return "Flawed";
	case Flawless:
		return "Flawless";
	case Perfect:
		return "Perfect";
	case Rough:
		return "Rought";
	default:
		return "";
	
	}
}

}
