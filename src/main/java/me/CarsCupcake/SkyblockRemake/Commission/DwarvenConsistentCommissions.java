package me.CarsCupcake.SkyblockRemake.Commission;

public enum DwarvenConsistentCommissions {
MithrilMiner,
LavaSpringsMithril,
RoyalMinesMithril,
CliffsideVeinsMithril,
RampartsQuarryMithril,
UpperMinesMithril,
TitaniumMiner,
LavaSpringsTitanium,
RoyalMinesTitanium,
CliffsideVeinsTitanium,
RampartsQuarryTitanium,
UpperMinesTitanium,
GoblinSlayer,
IceWalkerSlayer;
	
	public int maxScore() {
		
		switch(this) {
		case CliffsideVeinsMithril:
			return 350;
			
		case CliffsideVeinsTitanium:
			return 10;
		case GoblinSlayer:
			return 350;
		case IceWalkerSlayer:
			return 50;
		case LavaSpringsMithril:
			return 350;
		case LavaSpringsTitanium:
			return 10;
		case MithrilMiner:
			return 500;
		case RampartsQuarryMithril:
			return 350;
		case RampartsQuarryTitanium:
			return 10;
		case RoyalMinesMithril:
			return 350;
		case RoyalMinesTitanium:
			return 10;
		case TitaniumMiner:
			return 15;
		case UpperMinesMithril:
			return 350;
		case UpperMinesTitanium:
			return 10;
		default:
			break;
		
		}
		
		return 0;
	}
	
}

