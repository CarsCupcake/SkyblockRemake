package me.CarsCupcake.SkyblockRemake.Commission;

import java.util.ArrayList;

public enum DwarvenCommissions {
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
		IceWalkerSlayer,
	GoblinRaidSlayer,
		GoblinRaid,
		Raffle,
		GoldenGoblinSlayer,
		StarSentryPuncher,
		LuckyRaffle;
	 public int maxScore() {
		 switch(this) {
			case CliffsideVeinsMithril:
				return 350;
				
			case CliffsideVeinsTitanium:
				return 10;
			case GoblinSlayer:
				return 100;
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
	 public ArrayList<String> getDesciption() {
		 ArrayList<String> lore = new ArrayList<>();
		 
		 switch(this) {
		case CliffsideVeinsMithril:
			lore.add("§7Mine §a350 §7Mithril Ore in");
			lore.add("§bCliffside Veins");
			return lore;
		case CliffsideVeinsTitanium:
			lore.add("§7Mine §a10 §7Titanium Ore in");
			lore.add("§bCliffside Veins");
			return lore;
		case GoblinRaid:
			lore.add("§7Participate in the §cGoblin Raid");
			lore.add("§7Event");
			return lore;
		case GoblinRaidSlayer:
			lore.add("§7Slay §a20 §cGoblins §7during the");
			lore.add("§cGoblin Raid §7Event.");
		case GoblinSlayer:
			lore.add("§7Slay §a100 §cGoblins");
			return lore;
		case GoldenGoblinSlayer:
			lore.add("§7Slay §a1 §6Golden Goblin");
			return lore;
		case IceWalkerSlayer:
			lore.add("§7Slay §a50 §cIce Walker");
			return lore;
		case LavaSpringsMithril:
			lore.add("§7Mine §a350 §7Mithril Ore in");
			lore.add("§bLava Springs");
			return lore;
		case LavaSpringsTitanium:
			lore.add("§7Mine §a10 §7Titanium Ore in");
			lore.add("§bLava Springs");
			return lore;
		case LuckyRaffle:
			lore.add("§7Deposit §a20 §7during the");
			lore.add("§6Raffle §7Event");
			return lore;
		case MithrilMiner:
			lore.add("§7Mine §a500 §7Mithril Ore");
			return lore;
		case Raffle:
			lore.add("§7Participate in the §6Raffle");
			lore.add("§7Event");
			return lore;
		case RampartsQuarryMithril:
			lore.add("§7Mine §a350 §7Mithril Ore in");
			lore.add("§bRampart's Qarry");
			return lore;
		case RampartsQuarryTitanium:
			lore.add("§7Mine §a10 §7Titanium Ore in");
			lore.add("§bRampart's Qarry");
			return lore;
		case RoyalMinesMithril:
			lore.add("§7Mine §a350 §7Mithril Ore in");
			lore.add("§bRoyal Mines");
			return lore;
		case RoyalMinesTitanium:
			lore.add("§7Mine §a10 §7Titanium Ore in");
			lore.add("§bRoyal Mines");
			return lore;
		case StarSentryPuncher:
			lore.add("§7Damage §5Star Sentrys §710 times");
			return lore;
		case TitaniumMiner:
			lore.add("§7Mine §a15 §7Titanium Ore");
			return lore;
		case UpperMinesMithril:
			lore.add("§7Mine §a350 §7Mithril Ore in");
			lore.add("§bUpper Mines");
			return lore;
		case UpperMinesTitanium:
			lore.add("§7Mine §a10 §7Titanium Ore in");
			lore.add("§bUpper Mines");
			return lore;
		default:
			break;
		 
		 }
		 
		 return lore;
		
	}
}