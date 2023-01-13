package me.CarsCupcake.SkyblockRemake.Items;

public enum ItemRarity {
UNDEFINED(),
COMMON(),
UNCOMMON(),
RARE(),
EPIC(),
LEGENDARY(),
MYTHIC(),
DIVINE(),
SPECIAL(),
VERY_SPECIAL(),
SUPREME(),
ADMIN();

	ItemRarity() {}
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
			case ADMIN -> "§4§lADMIN";
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
			case SUPREME, ADMIN -> "§4";
			case UNCOMMON -> "§a";
			case MYTHIC -> "§d";
			default -> "§f";
		};
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
}
