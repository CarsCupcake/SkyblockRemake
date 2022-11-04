package me.CarsCupcake.SkyblockRemake.Commission;



import me.CarsCupcake.SkyblockRemake.Tools;
import me.CarsCupcake.SkyblockRemake.Areas.DwarvenAreas;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;


public class Commission {

	private final DwarvenCommissions com;
	private int score = 0;
	private final int requiredscore;
	
	public Commission(DwarvenConsistentCommissions com) {
		
		this.com = DwarvenCommissions.valueOf(com.toString());
		requiredscore = com.maxScore();
	}
	public Commission(DwarvenSituationalCommissions com) {
		this.com = DwarvenCommissions.valueOf(com.toString()) ;

		requiredscore = com.maxScore();
	
}
	public Commission(DwarvenCommissions com) {
		this.com = com ;

		requiredscore = com.maxScore();
	
}
public static Commission generateNewConsistentCommision() {
	return new Commission(Tools.randomEnum(DwarvenConsistentCommissions.class));
}
public static Commission generateNewSituationCommision() {
	return new Commission(Tools.randomEnum(DwarvenSituationalCommissions.class));
}
public DwarvenCommissions getComm() {

	
	return com;
}

public void setScore(int value) {
	score = value;
}
public double getPersentage() {
	if(score == 0)
		return 0;
	double pers = (double)score/(double)requiredscore ;
	return pers;
}
public int getScore() {
	return score;
}
public void adOne() {
	score += 1;
}
public boolean isDone() {
	if(score >= requiredscore)
		return true;
	else
		return false;
}

public static void updateMiningCommission(SkyblockPlayer player, boolean isMithril) {
	
	final DwarvenAreas area = player.dwarvenArea;
	for(Commission com : player.Comms) {
	
		if(!com.isDone())
		if(isMithril) {
			switch(com.getComm()) {
			case CliffsideVeinsMithril:
				if(area == DwarvenAreas.CliffsideVeins)
					com.adOne();
				break;
			
			
			case LavaSpringsMithril:
				if(area == DwarvenAreas.LavaSprings)
					com.adOne();
				break;
			
			case MithrilMiner:
				
					com.adOne();
				break;
			
			case RampartsQuarryMithril:
				if(area == DwarvenAreas.RampartsQuarry)
					com.adOne();
				break;
			
			case RoyalMinesMithril:
				if(area == DwarvenAreas.RoyalMines)
					com.adOne();
				break;
			
			
			
			case UpperMinesMithril:
				
				if(area == DwarvenAreas.UpperMines) {
					com.adOne();
				
				}
				break;
			
			default:
				break;
			
			}
			if(com.isDone()) {
				player.sendMessage("§a§lCommission Completed");
			}
		}else {
			switch(com.getComm()) {
			
			case CliffsideVeinsTitanium:
				if(area == DwarvenAreas.CliffsideVeins)
					com.adOne();
				break;
			
			case LavaSpringsTitanium:
				if(area == DwarvenAreas.LavaSprings)
					com.adOne();
				break;
			
			case RampartsQuarryTitanium:
				if(area == DwarvenAreas.RampartsQuarry)
					com.adOne();
				break;
			
			case RoyalMinesTitanium:
				if(area == DwarvenAreas.RoyalMines)
					com.adOne();
				break;
			
			case TitaniumMiner:

					com.adOne();
				break;
			
			case UpperMinesTitanium:
				if(area == DwarvenAreas.UpperMines)
					com.adOne();
				break;
			default:
				break;
			
			}
		}
	}
}

	
}
