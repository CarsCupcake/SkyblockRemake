package me.CarsCupcake.SkyblockRemake.Items;



public enum ItemType {

Helmet(1),
Chestplate(2),
Leggings(3),
Boots(4),
Sword(5),
Bow(6),
Non(7),
EnchantBook(8),
PotatoBook(9),
Recom(10),
Pickaxe(11),
Drill(12),
Rune(13),
Gauntlet(14),
Pet(15),
Gemstone(16),
FuelTank(17),
DrillEngine(18),
UpgradeModule(19),
DrillFuel(20),
Accessory(22),
Necklace(23),
Cloak(24),
Belt(25),
Gloves(26),
Bracelet(27),
	PowerStone(28),
	Deployable(29),
	FishingRod(30),
	Axe(31),
	Wand(32),
	Longsword(32);






ItemType(int i) {
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
		default -> "";
	};
	
}
	
	
	
}
