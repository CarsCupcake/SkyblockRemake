package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Items.Bonuses;
import me.CarsCupcake.SkyblockRemake.Items.FullSetBonus;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class DisgustingHealing implements FullSetBonus {

	public SkyblockPlayer player;
	
	@Override
	public void start() {
		player.addHealingMult(1d);

	}

	@Override
	public void stop() {
		player.addHealingMult(-1d);

	}

	@Override
	public int getPieces() {
		
		return 1;
	}

	@Override
	public void setPlayer(SkyblockPlayer player) {
		this.player = player;

	}

	@Override
	public Bonuses getBonus() {
		
		 return Bonuses.DisgustingHealing;
	}

	@Override
	public int getMaxPieces() {
		return 1;
	}

}
