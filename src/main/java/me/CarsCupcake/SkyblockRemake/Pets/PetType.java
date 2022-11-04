package me.CarsCupcake.SkyblockRemake.Pets;

import me.CarsCupcake.SkyblockRemake.Skills;

public enum PetType {
Combat,
Mining,
Enchanting,
Farming,
Alchemy;

public boolean equals(Skills skill) {
	
	if(this == Combat &&skill == Skills.Combat)
		return true;
	if(this == Mining &&skill == Skills.Mining)
		return true;
	if(this == Enchanting &&skill == Skills.Enchanting)
		return true;
	if(this == Farming &&skill == Skills.Farming)
		return true;
	if(this == Alchemy &&skill == Skills.Alchemy)
		return true;
	
	return false;
}

}
