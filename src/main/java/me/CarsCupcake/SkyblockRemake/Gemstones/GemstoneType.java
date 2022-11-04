package me.CarsCupcake.SkyblockRemake.Gemstones;

import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;

public enum GemstoneType {
Ruby,
Amber,
Topaz,
Jade,
Sapphire,
Amethyst,
Jasper,
Opal;
	
	public String getPrefix() {
		switch(this) {
		case Amber:
			return "§6";
		case Amethyst:
			return "§5";
		
		case Jade:
			return "§a";
		case Jasper:
			return "§d";
		
		case Opal:
			return "§f";
		case Ruby:
			return "§c";
		case Sapphire:
			return "§b";
		case Topaz:
			return "§e";
		
		default:
			return "§f";
		}
	}

	public int getStats(ItemRarity rarity, GemState state) {
		if(this == Ruby) {
			switch (state) {
			case Rough:{
				switch(rarity) {
				case COMMON:
					return 1;
				case DIVINE:
					return 7;
				case EPIC:
					return 4;
				case LEGENDARY:
					return 5;
				case MYTHIC:
					return 7;
				case RARE:
					return 3;
				case SPECIAL:
					return 7;
				case SUPREME:
					return 7;
				case UNCOMMON:
					return 2;
				case UNDEFINED:
					return 0;
				case VERY_SPECIAL:
					return 7;
				default:
					break;
				
				}
			}
				break;
			case Flawed:{
				switch(rarity) {
				case COMMON:
					return 3;
				case DIVINE:
					return 10;
				case EPIC:
					return 6;
				case LEGENDARY:
					return 8;
				case MYTHIC:
					return 10;
				case RARE:
					return 5;
				case SPECIAL:
					return 10;
				case SUPREME:
					return 10;
				case UNCOMMON:
					return 4;
				case UNDEFINED:
					return 0;
				case VERY_SPECIAL:
					return 10;
				default:
					break;
				
				}
			}
			case Fine:{
				switch(rarity) {
				case COMMON:
					return 4;
				case DIVINE:
					return 14;
				case EPIC:
					return 8;
				case LEGENDARY:
					return 10;
				case MYTHIC:
					return 14;
				case RARE:
					return 6;
				case SPECIAL:
					return 14;
				case SUPREME:
					return 14;
				case UNCOMMON:
					return 5;
				case UNDEFINED:
					return 0;
				case VERY_SPECIAL:
					return 14;
				default:
					break;
				
				}
			}
			case Flawless:{
				switch(rarity) {
				case COMMON:
					return 5;
				case DIVINE:
					return 22;
				case EPIC:
					return 14;
				case LEGENDARY:
					return 18;
				case MYTHIC:
					return 22;
				case RARE:
					return 10;
				case SPECIAL:
					return 22;
				case SUPREME:
					return 22;
				case UNCOMMON:
					return 7;
				case UNDEFINED:
					return 0;
				case VERY_SPECIAL:
					return 22;
				default:
					break;
				
				}
			}
			case Perfect:{
				switch(rarity) {
				case COMMON:
					return 6;
				case DIVINE:
					return 30;
				case EPIC:
					return 18;
				case LEGENDARY:
					return 24;
				case MYTHIC:
					return 30;
				case RARE:
					return 13;
				case SPECIAL:
					return 30;
				case SUPREME:
					return 30;
				case UNCOMMON:
					return 9;
				case UNDEFINED:
					return 0;
				case VERY_SPECIAL:
					return 30;
				default:
					break;
				
				}
			};
			default:
				break;
			
			}
		}
		
		if(this == Amber) {
			switch (state) {
			case Rough:{
				switch(rarity) {
				case COMMON:
					return 4;
				case DIVINE:
					return 28;
				case EPIC:
					return 16;
				case LEGENDARY:
					return 20;
				case MYTHIC:
					return 24;
				case RARE:
					return 12;
				case SPECIAL:
					return 28;
				case SUPREME:
					return 28;
				case UNCOMMON:
					return 8;
				case UNDEFINED:
					return 0;
				case VERY_SPECIAL:
					return 28;
				default:
					break;
				
				}
			}
				break;
			case Flawed:{
				switch(rarity) {
				case COMMON:
					return 6;
				case DIVINE:
					return 36;
				case EPIC:
					return 18;
				case LEGENDARY:
					return 24;
				case MYTHIC:
					return 30;
				case RARE:
					return 14;
				case SPECIAL:
					return 36;
				case SUPREME:
					return 36;
				case UNCOMMON:
					return 10;
				case UNDEFINED:
					return 0;
				case VERY_SPECIAL:
					return 36;
				default:
					break;
				
				}
			}
			case Fine:{
				switch(rarity) {
				case COMMON:
					return 10;
				case DIVINE:
					return 54;
				case EPIC:
					return 28;
				case LEGENDARY:
					return 36;
				case MYTHIC:
					return 45;
				case RARE:
					return 20;
				case SPECIAL:
					return 54;
				case SUPREME:
					return 54;
				case UNCOMMON:
					return 14;
				case UNDEFINED:
					return 0;
				case VERY_SPECIAL:
					return 54;
				default:
					break;
				
				}
			}
			case Flawless:{
				switch(rarity) {
				case COMMON:
					return 14;
				case DIVINE:
					return 92;
				case EPIC:
					return 44;
				case LEGENDARY:
					return 58;
				case MYTHIC:
					return 75;
				case RARE:
					return 30;
				case SPECIAL:
					return 92;
				case SUPREME:
					return 92;
				case UNCOMMON:
					return 20;
				case UNDEFINED:
					return 0;
				case VERY_SPECIAL:
					return 92;
				default:
					break;
				
				}
			}
			case Perfect:{
				switch(rarity) {
				case COMMON:
					return 20;
				case DIVINE:
					return 120;
				case EPIC:
					return 60;
				case LEGENDARY:
					return 80;
				case MYTHIC:
					return 100;
				case RARE:
					return 40;
				case SPECIAL:
					return 120;
				case SUPREME:
					return 120;
				case UNCOMMON:
					return 28;
				case UNDEFINED:
					return 0;
				case VERY_SPECIAL:
					return 120;
				default:
					break;
				
				}
			};
			default:
				break;
			
			}
		}
		
		if(this == Jade) {
			switch (state) {
			case Rough:{
				switch(rarity) {
				case COMMON:
					return 2;
				case UNCOMMON:
					return 4;
				case RARE:
					return 6;
				case EPIC:
					return 8;
				case LEGENDARY:
					return 10;
				case MYTHIC:
					return 12;
				case DIVINE, SPECIAL, SUPREME, VERY_SPECIAL:
					return 14;
				
				case UNDEFINED:
					return 0;
				
				default:
					break;
				
				}
			}
				break;
			case Flawed:{
				switch(rarity) {
				case COMMON:
					return 3;
				case UNCOMMON:
					return 5;
				case RARE:
					return 7;
				case EPIC:
					return 10;
				case LEGENDARY:
					return 14;
				case MYTHIC:
					return 18;
				case DIVINE, SPECIAL, SUPREME, VERY_SPECIAL:
					return 22;
				
				case UNDEFINED:
					return 0;
				
				default:
					break;
				
				}
			}
			case Fine:{
				switch(rarity) {
				case COMMON:
					return 4;
				case UNCOMMON:
					return 7;
				case RARE:
					return 10;
				case EPIC:
					return 15;
				case LEGENDARY:
					return 20;
				case MYTHIC:
					return 25;
				case DIVINE, SPECIAL, SUPREME, VERY_SPECIAL:
					return 30;
				
				case UNDEFINED:
					return 0;
				
				default:
					break;
				
				}
			}
			case Flawless:{
				switch(rarity) {
				case COMMON:
					return 7;
				case UNCOMMON:
					return 10;
				case RARE:
					return 15;
				case EPIC:
					return 20;
				case LEGENDARY:
					return 27;
				case MYTHIC:
					return 35;
				case DIVINE, SPECIAL, SUPREME, VERY_SPECIAL:
					return 44;
				
				case UNDEFINED:
					return 0;
				
				default:
					break;
				
				}
			}
			case Perfect:{
				switch(rarity) {
				case COMMON:
					return 12;
				case UNCOMMON:
					return 14;
				case RARE:
					return 20;
				case EPIC:
					return 30;
				case LEGENDARY:
					return 40;
				case MYTHIC:
					return 50;
				case DIVINE, SPECIAL, SUPREME, VERY_SPECIAL:
					return 60;
				
				case UNDEFINED:
					return 0;
				
				default:
					break;
				
				}
			};
			default:
				break;
			
			}
		}
		
		
		if(this == Sapphire) {
			switch (state) {
			case Rough:{
				switch(rarity) {
				case COMMON:
					return 2;
				case UNCOMMON:
					return 2;
				case RARE:
					return 3;
				case EPIC:
					return 4;
				case LEGENDARY:
					return 5;
				case MYTHIC:
					return 6;
				case DIVINE, SPECIAL, SUPREME, VERY_SPECIAL:
					return 8;
				
				case UNDEFINED:
					return 0;
				
				default:
					break;
				
				}
			}
				break;
			case Flawed:{
				switch(rarity) {
				case COMMON:
					return 4;
				case UNCOMMON:
					return 4;
				case RARE:
					return 5;
				case EPIC:
					return 6;
				case LEGENDARY:
					return 7;
				case MYTHIC:
					return 8;
				case DIVINE, SPECIAL, SUPREME, VERY_SPECIAL:
					return 10;
				
				case UNDEFINED:
					return 0;
				
				default:
					break;
				
				}
			}
			case Fine:{
				switch(rarity) {
				case COMMON:
					return 6;
				case UNCOMMON:
					return 6;
				case RARE:
					return 7;
				case EPIC:
					return 8;
				case LEGENDARY:
					return 9;
				case MYTHIC:
					return 10;
				case DIVINE, SPECIAL, SUPREME, VERY_SPECIAL:
					return 16;
				
				case UNDEFINED:
					return 0;
				
				default:
					break;
				
				}
			}
			case Flawless:{
				switch(rarity) {
				case COMMON:
					return 8;
				case UNCOMMON:
					return 9;
				case RARE:
					return 10;
				case EPIC:
					return 12;
				case LEGENDARY:
					return 14;
				case MYTHIC:
					return 16;
				case DIVINE, SPECIAL, SUPREME, VERY_SPECIAL:
					return 25;
				
				case UNDEFINED:
					return 0;
				
				default:
					break;
				
				}
			}
			case Perfect:{
				switch(rarity) {
				case COMMON:
					return 10;
				case UNCOMMON:
					return 12;
				case RARE:
					return 14;
				case EPIC:
					return 17;
				case LEGENDARY:
					return 20;
				case MYTHIC:
					return 25;
				case DIVINE, SPECIAL, SUPREME, VERY_SPECIAL:
					return 30;
				
				case UNDEFINED:
					return 0;
				
				default:
					break;
				
				}
			};
			default:
				break;
			
			}
		}
		
		if(this == Amethyst) {
			switch (state) {
			case Rough:{
				switch(rarity) {
				case COMMON:
					return 1;
				case UNCOMMON:
					return 2;
				case RARE:
					return 3;
				case EPIC:
					return 4;
				case LEGENDARY:
					return 5;
				case MYTHIC:
					return 7;
				case DIVINE, SPECIAL, SUPREME, VERY_SPECIAL:
					return 9;
				
				case UNDEFINED:
					return 0;
				
				default:
					break;
				
				}
			}
				break;
			case Flawed:{
				switch(rarity) {
				case COMMON:
					return 3;
				case UNCOMMON:
					return 4;
				case RARE:
					return 5;
				case EPIC:
					return 6;
				case LEGENDARY:
					return 8;
				case MYTHIC:
					return 10;
				case DIVINE, SPECIAL, SUPREME, VERY_SPECIAL:
					return 12;
				
				case UNDEFINED:
					return 0;
				
				default:
					break;
				
				}
			}
			case Fine:{
				switch(rarity) {
				case COMMON:
					return 4;
				case UNCOMMON:
					return 5;
				case RARE:
					return 6;
				case EPIC:
					return 8;
				case LEGENDARY:
					return 10;
				case MYTHIC:
					return 14;
				case DIVINE, SPECIAL, SUPREME, VERY_SPECIAL:
					return 18;
				
				case UNDEFINED:
					return 0;
				
				default:
					break;
				
				}
			}
			case Flawless:{
				switch(rarity) {
				case COMMON:
					return 5;
				case UNCOMMON:
					return 7;
				case RARE:
					return 10;
				case EPIC:
					return 14;
				case LEGENDARY:
					return 18;
				case MYTHIC:
					return 22;
				case DIVINE, SPECIAL, SUPREME, VERY_SPECIAL:
					return 25;
				
				case UNDEFINED:
					return 0;
				
				default:
					break;
				
				}
			}
			case Perfect:{
				switch(rarity) {
				case COMMON:
					return 6;
				case UNCOMMON:
					return 9;
				case RARE:
					return 13;
				case EPIC:
					return 18;
				case LEGENDARY:
					return 24;
				case MYTHIC:
					return 30;
				case DIVINE, SPECIAL, SUPREME, VERY_SPECIAL:
					return 35;
				
				case UNDEFINED:
					return 0;
				
				default:
					break;
				
				}
			};
			default:
				break;
			
			}
		}
		
		if(this == Jasper) {
			switch (state) {
			case Rough:{
				switch(rarity) {
				case COMMON:
					return 1;
				case UNCOMMON:
					return 1;
				case RARE:
					return 1;
				case EPIC:
					return 2;
				case LEGENDARY:
					return 2;
				case MYTHIC:
					return 3;
				case DIVINE, SPECIAL, SUPREME, VERY_SPECIAL:
					return 4;
				
				case UNDEFINED:
					return 0;
				
				default:
					break;
				
				}
			}
				break;
			case Flawed:{
				switch(rarity) {
				case COMMON:
					return 2;
				case UNCOMMON:
					return 2;
				case RARE:
					return 2;
				case EPIC:
					return 3;
				case LEGENDARY:
					return 3;
				case MYTHIC:
					return 4;
				case DIVINE, SPECIAL, SUPREME, VERY_SPECIAL:
					return 5;
				
				case UNDEFINED:
					return 0;
				
				default:
					break;
				
				}
			}
			case Fine:{
				switch(rarity) {
				case COMMON:
					return 3;
				case UNCOMMON:
					return 3;
				case RARE:
					return 3;
				case EPIC:
					return 4;
				case LEGENDARY:
					return 4;
				case MYTHIC:
					return 5;
				case DIVINE, SPECIAL, SUPREME, VERY_SPECIAL:
					return 6;
				
				case UNDEFINED:
					return 0;
				
				default:
					break;
				
				}
			}
			case Flawless:{
				switch(rarity) {
				case COMMON:
					return 4;
				case UNCOMMON:
					return 5;
				case RARE:
					return 5;
				case EPIC:
					return 6;
				case LEGENDARY:
					return 7;
				case MYTHIC:
					return 8;
				case DIVINE, SPECIAL, SUPREME, VERY_SPECIAL:
					return 9;
				
				case UNDEFINED:
					return 0;
				
				default:
					break;
				
				}
			}
			case Perfect:{
				switch(rarity) {
				case COMMON:
					return 6;
				case UNCOMMON:
					return 6;
				case RARE:
					return 7;
				case EPIC:
					return 9;
				case LEGENDARY:
					return 10;
				case MYTHIC:
					return 12;
				case DIVINE, SPECIAL, SUPREME, VERY_SPECIAL:
					return 14;
				
				case UNDEFINED:
					return 0;
				
				default:
					break;
				
				}
			};
			default:
				break;
			
			}
		}
			
			return 0;
	}
	
