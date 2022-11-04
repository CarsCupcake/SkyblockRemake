package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Items.Bonuses;
import me.CarsCupcake.SkyblockRemake.Items.FullSetBonus;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class TestBonus implements FullSetBonus {
public  SkyblockPlayer player;
	@Override
	public void start() {
		System.out.println("Start this shit lol");

	}

	@Override
	public void stop() {
		System.out.println("Bonus got Canceled");

	}

	@Override
	public int getPieces() {
		
		return 4;
	}

	@Override
	public void setPlayer(SkyblockPlayer player) {
		this.player = player;
		
	}

	@Override
	public Bonuses getBonus() {
	
		return Bonuses.TestBonus;
	}
	@Override
	public int getMaxPieces() {
		return 4;
	}

	
	

}
