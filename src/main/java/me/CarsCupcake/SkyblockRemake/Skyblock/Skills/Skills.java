package me.CarsCupcake.SkyblockRemake.Skyblock.Skills;

import lombok.Getter;

public enum Skills {
	Farming("Farming"),
	Mining("Mining"),
	Combat("Combat"),
	Foraging("Foraging"),
	Fishing("Fishing"),
	Enchanting("Enchanting"),
	Alchemy("Alchemy"),
	Taming("Taming"),
	Dungeoneering("Dungeoneering"),
	Carpentry("Carpentry"),
	Runecrafting("Runecrafting"),
	Social("Social");
	@Getter
	private final String name;

	Skills(String name) {
		this.name = name;
	}
}