	public double getDoubleStats(ItemRarity rarity, GemState state) {
		
		if(this == Topaz) {
			switch (state) {
			case Rough:{
				switch(rarity) {
				case COMMON:
					return 0.2;
				case UNCOMMON:
					return 0.3;
				case RARE:
					return 0.4;
				case EPIC:
					return 0.4;
				case LEGENDARY:
					return 0.4;
				case MYTHIC:
					return 0.4;
				case DIVINE, SPECIAL, SUPREME, VERY_SPECIAL:
					return 0.5;
				
				case UNDEFINED:
					return 0;
				
				default:
					break;
				
				}
			}
				break;
			case Flawed:{
				switch(rarity) {
				case COMMON:
					return 0.7;
				case UNCOMMON:
					return 0.8;
				case RARE:
					return 0.8;
				case EPIC:
					return 0.8;
				case LEGENDARY:
					return 0.8;
				case MYTHIC:
					return 0.8;
				case DIVINE, SPECIAL, SUPREME, VERY_SPECIAL:
					return 0.9;
				
				case UNDEFINED:
					return 0;
				
				default:
					break;
				
				}
			}
			case Fine:{
				switch(rarity) {
				case COMMON:
					return 1;
				case UNCOMMON:
					return 1;
				case RARE:
					return 1.2;
				case EPIC:
					return 1.2;
				case LEGENDARY:
					return 1.2;
				case MYTHIC:
					return 1.2;
				case DIVINE, SPECIAL, SUPREME, VERY_SPECIAL:
					return 1.3;
				
				case UNDEFINED:
					return 0;
				
				default:
					break;
				
				}
			}
			case Flawless:{
				switch(rarity) {
				case COMMON:
					return 1.4;
				case UNCOMMON:
					return 1.5;
				case RARE:
					return 1.6;
				case EPIC:
					return 1.6;
				case LEGENDARY:
					return 1.6;
				case MYTHIC:
					return 1.6;
				case DIVINE, SPECIAL, SUPREME, VERY_SPECIAL:
					return 1.8;
				
				case UNDEFINED:
					return 0;
				
				default:
					break;
				
				}
			}
			case Perfect:{
				switch(rarity) {
				case COMMON:
					return 1.8;
				case UNCOMMON:
					return 1.8;
				case RARE:
					return 2;
				case EPIC:
					return 2;
				case LEGENDARY:
					return 2;
				case MYTHIC:
					return 2;
				case DIVINE, SPECIAL, SUPREME, VERY_SPECIAL:
					return 2.2;
				
				case UNDEFINED:
					return 0;
				
				default:
					break;
				
				}
			};
			default:
				break;
			
			}
		}
		
		
		return 0;
	}

}
