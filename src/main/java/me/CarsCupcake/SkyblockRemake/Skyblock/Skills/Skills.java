package me.CarsCupcake.SkyblockRemake.Skyblock.Skills;

import lombok.Getter;

public enum Skills {
	Farming,
	Mining,
	Combat,
	Foraging,
	Fishing,
	Enchanting,
	Alchemy,
	Taming,
	Dungeoneering,
	Carpentry,
	Runecrafting,
	Social;
	@Getter
	private double xp = 0;
	@Getter
	private boolean hasXp = false;
	public void addXpToInstance(double xp){
		this.xp = xp;
		hasXp = true;
	}

}
