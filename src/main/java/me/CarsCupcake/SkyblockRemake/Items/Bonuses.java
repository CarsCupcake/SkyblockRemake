package me.CarsCupcake.SkyblockRemake.Items;

import me.CarsCupcake.SkyblockRemake.Items.farming.items.armor.CropieArmor;
import me.CarsCupcake.SkyblockRemake.Items.farming.items.armor.FermentoArmor;
import me.CarsCupcake.SkyblockRemake.Items.farming.items.armor.MelonArmor;
import me.CarsCupcake.SkyblockRemake.Items.farming.items.armor.SquashArmor;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.abilitys.*;

public enum Bonuses {

	DisgustingHealing(new me.CarsCupcake.SkyblockRemake.abilitys.DisgustingHealing()),
	TestBonus(new me.CarsCupcake.SkyblockRemake.abilitys.TestBonus()),
	Dominus(new me.CarsCupcake.SkyblockRemake.abilitys.Dominus()),
	HydraStrike(new me.CarsCupcake.SkyblockRemake.abilitys.HydraStrike()),
	Fervor(new me.CarsCupcake.SkyblockRemake.abilitys.Fervor()),
	ArcaneVision(new me.CarsCupcake.SkyblockRemake.abilitys.ArcaneVision()),
	DctrSpaceHelmet(new DctrSpaceHelmet()),
	Maid(new Maid()),
	BruteForce(new BruteForce()),
	StaticCharge(new StaticCharge()),
	MagmaLordArmor(new MagmaLordArmor()),
	Spirit(new Spirit()),
	SuperiorBlood(new SuperiorBlood()),
	ProtectiveBlood(new ProtectiveBlood()),
	OldBlood(new OldBlood()),
	WiseBlood(new WiseBlood()),
	UnstableBlood(new UnstableBlood()),
	YoungBlood(new YoungBlood()),
	StrongBlood(new StrongBlood()),
	AdminArmor(new AdminArmorAbility()),
	CropierCrops(new MelonArmor()),
	Squashbuckle(new CropieArmor()),
	MentoFermento(new SquashArmor()),
	Feast(new FermentoArmor()),
	ExpertMiner(new ExpertMiner()),
	HolyBlood(new HolyBlood());

	private final FullSetBonus b;
	Bonuses(FullSetBonus bonus) {
		b = bonus;
	}

	public FullSetBonus getBonus(SkyblockPlayer player) {
		FullSetBonus bonus;
		try {
			bonus = b.getClass().newInstance();
			bonus.setPlayer(player);
			return bonus;
		}catch (Exception ignored){}
		return null;


	}
}
