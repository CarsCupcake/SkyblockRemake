package me.CarsCupcake.SkyblockRemake.Items;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.abilitys.BruteForce;

public enum Bonuses {

	DisgustingHealing,
	TestBonus,
	Dominus,
	HydraStrike,
	Fervor,
	ArcaneVision,
	DctrSpaceHelmet,
	Maid,
	BruteForce,
	StaticCharge,
	MagmaLordArmor,
	Spirit,
	SuperiorBlood;




	
	public FullSetBonus getBonus(SkyblockPlayer player) {
		FullSetBonus bonus;
		switch (this) {
			case TestBonus -> {
				bonus = new me.CarsCupcake.SkyblockRemake.abilitys.TestBonus();
				bonus.setPlayer(player);
				return bonus;
			}
			case DisgustingHealing -> {
				bonus = new me.CarsCupcake.SkyblockRemake.abilitys.DisgustingHealing();
				bonus.setPlayer(player);
				return bonus;
			}
			case Dominus -> {
				bonus = new me.CarsCupcake.SkyblockRemake.abilitys.Dominus();
				bonus.setPlayer(player);
				return bonus;
			}
			case HydraStrike -> {
				bonus = new me.CarsCupcake.SkyblockRemake.abilitys.HydraStrike();
				bonus.setPlayer(player);
				return bonus;
			}
			case ArcaneVision -> {
				bonus = new me.CarsCupcake.SkyblockRemake.abilitys.ArcaneVision();
				bonus.setPlayer(player);
				return bonus;
			}
			case Fervor -> {
				bonus = new me.CarsCupcake.SkyblockRemake.abilitys.Fervor();
				bonus.setPlayer(player);
				return bonus;
			}
			case DctrSpaceHelmet -> {
				bonus = new me.CarsCupcake.SkyblockRemake.abilitys.DctrSpaceHelmet();
				bonus.setPlayer(player);
				return bonus;
			}
			case Maid -> {
				bonus = new me.CarsCupcake.SkyblockRemake.abilitys.Maid();
				bonus.setPlayer(player);
				return bonus;
			}
			case BruteForce -> {
				bonus = new me.CarsCupcake.SkyblockRemake.abilitys.BruteForce();
				bonus.setPlayer(player);
				return bonus;
			}
			case StaticCharge -> {
				bonus = new me.CarsCupcake.SkyblockRemake.abilitys.StaticCharge();
				bonus.setPlayer(player);
				return bonus;
			}
			case MagmaLordArmor -> {
				bonus = new me.CarsCupcake.SkyblockRemake.abilitys.MagmaLordArmor();
				bonus.setPlayer(player);
				return bonus;
			}
			case Spirit -> {
				bonus = new me.CarsCupcake.SkyblockRemake.abilitys.Spirit();
				bonus.setPlayer(player);
				return bonus;
			}
			case SuperiorBlood -> {
				bonus = new me.CarsCupcake.SkyblockRemake.abilitys.SuperiorBlood();
				bonus.setPlayer(player);
				return bonus;
			}


		}
		
		return null;
		
	}
}
