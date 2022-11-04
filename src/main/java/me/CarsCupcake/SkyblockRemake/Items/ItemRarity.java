package me.CarsCupcake.SkyblockRemake.Items;

public enum ItemRarity {
UNDEFINED(-1),
COMMON(0),
UNCOMMON(1),
RARE(2),
EPIC(3),
LEGENDARY(4),
MYTHIC(5),
DIVINE(6),
SPECIAL(7),
VERY_SPECIAL(8),
SUPREME(9);
private int value;
	ItemRarity(int i) {
	this.value = i;
}
	public String getRarityName() {
		return switch (this) {
			case COMMON -> "§f§lCOMMON";
			case DIVINE -> "§b§lDIVINE";
			case EPIC -> "§5§lEPIC";
			case LEGENDARY -> "§6§lLEGENDARY";
			case RARE -> "§9§lRARE";
			case SPECIAL -> "§c§lSPECIAL";
			case SUPREME -> "§4§lSUPREME";
			case UNCOMMON -> "§a§lUNCOMMON";
			case VERY_SPECIAL -> "§c§lVERY SPECIAL";
			case MYTHIC -> "§d§lMYTHIC";
			default -> "§f§lUNDEFINED";
		};
	}
	public String getPrefix() {
		return switch (this) {
			case DIVINE -> "§b";
			case EPIC -> "§5";
			case LEGENDARY -> "§6";
			case RARE -> "§9";
			case SPECIAL, VERY_SPECIAL -> "§c";
			case SUPREME -> "§4";
			case UNCOMMON -> "§a";
			case MYTHIC -> "§d";
			default -> "§f";
		};
	}
	public int toInt() {
		return value;
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
			default -> UNDEFINED;
		};
	}
}
