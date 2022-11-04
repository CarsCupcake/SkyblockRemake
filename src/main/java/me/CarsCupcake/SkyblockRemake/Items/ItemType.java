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
	Wand(32);






private ItemType(int i) {
	// TODO Auto-generated constructor stub
}
public String toString() {
	switch(this) {
	case Helmet:
		return "Helmet";
	case Chestplate:
		return "Chestplate";
	case Leggings:
		return "Leggings";
	case Boots:
		return "Boots";
	case Sword:
		return "Sword";
	case Bow:
		return "Bow";
		case Pickaxe:
		return "Pickaxe";
	case Drill:
		return "Drill";
	case Rune:
		return "Rune";
	case Gauntlet:
		return "Gauntlet";
	case Pet:
		return "Pet";
		case Accessory:
		return "Accessory";
	case Necklace:
		return "Necklace";
	case Cloak:
		return "Cloak";
	case Belt:
		return "Belt";
	case Gloves:
		return "Gloves";
		case Bracelet:
			return "Bracelet";
		case Deployable:
			return "Deployable";
		case FishingRod:
			return "Fishing Rod";
		case Axe:
			return "Axe";
		case Wand:
			return "Wand";
		
	default:
		return "";
	
	}
	
}
	
	
	
}
